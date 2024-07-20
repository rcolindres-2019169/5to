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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.ricardocolindres.bean.Login;
import org.ricardocolindres.bean.Usuario;
import org.ricardocolindres.db.Conexion;
import org.ricardocolindres.main.Principal;

public class LoginController implements Initializable{
      private Principal escenarioPrincipal;
      private ObservableList<Usuario> listaUsuario;
      
      @FXML private TextField txtUsuario;
      @FXML private TextField txtContrasena;
      
  @Override
  public void initialize(URL location, ResourceBundle resources){
        
  }
  
  public ObservableList<Usuario> getUsuario(){
      ArrayList<Usuario> lista = new ArrayList<Usuario>();
      try {
          PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarUsuarios()}");
          ResultSet resultado = procedimiento.executeQuery();
          while(resultado.next()){
              lista.add(new Usuario(resultado.getInt("codigoUsuario"),
                                    resultado.getString("nombreUsuario"),
                                    resultado.getString("apellidoUsuario"),
                                    resultado.getString("usuarioLogin"),
                                    resultado.getString("contrasena")));
          }
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return listaUsuario = FXCollections.observableArrayList(lista);
  }
  
  @FXML 
    private void sesion(){
        Login login = new Login();
        int x = 0;
        boolean bandera = false;
        login.setUsuarioMaster(txtUsuario.getText());
        login.setPasswwordLogin(txtContrasena.getText());
        
        while(x < getUsuario().size()){
            String user = getUsuario().get(x).getUsuarioLogin();
            String pass = getUsuario().get(x).getContrasena();
            if(user.equals(login.getUsuarioMaster()) && pass.equals(login.getPasswwordLogin())){
              JOptionPane.showMessageDialog(null, "Sesión iniciada\n"
                        + getUsuario().get(x).getNombreUsuario() + " "+
                        getUsuario().get(x).getApellidoUsuario() + "\n" + "Bienvenido!!");
              escenarioPrincipal.menuPrincipal();
              x = getUsuario().size();
              bandera = true;
            }
            x++; //x+ = 1  x= x+1        no ++x
        }
        if(bandera == false)
          JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");
    }
  
  

  public Principal getEscenarioPrincipal() {
    return escenarioPrincipal;
    
  }

  public void setEscenarioPrincipal(Principal escenarioPrincipal) {
    this.escenarioPrincipal = escenarioPrincipal;
  }
      
  public void ventanaUsuario(){
    escenarioPrincipal.ventanaUsuario();
  }
}
