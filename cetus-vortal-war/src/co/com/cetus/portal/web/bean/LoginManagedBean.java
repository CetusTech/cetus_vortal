package co.com.cetus.portal.web.bean;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContextAssociation;
import org.jboss.security.SimplePrincipal;

import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.UtilCommon;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;

/**
 * The Class LoginManagedBean.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-war (10/11/2013)
 */
public class LoginManagedBean extends GeneralManagedBean {
  
  /** The Constant serialVersionUID. */
  private static final long         serialVersionUID = 7837143337915219585L;
  
  /** The user. */
  private String                    user;
  
  /** The password. */
  private String                    password;
  
  /** The list app. */
  private List< Aplicacion >        listApp;
  
  /** The list app item. */
  private List< SelectItem >        listAppItem;
  
  /** The aplicacion. */
  private Aplicacion                aplicacion;
  
  /** The aplication servlet. */
  
  /** The flag app. */
  private boolean                   flagApp;
  
  /** The theme. */
  private String                    theme;
  
  /** The gp. */
  private GuestPreferences          gp;
  
  /** The themes. */
  private TreeMap< String, String > themes;
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @Override
  @PostConstruct
  public void initElement () {
    String idApp;
    try {
      
      // Listar todas las aplicaciones servlet existentes en Cetus
      this.listApp = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      this.convertToComboBox( listApp );
      aplicacion = null;
      theme = gp.getTheme();
      // Consultar si existe, esta variable se usa en la vista para
      // identificar si muestra o no el combo para seleccionar la
      // aplicacion
      flagApp = existeAppSession();
      
      // Id Aplicación Externa, si este valor llega es porque se realizo
      // una solicitud desde un acceso externo a cetus
      if ( getObjectSession( "idApp" ) != null ) {
        idApp = ( String ) getObjectSession( "idApp" );
        if ( idApp != null ) {
          aplicacion = this.delegate.find( Aplicacion.class, Integer.parseInt( idApp ) );
          if ( aplicacion != null ) {
            gp = ( GuestPreferences ) getObjectSession( "guestPreferences" );
            gp.setTheme( aplicacion.getTbTipoEstilo() != null ? aplicacion.getTbTipoEstilo().getParamXml() : null );
          }
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Convert to combo box. </p>
   *
   * @param pListRegister the p list register
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  private void convertToComboBox ( List< Aplicacion > pListRegister ) {
    listAppItem = new ArrayList< SelectItem >();
    for ( Aplicacion obj: pListRegister ) {
      listAppItem.add( new SelectItem( obj.getId(), obj.getDescripcion() ) );
    }
  }
  
  /**
   * </p> Login. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public void login () {
    String idAppExterno = null;
    HttpServletRequest request = null;
    try {
      if ( aplicacion != null ) {
        //Subir a Session el id dela aplicacion 
        addObjectSession( aplicacion.getId(), "aplicacionId" );
      } else {
        if ( getObjectSession( "idApp" ) != null ) {
          idAppExterno = ( String ) getObjectSession( "idApp" );
          //Subir a Session el id dela aplicacion
          addObjectSession( idAppExterno, "aplicacionId" );
        }
      }
      request = getRequest();
      // Realizar la autenticacion con la Base de datos
      request.login( this.getUser(), UtilCommon.encriptarClave( this.getPassword() ) );
      
      if ( request.getUserPrincipal() != null && !request.getUserPrincipal().getName().isEmpty() ) {
        Util.CETUS_WAR.info( "[LoginManagedBean.login]." + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_LOGIN ) );
        
        idAppExterno = ( String ) getObjectSession( "idApp" );
        
        if ( idAppExterno == null && aplicacion != null ) {
          aplicacion = this.delegate.find( Aplicacion.class, aplicacion.getId() );
          idAppExterno = String.valueOf( aplicacion.getId() );
          addObjectSession( idAppExterno, "idApp" );
          gp = ( GuestPreferences ) getObjectSession( "guestPreferences" );
          gp.setTheme( aplicacion.getTbTipoEstilo().getParamXml() );
        } else {
          aplicacion = this.delegate.find( Aplicacion.class, Integer.parseInt( idAppExterno ) );
        }
        if ( aplicacion != null ) {
          
          gp = ( GuestPreferences ) getObjectSession( "guestPreferences" );
          gp.setTheme( aplicacion.getTbTipoEstilo().getParamXml() );
          
          // Redireccionar despues del logueo a la pagina principal
          // del portal
          this.getResponse().sendRedirect( this.getContextPath() );
        } else {
          Util.CETUS_WAR.error( "[LoginManagedBean.login]." + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_APP ) + user );
          
          addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_APP ) );
          
        }
      } else {
        System.out.println( "Hola pailas!!!" );
      }
      
      //      else {
      //        cleanObjectSession( "aplicacionId" );
      //        if ( getObjectSession( "isFlagApp" ) != null && getObjectSession( "isFlagApp" ).equals( "1" ) ) {
      //          // El usuario no tiene aplicaciones asociadas
      //          addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_APP ) );
      //        } else {
      //          // No se autentico por problemas en las credenciales
      //          Util.CETUS_WAR.error( "[LoginManagedBean.login].  " + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN )
      //                                + "->" + user );
      //          addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.USER_NOT_LOGIN ) );
      //        }
      //      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INIT_SESSION ), e );
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INIT_SESSION ) );
    }
    
  }
  
  /**
   * </p> Existe app session. </p>
   *
   * @return true, si el proceso fue exitoso
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public boolean existeAppSession () {
    if ( getObjectSession( "idApp" ) != null ) return true;
    
    return false;
  }
  
  /**
   * </p> Logout. </p>
   *
   * @return el string
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public String logout () {
    ExternalContext ectx = null;
    Principal user = null;
    ObjectName jaasMgr = null;
    String domain = null;
    String idApp = null;
    try {
      // Obtengo una instancia del Contexto de la aplicacion
      ectx = FacesContext.getCurrentInstance().getExternalContext();
      // Obtengo el usuario Principal que esta logueado en el sistema
      user = new SimplePrincipal( getRequest().getUserPrincipal() != null ? getRequest().getUserPrincipal().getName() : null );
      // Obtengo el nombre del dominio de la aplicacion
      domain = SecurityContextAssociation.getSecurityContext()
                                         .getSecurityDomain();
      
      jaasMgr = new ObjectName( "jboss.as:subsystem=security,security-domain=" + domain );
      // Establesco los parametros necesarios para invocar el metodo
      Object[] params = { user != null ? user.getName() : null };
      String[] signature = { "java.lang.String" };
      MBeanServer server = ( MBeanServer ) MBeanServerFactory.findMBeanServer( null ).get( 0 );
      // Invocar el metodo para limpiar la cache paa el usuario en el
      // dominio especificado
      server.invoke( jaasMgr, "flushCache", params, signature );
      //      
      //      final ModelControllerClient client = ModelControllerClient.Factory.create( "localhost", 9999 );
      //      try {
      //        final ModelNode address = new ModelNode();
      //        address.add( "subsystem", "security" );
      //        address.add( "security-domain", "CetusVortalJaasRealm" );
      //        
      //        final ModelNode operation = new ModelNode();
      //        operation.get( "operation" ).set( "flush-cache" );
      //        operation.get( "address" ).set( address );
      //        operation.get( "principal" ).set( user.getName() );
      //        
      //        final ModelNode result = client.execute( operation );
      //        
      //        if ( !"success".equals( result.get( "outcome" ).asString() ) ) {
      //          throw new IllegalStateException( "operation failed" );
      //        }
      //        
      //      } finally {
      //        if ( client != null ) {
      //          client.close();
      //        }
      //      }
      
      if ( FacesContext.getCurrentInstance() != null && FacesContext.getCurrentInstance().getExternalContext() != null ) {
        ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = ( HttpSession ) ectx.getSession( true );
        idApp = ( String ) String.valueOf( getObjectSession( "idApp" ) );
        //session.removeAttribute( "idApp" );
        session.invalidate();
        ectx.invalidateSession();
      }
      
      // Redireccionar al inicio de sesion
      ectx.redirect( ectx.getRequestContextPath() );
      addObjectSession( idApp, "idApp" );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    return null;
  }
  
  public String getUser () {
    return user;
  }
  
  public void setUser ( String user ) {
    this.user = user;
  }
  
  public void setGp ( GuestPreferences gp ) {
    this.gp = gp;
  }
  
  public String getPassword () {
    return password;
  }
  
  public void setPassword ( String password ) {
    this.password = password;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public void saveTheme ( String ptheme ) {
    gp.setTheme( ptheme );
  }
  
  public TreeMap< String, String > getThemes () {
    return themes;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#delete()
   */
  @Override
  public String delete () {
    return null;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#update()
   */
  @Override
  public String update () {
    return null;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
   */
  @Override
  public String add () {
    return null;
  }
  
  public List< SelectItem > getListAppItem () {
    return listAppItem;
  }
  
  public void setListAppItem ( List< SelectItem > listAppItem ) {
    this.listAppItem = listAppItem;
  }
  
  public Aplicacion getAplicacion () {
    return aplicacion;
  }
  
  public void setAplicacion ( Aplicacion aplicacion ) {
    this.aplicacion = aplicacion;
  }
  
  public boolean isFlagApp () {
    return flagApp;
  }
  
  public void setFlagApp ( boolean flagApp ) {
    this.flagApp = flagApp;
  }
  
  public String getTheme () {
    return theme;
  }
  
  public void setTheme ( String theme ) {
    this.theme = theme;
  }
  
  public List< Aplicacion > getListApp () {
    return listApp;
  }
  
  public void setListApp ( List< Aplicacion > listApp ) {
    this.listApp = listApp;
  }
  
}
