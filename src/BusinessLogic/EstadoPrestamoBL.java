package BusinessLogic;

import DataAccess.DAO.EstadoPrestamoDAO;
import DataAccess.DTO.EstadoPrestamoDTO;
import java.util.List;

public class EstadoPrestamoBL {
    private EstadoPrestamoDTO estadoPrestamo;       // cache
    private static EstadoPrestamoDAO eDAO = new EstadoPrestamoDAO();
    
    public EstadoPrestamoBL(){}
    
    public static List<EstadoPrestamoDTO> getAll() throws Exception{
        return eDAO.readAll();
    }

    public EstadoPrestamoDTO getByIdEstadoPrestamo(int idEstadoPrestamo) throws Exception{
        estadoPrestamo = eDAO.readBy(idEstadoPrestamo);
        return estadoPrestamo;
    }
    public boolean create(EstadoPrestamoDTO estadoPrestamoDTO) throws Exception{   
        return eDAO.create(estadoPrestamoDTO);
    }
    public boolean update(EstadoPrestamoDTO estadoPrestamoDTO) throws Exception{
        return eDAO.update(estadoPrestamoDTO);
    }
    public boolean delete(int idEstadoPrestamo) throws Exception{
        return eDAO.delete(idEstadoPrestamo);
    }
    public Integer getMaxRow() throws Exception{
        return eDAO.getMaxRow();
    }
}
