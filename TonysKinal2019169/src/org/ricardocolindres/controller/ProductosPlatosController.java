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
import org.ricardocolindres.bean.Productos;
import org.ricardocolindres.bean.ProductosHasPlatos;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class ProductosPlatosController implements Initializable{
    
    private Principal escenarioPrincipal;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<ProductosHasPlatos> listaProductosHasPlatos;
    private ObservableList<Productos> listaProductos;
    private ObservableList<Platos> listaPlatos;
    
    @FXML private TextField txtCodigoProductoPlato;
    @FXML private ComboBox cmbCodigoPlato;
    @FXML private ComboBox cmbCodigoProducto;
    @FXML private TableView tblProductosPlatos;
    @FXML private TableColumn colCodigoProductosPlatos;
    @FXML private TableColumn colCodigoPlato;
    @FXML private TableColumn colCodigoProducto;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    
    @Override
    public void initialize (URL location, ResourceBundle resources){
        cargarDatos();
        cmbCodigoPlato.setItems(getPlatos());
        cmbCodigoProducto.setItems(getProducto());
        desactivarControles();
        btnEliminar.setDisable(true);
    }
    
    public void cargarDatos(){
        tblProductosPlatos.setItems(getProductosPlatos());
        colCodigoProductosPlatos.setCellValueFactory(new PropertyValueFactory<ProductosHasPlatos, Integer>("Productos_codigoProducto"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<ProductosHasPlatos, Integer>("codigoPlato"));
        colCodigoProducto.setCellValueFactory(new PropertyValueFactory<ProductosHasPlatos, Integer>("codigoProducto"));
    }
    
    public void seleccionarElemento(){
        if (tblProductosPlatos.getSelectionModel().getSelectedItem() !=null){
            txtCodigoProductoPlato.setText(String.valueOf(((ProductosHasPlatos)tblProductosPlatos.getSelectionModel().getSelectedItem()).getProductos_CodigoProducto()));
            cmbCodigoPlato.getSelectionModel().select(buscarPlato(((ProductosHasPlatos)tblProductosPlatos.getSelectionModel().getSelectedItem()).getCodigoPlato()));
            cmbCodigoProducto.getSelectionModel().select(buscarProducto(((ProductosHasPlatos)tblProductosPlatos.getSelectionModel().getSelectedItem()).getCodigoProducto()));
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un campo con datos");
        }
    }
    
    
    public Platos buscarPlato(int codigoPlato){
        Platos resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarPlato(?)");
            procedimiento.setInt(1, codigoPlato);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Platos(registro.getInt("codigoPlato"),
                                        registro.getInt("cantidad"),
                                        registro.getString("nombrePlato"),
                                        registro.getString("descripcionPlato"),
                                        registro.getDouble("precioPlato"),
                                        registro.getInt("codigoTipoPlato"));
                
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public Productos buscarProducto(int codigoProducto){
        Productos resultado = null;
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_BuscarProducto(?)");
            procedimiento.setInt(1, codigoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Productos(registro.getInt("codigoProducto"),
                                            registro.getString("nombreProducto"),
                                            registro.getInt("cantidad"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
            return resultado;
    }
    
    public ObservableList<ProductosHasPlatos> getProductosPlatos(){
        ArrayList<ProductosHasPlatos> lista = new ArrayList<ProductosHasPlatos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarProductosPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()){                
                lista.add(new ProductosHasPlatos(resultado.getInt("Productos_codigoProducto"),
                                                    resultado.getInt("codigoPlato"),    
                                                    resultado.getInt("codigoProducto")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return  listaProductosHasPlatos = FXCollections.observableArrayList(lista);
    }
    
    
    public ObservableList<Platos> getPlatos(){
        ArrayList<Platos> lista = new ArrayList<Platos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
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
    
    
    public ObservableList<Productos> getProducto(){
        ArrayList<Productos> lista = new ArrayList<Productos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarProductos()");
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
                limpiarControles();
                
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
        String codigo = txtCodigoProductoPlato.getText();
        codigo = codigo.replaceAll(" ", "");
        if (codigo.isEmpty() || cmbCodigoPlato.getSelectionModel().isEmpty() || cmbCodigoProducto.getSelectionModel().isEmpty() ){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }
        ProductosHasPlatos registro = new ProductosHasPlatos();
        
        registro.setProductos_CodigoProducto(Integer.parseInt(txtCodigoProductoPlato.getText()));
        registro.setCodigoPlato(((Platos)cmbCodigoPlato.getSelectionModel().getSelectedItem()).getCodigoPlato());
        registro.setCodigoProducto(((Productos)cmbCodigoProducto.getSelectionModel().getSelectedItem()).getCodigoProducto());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarProductoPlato(?, ?, ?)");
            procedimiento.setInt(1, registro.getProductos_CodigoProducto());
            procedimiento.setInt(2, registro.getCodigoPlato());
            procedimiento.setInt(3, registro.getCodigoProducto());
            procedimiento.execute();
            listaProductosHasPlatos.add(registro);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void eliminar(){
        switch (tipoDeOperacion) {
        case GUARDAR:
            limpiarControles();
            activarControles();
            btnNuevo.setText("Nuevo");
            btnEliminar.setText("Eliminar");
            imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
            imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
            tipoDeOperacion = operaciones.NINGUNO;
            cargarDatos();
            break;       
            }
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
    
    public void desactivarControles(){
        txtCodigoProductoPlato.setEditable(false);
        cmbCodigoPlato.setDisable(true);
        cmbCodigoProducto.setDisable(true);
        btnEliminar.setDisable(false);
    }
    
    public void activarControles(){
        txtCodigoProductoPlato.setEditable(true);
        cmbCodigoPlato.setDisable(false);
        cmbCodigoProducto.setDisable(false);
        btnEliminar.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoProductoPlato.clear();
        cmbCodigoPlato.setValue(null);
        cmbCodigoProducto.setValue(null);
    }
}
