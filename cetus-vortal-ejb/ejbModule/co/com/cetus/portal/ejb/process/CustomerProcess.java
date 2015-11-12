package co.com.cetus.portal.ejb.process;

import static co.com.cetus.common.util.UtilCommon.createMessageFAILURE_WS;
import static co.com.cetus.common.util.UtilCommon.createMessageNORESULT_WS;
import static co.com.cetus.common.util.UtilCommon.createMessageSUCCESS_WS;
import static co.com.cetus.common.util.UtilCommon.createMessageWRONG_PARAMETERS_WS;
import static co.com.cetus.portal.ejb.validator.CetusValidator.findParameterRequestDTONull;
import static co.com.cetus.portal.ejb.validator.CetusValidator.findRolLoginRequestDTONull;
import static co.com.cetus.portal.ejb.validator.CetusValidator.findRolRequestDTONull;
import static co.com.cetus.portal.ejb.validator.CetusValidator.idApplicationRequired;
import static co.com.cetus.portal.ejb.validator.CetusValidator.loginNullEmpty;
import static co.com.cetus.portal.ejb.validator.CetusValidator.nameComponentNullEmpty;
import static co.com.cetus.portal.ejb.validator.CetusValidator.passwordNullEmpty;
import static co.com.cetus.portal.ejb.validator.CetusValidator.serviceNullEmpty;
import static co.com.cetus.portal.ejb.validator.CetusValidator.userNullEmpty;
import static co.com.cetus.portal.ejb.validator.CetusValidator.usuarioDTONull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.com.cetus.common.dto.ParameterDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ValidatorException;
import co.com.cetus.common.mail.SendMail;
import co.com.cetus.common.util.Converter;
import co.com.cetus.common.util.UtilCommon;
import co.com.cetus.portal.ejb.util.ConstantBussines;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.portal.ejb.validator.CetusValidator;
import co.com.cetus.vortal.business.dto.RolDTO;
import co.com.cetus.vortal.business.dto.UserDTO;
import co.com.cetus.vortal.business.dto.UsuarioDTO;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Permission;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.vortal.jpa.entity.UsuarioRol;
import co.com.cetus.vortal.ws.dto.CreateUserRequestDTO;
import co.com.cetus.vortal.ws.dto.CreateUserResponseDTO;
import co.com.cetus.vortal.ws.dto.DeleteUserRequestDTO;
import co.com.cetus.vortal.ws.dto.FindParameterRequestDTO;
import co.com.cetus.vortal.ws.dto.FindParameterResponseDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByApplicationRequestDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByApplicationResponseDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByLoginRequestDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByLoginResponseDTO;
import co.com.cetus.vortal.ws.dto.UpdateUserRequestDTO;
import co.com.cetus.vortal.ws.dto.ValidPermServiceRequestDTO;

/**
 * Session Bean implementation class CustomerProcess.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-ejb (24/08/2013)
 */
@Singleton
@Lock ( LockType.READ )
public class CustomerProcess {
  
  /** The general process. */
  @EJB ( name = "cetus-vortal-ear/GeneralProcess/local" )
  private GeneralProcessLocal generalProcess;
  
  /** The portal process. */
  @EJB ( name = "cetus-vortal-ear/PortalProcess/local" )
  private PortalProcessLocal  portalProcess;
  
  @PersistenceContext ( unitName = "cetus-vortal-jpa" )
  private EntityManager       em;
  
  /** The converter. */
  private Converter           converter;
  
  /**
   * </p> Inits the. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (24/08/2013)
   */
  @PostConstruct
  public void init () {
    converter = new Converter( ConstantBussines.Internalizacion.PCK_CLASS_DTO, ConstantBussines.Internalizacion.PCK_CLASS_JPA );
  }
  
  /**
   * </p> Creates the user. </p>
   *
   * @param createUserRequestDTO the create user request dto
   * @return el response wsdto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (24/08/2013)
   */
  @SuppressWarnings ( "unchecked" )
  public CreateUserResponseDTO createUser ( CreateUserRequestDTO createUserRequestDTO ) {
    
    Usuario lEntityT = null;
    UserDTO userDto = null;
    Usuario usuario = null;
    String passwordTemp;
    CreateUserResponseDTO response = null;
    UsuarioRol usuarioRol = null;
    Rol rol = null;
    String roles = null;
    List< String > lista = null;
    try {
      response = new CreateUserResponseDTO();
      //con el idapp se debe validar que esta aplicacion tenga permiso de consumir el servicio de CREARUSUARIO
      Util.CETUS_CORE.info( "getUsuarioDTO ::> " + createUserRequestDTO.getUsuarioDTO() );
      usuarioDTONull( createUserRequestDTO.getUsuarioDTO() );
      userDto = createUserRequestDTO.getUsuarioDTO();
      passwordTemp = generatePassword( userDto );
      userDto.setPassword( UtilCommon.encriptarClave( passwordTemp ) );
      usuario = new Usuario();
      usuario.setAddress( userDto.getAddress() );
      usuario.setDescripcion( userDto.getDescripcion() );
      usuario.setEmail( userDto.getEmail() );
      usuario.setFechaCreacion( new Date() );
      usuario.setIdentificacion( userDto.getIdentificacion() );
      usuario.setLogin( userDto.getLogin() );
      usuario.setPhone( userDto.getPhone() );
      usuario.setPassword( userDto.getPassword() );
      usuario.setUsuarioCreacion( userDto.getUsuarioCreacion() );
      //converter.convertDtoToEntity( userDto, usuario );
      usuario.setStatus( ConstantBussines.ACTIVO );
      lEntityT = generalProcess.create( usuario );
      if ( lEntityT != null ) {
        if ( createUserRequestDTO.getRol() != null ) {
          roles = createUserRequestDTO.getRol();
          
          if ( roles != null ) {
            lista = ( List< String > ) UtilCommon.fromXML( roles );
          }
          
          for ( String item: lista ) {
            usuarioRol = new UsuarioRol();
            rol = new Rol();
            rol.setId( Integer.parseInt( item ) );
            usuarioRol.setTbUsuario( lEntityT );
            usuarioRol.setTbRol( rol );
            generalProcess.create( usuarioRol );
            Util.CETUS_CORE.info( "Usuario rol creado exitosamente " );
          }
        }
        
        response.setResponse( createMessageSUCCESS_WS() );
        converter.convertEntityToDto( lEntityT, userDto, false );
        response.setUsuarioDTO( userDto );
        try {
          Util.CETUS_CORE.info( "PREPARADO PARA ENVIAR EL MAIL AL " + userDto.getEmail() );
          this.sendEmail( userDto.getLogin(), passwordTemp, userDto.getEmail(), ConstantBussines.OP_CREATE );
        } catch ( Exception e ) {
          e.printStackTrace();
        }
      } else {
        response = new CreateUserResponseDTO();
        response.setResponse( createMessageFAILURE_WS() );
        return response;
      }
    } catch ( ValidatorException ve ) {
      response.setResponse( createMessageWRONG_PARAMETERS_WS() );
      response.getResponse().setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      response.setResponse( createMessageFAILURE_WS() );
      response.getResponse().setMessage( response.getResponse().getMessage() + "-" + e.getMessage() );
    }
    return response;
  }
  
  /**
   * </p> Delete user. </p>
   *
   * @param deleteUserRequestDTO the delete user request dto
   * @return el response wsdto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (24/08/2013)
   */
  public ResponseWSDTO deleteUser ( DeleteUserRequestDTO deleteUserRequestDTO ) {
    ResponseWSDTO lResponseWSDTO = null;
    UsuarioDTO userDto = null;
    Usuario usuario = null;
    try {
      if ( deleteUserRequestDTO.getIdUsuario() == 0 ) throw new ValidatorException( "El Id del usuario a eliminar es obligatorio.",
                                                                                    CetusValidator.class.getSimpleName() );
      
      loginNullEmpty( deleteUserRequestDTO.getLogin() );
      usuario = new Usuario();
      usuario.setId( deleteUserRequestDTO.getIdUsuario() );
      usuario.setLogin( deleteUserRequestDTO.getLogin() );
      usuario = portalProcess.findByLogin( usuario.getLogin() );
      if ( usuario != null ) {
        converter.convertDtoToEntity( userDto, usuario );
        //Eliminar los roles a la fecha y hacer lo que diga la lista de roles si trae insertar y si no no hace nada
        portalProcess.removerUserRolByUser( usuario.getId() );
        generalProcess.remove( usuario );
        lResponseWSDTO = createMessageSUCCESS_WS();
        this.sendEmail( usuario.getLogin(), null, usuario.getEmail(), ConstantBussines.OP_DELETE );
      } else {
        lResponseWSDTO = createMessageNORESULT_WS();
      }
    } catch ( ValidatorException ve ) {
      lResponseWSDTO = createMessageWRONG_PARAMETERS_WS();
      lResponseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      lResponseWSDTO = createMessageFAILURE_WS();
      lResponseWSDTO.setMessage( lResponseWSDTO.getMessage() + "-" + e.getMessage() );
    }
    return lResponseWSDTO;
  }
  
  /**
   * </p> Update user. </p>
   *
   * @param updateUserRequestDTO the update user request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (22/09/2013)
   */
  @SuppressWarnings ( "unchecked" )
  public ResponseWSDTO updateUser ( UpdateUserRequestDTO updateUserRequestDTO ) {
    ResponseWSDTO lResponseWSDTO = null;
    UserDTO userDto = null;
    Usuario usuario = null;
    Usuario usuarioTemp = null;
    UsuarioRol usuarioRol = null;
    Rol rol = null;
    String roles = null;
    List< String > lista = null;
    try {
      CetusValidator.usuarioDTONull( updateUserRequestDTO.getUsuarioDTO() );
      userDto = updateUserRequestDTO.getUsuarioDTO();
      usuario = new Usuario();
      usuario.setAddress( userDto.getAddress() );
      usuario.setDescripcion( userDto.getDescripcion() );
      usuario.setEmail( userDto.getEmail() );
      usuario.setFechaCreacion( new Date() );
      usuario.setIdentificacion( userDto.getIdentificacion() );
      usuario.setLogin( userDto.getLogin() );
      usuario.setPhone( userDto.getPhone() );
      usuario.setPassword( userDto.getPassword() );
      usuario.setUsuarioCreacion( userDto.getUsuarioCreacion() );
      usuario.setStatus( userDto.getStatus() );
      usuarioTemp = portalProcess.findByLogin( userDto.getLoginOld() );
      usuario.setId( usuarioTemp.getId() );
      usuario.setPassword( usuarioTemp.getPassword() );
      usuarioTemp = null;
      generalProcess.edit( usuario );
      //Eliminar los roles a la fecha y hacer lo que diga la lista de roles si trae insertar y si no no hace nada
      portalProcess.removerUserRolByUser( usuario.getId() );
      if ( updateUserRequestDTO.getRol() != null && !updateUserRequestDTO.getRol().isEmpty() ) {
        roles = updateUserRequestDTO.getRol();
        if ( roles != null ) {
          lista = ( List< String > ) UtilCommon.fromXML( roles );
        }
        for ( String item: lista ) {
          usuarioRol = new UsuarioRol();
          rol = new Rol();
          rol.setId( Integer.parseInt( item ) );
          usuarioRol.setTbUsuario( usuario );
          usuarioRol.setTbRol( rol );
          generalProcess.create( usuarioRol );
          Util.CETUS_CORE.info( "Usuario rol creado exitosamente " );
        }
      }
      
      lResponseWSDTO = createMessageSUCCESS_WS();
      this.sendEmail( userDto.getLogin(), null, userDto.getEmail(), ConstantBussines.OP_UPDATE );
      
    } catch ( ValidatorException ve ) {
      lResponseWSDTO = createMessageWRONG_PARAMETERS_WS();
      lResponseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      lResponseWSDTO = createMessageFAILURE_WS();
      lResponseWSDTO.setMessage( lResponseWSDTO.getMessage() + "-" + e.getMessage() );
    }
    return lResponseWSDTO;
  }
  
  /**
   * </p> Generate password. </p>
   *
   * @param pDto the p dto
   * @return el string
   * @throws Exception the exception
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (24/08/2013)
   */
  public String generatePassword ( UserDTO pDto ) throws Exception {
    String password = null;
    int entero = 0;
    try {
      if ( pDto != null ) {
        entero = ( int ) ( Math.random() * 1000 );
        password = pDto.getLogin() + entero;
      }
    } catch ( Exception e ) {
      throw e;
    }
    return password;
  }
  
  /**
   * </p> Send email. </p>
   *
   * @param login the login
   * @param password the password
   * @param email the email
   * @param subject the subject
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (24/08/2013)
   */
  private void sendEmail ( String login, String password, String email, int typeOp ) {
    String lParamPORT = null;
    String lParamHOST = null;
    String lParamPASS = null;
    String lParamFROM = null;
    String lParamUSER = null;
    String lParamSUBJECT = null;
    String lParamHTML = null;
    SendMail sendMail = null;
    String[] arg = null;
    String msgHtml = null;
    try {
      arg = new String[1];
      lParamPORT = portalProcess.getValueParameter( ConstantBussines.Parameter.SMPT_PORT );
      lParamHOST = portalProcess.getValueParameter( ConstantBussines.Parameter.SMTP_HOST );
      lParamPASS = portalProcess.getValueParameter( ConstantBussines.Parameter.SMTP_PASS );
      lParamFROM = portalProcess.getValueParameter( ConstantBussines.Parameter.SMTP_FROM );
      lParamUSER = portalProcess.getValueParameter( ConstantBussines.Parameter.SMTP_USERNAME );
      
      switch ( typeOp ) {
        case 1:
          //CREATE USER
          lParamSUBJECT = portalProcess.getValueParameter( ConstantBussines.Parameter.SUBJECT_CREATE_USER );
          lParamHTML = portalProcess.getValueParameter( ConstantBussines.Parameter.HTML_EMAIL_NEW_USER );
          if ( lParamHTML != null && !lParamHTML.isEmpty() && lParamHTML.contains( "{0}" ) && lParamHTML.contains( "{1}" ) ) {
            msgHtml = lParamHTML.replace( "{0}", login ).replace( "{1}", password );
          } else {
            Util.CETUS_CORE.info( "El mensaje HTML no contiene los parametros necesarios {0} y {1}" );
          }
          break;
        case 2:
          //UPDATE USER
          lParamSUBJECT = portalProcess.getValueParameter( ConstantBussines.Parameter.SUBJECT_UPDATE_USER );
          lParamHTML = portalProcess.getValueParameter( ConstantBussines.Parameter.HTML_EMAIL_UPDATE_USER );
          
          if ( lParamHTML != null && !lParamHTML.isEmpty() && lParamHTML.contains( "{0}" ) ) {
            msgHtml = lParamHTML.replace( "{0}", login );
          } else {
            Util.CETUS_CORE.info( "El mensaje HTML no contiene los parametros necesarios {0}" );
          }
          break;
        case 3:
          //DELETE USER
          lParamSUBJECT = portalProcess.getValueParameter( ConstantBussines.Parameter.SUBJECT_DELETE_USER );
          lParamHTML = portalProcess.getValueParameter( ConstantBussines.Parameter.HTML_EMAIL_DELETE_USER );
          
          if ( lParamHTML != null && !lParamHTML.isEmpty() && lParamHTML.contains( "{0}" ) ) {
            msgHtml = lParamHTML.replace( "{0}", login );
          } else {
            Util.CETUS_CORE.info( "El mensaje HTML no contiene los parametros necesarios {0}" );
          }
          break;
        
        default:
          break;
      }
      
      if ( lParamFROM != null && lParamHOST != null && lParamPORT != null && lParamUSER != null && lParamPASS != null && msgHtml != null ) {
        arg[0] = email;
        sendMail = new SendMail( lParamFROM, lParamHOST, lParamPORT, lParamUSER, lParamPASS, lParamSUBJECT, msgHtml, arg, null );
        sendMail.start();
      } else {
        Util.CETUS_CORE.info( "No se pudo enviar el correo por falta de parametros. Por favor revisar la entrada al proceso" );
      }
      
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "[UsuarioManagedBean.sendEmail]" + e.getMessage(), e );
    }
  }
  
  /**
   * Servicio para validar si un usuario tiene permiso para ejecutar el servicio enviado.
   *
   * @param <T> the generic type
   * @param validPermServiceRequestDTO the valid perm service request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (01/09/2013)
   */
  public ResponseWSDTO validPermissionService ( ValidPermServiceRequestDTO validPermServiceRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Permission > query = null;
    Permission permission = null;
    try {
      userNullEmpty( validPermServiceRequestDTO.getUser() );
      passwordNullEmpty( validPermServiceRequestDTO.getPassword() );
      serviceNullEmpty( validPermServiceRequestDTO.getService() );
      em.flush();
      query = em.createNamedQuery( "Permission.validPermissionService", Permission.class );
      query.setParameter( "user", validPermServiceRequestDTO.getUser() );
      query.setParameter( "password", validPermServiceRequestDTO.getPassword() );
      query.setParameter( "service", validPermServiceRequestDTO.getService() );
      query.setParameter( "application", validPermServiceRequestDTO.getApplication() );
      
      try {
        permission = query.getSingleResult();
        if ( permission != null ) {
          responseWSDTO = createMessageSUCCESS_WS();
        }
      } catch ( NoResultException e ) {
        responseWSDTO = createMessageNORESULT_WS();
      }
    } catch ( ValidatorException ve ) {
      responseWSDTO = createMessageWRONG_PARAMETERS_WS();
      responseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseWSDTO = createMessageFAILURE_WS();
      responseWSDTO.setMessage( responseWSDTO.getMessage() + "-" + e.getMessage() );
    }
    return responseWSDTO;
  }
  
  /**
   * </p> Find parameter. </p>
   *
   * @param findParameterRequestDTO the find parameter request dto
   * @return el find parameter response dto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (29/09/2013)
   */
  public FindParameterResponseDTO findParameter ( FindParameterRequestDTO findParameterRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Parametro > query = null;
    List< Parametro > list = null;
    FindParameterResponseDTO findResponseDTO = new FindParameterResponseDTO();
    List< ParameterDTO > params = null;
    ParameterDTO parameterDTO = null;
    try {
      findParameterRequestDTONull( findParameterRequestDTO );
      nameComponentNullEmpty( findParameterRequestDTO.getNameComponent() );
      idApplicationRequired( findParameterRequestDTO.getIdApplication() );
      
      Util.CETUS_CORE.info( "findParameter [NameComponent=" + findParameterRequestDTO.getNameComponent() + ", IdApplication="
                            + findParameterRequestDTO.getIdApplication() + "]" );
      
      query = em.createNamedQuery( "Parametro.QueryParamByComponentApp", Parametro.class );
      query.setParameter( "nameComponent", findParameterRequestDTO.getNameComponent() );
      query.setParameter( "idAppication", findParameterRequestDTO.getIdApplication() );
      query.setParameter( "status", ConstantBussines.ACTIVO_STR );
      
      list = query.getResultList();
      if ( list != null && list.size() > 0 ) {
        params = new ArrayList< ParameterDTO >();
        Util.CETUS_CORE.debug( "LISTA DE PARAMETROS PARA EL COMPONENTE [" + findParameterRequestDTO.getNameComponent() + "] APLICACION ["
                               + findParameterRequestDTO.getIdApplication() + "]" );
        for ( Parametro parametro: list ) {
          parameterDTO = new ParameterDTO( parametro.getAbreviatura(), parametro.getValor() );
          Util.CETUS_CORE.debug( parameterDTO.toString() );
          params.add( parameterDTO );
        }
        responseWSDTO = createMessageSUCCESS_WS();
        findResponseDTO.setParameters( params );
      } else {
        responseWSDTO = createMessageNORESULT_WS();
      }
      
    } catch ( ValidatorException ve ) {
      responseWSDTO = createMessageWRONG_PARAMETERS_WS();
      responseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseWSDTO = createMessageFAILURE_WS();
    }
    findResponseDTO.setResponseWSDTO( responseWSDTO );
    return findResponseDTO;
  }
  
  /**
   * </p> Find rol by application. </p>
   *
   * @param findRolsByApplicationRequestDTO the find rols by application request dto
   * @return el find rols by application response dto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public FindRolsByApplicationResponseDTO findRolByApplication ( FindRolsByApplicationRequestDTO findRolsByApplicationRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Rol > query = null;
    List< Rol > list = null;
    FindRolsByApplicationResponseDTO findResponseDTO = new FindRolsByApplicationResponseDTO();
    List< RolDTO > listDTO = null;
    RolDTO dto = null;
    try {
      findRolRequestDTONull( findRolsByApplicationRequestDTO );
      idApplicationRequired( findRolsByApplicationRequestDTO.getIdApplication() );
      
      query = em.createNamedQuery( "Usuario.findRolByApplication", Rol.class );
      query.setParameter( "idAppication", findRolsByApplicationRequestDTO.getIdApplication() );
      
      list = query.getResultList();
      if ( list != null && list.size() > 0 ) {
        listDTO = new ArrayList< RolDTO >();
        Util.CETUS_CORE.debug( "LISTA DE ROLES PARA EL COMPONENTE LA APLICACION ["
                               + findRolsByApplicationRequestDTO.getIdApplication() + "]" );
        for ( Rol item: list ) {
          dto = new RolDTO();
          converter.convertEntityToDto( item, dto, false );
          Util.CETUS_CORE.debug( dto.toString() );
          listDTO.add( dto );
        }
        responseWSDTO = createMessageSUCCESS_WS();
        findResponseDTO.setRol( listDTO );
      } else {
        responseWSDTO = createMessageNORESULT_WS();
      }
      
    } catch ( ValidatorException ve ) {
      responseWSDTO = createMessageWRONG_PARAMETERS_WS();
      responseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseWSDTO = createMessageFAILURE_WS();
    }
    findResponseDTO.setResponseWSDTO( responseWSDTO );
    return findResponseDTO;
  }
  
  public FindRolsByLoginResponseDTO findRolByLogin ( FindRolsByLoginRequestDTO findRolsByLoginRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    TypedQuery< Rol > query = null;
    List< Rol > list = null;
    FindRolsByLoginResponseDTO responseDTO = new FindRolsByLoginResponseDTO();
    List< RolDTO > listDTO = null;
    RolDTO dto = null;
    try {
      findRolLoginRequestDTONull( findRolsByLoginRequestDTO );
      loginNullEmpty( findRolsByLoginRequestDTO.getLogin() );
      
      query = em.createNamedQuery( "Usuario.findRolByLogin", Rol.class );
      query.setParameter( "login", findRolsByLoginRequestDTO.getLogin() );
      
      list = query.getResultList();
      if ( list != null && list.size() > 0 ) {
        listDTO = new ArrayList< RolDTO >();
        Util.CETUS_CORE.debug( "LISTA DE ROLES PARA EL COMPONENTE LA APLICACION ["
                               + findRolsByLoginRequestDTO.getLogin() + "]" );
        for ( Rol item: list ) {
          dto = new RolDTO();
          converter.convertEntityToDto( item, dto, false );
          Util.CETUS_CORE.debug( dto.toString() );
          listDTO.add( dto );
        }
        responseWSDTO = createMessageSUCCESS_WS();
        responseDTO.setRol( listDTO );
      } else {
        responseWSDTO = createMessageNORESULT_WS();
      }
      
    } catch ( ValidatorException ve ) {
      responseWSDTO = createMessageWRONG_PARAMETERS_WS();
      responseWSDTO.setMessage( ve.getMessage() );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseWSDTO = createMessageFAILURE_WS();
    }
    responseDTO.setResponseWSDTO( responseWSDTO );
    return responseDTO;
  }
  
}
