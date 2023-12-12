package model;


import static model.DAO.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author a248488
*/
public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;

    private ConsultaDAO() {
        getConnection();
        createTable();
    }

    // Singleton
    public static ConsultaDAO getInstance() {
        return (instance == null ? (instance = new ConsultaDAO()) : instance);
    }

// CRUD    
    public Consulta create(Calendar data, int hora, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou) throws ParseException {
        try {
            java.util.Date dt = new Date(data.getTimeInMillis());
            java.sql.Date d = new java.sql.Date(dt.getTime());
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, id_tratamento, terminado) VALUES (?,?,?,?,?,?,?)");
            stmt.setDate(1, d);
            stmt.setInt(2, hora);
            stmt.setString(3, comentarios);
            stmt.setInt(4, idAnimal);
            stmt.setInt(5, idVet);
            stmt.setInt(6, idTratamento);
            stmt.setBoolean(7, terminou);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    }
    
    
     public boolean isHorarioDisponivel(Calendar data, int idVet, int hora) {
        // Implementação do método
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT COUNT(*) FROM consulta WHERE id_vet = ? AND data = ? AND horario = ?");
            stmt.setInt(1, idVet);
            stmt.setDate(2, new java.sql.Date(data.getTimeInMillis()));
            stmt.setInt(3, hora);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0; // Retorna true se não houver consultas no horário
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return false;
    }
    
    private Consulta buildObject(ResultSet rs) throws ParseException {
        Consulta consulta = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(rs.getDate("data"));
            
            consulta = new Consulta(rs.getInt("id"), dt, rs.getInt("horario"), rs.getString("comentario"), rs.getInt("id_animal"), rs.getInt("id_vet"), rs.getInt("id_tratamento"), rs.getBoolean("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }

    // Generic Retriever
    public List retrieve(String query) throws ParseException {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        if (rs != null) {
            try {
                while (rs.next()) {
                    consultas.add(buildObject(rs));
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        return consultas;
    }
    
    // RetrieveAll
    public List retrieveAll() throws ParseException {
        return this.retrieve("SELECT * FROM consulta ORDER BY data");
    }
    
    // RetrieveLast
    public List retrieveLast() throws ParseException{
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta","id"));
    }

    // RetrieveById
    public Consulta retrieveById(int id) throws ParseException {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarComment(String comentario) throws ParseException {
        return this.retrieve("SELECT * FROM consulta WHERE comentario LIKE '%" + comentario + "%'");
    }    
        
    // Update
    public void update(Consulta consulta) {
        try {
            java.util.Date dt = new Date(consulta.getData().getTimeInMillis());
            java.sql.Date d = new java.sql.Date(dt.getTime());
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, id_tratamento=?, terminado=?  WHERE id=?");
            stmt.setDate(1, d);
            stmt.setInt(2, consulta.getHora());
            stmt.setString(3, consulta.getComentarios());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setBoolean(7, consulta.isTerminou());
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
        // Delete   
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteByAnimalId(int animalId) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id_animal = ?");
            stmt.setInt(1, animalId);
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
}

