
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *Modelo Dao de la clase Compra
 * @author Denis Abad
 */
public class CompraDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    /**
     * Método para listar las compras
     * @return lista de compras
     */
    public List listar(){
        String sql = "select * from Compra";
        List<Compra> listaCompra = new ArrayList<>();
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Compra c = new Compra();
                c.setCodigoCompra(rs.getInt(1));
                c.setCostoCompra(rs.getDouble(2));
                c.setDescripcionCompra(rs.getString(3));
                c.setFechaCompra(rs.getDate(4));
                c.setCodigoEmpresa(rs.getInt(5));
                listaCompra.add(c);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return listaCompra;
    }
    
    /**
     * Método para agregar una compra
     * @param c Objeto Compra
     * @return variable resp para executeUpdate
     */
    public int agregar (Compra c){
        String sql = "Insert into Compra (costoCompra, descripcionCompra, fechaCompra, codigoEmpresa) values (?, ?, ? ,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, c.getCostoCompra());
            ps.setString(2, c.getDescripcionCompra());
            ps.setDate(3, new java.sql.Date(c.getFechaCompra().getTime()));
            ps.setInt(4, c.getCodigoEmpresa());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    /**
     * Método para listar una compra
     * @param id ID de la compra que se quiere listar 
     * @return retorna la compra a listar
     */
    public Compra listarCodigoCompra(int id){
        Compra c = new Compra();
        String sql = "Select * from Compra where codigoCompra =" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()) {
                c.setCodigoCompra(rs.getInt(1));
                c.setCostoCompra(rs.getDouble(2));
                c.setDescripcionCompra(rs.getString(3));
                c.setFechaCompra(rs.getDate(4));
                c.setCodigoEmpresa(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    /**
     * Método para editar una compra
     * @param c Objeto Compra
     * @return variable resp para executeUpdate
     */
    public int actualizar(Compra c){
        String sql = "Update Compra set costoCompra = ?, descripcionCompra = ?, fechaCompra = ? where codigoCompra = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, c.getCostoCompra());
            ps.setString(2, c.getDescripcionCompra());
            ps.setDate(3, new java.sql.Date(c.getFechaCompra().getTime()));
            ps.setInt(4, c.getCodigoCompra());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    /**
     * Método para eliminar una compra
     * @param id ID de la compra que se quiere eliminar
     */
    public void eliminar(int id){
        String sql = "Delete from Compra where codigoCompra =" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
