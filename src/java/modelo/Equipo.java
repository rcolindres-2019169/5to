
package modelo;

/**
 * Modelo de la clase Equipo
 * @author Carlos Armas
 */
public class Equipo {
    
    private int codigoEquipo;
    private String nombreEquipo;
    private String descripcionEquipo;
    private int cantidad;

    public Equipo() {
    }
    /**
     * Constructor lleno de la calse Equipo
     * @param codigoEquipo variable int del codigo del equipo
     * @param nombreEquipo variable String del nombreEquipo
     * @param descripcionEquipo variable String de la descripcionEquipo
     * @param cantidad variable int de la cantidad
     */
    public Equipo(int codigoEquipo, String nombreEquipo, String descripcionEquipo, int cantidad) {
        this.codigoEquipo = codigoEquipo;
        this.nombreEquipo = nombreEquipo;
        this.descripcionEquipo = descripcionEquipo;
        this.cantidad = cantidad;
    }
    /**
     * Método que retorna el codigoEquipo
     * @return int codigoEquipo
     */
    public int getCodigoEquipo() {
        return codigoEquipo;
    }
    /**
     * Método que recibe el codigoEquipo
     * @param codigoEquipo int codigoEquipo
     */
    public void setCodigoEquipo(int codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }
    /**
     * Método que retorna el nombreEquipo
     * @return String nombreEquipo
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }
    /**
     * Método que recibe el nombreEquipo
     * @param nombreEquipo String nombreEquipo
     */
    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
    /**
     * Método que retorna la descripcionEquipo
     * @return String descripcionEquipo
     */
    public String getDescripcionEquipo() {
        return descripcionEquipo;
    }
    /**
     * Método que recibe descripcionEquipo
     * @param descripcionEquipo String descripcionEquipo
     */
    public void setDescripcionEquipo(String descripcionEquipo) {
        this.descripcionEquipo = descripcionEquipo;
    }
    /**
     * Método que retorna variable cantidad
     * @return int cantidad
     */
    public int getCantidad() {
        return cantidad;
    }
    /**
     * Método que recibe variable cantidad 
     * @param cantidad int cantidad
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
