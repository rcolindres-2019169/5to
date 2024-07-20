package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase del modelo DAO de la clase Empleado_has_Servicio
 * @author Edwin Coy
 */
public class Empleados_has_ServiciosDAO {
    
    Conexion cn = new Conexion(); 
    Connection con; 
    PreparedStatement ps; 
    ResultSet rs; 
    int resp;
    
    /**
     * Metodo que lista los elementos de Empleados_has_Servicios
     * @return lista de Empleados_has_Servicios
     */
    public List listar(){
        String sql = "select * from Empleados_has_Servicios"; 
        List<Empleados_has_Servicios> listaEmplaedos_has_Servicios = new ArrayList<>(); 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                Empleados_has_Servicios mt = new Empleados_has_Servicios(); 
                mt.setCodigoEmpleados_has_servicios(rs.getInt(1));
                mt.setCostoServicio(rs.getDouble(2));
                mt.setCodigoEmpleado(rs.getInt(3));
                mt.setCodigoServicio(rs.getInt(4));  
                listaEmplaedos_has_Servicios.add(mt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmplaedos_has_Servicios; 
    }
    
    /**
     * Método para agregar a Empleados_has_Servicios
     * @param mt objeto Empleados_has_Servicios
     * @return int resp(solo retorna para la utilizacion del executeUpdate)
     */
    public int agregar(Empleados_has_Servicios mt){
        String sql = "insert into Empleados_has_Servicios (costoServicio, codigoEmpleado, codigoServicio) values(?, ?, ?)"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setDouble(1, mt.getCostoServicio());
            ps.setInt(2, mt.getCodigoEmpleado());
            ps.setInt(3, mt.getCodigoServicio());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return resp; 
    }
    
    /**
     * Método para buscar por id en Empleados_has_Servicios
     * @param id int id de la tupla que se desea buscar de Empleados_has_Servicios
     * @return Objeto de Empleados_has_Servicios
     */
    public Empleados_has_Servicios listarEmpleados_has_Servicios(int id){
        Empleados_has_Servicios mt = new Empleados_has_Servicios(); 
        String sql = "select * from Empleados_has_Servicios where codigoEmpleados_has_servicios = "+id;
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                mt.setCostoServicio(rs.getDouble(2));
                mt.setCodigoEmpleado(rs.getInt(3));
                mt.setCodigoServicio(rs.getInt(4));     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mt;       
    }
    
    /**
     * Método par editar Empleados_has_Servicios
     * @param mt Objeto Empleados_has_Servicios
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int editar(Empleados_has_Servicios mt){
        String sql = "Update Empleados_has_Servicios set costoServicio = ? where codigoEmpleados_has_servicios = ?"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setDouble(1, mt.getCostoServicio());
            ps.setInt(2, mt.getCodigoEmpleados_has_servicios());
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp; 
    }
    
    /**
     * Método para eliminar Empleados_has_Servicios
     * @param id int id de  la tupla que se desea eliminar de Empleados_has_Servicios
     */
    public void eliminar(int id){
        String sql = "delete from Empleados_has_Servicios where codigoEmpleados_has_servicios = "+id; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    
}

