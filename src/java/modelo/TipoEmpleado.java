package modelo;
/**
 *Modelo de la clase TipoEmpleado
 * @author David Balcárcel
 */
public class TipoEmpleado {
    private int codigoTipoEmpleado;
    private String descripcionTipoEmpleado;
    private String categoria;
    private double sueldo;

    public TipoEmpleado(){

    }
    /**
     * Constructor lleno de la clase TipoEmpleado
     * @param codigoTipoEmpleado variable int del codigo del tipo de empleado.
     * @param descripcionTipoEmpleado variable String de la descripción del empleado.
     * @param categoria variable String de categoria
     * @param sueldo variable double del sueldo
     */
    
    public TipoEmpleado(int codigoTipoEmpleado, String descripcionTipoEmpleado, String categoria, double sueldo) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
        this.categoria = categoria;
        this.sueldo = sueldo;
    }

    /**
     * Método que retorna el codigoTipoEmpleado
     * @return int codigoTipoEmpleado
     */
    public int getCodigoTipoEmpleado() {
        return codigoTipoEmpleado;
    }
    
    /**
     * Método que recibe el codigoTipoEmpleado
     * @param codigoTipoEmpleado int codigoTipoEmpleado
     */
    
    public void setCodigoTipoEmpleado(int codigoTipoEmpleado) {
        this.codigoTipoEmpleado = codigoTipoEmpleado;
    }

    /**
     * *Método que retorna variable descripcionTipoEmpleado
     * @return String descripcionTipoEmpleado
     */    
    public String getDescripcionTipoEmpleado() {
        return descripcionTipoEmpleado;
    }

    /**
     * Método que recibe la variable descripcionTipoEmpleado
     * @param descripcionTipoEmpleado String descripcionTipoEmpleado
     */    
    public void setDescripcionTipoEmpleado(String descripcionTipoEmpleado) {
        this.descripcionTipoEmpleado = descripcionTipoEmpleado;
    }

    /**
     * Método que retorna el categoria
     * @return String categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Método que recibe categoria
     * @param categoria String categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Método que retorna variable sueldo
     * @return double sueldo
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Método que recibe variable sueldo 
     * @param sueldo double sueldo
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }


}
