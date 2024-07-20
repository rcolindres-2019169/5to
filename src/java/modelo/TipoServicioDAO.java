package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class TipoServicioDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    public TipoServicio validar(int codigoTipoServicio){
        // Vamos a instaciar un objeto de la entidad TipoServicio
        TipoServicio tipoServicio = new TipoServicio();
        // Vamos a agregar una variable de tipo String para nuestra consulta SQL
        String sql = "Select * from TipoServicio where codigoTipoServicio = ?";
        
        try{
        con = cn.Conexion();
        ps = con.prepareCall(sql);
        ps.setInt(1, codigoTipoServicio);
        rs = ps.executeQuery();
        while(rs.next()){
            tipoServicio.setCodigoTipoServicio(rs.getInt("codigoTipoServicio"));
            tipoServicio.setTipoServicio(rs.getString("tipoServicio"));
            tipoServicio.setDescripcion(rs.getString("descripcion"));
            tipoServicio.setPrecioTipoServicio(rs.getDouble("precioTipoServicio"));
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tipoServicio;// Retorna Tipo de servicio encontrado
        
    }
        public TipoServicio listarId(int id) {
        TipoServicio tsv = new TipoServicio();
        String sql = "select * from TipoServicio where codigoTipoServicio =" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                tsv.setCodigoTipoServicio(rs.getInt(1));
                tsv.setTipoServicio(rs.getString(2));
                tsv.setDescripcion(rs.getString(3));
                tsv.setPrecioTipoServicio(rs.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsv;
    }
    
    // Metodo Listar
    public List listar(){
        String sql = "Select * from TipoServicio";
        List<TipoServicio> listaTipoServicio = new ArrayList<>();
        try{
            con= cn.Conexion();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TipoServicio ts = new TipoServicio();
                ts.setCodigoTipoServicio(rs.getInt(1));
                ts.setTipoServicio(rs.getString(2));
                ts.setDescripcion(rs.getString(3));
                ts.setPrecioTipoServicio(rs.getDouble(4));
                listaTipoServicio.add(ts);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaTipoServicio;
        
    }
    
    // Metodo Agregar
    public int agregar(TipoServicio emp){
        String sql ="Insert into TipoServicio ( tipoServicio, descripcion, precioTipoServicio) values (?,?,?)" ;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getTipoServicio());
            ps.setString(2, emp.getDescripcion());
            ps.setDouble(3, emp.getPrecioTipoServicio());
            ps.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return resp;
    }
    
    // Buscar
    public TipoServicio listarCodigoTipoServicio(int id){
    TipoServicio ts = new TipoServicio();
    String sql ="Select * from TipoServicio where codigoTipoServicio = "+id;
    try{
        con = cn.Conexion();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
                ts.setCodigoTipoServicio(rs.getInt(1));
                ts.setTipoServicio(rs.getString(2));
                ts.setDescripcion(rs.getString(3));
                ts.setPrecioTipoServicio(rs.getDouble(4));
        }
    
    }catch(Exception e){
        e.printStackTrace();
    }
    
    
    return ts;
    }
    
    // Metodo actualizar
   public int editar(TipoServicio ts){
        String sql = "update TipoServicio set tipoServicio = ?, descripcion = ?, precioTipoServicio = ? where codigoTipoServicio = ?";
                
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, ts.getTipoServicio());
            ps.setString(2, ts.getDescripcion());
            ps.setDouble(3, ts.getPrecioTipoServicio());
            ps.setInt(4, ts.getCodigoTipoServicio());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resp;
    } 
   
   // Metodo Eliminar
   public void eliminar(int id){
        String sql = "delete from TipoServicio where codigoTipoServicio = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
