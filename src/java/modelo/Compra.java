package modelo;

import java.util.Date;

/**
 * Modelo de la entidad Compra
 * @author Denis Abad
 */
public class Compra {
    
    private int codigoCompra;
    private double costoCompra;
    private String descripcionCompra;
    private Date fechaCompra;
    private int codigoEmpresa;

    /*
    * Contructor sin parámetros de la clase Compra
    */
    public Compra() {
    }

    /**
     * Contructor con parámetros de la clase Compra
     * 
     * @param codigoCompra variable de tipo int
     * @param costoCompra variable de tipo double
     * @param descripcionCompra variable de tipo String
     * @param fechaCompra variable de tipo Date
     * @param codigoEmpresa variable de tipo int
     */
    public Compra(int codigoCompra, double costoCompra, String descripcionCompra, Date fechaCompra, int codigoEmpresa) {
        this.codigoCompra = codigoCompra;
        this.costoCompra = costoCompra;
        this.descripcionCompra = descripcionCompra;
        this.fechaCompra = fechaCompra;
        this.codigoEmpresa = codigoEmpresa;
    }

    /**
     * Método que retorna codigoCompra
     * @return int codigoCompra
     */
    public int getCodigoCompra() {
        return codigoCompra;
    }

    /**
     * Método que recibe codigoCompra
     * @param codigoCompra 
     */
    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    /**
     * Método que retorna costoCompra
     * @return 
     */
    public double getCostoCompra() {
        return costoCompra;
    }

    /**
     * Método que recibe costoCompra
     * @param costoCompra 
     */
    public void setCostoCompra(double costoCompra) {
        this.costoCompra = costoCompra;
    }

    /**
     * Método que retorna descripcionCompra
     * @return 
     */
    public String getDescripcionCompra() {
        return descripcionCompra;
    }

    /**
     * Método que recibe descripcionCompra
     * @param descripcionCompra 
     */
    public void setDescripcionCompra(String descripcionCompra) {
        this.descripcionCompra = descripcionCompra;
    }

    /**
     * Método que retorna fechaCompra
     * @return 
     */
    public Date getFechaCompra() {
        return fechaCompra;
    }

    /**
     * Método que recibe fechaCompra
     * @param fechaCompra 
     */
    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    /**
     * Método que retorna codigoEmpresa
     * @return 
     */
    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    /**
     * Método que recibe codigoEmpresa
     * @param codigoEmpresa 
     */
    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    
}
