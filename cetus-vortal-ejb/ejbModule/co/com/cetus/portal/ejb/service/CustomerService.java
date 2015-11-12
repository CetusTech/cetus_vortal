package co.com.cetus.portal.ejb.service;

import static co.com.cetus.common.util.UtilCommon.createMessageFAILURE_WS;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import co.com.cetus.portal.ejb.interceptor.InterceptorCetusService;
import co.com.cetus.portal.ejb.interceptor.InterceptorCreateUser;
import co.com.cetus.portal.ejb.interceptor.InterceptorFindParameter;
import co.com.cetus.portal.ejb.interceptor.InterceptorFindRolByApplication;
import co.com.cetus.portal.ejb.interceptor.InterceptorFindRolByLogin;
import co.com.cetus.portal.ejb.process.CustomerProcess;
import co.com.cetus.portal.ejb.util.Util;
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
import co.com.cetus.common.dto.ResponseWSDTO;

/**
 * Session Bean implementation class CustomerService
 */
@Stateless
@WebService
public class CustomerService {
  @EJB
  private CustomerProcess customerProcess;
  
  /**
   * </p> Create user. </p>
   *
   * @param createUserRequestDTO the create user request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (22/09/2013)
   */
  @Interceptors ( InterceptorCreateUser.class )
  @WebMethod
  public @WebResult ( name = "createUserResponseDTO" ) CreateUserResponseDTO
      createUser ( @WebParam ( name = "createUserRequestDTO" ) CreateUserRequestDTO createUserRequestDTO ) {
    return this.customerProcess.createUser( createUserRequestDTO );
  }
  
  /**
   * </p> Delete user. </p>
   *
   * @param deleteUserRequestDTO the delete user request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (22/09/2013)
   */
  @Interceptors ( InterceptorCetusService.class )
  @WebMethod
  public @WebResult ( name = "responseWSDTO" ) ResponseWSDTO
      deleteUser ( @WebParam ( name = "deleteUserRequestDTO" ) DeleteUserRequestDTO deleteUserRequestDTO ) {
    return this.customerProcess.deleteUser( deleteUserRequestDTO );
  }
  
  /**
   * </p> Update user. </p>
   *
   * @param updateUserRequestDTO the update user request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (22/09/2013)
   */
  @Interceptors ( InterceptorCetusService.class )
  @WebMethod
  public @WebResult ( name = "responseWSDTO" ) ResponseWSDTO
      updateUser ( @WebParam ( name = "updateUserRequestDTO" ) UpdateUserRequestDTO updateUserRequestDTO ) {
    return this.customerProcess.updateUser( updateUserRequestDTO );
  }
  
  /**
   * Valid permission service.
   *
   * @param validPermServiceRequestDTO the valid perm service request dto
   * @return the response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (01/09/2013)
   */
  @WebMethod
  public @WebResult ( name = "responseWSDTO" ) ResponseWSDTO
      validPermissionService ( @WebParam ( name = "validPermServiceRequestDTO" ) ValidPermServiceRequestDTO validPermServiceRequestDTO ) {
    return this.customerProcess.validPermissionService( validPermServiceRequestDTO );
  }
  
  /**
   * </p> Find parameter. </p>
   *
   * @param findParameterRequestDTO the find parameter request dto
   * @return el find parameter response dto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (29/09/2013)
   */
  @Interceptors ( InterceptorFindParameter.class )
  @WebMethod
  public @WebResult ( name = "findParameterResponseDTO" ) FindParameterResponseDTO
      findParameter ( @WebParam ( name = "findParameterRequestDTO" ) FindParameterRequestDTO findParameterRequestDTO ) {
    FindParameterResponseDTO responseDTO = new FindParameterResponseDTO();
    try {
      responseDTO = customerProcess.findParameter( findParameterRequestDTO );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseDTO.setResponseWSDTO( createMessageFAILURE_WS() );
    }
    return responseDTO;
  }
  
  @Interceptors ( InterceptorFindRolByApplication.class )
  @WebMethod
  public @WebResult ( name = "findRolsByApplicationResponseDTO" ) FindRolsByApplicationResponseDTO
      findRolByApplication ( @WebParam ( name = "findRolsByApplicationRequestDTO" ) FindRolsByApplicationRequestDTO findRolsByApplicationRequestDTO ) {
    FindRolsByApplicationResponseDTO responseDTO = new FindRolsByApplicationResponseDTO();
    try {
      responseDTO = customerProcess.findRolByApplication( findRolsByApplicationRequestDTO );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseDTO.setResponseWSDTO( createMessageFAILURE_WS() );
    }
    return responseDTO;
  }
  
  @Interceptors ( InterceptorFindRolByLogin.class )
  @WebMethod
  public @WebResult ( name = "findRolsByLoginResponseDTO" ) FindRolsByLoginResponseDTO
      findRolByLogin ( @WebParam ( name = "findRolsByLoginRequestDTO" ) FindRolsByLoginRequestDTO findRolsByLoginRequestDTO ) {
    FindRolsByLoginResponseDTO responseDTO = new FindRolsByLoginResponseDTO();
    try {
      responseDTO = customerProcess.findRolByLogin( findRolsByLoginRequestDTO );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      responseDTO.setResponseWSDTO( createMessageFAILURE_WS() );
    }
    return responseDTO;
  }
}
