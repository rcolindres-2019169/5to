package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *Clase del Modelo DAO de la clase Proveedor
 * @author Juan Boteo
 */

public class ProveedorDAO {
    Conexion cn = new Conexion(); 
    Connection con; 
    PreparedStatement ps; 
    ResultSet rs; 
    int resp;
    
    //Método listar de los elementos de Proveedor
    public List listar(){
        String sql = "select * from Proveedor"; 
        List<Proveedor> listaProveedor = new ArrayList<>(); 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                Proveedor pr = new Proveedor(); 
                pr.setCodigoProveedor(rs.getInt(1));
                pr.setNombreProveedor(rs.getString(2));
                pr.setDireccionProveedor(rs.getString(3));
                pr.setTelefonoProveedor(rs.getString(4));     
                listaProveedor.add(pr);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedor; 
    }
    
    //Método para agregar a Proveedor
    public int agregar(Proveedor pr){
        String sql = "insert into Proveedor (nombreProveedor, direccionProveedor, telefonoProveedor) values(?, ?, ?)"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setString(1, pr.getNombreProveedor());
            ps.setString(2, pr.getDireccionProveedor());
            ps.setString(3, pr.getTelefonoProveedor());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return resp; 
    }
    
    //Método para buscar por id en Proveedor
    public Proveedor listarProveedor(int id){
        Proveedor pr = new Proveedor(); 
        String sql = "select * from Proveedor where codigoProveedor = "+id;
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                pr.setNombreProveedor(rs.getString(2));
                pr.setDireccionProveedor(rs.getString(3));
                pr.setTelefonoProveedor(rs.getString(4));     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pr;       
    }
    
    //Método para actualizar en Proveedor
    public int editar(Proveedor pr){
        String sql = "Update Proveedor set nombreProveedor = ?, direccionProveedor = ?, telefonoProveedor = ? where codigoProveedor = ?"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setString(1, pr.getNombreProveedor());
            ps.setString(2, pr.getDireccionProveedor());
            ps.setString(3, pr.getTelefonoProveedor());
            ps.setInt(4, pr.getCodigoProveedor());
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp; 
    }
    
    //Método para eliminar en Proveedor
    public void eliminar(int id){
        String sql = "delete from Proveedor where codigoProveedor = "+id; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
    
    
}
