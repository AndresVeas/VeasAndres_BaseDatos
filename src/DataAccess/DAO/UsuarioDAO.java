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

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.UsuarioDTO;

public class UsuarioDAO extends SQLiteDataHelper implements IDAO <UsuarioDTO> {
    @Override
    public UsuarioDTO readBy(Integer id) throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        String query =" SELECT IdUsuario     " 
                     +"       ,Nombre               " 
                     +"       ,Apellido             " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  Usuario        "
                     +" WHERE IdUsuario =    " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new UsuarioDTO( rs.getInt(1)           // IdUsuario
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5)
                                    ,rs.getString(6));      // FechaMod
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<UsuarioDTO> readAll() throws Exception {
        UsuarioDTO dto;
        List<UsuarioDTO> lst = new ArrayList<>();
        String query =" SELECT IdUsuario     " 
                     +"       ,Nombre               " 
                     +"       ,Apellido             " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  Usuario        ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new UsuarioDTO( rs.getInt(1)           // IdUsuario
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5)      // FechaMod
                                    ,rs.getString(6));      // FechaMod
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(UsuarioDTO entity) throws Exception {
        String query = " INSERT INTO Usuario (Nombre,Apellido,Estado) VALUES (?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, entity.getEstado());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(UsuarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Usuario SET Nombre = ?,Apellido = ?, FechaModificacion = ? , Estado = ? WHERE IdUsuario = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getApellido());
            pstmt.setString(3, dtf.format(now).toString());
            pstmt.setString(4, entity.getEstado());
            pstmt.setInt(5, entity.getIdUsuario());
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


    
    public Integer getMaxRow()  throws Exception  {
        String query =" SELECT COUNT(*) TotalReg FROM Usuario";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                return rs.getInt(1);                    // TotalReg
            }
        } 
        catch (SQLException e) {
            throw e; 
        }
        return 0;
    }
}
