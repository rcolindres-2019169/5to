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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.TipoEmpleado;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class TipoEmpleadoController implements Initializable{
    
    private Principal escenarioPrincipal;   
    
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<TipoEmpleado> listaTipoEmpleado;
    
    @FXML private TextField txtCodigoTipoEmpleado;
        @FXML private TextField txtDescripcion;
        @FXML private TableView tblTiposEmpleados;
        @FXML private TableColumn colCodigoTipoEmpleado;
        @FXML private TableColumn colDescripcion;
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
        desactivarControles();
        btnReporte.setDisable(false);
    }
    
    
    public void cargarDatos(){
        tblTiposEmpleados.setItems(getTipoEmpleado());
        colCodigoTipoEmpleado.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, Integer>("codigoTipoEmpleado"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoEmpleado, String>("descripcion"));
    }
    
    
    public ObservableList<TipoEmpleado>getTipoEmpleado(){
        ArrayList<TipoEmpleado> lista = new ArrayList<TipoEmpleado>();
        try
        {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarTipoEmpleados;");
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
        
        switch (tipoDeOperacion){
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
      if (tblTiposEmpleados.getSelectionModel().getSelectedItem() != null) {
        txtCodigoTipoEmpleado.setText(String.valueOf(((TipoEmpleado)tblTiposEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado()));
        txtDescripcion.setText(((TipoEmpleado)tblTiposEmpleados.getSelectionModel().getSelectedItem()).getDescripcion());
      }else{
        JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
      }
        
    }
    
    
    public void eliminar(){
        switch(tipoDeOperacion){
            
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
                if (tblTiposEmpleados.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Tipo Empleado",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION ){
                        try{
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarTipoEmpleado(?)");
                            procedimiento.setInt(1, ((TipoEmpleado)tblTiposEmpleados.getSelectionModel().getSelectedItem()).getCodigoTipoEmpleado());
                            procedimiento.execute();
                            listaTipoEmpleado.remove(tblTiposEmpleados.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                            tblTiposEmpleados.getSelectionModel().clearSelection();
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
        switch (tipoDeOperacion){
            case NINGUNO:
                if(tblTiposEmpleados.getSelectionModel().getSelectedItem() !=null){
                btnNuevo.setDisable(true);
                btnEliminar.setDisable(true);
                btnReporte.setDisable(false);
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
    
    
    public void reporte(){
      switch(tipoDeOperacion){
        case NINGUNO:
          if (tblTiposEmpleados.getSelectionModel().getSelectedItem() != null){
                btnNuevo.setDisable(true);
                btnEliminar.setDisable(true);
                btnEditar.setText("Actualizar");
                btnReporte.setText("Cancelar");
                imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
                imgReporte.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
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
    
    
    public void actualizar(){
        
        String descripcion = txtDescripcion.getText();
        descripcion = descripcion.replaceAll(" ", "");
        
        if (descripcion.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }else if (txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar espacios en blanco.");
        }else{
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarTipoEmpleado(?,?)");
            TipoEmpleado registro = (TipoEmpleado)tblTiposEmpleados.getSelectionModel().getSelectedItem();
            registro.setDescripcion(txtDescripcion.getText());
            
            procedimiento.setInt(1, registro.getCodigoTipoEmpleado());
            procedimiento.setString(2, registro.getDescripcion());
            procedimiento.execute();
            limpiarControles();
            tblTiposEmpleados.getSelectionModel().clearSelection();
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void guardar(){
        TipoEmpleado  registro = new TipoEmpleado();
        
        String descripcion = txtDescripcion.getText();
        descripcion = descripcion.replaceAll(" ", "");
        
        registro.setDescripcion(txtDescripcion.getText());
        
        if (descripcion.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        
        }else if (txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar espacios en blanco.");
        }else{
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarTipoEmpleado (?)");
            procedimiento.setString(1, registro.getDescripcion());
            procedimiento.execute();
            listaTipoEmpleado.add(registro);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void desactivarControles(){
        txtCodigoTipoEmpleado.setEditable(false);
        txtDescripcion.setEditable(false);
        btnReporte.setDisable(true);
    }
    
    
    public void activarControles(){
        txtCodigoTipoEmpleado.setEditable(false);
        txtDescripcion.setEditable(true);
    }
    
    
    public void limpiarControles(){
        txtCodigoTipoEmpleado.clear();
        txtDescripcion.clear();
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
    
    public void ventanaEmpleado(){
        escenarioPrincipal.ventanaEmpleados();
    }
    
}
