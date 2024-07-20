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
import org.ricardocolindres.bean.Servicios;
import org.ricardocolindres.bean.ServiciosHasPlatos;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class ServiciosPlatosController implements Initializable{
    
    private Principal escenarioPrincipal;
    private enum operaciones{GUARDAR, ELIMINAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<ServiciosHasPlatos> listaServiciosHasPlatos;
    private ObservableList<Servicios> listaServicios;
    private ObservableList<Platos> listaPlatos;
    
    @FXML private TextField txtCodigoServicioPlato;
    @FXML private ComboBox cmbCodigoPlato;
    @FXML private ComboBox cmbCodigoServicio;
    @FXML private TableView tblServiciosPlatos;
    @FXML private TableColumn colCodigoServiciosPlatos;
    @FXML private TableColumn colCodigoPlato;
    @FXML private TableColumn colCodigoServicio;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    
    
    @Override
    public void initialize (URL location, ResourceBundle resources){
        cargarDatos();
        cmbCodigoPlato.setItems(getPlatos());
        cmbCodigoServicio.setItems(getServicio());
        desactivarControles();  
        btnEliminar.setDisable(true);
    }
    
    
    public void cargarDatos(){
        tblServiciosPlatos.setItems(getServiciosPlatos());
        colCodigoServiciosPlatos.setCellValueFactory(new PropertyValueFactory<ServiciosHasPlatos, Integer>("Servicios_codigoServicio"));
        colCodigoPlato.setCellValueFactory(new PropertyValueFactory<ServiciosHasPlatos, Integer>("codigoPlato"));
        colCodigoServicio.setCellValueFactory(new PropertyValueFactory<ServiciosHasPlatos, Integer>("codigoServicio"));    
    }
    
    public void seleccionarElemento(){
        if (tblServiciosPlatos.getSelectionModel().getSelectedItem() != null){
            txtCodigoServicioPlato.setText(String.valueOf(((ServiciosHasPlatos)tblServiciosPlatos.getSelectionModel().getSelectedItem()).getServicios_codigoServicio()));
            cmbCodigoPlato.getSelectionModel().select(buscarPlato(((ServiciosHasPlatos)tblServiciosPlatos.getSelectionModel().getSelectedItem()).getCodigoPlato()));
            cmbCodigoServicio.getSelectionModel().select(buscarServicio(((ServiciosHasPlatos)tblServiciosPlatos.getSelectionModel().getSelectedItem()).getCodigoServicio()));
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
    
    
    public ObservableList<ServiciosHasPlatos> getServiciosPlatos(){
        ArrayList<ServiciosHasPlatos> lista = new ArrayList<ServiciosHasPlatos>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarServiciosPlatos()");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
            lista.add(new ServiciosHasPlatos(resultado.getInt("Servicios_codigoServicio"),
                                                resultado.getInt("codigoPlato"),
                                                resultado.getInt("codigoServicio")));
        }
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaServiciosHasPlatos = FXCollections.observableArrayList(lista);
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
        } catch (Exception e){
            e.printStackTrace();
        }
        return listaServicios = FXCollections.observableArrayList(lista);
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
        String codigo = txtCodigoServicioPlato.getText();
        codigo = codigo.replaceAll(" ", "");
        if(codigo.isEmpty() || cmbCodigoPlato.getSelectionModel().isEmpty() || cmbCodigoServicio.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se pueden ingresar datos en blanco");
        }
        ServiciosHasPlatos registro = new ServiciosHasPlatos();
        
        registro.setServicios_codigoServicio(Integer.parseInt(txtCodigoServicioPlato.getText()));
        registro.setCodigoPlato(((Platos)cmbCodigoPlato.getSelectionModel().getSelectedItem()).getCodigoPlato());
        registro.setCodigoServicio(((Servicios)cmbCodigoServicio.getSelectionModel().getSelectedItem()).getCodigoServicio());
        
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_AgregarServicioPlato(?, ?, ?)");
            procedimiento.setInt(1, registro.getServicios_codigoServicio());
            procedimiento.setInt(2, registro.getCodigoPlato());
            procedimiento.setInt(3, registro.getCodigoServicio());
            procedimiento.execute();
            listaServiciosHasPlatos.add(registro);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminar(){
        switch (tipoDeOperacion) {
        case GUARDAR:
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
    
    public void reporte(){
        
    }
    
    
    public void desactivarControles(){
        txtCodigoServicioPlato.setEditable(false);
        cmbCodigoPlato.setDisable(true);
        cmbCodigoServicio.setDisable(true);
        btnEliminar.setDisable(false);
    }
    
    public void activarControles(){
        txtCodigoServicioPlato.setEditable(true);
        cmbCodigoPlato.setDisable(false);
        cmbCodigoServicio.setDisable(false);
        btnEliminar.setDisable(false);
    }
    
    public void limpiarControles(){
        txtCodigoServicioPlato.clear();
        cmbCodigoPlato.setValue(null);
        cmbCodigoServicio.setValue(null);
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
