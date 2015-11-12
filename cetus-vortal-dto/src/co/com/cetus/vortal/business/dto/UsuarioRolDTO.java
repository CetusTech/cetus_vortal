package co.com.cetus.vortal.business.dto;

import java.io.Serializable;

/**
 * The persistent class for the tb_usuario_rol database table.
 * 
 */
public class UsuarioRolDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int               id;
  
  private RolDTO            tbRol;
  
  private UsuarioDTO        tbUsuario;
  
  public UsuarioRolDTO () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public RolDTO getTbRol () {
    return this.tbRol;
  }
  
  public void setTbRol ( RolDTO tbRol ) {
    this.tbRol = tbRol;
  }
  
  public UsuarioDTO getTbUsuario () {
    return this.tbUsuario;
  }
  
  public void setTbUsuario ( UsuarioDTO tbUsuario ) {
    this.tbUsuario = tbUsuario;
  }
  
  @Override
  public String toString () {
    return null;
  }
  
}