package BusinessLogic;

import DataAccess.DAO.PrestamoDAO;
import DataAccess.DTO.PrestamoDTO;
import java.util.List;

public class PrestamoBL {
    private PrestamoDTO prestamo;       // cache
    private PrestamoDAO pDAO = new PrestamoDAO();

    public PrestamoBL(){}

    public List<PrestamoDTO> getAll() throws Exception{
        return pDAO.readAll();
    }
    public PrestamoDTO getByIdPrestamo(int idPrestamo) throws Exception{
        prestamo = pDAO.readBy(idPrestamo);
        return prestamo;
    }
    public boolean create(PrestamoDTO prestamoDTO) throws Exception{   
        return pDAO.create(prestamoDTO);
    }
    public boolean update(PrestamoDTO prestamoDTO) throws Exception{
        return pDAO.update(prestamoDTO);
    }
    public boolean delete(int idPrestamo) throws Exception{
        return pDAO.delete(idPrestamo);
    }
    public Integer getMaxRow() throws Exception{
        return pDAO.getMaxRow();
    }

}
