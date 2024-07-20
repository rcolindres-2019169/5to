package modelo;


public class Proveedor_has_Equipo {
    
    private int codigoProveedor_has_Equipo;
    private String descripcionProveedor;
    private int codigoProveedor;
    private int codigoEquipo;

    public Proveedor_has_Equipo() {
    }

    public Proveedor_has_Equipo(int codigoProveedor_has_Equipo, String descripcionProveedor, int codigoProveedor, int codigoEquipo) {
        this.codigoProveedor_has_Equipo = codigoProveedor_has_Equipo;
        this.descripcionProveedor = descripcionProveedor;
        this.codigoProveedor = codigoProveedor;
        this.codigoEquipo = codigoEquipo;
    }

    public int getCodigoProveedor_has_Equipo() {
        return codigoProveedor_has_Equipo;
    }

    public void setCodigoProveedor_has_Equipo(int codigoProveedor_has_Equipo) {
        this.codigoProveedor_has_Equipo = codigoProveedor_has_Equipo;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(int codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }
    
    
}
