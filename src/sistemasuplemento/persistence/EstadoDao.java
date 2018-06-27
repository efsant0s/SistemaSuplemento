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
import sistemasuplemento.model.Estado;



/**
 *
 * @author eduardo_fs1
 */
public class EstadoDao {
    public EstadoDao(){
        
    }
    private Estado parse(ResultSet res) throws SQLException {
       Estado model = new Estado();
        
        model.setIdEstado(res.getLong("idestado"));
        model.setCodigo(res.getString("codigo"));
        model.setNome(res.getString("nome"));
        
       
        
        return model;
    }
    public Estado find(long id) {
        try {
            String sql = "SELECT idestado, codigo, nome FROM tbestado WHERE idestado = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
            Estado model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public List<Estado> findAll() {
        try {
            String sql = "SELECT idestado, codigo, nome FROM tbestado";
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<Estado> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
                
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(EstadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public void insert(Estado model) {
        try {
            String sql = "INSERT INTO tbestado (codigo, nome) VALUES (?,?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbestado WHERE idestado=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Estado model) {
        try {
            String sql = "UPDATE tbestado SET codigo=?, nome=? WHERE idestado=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            stm.setLong(3, model.getIdEstado());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
