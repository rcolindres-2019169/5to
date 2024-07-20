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
import org.ricardocolindres.bean.Productos;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class ProductosController implements Initializable{
    
    private Principal escenarioPrincipal;


    
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Productos> listaProductos;
    
    @FXML private TextField txtCodigoProducto;
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtCantidad;
    @FXML private TableView tblProductos;
    @FXML private TableColumn colCodigoProducto;
    @FXML private TableColumn colNombreProducto;
    @FXML private TableColumn colCantidad;
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
        tblProductos.setItems(getProductos());
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("codigoProducto"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidad"));
    }
    
    
    public ObservableList<Productos>getProductos(){
        ArrayList<Productos> lista = new ArrayList<Productos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarProductos;");
            ResultSet resultado = procedimiento.executeQuery();
            
            while (resultado.next()){
                lista.add(new Productos(resultado.getInt("codigoProducto"),
                            resultado.getString("nombreProducto"),
                            resultado.getInt("cantidad")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaProductos = FXCollections.observableArrayList(lista);
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
        if (tblProductos.getSelectionModel().getSelectedItem() != null) {
        txtCodigoProducto.setText(String.valueOf(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto()));
        txtNombreProducto.setText(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getNombreProducto());
        txtCantidad.setText(String.valueOf(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getCantidad()));
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
            if (tblProductos.getSelectionModel().getSelectedItem() !=null){
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar Producto",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (respuesta == JOptionPane.YES_OPTION){
                    try{
                        PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EliminarProducto(?)");
                        procedimiento.setInt(1, ((Productos)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto());
                        procedimiento.execute();
                        listaProductos.remove(tblProductos.getSelectionModel().getSelectedItem());
                        limpiarControles();
                        tblProductos.getSelectionModel().clearSelection();
                    } catch (Exception e){
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
          if (tblProductos.getSelectionModel().getSelectedItem() != null){
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
    
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if (tblProductos.getSelectionModel().getSelectedItem() != null){
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
   
    
    public void actualizar(){
        String nombre = txtNombreProducto.getText();
        String cantidad = txtCantidad.getText();
        
        nombre = nombre.replaceAll(" ", "");
        cantidad = cantidad.replaceAll(" ", "");
        
        if (nombre.isEmpty() || cantidad.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }else if (txtNombreProducto.getText().isEmpty() || txtCantidad.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe de llenar las casillas");
        }else{    
        try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_EditarProducto(?, ?,?)");
           Productos registro = (Productos)tblProductos.getSelectionModel().getSelectedItem();
           registro.setNombreProducto(txtNombreProducto.getText());
           registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
           
           procedimiento.setInt(1, registro.getCodigoProducto());
           procedimiento.setString(2, registro.getNombreProducto());
           procedimiento.setInt(3, registro.getCantidad());
           procedimiento.execute();
           limpiarControles();
           tblProductos.getSelectionModel().clearSelection();
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void guardar(){
        Productos registro = new Productos();
        
        String nombre = txtNombreProducto.getText();
        String cantidad = txtCantidad.getText();
        
        nombre = nombre.replaceAll(" ", "");
        cantidad = cantidad.replaceAll(" ", "");
        
        if (nombre.isEmpty() || cantidad.isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
            
        }else if (txtNombreProducto.getText().isEmpty() || txtCantidad.getText().length()==0){
            JOptionPane.showMessageDialog(null, "Debe de llenar las casillas");
        
        }else{    
            registro.setNombreProducto(txtNombreProducto.getText());
            registro.setCantidad(Integer.parseInt(txtCantidad.getText()));
        try{
            
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarProducto(?, ?)");
            procedimiento.setString(1, registro.getNombreProducto());
            procedimiento.setInt(2, registro.getCantidad());
            procedimiento.execute();
            listaProductos.add(registro);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        }
    }
    
    
    public void desactivarControles(){
        txtCodigoProducto.setEditable(false);
        txtNombreProducto.setEditable(false);
        txtCantidad.setEditable(false);
        btnReporte.setDisable(true);
    }
    
    
    public void activarControles(){
        txtCodigoProducto.setEditable(false);
        txtNombreProducto.setEditable(true);
        txtCantidad.setEditable(true);
    }
    
    
    public void limpiarControles(){
        txtCodigoProducto.clear();
        txtNombreProducto.clear();
        txtCantidad.clear();
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
