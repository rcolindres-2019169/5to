package org.ricardocolindres.bean;

public class TipoPlato {
    
    private int codigoTipoPlato;
    private String descripcionTipo;

    public TipoPlato() {
    }

    public TipoPlato(int codigoTipoPlato, String descripcion) {
        this.codigoTipoPlato = codigoTipoPlato;
        this.descripcionTipo = descripcion;
    }

    public int getCodigoTipoPlato() {
        return codigoTipoPlato;
    }

    public void setCodigoTipoPlato(int codigoTipoPlato) {
        this.codigoTipoPlato = codigoTipoPlato;
    }

    public String getDescripcion() {
        return descripcionTipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionTipo = descripcion;
    }
    
        @Override
        public String toString() {
            return codigoTipoPlato  + "  |  " + descripcionTipo; 
    }


    }
