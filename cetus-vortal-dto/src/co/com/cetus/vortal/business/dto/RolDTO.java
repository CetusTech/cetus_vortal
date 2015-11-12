package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_rol database table.
 * 
 */

public class RolDTO implements Serializable {
  private static final long     serialVersionUID = 1L;
  
  private int                   id;
  
  private String                descripcion;
  
  private Date                  fechaCreacion;
  
  private String                usuarioCreacion;
  
  private List< RolMenuDTO >    tbRolMenus;
  
  private List< UsuarioRolDTO > tbUsuarioRols;
  
  public RolDTO () {
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
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public List< RolMenuDTO > getTbRolMenus () {
    return this.tbRolMenus;
  }
  
  public void setTbRolMenus ( List< RolMenuDTO > tbRolMenus ) {
    this.tbRolMenus = tbRolMenus;
  }
  
  public List< UsuarioRolDTO > getTbUsuarioRols () {
    return this.tbUsuarioRols;
  }
  
  public void setTbUsuarioRols ( List< UsuarioRolDTO > tbUsuarioRols ) {
    this.tbUsuarioRols = tbUsuarioRols;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof RolDTO ) {
      if ( this.id == ( ( RolDTO ) obj ).getId() ) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode () {
    return ( int ) this.id;
  }
  
  @Override
  public String toString () {
    return null;
  }
}