package co.com.cetus.portal.ejb.validator;

import static co.com.cetus.common.util.UtilCommon.stringNullOrEmpty;
import co.com.cetus.vortal.business.dto.UserDTO;
import co.com.cetus.vortal.business.dto.UsuarioDTO;
import co.com.cetus.vortal.ws.dto.FindParameterRequestDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByApplicationRequestDTO;
import co.com.cetus.vortal.ws.dto.FindRolsByLoginRequestDTO;
import co.com.cetus.common.exception.ValidatorException;

/**
 * The Class ManagementValidator.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version ManagementQuipusEJB (18/04/2012)
 */
public class CetusValidator {
  
  /**
   * </p> Instancia un nuevo management validator. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since ManagementQuipusEJB (18/04/2012)
   */
  public CetusValidator () {
  }
  
  public static < T > void validateDTO ( T dto ) throws ValidatorException {
    if ( dto == null ) throw new ValidatorException( "El DTO es nulo.", CetusValidator.class.getSimpleName() );
  }
  
  public static void dtoXmlNullEmpty ( String dtoXml ) throws ValidatorException {
    if ( stringNullOrEmpty( dtoXml ) ) throw new ValidatorException( "El parametro dto en xml es nulo o vacio.", CetusValidator.class.getSimpleName() );
  }
  
  public static void classDTONullEmpty ( String dtoXml ) throws ValidatorException {
    if ( stringNullOrEmpty( dtoXml ) ) throw new ValidatorException( "El parametro con el nombre del dto es nulo o vacio.",
                                                                     CetusValidator.class.getSimpleName() );
  }
  
  public static void listAttributesNullEmpty ( String listAttributes ) throws ValidatorException {
    if ( stringNullOrEmpty( listAttributes ) ) throw new ValidatorException( "El parametro dto en xml es nulo o vacio.",
                                                                             CetusValidator.class.getSimpleName() );
  }
  
  public static void userNullEmpty ( String user ) throws ValidatorException {
    if ( stringNullOrEmpty( user ) ) throw new ValidatorException( "El parametro user es nulo o vacio.", CetusValidator.class.getSimpleName() );
  }
  
  public static void passwordNullEmpty ( String password ) throws ValidatorException {
    if ( stringNullOrEmpty( password ) ) throw new ValidatorException( "El parametro password es nulo o vacio.", CetusValidator.class.getSimpleName() );
  }
  
  public static void serviceNullEmpty ( String service ) throws ValidatorException {
    if ( stringNullOrEmpty( service ) ) throw new ValidatorException( "El parametro service es nulo o vacio.", CetusValidator.class.getSimpleName() );
  }
  
  public static void usuarioDTONull ( UsuarioDTO usuarioDTO ) throws ValidatorException {
    if ( usuarioDTO == null ) throw new ValidatorException( "El objeto usuarioDTO es nulo", CetusValidator.class.getSimpleName() );
  }
  
  public static void usuarioDTONull ( UserDTO usuarioDTO ) throws ValidatorException {
    if ( usuarioDTO == null ) throw new ValidatorException( "El objeto usuarioDTO es nulo", CetusValidator.class.getSimpleName() );
  }
  
  public static void findParameterRequestDTONull ( FindParameterRequestDTO findParameterRequestDTO ) throws ValidatorException {
    if ( findParameterRequestDTO == null ) throw new ValidatorException( "El objeto findParameterRequestDTO es nulo",
                                                                         CetusValidator.class.getSimpleName() );
  }
  
  public static void nameComponentNullEmpty ( String nameComponent ) throws ValidatorException {
    if ( stringNullOrEmpty( nameComponent ) ) throw new ValidatorException( "El parametro nameComponent es nulo o vacio.",
                                                                            CetusValidator.class.getSimpleName() );
  }
  
  public static void idApplicationRequired ( int idApplication ) throws ValidatorException {
    if ( idApplication == 0 ) throw new ValidatorException( "El parametro idApplication es requerido.", CetusValidator.class.getSimpleName() );
  }
  
  public static void findRolRequestDTONull ( FindRolsByApplicationRequestDTO findRolsByApplicationRequestDTO ) throws ValidatorException {
    if ( findRolsByApplicationRequestDTO == null ) throw new ValidatorException( "El objeto findRolsByApplicationRequestDTO es nulo",
                                                                                 CetusValidator.class.getSimpleName() );
  }
  
  public static void findRolLoginRequestDTONull ( FindRolsByLoginRequestDTO findRolsByLoginRequestDTO ) throws ValidatorException {
    if ( findRolsByLoginRequestDTO == null ) throw new ValidatorException( "El objeto findRolsByLoginRequestDTO  es nulo",
                                                                           CetusValidator.class.getSimpleName() );
  }
  
  public static void loginNullEmpty ( String login ) throws ValidatorException {
    if ( stringNullOrEmpty( login ) ) throw new ValidatorException( "El Login es nulo o vacio.", CetusValidator.class.getSimpleName() );
  }
}
