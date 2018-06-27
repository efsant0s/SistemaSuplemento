/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sistemasuplemento.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemasuplemento.model.MarcaSuplemento;

/**
 *
 * @author eduardo_fs1
 */
public class MarcaSuplementoDao {
    public MarcaSuplementoDao() {
    }
    private MarcaSuplemento parse(ResultSet res) throws SQLException {
       MarcaSuplemento model = new MarcaSuplemento();
        
        model.setId(res.getLong("idmarcasuplemento"));
        model.setNome(res.getString("nome"));
        
        return model;
    }
    public MarcaSuplemento find(long id) {
        try {
            String sql = "SELECT idmarcasuplemento, nome FROM tbmarcasuplemento WHERE idmarcasuplemento = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
            MarcaSuplemento model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(MarcaSuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<MarcaSuplemento> findAll() {
        try {
            String sql = "SELECT idmarcasuplemento, nome FROM tbmarcasuplemento";
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<MarcaSuplemento> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
                
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(MarcaSuplementoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public void insert(MarcaSuplemento model) {
        try {
            String sql = "INSERT INTO tbmarcasuplemento (nome) VALUES (?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbmarcasuplemento WHERE idmarcasuplemento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(MarcaSuplemento model) {
        try {
            String sql = "UPDATE tbmarcasuplemento SET nome=? WHERE idmarcasuplemento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.setLong(2, model.getId());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcaSuplemento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
