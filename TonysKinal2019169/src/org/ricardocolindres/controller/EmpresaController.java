package org.ricardocolindres.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Empresa;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;
import org.ricardocolindres.report.GenerarReporte;


public class EmpresaController implements Initializable {
    
    private Principal escenarioPrincipal;
    
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO} ;
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Empresa> listaEmpresa;
    
    @FXML private TextField txtCodigoEmpresa;
    @FXML private TextField txtNombreEmpresa;
    @FXML private TextField txtDireccionEmpresa;
    @FXML private TextField txtTelefonoEmpresa;
    @FXML private TableView tblEmpresas;
    @FXML private TableColumn colCodigoEmpresa;
    @FXML private TableColumn colNombreEmpresa;
    @FXML private TableColumn colDireccionEmpresa;
    @FXML private TableColumn colTelefonoEmpresa;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;
    
    
    @Override
    public void initialize (URL location, ResourceBundle resources){
        cargarDatos();
    }
   
    
    public void cargarDatos(){
        tblEmpresas.setItems(getEmpresa());
        colCodigoEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, Integer>("codigoEmpresa"));
        colNombreEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nombreEmpresa"));
        colDireccionEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("direccion"));
        colTelefonoEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("telefono"));
      
    }
    
    
    public ObservableList<Empresa> getEmpresa(){
        ArrayList<Empresa> lista = new ArrayList<Empresa>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpresas();");
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
    
    
    public void seleccionarElemento(){
      if (tblEmpresas.getSelectionModel().getSelectedItem() != null) {
          txtCodigoEmpresa.setText(String.valueOf(((Empresa)tblEmpresas.getSelectionModel().getSelectedItem()).getCodigoEmpresa()));
          txtNombreEmpresa.setText(((Empresa)tblEmpresas.getSelectionModel().getSelectedItem()).getNombreEmpresa());
          txtDireccionEmpresa.setText(((Empresa)tblEmpresas.getSelectionModel().getSelectedItem()).getDireccion());
          txtTelefonoEmpresa.setText(((Empresa)tblEmpresas.getSelectionModel().getSelectedItem()).getTelefono());
      }else{
        JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
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
            if (tblEmpresas.getSelectionModel().getSelectedItem() != null) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Empresa",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION) {
                    try {
                      PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarEmpresa(?)");
                      procedimiento.setInt(1, ((Empresa)tblEmpresas.getSelectionModel().getSelectedItem()).getCodigoEmpresa());
                      procedimiento.execute();
                      listaEmpresa.remove(tblEmpresas.getSelectionModel().getSelectedIndex());
                      limpiarControles();
                      tblEmpresas.getSelectionModel().clearSelection();
                  }catch (Exception e) {
                      e.printStackTrace();
                  }
                }
                }else{
              JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            }
    }
}
    
    
    public void reporte(){
      switch(tipoDeOperacion){
        case NINGUNO:
                imprimirReporte();
                
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
    
    
    public void editar(){
      switch (tipoDeOperacion) {
        case NINGUNO:
          if(tblEmpresas.getSelectionModel().getSelectedItem() != null){
                btnNuevo.setDisable(true);
                btnEliminar.setDisable(true);
                btnEditar.setText("Actualizar");
                btnReporte.setText("Cancelar");
                imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
                imgReporte.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
                activarControles();
                tipoDeOperacion = operaciones.ACTUALIZAR;
          }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
          }
          break;
          
        case ACTUALIZAR:
          actualizar();
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
    
    
    public void actualizar(){
        
        String nombre = txtNombreEmpresa.getText();
        String direccion = txtDireccionEmpresa.getText();
        String telefono = txtTelefonoEmpresa.getText();
        
        
        nombre = nombre.replaceAll(" ", "");
        direccion = direccion.replaceAll(" ", "");
        telefono = telefono.replaceAll(" ", "");
        
        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }
        else if (txtNombreEmpresa.getText().isEmpty() || txtDireccionEmpresa.getText().isEmpty() || txtTelefonoEmpresa.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar espacios en blanco.");
        }else{
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarEmpresa(?, ?, ?, ?)");
            Empresa registro = (Empresa)tblEmpresas.getSelectionModel().getSelectedItem();
            registro.setNombreEmpresa(txtNombreEmpresa.getText());
            registro.setDireccion(txtDireccionEmpresa.getText());
            registro.setTelefono(txtTelefonoEmpresa.getText());
            
            procedimiento.setInt(1, registro.getCodigoEmpresa());
            procedimiento.setString(2, registro.getNombreEmpresa());
            procedimiento.setString(3, registro.getDireccion());
            procedimiento.setString(4, registro.getTelefono());
            procedimiento.execute();
            limpiarControles();
            tblEmpresas.getSelectionModel().clearSelection();
          
        }catch (Exception e) {
          e.printStackTrace();
      }
        }
    }
    
    
    public void guardar(){
        Empresa registro = new Empresa();
        
        String nombre = txtNombreEmpresa.getText();
        String direccion = txtDireccionEmpresa.getText();
        String telefono = txtTelefonoEmpresa.getText();
        
        nombre = nombre.replaceAll(" ", "");
        direccion = direccion.replaceAll(" ", "");
        telefono = telefono.replaceAll(" ", "");
        
        registro.setNombreEmpresa(txtNombreEmpresa.getText());
        registro.setDireccion(txtDireccionEmpresa.getText());
        registro.setTelefono(txtTelefonoEmpresa.getText());
        
        
        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }
        else if (txtNombreEmpresa.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "No se puede dejar en blanco el nombre de la empresa.");
          
        }else if (txtDireccionEmpresa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco la dirección de la empresa.");
        
        }else if (txtTelefonoEmpresa.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "No se puede dejar casillas en blanco");
      
        }else if (txtNombreEmpresa.getText().isEmpty() && txtDireccionEmpresa.getText().isEmpty() && txtTelefonoEmpresa.getText().length()==0) {
            JOptionPane.showMessageDialog(null, "No se puede dejar casillas en blanco");
            
        }else if (txtTelefonoEmpresa.getText().length() >8) {
          JOptionPane.showMessageDialog(null, "Solo se puede ingresar 8 números sin espacios.");

        }else{
          try {
              PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarEmpresa (?, ?, ?)");
              procedimiento.setString(1, registro.getNombreEmpresa());
              procedimiento.setString(2,registro.getDireccion());
              procedimiento.setString(3, registro.getTelefono());
              procedimiento.execute();
              listaEmpresa.add(registro);
              
      } catch (Exception e) {
          e.printStackTrace();
      }
        }
    }
    
    
    
    public void imprimirReporte(){
      Map parametros = new HashMap();
      URL imagenMenu = this.getClass().getResource("/org/ricardocolindres/image/Menu.png");
      URL imagenLogo = this.getClass().getResource("/org/ricardocolindres/image/logo.png");
      parametros.put("codigoEmpresa", null);
      parametros.put("imagenMenu", EmpresaController.class.getResource("/org/ricardocolindres/image/Menu.png"));
      parametros.put("imagenLogo", EmpresaController.class.getResource("/org/ricardocolindres/image/logo.png"));
      GenerarReporte.mostrarReporte("ReporteEmpresas.jasper", "Reporte de Empresas", parametros);
    }
    
    public void desactivarControles(){
        txtCodigoEmpresa.setEditable(false);
        txtNombreEmpresa.setEditable(false);
        txtDireccionEmpresa.setEditable(false);
        txtTelefonoEmpresa.setEditable(false);
    }
    
    
    
    public void activarControles(){
        txtCodigoEmpresa.setEditable(false);
        txtNombreEmpresa.setEditable(true);
        txtDireccionEmpresa.setEditable(true);
        txtTelefonoEmpresa.setEditable(true);
    }
    
    
    
    public void limpiarControles(){
        txtCodigoEmpresa.clear();
        txtNombreEmpresa.clear();
        txtDireccionEmpresa.clear();
        txtTelefonoEmpresa.clear();
  
    }
    
    

     public void ventanaPresupuestos(){
        escenarioPrincipal.ventanaPresupuestos();
    }
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
      escenarioPrincipal.menuPrincipal();
    }
    
    public void ventanaServicio(){
        escenarioPrincipal.ventanaServicios();
    }
    
    
}
