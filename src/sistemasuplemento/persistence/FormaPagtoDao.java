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
import sistemasuplemento.model.FormaPagto;

/**
 *
 * @author Gabriel_Farias_Mart1
 */
public class FormaPagtoDao {
    
    public FormaPagtoDao () {
        
    }


    private FormaPagto parse(ResultSet res) throws SQLException {
    FormaPagto model = new FormaPagto ();

    model.setId(res.getLong("idformapagamento"));
    model.setNome(res.getString("nome"));
    model.setCodigo(res.getString("codigo"));
    
    
    return model;
    
}
    public FormaPagto find(long id) {
        try {
            String sql = "SELECT idformapagamento, nome, codigo FROM tbformapagamento WHERE idformapagamento = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
            FormaPagto model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
public List<FormaPagto> findAll() {
        try {
            String sql = "SELECT idformapagamento, nome, codigo FROM tbformapagamento";
            
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<FormaPagto> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        }
            catch (SQLException ex) {
            Logger.getLogger(FormaPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
public void insert(FormaPagto model) {
        try {
            String sql = "INSERT INTO tbformapagamento (nome, codigo) VALUES (?, ?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getNome());
            stm.setString(2, model.getCodigo());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void update(FormaPagto model) {
        try { 
            String sql = "UPDATE tbformapagamento SET nome=?, codigo=? WHERE idformapagamento=?";
            
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
            String sql = "DELETE FROM tbformapagamento WHERE idformapagamento=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormaPagtoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}



