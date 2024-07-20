package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Servicio_has_CompraDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    /**
     * Metodo que lista los elementos de Servicio_has_Compra
     * @return lista de Servicio_has_Compra
     */
    
    public List listar(){
        String sql = "select * from Servicio_has_Compra";
        List<Servicio_has_Compra> listaServicio_has_Compra = new ArrayList<>();
        try{
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();
            while(rs.next()){
                Servicio_has_Compra sc = new Servicio_has_Compra();
                sc.setServicio_codigoCompra(rs.getInt(1));
                sc.setDescripcionDetalle(rs.getString(2));
                sc.setCodigoServicio(rs.getInt(3));
                sc.setCodigoCompra(rs.getInt(4));
                listaServicio_has_Compra.add(sc);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicio_has_Compra;
    }
    
    //Método Agregar
    
    public int agregar(Servicio_has_Compra shc){
        String sql = "insert into Servicio_has_Compra (descripcionDetalle, codigoServicio, codigoCompra) values (?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, shc.getDescripcionDetalle());
            ps.setInt(2, shc.getCodigoServicio());
            ps.setInt(3, shc.getCodigoCompra());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Método Buscar
    
    public Servicio_has_Compra listaServicio_has_Compra(int id){
        Servicio_has_Compra seh = new Servicio_has_Compra();
        String sql = "select * from Servicio_has_Compra where Servicio_codigoCompra = "+id;
        try{
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            rs = ps.executeQuery();
            while(rs.next()){
                seh.setServicio_codigoCompra(rs.getInt(1));
                seh.setDescripcionDetalle(rs.getString(2));
                seh.setCodigoServicio(rs.getInt(3));
                seh.setCodigoCompra(rs.getInt(4));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return seh;
    }
    
    // Método Editar
    
    public int actualizar(Servicio_has_Compra em){
        String sql = "Update Servicio_has_Compra set descripcionDetalle = ? where Servicio_codigoCompra = ?";
        try{
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDescripcionDetalle());
            ps.setInt(2, em.getServicio_codigoCompra());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resp;
    }
    
    //Método Eliminar
    
    public void eliminar(int id){
        String sql = "delete from Servicio_has_Compra where Servicio_codigoCompra = "+id;
        try{
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.executeUpdate(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
