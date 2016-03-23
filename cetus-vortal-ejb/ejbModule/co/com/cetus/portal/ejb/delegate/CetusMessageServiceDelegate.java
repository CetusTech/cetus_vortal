package co.com.cetus.portal.ejb.delegate;

import java.net.MalformedURLException;
import java.net.URL;

import co.com.cetus.messageservice.ejb.service.ReloadParameterRequestDTO;
import co.com.cetus.messageservice.ejb.service.ResponseWSDTO;
import co.com.cetus.messageservice.ejb.service.SendMailRequestDTO;
import co.com.cetus.messageservice.ws.facade.CetusMessageServiceFacade;

/**
 * The Class CetusMessageServiceDelegate.
 *
 * @author Jose David Salcedo M. - Cetus Technology
 * @version CetusControlEJB (9/09/2015)
 */
public class CetusMessageServiceDelegate {
  
  /** The message service. */
  private CetusMessageServiceFacade messageService;
  
  /**
   * </p> Instancia un nuevo cetus message service delegate. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param wsdl the wsdl
   * @since CetusControlEJB (9/09/2015)
   */
  public CetusMessageServiceDelegate ( String wsdl ) {
    init( wsdl );
  }
  
  /**
   * </p> Inits the. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param wsdl the wsdl
   * @since CetusControlEJB (9/09/2015)
   */
  private void init ( String wsdl ) {
    try {
      messageService = new CetusMessageServiceFacade( new URL( wsdl ) );
    } catch ( MalformedURLException e ) {
    }
  }
  
  /**
   * </p> Send email. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param sendMailRequestDTO the send mail request dto
   * @return el response wsdto
   * @since CetusControlEJB (9/09/2015)
   */
  public ResponseWSDTO sendEmail ( SendMailRequestDTO sendMailRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    try {
      responseWSDTO = messageService.sendEmail( sendMailRequestDTO );
    } catch ( Exception e ) {
    }
    return responseWSDTO;
  }
  
  /**
   * </p> Reload parameter. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param sendMailRequestDTO the send mail request dto
   * @return el response wsdto
   * @since cetus-vortal-ejb (23/03/2016)
   */
  public ResponseWSDTO reloadParameter ( ReloadParameterRequestDTO reloadParameterRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    try {
      responseWSDTO = messageService.reloadParameter( reloadParameterRequestDTO );
    } catch ( Exception e ) {
    }
    return responseWSDTO;
  }
  
  
}
