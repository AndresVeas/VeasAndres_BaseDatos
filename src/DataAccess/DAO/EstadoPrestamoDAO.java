package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.EstadoPrestamoDTO;
import DataAccess.IDAO;

public class EstadoPrestamoDAO extends SQLiteDataHelper implements IDAO <EstadoPrestamoDTO> {
    
    @Override
    public EstadoPrestamoDTO readBy(Integer id) throws Exception {
        EstadoPrestamoDTO dto = new EstadoPrestamoDTO();
        String query =" SELECT IdEstadoPrestamo     " 
                     +"       ,Nombre               " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  EstadoPrestamo        "
                     +" WHERE IdEstadoPrestamo =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new EstadoPrestamoDTO( rs.getInt(1)           // IdEstadoPrestamo
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5));      // FechaMod
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<EstadoPrestamoDTO> readAll() throws Exception {
        EstadoPrestamoDTO dto;
        List<EstadoPrestamoDTO> lst = new ArrayList<>();
        String query =" SELECT IdEstadoPrestamo     " 
                     +"       ,Nombre               " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  EstadoPrestamo        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new EstadoPrestamoDTO( rs.getInt(1)           // IdEstadoPrestamo
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5));      // FechaMod
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(EstadoPrestamoDTO entity) throws Exception {
        String query = " INSERT INTO EstadoPrestamo (Nombre) VALUES (?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(EstadoPrestamoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE EstadoPrestamo SET Nombre = ?, FechaModifica = ? WHERE IdEstadoPrestamo = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdEstadoPrestamo());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e; 
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
            return true;
    }

}
