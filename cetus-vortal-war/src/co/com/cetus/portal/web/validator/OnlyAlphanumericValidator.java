package co.com.cetus.portal.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import co.com.cetus.portal.web.util.Util;



/**
 * The Class OnlyAlphanumericValidator.
 * 
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version ManagementQuipusWAR (17/09/2012)
 */
@FacesValidator ( value = "onlyAlphanumericValidator" )
public class OnlyAlphanumericValidator implements Validator {
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.faces.validator.Validator#validate(javax.faces.context.FacesContext
   * , javax.faces.component.UIComponent, java.lang.Object)
   */
  @Override
  public void validate ( FacesContext context, UIComponent component,
                         Object object ) throws ValidatorException {
    boolean valid = false;
    try {
      if ( object != null ) {
        valid = object.toString().matches( "(\\p{Alpha})(\\p{Space})*" );
        if ( !valid ) {
          throw new ValidatorException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "El valor del campo debe ser Alfanumérico.", null ) );
        }
      }
    } catch ( ValidatorException ve ) {
      Util.CETUS_WAR.info( "Error ::> " + ve.getMessage() );
      throw new ValidatorException( new FacesMessage( FacesMessage.SEVERITY_ERROR, ve.getMessage(), null ) );
    } catch ( Exception e ) {
      Util.CETUS_WAR.info( "Error ::> " + e.getMessage(), e );
      throw new ValidatorException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Error validando el campo.", null ) );
    }
  }
  
}
