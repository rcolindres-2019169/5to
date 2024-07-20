package modelo;

/**
 * Modelo de la clase Empleados_has_Servicios
 * @author Edwin Coy
 */
public class Empleados_has_Servicios {
    
    private int codigoEmpleados_has_servicios;
    private double costoServicio;
    private int codigoEmpleado;
    private int codigoServicio;

    public Empleados_has_Servicios() {
    }
    
    /**
     * Constructor lleno de la clase Empleados_has_Servicios
     * @param codigoEmpleados_has_servicios variable int del codigo de Empleados_has_Servicios
     * @param costoServicio variable double de costoServicio
     * @param codigoEmpleado variable int de codigoEmpleado
     * @param codigoServicio variable int de codigoServicio
     */
    public Empleados_has_Servicios(int codigoEmpleados_has_servicios, double costoServicio, int codigoEmpleado, int codigoServicio) {
        this.codigoEmpleados_has_servicios = codigoEmpleados_has_servicios;
        this.costoServicio = costoServicio;
        this.codigoEmpleado = codigoEmpleado;
        this.codigoServicio = codigoServicio;
    }

    /**
     * Método que retorna codigoEmpleados_has_servicios
     * @return int codigoEmpleados_has_servicios
     */
    public int getCodigoEmpleados_has_servicios() {
        return codigoEmpleados_has_servicios;
    }

    /**
     * Método que recibe codigoEmpleados_has_servicios
     * @param codigoEmpleados_has_servicios int codigoEmpleados_has_servicios
     */
    public void setCodigoEmpleados_has_servicios(int codigoEmpleados_has_servicios) {
        this.codigoEmpleados_has_servicios = codigoEmpleados_has_servicios;
    }
    
    /**
     * Método que retorna costoServicio
     * @return double costoServicio
     */
    public double getCostoServicio() {
        return costoServicio;
    }

    /**
     * Método que recibe costoServicio
     * @param costoServicio double costoServicio
     */
    public void setCostoServicio(double costoServicio) {
        this.costoServicio = costoServicio;
    }

    /**
     * Método que retorna codigoEmpleado
     * @return int codigoEmpleado
     */
    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    /**
     * Método que recibe codigoEmpleado
     * @param codigoEmpleado int codigoEmpleado
     */
    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    /**
     * Método que retorna codigoServicio
     * @return int codigoServicio
     */
    public int getCodigoServicio() {
        return codigoServicio;
    }

    /**
     * Método que recibe codigoServicio
     * @param codigoServicio int codigoServicio
     */
    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }
    
    
}
