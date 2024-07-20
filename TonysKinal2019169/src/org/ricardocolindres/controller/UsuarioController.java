
package org.ricardocolindres.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Usuario;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class UsuarioController implements  Initializable{
        private Principal escenarioPrincipal;
        private enum operaciones{NUEVO, GUARDAR, NINGUNO};
        private operaciones tipoDeOperacion = operaciones.NINGUNO;
        
        @FXML private TextField txtCodigoUsuario;
        @FXML private TextField txtNombreUsuario;
        @FXML private TextField txtApellidoUsuario;
        @FXML private TextField txtUsuario;
        @FXML private TextField txtPassword;
        @FXML private Button btnNuevo;
        @FXML private Button btnEliminar;
        @FXML private ImageView imgNuevo;
        @FXML private ImageView imgEliminar;

  
    @Override
    public void initialize (URL location, ResourceBundle resources){
        
    }
    
    public void nuevo(){
      switch(tipoDeOperacion){
        case NINGUNO:
            limpiarControles();
            activarControles();
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
            login();
            break;
      }
    }
    
    public void guardar(){
        
       String nombre = txtNombreUsuario.getText();
       String apellido = txtApellidoUsuario.getText();
       String usuario = txtUsuario.getText();
       String password = txtPassword.getText();
       
       nombre = nombre.replaceAll(" ", "");
       apellido = apellido.replaceAll(" ", "");
       usuario = usuario.replaceAll(" ", "");
       password = password.replaceAll(" ", "");
       
        if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se puede dejar espacios en blanco.");
        }else if (txtNombreUsuario.getText().isEmpty() || txtApellidoUsuario.getText().isEmpty() || txtUsuario.getText().isEmpty() || txtPassword.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "No se puede dejar espacios en blanco.");
        }else{
      Usuario registro = new Usuario();
      
      registro.setNombreUsuario(txtNombreUsuario.getText());
      registro.setApellidoUsuario(txtApellidoUsuario.getText());
      registro.setUsuarioLogin(txtUsuario.getText());
      registro.setContrasena(txtPassword.getText());
      
      try {
          PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarUsuario(?, ?, ?, ?)}");
          procedimiento.setString(1, registro.getNombreUsuario());
          procedimiento.setString(2, registro.getApellidoUsuario());
          procedimiento.setString(3, registro.getUsuarioLogin());
          procedimiento.setString(4, registro.getContrasena());
          procedimiento.execute();
          
      } catch (Exception e) {
          e.printStackTrace();
      }
        }
    }
    
    
    public void eliminar(){
        switch(tipoDeOperacion){
          case GUARDAR:
            limpiarControles();
            desactivarControles();
            btnNuevo.setText("Nuevo");
            btnEliminar.setText("Eliminar");
            imgNuevo.setImage(new Image("/org/ricardocolindres/image/nuevo.png"));
            imgEliminar.setImage(new Image("/org/ricardocolindres/image/eliminar.png"));
            tipoDeOperacion = operaciones.NINGUNO;
          break; 
        }
    }
    
    

  public Principal getEscenarioPrincipal() {
    return escenarioPrincipal;
  }

  public void setEscenarioPrincipal(Principal escenarioPrincipal) {
    this.escenarioPrincipal = escenarioPrincipal;
  }
    
  public void desactivarControles(){
      txtCodigoUsuario.setEditable(false);
      txtNombreUsuario.setEditable(false);
      txtApellidoUsuario.setEditable(false);
      txtUsuario.setEditable(false);
      txtPassword.setEditable(false);
  }
  
  public void activarControles(){
      txtCodigoUsuario.setEditable(false);
      txtNombreUsuario.setEditable(true);
      txtApellidoUsuario.setEditable(true);
      txtUsuario.setEditable(true);
      txtPassword.setEditable(true);
  }
  
  public void limpiarControles(){
      txtCodigoUsuario.clear();
      txtNombreUsuario.clear();
      txtApellidoUsuario.clear();
      txtUsuario.clear();
      txtPassword.clear();
  }
  
  public void login(){
    escenarioPrincipal.login();
  }
    
}
