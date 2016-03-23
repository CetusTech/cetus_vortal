package co.com.cetus.portal.ejb.delegate;

import java.net.MalformedURLException;
import java.net.URL;

import co.com.cetus.cetuscontrol.ejb.service.ReloadParameterRequestDTO;
import co.com.cetus.cetuscontrol.ejb.service.ResponseWSDTO;
import co.com.cetus.cetuscontrol.ws.facade.CetusControlServiceFacade;

/**
 * The Class CetusControlDelegate.
 *
 * @author Jose David Salcedo M. - Cetus Technology
 * @version cetus-vortal-ejb (23/03/2016)
 */
public class CetusControlDelegate {
  
  /** The control service. */
  private CetusControlServiceFacade controlService;
  
  /**
   * </p> Instancia un nuevo cetus control delegate. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param wsdl the wsdl
   * @since cetus-vortal-ejb (23/03/2016)
   */
  public CetusControlDelegate ( String wsdl ) {
    init( wsdl );
  }
  
  /**
   * </p> Inits the. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param wsdl the wsdl
   * @since cetus-vortal-ejb (23/03/2016)
   */
  private void init ( String wsdl ) {
    try {
      controlService = new CetusControlServiceFacade( new URL( wsdl ) );
    } catch ( MalformedURLException e ) {
    }
  }
  
  /**
   * </p> Reload parameter. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param reloadParameterRequestDTO the reload parameter request dto
   * @return el response wsdto
   * @since cetus-vortal-ejb (23/03/2016)
   */
  public ResponseWSDTO reloadParameter ( ReloadParameterRequestDTO reloadParameterRequestDTO ) {
    ResponseWSDTO responseWSDTO = null;
    try {
      responseWSDTO = controlService.reloadParameter( reloadParameterRequestDTO );
    } catch ( Exception e ) {
    }
    return responseWSDTO;
  }
  
}
