package DataAccess.DTO;

public class LibroDTO {
    private Integer IdLibro;
    private String  NombreLibro;
    private String  NombreAutor;
    private String  ApellidoAutor;
    private String  Editorial;
    private String  Estado;
    private String  FechaCreacion;
    private String  FechaModificacion;
  
    public LibroDTO() {}

    public LibroDTO(String nombreLibro, String nombreAutor, String apellidoAutor, String editorial,String estado) {
        NombreLibro = nombreLibro;
        NombreAutor = nombreAutor;
        ApellidoAutor = apellidoAutor;
        Editorial = editorial;
        Estado = estado;
    }

    public LibroDTO(Integer idLibro, String nombreLibro, String nombreAutor, String apellidoAutor, String editorial,
            String estado, String fechaCreacion, String fechaModificacion) {
        IdLibro = idLibro;
        NombreLibro = nombreLibro;
        NombreAutor = nombreAutor;
        ApellidoAutor = apellidoAutor;
        Editorial = editorial;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModificacion = fechaModificacion;
    }

    public Integer getIdLibro() {
        return IdLibro;
    }
    public void setIdLibro(Integer idLibro) {
        IdLibro = idLibro;
    }
    public String getNombreLibro() {
        return NombreLibro;
    }
    public void setNombreLibro(String nombreLibro) {
        NombreLibro = nombreLibro;
    }
    public String getNombreAutor() {
        return NombreAutor;
    }
    public void setNombreAutor(String nombreAutor) {
        NombreAutor = nombreAutor;
    }
    public String getApellidoAutor() {
        return ApellidoAutor;
    }
    public void setApellidoAutor(String apellidoAutor) {
        ApellidoAutor = apellidoAutor;
    }
    public String getEditorial() {
        return Editorial;
    }
    public void setEditorial(String editorial) {
        Editorial = editorial;
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
                + "\nIdLibro:           " + getIdLibro              ()
                + "\nNombreLibro:       " + getNombreLibro          ()
                + "\nNombreAutor:       " + getNombreAutor          ()    
                + "\nApellidoAutor:     " + getApellidoAutor        ()	    
                + "\nEditorial:         " + getEditorial            ()
                + "\nEstado:            " + getEstado               ()
                + "\nFechaCreacion:     " + getFechaCreacion        ()    
                + "\nFechaModificacion: " + getFechaModificacion    ();
    }    
}