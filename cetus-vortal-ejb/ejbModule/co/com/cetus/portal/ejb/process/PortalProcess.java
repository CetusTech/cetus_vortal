package co.com.cetus.portal.ejb.process;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.com.cetus.common.dto.LoginDTO;
import co.com.cetus.common.dto.ParameterDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.Converter;
import co.com.cetus.common.util.UtilCommon;
import co.com.cetus.messageservice.ejb.service.ReloadParameterRequestDTO;
import co.com.cetus.portal.ejb.delegate.CetusControlDelegate;
import co.com.cetus.portal.ejb.delegate.CetusMessageServiceDelegate;
import co.com.cetus.portal.ejb.util.AppConstants;
import co.com.cetus.portal.ejb.util.ConstantBussines;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.vortal.business.dto.AplicationServletDTO;
import co.com.cetus.vortal.business.dto.MenuDTO;
import co.com.cetus.vortal.business.dto.ParametroDTO;
import co.com.cetus.vortal.business.dto.UserDTO;
import co.com.cetus.vortal.business.dto.UserGeneralDTO;
import co.com.cetus.vortal.business.dto.UsuarioDTO;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.AplicationServlet;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.RolMenu;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.vortal.jpa.entity.UsuarioRol;

/**
 * The Class PortalProcess.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-ejb (1/09/2013)
 */
@Stateless
public class PortalProcess implements PortalProcessLocal {
  
  /** The em. */
  @PersistenceContext ( unitName = "cetus-vortal-jpa" )
  private EntityManager em;
                        
  /** The converter. */
  private Converter     converter;
                        
  /**
   * </p> Inits the. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (1/09/2013)
   */
  @PostConstruct
  public void init () {
    converter = new Converter( ConstantBussines.Internalizacion.PCK_CLASS_DTO, ConstantBussines.Internalizacion.PCK_CLASS_JPA );
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#authenticationUser(co.com.cetus.common.dto.LoginDTO)
   */
  @Override
  public ResponseWSDTO authenticationUser ( LoginDTO pObj ) throws ProcessException {
    TypedQuery< Usuario > query = null;
    UserGeneralDTO usuarioGeneralDTO = null;
    Usuario lUsuario = null;
    ResponseWSDTO lResponseWSDTO = null;
    try {
      lResponseWSDTO = new ResponseWSDTO();
      
      usuarioGeneralDTO = new UserGeneralDTO();
      if ( pObj != null && !pObj.getLogin().isEmpty() && !pObj.getPassword().isEmpty() ) {
        query = em.createNamedQuery( "validatedLogin", Usuario.class );
        query.setParameter( "login", pObj.getLogin() );
        query.setParameter( "password", pObj.getPassword() );
        
        // Obtener Usuario
        lUsuario = query.getSingleResult();
        if ( lUsuario != null ) {
          usuarioGeneralDTO.setUser( getUsuarioDTO( lUsuario ) );
          lResponseWSDTO = UtilCommon.createMessageSUCCESS_WS();
          String xml = UtilCommon.toXML( usuarioGeneralDTO );
          // Se parcea el objeto en un xml
          lResponseWSDTO.setDataResponseXML( xml );
        }
      } else {
        lResponseWSDTO = UtilCommon.createMessageWRONG_PARAMETERS_WS();
      }
      
    } catch ( NoResultException nrs ) {
      lResponseWSDTO = UtilCommon.createMessageNORESULT_WS();
      lResponseWSDTO.setDataResponseXML( null );
      
    } catch ( Exception e ) {
      lResponseWSDTO = UtilCommon.createMessageFAILURE_WS();
      lResponseWSDTO.setDataResponseXML( null );
      
    }
    
    return lResponseWSDTO;
  }
  
  /**
   * </p> Obtiene el usuario dto. </p>
   *
   * @param pUsuario the p usuario
   * @return the usuario dto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (1/09/2013)
   */
  private UserDTO getUsuarioDTO ( Usuario pUsuario ) {
    UserDTO lUsuarioDTO = null;
    if ( pUsuario != null ) {
      lUsuarioDTO = new UserDTO();
      lUsuarioDTO.setDescripcion( pUsuario.getDescripcion() );
      lUsuarioDTO.setFechaCreacion( pUsuario.getFechaCreacion() );
      lUsuarioDTO.setId( pUsuario.getId() );
      lUsuarioDTO.setIdentificacion( pUsuario.getIdentificacion() );
      lUsuarioDTO.setLogin( pUsuario.getLogin() );
      lUsuarioDTO.setPassword( pUsuario.getPassword() );
      lUsuarioDTO.setUsuarioCreacion( pUsuario.getUsuarioCreacion() );
    }
    return lUsuarioDTO;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllMenuByIdPadre(int, java.lang.String, int)
   */
  public List< Menu > findAllMenuByIdPadre ( int pIdPadre, String pLogin, int pIdAplicacion ) throws ProcessException {
    TypedQuery< Menu > query = null;
    query = em.createNamedQuery( "findMenuByIdPadre", Menu.class );
    query.setParameter( "idPadre", pIdPadre );
    query.setParameter( "login", pLogin );
    query.setParameter( "aplicacion", pIdAplicacion );
    return query.getResultList();
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findMenuByLoginAndApplication(int, java.lang.String)
   */
  @Override
  public List< Menu > findMenuByLoginAndApplication ( int pIdAplicacion, String pLogin ) throws ProcessException {
    TypedQuery< Menu > query = null;
    try {
      query = em.createNamedQuery( "findMenuByLoginAndApplication", Menu.class );
      
      query.setParameter( "aplicacion", pIdAplicacion );
      query.setParameter( "login", pLogin );
      query.setParameter( "aplicacion", pIdAplicacion );
      
      return query.getResultList();
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#removerRolMenuByRol(int, int)
   */
  @Override
  public int removerRolMenuByRol ( int pIdRol, int pIdApp ) throws ProcessException {
    Query query = null;
    try {
      query = em.createNamedQuery( "RolMenu.removerAllByIdRol" );
      query.setParameter( "rol", pIdRol );
      query.setParameter( "idApp", pIdApp );
      return query.executeUpdate();
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllByIdRol(int, int)
   */
  @Override
  public List< Menu > findAllByIdRol ( int pIdRol, int pIdApp ) throws ProcessException {
    TypedQuery< RolMenu > query = null;
    List< RolMenu > listRolMenu = null;
    List< Menu > listMenu = null;
    try {
      query = em.createNamedQuery( "RolMenu.findAllByIdRol", RolMenu.class );
      query.setParameter( "rol", pIdRol );
      query.setParameter( "idApp", pIdApp );
      listRolMenu = query.getResultList();
      if ( listRolMenu != null && !listRolMenu.isEmpty() ) {
        // Obtener Menu padre
        listMenu = new ArrayList< Menu >();
        for ( RolMenu data: listRolMenu ) {
          listMenu.add( data.getTbMenu() );
        }
      }
      return listMenu;
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#removerUserRolByUser(int)
   */
  @Override
  public int removerUserRolByUser ( int pIdUser ) throws ProcessException {
    Query query = null;
    try {
      query = em.createNamedQuery( "UsuarioRol.removerAllByIdUser" );
      query.setParameter( "user", pIdUser );
      return query.executeUpdate();
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllRolByIdUser(int)
   */
  @Override
  public List< Rol > findAllRolByIdUser ( int pIdUsuario ) throws ProcessException {
    TypedQuery< UsuarioRol > query = null;
    List< UsuarioRol > listRolMenu = null;
    List< Rol > listRol = null;
    try {
      query = em.createNamedQuery( "UsuarioRol.findAllByIdUser", UsuarioRol.class );
      query.setParameter( "user", pIdUsuario );
      listRolMenu = query.getResultList();
      if ( listRolMenu != null && !listRolMenu.isEmpty() ) {
        // Obtener Menu padre
        listRol = new ArrayList< Rol >();
        for ( UsuarioRol data: listRolMenu ) {
          listRol.add( data.getTbRol() );
        }
      }
      return listRol;
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllMenu()
   */
  @Override
  public List< Menu > findAllMenu () throws ProcessException {
    TypedQuery< Menu > query = null;
    query = em.createNamedQuery( "Menu.findAllMenu", Menu.class );
    return query.getResultList();
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllAplicacionesByLogin(java.lang.String)
   */
  public List< Aplicacion > findAllAplicacionesByLogin ( String login ) throws ProcessException {
    TypedQuery< Aplicacion > query = null;
    List< Aplicacion > list = null;
    try {
      query = em.createNamedQuery( "Aplicacion.findAllAplicacionesByLogin", Aplicacion.class );
      
      query.setParameter( "login", login );
      
      list = query.getResultList();
      
      if ( list != null && !list.isEmpty() ) {
        return list;
      }
      
      return null;
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#getUserSessionCetus(java.lang.String)
   */
  public ResponseWSDTO getUserSessionCetus ( String pIdUser ) throws ProcessException {
    TypedQuery< Usuario > query = null;
    ResponseWSDTO response = null;
    Usuario usuario = null;
    UsuarioDTO lUsuarioDTO = null;
    try {
      query = em.createNamedQuery( "Usuario.getUserSessio", Usuario.class );
      query.setParameter( "id", Integer.parseInt( pIdUser ) );
      if ( query.getSingleResult() != null ) {
        usuario = query.getSingleResult();
        lUsuarioDTO = new UsuarioDTO();
        converter.convertEntityToDto( usuario, lUsuarioDTO, false );
        response = UtilCommon.createMessageSUCCESS_WS();
        response.setDataResponseXML( UtilCommon.toXML( lUsuarioDTO ) );
      }
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return response;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#validateUserApp(java.lang.String, java.lang.String)
   */
  public boolean validateUserApp ( String pLogin, String pIdApp ) throws ProcessException {
    Query query = null;
    BigInteger count = null;
    try {
      query = em.createNamedQuery( "Usuario.validateUserApp", BigInteger.class );
      query.setParameter( "login", pLogin );
      query.setParameter( "idApp", pIdApp );
      
      count = ( BigInteger ) query.getSingleResult();
      
      if ( count != null && count.intValue() > 0 ) {
        return true;
      }
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#validateUserApp(java.lang.String)
   */
  public boolean validateUserApp ( String pLogin ) throws ProcessException {
    Query query = null;
    BigInteger count = null;
    try {
      query = em.createNamedQuery( "Usuario.validateUserApp2", BigInteger.class );
      query.setParameter( "login", pLogin );
      
      count = ( BigInteger ) query.getSingleResult();
      
      if ( count != null && count.intValue() > 0 ) {
        return true;
      }
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#handleChangeApplication(int)
   */
  public < E > ResponseWSDTO handleChangeServletComboBox ( int pIdServ ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Menu > query = null;
    List< Menu > list = null;
    List< MenuDTO > listDTO = null;
    MenuDTO depDTO = null;
    try {
      query = this.em.createNamedQuery( "Menu.findMenuByIdServlet", Menu.class );
      query.setParameter( "idServ", pIdServ );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        listDTO = new ArrayList< MenuDTO >();
        list = query.getResultList();
        for ( Menu entity: list ) {
          depDTO = new MenuDTO();
          converter.convertEntityToDto( entity, depDTO, false );
          listDTO.add( depDTO );
        }
        responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
        //Establecer DTO en el XML
        responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      }
      
    } catch ( NoResultException e ) {
      listDTO = new ArrayList< MenuDTO >();
      responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
      responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      return responseWSDTO;
    } catch ( Exception e ) {
      return UtilCommon.createMessageFAILURE_WS( e );
    }
    
    return responseWSDTO;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#queryParamByAbreviature(java.lang.String)
   */
  public < E > ResponseWSDTO queryParamByAbreviature ( String pAbreviature ) throws ProcessException {
    TypedQuery< Parametro > query = null;
    ResponseWSDTO response = null;
    Parametro param = null;
    ParametroDTO lParamDTO = null;
    try {
      query = em.createNamedQuery( "Parametro.QueryParamByAbreviature", Parametro.class );
      query.setParameter( "pAbreviature", pAbreviature );
      query.setParameter( "pStatus", ConstantCommon.ACTIVO );
      
      if ( query.getSingleResult() != null ) {
        param = query.getSingleResult();
        lParamDTO = new ParametroDTO();
        converter.convertEntityToDto( param, lParamDTO, false );
        response = UtilCommon.createMessageSUCCESS_WS();
        response.setDataResponseXML( UtilCommon.toXML( lParamDTO ) );
      }
    } catch ( NoResultException e ) {
      response = UtilCommon.createMessageNORESULT_WS();
      return response;
    } catch ( Exception e ) {
      return UtilCommon.createMessageFAILURE_WS( e );
    }
    return response;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllByTypeParameter(java.lang.String)
   */
  public < E > ResponseWSDTO findAllByTypeParameter ( String pType ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Parametro > query = null;
    List< Parametro > list = null;
    List< ParametroDTO > listDTO = null;
    ParametroDTO dto = null;
    try {
      query = this.em.createNamedQuery( "Parametro.findAllByType", Parametro.class );
      query.setParameter( "type", pType );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        listDTO = new ArrayList< ParametroDTO >();
        list = query.getResultList();
        for ( Parametro entity: list ) {
          dto = new ParametroDTO();
          converter.convertEntityToDto( entity, dto, false );
          listDTO.add( dto );
        }
        responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
        //Establecer DTO en el XML
        responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      }
      
    } catch ( NoResultException e ) {
      listDTO = new ArrayList< ParametroDTO >();
      responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
      responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      return responseWSDTO;
    } catch ( Exception e ) {
      return UtilCommon.createMessageFAILURE_WS( e );
    }
    
    return responseWSDTO;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#handleChangeApplicationRolMenu(int)
   */
  public < E > ResponseWSDTO handleChangeApplicationRolMenu ( int pIdApp ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Menu > query = null;
    List< Menu > list = null;
    List< MenuDTO > listDTO = null;
    MenuDTO depDTO = null;
    try {
      query = this.em.createNamedQuery( "Menu.handleChangeApplicationRolMenu", Menu.class );
      query.setParameter( "idApp", pIdApp );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        listDTO = new ArrayList< MenuDTO >();
        list = query.getResultList();
        for ( Menu entity: list ) {
          depDTO = new MenuDTO();
          converter.convertEntityToDto( entity, depDTO, false );
          listDTO.add( depDTO );
        }
        responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
        //Establecer DTO en el XML
        responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      }
      
    } catch ( NoResultException e ) {
      listDTO = new ArrayList< MenuDTO >();
      responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
      responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      return responseWSDTO;
    } catch ( Exception e ) {
      return UtilCommon.createMessageFAILURE_WS( e );
    }
    
    return responseWSDTO;
  }
  
  /**
   * </p> Find all service by application. </p>
   *
   * @param pIdApp the p id app
   * @return el response wsdto
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (14/09/2013)
   */
  public List< Service > findAllServiceByApplication ( int pIdApp ) throws ProcessException {
    TypedQuery< Service > query = null;
    List< Service > list = null;
    try {
      query = this.em.createNamedQuery( "Service.findAllServiceByApplication", Service.class );
      query.setParameter( "idApp", pIdApp );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        list = query.getResultList();
      }
      
    } catch ( NoResultException e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
    return list;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findByLogin(java.lang.String)
   */
  public Usuario findByLogin ( String pLogin ) throws ProcessException {
    TypedQuery< Usuario > query = null;
    Usuario usuario = null;
    try {
      query = em.createNamedQuery( "Usuario.findByLogin", Usuario.class );
      query.setParameter( "login", pLogin );
      
      if ( query.getMaxResults() > 0 ) {
        usuario = query.getSingleResult();
      }
      
    } catch ( NoResultException e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
    return usuario;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#findAllComponentByApplication(int)
   */
  public List< Component > findAllComponentByApplication ( int pIdApp ) throws ProcessException {
    TypedQuery< Component > query = null;
    List< Component > list = null;
    try {
      query = this.em.createNamedQuery( "Component.findAllComponentByApplication", Component.class );
      query.setParameter( "idApp", pIdApp );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        list = query.getResultList();
      }
      
    } catch ( NoResultException e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
    return list;
  }
  
  /**
   * </p> Find all parameter by application. </p>
   *
   * @param pIdApp the p id app
   * @return el list
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public List< Parametro > findAllParameterByApplication ( int pIdApp ) throws ProcessException {
    TypedQuery< Parametro > query = null;
    List< Parametro > list = null;
    try {
      query = this.em.createNamedQuery( "Parametro.findAllParameterByApp", Parametro.class );
      query.setParameter( "idApp", pIdApp );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        list = query.getResultList();
      }
      
    } catch ( NoResultException e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
    return list;
  }
  
  /**
   * </p> Find all parameter by comp app. </p>
   *
   * @param pIdApp the p id app
   * @param idComponent the id component
   * @return el list
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public List< Parametro > findAllParameterByCompApp ( int pIdApp, int idComponent ) throws ProcessException {
    TypedQuery< Parametro > query = null;
    List< Parametro > list = null;
    try {
      query = em.createNamedQuery( "Parametro.findAllParamByCompApp", Parametro.class );
      query.setParameter( "idComponent", idComponent );
      query.setParameter( "idAppication", pIdApp );
      query.setParameter( "status", ConstantBussines.ACTIVO_STR );
      
      list = query.getResultList();
      
    } catch ( NoResultException e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    
    return list;
  }
  
  /**
   * </p> Get value parameter. </p>
   *
   * @param name the name
   * @return el string
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public String getValueParameter ( String name ) throws ProcessException {
    String value = null;
    List< Parametro > list = null;
    try {
      if ( AppConstants.parameter == null ) {
        list = findAllParameterByCompApp( AppConstants.ID_APPLICATION_CETUS, AppConstants.ID_COMPONENT_CETUS );
        AppConstants.loadParameter( list );
      }
      
      value = AppConstants.getParameter( name );
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return value;
  }
  
  /**
   * </p> Reload parameter. </p>
   *
   * @param pIdApp the p id app
   * @param idComponent the id component
   * @return el string
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public boolean reloadParameter ( int pIdApp, int idComponent ) throws ProcessException {
    List< Parametro > list = null;
    boolean result = false;
    try {
      list = findAllParameterByCompApp( pIdApp, idComponent );
      if ( list == null ) {
        list = new ArrayList< Parametro >();
      }
      result = AppConstants.loadParameter( list );
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return result;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.ejb.process.PortalProcessLocal#handleChangeApplication(int)
   */
  public < E > ResponseWSDTO handleChangeApplicationComboBoxServlet ( int pIdApp ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< AplicationServlet > query = null;
    List< AplicationServlet > list = null;
    List< AplicationServletDTO > listDTO = null;
    AplicationServletDTO objDTO = null;
    try {
      query = this.em.createNamedQuery( "AplicationServlet.findAplicationServletByIdApp", AplicationServlet.class );
      query.setParameter( "idApp", pIdApp );
      
      if ( query != null && query.getMaxResults() > 0 ) {
        listDTO = new ArrayList< AplicationServletDTO >();
        list = query.getResultList();
        for ( AplicationServlet entity: list ) {
          objDTO = new AplicationServletDTO();
          converter.convertEntityToDto( entity, objDTO, false );
          listDTO.add( objDTO );
        }
        responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
        //Establecer DTO en el XML
        responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      }
      
    } catch ( NoResultException e ) {
      listDTO = new ArrayList< AplicationServletDTO >();
      responseWSDTO = UtilCommon.createMessageSUCCESS_WS();
      responseWSDTO.setDataResponseXML( UtilCommon.toXML( listDTO ) );
      return responseWSDTO;
    } catch ( Exception e ) {
      return UtilCommon.createMessageFAILURE_WS( e );
    }
    
    return responseWSDTO;
  }
  
  /**
   * </p> Reload parameter component. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param pIdApp the p id app
   * @param idComponent the id component
   * @param nameComponent the name component
   * @return true, si el proceso fue exitoso
   * @throws ProcessException the process exception
   * @since cetus-vortal-ejb (23/03/2016)
   */
  public boolean reloadParameterComponent ( int pIdApp, int idComponent, String nameComponent ) throws ProcessException {
    List< Parametro > list = null;
    boolean result = false;
    ParameterDTO parameterDTO = null;
    List< ParameterDTO > params = null;
    try {
      list = findAllParameterByCompApp( pIdApp, idComponent );
      if ( list == null ) {
        list = new ArrayList< Parametro >();
      }
      params = new ArrayList<ParameterDTO>();
      for ( Parametro parametro: list ) {
        parameterDTO = new ParameterDTO( parametro.getAbreviatura(), parametro.getValor() );
        params.add( parameterDTO );
      }
      
      Util.CETUS_CORE.info( "pIdApp=" + pIdApp + ", idComponent=" + idComponent + ", nameComponent=" + nameComponent );
      
      if ( nameComponent != null && nameComponent.equals( AppConstants.COMPONENT_CMS ) ) {
        CetusMessageServiceDelegate delegate = new CetusMessageServiceDelegate( AppConstants.WSDL_CETUS_MESSAGE_SERVICE );
        
        ReloadParameterRequestDTO parameterRequestDTO = new ReloadParameterRequestDTO();
        parameterRequestDTO.setUser( AppConstants.USER_WS_MESSAGE_SERVICE );
        parameterRequestDTO.setPassword( AppConstants.PASSWORD_WS_MESSAGE_SERVICE );
        parameterRequestDTO.setComponent( nameComponent );
        parameterRequestDTO.setListParameter( UtilCommon.toXML( params ) );
        
        co.com.cetus.messageservice.ejb.service.ResponseWSDTO responseWSDTO = delegate.reloadParameter( parameterRequestDTO );
        
        Util.CETUS_CORE.info( "responseWSDTO :: " + responseWSDTO.toString() );
        
        if ( responseWSDTO != null && responseWSDTO.getCode() != null && responseWSDTO.getType() != null
             && responseWSDTO.getCode().equals( ConstantCommon.WSResponse.CODE_ONE )
             && responseWSDTO.getType().equals( ConstantCommon.WSResponse.TYPE_SUCCESS ) ) {
          result = true;
        }
        
      } else if ( nameComponent != null && nameComponent.equals( AppConstants.COMPONENT_CETUS_CONTROL ) ) {
        CetusControlDelegate delegate = new CetusControlDelegate( AppConstants.WSDL_CETUS_CONTROL );
        
        co.com.cetus.cetuscontrol.ejb.service.ReloadParameterRequestDTO parameterRequestDTO = new co.com.cetus.cetuscontrol.ejb.service.ReloadParameterRequestDTO();
        parameterRequestDTO.setUser( AppConstants.USER_WS_MESSAGE_SERVICE );
        parameterRequestDTO.setPassword( AppConstants.PASSWORD_WS_MESSAGE_SERVICE );
        parameterRequestDTO.setComponent( nameComponent );
        parameterRequestDTO.setListParameter( UtilCommon.toXML( params ) );
        
        co.com.cetus.cetuscontrol.ejb.service.ResponseWSDTO responseWSDTO = delegate.reloadParameter( parameterRequestDTO );
        Util.CETUS_CORE.info( "responseWSDTO :: " + responseWSDTO.toString() );
        
        if ( responseWSDTO != null && responseWSDTO.getCode() != null && responseWSDTO.getType() != null
             && responseWSDTO.getCode().equals( ConstantCommon.WSResponse.CODE_ONE )
             && responseWSDTO.getType().equals( ConstantCommon.WSResponse.TYPE_SUCCESS ) ) {
          result = true;
        }
      } else {
        Util.CETUS_CORE.info( "El componente no esta configurado para la recarga de parametros" );
      }
      
    } catch ( Exception e ) {
      throw new ProcessException( e.getMessage(),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.PORTAL_PROCESS ), null );
    }
    return result;
  }
  
}
