package co.com.cetus.portal.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The Class EmailValidator.
 *
 * @author Andres Herrera - Cetus Technology
 * @version cetus-vortal-war (12/08/2013)
 */
@FacesValidator ( value = "emailValidator" )
public class EmailValidator implements Validator {
  
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
                                              "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
                                              "(\\.[A-Za-z]{2,})$";
  
  private Pattern             pattern;
  
  private Matcher             matcher;
  
  /**
   * </p> Instancia un nuevo email validator. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (12/08/2013)
   */
  public EmailValidator () {
    pattern = Pattern.compile( EMAIL_PATTERN );
  }
  
  /* (non-Javadoc)
   * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
   */
  @Override
  public void validate ( FacesContext context, UIComponent component,
                         Object value ) throws ValidatorException {
    
    matcher = pattern.matcher( value.toString() );
    if ( !matcher.matches() ) {
      
      FacesMessage msg =
                         new FacesMessage( "E-mail Fallo la Validaci\u00F3n.",
                                           "Formato Invalido del Correo Electr\u00f3nico." );
      msg.setSeverity( FacesMessage.SEVERITY_ERROR );
      throw new ValidatorException( msg );
    }
  }
}
