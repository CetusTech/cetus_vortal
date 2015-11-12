package co.com.cetus.vortal.business.dto;

import java.io.Serializable;

/**
 * The persistent class for the tb_rol_menu database table.
 * 
 */
public class RolMenuDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int               id;
  
  private MenuDTO           tbMenu;
  
  private RolDTO            tbRol;
  
  public RolMenuDTO () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public MenuDTO getTbMenu () {
    return this.tbMenu;
  }
  
  public void setTbMenu ( MenuDTO tbMenu ) {
    this.tbMenu = tbMenu;
  }
  
  public RolDTO getTbRol () {
    return this.tbRol;
  }
  
  public void setTbRol ( RolDTO tbRol ) {
    this.tbRol = tbRol;
  }
  
  @Override
  public String toString () {
    return null;
  }
  
}