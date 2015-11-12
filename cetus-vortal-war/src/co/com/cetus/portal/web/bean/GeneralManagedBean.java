package co.com.cetus.portal.web.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.com.cetus.portal.web.delegate.GeneralDelegate;
import co.com.cetus.portal.web.security.UsuarioPortalPrincipal;
import co.com.cetus.portal.web.util.ConstantView;

/**
 * The Class GeneralManagedBean.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-war (1/09/2013)
 */
@SuppressWarnings ( "serial" )
public abstract class GeneralManagedBean implements Serializable {
  
  /** The delegate. */
  protected GeneralDelegate delegate = null;
  
  /**
   * </p> Instancia un nuevo general managed bean. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public GeneralManagedBean () {
    delegate = new GeneralDelegate();
  }
  
  /**
   * </p> Obtiene el context path. </p>
   *
   * @return the context path
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public String getContextPath () {
    ServletContext sc = ( ServletContext ) FacesContext.getCurrentInstance().getExternalContext().getContext();
    return sc.getContextPath();
  }
  
  /**
   * </p> Obtiene el response. </p>
   *
   * @return the response
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public HttpServletResponse getResponse () {
    ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletResponse response = ( HttpServletResponse ) ex.getResponse();
    return response;
  }
  
  /**
   * </p> Obtiene el request. </p>
   *
   * @return the request
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public HttpServletRequest getRequest () {
    ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletRequest request = ( HttpServletRequest ) ex.getRequest();
    return request;
  }
  
  /**
   * </p> Adds the message. </p>
   *
   * @param compId the comp id
   * @param msg the msg
   * @param detail the detail
   * @param severity the severity
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public static void addMessage ( String compId, String msg, String detail, FacesMessage.Severity severity ) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    FacesMessage fmsg = new FacesMessage( severity, msg, detail );
    ctx.addMessage( compId, fmsg );
  }
  
  /**
   * </p> Adds the message info. </p>
   *
   * @param compId the comp id
   * @param msg the msg
   * @param detail the detail
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public static void addMessageInfo ( String compId, String msg, String detail ) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    FacesMessage fmsg = new FacesMessage( FacesMessage.SEVERITY_INFO, msg, detail );
    ctx.addMessage( compId, fmsg );
  }
  
  /**
   * </p> Adds the message error. </p>
   *
   * @param compId the comp id
   * @param msg the msg
   * @param detail the detail
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public static void addMessageError ( String compId, String msg, String detail ) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    FacesMessage fmsg = new FacesMessage( FacesMessage.SEVERITY_ERROR, msg, detail );
    ctx.addMessage( compId, fmsg );
  }
  
  /**
   * </p> Limpiar mensajes. </p>
   *
   * @return el string
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public String limpiarMensajes () {
    return null;
  }
  
  /**
   * </p> Obtiene el real path. </p>
   *
   * @return the real path
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public String getRealPath () {
    String path = "";
    ServletContext sc = ( ServletContext ) FacesContext.getCurrentInstance().getExternalContext().getContext();
    path = sc.getRealPath( "/" );
    return path;
  }
  
  /**
   * </p> Obtiene el list message. </p>
   *
   * @return the list message
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public static List< FacesMessage > getListMessage () {
    FacesContext ctx = FacesContext.getCurrentInstance();
    return ctx.getMessageList();
  }
  
  /**
   * </p> Inits the element. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  @PostConstruct
  public abstract void initElement ();
  
  /*
   * METODO PARA CRUD
   */
  /**
   * </p> Delete. </p>
   *
   * @return el string
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public abstract String delete ();
  
  /**
   * </p> Update. </p>
   *
   * @return el string
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public abstract String update ();
  
  /**
   * </p> Adds the. </p>
   *
   * @return el string
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public abstract String add ();
  
  /**
   * </p> Obtiene el usuario creacion. </p>
   *
   * @return the usuario creacion
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public String getUsuarioCreacion () {
    String login = null;
    String ip = null;
    String acronimo = null;
    //String hostName = null;
    String usuarioCreacion = null;
    
    PortalManagedBean portalSession = ( PortalManagedBean ) getObjectSession( "portalManagedBean" );
    
    UsuarioPortalPrincipal principal = ( UsuarioPortalPrincipal ) getRequest().getUserPrincipal();
    ip = principal.getUserGeneralDTO().getIp();
    login = principal.getUserGeneralDTO().getUser().getLogin();
    //hostName = principal.getUserGeneralDTO().getHostName();
    
    if ( portalSession != null ) {
      acronimo = portalSession.getAcronimo();
      usuarioCreacion = acronimo + ConstantView.SEPARATOR + login + ConstantView.SEPARATOR + ip;
    }
    return usuarioCreacion;
    
  }
  
  /**
   * </p> Adds the object session. </p>
   *
   * @param obj the obj
   * @param key the key
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public void addObjectSession ( Object obj, String key ) {
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( key, obj );
  }
  
  /**
   * </p> Obtiene el object session. </p>
   *
   * @param pKey the p key
   * @return the object session
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public static Object getObjectSession ( String pKey ) {
    return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get( pKey ) != null ? FacesContext.getCurrentInstance().getExternalContext()
                                                                                                                    .getSessionMap().get( pKey ) : null;
  }
  
  /**
   * </p> Clean object session. </p>
   *
   * @param pKey the p key
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public void cleanObjectSession ( String pKey ) {
    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove( pKey );
  }
  
}
