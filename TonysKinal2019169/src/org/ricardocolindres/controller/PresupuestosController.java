    package org.ricardocolindres.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Empresa;
import org.ricardocolindres.bean.Presupuestos;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;
import org.ricardocolindres.report.GenerarReporte;

public class PresupuestosController implements Initializable{
    
    private Principal escenarioPrincipal;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Presupuestos> listaPresupuestos;
    private ObservableList<Empresa> listaEmpresa;
    private DatePicker fecha;
    
    @FXML private TextField txtCodigoPresupuesto;
    @FXML private TextField  txtCantidadPresupuesto;
    @FXML private GridPane grpFecha;
    @FXML private ComboBox cmbCodigoEmpresa;
    @FXML private TableView tblPresupuestos;
    @FXML private TableColumn colCodigoPresupuesto;
    @FXML private TableColumn colFechaSolicitud;
    @FXML private TableColumn colCantidadPresupuesto;
    @FXML private TableColumn colCodigoEmpresa;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    
    
    @Override
    public void initialize (URL location, ResourceBundle resources){
        cargarDatos();
        fecha = new DatePicker(Locale.ENGLISH);
        fecha.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        fecha.getCalendarView().todayButtonTextProperty().set("Today");
        fecha.getCalendarView().setShowWeeks(false);
        fecha.getStylesheets().add("/org/ricardocolindres/resource/TonysKinal.css");
        grpFecha.add(fecha, 1, 1);
        cmbCodigoEmpresa.setItems(getEmpresa());
        
        desactivarControles();
    }
    
    
    public void cargarDatos(){
      tblPresupuestos.setItems(getPresupuesto());
      colCodigoPresupuesto.setCellValueFactory(new PropertyValueFactory<Presupuestos,Integer>("codigoPresupuesto"));
      colFechaSolicitud.setCellValueFactory(new PropertyValueFactory<Presupuestos, Date>("fechaSolicitud"));
      colCantidadPresupuesto.setCellValueFactory(new PropertyValueFactory<Presupuestos,Double>("cantidadPresupuesto"));
      colCodigoEmpresa.setCellValueFactory(new PropertyValueFactory<Presupuestos, Integer>("codigoEmpresa"));
    }
    
    public void seleccionarElemento(){
        if (tblPresupuestos.getSelectionModel().getSelectedItem() != null) {
        txtCodigoPresupuesto.setText(String.valueOf(((Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem()).getCodigoPresupuesto()));
        fecha.selectedDateProperty().set(((Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem()).getFechaSolicitud());
        txtCantidadPresupuesto.setText(String.valueOf(((Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem()).getCantidadPresupuesto()));
        cmbCodigoEmpresa.getSelectionModel().select(buscarEmpresa(((Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
      }
    }
    
    public Empresa buscarEmpresa(int codigoEmpresa){
        Empresa resultado = null;
        try {
         PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarEmpresa(?)");
         procedimiento.setInt(1, codigoEmpresa);
         ResultSet registro = procedimiento.executeQuery();
          while (registro.next()) {
            resultado = new Empresa(registro.getInt("codigoEmpresa"),
                                    registro.getString("nombreEmpresa"),
                                    registro.getString("direccion"),
                                    registro.getString("telefono"));
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      
        return resultado;
    }
    
    
    public ObservableList<Presupuestos> getPresupuesto(){
        ArrayList<Presupuestos> lista = new ArrayList<Presupuestos>();
        try {
          PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarPresupuestos()");
          ResultSet resultado = procedimiento.executeQuery();
          while(resultado.next()){
            lista.add(new Presupuestos(resultado.getInt("codigoPresupuesto"),
                                      resultado.getDate("fechaSolicitud"),
                                      resultado.getDouble("cantidadPresupuesto"),
                                      resultado.getInt("codigoEmpresa")));
          }
          
      }catch (Exception e) {
          e.printStackTrace();
      }
      
        return listaPresupuestos = FXCollections.observableArrayList(lista); 
    }
    
    public ObservableList<Empresa> getEmpresa(){
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpresas;");
            ResultSet resultado = procedimiento.executeQuery();
            
            while (resultado.next()) {            
                lista.add(new Empresa(resultado.getInt("codigoEmpresa"),
                        resultado.getString("nombreEmpresa"),
                        resultado.getString("direccion"),
                        resultado.getString("telefono")));
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
      return listaEmpresa = FXCollections.observableArrayList(lista);
    }
    
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
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
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
                imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
            }
            
    }
    
    public void guardar(){
        String cantidad = txtCantidadPresupuesto.getText();
        cantidad = cantidad.replaceAll(" ", "");
        if (cantidad.isEmpty() || cmbCodigoEmpresa.getSelectionModel().isEmpty()){
           JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
           
        }else if (fecha.getSelectedDate()  == null){
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco este campo");
  
        }else{
            Presupuestos registro = new Presupuestos();
        
            registro.setFechaSolicitud(fecha.getSelectedDate());
            registro.setCantidadPresupuesto(Double.parseDouble(txtCantidadPresupuesto.getText()));
            registro.setCodigoEmpresa(((Empresa)cmbCodigoEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
                
        try{
            PreparedStatement procedimiento  = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarPresupuesto(?, ?, ?)");
            procedimiento.setDate(1, new java.sql.Date(registro.getFechaSolicitud().getTime()));
            procedimiento.setDouble(2, registro.getCantidadPresupuesto());
            procedimiento.setInt(3, registro.getCodigoEmpresa());
            procedimiento.execute();
            listaPresupuestos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }
    
    public void eliminar(){
  
        switch (tipoDeOperacion) {
        case GUARDAR:
        limpiarControles();
          desactivarControles();
          btnNuevo.setText("Nuevo");
          btnEliminar.setText("Eliminar");
          btnEditar.setDisable(false);
          btnReporte.setDisable(false);
          imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
          imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
          tipoDeOperacion = operaciones.NINGUNO;
          break;
          
        default:
            if (tblPresupuestos.getSelectionModel().getSelectedItem() !=null){
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Presupuesto",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                    try{
                       PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarPresupuesto(?)");
                       procedimiento.setInt(1, ((Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem()).getCodigoPresupuesto());
                       procedimiento.execute();
                       listaPresupuestos.remove(tblPresupuestos.getSelectionModel().getSelectedIndex());
                       limpiarControles();
                       tblPresupuestos.getSelectionModel().clearSelection();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            }
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if (tblPresupuestos.getSelectionModel().getSelectedItem() !=null){
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
                    imgReporte.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
                    activarControles();
                    cmbCodigoEmpresa.setDisable(true);
                    tipoDeOperacion = operaciones.ACTUALIZAR;   
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
                }
                break;
            
            case ACTUALIZAR: 
                
                actualizar();
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/ricardocolindres/image/editar.png"));
                imgReporte.setImage(new Image("/org/ricardocolindres/image/reporte.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                desactivarControles();
                limpiarControles();
                cargarDatos();
                break;
        }
    }
    
    
    
    public void actualizar(){
        String cantidad = txtCantidadPresupuesto.getText();
        cantidad = cantidad.replaceAll(" ", "");
        
        
        if (cantidad.isEmpty()){
           JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }else if (fecha.getSelectedDate()  == null){
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco este campo");
           
        }else{
          
          try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarPresupuesto(?, ?, ?, ?)");
            Presupuestos registro = (Presupuestos)tblPresupuestos.getSelectionModel().getSelectedItem();
            registro.setFechaSolicitud(fecha.getSelectedDate());
            registro.setCantidadPresupuesto(Double.valueOf(txtCantidadPresupuesto.getText()));
            registro.setCodigoEmpresa(((Empresa)cmbCodigoEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
            
            procedimiento.setInt(1, registro.getCodigoPresupuesto());
            procedimiento.setDate(2, new java.sql.Date(registro.getFechaSolicitud().getTime()));
            procedimiento.setDouble(3, registro.getCantidadPresupuesto());
            procedimiento.setInt(4, registro.getCodigoEmpresa());
            procedimiento.execute();
            tblPresupuestos.getSelectionModel().clearSelection();
          } catch (Exception e){
            e.printStackTrace();
          }
        }
    }
    
    public void reporte(){
      switch(tipoDeOperacion){
        case NINGUNO:
            if (tblPresupuestos.getSelectionModel().getSelectedItem() !=null){
                imprimirReporte();
            }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            }
          
        break;
        
        
        case ACTUALIZAR:
          limpiarControles();
          desactivarControles();
          btnNuevo.setDisable(false);
          btnEliminar.setDisable(false);
          btnEditar.setText("Editar");
          btnReporte.setText("Reporte");
          imgEditar.setImage(new Image("/org/ricardocolindres/image/editar.png"));
          imgReporte.setImage(new Image("/org/ricardocolindres/image/reporte.png"));
          tipoDeOperacion = operaciones.NINGUNO;
          cargarDatos();
        break;

          
      }
    }
    
    
    public void imprimirReporte(){
        Map parametros = new HashMap();
        int codEmpresa = Integer.valueOf(((Empresa)cmbCodigoEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa()); 
        URL subReportePresupuesto = this.getClass().getResource("/org/ricardocolindres/report/");
        URL imagenMenu = this.getClass().getResource("/org/ricardocolindres/image/Menu.png");
        URL imagenLogo = this.getClass().getResource("/org/ricardocolindres/image/logo.png");
        parametros.put("codEmpresa", codEmpresa);
        parametros.put("subReportePresupuesto", subReportePresupuesto);
        parametros.put("imagenMenu", PresupuestosController.class.getResource("/org/ricardocolindres/image/Menu.png"));
        parametros.put("imagenLogo", PresupuestosController.class.getResource("/org/ricardocolindres/image/logo.png"));
        parametros.put("subReportePresupuesto", PresupuestosController.class.getResource("/org/ricardocolindres/report/"));
        GenerarReporte.mostrarReporte("ReportePresupuestos.jasper", "Reporte de Presupuesto", parametros);
    }
    
    

    public void desactivarControles(){
        txtCodigoPresupuesto.setEditable(false);
        txtCantidadPresupuesto.setEditable(false);
        fecha.setDisable(true);
        cmbCodigoEmpresa.setDisable(true);
        
    }
    
    public void activarControles(){
        txtCodigoPresupuesto.setEditable(false);
        txtCantidadPresupuesto.setEditable(true);
        cmbCodigoEmpresa.setDisable(false);
        fecha.setDisable(false);
        
    }
    
    public void limpiarControles(){
        txtCodigoPresupuesto.clear();
        txtCantidadPresupuesto.clear();
        fecha.setSelectedDate(null);
        cmbCodigoEmpresa.setValue(null);
        
        
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

    public void empresaPresupuesto(){
        escenarioPrincipal.ventanaEmpresa();
    }
    
}
//jfeonix hora para servicios
//string modelo servicio hora