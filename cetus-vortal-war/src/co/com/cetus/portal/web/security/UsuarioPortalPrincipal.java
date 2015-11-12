package co.com.cetus.portal.web.security;

import java.io.Serializable;
import java.security.Principal;

import co.com.cetus.vortal.business.dto.UserGeneralDTO;

/**
 * UsuarioPortalPrincipal para autenticacion de usuarios, almacena informaciode los usuarios autenticados en el sistema
 * 
 * @author Andres Herrera
 * 
 */
public class UsuarioPortalPrincipal implements Principal, Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String            name;
  private UserGeneralDTO    userGeneralDTO;
  
  public UsuarioPortalPrincipal ( String name ) {
    this.name = name;
  }
  
  @Override
  public String getName () {
    return name;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof UsuarioPortalPrincipal ) {
      return ( ( UsuarioPortalPrincipal ) obj ).getName().equals( this.name );
    }
    return false;
  }
  
  @Override
  public int hashCode () {
    return name.hashCode();
  }
  
  public void setName ( String name ) {
    this.name = name;
  }
  
  public UserGeneralDTO getUserGeneralDTO () {
    return userGeneralDTO;
  }
  
  public void setUserGeneralDTO ( UserGeneralDTO userGeneralDTO ) {
    this.userGeneralDTO = userGeneralDTO;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
}
