package BusinessLogic;

import DataAccess.DAO.LibroDAO;
import DataAccess.DTO.LibroDTO;
import java.util.List;

public class LibroBL {
    private LibroDTO libro;       // cache
    private static LibroDAO lDAO = new LibroDAO();
    
    public LibroBL(){}
    
    public static List<LibroDTO> getAll() throws Exception{
        return lDAO.readAll();
    }

    public LibroDTO getByIdLibro(int idLibro) throws Exception{
        libro = lDAO.readBy(idLibro);
        return libro;
    }
    public boolean create(LibroDTO libroDTO) throws Exception{   
        return lDAO.create(libroDTO);
    }
    public boolean update(LibroDTO libroDTO) throws Exception{
        return lDAO.update(libroDTO);
    }
    public boolean delete(int idLibro) throws Exception{
        return lDAO.delete(idLibro);
    }
    public Integer getMaxRow() throws Exception{
        return lDAO.getMaxRow();
    }
}
