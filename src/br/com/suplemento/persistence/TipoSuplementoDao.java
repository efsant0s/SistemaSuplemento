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
import br.com.suplemento.model.TipoSuplemento;


/**
 *
 * @author eduardo_fs1
 */
public class TipoSuplementoDao {
    public TipoSuplementoDao(){
        
    }
    private TipoSuplemento parse(ResultSet res) throws SQLException {
       TipoSuplemento model = new TipoSuplemento();
        
        model.setId(res.getLong("idtiposuplemento"));
        model.setNome(res.getString("nome"));
        
        return model;
    }
    public TipoSuplemento find(long id) {
        try {
            String sql = "SELECT idtiposuplemento, nome FROM tbtiposuplemento WHERE idtiposuplemento = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
            TipoSuplemento model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(TipoSuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<TipoSuplemento> findAll() {
        try {
            String sql = "SELECT idtiposuplemento, nome FROM tbtiposuplemento";
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<TipoSuplemento> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
                
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(TipoSuplementoDao
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public void insert(TipoSuplemento model) {
        try {
            String sql = "INSERT INTO tbtiposuplemento (nome) VALUES (?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbtiposuplemento WHERE idtiposuplemento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(TipoSuplemento model) {
        try {
            String sql = "UPDATE tbtiposuplemento SET nome=? WHERE idtiposuplemento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.setLong(2, model.getId());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TipoSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
