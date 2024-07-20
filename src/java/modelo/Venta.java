package modelo;

/**
 *
 * @author rodro
 */
public class Venta {
    private int codigoVenta;
    private int item;
    private int codigoEmpresa;
    private int codigoTipoServicio;
    private String numeroSerie;
    private String descripcion;
    private double precio;
    private double subTotal;
    private double monto;
    private String fecha;
    private String estado;

    public Venta() {
    }

    public Venta(int codigoVenta, int item, int codigoEmpresa, int codigoTipoServicio, String numeroSerie, String descripcion, double precio, double subTotal, double monto, String fecha, String estado) {
        this.codigoVenta = codigoVenta;
        this.item = item;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoTipoServicio = codigoTipoServicio;
        this.numeroSerie = numeroSerie;
        this.descripcion = descripcion;
        this.precio = precio;
        this.subTotal = subTotal;
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(int codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
