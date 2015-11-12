package co.com.cetus.portal.ejb.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.ConstraintViolationException;

import co.com.cetus.portal.ejb.util.ConstantBussines;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.portal.ejb.validator.UniqueValidator;
import co.com.cetus.common.dto.AttributeDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.validation.exception.UniqueViolationException;

@Stateless
public class GeneralProcess implements GeneralProcessLocal {
  
  @PersistenceContext ( unitName = "cetus-vortal-jpa" )
  private EntityManager em;
  
  public < T > T create ( T entity ) throws ProcessException {
    try {
      UniqueValidator uniqueValidator = new UniqueValidator( this );
      uniqueValidator.validate( entity );
      em.persist( entity );
      em.flush();
      return entity;
    } catch ( ConstraintViolationException cv ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          cv.getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.CREATE );
    } catch ( UniqueViolationException uve ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          uve.getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.CREATE );
    } catch ( PersistenceException p ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          p.getCause().getCause().getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.CREATE );
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          e.getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.CREATE );
      
    }
  }
  
  public < T > Boolean edit ( T entity ) throws ProcessException {
    try {
      em.merge( entity );
      em.flush();
      return true;
    } catch ( ConstraintViolationException cv ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", cv.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                            ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.EDIT );
    } catch ( PersistenceException p ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", p.getCause().getCause().getMessage() ),
                                  Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.EDIT );
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.EDIT );
      
    }
  }
  
  public < T > Boolean remove ( T entity ) throws ProcessException {
    try {
      em.remove( em.merge( entity ) );
      em.flush();
      return true;
    } catch ( PersistenceException p ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.error_eliminando_registro )
                                      .replace( "{0}", "" ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                               ConstantBussines.Internalizacion.GENERAL_PROCESS ) + ":"
                                                             + p.getCause().toString(), ConstantBussines.Operation.REMOVE );
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.REMOVE );
    }
  }
  
  public < T > T find ( Class< T > pEntityClass, Object id ) throws ProcessException {
    try {
      return em.find( pEntityClass, id );
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.FIND );
    }
  }
  
  public < T > List< T > findAll ( Class< T > pEntityClass ) throws ProcessException {
    try {
      CriteriaQuery< T > cq = em.getCriteriaBuilder().createQuery( pEntityClass );
      cq.select( cq.from( pEntityClass ) );
      return em.createQuery( cq ).getResultList();
    } catch ( PersistenceException p ) {
      throw p;
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.FIND_ALL );
    }
  }
  
  @Override
  public < T > List< T > findAll ( Class< T > pEntityClass, String pFieldOrder, String pTipyOrder ) throws ProcessException {
    try {
      Metamodel m = em.getMetamodel();
      CriteriaBuilder cb = em.getCriteriaBuilder();
      EntityType< T > entidad_ = m.entity( pEntityClass );
      CriteriaQuery< T > cq = cb.createQuery( pEntityClass );
      Root< T > root = cq.from( pEntityClass );
      cq.select( root );
      if ( pTipyOrder != null && pTipyOrder.equals( ConstantCommon.TIPO_ASC ) ) {
        cq.orderBy( cb.asc( root.get( entidad_.getSingularAttribute( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, pFieldOrder ) ) ) ) );
      } else {
        cq.orderBy( cb.desc( root.get( entidad_.getSingularAttribute( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, pFieldOrder ) ) ) ) );
      }
      return em.createQuery( cq ).getResultList();
    } catch ( PersistenceException p ) {
      throw p;
    } catch ( Exception e ) {
      e.printStackTrace();
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.FIND_ALL );
    }
  }
  
  public < T > List< T > findAllFilter ( Class< T > pEntityClass, List< AttributeDTO > atributos, String pIsAndOr ) throws ProcessException {
    try {
      Map< SingularAttribute< T, ? >, List< Object >> map = new HashMap< SingularAttribute< T, ? >, List< Object >>();
      Metamodel metamodel = em.getMetamodel();
      EntityType< T > entityType = metamodel.entity( pEntityClass );
      for ( AttributeDTO atributo: atributos ) {
        @SuppressWarnings ( "unchecked" )
        SingularAttribute< T, ? > attr = ( SingularAttribute< T, ? > ) entityType.getSingularAttribute( atributo.getNombre() );
        List< Object > valores = map.get( attr );
        if ( valores == null ) {
          valores = new ArrayList< Object >();
        }
        valores.add( atributo.getValor() );
        map.put( attr, valores );
      }
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery< T > cq = cb.createQuery( pEntityClass );
      Root< T > r = cq.from( pEntityClass );
      Predicate p = pIsAndOr != null && pIsAndOr.equals( ConstantCommon.CONJUNCION ) ? cb.conjunction() : cb.disjunction();
      if ( pIsAndOr != null ) {
        if ( pIsAndOr.equals( ConstantCommon.CONJUNCION ) ) {
          for ( Map.Entry< SingularAttribute< T, ? >, List< Object >> param: map.entrySet() ) {
            List< Object > valores = param.getValue();
            for ( Object valor: valores ) {
              p = cb.and( p, cb.equal( r.get( param.getKey() ), valor ) );
            }
          }
        } else {
          if ( pIsAndOr.equals( ConstantCommon.DISYUNCION ) ) {
            for ( Map.Entry< SingularAttribute< T, ? >, List< Object >> param: map.entrySet() ) {
              List< Object > valores = param.getValue();
              for ( Object valor: valores ) {
                p = cb.or( p, cb.equal( r.get( param.getKey() ), valor ) );
              }
            }
          }
        }
      }
      cq.select( r ).where( p );
      return em.createQuery( cq ).getResultList();
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO, ConstantBussines.Internalizacion.ERROR_EJB )
                                      .replace( "{0}", e.getMessage() ), Util.getProperty( ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                                                           ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.FIND_ALL_FILTER );
    }
  }
  
  public < T > List< T > findRange ( Class< T > pEntityClass, int[] range )
                                                                           throws ProcessException {
    try {
      CriteriaQuery< T > cq = em.getCriteriaBuilder().createQuery(
                                                                   pEntityClass );
      cq.select( cq.from( pEntityClass ) );
      TypedQuery< T > q = em.createQuery( cq );
      q.setMaxResults( range[1] - range[0] );
      q.setFirstResult( range[0] );
      return q.getResultList();
    } catch ( PersistenceException p ) {
      throw p;
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          e.getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.FIND_RANGE );
    }
  }
  
  public < T > Integer count ( Class< T > pEntityClass ) throws ProcessException {
    CriteriaQuery< Long > cq = null;
    try {
      cq = em.getCriteriaBuilder().createQuery( Long.class );
      cq.select( em.getCriteriaBuilder().count( cq.from( pEntityClass ) ) );
      return em.createQuery( cq ).getSingleResult().intValue();
    } catch ( PersistenceException p ) {
      throw p;
    } catch ( Exception e ) {
      throw new ProcessException( Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.ERROR_EJB ).replace( "{0}",
                                                                                                          e.getMessage() ),
                                  Util.getProperty(
                                                    ConstantBussines.NAME_BUNDLE_NEGOCIO,
                                                    ConstantBussines.Internalizacion.GENERAL_PROCESS ),
                                  ConstantBussines.Operation.COUNT );
    }
  }
}
