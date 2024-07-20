package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 * Modelo DAO de Empleado
 * @author Ricardo Adrián Colindres Franco - 2019169
 */
public class EmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;
    
   
    public Empleado validar(String usuario, String DPIEmpleado){
        Empleado empleado = new Empleado();
        String sql = "select * from Empleado where usuario = ? and DPIEmpleado = ?";
        try
        {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.setString(1, usuario);
            ps.setString(2, DPIEmpleado);
            rs = ps.executeQuery();
            while (rs.next()){                
                empleado.setCodigoEmpleado(rs.getInt("codigoEmpleado"));
                empleado.setUsuario(rs.getString("usuario"));
                empleado.setDPIEmpleado(rs.getString("DPIEmpleado"));
                empleado.setNombresEmpleado(rs.getString("nombresEmpleado"));
                empleado.setApellidosEmpleado(rs.getString("apellidosEmpleado"));
                empleado.setTelefonoContacto(rs.getString("telefonoContacto"));
                empleado.setCodigoTipoEmpleado(rs.getInt("codigoTipoEmpleado"));
                empleado.setCodigoTransporte(rs.getInt("codigoTransporte"));
                empleado.setCodigoEquipo(rs.getInt("codigoEquipo"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return empleado;
    }
    
    //Método que lista las tuplas de la entidad Emepleado
    public List listar(){
        String sql = "select * from Empleado";
        List<Empleado>listaEmpleado = new ArrayList<>();
        try
        {
          con = cn.Conexion();
          ps = con.prepareStatement(sql);
          rs = ps.executeQuery();
            while (rs.next()){                
                Empleado em = new Empleado();
                em.setCodigoEmpleado(rs.getInt(1));
                em.setUsuario(rs.getString(2));
                em.setDPIEmpleado(rs.getString(3));
                em.setNombresEmpleado(rs.getString(4));
                em.setApellidosEmpleado(rs.getString(5));
                em.setTelefonoContacto(rs.getString(6));
                em.setCodigoTipoEmpleado(rs.getInt(7));
                em.setCodigoTransporte(rs.getInt(8));
                em.setCodigoEquipo(rs.getInt(9));
                em.setFoto(rs.getBinaryStream(10));
                listaEmpleado.add(em);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaEmpleado;
    }
    
    public void listarImg(int codigoEmpleado, HttpServletResponse response){
        String sql = "Select * from Empleado where codigoEmpleado="+ codigoEmpleado; 
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        response.setContentType("img/*");        
        try {
            outputStream=response.getOutputStream();
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                inputStream = rs.getBinaryStream(10); 
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOutputStream=new BufferedOutputStream(outputStream);
            int i = 0; 
            while ((i=bufferedInputStream.read())!= -1) {
                bufferedOutputStream.write(i);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Método que agrega datos a Empleado
    public int agregar(Empleado emp){
        String sql = "Insert into Empleado(usuario, DPIEmpleado, nombresEmpleado, apellidosEmpleado, telefonoContacto, codigoTipoEmpleado, codigoTransporte, codigoEquipo, foto) values(?,?,?,?,?,?,?,?,?)";
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            ps.setString(1, emp.getUsuario());
            ps.setString(2,emp.getDPIEmpleado());
            ps.setString(3, emp.getNombresEmpleado());
            ps.setString(4, emp.getApellidosEmpleado());
            ps.setString(5, emp.getTelefonoContacto());
            ps.setInt(6, emp.getCodigoTipoEmpleado());
            ps.setInt(7, emp.getCodigoTransporte());
            ps.setInt(8, emp.getCodigoEquipo());
            ps.setBlob(9, emp.getFoto());
            ps.executeUpdate(); 
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No se agrego el registro");
        }
        return resp;
    }
    
    //Método para buscar empleado por codigo del empleado
    public Empleado listarCodigoEmpleado(int id){
        Empleado emp = new Empleado();
        String sql = "select * from Empleado where codigoEmpleado = "+id;
        try{
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()){                
                emp.setUsuario(rs.getString(2));
                emp.setDPIEmpleado(rs.getString(3));
                emp.setNombresEmpleado(rs.getString(4));
                emp.setApellidosEmpleado(rs.getString(5));
                emp.setTelefonoContacto(rs.getString(6));
                emp.setCodigoTipoEmpleado(rs.getInt(7));
                emp.setCodigoTransporte(rs.getInt(8));
                emp.setCodigoEquipo(rs.getInt(9));
                emp.setFoto(rs.getBinaryStream(10));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return emp;
    }
    
    //Método que actualiza los datos de Empleado
    public int actualizar(Empleado emp){
        String sql = "update Empleado set usuario = ?, DPIEmpleado = ?, nombresEmpleado = ?, apellidosEmpleado = ?, telefonoContacto = ?, foto = ? where codigoEmpleado = ? ";
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, emp.getUsuario());
            ps.setString(2, emp.getDPIEmpleado());
            ps.setString(3, emp.getNombresEmpleado());
            ps.setString(4, emp.getApellidosEmpleado());
            ps.setString(5, emp.getTelefonoContacto());
            ps.setBlob(6, emp.getFoto());
            ps.setInt(7, emp.getCodigoEmpleado());
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("No se agrego el registro");
        }
        return resp;
    }
    
    //Método que elimina tupla de Empleado
    public void eliminar(int id){
        String sql = "delete from Empleado where codigoEmpleado = " + id;
        try{
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
