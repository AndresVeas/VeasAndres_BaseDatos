package BusinessLogic;

import java.util.List;

import DataAccess.DAO.LibroDAO;
import DataAccess.DTO.LibroDTO;

public class LibroBL {
    private LibroDTO libro;       // cache
    private LibroDAO lDAO = new LibroDAO();

    public LibroBL(){}

    public List<LibroDTO> getAll() throws Exception{
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
