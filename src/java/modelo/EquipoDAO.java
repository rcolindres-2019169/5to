
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase del Modelo DAO de la clase Equipo
 * @author Carlos Armas
 */
public class EquipoDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;   
    
    /**
     * Método que listar los elementos de Equipo
     * @return lista de Equipo
     */
    public List listar(){
        String sql = "select * from equipo";
        List<Equipo> listaEquipo = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Equipo eq = new Equipo();
                eq.setCodigoEquipo(rs.getInt(1));
                eq.setNombreEquipo(rs.getString(2));
                eq.setDescripcionEquipo(rs.getString(3));
                eq.setCantidad(rs.getInt(4));
                listaEquipo.add(eq);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEquipo;
    }
    
    /**
     * Método para agregar a Equipo
     * @param eq objeto Equipo
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int agregar(Equipo eq){
        String sql = "insert into Equipo (nombreEquipo, descripcionEquipo, cantidad) values(?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, eq.getNombreEquipo());
            ps.setString(2, eq.getDescripcionEquipo());
            ps.setInt(3, eq.getCantidad());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
     /**
     * Método para buscar por id en Equipo
     * @param id int id de la tupla que se desea buscar de Equipo
     * @return Objeto de Equipo
     */
    public Equipo listarEquipo(int id){
        Equipo eq = new Equipo();
        String sql = "select * from Equipo where codigoEquipo = "+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                eq.setCodigoEquipo(rs.getInt(1));
                eq.setNombreEquipo(rs.getString(2));
                eq.setDescripcionEquipo(rs.getString(3));
                eq.setCantidad(rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eq;
    }
    
    /**
     * Método para editar Equipo
     * @param eq Objeto Equipo
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int editar(Equipo eq){
        String sql = "Update Equipo set nombreEquipo = ?, descripcionEquipo = ?, cantidad = ? where codigoEquipo = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, eq.getNombreEquipo());
            ps.setString(2, eq.getDescripcionEquipo());
            ps.setInt(3, eq.getCantidad());
            ps.setInt(4, eq.getCodigoEquipo());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    /**
     * Método para eliminar Equipo
     * @param id int id de la tupla que se desea eliminar de Equipo
     */
    public void eliminar(int id){
        String sql = "delete from Equipo where codigoEquipo = "+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
