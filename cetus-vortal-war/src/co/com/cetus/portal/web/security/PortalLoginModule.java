package co.com.cetus.portal.web.security;

import java.io.IOException;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.servlet.http.HttpSession;

import co.com.cetus.portal.web.delegate.GeneralDelegate;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.business.dto.UserGeneralDTO;
import co.com.cetus.common.dto.LoginDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class PortalLoginModule.
 * 
 * @author Andres Herrera - Cetus Technology
 * @version cetus-vortal-war (26/09/2012)
 */
public class PortalLoginModule implements LoginModule {
  
  /** The subject. */
  private Subject                  subject;
  
  /** The callback handler. */
  private CallbackHandler          callbackHandler;
  
  /** The username. */
  private String                   username;
  
  /** The user. */
  private UsuarioPortalPrincipal   user;
  
  /** The roles. */
  private UsuarioPortalPrincipal[] roles;
  
  /*
   * (non-Javadoc)
   * 
   * @see javax.security.auth.spi.LoginModule#abort()
   */
  @Override
  public boolean abort () throws LoginException {
    System.out.println("Abortado el loguego.... ");
    return false;
  }
  
  
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject
   * , javax.security.auth.callback.CallbackHandler, java.util.Map,
   * java.util.Map)
   */
  @Override
  public void initialize ( Subject subject, CallbackHandler callbackHandler, Map< String, ? > sharedState, Map< String, ? > options ) {
    this.subject = subject;
    this.callbackHandler = callbackHandler;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see javax.security.auth.spi.LoginModule#login()
   */
  @Override
  public boolean login () throws LoginException {
    NameCallback nameCallback = null;
    String idAppExterno = null;
    PasswordCallback passwordCallback = null;
    LoginDTO loginDTO = null;
    Callback[] callbacks = null;
    char[] password = null;
    String pass = null;
    GeneralDelegate portalDelegate = null;
    UserGeneralDTO usuarioBD = null;
    ResponseWSDTO lResponseWSDTO = null;
    
    try {
      portalDelegate = new GeneralDelegate();
      nameCallback = new NameCallback( "Username" );
      passwordCallback = new PasswordCallback( "Password", false );
      loginDTO = new LoginDTO();
      callbacks = new Callback[]{ nameCallback, passwordCallback };
      callbackHandler.handle( callbacks );
      username = nameCallback.getName();
      password = passwordCallback.getPassword();
      pass = new String( password );
      passwordCallback.clearPassword();
      FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "isFlagApp", "0" );
      loginDTO.setLogin( username );
      loginDTO.setPassword( pass );
      // Realizar la autenticacion con la base de datos
      lResponseWSDTO = portalDelegate.authenticationUser( loginDTO );
      if ( lResponseWSDTO != null && lResponseWSDTO.getDataResponseXML() != null ) {
        // Se debe converitir el XML a Object
        usuarioBD = new UserGeneralDTO();
        usuarioBD = ( UserGeneralDTO ) UtilCommon.fromXML( lResponseWSDTO.getDataResponseXML() );
      } else {
        Util.CETUS_WAR.error( "[PortalLoginModule.login]" + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN ) );
      }
      if ( usuarioBD != null && lResponseWSDTO.getDataResponseXML() != null ) {
        user = new UsuarioPortalPrincipal( username );
        user.setUserGeneralDTO( usuarioBD );
        HttpSession session = ( HttpSession ) FacesContext.getCurrentInstance().getExternalContext().getSession( true );
        roles = new UsuarioPortalPrincipal[]{ new UsuarioPortalPrincipal( "admin" ) };
        // Aplicacion con la que intenta loguarse
        idAppExterno = String.valueOf( FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get( "aplicacionId" ) );
        // if (portalDelegate.validateUserApp(username,
        // idAppExterno)) {
        if ( portalDelegate.validateUserApp( username, idAppExterno ) ) {
          // El usuario tiene aplicaciones asociadas
          // Iniciar la session del usuario
          session.setAttribute( "usuarioLogin", user );
          return true;
        } else {
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "isFlagApp", "1" );
        }
      } else {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "isFlagApp", "0" );
      }
    } catch ( IOException e ) {
      Util.CETUS_WAR.error( "[PortalLoginModule.login]" + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN ) );
    } catch ( UnsupportedCallbackException e ) {
      Util.CETUS_WAR.error( "[PortalLoginModule.login]" + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN ) );
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( "[PortalLoginModule.login]" + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN ) );
    }
    return false;
  }
  /*
   * (non-Javadoc)
   * 
   * @see javax.security.auth.spi.LoginModule#commit()
   */
  @Override
  public boolean commit () throws LoginException {
    // this is the important part to work with JBoss:
    subject.getPrincipals().add( user );
    // jboss requires the name 'Roles'
    PortalGroup group = new PortalGroup( "Roles" );
    for ( UsuarioPortalPrincipal role: roles ) {
      group.addMember( role );
    }
    subject.getPrincipals().add( group );
    
    return true;
  }
  /*
   * (non-Javadoc)
   * 
   * @see javax.security.auth.spi.LoginModule#logout()
   */
  @Override
  public boolean logout () throws LoginException {
    ExternalContext ectx = null;
    HttpSession session = null;
    String idApp = null;
    try {
      if ( FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null ) {
        ectx = FacesContext.getCurrentInstance().getExternalContext();
        session = ( HttpSession ) ectx.getSession( true );
        idApp = ( String ) session.getAttribute( "idApp" );
        session.removeAttribute( "idApp" );
        session.invalidate();
        ectx.invalidateSession();
        ectx = FacesContext.getCurrentInstance().getExternalContext();
        session = ( HttpSession ) ectx.getSession( true );
        session.setAttribute( "idApp", idApp );
      }
    } catch ( NullPointerException e ) {
      e.printStackTrace();
    }
    return true;
  }
  
}
