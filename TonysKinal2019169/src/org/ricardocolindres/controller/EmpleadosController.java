package org.ricardocolindres.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Empleado;
import org.ricardocolindres.bean.TipoEmpleado;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class EmpleadosController implements Initializable {
    
    private Principal escenarioPrincipal;
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO} ;
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Empleado>listaEmpleados;
    private ObservableList<TipoEmpleado>listaTipoEmpleado;
    
    @FXML private TextField txtCodigoEmpleado;
    @FXML private TextField txtNumeroEmpleado;
    @FXML private TextField txtApellidosEmpleado;
    @FXML private TextField txtNombresEmpleado;
    @FXML private TextField txtDireccionEmpleado;
    @FXML private TextField txtTelefonoContacto;
    @FXML private TextField txtGradoCocinero;
    @FXML private ComboBox cmbCodigoTipoEmpleado;
    @FXML private TableView tblEmpleados;
    @FXML private TableColumn colCodigoEmpleado;
    @FXML private TableColumn colNumeroEmpleado;
    @FXML private TableColumn colApellidosEmpleado;
    @FXML private TableColumn colNombresEmpleado;
    @FXML private TableColumn colDireccionEmpleado;
    @FXML private TableColumn colTelefonoContacto;
    @FXML private TableColumn colGradoCocinero;
    @FXML private TableColumn colCodigoTipoEmpleado;
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
        cmbCodigoTipoEmpleado.setItems(getTipoEmpleado());
        desactivarControles();
        btnReporte.setDisable(true);
    }
    
    public void cargarDatos(){
        tblEmpleados.setItems(getEmpleados());
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("codigoEmpleado"));
        colNumeroEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("numeroEmpleado"));
        colApellidosEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellidosEmpleado"));
        colNombresEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombresEmpleado"));
        colDireccionEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("direccionEmpleado"));
        colTelefonoContacto.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefonoContacto"));
        colGradoCocinero.setCellValueFactory(new PropertyValueFactory<Empleado, String>("gradoCocinero"));
        colCodigoTipoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("codigoTipoEmpleado"));
    }
    
    public void seleccionarElemento(){
        if (tblEmpleados.getSelectionModel().getSelectedItem() !=null){
            txtCodigoEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
            txtNumeroEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getNumeroEmpleado()));
            txtApellidosEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getApellidosEmpleado()));
            txtNombresEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getNombresEmpleado()));
            txtDireccionEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getDireccionEmpleado()));
            txtTelefonoContacto.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getTelefonoContacto()));
            txtGradoCocinero.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getGradoCocinero()));
            cmbCodigoTipoEmpleado.getSelectionModel().select(buscarTipoEmpleado(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado()));
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
            
        }
    }
    
    public TipoEmpleado buscarTipoEmpleado(int codigoTipoEmpleado){
        TipoEmpleado resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarTipoEmpleado(?)");
            procedimiento.setInt(1, codigoTipoEmpleado);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){                
                resultado = new TipoEmpleado(registro.getInt("codigoTipoEmpleado"),
                                            registro.getString("descripcion"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
            return resultado;
    }
    
    public ObservableList<Empleado>getEmpleados(){
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){                
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
    
    public ObservableList<TipoEmpleado> getTipoEmpleado(){
        ArrayList<TipoEmpleado> lista = new ArrayList<TipoEmpleado>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarTipoEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            
            while (resultado.next()){                
                lista.add(new TipoEmpleado(resultado.getInt("codigoTipoEmpleado"),
                                            resultado.getString("descripcion")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaTipoEmpleado = FXCollections.observableArrayList(lista);
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
        
        
        String apellido = txtApellidosEmpleado.getText();
        String nombre = txtNombresEmpleado.getText();
        String direccion = txtDireccionEmpleado.getText();
        String telefono = txtTelefonoContacto.getText();
        
        apellido = apellido.replaceAll(" ", "");
        nombre = nombre.replaceAll(" ", "");
        direccion = direccion.replaceAll("", " ");
        telefono = telefono.replaceAll(" ", "");
        
        if (apellido.isEmpty() || nombre.isEmpty() || direccion.isEmpty()|| telefono.isEmpty() || cmbCodigoTipoEmpleado.getSelectionModel().isEmpty() ){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
            
        }else if(txtNombresEmpleado.getText().length()==0 || txtNombresEmpleado.getText().length()>8){
            JOptionPane.showMessageDialog(null, "Solo se puede ingresar 8 números sin espacios o no se puede dejar vacío");
        }else{
        
        Empleado registro = new Empleado();
        
        registro.setNumeroEmpleado(Integer.parseInt(txtNumeroEmpleado.getText()));
        registro.setApellidosEmpleado(txtApellidosEmpleado.getText());
        registro.setNombresEmpleado(txtNombresEmpleado.getText());
        registro.setDireccionEmpleado(txtDireccionEmpleado.getText());
        registro.setTelefonoContacto(txtTelefonoContacto.getText());
        registro.setGradoCocinero(txtGradoCocinero.getText());
        registro.setCodigoTipoEmpleado(((TipoEmpleado)cmbCodigoTipoEmpleado.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarEmpleado(?, ?, ?, ?, ?, ?, ?)");
            procedimiento.setInt(1, registro.getNumeroEmpleado());
            procedimiento.setString(2, registro.getApellidosEmpleado());
            procedimiento.setString(3, registro.getNombresEmpleado());
            procedimiento.setString(4, registro.getDireccionEmpleado());
            procedimiento.setString(5, registro.getTelefonoContacto());
            procedimiento.setString(6, registro.getGradoCocinero());
            procedimiento.setInt(7, registro.getCodigoTipoEmpleado());
            procedimiento.execute();
            listaEmpleados.add(registro);
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
            if (tblEmpleados.getSelectionModel().getSelectedItem()!=null){
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Empleado",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                      try{
                        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarEmpleado(?)");
                        procedimiento.setInt(1, ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado());
                        procedimiento.execute();
                        listaEmpleados.remove(tblEmpleados.getSelectionModel().getSelectedIndex());
                        limpiarControles();
                        tblEmpleados.getSelectionModel().clearSelection();
                      }catch (Exception e){
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
                if (tblEmpleados.getSelectionModel().getSelectedItem() !=null){
                    
                    btnReporte.setDisable(false);
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
                    imgReporte.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
                    activarControles();
                    cmbCodigoTipoEmpleado.setDisable(true);
                    tipoDeOperacion = operaciones.ACTUALIZAR;   
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
        String apellido = txtApellidosEmpleado.getText();
        String nombre = txtNombresEmpleado.getText();
        String direccion = txtDireccionEmpleado.getText();
        String telefono = txtTelefonoContacto.getText();
        
        apellido = apellido.replaceAll(" ", "");
        nombre = nombre.replaceAll(" ", "");
        direccion = direccion.replaceAll("", " ");
        telefono = telefono.replaceAll(" ", "");
        
        if (apellido.isEmpty() || nombre.isEmpty() || direccion.isEmpty()|| telefono.isEmpty() ){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
            
        }else if(txtNombresEmpleado.getText().length()==0 || txtNombresEmpleado.getText().length()>8){
            JOptionPane.showMessageDialog(null, "Solo se puede ingresar 8 números sin espacios o no se puede dejar vacío");
        }else{
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarEmpleado(?, ?, ?, ?, ?, ?, ?, ?)");
            Empleado registro = (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
            registro.setNumeroEmpleado(Integer.parseInt(txtNumeroEmpleado.getText()));
            registro.setApellidosEmpleado(txtApellidosEmpleado.getText());
            registro.setNombresEmpleado(txtNombresEmpleado.getText());
            registro.setDireccionEmpleado(txtDireccionEmpleado.getText());
            registro.setTelefonoContacto(txtTelefonoContacto.getText());
            registro.setGradoCocinero(txtGradoCocinero.getText());
            registro.setCodigoTipoEmpleado(((TipoEmpleado)cmbCodigoTipoEmpleado.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado());
            
            procedimiento.setInt(1, registro.getCodigoEmpleado());
            procedimiento.setInt(2, registro.getNumeroEmpleado());
            procedimiento.setString(3, registro.getApellidosEmpleado());
            procedimiento.setString(4, registro.getNombresEmpleado());
            procedimiento.setString(5, registro.getDireccionEmpleado());
            procedimiento.setString(6, registro.getTelefonoContacto());
            procedimiento.setString(7, registro.getGradoCocinero());
            procedimiento.setInt(8, registro.getCodigoTipoEmpleado());
            procedimiento.execute();
            tblEmpleados.getSelectionModel().clearSelection();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    public void reporte(){
      switch(tipoDeOperacion){
        case NINGUNO:
          if (tblEmpleados.getSelectionModel().getSelectedItem() != null) {
            btnNuevo.setDisable(true);
            btnEliminar.setDisable(true);
            btnEditar.setText("Actualizar");
            btnReporte.setText("Cancelar");
            imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
            imgReporte.setImage(new Image("/org/ricardocolindres/image/cancelar.png"));
            activarControles();
            tipoDeOperacion = operaciones.ACTUALIZAR;

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
    
    public void desactivarControles(){
        txtCodigoEmpleado.setEditable(false);
        txtNumeroEmpleado.setEditable(false);
        txtApellidosEmpleado.setEditable(false);
        txtNombresEmpleado.setEditable(false);
        txtDireccionEmpleado.setEditable(false);
        txtTelefonoContacto.setEditable(false);
        txtGradoCocinero.setEditable(false);
        cmbCodigoTipoEmpleado.setDisable(true);
    }
    
    public void activarControles(){
        txtCodigoEmpleado.setEditable(false);
        txtNumeroEmpleado.setEditable(true);
        txtApellidosEmpleado.setEditable(true);
        txtNombresEmpleado.setEditable(true);
        txtDireccionEmpleado.setEditable(true);
        txtTelefonoContacto.setEditable(true);
        txtGradoCocinero.setEditable(true);
        cmbCodigoTipoEmpleado.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoEmpleado.setEditable(false);
        txtNumeroEmpleado.setEditable(true);
        txtApellidosEmpleado.setEditable(true);
        txtNombresEmpleado.setEditable(true);
        txtDireccionEmpleado.setEditable(true);
        txtTelefonoContacto.setEditable(true);
        txtGradoCocinero.setEditable(true);
        cmbCodigoTipoEmpleado.setDisable(false);
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
    
    public void tipoEmpleado(){
        escenarioPrincipal.ventanaTipoEmpleado();
    }
    
}
