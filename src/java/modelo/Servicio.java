package modelo;

import java.util.Date;

/**
 *
 * @author Rodrigo Díaz
 */
public class Servicio {
    
    private int codigoServicio;
    private String lugarServicio;
    private String numeroServicio;
    private String horaServicio;
    private Date fechaServicio;
    private int codigoTipoServicio;

    public Servicio() {
    }

    public Servicio(int codigoServicio, String lugarServicio, String numeroServicio, String horaServicio, Date fechaServicio, int codigoTipoServicio) {
        this.codigoServicio = codigoServicio;
        this.lugarServicio = lugarServicio;
        this.numeroServicio = numeroServicio;
        this.horaServicio = horaServicio;
        this.fechaServicio = fechaServicio;
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
        
    }

    public String getLugarServicio() {
        return lugarServicio;
    }

    public void setLugarServicio(String lugarServicio) {
        this.lugarServicio = lugarServicio;
    }

    public String getNumeroServicio() {
        return numeroServicio;
    }

    public void setNumeroServicio(String numeroServicio) {
        this.numeroServicio = numeroServicio;
    }

    public String getHoraServicio() {
        return horaServicio;
    }

    public void setHoraServicio(String horaServicio) {
        this.horaServicio = horaServicio;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public int getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(int codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }
    
    
    

    
    
    
    
    
}
