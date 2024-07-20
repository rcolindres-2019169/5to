package modelo;
/*pablo bermudez modificado el 16/08/23*/
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Equipo_has_EmpleadoDAO{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public Equipo_has_Empleado validar(int Equipo_codigoEmpleado){
        // Vamos a instaciar un objeto de la entidad Equipo_has_Empleado
        Equipo_has_Empleado equipo_has_empleado = new Equipo_has_Empleado();
        String sql = "Select * from Equipo_has_Empleado where Equipo_codigoEmpleado = ?";
        
        try{
        con = cn.Conexion();
        ps = con.prepareCall(sql);
        ps.setInt(1, Equipo_codigoEmpleado);
        rs = ps.executeQuery();
        while(rs.next()){
            equipo_has_empleado.setEquipo_codigoEmpleado(rs.getInt("Equipo_codigoEmpleado"));
            equipo_has_empleado.setCantidadEquipo(rs.getInt("codigoEquipo"));
            equipo_has_empleado.setCodigoEquipo(rs.getInt("codigoEmpleado"));
            equipo_has_empleado.setCodigoEmpleado(rs.getInt("cantidadEquipo"));
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return equipo_has_empleado;// Retorna Equipo_has_Empleado encontrado
        
    }
    
    // Metodo Listar Equipo_has_Empleado
    public List listar(){
        String sql = "Select * from Equipo_has_Empleado";
        List<Equipo_has_Empleado> listaEquipo_has_Empleado = new ArrayList<>();
        try{
            con= cn.Conexion();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Equipo_has_Empleado es = new Equipo_has_Empleado();
                es.setEquipo_codigoEmpleado(rs.getInt(1));
                es.setCodigoEquipo(rs.getInt(2));
                es.setCodigoEmpleado(rs.getInt(3));
                es.setCantidadEquipo(rs.getInt(4));
                listaEquipo_has_Empleado.add(es);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaEquipo_has_Empleado;
        
    }
    
    // Metodo Agregar Equipo_has_Empleado
    public int agregar(Equipo_has_Empleado emp){
        String sql ="Insert into Equipo_has_Empleado (codigoEquipo, codigoEmpleado, cantidadEquipo) values (?,?,?)" ;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, emp.getCodigoEquipo());
            ps.setInt(2, emp.getCodigoEmpleado());
            ps.setInt(3, emp.getCantidadEquipo());
            
            ps.executeUpdate();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return resp;
    }
    
    // Buscar Equipo_has_Empleado
    public Equipo_has_Empleado listarEquipoEmpleado(int id){
    Equipo_has_Empleado ts = new Equipo_has_Empleado();
    String sql ="Select * from Equipo_has_Empleado where Equipo_codigoEmpleado = "+id;
    try{
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
                ts.setCodigoEquipo(rs.getInt(2));
                ts.setCodigoEmpleado(rs.getInt(3));
                ts.setCantidadEquipo(rs.getInt(4));
        }
    
    }catch(Exception e){
        e.printStackTrace();
    }
    
    
    return ts;
    }

        //Método que editar los datos de Empleado
    public int actualizar(Equipo_has_Empleado ehm){
        String sql = "update Equipo_has_Empleado set cantidadEquipo = ? where Equipo_codigoEmpleado = ?";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ehm.getCantidadEquipo());
            ps.setInt(2, ehm.getEquipo_codigoEmpleado());
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
   // Metodo Eliminar Equipo_has_Empleado
   public void eliminar(int id){
        String sql = "delete from Equipo_has_Empleado where Equipo_codigoEmpleado = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}