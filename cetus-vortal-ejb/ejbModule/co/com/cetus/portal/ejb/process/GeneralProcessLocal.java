package co.com.cetus.portal.ejb.process;

import java.util.List;

import javax.ejb.Local;

import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.exception.ProcessException;

/**
 * The Interface GeneralProcessLocal.
 * 
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version Cetus-ejb (19/11/2011)
 */
@Local
public interface GeneralProcessLocal {
  
  /**
   * <p> Creates the. </p>
   *
   * @param <T> the generic type
   * @param o the o
   * @return el t
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > T create( T o ) throws ProcessException;
  
  /**
   * <p> Removes the. </p>
   *
   * @param <T> the generic type
   * @param o the o
   * @return el boolean
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > Boolean remove( T o ) throws ProcessException;
  
  /**
   * <p> Find all. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @return el list
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > List<T> findAll( Class<T> pEntityClass ) throws ProcessException;
  
  /**
   * <p> Edits the. </p>
   *
   * @param <T> the generic type
   * @param o the o
   * @return el boolean
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > Boolean edit( T o ) throws ProcessException;
  
  /**
   * <p> Find. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param o the o
   * @return el t
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > T find( Class<T> pEntityClass, Object o ) throws ProcessException;
  
  /**
   * <p> Count. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @return el integer
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > Integer count( Class<T> pEntityClass ) throws ProcessException;
  
  /**
   * <p> Find all filter. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param atributos the atributos
   * @param pIsAndOr the p is and or
   * @return el list
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > List<T> findAllFilter( Class<T> pEntityClass, List<AttributeDTO> atributos, String pIsAndOr ) throws ProcessException;
  
  /**
   * <p> Find all. </p>
   *
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param pFindOrder the p find order
   * @param pTipyOrder the p tipy order
   * @return el list
   * @throws ProcessException the process exception
   * @author Andres Herrera - Cetus Technology
   * @since Cetus-ejb (19/11/2011)
   */
  public < T > List<T> findAll( Class<T> pEntityClass, String pFindOrder, String pTipyOrder ) throws ProcessException;
  
  /**
   * <p> Find range. </p>
   * 
   * @param <T> the generic type
   * @param pEntityClass the p entity class
   * @param range the range
   * @return el list
   * @throws ProcessException the process exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  public < T > List<T> findRange( Class<T> pEntityClass, int[] range ) throws ProcessException;
  
}
