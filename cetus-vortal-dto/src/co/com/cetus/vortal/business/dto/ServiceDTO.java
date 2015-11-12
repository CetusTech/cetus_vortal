package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The Class ServiceDTO.
 */

public class ServiceDTO implements Serializable {
  private static final long     serialVersionUID = 1L;
  
  private int                   id;
  
  private Date                  dateCreation;
  
  private Date                  dateUpdate;
  
  private String                name;
  
  private int                   status;
  
  private String                userCreation;
  
  private String                userUpdate;
  
  private String                wsdl;
  
  private AplicacionDTO         tbAplicacion;
  
  private List< PermissionDTO > tbPermissions;
  
  public ServiceDTO () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public Date getDateCreation () {
    return this.dateCreation;
  }
  
  public void setDateCreation ( Date dateCreation ) {
    this.dateCreation = dateCreation;
  }
  
  public Date getDateUpdate () {
    return this.dateUpdate;
  }
  
  public void setDateUpdate ( Date dateUpdate ) {
    this.dateUpdate = dateUpdate;
  }
  
  public String getName () {
    return this.name;
  }
  
  public void setName ( String name ) {
    this.name = name;
  }
  
  public int getStatus () {
    return this.status;
  }
  
  public void setStatus ( int status ) {
    this.status = status;
  }
  
  public AplicacionDTO getTbAplicacion () {
    return tbAplicacion;
  }

  public void setTbAplicacion ( AplicacionDTO tbAplicacion ) {
    this.tbAplicacion = tbAplicacion;
  }

  public String getUserCreation () {
    return this.userCreation;
  }
  
  public void setUserCreation ( String userCreation ) {
    this.userCreation = userCreation;
  }
  
  public String getUserUpdate () {
    return this.userUpdate;
  }
  
  public void setUserUpdate ( String userUpdate ) {
    this.userUpdate = userUpdate;
  }
  
  public String getWsdl () {
    return this.wsdl;
  }
  
  public void setWsdl ( String wsdl ) {
    this.wsdl = wsdl;
  }
  
  public List< PermissionDTO > getTbPermissions () {
    return this.tbPermissions;
  }
  
  public void setTbPermissions ( List< PermissionDTO > tbPermissions ) {
    this.tbPermissions = tbPermissions;
  }
  
  public PermissionDTO addTbPermission ( PermissionDTO tbPermission ) {
    getTbPermissions().add( tbPermission );
    tbPermission.setTbService( this );
    
    return tbPermission;
  }
  
  public PermissionDTO removeTbPermission ( PermissionDTO tbPermission ) {
    getTbPermissions().remove( tbPermission );
    tbPermission.setTbService( null );
    
    return tbPermission;
  }
  
}