package DataAccess.DTO;

public class PrestamoDTO {
    private Integer IdPrestamo;
    private Integer IdUsuario;
    private Integer IdLibro;
    private Integer IdEstadoPrestamo;
    private String  NombreUsuario;
    private String  ApellidoUsuario;
    private String  NombreLibro;
    private String  EstadoPrestamo;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaDevolucion;
    
    public PrestamoDTO() {
    }
    

    public PrestamoDTO(Integer idUsuario, Integer idLibro, Integer idEstadoPrestamo) {
        IdUsuario = idUsuario;
        IdLibro = idLibro;
        IdEstadoPrestamo = idEstadoPrestamo;
    }


    public PrestamoDTO(Integer idPrestamo, Integer idUsuario, Integer idLibro, Integer idEstadoPrestamo, String estado,
            String fechaCreacion, String fechaDevolucion) {
        IdPrestamo = idPrestamo;
        IdUsuario = idUsuario;
        IdLibro = idLibro;
        IdEstadoPrestamo = idEstadoPrestamo;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaDevolucion = fechaDevolucion;
    }

    public PrestamoDTO(Integer idPrestamo, Integer idUsuario, Integer idLibro, Integer idEstadoPrestamo,
            String nombreUsuario, String apellidoUsuario, String nombreLibro, String estadoPrestamo, String estado,
            String fechaCreacion, String fechaDevolucion) {
        IdPrestamo = idPrestamo;
        IdUsuario = idUsuario;
        IdLibro = idLibro;
        IdEstadoPrestamo = idEstadoPrestamo;
        NombreUsuario = nombreUsuario;
        ApellidoUsuario = apellidoUsuario;
        NombreLibro = nombreLibro;
        EstadoPrestamo = estadoPrestamo;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaDevolucion = fechaDevolucion;
    }

    public Integer getIdPrestamo() {
        return IdPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        IdPrestamo = idPrestamo;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }

    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }

    public Integer getIdEstadoPrestamo() {
        return IdEstadoPrestamo;
    }

    public void setIdEstadoPrestamo(Integer idEstadoPrestamo) {
        IdEstadoPrestamo = idEstadoPrestamo;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }


    public String getApellidoUsuario() {
        return ApellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        ApellidoUsuario = apellidoUsuario;
    }

    public String getNombreLibro() {
        return NombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        NombreLibro = nombreLibro;
    }

    public String getEstadoPrestamo() {
        return EstadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        EstadoPrestamo = estadoPrestamo;
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

    public String getFechaDevolucion() {
        return FechaDevolucion;
    }


    public void setFechaDevolucion(String fechaDevolucion) {
        FechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return getClass().getName()
            + "\n IdPrestamo:       " + getIdPrestamo()
            + "\n IdUsuario:        " + getIdUsuario()
            + "\n IdLibro:          " + getIdLibro()
            + "\n IdEstadoPrestamo: " + getIdEstadoPrestamo()
            + "\n NombreUsuario:    " + getNombreUsuario()
            + "\n ApellidoUsuario:  " + getApellidoUsuario()
            + "\n NombreLibro:      " + getNombreLibro()
            + "\n EstadoPrestamo:   " + getEstadoPrestamo()
            + "\n Estado:           " + getEstado()
            + "\n FechaCreacion:    " + getFechaCreacion()
            + "\n FechaDevolucion:  " + getFechaDevolucion();
    }

}
