package co.com.cetus.portal.ejb.util;

import java.util.HashMap;
import java.util.List;

import co.com.cetus.vortal.jpa.entity.Parametro;

/**
 * The Class AppConstants.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-ejb (6/10/2013)
 */
public class AppConstants {
  
  public static PropertiesLoader          properties           = PropertiesLoader.getInstance();
  
  public static int                       ID_APPLICATION_CETUS = Integer.parseInt( properties.getProperty( "ID_APPLICATION_CETUS_VORTAL" ) );
  public static int                       ID_COMPONENT_CETUS   = Integer.parseInt( properties.getProperty( "ID_COMPONENT_CETUS_VORTAL" ) );
  
  public static HashMap< String, String > parameter            = null;
  
  /**
   * </p> Load parameter. </p>
   *
   * @param list the list
   * @return true, si el proceso fue exitoso
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public static boolean loadParameter ( List< Parametro > list ) {
    boolean result = false;
    try {
      parameter = new HashMap< String, String >();
      if ( list != null ) {
        if ( list != null && list.size() > 0 ) {
          for ( Parametro parametro: list ) {
            parameter.put( parametro.getAbreviatura(), parametro.getValor() );
          }
          result = true;
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( e.getMessage(), e );
      result = false;
    }
    return result;
  }
  
  /**
   * </p> Get parameter. </p>
   *
   * @param nameParam the name param
   * @param valueDefault the value default
   * @return el string
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-ejb (6/10/2013)
   */
  public static String getParameter ( String nameParam ) {
    String valueParameter = null;
    try {
      if ( parameter != null ) {
        valueParameter = parameter.get( nameParam );
      }
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( e.getMessage(), e );
    }
    return valueParameter;
  }
  
}
