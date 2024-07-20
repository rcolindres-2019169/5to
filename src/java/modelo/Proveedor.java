package modelo;

public class Proveedor {
    private int codigoProveedor;
    private String nombreProveedor;
    private String direccionProveedor;
    private String telefonoProveedor;

    public Proveedor() {
        
    }
    /**
     * Constructor vacio y lleno de la calse Proveedor
     * @param codigoProveedor variable int del codigo del proveedor
     * @param nombreProveedor variable String del nombre del Proveedor
     * @param direccionProveedor variable String de la dirección del proveedor
     * @param telefonoProveedor variable String del teléfono del proveedor
     */

    public Proveedor(int codigoProveedor, String nombreProveedor, String direccionProveedor, String telefonoProveedor) {
        this.codigoProveedor = codigoProveedor;
        this.nombreProveedor = nombreProveedor;
        this.direccionProveedor = direccionProveedor;
        this.telefonoProveedor = telefonoProveedor;
    }
    // Método que retorna el codigoProveedor
    public int getCodigoProveedor() {
        return codigoProveedor;
    }
    //método que recibe el codigoProveedor
    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }
    //método que retorna el nnombreProveedor
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    //método que recibe el nombreProveedor
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
    //método que retorna la direccionProveedor
    public String getDireccionProveedor() {
        return direccionProveedor;
    }
    //método que recibe la direccionProveedor
    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }
    //método que retorna el telefonoProveedor
    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }
    //método que recibe el telefonoProveedor
    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }
    
    
    
    
    
    
    
}
