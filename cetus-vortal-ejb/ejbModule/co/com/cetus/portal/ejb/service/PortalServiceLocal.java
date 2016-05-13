/*
 * 
 */
package co.com.cetus.portal.ejb.service;

import java.util.List;

import javax.ejb.Local;

import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.FilterSearch;
import co.com.cetus.vortal.jpa.entity.GeneralSearch;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.common.exception.ServiceException;

/**
 * The Interface CetusServiceLocal.
 * 
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version Cetus-ejb (19/11/2011)
 */
@Local
public interface PortalServiceLocal {
  
  /**
   * <p>
   * M�todo utilizado para crear un registro en una entidad determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param obj
   *            the obj
   * @return el t
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > T create ( T obj ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para eliminar un registro de una entidad determianda.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param obj
   *            the obj
   * @return el boolean
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > Boolean remove ( T obj ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para listar todos los registros de una entidad
   * determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @return el list
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > List< T > findAll ( Class< T > pEntityClass ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para modificar un registro de una entidad determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param obj
   *            the obj
   * @return el boolean
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > Boolean edit ( T obj ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para consultar un registro por id de una entidad
   * determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @param obj
   *            the obj
   * @return el t
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > T find ( Class< T > pEntityClass, Object obj ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para contar los registros de una entidad determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @return el integer
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > Integer count ( Class< T > pEntityClass ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para listar los registros de una entidad determinada por
   * filtro y ordenada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @param atributos
   *            the atributos
   * @param pIsAndOr
   *            the p is and or
   * @return el list
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > List< T > findAllFilter ( Class< T > pEntityClass, List< AttributeDTO > atributos, String pIsAndOr ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para listar los registros de una entidad determinada
   * ordenada por una columna especifica.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @param pFindOrder
   *            the p find order
   * @param pTipyOrder
   *            the p tipy order
   * @return el list
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > List< T > findAllOrder ( Class< T > pEntityClass, String pFindOrder, String pTipyOrder ) throws ServiceException;
  
  /**
   * <p>
   * M�todo utilizado para listar un rango de registros de una entidad
   * determinada.
   * </p>
   * 
   * @param <T>
   *            the generic type
   * @param pEntityClass
   *            the p entity class
   * @param range
   *            the range
   * @return el list
   * @throws ServiceException
   *             the service exception
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since Cetus-ejb (20/11/2011)
   */
  < T > List< T > findRange ( Class< T > pEntityClass, int[] range ) throws ServiceException;
  
  public boolean validateUserApp ( String pLogin, String pIdApp ) throws ServiceException;
  
  public boolean validateUserApp2 ( String pLogin ) throws ServiceException;
  
  public < E > ResponseWSDTO handleChangeServletComboBox ( int pIdApp ) throws ServiceException;
  
  public List< Service > findAllServiceByApplication ( int pIdApp ) throws ServiceException;
  
  public List< Component > findAllComponentByApplication ( int pIdApp ) throws ServiceException;
  
  public List< Parametro > findAllParameterByApplication ( int pIdApp ) throws ServiceException;
  
  public List< Parametro > findAllParameterByCompApp ( int pIdApp, int idComponent ) throws ServiceException;
  
  public String getValueParameter ( String name ) throws ServiceException;
  
  public boolean reloadParameter ( int pIdApp, int idComponent ) throws ServiceException;
  
  public boolean reloadParameterComponent ( int pIdApp, int idComponent, String nameComponent ) throws ServiceException;
  
  public boolean createUser ( Usuario user ) throws ServiceException;
  
  public List< FilterSearch > findFilterByGenSearch ( int idGeneralSearch ) throws ServiceException;
  
  public List< GeneralSearch > findGenSearchByApp ( int idApp ) throws ServiceException;
}
