package co.com.cetus.portal.ejb.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.util.ConstantCommon;

public class Util {
  
  public static Logger CETUS_CORE          = Logger.getLogger( "CetusVortalLog" );
  
  /**
   * <p> Obtiene el property. </p>
   *
   * @param nameProperties the name properties
   * @param nameProperty the name property
   * @return the property
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (8/12/2011)
   */
  public static String getProperty ( String nameProperties, String nameProperty ) {
    ResourceBundle rb = null;
    String valueProperty = null;
    try {
      rb = ResourceBundle.getBundle( nameProperties );
      if ( rb != null ) {
        valueProperty = rb.getString( nameProperty );
      }
    } catch ( Exception e ) {
      try {
        throw new Exception( "Error obteniendo la propiedad " + nameProperty + " del properties " + nameProperties );
      } catch ( Exception e1 ) {
        e1.printStackTrace();
      }
    }
    return valueProperty;
  }
  
  /**
   * <p> String null or empty. </p>
   *
   * @param string the string
   * @return true, si el proceso fue exitoso
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (15/01/2012)
   */
  public static boolean stringNullOrEmpty ( String string ) {
    if ( string == null ) return true;
    if ( string.isEmpty() ) return true;
    return false;
  }
  
  public static boolean validateResponseSuccess ( ResponseWSDTO pResponse ) {
    if ( pResponse != null && pResponse.getCode() != null && !pResponse.getCode().isEmpty() && pResponse.getCode() != null && !pResponse.getCode().isEmpty()
         && pResponse.getCode().equals( ConstantCommon.WSResponse.CODE_ONE )
         && pResponse.getType().equals( ConstantCommon.WSResponse.TYPE_SUCCESS ) ) {
      return true;
    }
    return false;
  }
  
  /**
   * </p> Validate response no result. </p>
   *
   * @param pResponse the p response
   * @return true, si el proceso fue exitoso
   * @author Andres Herrera - Cetus Technology
   * @since AgarthiWeb (19/02/2013)
   */
  public static boolean validateResponseNoResult ( ResponseWSDTO pResponse ) {
    if ( pResponse != null && pResponse.getCode() != null && !pResponse.getCode().isEmpty() && pResponse.getCode() != null && !pResponse.getCode().isEmpty()
         && pResponse.getCode().equals( ConstantCommon.WSResponse.CODE_ZERO )
         && pResponse.getType().equals( ConstantCommon.WSResponse.TYPE_NORESULT ) ) {
      return true;
    }
    return false;
  }
  
  /**
   * </p> Validate response success xml output. </p>
   *
   * @param pResponse the p response
   * @return true, si el proceso fue exitoso
   * @author Andres Herrera - Cetus Technology
   * @since AgarthiWeb (19/02/2013)
   */
  public static boolean validateResponseSuccessXMLOutput ( ResponseWSDTO pResponse ) {
    if ( pResponse != null && pResponse.getCode() != null && !pResponse.getCode().isEmpty() && pResponse.getCode() != null && !pResponse.getCode().isEmpty()
         && pResponse.getCode().equals( ConstantCommon.WSResponse.CODE_ONE )
         && pResponse.getType().equals( ConstantCommon.WSResponse.TYPE_SUCCESS ) && pResponse.getDataResponseXML() != null && !pResponse.getDataResponseXML().isEmpty() ) {
      return true;
    }
    return false;
  }
}
