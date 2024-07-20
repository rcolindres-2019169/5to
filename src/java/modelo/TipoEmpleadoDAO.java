package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *Clase del Modelo DAO de la clase TipoEmpleado
 * @author David Balcárcel
 */

public class TipoEmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
    

    public TipoEmpleado validar(int codigoTipoEmpleado){
        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        // Vamos a agregar una variable de tipo String para nuestra consulta SQL
        String sql = "Select * from TipoEmpleado where codigoTipoEmpleado = ?";
        
        try{
        con = cn.Conexion();
        ps = con.prepareCall(sql);
        ps.setInt(1, codigoTipoEmpleado);
        rs = ps.executeQuery();
        while(rs.next()){
            tipoEmpleado.setCodigoTipoEmpleado(rs.getInt("codigoTipoEmpleado"));
            tipoEmpleado.setDescripcionTipoEmpleado(rs.getString("descripcionTipoEmpleado"));
            tipoEmpleado.setCategoria(rs.getString("categoria"));
            tipoEmpleado.setSueldo(rs.getDouble("sueldo"));
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return tipoEmpleado;// Retorna Tipo de servicio encontrado
        
    }

    /**
     * Método de listar los elementos de TipoEmpleado
     * @return lista de TipoRmpleado
     */ 
    public List Listar(){
        String sql = "Select * from TipoEmpleado";
        List<TipoEmpleado> listaTipoEmpleado = new ArrayList<>();
        try{
            con= cn.Conexion();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                TipoEmpleado te = new TipoEmpleado();
                te.setCodigoTipoEmpleado(rs.getInt(1));
                te.setDescripcionTipoEmpleado(rs.getString(2));
                te.setCategoria(rs.getString(3));
                te.setSueldo(rs.getDouble(4));
                listaTipoEmpleado.add(te);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaTipoEmpleado;
        
    }

    /**
     * Método para agregar a TipoEmpleado
     * @param emp objeto TipoEmpleado
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int agregar(TipoEmpleado emp){
        String sql ="Insert into TipoEmpleado ( descripcionTipoEmpleado, categoria, sueldo) values (?,?,?)" ;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getDescripcionTipoEmpleado());
            ps.setString(2, emp.getCategoria());
            ps.setDouble(3, emp.getSueldo());
            ps.executeUpdate();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return resp;
    }
    
    /**
     * Método para buscar por id en TipoEmpleado
     * @param id int id de la tupla que se desea buscar de TipoEmpleado
     * @return Objeto de TipoEmpleado
     */
    public TipoEmpleado listarCodigoTipoEmpleado(int id){
        TipoEmpleado te = new TipoEmpleado();
        String sql ="Select * from tipoEmpleado where codigoTipoEmpleado = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                    te.setCodigoTipoEmpleado(rs.getInt(1));
                    te.setDescripcionTipoEmpleado(rs.getString(2));
                    te.setCategoria(rs.getString(3));
                    te.setSueldo(rs.getDouble(4));
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        return te;
        }

    /**
     * Método para editar TipoEmpleado
     * @param te Objeto TipoEmpleado
     * @return int resp(solo retorna para utilizar el executeUpdate)
     */
    public int actualizar(TipoEmpleado te){
            String sql = "update TipoEmpleado set descripcionTipoEmpleado = ?, categoria = ?, sueldo = ?"
                    + "where codigoTipoEmpleado = ?";
            try{
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.setString(1, te.getDescripcionTipoEmpleado());
                ps.setString(2, te.getCategoria());
                ps.setDouble(3, te.getSueldo());
                ps.setInt(4, te.getCodigoTipoEmpleado());
                ps.executeUpdate();
            }catch(Exception e){
                e.printStackTrace();
            }

            return resp;
        }
   
     /**
      * Método para eliminar TipoEmpleado
      * @param id int id de la tupla que se desea eliminar de TipoEmpleado
      */
    public void eliminar(int id){
        String sql = "delete from TipoEmpleado where codigoTipoEmpleado = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
}
