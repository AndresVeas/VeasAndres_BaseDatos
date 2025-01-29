package DataAccess.DTO;

public class UsuarioDTO {
    private Integer IdUsuario;
    private String  Nombre;
    private String  Apellido;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;

    public UsuarioDTO() {}

    public UsuarioDTO(String nombre, String apellido) {
        Nombre = nombre;
        Apellido = apellido;
    }

    public UsuarioDTO(Integer idUsuario, String nombre, String apellido, String estado, String fechaCreacion,
                    String fechaModificacion) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        Apellido = apellido;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModificacion = fechaModificacion;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getApellido() {
        return Apellido;
    }
    public void setApellido(String apellido) {
        Apellido = apellido;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCreacion() {
        return FechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
    public String getFechaModificacion() {
        return FechaModificacion;
    }
    public void setFechaModificacion(String fechaModificacion) {
        FechaModificacion = fechaModificacion;
    }

    public String toString(){
        return getClass().getName()
        + "\n IdUsuario         : "+ getIdUsuario           ()    
        + "\n Nombre            : "+ getNombre              ()    
        + "\n Apellido          : "+ getApellido            ()    
        + "\n Estado            : "+ getEstado              ()  
        + "\n FechaCreacion     : "+ getFechaCreacion       ()
        + "\n FechaModificacion : "+ getFechaModificacion   ();
    }
}
