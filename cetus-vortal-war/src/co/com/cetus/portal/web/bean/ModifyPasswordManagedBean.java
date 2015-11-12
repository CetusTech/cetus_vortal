package co.com.cetus.portal.web.bean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.validation.constraints.Size;

import org.primefaces.context.RequestContext;

import co.com.cetus.portal.web.security.UsuarioPortalPrincipal;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.common.exception.ServiceException;
import co.com.cetus.common.util.UtilCommon;

@ManagedBean ( name = "modifyPasswordManagedBean" )
@RequestScoped
public class ModifyPasswordManagedBean extends GeneralManagedBean implements
                                                                 Cloneable, Serializable {
  
  private Usuario           usuario          = null;
  private String            passwordActual   = null;
  private static final long serialVersionUID = 1952428504080910113L;
  @Size ( min = 5, max = 15, message = "La Longitud debe estar entre 5 y 15." )
  private String            password         = "";
  @Size ( min = 5, max = 15, message = "La Longitud debe estar entre 5 y 15." )
  private String            confirm          = "";
  private UIComponent       btnSave          = null;
  private UIComponent       btnConfirm       = null;
  
  public void storeNewPassword () {
    
    try {
      if ( usuario != null ) {
        password = ( String ) getObjectSession( "password" );
        
        usuario.setPassword( UtilCommon.encriptarClave( password != null ? password : "" ) );
        usuario.setUsuarioCreacion( getUsuarioCreacion() );
        usuario.setFechaCreacion( new Date() );
        try {
          this.delegate.edit( usuario );
          this.password = null;
          this.passwordActual = null;
          this.confirm = null;
          addMessageInfo( btnConfirm != null ? btnConfirm.getClientId() : null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
          cleanObjectSession( "password" );
          cleanObjectSession( "password" );
          
        } catch ( ServiceException e ) {
          addMessageError( btnConfirm != null ? btnConfirm.getClientId() : null, ConstantView.ERROR,
                           Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ) );
          Util.CETUS_WAR.error( e.getMessage(), e );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  public void validateFormModPass () {
    RequestContext context = null;
    try {
      if ( usuario != null ) {
        if ( passwordActual != null && UtilCommon.encriptarClave( passwordActual ).equals( usuario.getPassword() ) ) {
          context = RequestContext.getCurrentInstance();
          if ( !passwordActual.equals( password ) ) {
            usuario.setPassword( UtilCommon.encriptarClave( this.password ) );
            usuario.setUsuarioCreacion( getUsuarioCreacion() );
            usuario.setFechaCreacion( new Date() );
            if ( password.equals( confirm ) ) {
              addObjectSession( password, "password" );
              context.addCallbackParam( "validate", true );
            } else {
              context.addCallbackParam( "validate", false );
              addMessageError( btnSave != null ? btnSave.getClientId() : null, ConstantView.ERROR,
                               Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PASS_DIF ) );
            }
          } else {
            context.addCallbackParam( "validate", false );
            addMessageError( btnSave != null ? btnSave.getClientId() : null, ConstantView.ERROR,
                             Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OLD_NEW_EQUALS ) );
          }
          
        } else {
          addMessageError( btnSave != null ? btnSave.getClientId() : null, ConstantView.ERROR,
                           Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_PASS_NEW_PASS_OLD_DIF ) );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void setPassword ( String password ) {
    this.password = password;
  }
  
  public void setConfirm ( String confirm ) {
    this.confirm = confirm;
  }
  
  public String getPassword () {
    return password;
  }
  
  public String getConfirm () {
    return confirm;
  }
  
  @Override
  public Object clone () throws CloneNotSupportedException {
    return super.clone();
  }
  
  public Usuario getUsuario () {
    return usuario;
  }
  
  public void setUsuario ( Usuario usuario ) {
    this.usuario = usuario;
  }
  
  public String getPasswordActual () {
    return passwordActual;
  }
  
  public void setPasswordActual ( String passwordActual ) {
    this.passwordActual = passwordActual;
  }
  
  @Override
  public String delete () {
    return null;
  }
  
  @Override
  public String update () {
    return null;
  }
  
  @Override
  public String add () {
    return null;
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    UsuarioPortalPrincipal principal = ( UsuarioPortalPrincipal ) getRequest().getUserPrincipal();
    try {
      if ( principal != null && principal.getUserGeneralDTO() != null && principal.getUserGeneralDTO().getUser() != null ) {
        this.usuario = this.delegate.find( Usuario.class, principal.getUserGeneralDTO().getUser().getId() );
      }
    } catch ( ServiceException e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }
  
  public UIComponent getBtnConfirm () {
    return btnConfirm;
  }
  
  public void setBtnConfirm ( UIComponent btnConfirm ) {
    this.btnConfirm = btnConfirm;
  }
  
}