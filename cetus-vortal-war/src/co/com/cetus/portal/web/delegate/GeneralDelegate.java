package co.com.cetus.portal.web.delegate;

import java.util.List;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.ejb.service.PortalServiceRemote;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.dto.LoginDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ServiceException;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class GeneralDelegate.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-war (10/11/2013)
 */
public class GeneralDelegate {
  
  /** The portal service. */
  private PortalServiceRemote portalService;
  
  /**
   * </p> Instancia un nuevo general delegate. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public GeneralDelegate () {
    portalService = ( PortalServiceRemote ) UtilCommon.getLookup( ConstantView.CONTEXT_SERVICE_REMOTE, ConstantView.Internalizacion.JAVA_NAMING_PROVIDER_URL );
  }
  
  /**
   * </p> Find all. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @return el list
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > List< T > findAll ( Class< T > pEntityClass ) throws ServiceException {
    return portalService.findAll( pEntityClass );
  }
  
  /**
   * </p> Find all order. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param pFindOrder the p find order
   * @param pTipyOrder the p tipy order
   * @return el list
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > List< T > findAllOrder ( Class< T > pEntityClass, String pFindOrder, String pTipyOrder ) throws ServiceException {
    return portalService.findAllOrder( pEntityClass, pFindOrder, pTipyOrder );
  }
  
  /**
   * </p> Find. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param obj the obj
   * @return el t
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > T find ( Class< T > pEntityClass, Object obj ) throws ServiceException {
    return portalService.find( pEntityClass, obj );
  }
  
  /**
   * </p> Creates the. </p>
   *
   * @param <T> the generic type
   * @param obj the obj
   * @return el t
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > T create ( T obj ) throws ServiceException {
    return portalService.create( obj );
  }
  
  /**
   * </p> Removes the. </p>
   *
   * @param <T> the generic type
   * @param obj the obj
   * @return el boolean
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > Boolean remove ( T obj ) throws ServiceException {
    return portalService.remove( obj );
  }
  
  /**
   * </p> Edits the. </p>
   *
   * @param <T> the generic type
   * @param obj the obj
   * @return el boolean
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > Boolean edit ( T obj ) throws ServiceException {
    return portalService.edit( obj );
  }
  
  /**
   * </p> Authentication user. </p>
   *
   * @param obj the obj
   * @return el response wsdto
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public ResponseWSDTO authenticationUser ( LoginDTO obj ) throws Exception {
    try {
      return portalService.authenticationUser( obj );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all menu by id padre. </p>
   *
   * @param pIdPadre the p id padre
   * @param pLogin the p login
   * @param pIdAplicacion the p id aplicacion
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Menu > findAllMenuByIdPadre ( int pIdPadre, String pLogin, int pIdAplicacion ) throws Exception {
    try {
      return portalService.findAllMenuByIdPadre( pIdPadre, pLogin, pIdAplicacion );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find menu by login and application. </p>
   *
   * @param pIdAplicacion the p id aplicacion
   * @param pLogin the p login
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Menu > findMenuByLoginAndApplication ( int pIdAplicacion, String pLogin ) throws Exception {
    try {
      return portalService.findMenuByLoginAndApplication( pIdAplicacion, pLogin );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Remover rol menu by rol. </p>
   *
   * @param pIdRol the p id rol
   * @param pIdApp the p id app
   * @return el int
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public int removerRolMenuByRol ( int pIdRol, int pIdApp ) throws Exception {
    try {
      return portalService.removerRolMenuByRol( pIdRol, pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all by id rol. </p>
   *
   * @param pIdRol the p id rol
   * @param pIdApp the p id app
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Menu > findAllByIdRol ( int pIdRol, int pIdApp ) throws Exception {
    try {
      return portalService.findAllByIdRol( pIdRol, pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Remover user rol by user. </p>
   *
   * @param pIdUser the p id user
   * @return el int
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public int removerUserRolByUser ( int pIdUser ) throws Exception {
    try {
      return portalService.removerUserRolByUser( pIdUser );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all rol by id user. </p>
   *
   * @param pIdUser the p id user
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Rol > findAllRolByIdUser ( int pIdUser ) throws Exception {
    try {
      return portalService.findAllRolByIdUser( pIdUser );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all menu. </p>
   *
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Menu > findAllMenu () throws Exception {
    try {
      return portalService.findAllMenu();
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all aplicaciones by login. </p>
   *
   * @param login the login
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Aplicacion > findAllAplicacionesByLogin ( String login ) throws Exception {
    try {
      return portalService.findAllAplicacionesByLogin( login );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Validate user app. </p>
   *
   * @param pLogin the p login
   * @param pIdApp the p id app
   * @return true, si el proceso fue exitoso
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public boolean validateUserApp ( String pLogin, String pIdApp ) throws Exception {
    try {
      return portalService.validateUserApp( pLogin, pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Validate user app. </p>
   *
   * @param pLogin the p login
   * @return true, si el proceso fue exitoso
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public boolean validateUserApp ( String pLogin ) throws Exception {
    try {
      return portalService.validateUserApp2( pLogin );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Handle change application. </p>
   *
   * @param pIdApp the p id app
   * @return el response wsdto
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public ResponseWSDTO handleChangeServletComboBox ( int pIdApp ) throws Exception {
    try {
      return portalService.handleChangeServletComboBox( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Query param by abreviature. </p>
   *
   * @param pAbreviature the p abreviature
   * @return el response wsdto
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public ResponseWSDTO queryParamByAbreviature ( String pAbreviature ) throws Exception {
    try {
      return portalService.queryParamByAbreviature( pAbreviature );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all by type parameter. </p>
   *
   * @param <E> the element type
   * @param pType the p type
   * @return el response wsdto
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < E > ResponseWSDTO findAllByTypeParameter ( String pType ) throws Exception {
    try {
      return portalService.findAllByTypeParameter( pType );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Handle change application rol menu. </p>
   *
   * @param pIdApp the p id app
   * @return el response wsdto
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public ResponseWSDTO handleChangeApplicationRolMenu ( int pIdApp ) throws Exception {
    try {
      return portalService.handleChangeApplicationRolMenu( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all filter. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param atributos the atributos
   * @param pIsAndOr the p is and or
   * @return el list
   * @throws ServiceException the service exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public < T > List< T > findAllFilter ( Class< T > pEntityClass, List< AttributeDTO > atributos, String pIsAndOr ) throws ServiceException {
    return portalService.findAllFilter( pEntityClass, atributos, pIsAndOr );
  }
  
  /**
   * </p> Find all service by application. </p>
   *
   * @param pIdApp the p id app
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Service > findAllServiceByApplication ( int pIdApp ) throws Exception {
    try {
      return portalService.findAllServiceByApplication( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all component by application. </p>
   *
   * @param pIdApp the p id app
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Component > findAllComponentByApplication ( int pIdApp ) throws Exception {
    try {
      return portalService.findAllComponentByApplication( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all parameter by application. </p>
   *
   * @param pIdApp the p id app
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Parametro > findAllParameterByApplication ( int pIdApp ) throws Exception {
    try {
      return portalService.findAllParameterByApplication( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Find all parameter by comp app. </p>
   *
   * @param pIdApp the p id app
   * @param idComponent the id component
   * @return el list
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public List< Parametro > findAllParameterByCompApp ( int pIdApp, int idComponent ) throws Exception {
    try {
      return portalService.findAllParameterByCompApp( pIdApp, idComponent );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Gets the value parameter. </p>
   *
   * @param name the name
   * @return el string
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public String getValueParameter ( String name ) throws Exception {
    try {
      return portalService.getValueParameter( name );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  /**
   * </p> Reload parameter. </p>
   *
   * @param pIdApp the p id app
   * @param idComponent the id component
   * @return true, si el proceso fue exitoso
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public boolean reloadParameter ( int pIdApp, int idComponent ) throws Exception {
    try {
      return portalService.reloadParameter( pIdApp, idComponent );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  public ResponseWSDTO handleChangeApplicationComboBoxServlet ( int pIdApp ) throws Exception {
    try {
      return portalService.handleChangeApplicationComboBoxServlet( pIdApp );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
  public boolean reloadParameterComponent ( int pIdApp, int idComponent, String nameComponent ) throws Exception {
    try {
      return portalService.reloadParameterComponent( pIdApp, idComponent, nameComponent );
    } catch ( ServiceException e ) {
      throw new Exception( e.getMessage() );
    }
  }
  
}
