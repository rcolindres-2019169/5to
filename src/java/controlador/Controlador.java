/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import modelo.Compra;
import modelo.CompraDAO;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import modelo.Empleados_has_Servicios;
import modelo.Empleados_has_ServiciosDAO;
import modelo.Empresa;
import modelo.EmpresaDao;
import modelo.Equipo;
import modelo.EquipoDAO;
import modelo.Equipo_has_Empleado;
import modelo.Equipo_has_EmpleadoDAO;
import modelo.MedioTransporte;
import modelo.MedioTransporteDAO;
import modelo.Proveedor;
import modelo.ProveedorDAO;
import modelo.Proveedor_has_Equipo;
import modelo.Proveedor_has_EquipoDAO;
import modelo.Servicio;
import modelo.ServicioDAO;
import modelo.Servicio_has_Compra;
import modelo.Servicio_has_CompraDAO;
import modelo.TipoEmpleado;
import modelo.TipoEmpleadoDAO;
import modelo.TipoServicio;
import modelo.TipoServicioDAO;
import modelo.Venta;
import modelo.VentaDAO;

/**
 *
 * @author colin
 */
@MultipartConfig
public class Controlador extends HttpServlet {

    MedioTransporte medioTransporte = new MedioTransporte();
    MedioTransporteDAO medioTransporteDAO = new MedioTransporteDAO();
    int codMedioTransporte;
    Equipo equipo = new Equipo();
    EquipoDAO equipoDAO = new EquipoDAO();
    Proveedor_has_Equipo proveedor_has_Equipo = new Proveedor_has_Equipo();
    Proveedor_has_EquipoDAO proveedor_has_EquipoDAO = new Proveedor_has_EquipoDAO();
    int codProveedorEquipo;
    Servicio_has_Compra servicio_has_Compra = new Servicio_has_Compra();
    Servicio_has_CompraDAO servicio_has_CompraDAO = new Servicio_has_CompraDAO();
    int codServicioCompra;
    Compra compra = new Compra();
    CompraDAO compraDAO = new CompraDAO();
    Servicio servicio = new Servicio();
    ServicioDAO servicioDAO = new ServicioDAO();
    Equipo_has_Empleado equipo_has_Empleado = new Equipo_has_Empleado();
    Equipo_has_EmpleadoDAO equipo_has_EmpleadoDAO = new Equipo_has_EmpleadoDAO();
    TipoEmpleado tipoEmpleado = new TipoEmpleado();
    TipoEmpleadoDAO tipoEmpleadoDAO = new TipoEmpleadoDAO();
    Empleado empleado = new Empleado();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDao = new ProveedorDAO();
    Empresa empresa = new Empresa();
    EmpresaDao empresaDao = new EmpresaDao();
    Empleados_has_Servicios empleados_has_servicios = new Empleados_has_Servicios();
    Empleados_has_ServiciosDAO empleados_has_serviciosDAO = new Empleados_has_ServiciosDAO();
    TipoServicio tipoServicio = new TipoServicio();
    TipoServicioDAO tipoServicioDAO = new TipoServicioDAO();
    int codCompra;
    int codServicio;
    int codEquipo;
    int codTipoEmpleado;
    int codTipoServicio;
    int codEmpresa;
    int Equipo_codigoEmpleado;
    int codEmpleado;
    int codEmpleadoServicio;
    int codProveedor;

    Venta venta = new Venta();
    VentaDAO ventaDao = new VentaDAO();
    List<Venta> lista = new ArrayList<>();
    int item = 0;
    int codigoTipoServicio;
    String descripcion;
    String numeroSerie;
    double precio;
    double subTotal;
    double totalPagar;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);

        } else if (menu.equals("HomePage")) {
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } else if (menu.equals("Equipo")) {
            switch (accion) {
                case "Listar":
                    List listaEquipo = equipoDAO.listar();
                    request.setAttribute("equipos", listaEquipo);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombreEquipo");
                    String decri = request.getParameter("txtDescripcionEquipo");
                    String cantidad = request.getParameter("txtCantidad");
                    equipo.setNombreEquipo(nombre);
                    equipo.setDescripcionEquipo(decri);
                    equipo.setCantidad(Integer.parseInt(cantidad));
                    equipoDAO.agregar(equipo);
                    request.getRequestDispatcher("Controlador?menu=Equipo&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    codEquipo = Integer.parseInt(request.getParameter("codigoEquipo"));
                    Equipo e = equipoDAO.listarEquipo(codEquipo);
                    request.setAttribute("equipo", e);
                    request.getRequestDispatcher("Controlador?menu=Equipo&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String nombreEM = request.getParameter("txtNombreEquipo");
                    String descriEM = request.getParameter("txtDescripcionEquipo");
                    String cantidadEM = request.getParameter("txtCantidad");
                    equipo.setNombreEquipo(nombreEM);
                    equipo.setDescripcionEquipo(descriEM);
                    equipo.setCantidad(Integer.parseInt(cantidadEM));
                    equipo.setCodigoEquipo(codEquipo);
                    equipoDAO.editar(equipo);
                    request.getRequestDispatcher("Controlador?menu=Equipo&accion=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    codEquipo = Integer.parseInt(request.getParameter("codigoEquipo"));
                    equipoDAO.eliminar(codEquipo);
                    request.getRequestDispatcher("Controlador?menu=Equipo&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Equipo.jsp").forward(request, response);
        } else if (menu.equals("ProveedorEquipo")) {
            switch (accion) {
                case "Listar":
                    List listaProveedorEquipo = proveedor_has_EquipoDAO.listar();
                    request.setAttribute("proveedorEquipos", listaProveedorEquipo);

                    List listaProveedor = proveedorDao.listar();
                    request.setAttribute("proveedores", listaProveedor);

                    List listaEquipo = equipoDAO.listar();
                    request.setAttribute("equipos", listaEquipo);
                    break;
                case "Agregar":
                    String Descripcion = request.getParameter("txtDescripcionProveedor");
                    //String codProveedor = request.getParameter("txtCodigoProveedor");
                    //String codEquipo = request.getParameter("txtCodigoEquipo");
                    String disponibilidad = request.getParameter("cmbDisponible");
                    String cmb = request.getParameter("cmbCodigoProveedor");
                    proveedor_has_Equipo.setDescripcionProveedor(Descripcion);
                    proveedor_has_Equipo.setCodigoProveedor(Integer.parseInt(cmb));
                    //proveedor_has_Equipo.setCodigoProveedor(Integer.parseInt(codProveedor));
                    proveedor_has_Equipo.setCodigoEquipo(Integer.parseInt(disponibilidad));
                    //proveedor_has_Equipo.setCodigoEquipo(Integer.parseInt(codEquipo));
                    proveedor_has_EquipoDAO.agregar(proveedor_has_Equipo);
                    request.getRequestDispatcher("Controlador?menu=ProveedorEquipo&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codProveedorEquipo = Integer.parseInt(request.getParameter("codigoProveedor_has_Equipo"));
                    Proveedor_has_Equipo pe = proveedor_has_EquipoDAO.listaProveedor_has_Equipo(codProveedorEquipo);
                    request.setAttribute("Proequipo", pe);
                    request.getRequestDispatcher("Controlador?menu=ProveedorEquipo&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String DescripcionPe = request.getParameter("txtDescripcionProveedor");
                    String disponibilidadPe = request.getParameter("cmbDisponible");
                    String cmbPe = request.getParameter("cmbCodigoProveedor");
                    proveedor_has_Equipo.setDescripcionProveedor(DescripcionPe);
                    //proveedor_has_Equipo.setCodigoProveedor(Integer.parseInt(cmbPe));
                    //proveedor_has_Equipo.setCodigoEquipo(Integer.parseInt(disponibilidadPe));    
                    proveedor_has_Equipo.setCodigoProveedor_has_Equipo(codProveedorEquipo);
                    proveedor_has_EquipoDAO.actualizar(proveedor_has_Equipo);
                    request.getRequestDispatcher("Controlador?menu=ProveedorEquipo&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codProveedorEquipo = Integer.parseInt(request.getParameter("codigoProveedor_has_Equipo"));
                    proveedor_has_EquipoDAO.eliminar(codProveedorEquipo);
                    request.getRequestDispatcher("Controlador?menu=ProveedorEquipo&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("ProveedorEquipo.jsp").forward(request, response);
        } else if (menu.equals("ServicioCompra")) {
            switch (accion) {
                case "Listar":
                    List listaServicioCompra = servicio_has_CompraDAO.listar();
                    request.setAttribute("servicioCompras", listaServicioCompra);

                    List listaServicio = servicioDAO.listar();
                    request.setAttribute("servicios", listaServicio);

                    List listaCompras = compraDAO.listar();
                    request.setAttribute("compras", listaCompras);
                    break;
                case "Agregar":
                    String Descripcion = request.getParameter("txtDescripcion");
                    String codServicio = request.getParameter("cmbCodigoServicio");
                    String codCompra = request.getParameter("cmbCodigoCompra");
                    servicio_has_Compra.setDescripcionDetalle(Descripcion);
                    servicio_has_Compra.setCodigoServicio(Integer.parseInt(codServicio));
                    servicio_has_Compra.setCodigoCompra(Integer.parseInt(codCompra));
                    servicio_has_CompraDAO.agregar(servicio_has_Compra);
                    request.getRequestDispatcher("Controlador?menu=ServicioCompra&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codServicioCompra = Integer.parseInt(request.getParameter("Servicio_codigoCompra"));
                    Servicio_has_Compra sc = servicio_has_CompraDAO.listaServicio_has_Compra(codServicioCompra);
                    request.setAttribute("Sercompra", sc);
                    request.getRequestDispatcher("Controlador?menu=ServicioCompra&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String DescripcionSc = request.getParameter("txtDescripcion");
                    // String codServicioSc = request.getParameter("cmbCodigoServicio");
                    // String codCompraSc = request.getParameter("cmbCodigoCompra");
                    servicio_has_Compra.setDescripcionDetalle(DescripcionSc);
                    // servicio_has_Compra.setCodigoServicio(Integer.parseInt(codServicioSc));
                    // servicio_has_Compra.setCodigoCompra(Integer.parseInt(codCompraSc));
                    servicio_has_Compra.setServicio_codigoCompra(codServicioCompra);
                    servicio_has_CompraDAO.actualizar(servicio_has_Compra);
                    request.getRequestDispatcher("Controlador?menu=ServicioCompra&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codServicioCompra = Integer.parseInt(request.getParameter("Servicio_codigoCompra"));
                    servicio_has_CompraDAO.eliminar(codServicioCompra);
                    request.getRequestDispatcher("Controlador?menu=ServicioCompra&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("ServicioCompra.jsp").forward(request, response);
        } else if (menu.equals("Compra")) {
            switch (accion) {
                case "Listar":
                    List listaCompras = compraDAO.listar();
                    request.setAttribute("compras", listaCompras);
                    List listaEmpresa = empresaDao.listar();
                    request.setAttribute("empresas", listaEmpresa);
                    break;
                case "Agregar":
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String costo = request.getParameter("txtCostoCompra");
                    String descri = request.getParameter("txtDescripcionCompra");
                    String fecha = request.getParameter("txtFechaCompra");
                    String codEmpre = request.getParameter("cmbCodigoEmpresa");
                    try {
                        java.util.Date formato = formatoFecha.parse(fecha);
                        java.sql.Date fechaCompra = new java.sql.Date(formato.getTime());
                        compra.setCostoCompra(Double.parseDouble(costo));
                        compra.setDescripcionCompra(descri);
                        compra.setFechaCompra(fechaCompra);
                        compra.setCodigoEmpresa(Integer.parseInt(codEmpre));
                        compraDAO.agregar(compra);
                        request.getRequestDispatcher("Controlador?menu=Compra&accion=Listar").forward(request, response);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Editar":
                    codCompra = Integer.parseInt(request.getParameter("codigoCompra"));
                    Compra c = compraDAO.listarCodigoCompra(codCompra);
                    request.setAttribute("compra", c);
                    request.getRequestDispatcher("Controlador?menu=Compra&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
                    String costoCom = request.getParameter("txtCostoCompra");
                    String descriCom = request.getParameter("txtDescripcionCompra");
                    String fechaCom = request.getParameter("txtFechaCompra");
                    try {
                        java.util.Date formato = fechaFormato.parse(fechaCom);
                        java.sql.Date fechaCompra = new java.sql.Date(formato.getTime());
                        compra.setCostoCompra(Double.parseDouble(costoCom));
                        compra.setDescripcionCompra(descriCom);
                        compra.setFechaCompra(fechaCompra);
                        compra.setCodigoCompra(codCompra);
                        compraDAO.actualizar(compra);
                        request.getRequestDispatcher("Controlador?menu=Compra&accion=Listar").forward(request, response);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Eliminar":
                    codCompra = Integer.parseInt(request.getParameter("codigoCompra"));
                    compraDAO.eliminar(codCompra);
                    request.getRequestDispatcher("Controlador?menu=Compra&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Compra.jsp").forward(request, response);
        } else if (menu.equals("Servicio")) {
            switch (accion) {
                case "Listar":
                    List listaServicio = servicioDAO.listar();
                    request.setAttribute("servicios", listaServicio);
                    List listaTipoServicio = tipoServicioDAO.listar();
                    request.setAttribute("tipoServicios", listaTipoServicio);
                    break;
                case "Agregar":
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    String lugar = request.getParameter("txtLugarServicio");
                    String numero = request.getParameter("txtNumeroServicio");
                    String hora = request.getParameter("txtHoraServicio");
                    String fecha = request.getParameter("txtFechaServicio");
                    int codigoTipo = Integer.parseInt(request.getParameter("cmbCodigoTipoServicio"));
                    try {
                        java.util.Date formato = formatoFecha.parse(fecha);
                        java.sql.Date fechaCompra = new java.sql.Date(formato.getTime());
                        servicio.setLugarServicio(lugar);
                        servicio.setNumeroServicio(numero);
                        servicio.setHoraServicio(hora);
                        servicio.setFechaServicio(fechaCompra);
                        servicio.setCodigoTipoServicio(codigoTipo);
                        servicioDAO.agregar(servicio);
                        request.getRequestDispatcher("Controlador?menu=Servicio&accion=Listar").forward(request, response);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case "Editar":

                    codServicio = Integer.parseInt(request.getParameter("codigoServicio"));
                    Servicio s = servicioDAO.listarCodigoServicio(codServicio);
                    request.setAttribute("servicio", s);
                    request.getRequestDispatcher("Controlador?menu=Servicio&accion=Listar").forward(request, response);

                    break;

                case "Actualizar":
                    SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
                    String lugarS = request.getParameter("txtLugarServicio");
                    String numeroS = request.getParameter("txtNumeroServicio");
                    String horaS = request.getParameter("txtHoraServicio");
                    String fechaSer = request.getParameter("txtFechaServicio");
                    try {
                        java.util.Date formato = fechaFormato.parse(fechaSer);
                        java.sql.Date fechaServicio = new java.sql.Date(formato.getTime());
                        servicio.setLugarServicio(lugarS);
                        servicio.setNumeroServicio(numeroS);
                        servicio.setHoraServicio(horaS);
                        servicio.setFechaServicio(fechaServicio);
                        servicio.setCodigoServicio(codServicio);
                        servicioDAO.actualizar(servicio);
                        request.getRequestDispatcher("Controlador?menu=Servicio&accion=Listar").forward(request, response);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;

                case "Eliminar":
                    codServicio = Integer.parseInt(request.getParameter("codigoServicio"));
                    servicioDAO.eliminar(codServicio);
                    request.getRequestDispatcher("Controlador?menu=Servicio&accion=Listar").forward(request, response);

                    break;
            }
            request.getRequestDispatcher("Servicio.jsp").forward(request, response);

        } else if (menu.equals("EquipoEmpleado")) {
            switch (accion) {
                case "Listar":
                    List listaEmpleados = empleadoDAO.listar();
                    request.setAttribute("empleados", listaEmpleados);
                    List listaEquipoEmpleado = equipo_has_EmpleadoDAO.listar();
                    request.setAttribute("equipoEmpleados", listaEquipoEmpleado);
                    List listaEquipo = equipoDAO.listar();
                    request.setAttribute("equipos", listaEquipo);
                    break;
                case "Agregar":
                    String cadEquipo = request.getParameter("cmbCodigoEquipo");
                    String cadEmpleado = request.getParameter("cmbEmpleadoEquipo");
                    String canEquipo = request.getParameter("txtCantidadEquipo");
                    equipo_has_Empleado.setCodigoEquipo(Integer.parseInt(cadEquipo));
                    System.out.println(cadEquipo);
                    equipo_has_Empleado.setCodigoEmpleado(Integer.parseInt(cadEmpleado));
                    System.out.println(cadEmpleado);
                    equipo_has_Empleado.setCantidadEquipo(Integer.parseInt(canEquipo));
                    System.out.println(canEquipo);
                    equipo_has_EmpleadoDAO.agregar(equipo_has_Empleado);
                    request.getRequestDispatcher("Controlador?menu=EquipoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    Equipo_codigoEmpleado = Integer.parseInt(request.getParameter("Equipo_codigoEmpleado"));
                    Equipo_has_Empleado emp = equipo_has_EmpleadoDAO.listarEquipoEmpleado(Equipo_codigoEmpleado);
                    request.setAttribute("equipoEmpleado", emp);
                    request.getRequestDispatcher("Controlador?menu=EquipoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String canEquipos = request.getParameter("txtCantidadEquipo");
                    equipo_has_Empleado.setCantidadEquipo(Integer.parseInt(canEquipos));
                    equipo_has_Empleado.setEquipo_codigoEmpleado(Equipo_codigoEmpleado);
                    equipo_has_EmpleadoDAO.actualizar(equipo_has_Empleado);
                    request.getRequestDispatcher("Controlador?menu=EquipoEmpleado&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    Equipo_codigoEmpleado = Integer.parseInt(request.getParameter("Equipo_codigoEmpleado"));
                    equipo_has_EmpleadoDAO.eliminar(Equipo_codigoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=EquipoEmpleado&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("EquipoEmpleado.jsp").forward(request, response);

        } else if (menu.equals(
                "TipoEmpleado")) {
            switch (accion) {
                case "Listar":
                    List listaTiposEmpleados = tipoEmpleadoDAO.Listar();
                    request.setAttribute("tiposEmpleados", listaTiposEmpleados);
                    break;
                case "Agregar":
                    String descrip = request.getParameter("txtDescripcionTipoEmpleado");
                    String cate = request.getParameter("txtCategoria");
                    String sueldo = request.getParameter("txtSueldo");
                    tipoEmpleado.setDescripcionTipoEmpleado(descrip);
                    tipoEmpleado.setCategoria(cate);
                    tipoEmpleado.setSueldo(Double.parseDouble(sueldo));
                    tipoEmpleadoDAO.agregar(tipoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=TipoEmpleado&accion=Listar").forward(request, response);

                    break;
                case "Editar":
                    codTipoEmpleado = Integer.parseInt(request.getParameter("codigoTipoEmpleado"));
                    TipoEmpleado te = tipoEmpleadoDAO.listarCodigoTipoEmpleado(codTipoEmpleado);
                    request.setAttribute("tipoEmpleado", te);
                    request.getRequestDispatcher("Controlador?menu=TipoEmpleado&accion=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    String descripTE = request.getParameter("txtDescripcionTipoEmpleado");
                    String cateTE = request.getParameter("txtCategoria");
                    String sueldoTE = request.getParameter("txtSueldo");
                    tipoEmpleado.setDescripcionTipoEmpleado(descripTE);
                    tipoEmpleado.setCategoria(cateTE);
                    tipoEmpleado.setSueldo(Double.parseDouble(sueldoTE));
                    tipoEmpleado.setCodigoTipoEmpleado(codTipoEmpleado);
                    tipoEmpleadoDAO.actualizar(tipoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=TipoEmpleado&accion=Listar").forward(request, response);

                    break;
                case "Eliminar":
                    codTipoEmpleado = Integer.parseInt(request.getParameter("codigoTipoEmpleado"));
                    tipoEmpleadoDAO.eliminar(codTipoEmpleado);
                    request.getRequestDispatcher("Controlador?menu=TipoEmpleado&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("TipoEmpleado.jsp").forward(request, response);
        } else if (menu.equals(
                "Empleado")) {
            switch (accion) {

                case "Listar":
                    List listaEmpleados = empleadoDAO.listar();
                    request.setAttribute("empleados", listaEmpleados);
                    List listaTipos = tipoEmpleadoDAO.Listar();
                    request.setAttribute("tipos", listaTipos);
                    List listaTrasporte = medioTransporteDAO.listar();
                    request.setAttribute("transportes", listaTrasporte);
                    List listaEquipo = equipoDAO.listar();
                    request.setAttribute("equipos", listaEquipo);
                    break;
                case "Agregar":
                    String usuario = request.getParameter("txtUsuario");
                    String DPI = request.getParameter("txtDPIEmpleado");
                    String nombres = request.getParameter("txtNombresEmpleado");
                    String apellidos = request.getParameter("txtApellidosEmpleado");
                    String telefono = request.getParameter("txtTelefonoContacto");
                    String codTipo = request.getParameter("cmbCodigoTipoEmpleado");
                    String codTransporte = request.getParameter("cmbCodigoMedioTransporte");
                    String codEquipo = request.getParameter("cmbCodigoEquipo");
                    Part part = request.getPart("fileFoto");
                    InputStream inputStream = part.getInputStream();

                    empleado.setUsuario(usuario);
                    empleado.setDPIEmpleado(DPI);
                    empleado.setNombresEmpleado(nombres);
                    empleado.setApellidosEmpleado(apellidos);
                    empleado.setTelefonoContacto(telefono);
                    empleado.setCodigoTipoEmpleado(Integer.parseInt(codTipo));
                    empleado.setCodigoTransporte(Integer.parseInt(codTransporte));
                    empleado.setCodigoEquipo(Integer.parseInt(codEquipo));
                    empleado.setFoto(inputStream);
                    empleadoDAO.agregar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);

                    break;
                case "Editar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    Empleado e = empleadoDAO.listarCodigoEmpleado(codEmpleado);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String usuarioEmp = request.getParameter("txtUsuario");
                    String DPIEmp = request.getParameter("txtDPIEmpleado");
                    String nombresEmp = request.getParameter("txtNombresEmpleado");
                    String apellidosEmp = request.getParameter("txtApellidosEmpleado");
                    String telefonoEmp = request.getParameter("txtTelefonoContacto");
                    String tipoEmp = request.getParameter("cmbCodigoTipoEmpleado");
                    String transporteEmp = request.getParameter("cmbCodigoMedioTransporte");
                    String equipoEmp = request.getParameter("cmbCodigoEquipo");
                    Part part1 = request.getPart("fileFoto");
                    InputStream inputStream1 = part1.getInputStream();
                    empleado.setUsuario(usuarioEmp);
                    empleado.setDPIEmpleado(DPIEmp);
                    empleado.setNombresEmpleado(nombresEmp);
                    empleado.setApellidosEmpleado(apellidosEmp);
                    empleado.setTelefonoContacto(telefonoEmp);
                    empleado.setFoto(inputStream1);
                    //empleado.setCodigoTipoEmpleado(Integer.parseInt(tipoEmp));
                    //empleado.setCodigoTransporte(Integer.parseInt(transporteEmp));
                    //empleado.setCodigoEquipo(Integer.parseInt(equipoEmp));
                    empleado.setCodigoEmpleado(codEmpleado);
                    empleadoDAO.actualizar(empleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codEmpleado = Integer.parseInt(request.getParameter("codigoEmpleado"));
                    empleadoDAO.eliminar(codEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        } else if (menu.equals("MedioTransporte")) {
            switch (accion) {
                case "Listar":
                    List listaMedioTransporte = medioTransporteDAO.listar();
                    request.setAttribute("medioTransportes", listaMedioTransporte);
                    break;
                case "Agregar":
                    String placa = request.getParameter("txtPlaca");
                    String tipoVehiculo = request.getParameter("txtTipoVehiculo");
                    String marca = request.getParameter("txtMarca");
                    medioTransporte.setPlaca(placa);
                    medioTransporte.setTipoVehiculo(tipoVehiculo);
                    medioTransporte.setMarca(marca);
                    medioTransporteDAO.agregar(medioTransporte);
                    request.getRequestDispatcher("Controlador?menu=MedioTransporte&accion=Listar").forward(request, response);

                    break;
                case "Editar":
                    codMedioTransporte = Integer.parseInt(request.getParameter("codigoTransporte"));
                    MedioTransporte me = medioTransporteDAO.listarMedioTransporte(codMedioTransporte);
                    request.setAttribute("medioTransporte", me);
                    request.getRequestDispatcher("Controlador?menu=MedioTransporte&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String placaT = request.getParameter("txtPlaca");
                    String tipoVehiculoT = request.getParameter("txtTipoVehiculo");
                    String marcaT = request.getParameter("txtMarca");
                    medioTransporte.setPlaca(placaT);
                    medioTransporte.setTipoVehiculo(tipoVehiculoT);
                    medioTransporte.setMarca(marcaT);
                    medioTransporte.setCodigoTransporte(codMedioTransporte);
                    medioTransporteDAO.editar(medioTransporte);
                    request.getRequestDispatcher("Controlador?menu=MedioTransporte&accion=Listar").forward(request, response);

                    break;
                case "Eliminar":
                    codMedioTransporte = Integer.parseInt(request.getParameter("codigoTransporte"));
                    medioTransporteDAO.eliminar(codMedioTransporte);
                    request.getRequestDispatcher("Controlador?menu=MedioTransporte&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("MedioTransporte.jsp").forward(request, response);

        } else if (menu.equals("Proveedor")) {
            switch (accion) {
                case "Listar":
                    List listaProveedor = proveedorDao.listar();
                    request.setAttribute("proveedores", listaProveedor);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombreProveedor");
                    String direccion = request.getParameter("txtDireccionProveedor");
                    String telefono = request.getParameter("txtTelefonoProveedor");
                    proveedor.setNombreProveedor(nombre);
                    proveedor.setDireccionProveedor(direccion);
                    proveedor.setTelefonoProveedor(telefono);
                    proveedorDao.agregar(proveedor);
                    request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
                    Proveedor p = proveedorDao.listarProveedor(codProveedor);
                    request.setAttribute("proveedor", p);
                    request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nombrePR = request.getParameter("txtNombreProveedor");
                    String direccionPR = request.getParameter("txtDireccionProveedor");
                    String telefonoPR = request.getParameter("txtTelefonoProveedor");
                    proveedor.setNombreProveedor(nombrePR);
                    proveedor.setDireccionProveedor(direccionPR);
                    proveedor.setTelefonoProveedor(telefonoPR);
                    proveedor.setCodigoProveedor(codProveedor);
                    proveedorDao.editar(proveedor);
                    request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codProveedor = Integer.parseInt(request.getParameter("codigoProveedor"));
                    proveedorDao.eliminar(codProveedor);
                    request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Proveedor.jsp").forward(request, response);

        } else if (menu.equals("Empresa")) {
            switch (accion) {
                case "Listar":
                    List listaEmpresa = empresaDao.listar();
                    request.setAttribute("empresas", listaEmpresa);
                    break;
                case "Agregar":
                    String nombre = request.getParameter("txtNombreEmpresa");
                    String telefono = request.getParameter("txtTelefonoEmpresa");
                    String direccion = request.getParameter("txtDireccionEmpresa");
                    String estado = request.getParameter("txtEstadoEmpresa");

                    empresa.setNombreEmpresa(nombre);
                    empresa.setTelefonoEmpresa(telefono);
                    empresa.setDireccionEmpresa(direccion);
                    empresa.setEstadoEmpresa(estado);
                    empresaDao.agregar(empresa);
                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    codEmpresa = Integer.parseInt(request.getParameter("codigoEmpresa"));
                    Empresa e = empresaDao.listarCodigoEmpresa(codEmpresa);
                    request.setAttribute("empresa", e);
                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    System.out.println("yvcgfrtd");
                    break;
                case "Actualizar":
                    String nombreEmp = request.getParameter("txtNombreEmpresa");
                    String telefonoEmp = request.getParameter("txtTelefonoEmpresa");
                    String direccionEmp = request.getParameter("txtDireccionEmpresa");
                    String estadoEmp = request.getParameter("txtEstadoEmpresa");
                    empresa.setNombreEmpresa(nombreEmp);
                    empresa.setTelefonoEmpresa(telefonoEmp);
                    empresa.setDireccionEmpresa(direccionEmp);
                    empresa.setEstadoEmpresa(estadoEmp);
                    empresa.setCodigoEmpresa(codEmpresa);
                    empresaDao.editarEmpresa(empresa);
                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);

                    break;
                case "Eliminar":
                    codEmpresa = Integer.parseInt(request.getParameter("codigoEmpresa"));
                    empresaDao.eliminarEmpresa(codEmpresa);
                    request.getRequestDispatcher("Controlador?menu=Empresa&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("Empresa.jsp").forward(request, response);

        } else if (menu.equals("EmpleadoServicio")) {
            switch (accion) {
                case "Listar":
                    List listaEmpleados_has_Servicios = empleados_has_serviciosDAO.listar();
                    request.setAttribute("empleadoServicios", listaEmpleados_has_Servicios);

                    List listaEmpleado = empleadoDAO.listar();
                    request.setAttribute("empleados", listaEmpleado);

                    List listaServicio = servicioDAO.listar();
                    request.setAttribute("servicios", listaServicio);
                    break;
                case "Agregar":
                    String costoServicio = request.getParameter("txtCostoServicio");
                    int codigoEmpleado = Integer.parseInt(request.getParameter("cmbCodigoEmpleado"));
                    int codigoServicio = Integer.parseInt(request.getParameter("cmbCodigoServicio"));
                    try {
                        empleados_has_servicios.setCostoServicio(Double.parseDouble(costoServicio));
                        empleados_has_servicios.setCodigoEmpleado(codigoEmpleado);
                        empleados_has_servicios.setCodigoServicio(codigoServicio);
                        empleados_has_serviciosDAO.agregar(empleados_has_servicios);
                        request.getRequestDispatcher("Controlador?menu=EmpleadoServicio&accion=Listar").forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Editar":
                    codEmpleadoServicio = Integer.parseInt(request.getParameter("codigoEmpleados_has_servicios"));
                    Empleados_has_Servicios es = empleados_has_serviciosDAO.listarEmpleados_has_Servicios(codEmpleadoServicio);
                    request.setAttribute("empleadoServ", es);
                    request.getRequestDispatcher("Controlador?menu=EmpleadoServicio&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String costoServicioD = request.getParameter("txtCostoServicio");
                    try {
                        empleados_has_servicios.setCostoServicio(Double.parseDouble(costoServicioD));
                        empleados_has_servicios.setCodigoEmpleados_has_servicios(codEmpleadoServicio);
                        empleados_has_serviciosDAO.editar(empleados_has_servicios);
                        request.getRequestDispatcher("Controlador?menu=EmpleadoServicio&accion=Listar").forward(request, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Eliminar":
                    codEmpleadoServicio = Integer.parseInt(request.getParameter("codigoEmpleados_has_servicios"));
                    empleados_has_serviciosDAO.eliminar(codEmpleadoServicio);
                    request.getRequestDispatcher("Controlador?menu=EmpleadoServicio&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("EmpleadoServicio.jsp").forward(request, response);
        } else if (menu.equals("TipoServicio")) {
            switch (accion) {
                case "Listar":
                    List listaTipoServicio = tipoServicioDAO.listar();
                    request.setAttribute("tipoServicios", listaTipoServicio);
                    break;
                case "Agregar":
                    String tipoServ = request.getParameter("txtTipoServicio");
                    String descripcion = request.getParameter("txtDescripcion");
                    String precioTipoServicio = request.getParameter("txtPrecioTipoServicio");
                    try {
                        tipoServicio.setTipoServicio(tipoServ);
                        tipoServicio.setDescripcion(descripcion);
                        double prec = Double.parseDouble(precioTipoServicio);
                        tipoServicio.setPrecioTipoServicio(prec);
                        tipoServicioDAO.agregar(tipoServicio);
                        request.getRequestDispatcher("Controlador?menu=TipoServicio&accion=Listar").forward(request, response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Editar":
                    codTipoServicio = Integer.parseInt(request.getParameter("codigoTipoServicio"));
                    TipoServicio ts = tipoServicioDAO.listarCodigoTipoServicio(codTipoServicio);
                    request.setAttribute("tipoServicio", ts);
                    request.getRequestDispatcher("Controlador?menu=TipoServicio&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String tipoServA = request.getParameter("txtTipoServicio");
                    String descripcionA = request.getParameter("txtDescripcion");
                    String precioTipoServicioA = request.getParameter("txtPrecioTipoServicio");
                    tipoServicio.setTipoServicio(tipoServA);
                    tipoServicio.setDescripcion(descripcionA);
                    double prec = Double.parseDouble(precioTipoServicioA);
                    tipoServicio.setPrecioTipoServicio(prec);
                    tipoServicio.setCodigoTipoServicio(codTipoServicio);
                    tipoServicioDAO.editar(tipoServicio);
                    request.getRequestDispatcher("Controlador?menu=TipoServicio&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    codTipoServicio = Integer.parseInt(request.getParameter("codigoTipoServicio"));
                    tipoServicioDAO.eliminar(codTipoServicio);
                    request.getRequestDispatcher("Controlador?menu=TipoServicio&accion=Listar").forward(request, response);
                    break;
            }
            request.getRequestDispatcher("TipoServicio.jsp").forward(request, response);

        } else if (menu.equals("Venta")) {
            switch (accion) {
                case "Buscar":
                    int nombre = Integer.parseInt(request.getParameter("txtEmpresa"));
                    empresa.setCodigoEmpresa(nombre);
                    empresa = empresaDao.buscar(nombre);
                    request.setAttribute("empresa", empresa);
                    request.setAttribute("numeroSerie", numeroSerie);

                    break;
                case "BuscarServicio":
                    int id = Integer.parseInt(request.getParameter("txtCodigoServicio"));
                    tipoServicio = tipoServicioDAO.listarId(id);
                    request.setAttribute("tipoServicio", tipoServicio);
                    request.setAttribute("lista", lista);
                    request.setAttribute("empresa", empresa);
                    request.setAttribute("totalPagar", totalPagar);
                    request.setAttribute("numeroSerie", numeroSerie);

                    break;
                case "Agregar":
                    venta = new Venta();
                    totalPagar = 0.0;
                    item = item + 1;
                    codigoTipoServicio = tipoServicio.getCodigoTipoServicio();
                    descripcion = request.getParameter("txtNombreServicio");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    venta = new Venta();
                    venta.setItem(item);
                    venta.setCodigoTipoServicio(codigoTipoServicio);
                    venta.setDescripcion(descripcion);
                    venta.setPrecio(precio);
                    lista.add(venta);
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getPrecio();
                    }
                    request.setAttribute("totalPagar", totalPagar);
                    request.setAttribute("empresa", empresa);
                    request.setAttribute("numeroSerie", numeroSerie);

                    request.setAttribute("lista", lista);
                    break;

                case "GenerarVenta":
                    venta = new Venta();
                    venta.setCodigoEmpresa(empresa.getCodigoEmpresa());
                    venta.setNumeroSerie(numeroSerie);
                    venta.setPrecio(totalPagar);
                    venta.setEstado("1");
                    ventaDao.guardarVenta(venta);
                    request.setAttribute("numeroSerie", numeroSerie);
                    for (int i = 0; i < lista.size(); i++) {
                        venta = new Venta();
                        venta.setCodigoVenta(Integer.parseInt(ventaDao.idVenta()));
                        venta.setCodigoEmpresa(lista.get(i).getCodigoEmpresa());
                        venta.setCodigoTipoServicio(lista.get(i).getCodigoTipoServicio());
                        venta.setPrecio(lista.get(i).getPrecio());
                        ventaDao.guardarDetalleVentas(venta);
                    }
                    lista.clear();
                    item = 0;
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);

                    break;

                case "Cancelar":
                    lista.clear();
                    item = 0;
                    request.setAttribute("numeroSerie", numeroSerie);
                    request.setAttribute("empresa", empresa);

                    break;

                default:
                    numeroSerie = ventaDao.GenerarSerie();
                    if (numeroSerie == null) {
                        numeroSerie = "00000001";
                        request.setAttribute("numeroSerie", numeroSerie);
                    } else {
                        int incrementar = Integer.parseInt(numeroSerie);
                        numeroSerie = ventaDao.numeroSerie(incrementar);
                        request.setAttribute("numeroSerie", numeroSerie);
                    }
            }

            request.getRequestDispatcher("Venta.jsp").forward(request, response);

        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
