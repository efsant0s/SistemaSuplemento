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
import br.com.suplemento.model.Cliente;

public class ClienteDao {
    public ClienteDao(){
        
    }
    private Cliente parse(ResultSet res) {

        try {
            CidadeDao daoCidade = new CidadeDao();
           

            Cliente model = new Cliente();

            model.setIdCliente(res.getLong("idcliente"));
            model.setCodigo(res.getString("codigo"));
            model.setNome(res.getString("nome"));
            model.setTelefone(res.getString("telefone"));
            model.setEndereco(res.getString("endereco")); // Possivel erro
            model.setDataNascimento(res.getString("datanascimento"));
            model.setEmail(res.getString("email"));
            

            if (res.getLong("idcidade") != 0) {
                Cidade cidade = daoCidade.find(res.getLong("idcidade"));
                model.setCidade(cidade);
            }
         

            return model;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Cliente find(long id) {
        try {
            String sql = "SELECT idcliente, codigo, nome, telefone, endereco, datanascimento, email, idcidade "
                    + " FROM tbcliente WHERE idcliente = ?";

            Connection conn = DbUtils.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setLong(1, id);

            ResultSet res = stm.executeQuery();
            res.next();

            Cliente model = parse(res);

            res.close();
            stm.close();
            conn.close();

            return model;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Cliente> findAll() {
        try {
            String sql = "SELECT idcliente, codigo, nome, telefone, endereco, datanascimento, email, idcidade FROM tbcliente ORDER BY codigo";

            Connection conn = DbUtils.getConnection();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            List<Cliente> registros = new ArrayList<>();

            while (res.next()) {
                registros.add(parse(res));
            }

            res.close();
            stm.close();
            conn.close();

            return registros;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void insert(Cliente model) {
        try {
            String sql = "INSERT INTO tbcliente (codigo, nome, telefone, endereco, datanascimento, email, idcidade)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

            Connection conn = DbUtils.getConnection();

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            stm.setString(3, model.getTelefone());
            stm.setString(4, model.getEndereco());
            stm.setString(5, model.getDataNascimento());
            stm.setString(6, model.getEmail());
            
            if (model.getCidade()== null) {
                stm.setNull(7, Types.BIGINT);
            } else {
                stm.setLong(7, model.getCidade().getIdCidade());
            }
            
            stm.execute();

            stm.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void update(Cliente model) {
        try {
            String sql = "UPDATE tbcliente SET codigo=?, nome=?, telefone=?, endereco=?, datanascimento=?, email=?, idcidade=? WHERE idcliente=?";


            Connection conn = DbUtils.getConnection();

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, model.getCodigo());
            stm.setString(2, model.getNome());
            stm.setString(3, model.getTelefone());
            stm.setString(4, model.getEndereco());
            stm.setString(5, model.getDataNascimento());
            stm.setString(6, model.getEmail());
            
            if (model.getCidade()== null) {
                stm.setNull(7, Types.BIGINT);
            } else {
                stm.setLong(7, model.getCidade().getIdCidade());
            }
            stm.setLong(8, model.getIdCliente());
            
            stm.execute();

            stm.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void delete(long id) {
        try {
            String sql = "DELETE FROM tbcliente WHERE idcliente=?";

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
