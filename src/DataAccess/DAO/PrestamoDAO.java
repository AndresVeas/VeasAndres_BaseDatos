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

public class PrestamoDAO extends SQLiteDataHelper implements IDAO<PrestamoDTO> {
    @Override
    public PrestamoDTO readBy(Integer id) throws Exception {
        PrestamoDTO dto = new PrestamoDTO();
        String query = "SELECT p.IdPrestamo,             " +
                       "       p.IdUsuario AS PrestamoIdUsuario, " +
                       "       p.IdLibro,               " +
                       "       p.IdEstadoPrestamo,      " +
                       "       u.Nombre AS NombreUsuario, " +
                       "       u.Apellido AS ApellidoUsuario, " +
                       "       l.NombreLibro,           " +
                       "       e.Nombre AS EstadoPrestamo, " +
                       "       p.Estado,                " +
                       "       p.FechaCreacion,         " +
                       "       p.FechaDevolucion        " +
                       "FROM   Prestamo p               " +
                       "JOIN   Usuario u ON p.IdUsuario = u.IdUsuario " +
                       "JOIN   Libro l ON p.IdLibro = l.IdLibro " +
                       "JOIN   EstadoPrestamo e ON p.IdEstadoPrestamo = e.IdEstadoPrestamo " +
                       "WHERE  p.IdPrestamo = " + id.toString();
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                dto = new PrestamoDTO(
                        rs.getInt("IdPrestamo"),
                        rs.getInt("PrestamoIdUsuario"),
                        rs.getInt("IdLibro"),
                        rs.getInt("IdEstadoPrestamo"),
                        rs.getString("NombreUsuario"),
                        rs.getString("ApellidoUsuario"),
                        rs.getString("NombreLibro"),
                        rs.getString("EstadoPrestamo"),
                        rs.getString("Estado"),
                        rs.getString("FechaCreacion"),
                        rs.getString("FechaDevolucion"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return dto;
    }

    @Override
    public List<PrestamoDTO> readAll() throws Exception {
        List<PrestamoDTO> lst = new ArrayList<>();
        String query = "SELECT " +
                "        p.IdPrestamo, " +
                "        p.IdUsuario AS PrestamoIdUsuario, " + // Especificar origen para evitar ambigüedad
                "        p.IdLibro, " +
                "        p.IdEstadoPrestamo, " +
                "        u.Nombre AS NombreUsuario, " +
                "        u.Apellido AS ApellidoUsuario, " +
                "        l.NombreLibro, " +
                "        e.Nombre AS EstadoPrestamo, " +
                "        p.Estado, " +
                "        p.FechaCreacion, " +
                "        p.FechaDevolucion " +
                "FROM Prestamo p " +
                "JOIN Usuario u ON p.IdUsuario = u.IdUsuario " +
                "JOIN Libro l ON p.IdLibro = l.IdLibro " +
                "JOIN EstadoPrestamo e ON p.IdEstadoPrestamo = e.IdEstadoPrestamo ";

        try {
            Connection conn = openConnection();         // Conectar a DB     
            Statement stmt = conn.createStatement();    // CRUD : select * ...    
            ResultSet rs = stmt.executeQuery(query);    // Ejecutar la consulta
            while (rs.next()) {
                PrestamoDTO dto = new PrestamoDTO(
                        rs.getInt("IdPrestamo"),
                        rs.getInt("PrestamoIdUsuario"),
                        rs.getInt("IdLibro"),
                        rs.getInt("IdEstadoPrestamo"),
                        rs.getString("NombreUsuario"),
                        rs.getString("ApellidoUsuario"),
                        rs.getString("NombreLibro"),
                        rs.getString("EstadoPrestamo"),
                        rs.getString("Estado"),
                        rs.getString("FechaCreacion"),
                        rs.getString("FechaDevolucion"));

                lst.add(dto);
            }
        } catch (SQLException e) {
            throw e;
        }
        return lst;
    }

    @Override
    public boolean create(PrestamoDTO entity) throws Exception {
        String query = " INSERT INTO Prestamo (IdUsuario,IdLibro,IdEstadoPrestamo) VALUES (?,?,?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getIdUsuario().toString());
            pstmt.setString(2, entity.getIdLibro().toString());
            pstmt.setString(3, entity.getIdEstadoPrestamo().toString());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw e;  
        }
    }

    @Override
    public boolean update(PrestamoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Prestamo SET IdUsuario = ?, IdLibro = ?, IdEstadoPrestamo = ?, FechaDevolucion = ? WHERE IdPrestamo = ?";
        try (PreparedStatement pstmt = openConnection().prepareStatement(query)) {
            pstmt.setString(1, entity.getIdUsuario().toString());
            pstmt.setString(2, entity.getIdLibro().toString());
            pstmt.setString(3, entity.getIdEstadoPrestamo().toString());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setString(5, entity.getIdPrestamo().toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return true;
    }

    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) TotalReg FROM Prestamo";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
                return rs.getInt(1);                    // TotalReg
            }
        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }
}
