/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.cetus.servicie.facade;

import java.net.URL;

import javax.xml.namespace.QName;

import co.com.cetus.portal.ejb.service.CreateUserRequestDTO;
import co.com.cetus.portal.ejb.service.CreateUserResponseDTO;
import co.com.cetus.portal.ejb.service.CustomerService;
import co.com.cetus.portal.ejb.service.CustomerServiceService;
import co.com.cetus.portal.ejb.service.DeleteUserRequestDTO;
import co.com.cetus.portal.ejb.service.FindParameterRequestDTO;
import co.com.cetus.portal.ejb.service.FindParameterResponseDTO;
import co.com.cetus.portal.ejb.service.FindRolsByApplicationRequestDTO;
import co.com.cetus.portal.ejb.service.FindRolsByApplicationResponseDTO;
import co.com.cetus.portal.ejb.service.FindRolsByLoginRequestDTO;
import co.com.cetus.portal.ejb.service.FindRolsByLoginResponseDTO;
import co.com.cetus.portal.ejb.service.ResponseWSDTO;
import co.com.cetus.portal.ejb.service.UpdateUserRequestDTO;
import co.com.cetus.portal.ejb.service.ValidPermServiceRequestDTO;

/**
 * The Class CustomerServiceFacade.
 *
 * @author Andres Herrera - Cetus Technology
 * @version CetusCoreClienteWS (22/08/2013)
 */
public class CustomerServiceFacade {
  
  /** The url services agarthi. */
  private URL                    urlServicesAgarthi;
  
  /** The service. */
  private CustomerServiceService service                      = null;
  
  /** The port. */
  private CustomerService        port                         = null;
  
  /** The Constant CETUS_CUSTOMER_SERVICE_QNAME. */
  private final static QName     CETUS_CUSTOMER_SERVICE_QNAME = new QName( "http://service.ejb.portal.cetus.com.co/", "CustomerServiceService" );
  
  /**
   * </p> Instancia un nuevo customer service facade. </p>
   *
   * @param pUrlServicesAgarthi the p url services agarthi
   * @author Andres Herrera - Cetus Technology
   * @since CetusCoreClienteWS (22/08/2013)
   */
  public CustomerServiceFacade ( URL pUrlServicesAgarthi ) {
    try {
      urlServicesAgarthi = pUrlServicesAgarthi;
      if ( urlServicesAgarthi != null ) {
        service = new CustomerServiceService( urlServicesAgarthi, CETUS_CUSTOMER_SERVICE_QNAME );
      } else {
        //WSDL por defecto
        service = new CustomerServiceService();
      }
      port = service.getCustomerServicePort();
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * </p> Creates the user. </p>
   *
   * @param userDTO the user dto
   * @param classDTO the class dto
   * @param user the user
   * @param password the password
   * @return el response wsdto
   * @author Andres Herrera - Cetus Technology
   * @since CetusCoreClienteWS (22/08/2013)
   */
  public CreateUserResponseDTO createUser ( CreateUserRequestDTO request ) {
    return port.createUser( request );
    
  }
  
  /**
   * </p> Delete user. </p>
   *
   * @param request the request
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since CetusCoreClienteWS (29/09/2013)
   */
  public ResponseWSDTO deleteUser ( DeleteUserRequestDTO request ) {
    return port.deleteUser( request );
    
  }
  
  /**
   * Update user.
   *
   * @param userDTO the user dto
   * @param classDTO the class dto
   * @param user the user
   * @param password the password
   * @return the response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since CetusCoreClienteWS (2/09/2013)
   */
  public ResponseWSDTO updateUser ( UpdateUserRequestDTO update ) {
    return port.updateUser( update );
  }
  
  /**
   * </p> Find parameter. </p>
   *
   * @param findParameterRequestDTO the find parameter request dto
   * @return el find parameter response dto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since CetusCoreClienteWS (29/09/2013)
   */
  public FindParameterResponseDTO findParameter ( FindParameterRequestDTO findParameterRequestDTO ) {
    return port.findParameter( findParameterRequestDTO );
  }
  
  /**
   * </p> Valid permission service. </p>
   *
   * @param validPermServiceRequestDTO the valid perm service request dto
   * @return el response wsdto
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since CetusCoreClienteWS (29/09/2013)
   */
  public ResponseWSDTO validPermissionService ( ValidPermServiceRequestDTO validPermServiceRequestDTO ) {
    return port.validPermissionService( validPermServiceRequestDTO );
  }
  
  /**
   * </p> Find rol by application. </p>
   *
   * @param findRolsByApplicationRequestDTO the find rols by application request dto
   * @return el find rols by application response dto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since CetusCoreClienteWS (6/10/2013)
   */
  public FindRolsByApplicationResponseDTO findRolByApplication ( FindRolsByApplicationRequestDTO findRolsByApplicationRequestDTO ) {
    return port.findRolByApplication( findRolsByApplicationRequestDTO );
  }
  
  /**
   * </p> Find rol by login. </p>
   *
   * @param findRolsByLoginRequestDTO the find rols by login request dto
   * @return el find rols by login response dto
   * @author Andres Herrera Hdez - Cetus Technology
   * @since CetusCoreClienteWS (18/10/2013)
   */
  public FindRolsByLoginResponseDTO findRolByLogin ( FindRolsByLoginRequestDTO findRolsByLoginRequestDTO ) {
    return port.findRolByLogin( findRolsByLoginRequestDTO );
  }
}
