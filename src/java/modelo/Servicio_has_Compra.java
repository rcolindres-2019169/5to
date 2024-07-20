package modelo;


public class Servicio_has_Compra {
    
    private int Servicio_codigoCompra;
    private String descripcionDetalle;
    private int codigoServicio;
    private int codigoCompra;

    public Servicio_has_Compra() {
    }

    public Servicio_has_Compra(int Servicio_codigoCompra, String descripcionDetalle, int codigoServicio, int codigoCompra) {
        this.Servicio_codigoCompra = Servicio_codigoCompra;
        this.descripcionDetalle = descripcionDetalle;
        this.codigoServicio = codigoServicio;
        this.codigoCompra = codigoCompra;
    }

    public int getServicio_codigoCompra() {
        return Servicio_codigoCompra;
    }

    public void setServicio_codigoCompra(int Servicio_codigoCompra) {
        this.Servicio_codigoCompra = Servicio_codigoCompra;
    }

    public String getDescripcionDetalle() {
        return descripcionDetalle;
    }

    public void setDescripcionDetalle(String descripcionDetalle) {
        this.descripcionDetalle = descripcionDetalle;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }
    
    
}
