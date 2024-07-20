package org.ricardocolindres.bean;


import java.sql.Time;
import java.util.Date;

public class ServiciosHasEmpleados {
    
    private int servicios_codigoServicio;
    private int codigoServicio;
    private int codigoEmpleado;
    private Date fechaEvento;
    private String horaEvento;
    private String lugarEvento;
    
   public ServiciosHasEmpleados(){
       
   }

    public ServiciosHasEmpleados(int servicios_codigoServicio, int codigoServicio, int codigoEmpleado, Date fechaEvento, String horaEvento, String lugarEvento) {
        this.servicios_codigoServicio = servicios_codigoServicio;
        this.codigoServicio = codigoServicio;
        this.codigoEmpleado = codigoEmpleado;
        this.fechaEvento = fechaEvento;
        this.horaEvento = horaEvento;
        this.lugarEvento = lugarEvento;
    }

    public int getServicios_codigoServicio() {
        return servicios_codigoServicio;
    }

    public void setServicios_codigoServicio(int servicios_codigoServicio) {
        this.servicios_codigoServicio = servicios_codigoServicio;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }
   
   
    
}
