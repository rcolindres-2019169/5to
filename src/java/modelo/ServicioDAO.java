package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Díaz
 */
public class ServicioDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List listar() {
        String sql = "select * from Servicio";
        List<Servicio> listaServicio = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setCodigoServicio(rs.getInt(1));
                servicio.setLugarServicio(rs.getString(2));
                servicio.setNumeroServicio(rs.getString(3));
                servicio.setHoraServicio(rs.getString(4));
                servicio.setFechaServicio(rs.getDate(5));
                servicio.setCodigoTipoServicio(rs.getInt(6));
                listaServicio.add(servicio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaServicio;
    }

    public int agregar(Servicio servi) {
        String sql = "insert into Servicio (lugarServicio, numeroServicio, horaServicio, fechaServicio, codigoTipoServicio) values(?, ?, ?, ?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, servi.getLugarServicio());
            ps.setString(2, servi.getNumeroServicio());
            ps.setString(3, servi.getHoraServicio());
            ps.setDate(4, new java.sql.Date(servi.getFechaServicio().getTime()));
            ps.setInt(5, servi.getCodigoTipoServicio());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int actualizar(Servicio servi) {
        String sql = "Update Servicio set lugarServicio = ?, numeroServicio = ?, horaServicio = ?, fechaServicio = ? where codigoServicio = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, servi.getLugarServicio());
            ps.setString(2, servi.getNumeroServicio());
            ps.setString(3, servi.getHoraServicio());
            ps.setDate(4, new java.sql.Date(servi.getFechaServicio().getTime()));
            ps.setInt(5, servi.getCodigoServicio());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;

    }

    public Servicio listarCodigoServicio(int id) {
        Servicio ser = new Servicio();
        String sql = "Select * from Servicio where codigoServicio = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ser.setLugarServicio(rs.getString(2));
                ser.setNumeroServicio(rs.getString(3));
                ser.setHoraServicio(rs.getString(4));
                ser.setFechaServicio(rs.getDate(5));
                ser.setCodigoTipoServicio(rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ser;
    }

    public void eliminar(int id) {
        String sql = "delete from Servicio where codigoServicio = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
