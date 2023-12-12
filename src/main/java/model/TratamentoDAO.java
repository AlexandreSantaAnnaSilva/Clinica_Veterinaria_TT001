package model;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author a248488
*/
public class TratamentoDAO extends DAO {
    private static TratamentoDAO instance;

    private TratamentoDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static TratamentoDAO getInstance() {
        return (instance == null ? (instance = new TratamentoDAO()) : instance);
    }

// CRUD    
    public Tratamento create(int id_animal, String nome, Date dataIni, Date dataFim, int terminado) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        
        String maskDataIni = dateFormat.format(dataIni);
        String maskDataFim = dateFormat.format(dataFim);
        
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO tratamento (id_animal, nome, dataIni, dataFim, terminado) VALUES (?,?,?,?,?)");
            stmt.setInt(1, id_animal);
            stmt.setString(2, nome);
            stmt.setString(3, maskDataIni);
            stmt.setString(4, maskDataFim);
            stmt.setInt(5, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento","id"));
    }
    
    public boolean isLastEmpty() throws ParseException{
        Tratamento lastTratamento = this.retrieveById(lastId("tratamento","id"));
        if (lastTratamento != null) {
            return lastTratamento.getNome().isBlank();
        }
        return false;
    }

    private Tratamento buildObject(ResultSet rs) throws ParseException {
        Tratamento tratamento = null;
        try {
            String maskDataIni = rs.getString("dataIni");
            String maskDataFim = rs.getString("dataFim");  
            Date dataIni = new SimpleDateFormat("yyyy-mm-dd").parse(maskDataIni);
            Date dataFim = new SimpleDateFormat("yyyy-mm-dd").parse(maskDataFim);  
            tratamento = new Tratamento(rs.getInt("id"), rs.getString("nome"), dataIni, dataFim, rs.getInt("id_animal"), rs.getBoolean("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }

    // Generic Retriever
    public List retrieve(String query) throws ParseException {
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                tratamentos.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }
    
    // RetrieveAll
    public List retrieveAll() throws ParseException {
        return this.retrieve("SELECT * FROM tratamento");
    }
    
    // RetrieveLast
    public List retrieveLast() throws ParseException{
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento","id"));
    }

    // RetrieveById
    public Tratamento retrieveById(int id) throws ParseException {
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (tratamentos.isEmpty() ? null : tratamentos.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) throws ParseException {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Update
    public void update(Tratamento tratamento) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        
            String maskDataIni = dateFormat.format(tratamento.getData_incio());
            String maskDataFim = dateFormat.format(tratamento.getData_fim());
            
            
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataIni=?, dataFim=?, terminado=? WHERE id=?");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setString(2, tratamento.getNome());
            stmt.setString(3, maskDataIni);
            stmt.setString(4, maskDataFim);
            stmt.setBoolean(5, tratamento.isTerminou());
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
        // Delete   
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

