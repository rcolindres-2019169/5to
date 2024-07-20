
package modelo;

/**
 * Modelo de la clase Empresa
 * @author carlo
 */

public class Empresa {
    //creacion de las variables de la DB
    private int codigoEmpresa;
    private String nombreEmpresa;
    private String telefonoEmpresa;
    private String direccionEmpresa;
    private String estadoEmpresa;
    
    // creacion de construcctores

    public Empresa() {
    }

    public Empresa(int codigoEmpresa, String nombreEmpresa, String telefonoEmpresa, String direccionEmpresa, String estadoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.estadoEmpresa = estadoEmpresa;
    }
    
    // getter y setters

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getEstadoEmpresa() {
        return estadoEmpresa;
    }

    public void setEstadoEmpresa(String estadoEmpresa) {
        this.estadoEmpresa = estadoEmpresa;
    }
    
    
}
