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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.suplemento.model.CondicaoPagto;

/**
 *
 * @author Gabriel_Farias_Mart1
 */
public class CondicaoPagtoDao {
    
    public CondicaoPagtoDao () {
        
    }


    private CondicaoPagto parse(ResultSet res) throws SQLException {
    CondicaoPagto model = new CondicaoPagto ();

    model.setId(res.getLong("idcondicaopagamento"));
    model.setNome(res.getString("nome"));
    model.setCodigo(res.getString("codigo"));
    
    
    return model;
    
}
    public CondicaoPagto find(long id) {
        try {
            String sql = "SELECT idcondicaopagamento, nome, codigo FROM tbcondicaopagamento WHERE idcondicaopagamento = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
            CondicaoPagto model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(CondicaoPagto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
public List<CondicaoPagto> findAll() {
        try {
            String sql = "SELECT idcondicaopagamento, nome, codigo FROM tbcondicaopagamento";
            
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<CondicaoPagto> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        }
            catch (SQLException ex) {
            Logger.getLogger(CondicaoPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
public void insert(CondicaoPagto model) {
        try {
            String sql = "INSERT INTO tbcondicaopagamento (nome, codigo) VALUES (?, ?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.setString(2, model.getCodigo());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CondicaoPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void update(CondicaoPagto model) {
        try { 
            String sql = "UPDATE tbcondicaopagamento SET nome=?, codigo=? WHERE idcondicaopagamento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.setString(2, model.getCodigo());
            stm.setLong(3, model.getId());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void delete(long id) {
        try {
            String sql = "DELETE FROM tbcondicaopagamento WHERE idcondicaopagamento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CondicaoPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

