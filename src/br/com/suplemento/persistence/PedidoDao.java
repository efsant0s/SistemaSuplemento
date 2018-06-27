/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.suplemento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.suplemento.model.Pedido;
import br.com.suplemento.model.PedidoItem;

/**
 *
 * @author eduardo_fs1
 */
public class PedidoDao {
private ClienteDao daoCliente = null;
    private SuplementoDao daoSuplemento = null;
private FormaPagtoDao daoForma = null;
private CondicaoPagtoDao daoCondicao = null;

private CondicaoPagtoDao getDaoCondicao(){
   if (daoCondicao == null) {
            daoCondicao = new CondicaoPagtoDao();
        }
        return daoCondicao; 
}
private FormaPagtoDao getDaoForma(){
   if (daoForma == null) {
            daoForma = new FormaPagtoDao();
        }
        return daoForma; 
}

    private ClienteDao getDaoCliente() {
        if (daoCliente == null) {
            daoCliente = new ClienteDao();
        }
        return daoCliente;
    }

    private SuplementoDao getDaoSuplemento() {
        if (daoSuplemento == null) {
            daoSuplemento = new SuplementoDao();
        }
        return daoSuplemento;
    }

    private Pedido parse(ResultSet res) throws SQLException {
        Pedido model = new Pedido();

        model.setIdPedido(res.getLong("idpedido"));
        
        model.setNumero(res.getLong("numero"));
        model.setEmissao(res.getDate("dataemissao"));
        model.setAprovacao(res.getDate("aprovacao"));        
        model.setValorTotal(res.getDouble("ValorTotal"));
        model.setCondicaoPagto(getDaoCondicao().find(res.getLong("idCondicaopagamento")));
        model.setCliente(getDaoCliente().find(res.getLong("idcliente")));
        model.setFormaPagto(getDaoForma().find(res.getLong("idformapagamento")));
        return model;
    }

    private PedidoItem parseItem(ResultSet res) throws SQLException {
        PedidoItem model = new PedidoItem();

        model.setId(res.getLong("idPedidoItem"));
        
        model.setSuplemento(getDaoSuplemento().find(res.getLong("idproduto")));
        model.setOrdem(res.getInt("ordem"));
        model.setQuantidade(res.getDouble("quantidade"));
        model.setValorUnitario(res.getDouble("valorunitario"));
        model.setValorTotal(res.getDouble("valortotal"));

        return model;
    }

    public Pedido find(Long id) {

        final String sql = "SELECT idpedido, numero, dataemissao, aprovacao, valortotal, idcondicaopagamento, idcliente, idformapagamento  FROM tbpedido WHERE idpedido = ?";
        Pedido model = null;

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet res = stm.executeQuery()) {
                    res.next();
                    model = parse(res);
                    model.setItens(findItens(id));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    private List<PedidoItem> findItens(Long id) {
        
        final String sql = "SELECT idpedidoitem, idproduto, ordem, quantidade, valorunitario, valortotal "
                         + " FROM tbpedidoitem WHERE idpedido = ? ORDER BY ordem";
        List<PedidoItem> itens = new ArrayList<>();
        
        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, id);
                try (ResultSet res = stm.executeQuery()) {
                    while (res.next()) {
                        PedidoItem item = parseItem(res);
                        itens.add(item);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itens;
    }

    public List<Pedido> findAll() {

        final String sql = "SELECT idpedido, numero, dataemissao, aprovacao, valortotal, idcondicaopagamento, idcliente, idformapagamento FROM tbpedido ORDER BY dataemissao DESC";
        List<Pedido> records = new ArrayList<>();

        try (Connection conn = DbUtils.getConnection()) {
            try (Statement stm = conn.createStatement()) {
                try (ResultSet res = stm.executeQuery(sql)) {
                    while (res.next()) {
                        Pedido model = parse(res);
                        records.add(model);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public void insert(Pedido model) {
        final String sql = "INSERT INTO tbpedido (numero, dataemissao, valortotal, idcondicaopagamento, idcliente, idformapagamento) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, model.getNumero());
                stm.setTimestamp(2, new Timestamp(model.getEmissao().getTime()));
                
                stm.setDouble(3, model.getValorTotal());
                
                if (model.getCondicaoPagto()== null) {
                stm.setNull(4, Types.BIGINT);
            } else {
                stm.setLong(4, model.getCondicaoPagto().getId());
            }
                stm.setLong(5, model.getCliente().getIdCliente());
                
            if (model.getFormaPagto()== null) {
                stm.setNull(6, Types.BIGINT);
            } else {
                stm.setLong(6, model.getFormaPagto().getId());
            }

                stm.execute();
                
                Long idPedido = findUltimoId(conn);
                insertItens(idPedido, model.getItens());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Pedido model) {

        final String sql = "UPDATE tbpedido SET numero = ?, aprovacao = ?, valortotal = ?, idcondicaopagamento = ?, idcliente = ?, idformapagamento = ? WHERE idPedido = ?";

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, model.getNumero());
                if (model.getAprovacao() == null) {
                    stm.setNull(2, Types.TIMESTAMP);
                } else {
                    stm.setTimestamp(2, new Timestamp(model.getAprovacao().getTime()));
                }
                stm.setDouble(3, model.getValorTotal());
                
                if (model.getCondicaoPagto()== null) {
                stm.setNull(4, Types.BIGINT);
            } else { 
                stm.setLong(4, model.getCondicaoPagto().getId());
            }
                stm.setLong(5, model.getCliente().getIdCliente());
                
            if (model.getFormaPagto()== null) {
                stm.setNull(6, Types.BIGINT);
            } else {
                stm.setLong(6, model.getFormaPagto().getId());
            }
            stm.setLong(7, model.getIdPedido());

                stm.execute();
                
                
                removeAllItens(model.getIdPedido());
                insertItens(model.getIdPedido(), model.getItens());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Pedido model) {
        delete(model.getIdPedido());
    }


    public void delete(Long id) {

        final String sql = "DELETE FROM tbpedido WHERE idPedido = ?";

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, id);

                stm.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Long findUltimoId(Connection conn) throws SQLException {
        final String sql = "SELECT LAST_INSERT_ID()";
        
        ResultSet res = conn.createStatement().executeQuery(sql);
        res.next();
        return res.getLong(1);
    }

    private void insertItens(Long idPedido, List<PedidoItem> itens) throws SQLException {
        for (PedidoItem pedidoItem : itens) {
            insertItem(idPedido, pedidoItem);
        }
    }

    private void insertItem(Long idPedido, PedidoItem pedidoItem) throws SQLException {
        final String sql = "INSERT INTO tbpedidoitem (ordem, idpedido, idproduto, quantidade, valorunitario, valortotal) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setInt(1, pedidoItem.getOrdem());
                stm.setLong(2, idPedido);
                stm.setLong(3, pedidoItem.getSuplemento().getIdProduto());
                stm.setDouble(4, pedidoItem.getQuantidade());
                stm.setDouble(5, pedidoItem.getValorUnitario());
                stm.setDouble(6, pedidoItem.getValorTotal());
                
                stm.execute();
            }
        }
    }

    private void removeAllItens(Long id) throws SQLException {
        final String sql = "DELETE FROM tbpedidoitem WHERE idpedido = ?";

        try (Connection conn = DbUtils.getConnection()) {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setLong(1, id);

                stm.execute();
            }
        }
    }

}    


