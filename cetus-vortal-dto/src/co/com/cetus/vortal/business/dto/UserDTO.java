package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int               id;
  
  private String            descripcion;
  
  private Date              fechaCreacion;
  
  private String            identificacion;
  
  private String            login;
  
  private String            loginOld;
  
  private String            password;
  
  private String            usuarioCreacion;
  
  private String            email;
  private String            phone;
  private String            address;
  private int               status;
  
  public int getStatus () {
    return status;
  }
  
  public void setStatus ( int status ) {
    this.status = status;
  }
  
  public String getEmail () {
    return email;
  }
  
  public void setEmail ( String email ) {
    this.email = email;
  }
  
  public String getPhone () {
    return phone;
  }
  
  public void setPhone ( String phone ) {
    this.phone = phone;
  }
  
  public String getAddress () {
    return address;
  }
  
  public void setAddress ( String address ) {
    this.address = address;
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public String getDescripcion () {
    return this.descripcion;
  }
  
  public void setDescripcion ( String descripcion ) {
    this.descripcion = descripcion;
  }
  
  public Date getFechaCreacion () {
    return this.fechaCreacion;
  }
  
  public void setFechaCreacion ( Date fechaCreacion ) {
    this.fechaCreacion = fechaCreacion;
  }
  
  public String getIdentificacion () {
    return this.identificacion;
  }
  
  public void setIdentificacion ( String identificacion ) {
    this.identificacion = identificacion;
  }
  
  public String getLogin () {
    return this.login;
  }
  
  public void setLogin ( String login ) {
    this.login = login;
  }
  
  public String getPassword () {
    return this.password;
  }
  
  public void setPassword ( String password ) {
    this.password = password;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  @Override
  public String toString () {
    return null;
  }
  
  public String getLoginOld () {
    return loginOld;
  }
  
  public void setLoginOld ( String loginOld ) {
    this.loginOld = loginOld;
  }
  
}
