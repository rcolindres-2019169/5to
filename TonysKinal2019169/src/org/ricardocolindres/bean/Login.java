package org.ricardocolindres.bean;

public class Login {
    
    private String usuarioMaster;
    private String passwwordLogin;

  public Login() {
    
  }

  public Login(String usuarioMaster, String passwwordLogin) {
    this.usuarioMaster = usuarioMaster;
    this.passwwordLogin = passwwordLogin;
  }

  public String getUsuarioMaster() {
    return usuarioMaster;
  }

  public void setUsuarioMaster(String usuarioMaster) {
    this.usuarioMaster = usuarioMaster;
  }

  public String getPasswwordLogin() {
    return passwwordLogin;
  }

  public void setPasswwordLogin(String passwwordLogin) {
    this.passwwordLogin = passwwordLogin;
  }
  
  
    
    
}
