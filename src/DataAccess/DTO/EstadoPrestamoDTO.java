package DataAccess.DTO;

public class EstadoPrestamoDTO {
    private Integer IdEstadoPrestamo;
    private String  Nombre;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
    
    public EstadoPrestamoDTO() {
    }

    public EstadoPrestamoDTO(String nombre) {
        Nombre = nombre;
    }

    public EstadoPrestamoDTO(Integer idEstadoPrestamo, String nombre, String estado, String fechaCreacion,
                             String fechaModificacion) {
        IdEstadoPrestamo = idEstadoPrestamo;
        Nombre = nombre;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModificacion = fechaModificacion;
    }

    public Integer getIdEstadoPrestamo() {
        return IdEstadoPrestamo;
    }

    public void setIdEstadoPrestamo(Integer idEstadoPrestamo) {
        IdEstadoPrestamo = idEstadoPrestamo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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
    
    @Override
    public String toString(){
        return getClass().getName()
        + "\n IdEstadoPrestamo: "+ getIdEstadoPrestamo()       
        + "\n Nombre:           "+ getNombre()       
        + "\n Estado:           "+ getEstado()       
        + "\n FechaCreacion:    "+ getFechaCreacion()    
        + "\n FechaModificacion:"+ getFechaModificacion();
    }
}
