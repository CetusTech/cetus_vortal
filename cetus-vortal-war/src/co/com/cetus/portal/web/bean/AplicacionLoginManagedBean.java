package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import co.com.cetus.portal.web.security.UsuarioPortalPrincipal;
import co.com.cetus.vortal.jpa.entity.Aplicacion;

/**
 * The Class PortalManagedBean.
 * 
 * @author Andres Herrera - Cetus Technology
 */
@ManagedBean ( name = "aplicacionLoginManagedBean" )
@SessionScoped
public class AplicacionLoginManagedBean extends GeneralManagedBean {
  
  /** The Constant serialVersionUID. */
  private static final long      serialVersionUID = 5852872097452516214L;
  
  /** The principal. */
  private UsuarioPortalPrincipal principal        = null;
  
  private List< Aplicacion >     listaAplicacionesUsuario;
  private List< SelectItem >     listaAplicacionesUsuarioItem;
  
  private String                 itemAppSelected;
  
  /**
   * <p>
   * Inits the.
   * </p>
   * 
   * @author Andres Herrera - Cetus Technology
   */
  @PostConstruct
  public void init () {
    this.inicializarUsuario();
  }
  
  /**
   * <p>
   * Obtiene el serialversionuid.
   * </p>
   * 
   * @return the serialversionuid
   * @author Andres Herrera - Cetus Technology
   */
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  /**
   * <p>
   * Inicializar usuario.
   * </p>
   * 
   * @author Andres Herrera - Cetus Technology
   */
  private void inicializarUsuario () {
    HttpServletRequest httpServletRequest = null;
    principal = ( UsuarioPortalPrincipal ) getRequest().getUserPrincipal();
    
    if ( principal != null && principal.getUserGeneralDTO() != null ) {
      httpServletRequest = ( HttpServletRequest ) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      principal.getUserGeneralDTO().setIp( httpServletRequest.getRemoteAddr() );
      
      principal.getUserGeneralDTO().setHostName( httpServletRequest.getRemoteUser() );
      
      if ( principal.getUserGeneralDTO().getUser() != null
           && !principal.getUserGeneralDTO().getUser().getLogin().isEmpty() ) {
        try {
          listaAplicacionesUsuario = this.delegate.findAllAplicacionesByLogin( principal.getUserGeneralDTO().getUser().getLogin() );
          listaAplicacionesUsuarioItem = new ArrayList< SelectItem >();
          for ( Aplicacion data: listaAplicacionesUsuario ) {
            listaAplicacionesUsuarioItem.add( new SelectItem( data.getDescripcion(), String.valueOf( data.getId() ) ) );
          }
        } catch ( Exception e ) {
        }
        
      }
      
    }
    
  }
  
  /**
   * <p>
   * Obtiene el principal.
   * </p>
   * 
   * @return the principal
   * @author Andres Herrera - Cetus Technology
   */
  public UsuarioPortalPrincipal getPrincipal () {
    return principal;
  }
  
  /**
   * <p>
   * Asigna el principal.
   * </p>
   * 
   * @param principal
   *            the new principal
   * @author Andres Herrera - Cetus Technology
   */
  public void setPrincipal ( UsuarioPortalPrincipal principal ) {
    this.principal = principal;
  }
  
  @Override
  public void initElement () {
    
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
  
  public List< SelectItem > getListaAplicacionesUsuarioItem () {
    return listaAplicacionesUsuarioItem;
  }
  
  public void setListaAplicacionesUsuarioItem (
                                                List< SelectItem > listaAplicacionesUsuarioItem ) {
    this.listaAplicacionesUsuarioItem = listaAplicacionesUsuarioItem;
  }
  
  public String getItemAppSelected () {
    return itemAppSelected;
  }
  
  public void setItemAppSelected ( String itemAppSelected ) {
    this.itemAppSelected = itemAppSelected;
  }
  
}
