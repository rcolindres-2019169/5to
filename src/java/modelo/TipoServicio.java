package modelo;


public class TipoServicio {
    
    private int codigoTipoServicio;
    private String tipoServicio;
    private String descripcion;
    private double precioTipoServicio;

    //Contructor vacio
    public TipoServicio() {
    }

    //Constructor
    public TipoServicio(int codigoTipoServicio, String tipoServicio, String descripcion, double precioTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
        this.tipoServicio = tipoServicio;
        this.descripcion = descripcion;
        this.precioTipoServicio = precioTipoServicio;
    }
    
    // Getters y Setters
    public int getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(int codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioTipoServicio() {
        return precioTipoServicio;
    }

    public void setPrecioTipoServicio(double precioTipoServicio) {
        this.precioTipoServicio = precioTipoServicio;
    }
    
    
    
    
    
}
