package co.com.cetus.portal.ejb.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Entity;

import co.com.cetus.portal.ejb.process.GeneralProcessLocal;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.common.util.ConstantCommon;

import co.com.cetus.common.validation.UniqueConstraint;
import co.com.cetus.common.validation.UniqueConstraints;
import co.com.cetus.common.validation.exception.UniqueViolationException;

public class UniqueValidator {
  @EJB
  private GeneralProcessLocal generalProcess;
  
  public UniqueValidator ( GeneralProcessLocal generalProcess ) {
    this.generalProcess = generalProcess;
  }
  
  public void validate ( Object obj ) throws UniqueViolationException {
    if ( isEntity( obj ) ) {
      UniqueConstraints uniqueConstraints = obj.getClass().getAnnotation(
                                                                          UniqueConstraints.class );
      if ( uniqueConstraints != null ) {
        UniqueConstraint[] constraints = uniqueConstraints
                                                          .uniqueConstraints();
        if ( constraints != null && constraints.length > 0 ) {
          for ( UniqueConstraint constraint: constraints ) {
            validateConstraint( constraint, obj );
          }
        }
      }
    }
  }
  
  private boolean isEntity ( Object obj ) {
    return obj != null
           && obj.getClass().getAnnotation( Entity.class ) != null;
  }
  
  private void validateConstraint ( UniqueConstraint constraint, Object obj )
                                                                             throws UniqueViolationException {
    String[] fields = constraint.fields();
    ArrayList< AttributeDTO > atributos = new ArrayList< AttributeDTO >();
    for ( String field: fields ) {
      AttributeDTO atributo = this.getValueForField( field, obj );
      if ( atributo != null ) {
        atributos.add( atributo );
      }
    }
    @SuppressWarnings ( "rawtypes" )
    List entities = null;
    try {
      entities = generalProcess.findAllFilter( obj.getClass(), atributos,
                                               ConstantCommon.CONJUNCION );
    } catch ( ProcessException e ) {
      Util.CETUS_CORE.error( e.getMessage(), e );
    }
    if ( entities != null && entities.size() > 0 ) {
      UniqueViolationException uve = new UniqueViolationException(
                                                                   constraint.name(), constraint.errorMessage(),
                                                                   constraint.fields() );
      throw uve;
    }
  }
  
  private AttributeDTO getValueForField ( String fieldName, Object obj ) {
    AttributeDTO attribute = null;
    if ( fieldName != null ) {
      String get = "get" + Character.toUpperCase( fieldName.charAt( 0 ) )
                   + fieldName.substring( 1 );
      try {
        Method method = obj.getClass().getMethod( get );
        if ( method != null ) {
          Object value = method.invoke( obj );
          attribute = new AttributeDTO( fieldName, value );
        }
      } catch ( SecurityException e ) {
        e.printStackTrace();
      } catch ( NoSuchMethodException e ) {
        e.printStackTrace();
      } catch ( IllegalArgumentException e ) {
        e.printStackTrace();
      } catch ( IllegalAccessException e ) {
        e.printStackTrace();
      } catch ( InvocationTargetException e ) {
        e.printStackTrace();
      }
    }
    return attribute;
  }
}
