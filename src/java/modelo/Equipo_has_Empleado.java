package modelo;
/*pablo bermudez modificado el 16/08/23*/

public class Equipo_has_Empleado{
    
    private int Equipo_codigoEmpleado;
    private int codigoEquipo;
    private int codigoEmpleado;
    private int cantidadEquipo;

    //Contructor vacio
    public Equipo_has_Empleado(){
        
    }

    public Equipo_has_Empleado(int Equipo_codigoEmpleado, int codigoEquipo, int codigoEmpleado, int cantidadEquipo) {
        this.Equipo_codigoEmpleado = Equipo_codigoEmpleado;
        this.codigoEquipo = codigoEquipo;
        this.codigoEmpleado = codigoEmpleado;
        this.cantidadEquipo = cantidadEquipo;
    }

    public int getEquipo_codigoEmpleado() {
        return Equipo_codigoEmpleado;
    }

    public void setEquipo_codigoEmpleado(int Equipo_codigoEmpleado) {
        this.Equipo_codigoEmpleado = Equipo_codigoEmpleado;
    }

    public int getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(int codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getCantidadEquipo() {
        return cantidadEquipo;
    }

    public void setCantidadEquipo(int cantidadEquipo) {
        this.cantidadEquipo = cantidadEquipo;
    }

  
    
    
}