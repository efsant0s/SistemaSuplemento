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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.suplemento.model.MarcaSuplemento;
import br.com.suplemento.model.Suplemento;
import br.com.suplemento.model.TipoSuplemento;

/**
 *
 * @author eduardo_fs1
 */
public class SuplementoDao {
    public SuplementoDao() {
    }

    private Suplemento parse(ResultSet res) {

        try {
            TipoSuplementoDao daoTipo = new TipoSuplementoDao();
           MarcaSuplementoDao daoMarca = new MarcaSuplementoDao();

            Suplemento model = new Suplemento();

            model.setIdProduto(res.getLong("idproduto"));
            model.setCodigo(res.getString("codigo"));
            model.setNome(res.getString("nome"));
            model.setPreco(res.getDouble("preco")); // Possivel erro
            model.setQuantidade(res.getDouble("quantidade"));
            

            if (res.getLong("idtiposuplemento") != 0) {
                TipoSuplemento tipo = daoTipo.find(res.getLong("idtiposuplemento"));
                model.setTipoSuplemento(tipo);
            }
            if (res.getLong("idmarcasuplemento") != 0) {
                MarcaSuplemento marca = daoMarca.find(res.getLong("idmarcasuplemento"));
                model.setMarcaSuplemento(marca);
            }

            return model;
        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Suplemento find(long id) {
        try {
            String sql = "SELECT idproduto, codigo, nome, preco, quantidade, idtiposuplemento, idmarcasuplemento "
                    + " FROM tbsuplemento WHERE idproduto = ?";

            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);

            ResultSet res = stm.executeQuery();
            res.next();

            Suplemento model = parse(res);

            res.close();
            stm.close();
            conn.close();

            return model;
        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Suplemento> findAll() {
        try {
            String sql = "SELECT idproduto, codigo, nome, preco, quantidade, idtiposuplemento, idmarcasuplemento "
                    + "FROM tbsuplemento ORDER BY codigo";

            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            List<Suplemento> registros = new ArrayList<>();

            while (res.next()) {
                registros.add(parse(res));
            }

            res.close();
            stm.close();
            conn.close();

            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Suplemento model) {
        try {
            String sql = "INSERT INTO tbsuplemento (codigo, nome, preco, quantidade, idmarcasuplemento, idtiposuplemento )"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

            Connection conn = DbUtils.getConnection();

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            stm.setDouble(3, model.getPreco());
            stm.setDouble(4, model.getQuantidade());
            
            if (model.getMarcaSuplemento()== null) {
                stm.setNull(5, Types.BIGINT);
            } else {
                stm.setLong(5, model.getMarcaSuplemento().getId());
            }
            if (model.getTipoSuplemento()== null) {
                stm.setNull(6, Types.BIGINT);
            } else {
                stm.setLong(6, model.getTipoSuplemento().getId());
            }
            
            stm.execute();

            stm.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Suplemento model) {
        try {
            String sql = "UPDATE tbsuplemento SET codigo=?, nome=?,  preco=?, quantidade=?, idtiposuplemento=?, idmarcasuplemento=? WHERE idproduto=?";


            Connection conn = DbUtils.getConnection();

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            stm.setDouble(3, model.getPreco());
            stm.setDouble(4, model.getQuantidade());
            
            if (model.getMarcaSuplemento()== null) {
                stm.setNull(5, Types.BIGINT);
            } else {
                stm.setLong(5, model.getMarcaSuplemento().getId());
            }
            if (model.getTipoSuplemento()== null) {
                stm.setNull(6, Types.BIGINT);
            } else {
                stm.setLong(6, model.getTipoSuplemento().getId());
            }
            stm.setLong(7, model.getIdProduto());

            stm.execute();

            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Suplemento model) {
        delete(model.getIdProduto());
    }

    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbsuplemento WHERE idproduto=?";

            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            stm.execute();
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
