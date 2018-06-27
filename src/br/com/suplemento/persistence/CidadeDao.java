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
import br.com.suplemento.model.Cidade;
import br.com.suplemento.model.Estado;


/**
 *
 * @author eduardo_fs1
 */
public class CidadeDao {
    public CidadeDao(){
        
    }
    private Cidade parse(ResultSet res) throws SQLException {
       Cidade model = new Cidade();
       EstadoDao daoEstado = new EstadoDao();
        
        model.setIdCidade(res.getLong("idcidade"));
        model.setCodigo(res.getString("codigo"));
        model.setNome(res.getString("nome"));
        if (res.getLong("idestado") != 0) {
                Estado estado = daoEstado.find(res.getLong("idestado"));
                model.setEstado(estado);
            }
       
        
        return model;
    }
    public Cidade find(long id) {
        try {
            String sql = "SELECT idcidade, codigo, nome, idestado FROM tbcidade WHERE idcidade = ?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            ResultSet res = stm.executeQuery();
            res.next();
           Cidade model = parse(res);
            
            res.close();
            stm.close();
            conn.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     public List<Cidade> findAll() {
        try {
            String sql = "SELECT idcidade, codigo, nome, idestado FROM tbcidade";
            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            
            List<Cidade> registros = new ArrayList<>();
            
            while (res.next()) {
                registros.add(parse(res));
                
            }
            res.close();
            stm.close();
            conn.close();
            
            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public void insert(Cidade model) {
        try {
            String sql = "INSERT INTO tbcidade (codigo, nome, idestado) VALUES (?,?,?)";
            
            Connection conn = DbUtils.getConnection();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            if (model.getEstado()== null) {
                stm.setNull(3, Types.BIGINT);
            } else {
                stm.setLong(3, model.getEstado().getIdEstado());
            }
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbcidade WHERE idcidade=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Cidade model) {
        try {
            String sql = "UPDATE tbcidade SET codigo=?, nome=? WHERE idcidade=?";
            
            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            if (model.getEstado()== null) {
                stm.setNull(3, Types.BIGINT);
            } else {
                stm.setLong(3, model.getEstado().getIdEstado());
            }
            stm.setLong(4, model.getIdCidade());
            stm.execute();
            
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Cidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
