/*
Ricardo Adrián Colindres Franco
2019169
IN5AM
Fecha de Creación: 29/03/2023
Fechas de Modificación: 
    11/04/2023
    15/04/2023
    18/04/2023
    23/04/2023
    25/04/2023
*/

package org.ricardocolindres.main;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.ricardocolindres.controller.EmpleadosController;
import org.ricardocolindres.controller.EmpresaController;
import org.ricardocolindres.controller.LoginController;
import org.ricardocolindres.controller.MenuPrincipalController;
import org.ricardocolindres.controller.PlatosController;
import org.ricardocolindres.controller.PresupuestosController;
import org.ricardocolindres.controller.ProductosController;
import org.ricardocolindres.controller.ProductosPlatosController;
import org.ricardocolindres.controller.ProgramadorController;
import org.ricardocolindres.controller.ServiciosController;
import org.ricardocolindres.controller.ServiciosEmpleadosController;
import org.ricardocolindres.controller.ServiciosPlatosController;
import org.ricardocolindres.controller.TipoEmpleadoController;
import org.ricardocolindres.controller.TipoPlatoController;
import org.ricardocolindres.controller.UsuarioController;


public class Principal extends Application {
    private final String PAQUETE_VISTA = "/org/ricardocolindres/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    
    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Tony's Kinal 2023");
        escenarioPrincipal.getIcons().add(new Image("/org/ricardocolindres/image/Menu.png"));
        //Parent root = FXMLLoader.load(getClass().getResource("/org/ricardocolindres/view/MenuPrincipalView.fxml"));
        //Scene escena = new Scene(root);
        //escenarioPrincipal.setScene(escena);
        login();
        escenarioPrincipal.show();
        
    }
    
   
    public void menuPrincipal(){
        try {
          MenuPrincipalController menu = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml", 587, 596);
          menu.setEscenarioPrincipal(this);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    
    public void ventanaProgramador(){
      try {
        ProgramadorController programador = (ProgramadorController)cambiarEscena("ProgramadorView.fxml",566,414);
        programador.setEscenarioPrincipal(this);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    
    public void ventanaEmpresa(){
        try
        {
            EmpresaController empresaController = (EmpresaController)cambiarEscena("EmpresaView.fxml", 950, 540);
            empresaController.setEscenarioPrincipal(this);
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoEmpleado(){
        try
        {
            TipoEmpleadoController tipoEmpleadoController = (TipoEmpleadoController)cambiarEscena("TipoEmpleadoView.fxml", 950, 540);
            tipoEmpleadoController.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaTipoPlato(){
        try
        {
            TipoPlatoController tipoPlatoController = (TipoPlatoController) cambiarEscena("TipoPlatoView.fxml", 950, 540);
            tipoPlatoController.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProductos(){
        try
        {
            ProductosController productosController = (ProductosController) cambiarEscena("ProductosView.fxml", 950, 570);
            productosController.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleados(){
        try
        {
            EmpleadosController empleados = (EmpleadosController)cambiarEscena("EmpleadosView.fxml", 1200, 570);
            empleados.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaServicios(){
        try
        {
            ServiciosController servicios = (ServiciosController)cambiarEscena("ServiciosView.fxml", 1200, 570);
            servicios.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPresupuestos(){
        try
        {
            PresupuestosController presupuestosController = (PresupuestosController)cambiarEscena("PresupuestosView.fxml", 950, 570);
            presupuestosController.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPlatos(){
        try
        {
            PlatosController platos = (PlatosController)cambiarEscena("PlatosView.fxml", 1000, 540);
            platos.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaProductosPlatos(){
        try
        {
            ProductosPlatosController productosPlatos = (ProductosPlatosController) cambiarEscena("ProductosPlatosView.fxml", 750, 540);
            productosPlatos.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventantaServiciosPlatos(){
        try
        {
            ServiciosPlatosController serviciosPlatos = (ServiciosPlatosController)cambiarEscena("ServiciosPlatosView.fxml", 750, 540);
            serviciosPlatos.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaServiciosEmpleados(){
        try
        {
            ServiciosEmpleadosController serviciosEmpleados = (ServiciosEmpleadosController)cambiarEscena("ServiciosEmpleadosView.fxml", 1000, 540);
            serviciosEmpleados.setEscenarioPrincipal(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void login(){
      try {
          LoginController login = (LoginController)cambiarEscena("LoginView.fxml", 700, 500);
          login.setEscenarioPrincipal(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    
    public void ventanaUsuario(){
      try {
          UsuarioController usuario =(UsuarioController)cambiarEscena("UsuariosView.fxml", 660, 510);
          usuario.setEscenarioPrincipal(this);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
 

    public static void main(String[] args) {
        launch(args);
    }
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        
        return resultado;
    }
}
