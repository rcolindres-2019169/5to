
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *Clase del Modelo DAO de la clase MedioTransporte
 * @author Carlos Altán
 */
public class MedioTransporteDAO {
    
    Conexion cn = new Conexion(); 
    Connection con; 
    PreparedStatement ps; 
    ResultSet rs; 
    int resp; 
    
    /**
     * Método que listar los elementos de MedioTransporte
     * @return lista de MedioTransporte
     */
    public List listar(){
        String sql = "select * from MedioTransporte"; 
        List<MedioTransporte> listaMedioTransporte = new ArrayList<>(); 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                MedioTransporte mt = new MedioTransporte(); 
                mt.setCodigoTransporte(rs.getInt(1));
                mt.setPlaca(rs.getString(2));
                mt.setTipoVehiculo(rs.getString(3));
                mt.setMarca(rs.getString(4)); 
                listaMedioTransporte.add(mt);
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMedioTransporte; 
    }
    
      /**
     * Método para agregar a MedioTransporte
     * @param mt objeto MedioTransporte
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int agregar(MedioTransporte mt){
        String sql = "insert into MedioTransporte (placa, tipoVehiculo, marca) values(?, ?, ?)"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setString(1, mt.getPlaca());
            ps.setString(2, mt.getTipoVehiculo());
            ps.setString(3, mt.getMarca());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return resp; 
    }
    /**
     * Método para buscar por id en MedioTransporte
     * @param id int id de la tupla que se desea buscar de MedioTransporte
     * @return Objeto de MedioTransporte
     */
    public MedioTransporte listarMedioTransporte(int id){
        MedioTransporte mt = new MedioTransporte(); 
        String sql = "select * from MedioTransporte where codigoTransporte = "+id;
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                mt.setCodigoTransporte(rs.getInt(1));
                mt.setPlaca(rs.getString(2));
                mt.setTipoVehiculo(rs.getString(3));
                mt.setMarca(rs.getString(4));     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mt;       
    }
    
    /**
     * Método para editar MedioTransporte
     * @param mt Objeto MedioTransporte
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int editar(MedioTransporte mt){
        String sql = "Update MedioTransporte set placa = ?, tipoVehiculo = ?, marca = ? where codigoTransporte = ?"; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setString(1, mt.getPlaca());
            ps.setString(2, mt.getTipoVehiculo());
            ps.setString(3, mt.getMarca());
            ps.setInt(4, mt.getCodigoTransporte());
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp; 
    }
     /**
      * Método para eliminar MedioTransporte
      * @param id int id de la tupla que se desea eliminar de MedioTransporte
      */
    public void eliminar(int id){
        String sql = "delete from MedioTransporte where codigoTransporte = "+id; 
        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }

}
