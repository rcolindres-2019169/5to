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
import org.ricardocolindres.bean.Platos;
import org.ricardocolindres.bean.TipoPlato;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class PlatosController implements Initializable{
    
    private Principal escenarioPrincipal; 
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO} ;
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Platos>listaPlatos;
    private ObservableList<TipoPlato>listaTipoPlato;
    
    @FXML private TextField txtCodigoPlato;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtNombrePlato;
    @FXML private TextField txtDescripcionPlato;
    @FXML private TextField txtPrecioPlato;
    @FXML private ComboBox cmbCodigoTipoPlato;
    @FXML private TableView tblPlatos;
    @FXML private TableColumn colCodigoPlato;
    @FXML private TableColumn colCantidad;
    @FXML private TableColumn colNombrePlato;
    @FXML private TableColumn colDescripcionPlato;
    @FXML private TableColumn colPrecioPlato;
    @FXML private TableColumn colCodigoTipoPlato;
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
        cmbCodigoTipoPlato.setItems(getTipoPlato());
        desactivarControles();
        btnReporte.setDisable(true);
    }
    
    public void cargarDatos(){
        tblPlatos.setItems(getPlatos());
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("codigoPlato"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("cantidad"));
        colNombrePlato.setCellValueFactory(new PropertyValueFactory<Platos, String>("nombrePlato"));
        colDescripcionPlato.setCellValueFactory(new PropertyValueFactory<Platos, String>("descripcionPlato"));
        colPrecioPlato.setCellValueFactory(new PropertyValueFactory<Platos, Double>("precioPlato"));
        colCodigoTipoPlato.setCellValueFactory(new PropertyValueFactory<Platos, Integer>("codigoTipoPlato"));
        
        
    }
    
    public void seleccionarElemento(){
        if (tblPlatos.getSelectionModel().getSelectedItem()!=null){
            txtCodigoPlato.setText(String.valueOf(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getCodigoPlato()));
            txtCantidad.setText(String.valueOf(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getCantidad()));
            txtNombrePlato.setText(String.valueOf(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getNombrePlato()));
            txtDescripcionPlato.setText(String.valueOf(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getDescripcionPlato()));
            txtPrecioPlato.setText(Double.toString(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getPrecioPlato()));
            cmbCodigoTipoPlato.getSelectionModel().select(buscarTipoPlato(((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getCodigoTipoPlato()));
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
        }
    }
    
    public TipoPlato buscarTipoPlato(int codigoTipoPlato){
        TipoPlato resultado = null;
        try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarTipoPlato(?)");
           procedimiento.setInt(1, codigoTipoPlato);
           ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){                
                resultado = new TipoPlato(registro.getInt("codigoTipoPlato"),
                                         registro.getString("descripcionTipo"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public ObservableList<Platos> getPlatos(){
        ArrayList<Platos> lista = new ArrayList<Platos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarPlatos()");
            ResultSet resultado = procedimiento.executeQuery(); 
            while (resultado.next()){
                lista.add(new Platos(resultado.getInt("codigoPlato"),
                                    resultado.getInt("cantidad"),
                                    resultado.getString("nombrePlato"),
                                    resultado.getString("descripcionPlato"),
                                    resultado.getDouble("precioPlato"),
                                    resultado.getInt("codigoTipoPlato")));
                
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaPlatos = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<TipoPlato> getTipoPlato(){
        ArrayList<TipoPlato> lista = new ArrayList<TipoPlato>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarTipoPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            
            while (resultado.next()){                
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
        
        String cantidad  = txtCantidad.getText();
        String nombrePlato = txtNombrePlato.getText();
        String descripcionPlato = txtDescripcionPlato.getText();
        String precio = txtPrecioPlato.getText();
        
        cantidad = cantidad.replaceAll(" ", "");
        nombrePlato = nombrePlato.replaceAll(" ", "");
        descripcionPlato = descripcionPlato.replaceAll(" ", "");
        precio = precio.replaceAll(" ", "");
        
        if (cantidad.isEmpty() || nombrePlato.isEmpty() || descripcionPlato.isEmpty() || precio.isEmpty() || cmbCodigoTipoPlato.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco este campo");
        }else{
        
        Platos registro = new Platos();
        
        registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        registro.setNombrePlato(txtNombrePlato.getText());
        registro.setDescripcionPlato(txtDescripcionPlato.getText());
        registro.setPrecioPlato(Double.valueOf(txtPrecioPlato.getText()));
        registro.setCodigoTipoPlato(((TipoPlato)cmbCodigoTipoPlato.getSelectionModel().getSelectedItem()).getCodigoTipoPlato());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarPlato(?, ?, ?, ?, ?)");
            procedimiento.setInt(1, registro.getCantidad());
            procedimiento.setString(2, registro.getNombrePlato());
            procedimiento.setString(3, registro.getDescripcionPlato());
            procedimiento.setDouble(4, registro.getPrecioPlato());
            procedimiento.setInt(5, registro.getCodigoTipoPlato());
            procedimiento.execute();
            listaPlatos.add(registro);
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
            if (tblPlatos.getSelectionModel().getSelectedItem()!=null){
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Plato",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                    try{
                       PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarPlato(?)");
                       procedimiento.setInt(1, ((Platos)tblPlatos.getSelectionModel().getSelectedItem()).getCodigoPlato());
                       procedimiento.execute();
                       listaPlatos.remove(tblPlatos.getSelectionModel().getSelectedIndex());    
                       limpiarControles();
                       tblPlatos.getSelectionModel().clearSelection();
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
                if (tblPlatos.getSelectionModel().getSelectedItem() !=null){
                    btnReporte.setDisable(false);
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    imgEditar.setImage(new Image("/org/ricardocolindres/image/actualizar.png"));
                    imgReporte.setImage(new Image("/org/ricardocolindres/image/cancel.png"));
                    activarControles();
                    cmbCodigoTipoPlato.setDisable(true);
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
        
        String cantidad  = txtCantidad.getText();
        String nombrePlato = txtNombrePlato.getText();
        String descripcionPlato = txtDescripcionPlato.getText();
        String precio = txtPrecioPlato.getText();
        
        cantidad = cantidad.replaceAll(" ", "");
        nombrePlato = nombrePlato.replaceAll(" ", "");
        descripcionPlato = descripcionPlato.replaceAll(" ", "");
        precio = precio.replaceAll(" ", "");
        
        if (cantidad.isEmpty() || nombrePlato.isEmpty() || descripcionPlato.isEmpty() || precio.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar en blanco este campo");
        }else{
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarPlato(?, ?, ?, ?, ?, ?)");
            Platos registro = (Platos)tblPlatos.getSelectionModel().getSelectedItem();
            registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
            registro.setNombrePlato(txtNombrePlato.getText());
            registro.setDescripcionPlato(txtDescripcionPlato.getText());
            registro.setPrecioPlato(Double.valueOf(txtPrecioPlato.getText()));
            registro.setCodigoTipoPlato(((TipoPlato)cmbCodigoTipoPlato.getSelectionModel().getSelectedItem()).getCodigoTipoPlato());
            
            procedimiento.setInt(1, registro.getCodigoPlato());
            procedimiento.setInt(2, registro.getCantidad());
            procedimiento.setString(3, registro.getNombrePlato());
            procedimiento.setString(4, registro.getDescripcionPlato());
            procedimiento.setDouble(5, registro.getPrecioPlato());
            procedimiento.setInt(6, registro.getCodigoTipoPlato());
            procedimiento.execute();
            tblPlatos.getSelectionModel().clearSelection();
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    public void reporte(){
        switch(tipoDeOperacion){
        case NINGUNO:
          if (tblPlatos.getSelectionModel().getSelectedItem() != null) {
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
        txtCodigoPlato.setEditable(false);
        txtCantidad.setEditable(false);
        txtNombrePlato.setEditable(false);
        txtDescripcionPlato.setEditable(false);
        txtPrecioPlato.setEditable(false);
        cmbCodigoTipoPlato.setDisable(true);
        btnReporte.setDisable(true);
    }
    
    public void activarControles(){
        txtCodigoPlato.setEditable(false);
        txtCantidad.setEditable(true);
        txtNombrePlato.setEditable(true);
        txtDescripcionPlato.setEditable(true);
        txtPrecioPlato.setEditable(true);
        cmbCodigoTipoPlato.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoPlato.clear();
        txtCantidad.clear();
        txtNombrePlato.clear();
        txtDescripcionPlato.clear();
        txtPrecioPlato.clear();
        cmbCodigoTipoPlato.setValue(null);
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
    
    public void TipoPlatoPlatos(){
        escenarioPrincipal.ventanaTipoPlato();
    }
    
}   
