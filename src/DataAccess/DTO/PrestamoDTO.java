package DataAccess.DTO;

public class PrestamoDTO {
    private Integer IdPrestamo;
    private String  NombreUsuario;
    private String  ApellidoUsuario;
    private String  NombreLibro;
    private String  EstadoPrestamo;
    private String  FechaDevolucion;
    
    public PrestamoDTO() {
    }
    

    public PrestamoDTO(String nombreUsuario, String apellidoUsuario, String nombreLibro, String estadoPrestamo) {
        NombreUsuario = nombreUsuario;
        ApellidoUsuario = apellidoUsuario;
        NombreLibro = nombreLibro;
        EstadoPrestamo = estadoPrestamo;
    }

    public PrestamoDTO(Integer idPrestamo, String nombreUsuario, String apellidoUsuario, String nombreLibro,
            String estadoPrestamo, String fechaDevolucion) {
        IdPrestamo = idPrestamo;
        NombreUsuario = nombreUsuario;
        ApellidoUsuario = apellidoUsuario;
        NombreLibro = nombreLibro;
        EstadoPrestamo = estadoPrestamo;
        FechaDevolucion = fechaDevolucion;
    }

    public Integer getIdPrestamo() {
        return IdPrestamo;
    }


    public void setIdPrestamo(Integer idPrestamo) {
        IdPrestamo = idPrestamo;
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


    public String getFechaDevolucion() {
        return FechaDevolucion;
    }


    public void setFechaDevolucion(String fechaDevolucion) {
        FechaDevolucion = fechaDevolucion;
    }


    @Override
    public String toString(){
        return getClass().getName()
            + "\n IdPrestamo:       " + getIdPrestamo()
            + "\n NombreUsuario:    " + getNombreUsuario()
            + "\n ApellidoUsuario:  " + getApellidoUsuario()
            + "\n NombreLibro:      " + getNombreLibro()
            + "\n EstadoPrestamo:   " + getEstadoPrestamo()
            + "\n FechaDevolucion:  " + getFechaDevolucion();
    }
}
