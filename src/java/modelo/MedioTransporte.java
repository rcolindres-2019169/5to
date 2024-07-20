
package modelo;

/**
 *Modelo de la clase MedioTransporte
 * @author Carlos Altán
 */
public class MedioTransporte {
    
    private int codigoTransporte; 
    private String placa; 
    private String tipoVehiculo; 
    private String marca; 
    
    public MedioTransporte() {
    }
    /**
     * Constructor lleno de la calse MedioTransporte
     * @param codigoTransporte variable int del codigo del transporte
     * @param placa variable String de la placa
     * @param tipoVehiculo variable String del tipoVehiculo
     * @param marca variable String de la marca
     */
    public MedioTransporte(int codigoTransporte, String placa, String tipoVehiculo, String marca) {
        this.codigoTransporte = codigoTransporte;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
    }
    /**
     * Método que retorna el codigoTransporte
     * @return int codigoTransporte
     */
    public int getCodigoTransporte() {
        return codigoTransporte;
    }
    /**
     * Método que recibe el codigoTransporte
     * @param codigoTransporte int codigoTransporte
     */
    public void setCodigoTransporte(int codigoTransporte) {
        this.codigoTransporte = codigoTransporte;
    }
    /**
     * *Método que retorna variable placa
     * @return String placa
     */
    public String getPlaca() {
        return placa;
    }
    /**
     * Método que recibe la variable placa
     * @param placa String placa
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    /**
     * Método que retorna el tipoVehiculo
     * @return String tipoVehiculo
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
    /**
     * Método que recibe tipoVehiculo
     * @param tipoVehiculo String tipoVehiculo
     */
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    /**
     * Método que retorna variable placa
     * @return String placa
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Método que recibe variable placa 
     * @param marca String placa
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    
    
}
