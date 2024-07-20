package org.ricardocolindres.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
import org.ricardocolindres.bean.Empresa;
import org.ricardocolindres.bean.Servicios;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class ServiciosController implements Initializable{
    
    private Principal escenarioPrincipal;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Servicios> listaServicios;
    private ObservableList<Empresa> listaEmpresa;
    private DatePicker fecha;
    Tooltip t = new Tooltip("Usar formato de HH:MM");
    
    @FXML private TextField txtCodigoServicio;
    @FXML private TextField txtTipoServicio;
    @FXML private TextField txtLugarServicio;
    @FXML private TextField txtHoraServicio;
    @FXML private TextField txtTelefonoContacto;
    @FXML private GridPane grpFecha;
    @FXML private ComboBox cmbCodigoEmpresa;
    @FXML private  TableView tblServicios;
    @FXML private TableColumn colCodigoServicio;
    @FXML private TableColumn colFechaServicio;
    @FXML private TableColumn colTipoServicio;
    @FXML private TableColumn colHoraServicio;
    @FXML private TableColumn colLugarServicio;
    @FXML private TableColumn colTelefonoContacto;
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
        fecha.getStylesheets().add("/org/ricardocolindres/resource/TonysKinal.css");
        grpFecha.add(fecha, 3, 0);
        cmbCodigoEmpresa.setItems(getEmpresa());
        Tooltip.install(txtHoraServicio, t);
        desactivarControles();
        btnReporte.setDisable(true);
        
    }
    
    public void cargarDatos(){
        tblServicios.setItems(getServicio());
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<Servicios, Integer>("codigoServicio"));
        colFechaServicio.setCellValueFactory(new PropertyValueFactory<Servicios, Date>("fechaServicio"));
        colTipoServicio.setCellValueFactory(new PropertyValueFactory<Servicios, String>("tipoServicio"));
        colHoraServicio.setCellValueFactory(new PropertyValueFactory<Servicios, String>("horaServicio"));
        colLugarServicio.setCellValueFactory(new PropertyValueFactory<Servicios, String>("lugarServicio"));
        colTelefonoContacto.setCellValueFactory(new PropertyValueFactory<Servicios, String>("telefonoContacto"));
        colCodigoEmpresa.setCellValueFactory(new PropertyValueFactory<Servicios, Integer>("codigoEmpresa"));
  
    }
    
    public void seleccionarElemento(){
        if (tblServicios.getSelectionModel().getSelectedItem()!=null){
            txtCodigoServicio.setText(String.valueOf(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getCodigoServicio()));
            fecha.selectedDateProperty().set(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getFechaServicio());
            txtTipoServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getTipoServicio());
            txtHoraServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getHoraServicio());
            txtLugarServicio.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getLugarServicio());
            txtTelefonoContacto.setText(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getTelefonoContacto());
            cmbCodigoEmpresa.getSelectionModel().select(buscarEmpresa(((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
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
    
    
    public ObservableList<Servicios> getServicio(){
        ArrayList<Servicios> lista = new ArrayList<Servicios>();
        try{
            PreparedStatement procedimiento  = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServicios()");
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
        } catch (Exception e){
            e.printStackTrace();
        }
            return listaServicios = FXCollections.observableArrayList(lista);
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
                //limpiarControles();
                //desactivarControles();
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
        String tipo = txtTipoServicio.getText();
        String  hora = txtHoraServicio.getText();
        String lugar = txtLugarServicio.getText();
        String telefono = txtTelefonoContacto.getText();
        
        tipo = tipo.replaceAll(" ", "");
        hora = hora.replaceAll(" ", "");
        lugar = lugar.replaceAll(" ", "");
        telefono = telefono.replaceAll(" ", "");
        
        if (tipo.isEmpty() || hora.isEmpty() || lugar.isEmpty() ||  telefono.isEmpty() ||  cmbCodigoEmpresa.getSelectionModel().isEmpty() || fecha.getSelectedDate() == null){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
            
        }else{
        
        Servicios registro = new Servicios();
        
        registro.setFechaServicio(fecha.getSelectedDate());
        registro.setTipoServicio(txtTipoServicio.getText());
        registro.setHoraServicio(txtHoraServicio.getText());
        registro.setLugarServicio(txtLugarServicio.getText());
        registro.setTelefonoContacto(txtTelefonoContacto.getText());
        registro.setCodigoEmpresa(((Empresa)cmbCodigoEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarServicio(?, ?, ?, ?, ?, ?)");
            procedimiento.setDate(1, new java.sql.Date(registro.getFechaServicio().getTime()));
            procedimiento.setString(2, registro.getTipoServicio());
            procedimiento.setString(3, registro.getHoraServicio());
            procedimiento.setString(4, registro.getLugarServicio());
            procedimiento.setString(5, registro.getTelefonoContacto());
            procedimiento.setInt(6, registro.getCodigoEmpresa());
            procedimiento.execute();
            listaServicios.add(registro);
        } catch (Exception e){
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
            if (tblServicios.getSelectionModel().getSelectedItem() !=null){
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Servicio",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                    try{
                       PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarServicio(?)");
                       procedimiento.setInt(1, ((Servicios)tblServicios.getSelectionModel().getSelectedItem()).getCodigoServicio());
                       procedimiento.execute();
                       listaServicios.remove(tblServicios.getSelectionModel().getSelectedIndex());
                       limpiarControles();
                       tblServicios.getSelectionModel().clearSelection();
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
                if (tblServicios.getSelectionModel().getSelectedItem() !=null){
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnReporte.setDisable(false);
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
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarServicio(?, ?, ?, ?, ?, ?, ?)");
            Servicios registro = (Servicios)tblServicios.getSelectionModel().getSelectedItem(); 
        
            registro.setFechaServicio(fecha.getSelectedDate());
            registro.setTipoServicio(txtTipoServicio.getText());
            registro.setHoraServicio(txtHoraServicio.getText());
            registro.setLugarServicio(txtLugarServicio.getText());
            registro.setTelefonoContacto(txtTelefonoContacto.getText());
            registro.setCodigoEmpresa(((Empresa)cmbCodigoEmpresa.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
            
            procedimiento.setInt(1, registro.getCodigoServicio());
            procedimiento.setDate(2, new java.sql.Date(registro.getFechaServicio().getTime()));
            procedimiento.setString(3, registro.getTipoServicio());
            procedimiento.setString(4, registro.getHoraServicio());
            procedimiento.setString(5, registro.getLugarServicio());
            procedimiento.setString(6, registro.getTelefonoContacto());
            procedimiento.setInt(7, registro.getCodigoEmpresa());
            procedimiento.execute();
        } catch (Exception e){
            e.printStackTrace();
          }
    }
    
    
    public void reporte(){
      switch(tipoDeOperacion){
        case NINGUNO:
          if (tblServicios.getSelectionModel().getSelectedItem() != null) {
            btnNuevo.setDisable(true);
            btnEliminar.setDisable(true);
            btnEditar.setText("Actualizar");
            btnReporte.setText("Cancelar");
            imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
            imgReporte.setImage(new Image("/org/ricardocolindres/image/cancelar.png"));
            activarControles();
            tipoDeOperacion = operaciones.ACTUALIZAR;
            
          }else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
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
    
    public void limpiarControles(){
        txtCodigoServicio.clear();
        fecha.setSelectedDate(null);
        txtTipoServicio.clear();
        txtLugarServicio.clear();
        txtHoraServicio.clear();
        txtTelefonoContacto.clear();
        cmbCodigoEmpresa.setValue(null);
    }

    public void desactivarControles(){
        txtCodigoServicio.setEditable(false);
        fecha.setDisable(true);
        txtTipoServicio.setEditable(false);
        txtLugarServicio.setEditable(false);
        txtHoraServicio.setEditable(false);
        txtTelefonoContacto.setEditable(false);
        cmbCodigoEmpresa.setDisable(true);
        btnReporte.setDisable(true);
    }

    public void activarControles(){
        txtCodigoServicio.setEditable(false);
        fecha.setDisable(false);
        txtTipoServicio.setEditable(true);
        txtLugarServicio.setEditable(true);
        txtTelefonoContacto.setEditable(true);
        txtHoraServicio.setEditable(true);
        cmbCodigoEmpresa.setDisable(false);
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
    
    public void empresaServicio(){
        escenarioPrincipal.ventanaEmpresa();
    }
}
