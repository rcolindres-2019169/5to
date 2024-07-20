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
import org.ricardocolindres.bean.TipoPlato;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class TipoPlatoController implements Initializable{
    
    private Principal escenarioPrinipal;
    
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<TipoPlato> listaTipoPlato;
    
    @FXML private TextField txtCodigoTipoPlato;
    @FXML private TextField txtDescripcion;
    @FXML private TableView tblTiposPlatos;
    @FXML private TableColumn colCodigoTipoPlato;
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
        btnReporte.setDisable(true);
    }
    
    
    public void cargarDatos(){
        tblTiposPlatos.setItems(getTipoPlato());
        colCodigoTipoPlato.setCellValueFactory(new PropertyValueFactory<TipoPlato, Integer>("codigoTipoPlato"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoPlato, String>("descripcion"));
    }
    
    
    public ObservableList<TipoPlato>getTipoPlato(){
        ArrayList<TipoPlato> lista = new ArrayList<TipoPlato>();
        try
        {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarTipoPlatos;");
            ResultSet resultado = procedimiento.executeQuery();
            
            while(resultado.next()){
                lista.add(new TipoPlato(resultado.getInt("codigoTipoPlato"),
                        resultado.getString("descripcionTipo")));
            }
                    
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaTipoPlato = FXCollections.observableArrayList(lista);
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
      if (tblTiposPlatos.getSelectionModel().getSelectedItem() !=null) {
        txtCodigoTipoPlato.setText(String.valueOf(((TipoPlato)tblTiposPlatos.getSelectionModel().getSelectedItem()).getCodigoTipoPlato()));
        txtDescripcion.setText(((TipoPlato)tblTiposPlatos.getSelectionModel().getSelectedItem()).getDescripcion());
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
                if (tblTiposPlatos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Tipo Plato",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        try
                        {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarTipoPlato(?)");
                            procedimiento.setInt(1, ((TipoPlato)tblTiposPlatos.getSelectionModel().getSelectedItem()).getCodigoTipoPlato());
                            procedimiento.execute();
                            listaTipoPlato.remove(tblTiposPlatos.getSelectionModel().getSelectedIndex());
                            limpiarControles();
                            tblTiposPlatos.getSelectionModel().clearSelection();
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
                if (tblTiposPlatos.getSelectionModel().getSelectedItem()!= null){
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
          if (tblTiposPlatos.getSelectionModel().getSelectedItem() != null){
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
            JOptionPane.showMessageDialog(null, "No se puede dejar casillas en blanco.");
        }else{
        try{
           PreparedStatement procedimiento =  Conexion.getInstance().getConexion().prepareCall("call sp_EditarTipoPlato(?,?)");
           TipoPlato registro = (TipoPlato)tblTiposPlatos.getSelectionModel().getSelectedItem();
           registro.setDescripcion(txtDescripcion.getText());
           
           procedimiento.setInt(1, registro.getCodigoTipoPlato());
           procedimiento.setString(2, registro.getDescripcion());
           procedimiento.execute();
           limpiarControles();
           tblTiposPlatos.getSelectionModel().clearSelection();
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void guardar(){
        TipoPlato registro = new TipoPlato();
        registro.setDescripcion(txtDescripcion.getText());
        
        String descripcion = txtDescripcion.getText();
        descripcion = descripcion.replaceAll(" ", "");
        
          if (descripcion.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }else if (txtDescripcion.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar casillas en blanco.");
        }else{
        try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarTipoPlato(?)");
           procedimiento.setString(1, registro.getDescripcion());
           procedimiento.execute();
           listaTipoPlato.add(registro);
           
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void desactivarControles(){
        txtCodigoTipoPlato.setEditable(false);
        txtDescripcion.setEditable(false);
        btnReporte.setDisable(true);
    }
    
    
    public void activarControles(){
        txtCodigoTipoPlato.setEditable(false);
        txtDescripcion.setEditable(true);
    }
    
    
    public void limpiarControles(){
        txtCodigoTipoPlato.clear();
        txtDescripcion.clear();
    }
    
    
    public void ventanaPlatos(){
        escenarioPrinipal.ventanaPlatos();
    }
    
    public Principal getEscenarioPrincipal(){
        return escenarioPrinipal;
    }
    
    public void setEscenarioPrincipal(Principal escenarioPrincipal){
        this.escenarioPrinipal = escenarioPrincipal;
    }
    
    
    public void menuPrincipal(){
        escenarioPrinipal.menuPrincipal();
    }
}
