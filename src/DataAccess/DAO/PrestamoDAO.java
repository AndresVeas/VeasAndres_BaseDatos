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
import DataAccess.DTO.PrestamoDTO;

public class PrestamoDAO extends SQLiteDataHelper implements IDAO<PrestamoDTO>{
    @Override
    public PrestamoDTO readBy(Integer id) throws Exception {
        PrestamoDTO dto = new PrestamoDTO();
        String query =   "SELECT                                          "        
                        +" p.IdPrestamo           AS 'Numero de Prestamo' "
                        +",u.Nombre               AS 'Nombre Usuario'     "
                        +",u.Apellido             AS 'Apellido Usuario'   "
                        +",l.NombreLibro          AS 'Nombre del Libro'   "
                        +",e.Nombre               AS 'Estado de Prestamo' "
                        +",p.FechaDevolucion                              "
                        +"FROM Prestamo           AS p                    "
                        +"JOIN Usuario            AS u ON p.IdUsuario         = u.IdUsuario "
                        +"JOIN Libro              AS l ON p.IdLibro           = l.IdLibro   "
                        +"JOIN EstadoPrestamo     AS e ON p.IdEstadoPrestamo  = e.IdEstadoPrestamo "
                        +"WHERE p.IdPrestamo = " + id.toString();
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new PrestamoDTO(  rs.getInt("Numero de Prestamo"),    // IdPrestamo
                                        rs.getString("Nombre Usuario"),     // Nombre Usuario
                                        rs.getString("Apellido Usuario"),   // Apellido Usuario
                                        rs.getString("Nombre del Libro"),   // Nombre del Libro
                                        rs.getString("Estado de Prestamo"), // Estado de Prestamo
                                        rs.getString("FechaDevolucion") );       // FechaCrea
            }
        } 
        catch (SQLException e) {
            throw e;
        }
        return dto;
    }



    @Override
public List<PrestamoDTO> readAll() throws Exception {
    List<PrestamoDTO> lst = new ArrayList<>();
    String query = "SELECT * FROM ViewPrestamo";
    try {
        Connection conn = openConnection();         // Conectar a DB     
        Statement stmt = conn.createStatement();    // CRUD : select * ...    
        ResultSet rs = stmt.executeQuery(query);    // Ejecutar la consulta
        while (rs.next()) {
            PrestamoDTO dto = new PrestamoDTO(
                rs.getInt("Numero de Prestamo"),    // IdPrestamo
                rs.getString("Nombre Usuario"),     // Nombre Usuario
                rs.getString("Apellido Usuario"),   // Apellido Usuario
                rs.getString("Nombre del Libro"),   // Nombre del Libro
                rs.getString("Estado de Prestamo"), // Estado de Prestamo
                rs.getString("FechaDevolucion")     // FechaDevolucion
            );
            lst.add(dto);
        }
    } catch (SQLException e) {
        throw e;
    }
    return lst;
}

    @Override
    public boolean create(PrestamoDTO entity) throws Exception {
        String query = "INSERT INTO Prestamo (NombreUsuario, ApellidoUsuario, NombreLibro, EstadoPrestamo) VALUES (?, ?, ?, ?)";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, entity.getNombreUsuario());
            pstmt.setString(2, entity.getApellidoUsuario());
            pstmt.setString(3, entity.getNombreLibro());
            pstmt.setString(4, entity.getEstadoPrestamo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(PrestamoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Prestamo SET IdPrestamo = ?, IdUsuario = ?, IdLibro = ?, IdEstadoPrestamo = ?, FechaModificacion = ? WHERE IdPrestamo = ?";
        try (Connection conn = openConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
/*                 pstmt.setInt(1, entity.getIdPrestamo());
                pstmt.setString(2, entity.getNombreUsuario());
                pstmt.setString(3, entity.getApellidoUsuario());
                pstmt.setString(4, entity.getNombreLibro());
                pstmt.setString(5, entity.getEstadoPrestamo());
                pstmt.setString(7, dtf.format(now).toString());
                pstmt.executeUpdate(); */
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
            return true;
    }
}
