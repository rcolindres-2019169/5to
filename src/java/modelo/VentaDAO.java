package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author rodro
 */
public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int dato;
    String numero;
    int r;

    public String GenerarSerie() {
        String numeroSerie = "";
        String sql = "select max(numeroSerie) from Venta";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                numeroSerie = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numeroSerie;
    }

           

    public String numeroSerie(int dato) {
        this.dato = dato + 1;
        if ((this.dato >= 10000000) && (this.dato <= 100000000)) {
            numero = "" + this.dato;
        }
        if ((this.dato >= 1000000) && (this.dato <= 10000000)) {
            numero = "0" + this.dato;
        }
        if ((this.dato >= 100000) && (this.dato <= 1000000)) {
            numero = "00" + this.dato;
        }
        if ((this.dato >= 10000) && (this.dato <= 100000)) {
            numero = "000" + this.dato;
        }
        if ((this.dato >= 1000) && (this.dato <= 10000)) {
            numero = "0000" + this.dato;
        }
        if ((this.dato >= 100) && (this.dato <= 1000)) {
            numero = "00000" + this.dato;
        }

        if ((this.dato >= 10) && (this.dato <= 100)) {
            numero = "000000" + this.dato;
        }
        if (this.dato < 10) {
            numero = "0000000" + this.dato;
        }
        return numero;

    }

    public String idVenta() {
        String idVenta = "";
        String sql = "select max(codigoVenta) from Venta";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idVenta = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idVenta;
    }

    public int guardarVenta(Venta venta) {
        String sql = "call sp_AgregaVenta(?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getCodigoEmpresa());
            ps.setString(2, venta.getNumeroSerie());
            ps.setDouble(3, venta.getPrecio());
            ps.setString(4, venta.getEstado());
            ps.executeUpdate();
            System.out.println("asdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public int guardarDetalleVentas(Venta venta) {
        String sql = "insert into detalleVenta(codigoVenta, codigoServicio, precioVenta)values(?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getCodigoVenta());
            ps.setInt(2, venta.getCodigoTipoServicio());
            ps.setDouble(3, venta.getPrecio());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

}
