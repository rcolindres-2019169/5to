package org.ricardocolindres.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Empleado;
import org.ricardocolindres.bean.Servicios;
import org.ricardocolindres.bean.ServiciosHasEmpleados;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class ServiciosEmpleadosController implements Initializable{
    
    private Principal escenarioPrincipal;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<ServiciosHasEmpleados> listaServicioEmpleado;
    private ObservableList<Servicios> listaServicios;
    private ObservableList<Empleado> listaEmpleados;
    private DatePicker fecha;
    Tooltip t = new Tooltip("Usar formato de HH:MM");
    
    @FXML private TextField txtCodigoServicioEmpleado;
    @FXML private ComboBox cmbCodigoServicio;
    @FXML private ComboBox cmbCodigoEmpleado;
    @FXML private TextField txtHoraEvento;
    @FXML private TextField txtLugarEvento;
    @FXML private TableView tblServiciosEmpleados;
    @FXML private TableColumn colCodigoServicioEmpleado;
    @FXML private TableColumn colCodigoServicio;
    @FXML private TableColumn colCodigoEmpleado;
    @FXML private TableColumn colFechaEvento;
    @FXML private TableColumn colHoraEvento;
    @FXML private TableColumn colLugarEvento;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private GridPane grpFecha;
        
    @Override
    public void initialize (URL location, ResourceBundle resources){
        cargarDatos();
        fecha = new DatePicker(Locale.ENGLISH);
        fecha.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        fecha.getCalendarView().todayButtonTextProperty().set("Today");
        fecha.getCalendarView().setShowWeeks(false);
        fecha.getStylesheets().add("/org/ricardocolindres/resource/TonysKinal.css");
        grpFecha.add(fecha, 1, 3);
        cmbCodigoServicio.setItems(getServicio());
        cmbCodigoEmpleado.setItems(getEmpleado());
        desactivarControles();
        Tooltip.install(txtHoraEvento, t);
        desactivarControles();
        btnEliminar.setDisable(true);
    }
    
    public void cargarDatos(){
        tblServiciosEmpleados.setItems(getServicioEmpleado());
        colCodigoServicioEmpleado.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Integer>("Servicios_codigoServicio"));
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Integer>("codigoServicio"));
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Integer>("codigoEmpleado"));
        colFechaEvento.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Date>("fechaEvento"));
        colHoraEvento.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, Time>("horaEvento"));
        colLugarEvento.setCellValueFactory(new PropertyValueFactory<ServiciosHasEmpleados, String>("lugarEvento"));
        
        
    }
    
    public void seleccionarElemento(){
        if(tblServiciosEmpleados.getSelectionModel().getSelectedItem() !=null){
            Tooltip.install(txtHoraEvento, t);
            txtCodigoServicioEmpleado.setText(String.valueOf(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
            cmbCodigoServicio.getSelectionModel().select(buscarServicio(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getCodigoServicio()));
            cmbCodigoEmpleado.getSelectionModel().select(buscarEmpleado(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
            fecha.selectedDateProperty().set(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getFechaEvento());
            txtHoraEvento.setText(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getHoraEvento());
            txtLugarEvento.setText(((ServiciosHasEmpleados)tblServiciosEmpleados.getSelectionModel().getSelectedItem()).getLugarEvento());
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
        }
    }
    
    public Servicios buscarServicio(int codigoServicio){
        Servicios resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarServicio(?)");
            procedimiento.setInt(1, codigoServicio);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Servicios(registro.getInt("codigoServicio"),
                                            registro.getDate("fechaServicio"),
                                            registro.getString("tipoServicio"),
                                            registro.getString("horaServicio"),
                                            registro.getString("lugarServicio"),
                                            registro.getString("telefonoContacto"),
                                            registro.getInt("codigoEmpresa"));
            }
        } catch (Exception e){
            e.printStackTrace();    
        }
        return resultado;
    }
    
    public Empleado buscarEmpleado(int codigoEmpleado){
        Empleado resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarEmpleado(?)");
            procedimiento.setInt(1, codigoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while(registro.next()){
                resultado = new Empleado(registro.getInt("codigoEmpleado"),
                                            registro.getInt("numeroEmpleado"),
                                            registro.getString("apellidosEmpleado"),
                                            registro.getString("nombresEmpleado"),
                                            registro.getString("direccionEmpleado"),
                                            registro.getString("telefonoContacto"),
                                            registro.getString("gradoCocinero"),
                                            registro.getInt("codigoTipoEmpleado"));
            }  
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<ServiciosHasEmpleados>getServicioEmpleado(){
        ArrayList<ServiciosHasEmpleados> lista = new ArrayList<ServiciosHasEmpleados>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServiciosEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new ServiciosHasEmpleados(resultado.getInt("Servicios_codigoServicio"),
                                                    resultado.getInt("codigoServicio"),
                                                    resultado.getInt("codigoEmpleado"),
                                                    resultado.getDate("fechaEvento"),
                                                    resultado.getString("horaEvento"),
                                                    resultado.getString("lugarEvento")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicioEmpleado = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Servicios> getServicio(){
        ArrayList<Servicios> lista = new ArrayList<Servicios>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServicios()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){                
                lista.add(new Servicios(resultado.getInt("codigoServicio"),
                                        resultado.getDate("fechaServicio"),
                                        resultado.getString("tipoServicio"),
                                        resultado.getString("horaServicio"),
                                        resultado.getString("lugarServicio"),
                                        resultado.getString("telefonoContacto"),
                                        resultado.getInt("codigoEmpresa")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaServicios = FXCollections.observableArrayList(lista);
    }
    
    
    public ObservableList<Empleado> getEmpleado(){
        ArrayList<Empleado> lista  = new ArrayList<Empleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Empleado(resultado.getInt("codigoEmpleado"),
                                        resultado.getInt("numeroEmpleado"),
                                        resultado.getString("apellidosEmpleado"),
                                        resultado.getString("nombresEmpleado"),
                                        resultado.getString("direccionEmpleado"),
                                        resultado.getString("telefonoContacto"),
                                        resultado.getString("gradoCocinero"),
                                        resultado.getInt("codigoTipoEmpleado")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaEmpleados = FXCollections.observableArrayList(lista);
    }
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                btnEliminar.setDisable(false);
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                imgNuevo.setImage(new Image("/org/ricardocolindres/image/guardar.png"));
                imgEliminar.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
                tipoDeOperacion = operaciones.GUARDAR;
            break;
            
            case GUARDAR:
                guardar();
                limpiarControles();
                desactivarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
                imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
            }
            
    }
     
    public void guardar(){
        String hora = txtHoraEvento.getText();
        String lugar = txtLugarEvento.getText();
        String codigo = txtCodigoServicioEmpleado.getText();
        
        hora = hora.replaceAll(" ", "");
        lugar = lugar.replaceAll(" ", "");
        codigo = codigo.replaceAll(" ", "");
        
        if (hora.isEmpty() || lugar.isEmpty() || cmbCodigoEmpleado.getSelectionModel().isEmpty() || cmbCodigoServicio.getSelectionModel().isEmpty() || codigo.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }else if (fecha.getSelectedDate()  == null){
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco este campo");
        }else{
        
        ServiciosHasEmpleados registro = new ServiciosHasEmpleados();
        
        registro.setServicios_codigoServicio(Integer.parseInt(txtCodigoServicioEmpleado.getText()));
        registro.setCodigoServicio(((Servicios)cmbCodigoServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        registro.setCodigoEmpleado(((Empleado)cmbCodigoEmpleado.getSelectionModel().getSelectedItem()).getCodigoEmpleado());
        registro.setFechaEvento(fecha.getSelectedDate());
        registro.setHoraEvento(txtHoraEvento.getText());
        registro.setLugarEvento(txtLugarEvento.getText());
        
        try {
          PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarServicioEmpleado(?, ?, ?, ?, ?, ?)");
          procedimiento.setInt(1, registro.getServicios_codigoServicio());
          procedimiento.setInt(2, registro.getCodigoServicio());
          procedimiento.setInt(3, registro.getCodigoEmpleado());
          procedimiento.setDate(4, new java.sql.Date(registro.getFechaEvento().getTime()));
          procedimiento.setString(5, registro.getHoraEvento());
          procedimiento.setString(6, registro.getLugarEvento());
          procedimiento.execute();
          listaServicioEmpleado.add(registro);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    }
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                activarControles();
                limpiarControles();
                
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
                imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
            break;
        }
    }
    
    public void activarControles(){
        txtCodigoServicioEmpleado.setEditable(true);
        txtHoraEvento.setEditable(true);
        txtLugarEvento.setEditable(true);
        cmbCodigoEmpleado.setDisable(false);
        cmbCodigoServicio.setDisable(false);
        btnEliminar.setDisable(false);
        fecha.setDisable(false);
    }
    
    public void desactivarControles(){
        txtCodigoServicioEmpleado.setEditable(false);
        txtHoraEvento.setEditable(false);
        txtLugarEvento.setEditable(false);
        cmbCodigoEmpleado.setDisable(true);
        cmbCodigoServicio.setDisable(true);
        btnEliminar.setDisable(true);
        fecha.setDisable(true);
    }
    
    public void limpiarControles(){
        txtCodigoServicioEmpleado.clear();
        cmbCodigoEmpleado.setValue(null);
        cmbCodigoServicio.setValue(null);
        txtHoraEvento.clear();
        txtLugarEvento.clear();
        fecha.setSelectedDate(null);
    }
            
            
    public Principal getEscenarioPrincipal(){
        return escenarioPrincipal;
    }
    
    public void setEscenarioPrincipal(Principal escenarioPrincipal){
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
}
