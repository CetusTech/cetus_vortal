package co.com.cetus.portal.ejb.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import co.com.cetus.portal.ejb.process.GeneralProcessLocal;
import co.com.cetus.portal.ejb.process.PortalProcessLocal;
import co.com.cetus.portal.ejb.util.ConstantBussines;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.dto.LoginDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.common.exception.ServiceException;

@WebService
@Stateless ( mappedName = "PortalServiceBean" )
public class PortalService implements PortalServiceRemote, PortalServiceLocal {
  
  @EJB ( name = "cetus-vortal-ear/PortalProcess/local" )
  private PortalProcessLocal  portalProcess;
  
  /** The general process. */
  @EJB ( name = "cetus-vortal-ear/GeneralProcess/local" )
  private GeneralProcessLocal generalProcess;
  
  @WebMethod ( exclude = true )
  public < T > T create ( T obj ) throws ServiceException {
    try {
      return generalProcess.create( obj );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > Boolean remove ( T obj ) throws ServiceException {
    try {
      return generalProcess.remove( obj );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_SERVICE ),
                                  e.getProcess(), e.getDelegate() );
    } catch ( Exception e ) {
      throw new ServiceException( e.getMessage(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_SERVICE ),
                                  e.getCause().toString(), null );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > Boolean edit ( T obj ) throws ServiceException {
    try {
      return generalProcess.edit( obj );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > T find ( Class< T > pEntityClass, Object obj ) throws ServiceException {
    try {
      return generalProcess.find( pEntityClass, obj );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > Integer count ( Class< T > pEntityClass ) throws ServiceException {
    try {
      return generalProcess.count( pEntityClass );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > List< T > findAllFilter ( Class< T > pEntityClass, List< AttributeDTO > atributos, String pIsAndOr ) throws ServiceException {
    try {
      return generalProcess.findAllFilter( pEntityClass, atributos, pIsAndOr );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > List< T > findAllOrder ( Class< T > pEntityClass, String pFindOrder, String pTipyOrder ) throws ServiceException {
    try {
      return generalProcess.findAll( pEntityClass, pFindOrder, pTipyOrder );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public < T > List< T > findRange ( Class< T > pEntityClass, int[] range ) throws ServiceException {
    try {
      return generalProcess.findRange( pEntityClass, range );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @Override
  @WebMethod ( exclude = true )
  public < T > List< T > findAll ( Class< T > pEntityClass ) throws ServiceException {
    try {
      return generalProcess.findAll( pEntityClass );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @Override
  @WebMethod ( exclude = true )
  public @WebResult ( name = "ResponseWSDTO" )
  ResponseWSDTO authenticationUser ( @WebParam ( name = "dataLogin" ) LoginDTO obj ) throws ServiceException {
    try {
      return portalProcess.authenticationUser( obj );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Menu > findAllMenuByIdPadre ( int pIdPadre, String pLogin, int pIdAplicacion ) throws ServiceException {
    try {
      return portalProcess.findAllMenuByIdPadre( pIdPadre, pLogin, pIdAplicacion );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @Override
  @WebMethod ( exclude = true )
  public List< Menu >
      findMenuByLoginAndApplication ( @WebParam ( name = "aplicacionId" ) int pIdAplicacion, @WebParam ( name = "Login" ) String pLogin ) throws ServiceException {
    try {
      return portalProcess.findMenuByLoginAndApplication( pIdAplicacion, pLogin );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public int removerRolMenuByRol ( int pIdRol, int pIdApp ) throws ServiceException {
    try {
      return portalProcess.removerRolMenuByRol( pIdRol, pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Menu > findAllByIdRol ( int pIdRol, int pIdApp ) throws ServiceException {
    try {
      return portalProcess.findAllByIdRol( pIdRol, pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public int removerUserRolByUser ( int pIdUser ) throws ServiceException {
    try {
      return portalProcess.removerUserRolByUser( pIdUser );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Rol > findAllRolByIdUser ( int pIdUser ) throws ServiceException {
    try {
      return portalProcess.findAllRolByIdUser( pIdUser );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Menu > findAllMenu () throws ServiceException {
    try {
      return portalProcess.findAllMenu();
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Aplicacion > findAllAplicacionesByLogin ( String login ) throws ServiceException {
    try {
      return portalProcess.findAllAplicacionesByLogin( login );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public ResponseWSDTO getUserSessionCetus ( String pIdUser ) throws ServiceException {
    try {
      return portalProcess.getUserSessionCetus( pIdUser );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public boolean validateUserApp ( String pLogin, String pIdApp ) throws ServiceException {
    try {
      return portalProcess.validateUserApp( pLogin, pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public boolean validateUserApp2 ( String pLogin ) throws ServiceException {
    try {
      return portalProcess.validateUserApp( pLogin );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public < E > ResponseWSDTO handleChangeServletComboBox ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.handleChangeServletComboBox( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  public ResponseWSDTO queryParamByAbreviature ( String pAbreviature ) throws ServiceException {
    try {
      return portalProcess.queryParamByAbreviature( pAbreviature );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  public < E > ResponseWSDTO findAllByTypeParameter ( String pType ) throws ServiceException
  {
    try {
      return portalProcess.findAllByTypeParameter( pType );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public < E > ResponseWSDTO handleChangeApplicationRolMenu ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.handleChangeApplicationRolMenu( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Service > findAllServiceByApplication ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.findAllServiceByApplication( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Component > findAllComponentByApplication ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.findAllComponentByApplication( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Parametro > findAllParameterByApplication ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.findAllParameterByApplication( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public List< Parametro > findAllParameterByCompApp ( int pIdApp, int idComponent ) throws ServiceException {
    try {
      return portalProcess.findAllParameterByCompApp( pIdApp, idComponent );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public String getValueParameter ( String name ) throws ServiceException {
    try {
      return portalProcess.getValueParameter( name );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public boolean reloadParameter ( int pIdApp, int idComponent ) throws ServiceException {
    try {
      return portalProcess.reloadParameter( pIdApp, idComponent );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @Override
  @WebMethod ( exclude = true )
  public < E > ResponseWSDTO handleChangeApplicationComboBoxServlet ( int pIdApp ) throws ServiceException {
    try {
      return portalProcess.handleChangeApplicationComboBoxServlet( pIdApp );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }

  @WebMethod ( exclude = true )
  @Override
  public boolean reloadParameterComponent ( int pIdApp, int idComponent, String nameComponent ) throws ServiceException {
    try {
      return portalProcess.reloadParameterComponent( pIdApp, idComponent,nameComponent );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  @WebMethod ( exclude = true )
  @Override
  public boolean createUser( Usuario user ) throws ServiceException {
    try {
      return portalProcess.createUser( user );
    } catch ( ProcessException e ) {
      throw new ServiceException( e.getMessage(), e.getStackTrace().toString(), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                                  ConstantBussines.Internalizacion.PORTAL_SERVICE ), e.getDelegate() );
    }
  }
  
  
  
}
