package org.ricardocolindres.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.ricardocolindres.main.Principal;

public class MenuPrincipalController implements Initializable{
    private Principal escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaProgramador(){
      escenarioPrincipal.ventanaProgramador();
    }
    
    public void ventanaEmpresa(){
        escenarioPrincipal.ventanaEmpresa();
    }
    
    public void ventanaTipoEmpleado(){
        escenarioPrincipal.ventanaTipoEmpleado();
    }
    
    public void ventanaTipoPlato(){
        escenarioPrincipal.ventanaTipoPlato();
    }
    
    public void ventanaProductos(){
        escenarioPrincipal.ventanaProductos();
    }
    
    public void ventanaEmpleados(){
        escenarioPrincipal.ventanaEmpleados();
    }
    
    public void ventanaServicios(){
        escenarioPrincipal.ventanaServicios();
    }
    
    public void ventanaPresupuestos(){
        escenarioPrincipal.ventanaPresupuestos();
    }
    
    public void ventanaPlatos(){
        escenarioPrincipal.ventanaPlatos();
    }
    
    public void ventanaProductosPlatos(){
        escenarioPrincipal.ventanaProductosPlatos();
    }
    
    public void ventantaServiciosPlatos(){
        escenarioPrincipal.ventantaServiciosPlatos();
    }
    
    public void ventanaServiciosEmpleados(){
        escenarioPrincipal.ventanaServiciosEmpleados();
    }
    
    public void login(){
      escenarioPrincipal.login();
    }
    
    public void ventanaUsuario(){
      escenarioPrincipal.ventanaUsuario();
    }
}
    