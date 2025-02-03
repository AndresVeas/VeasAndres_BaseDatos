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
import DataAccess.DTO.LibroDTO;

public class LibroDAO extends SQLiteDataHelper implements IDAO <LibroDTO> {
@Override
    public LibroDTO readBy(Integer id) throws Exception {
        LibroDTO dto = new LibroDTO();
        String query =" SELECT IdLibro              " 
                     +"       ,NombreLibro          " 
                     +"       ,NombreAutor          " 
                     +"       ,ApellidoAutor        " 
                     +"       ,Editorial            " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  Libro                 "
                     +" WHERE IdLibro =             " + id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new LibroDTO(  rs.getInt(1)           // IdLibro
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5)
                                    ,rs.getString(6)      // FechaMod
                                    ,rs.getString(7)      // FechaMod
                                    ,rs.getString(8));      // FechaMod
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<LibroDTO> readAll() throws Exception {
        LibroDTO dto;
        List<LibroDTO> lst = new ArrayList<>();
        String query =" SELECT IdLibro              " 
                     +"       ,NombreLibro          " 
                     +"       ,NombreAutor          " 
                     +"       ,ApellidoAutor        " 
                     +"       ,Editorial            " 
                     +"       ,Estado               " 
                     +"       ,FechaCreacion        " 
                     +"       ,FechaModificacion    " 
                     +" FROM  Libro                 ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new LibroDTO(  rs.getInt(1)           // IdLibro
                                    ,rs.getString(2)        // Nombre            
                                    ,rs.getString(3)        // Estado      
                                    ,rs.getString(4)        // FechaCrea
                                    ,rs.getString(5)
                                    ,rs.getString(6)      // FechaMod
                                    ,rs.getString(7)      // FechaMod
                                    ,rs.getString(8));      // FechaMod
                lst.add(dto);
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(LibroDTO entity) throws Exception {
        String query = " INSERT INTO Libro (NombreLibro, NombreAutor, ApellidoAutor, Editorial,Estado) VALUES (?,?,?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreLibro());
            pstmt.setString(2, entity.getNombreAutor());
            pstmt.setString(3, entity.getApellidoAutor());
            pstmt.setString(4, entity.getEditorial());
            pstmt.setString(5, entity.getEstado());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(LibroDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE Libro SET "
                      +" NombreLibro         = ? "
                      +",NombreAutor         = ? "
                      +",ApellidoAutor       = ? "
                      +",Editorial           = ? "
                      +",Estado              = ? "
                      +",FechaModificacion   = ? "
                      +"WHERE IdLibro        = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombreLibro());
            pstmt.setString(2, entity.getNombreAutor());
            pstmt.setString(3, entity.getApellidoAutor());
            pstmt.setString(4, entity.getEditorial());
            pstmt.setString(5, entity.getEstado());
            pstmt.setString(6, dtf.format(now).toString());
            pstmt.setInt(7, entity.getIdLibro());
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


        String query =" SELECT COUNT(*) TotalReg FROM Libro";
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
