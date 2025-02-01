package BusinessLogic;

import DataAccess.DAO.UsuarioDAO;
import DataAccess.DTO.UsuarioDTO;
import java.util.List;

public class UsuarioBL {
    private UsuarioDTO usuario;       // cache
    private static UsuarioDAO uDAO = new UsuarioDAO();
    
        public UsuarioBL(){}
    
        public static List<UsuarioDTO> getAll() throws Exception{
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
