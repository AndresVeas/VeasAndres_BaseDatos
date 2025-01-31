package BusinessLogic;

import java.util.List;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;

public class UsuarioBL {
    private UsuarioDTO usuario;       // cache
    private UsuarioDAO uDAO = new UsuarioDAO();

    public UsuarioBL(){}

    public List<UsuarioDTO> getAll() throws Exception{
        return uDAO.readAll();
    }
    public UsuarioDTO getByIdUsuario(int idUsuario) throws Exception{
        usuario = uDAO.readBy(idUsuario);
        return usuario;
    }
    public boolean create(UsuarioDTO usuarioDTO) throws Exception{   
        return uDAO.create(usuarioDTO);
    }
    public boolean update(UsuarioDTO usuarioDTO) throws Exception{
        return uDAO.update(usuarioDTO);
    }
    public boolean delete(int idUsuario) throws Exception{
        return uDAO.delete(idUsuario);
    }
    public Integer getMaxRow() throws Exception{
        return uDAO.getMaxRow();
    }
}
