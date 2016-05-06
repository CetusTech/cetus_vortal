package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.UtilCommon;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.business.dto.AplicationServletDTO;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.AplicationServlet;
import co.com.cetus.vortal.jpa.entity.FilterSearch;
import co.com.cetus.vortal.jpa.entity.GeneralSearch;
import co.com.cetus.vortal.jpa.entity.Servlet;

/**
 * The Class GeneralSearchMBean.
 *
 * @author Jose David Salcedo M. - Cetus Technology
 * @version cetus-vortal-war (25/04/2016)
 */
@ManagedBean
@RequestScoped
public class GeneralSearchMBean extends GeneralManagedBean {
  
  private static final long            serialVersionUID = 1L;
  /*
   * Lista de Atributos del CRUD
   */
  private GeneralSearch                addObject;
  private GeneralSearch                selectedObject;
  private List< GeneralSearch >        listRegister;
  private List< AplicationServletDTO > listAppSer;
  private List< Aplicacion >           listApplication;
  private boolean                      status;
  private int                          idApplication;
  private int                          idAppSer;
  private List< Integer >              listOptionSearch;
                                       
  private FilterSearch                 filter1          = null;
  private FilterSearch                 filter2          = null;
  private FilterSearch                 filter3          = null;
  private FilterSearch                 filter4          = null;
  private FilterSearch                 filter5          = null;
  private FilterSearch                 filter6          = null;
  private FilterSearch                 filter7          = null;
  private FilterSearch                 filter8          = null;
  private FilterSearch                 filter9          = null;
  private FilterSearch                 filter10         = null;
                                                        
  private boolean                      showConfirmAdd   = false;
  private boolean                      showConfirmUpd   = false;
                                                        
  public GeneralSearchMBean () {
    addObject = new GeneralSearch();
    selectedObject = new GeneralSearch();
    selectedObject.setAppSerId( new AplicationServlet() );
    selectedObject.getAppSerId().setTbAplicacion( new Aplicacion() );
    selectedObject.getAppSerId().setTbServlet( new Servlet() );
    
    filter1 = new FilterSearch( "filter1" );
    filter2 = new FilterSearch( "filter2" );
    filter3 = new FilterSearch( "filter3" );
    filter4 = new FilterSearch( "filter4" );
    filter5 = new FilterSearch( "filter5" );
    filter6 = new FilterSearch( "filter6" );
    filter7 = new FilterSearch( "filter7" );
    filter8 = new FilterSearch( "filter8" );
    filter9 = new FilterSearch( "filter9" );
    filter10 = new FilterSearch( "filter10" );
    
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      
      this.listRegister = this.delegate.findAllOrder( GeneralSearch.class, ConstantView.ColumnaEntityProperties.DESCRIPTION_PROPERTIES_NEGOCIO,
                                                      ConstantCommon.TIPO_ASC );
                                                      
      this.listApplication = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                                         ConstantCommon.TIPO_ASC );
                                                         
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR,
                       Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Load selected. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @since cetus-vortal-war (3/05/2016)
   */
  public void loadSelected () {
    List< FilterSearch > listFilter = null;
    try {
      if ( selectedObject != null && selectedObject.getId() > 0 ) {
        idApplication = selectedObject.getAppSerId().getTbAplicacion().getId();
        idAppSer = selectedObject.getAppSerId().getId();
        addObjectSession( idApplication, "idApplication" );
        addObjectSession( idAppSer, "idAppSer" );
        addObjectSession( this.selectedObject, "selectedObject" );
        
        changeAplication();
        changeServlet();
        
        listFilter = this.delegate.findFilterByGenSearch( selectedObject.getId() );
        if ( listFilter != null && listFilter.size() > 0 ) {
          for ( FilterSearch filterSearch: listFilter ) {
            if ( filterSearch.getFilter().equals( "filter1" ) ) {
              filter1 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter2" ) ) {
              filter2 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter3" ) ) {
              filter3 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter4" ) ) {
              filter4 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter5" ) ) {
              filter5 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter6" ) ) {
              filter6 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter7" ) ) {
              filter7 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter8" ) ) {
              filter8 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter9" ) ) {
              filter9 = filterSearch;
              continue;
            }
            if ( filterSearch.getFilter().equals( "filter10" ) ) {
              filter10 = filterSearch;
              continue;
            }
          }
          addObjectSession( filter1, "filter1" );
          addObjectSession( filter2, "filter2" );
          addObjectSession( filter3, "filter3" );
          addObjectSession( filter4, "filter4" );
          addObjectSession( filter5, "filter5" );
          addObjectSession( filter6, "filter6" );
          addObjectSession( filter7, "filter7" );
          addObjectSession( filter8, "filter8" );
          addObjectSession( filter9, "filter9" );
          addObjectSession( filter10, "filter10" );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelectedDelete () {
    try {
      if ( selectedObject != null && selectedObject.getId() > 0 ) {
        addObjectSession( this.selectedObject, "selectedObject" );
        
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Adds the. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @return el string
   * @since cetus-vortal-war (3/05/2016)
   */
  @Override
  public String add () {
    int idGeneralSearch = 0;
    try {
      addObject = ( GeneralSearch ) getObjectSession( "addObject" );
      if ( addObject != null && !UtilCommon.isNullOrEmptyString( addObject.getDescription() ) ) {
        addObject.setUserCreation( getUsuarioCreacion() );
        addObject.setDateCreation( new Date() );
        addObject.setStatus( 1 );
        addObject.setAppSerId( new AplicationServlet() );
        addObject.getAppSerId().setId( ( int ) getObjectSession( "idAppSer" ) );
        addObject = this.delegate.create( addObject );
        if ( addObject != null && addObject.getId() > 0 ) {
          idGeneralSearch = addObject.getId();
          
          Util.CETUS_WAR.info( "Inicia la insercion de los filtros..." );
          Util.CETUS_WAR.info( "idGeneralSearch:" + idGeneralSearch );
          
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter1" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter2" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter3" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter4" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter5" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter6" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter7" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter8" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter9" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter10" ), idGeneralSearch );
          
          Util.CETUS_WAR.info( "Finaliza la insercion de los filtros..." );
          
          this.initElement();
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ),
                          ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ),
                         ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  /**
   * </p> Filter add upd del. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @param filterSearch the filter search
   * @param idGeneralSearch the id general search
   * @since cetus-vortal-war (2/05/2016)
   */
  private void filterAddUpdDel ( FilterSearch filterSearch, int idGeneralSearch ) {
    try {
      filterSearch.setUserCreation( getUsuarioCreacion() );
      filterSearch.setDateCreation( new Date() );
      
      if ( filterSearch.getId() == 0 ) {
        if ( !UtilCommon.isNullOrEmptyString( filterSearch.getLabel() ) ) {
          Util.CETUS_WAR.info( "Insertando el filtro [" + filterSearch.getLabel() + "] para el idGeneralSearch:" + idGeneralSearch );
          filterSearch.setGeneralSearch( new GeneralSearch() );
          filterSearch.getGeneralSearch().setId( idGeneralSearch );
          
          filterSearch = this.delegate.create( filterSearch );
          if ( filterSearch.getId() > 0 ) {
            Util.CETUS_WAR.info( "Filtro insertado exitosamente con id = " + filterSearch.getId() );
          } else {
            Util.CETUS_WAR.error( "Error al insertar el filtro = " + filterSearch.getLabel() );
          }
        }
      } else {
        if ( !UtilCommon.isNullOrEmptyString( filterSearch.getLabel() ) ) {
          Util.CETUS_WAR.info( "Actualizando el filtro [" + filterSearch.getLabel() + "] para el idGeneralSearch:" + idGeneralSearch );
          
          if ( this.delegate.edit( filterSearch ) ) {
            Util.CETUS_WAR.info( "Filtro con id = " + filterSearch.getId() + ", actualizado exitosamente" );
          } else {
            Util.CETUS_WAR.error( "Error al actualizar el filtro con id = " + filterSearch.getId() );
          }
          
        } else {
          Util.CETUS_WAR.info( "Eliminando el filtro [" + filterSearch.getId() + "] para el idGeneralSearch:" + idGeneralSearch );
          if ( this.delegate.remove( filterSearch ) ) {
            Util.CETUS_WAR.info( "Filtro con id = " + filterSearch.getId() + ", eliminado exitosamente" );
          } else {
            Util.CETUS_WAR.error( "Error al eliminar el filtro con id = " + filterSearch.getId() );
          }
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadAdd () {
    try {
      if ( addObject != null && !UtilCommon.isNullOrEmptyString( addObject.getDescription() ) && idAppSer > 0 ) {
        if ( UtilCommon.isNullOrEmptyString( filter1.getLabel() ) && UtilCommon.isNullOrEmptyString( filter2.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter3.getLabel() ) && UtilCommon.isNullOrEmptyString( filter4.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter5.getLabel() ) && UtilCommon.isNullOrEmptyString( filter6.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter7.getLabel() ) && UtilCommon.isNullOrEmptyString( filter8.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter9.getLabel() ) && UtilCommon.isNullOrEmptyString( filter10.getLabel() ) ) {
          addMessageError( "msgAdd", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_NO_EXISTS_FILTER ), null );
          showConfirmAdd = false;
        } else {
          addObjectSession( addObject, "addObject" );
          addObjectSession( filter1, "filter1" );
          addObjectSession( filter2, "filter2" );
          addObjectSession( filter3, "filter3" );
          addObjectSession( filter4, "filter4" );
          addObjectSession( filter5, "filter5" );
          addObjectSession( filter6, "filter6" );
          addObjectSession( filter7, "filter7" );
          addObjectSession( filter8, "filter8" );
          addObjectSession( filter9, "filter9" );
          addObjectSession( filter10, "filter10" );
          
          showConfirmAdd = true;
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( GeneralSearch ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ) );
        }
      } else {
        addMessageError( null, ConstantView.ERROR,
                         Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ) );
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR,
                       Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  @Override
  public String update () {
    int idGeneralSearch = 0;
    try {
      
      selectedObject = ( GeneralSearch ) getObjectSession( "selectedObject" );
      if ( selectedObject != null && !UtilCommon.isNullOrEmptyString( selectedObject.getDescription() ) ) {
        selectedObject.setUserUpdate( getUsuarioCreacion() );
        selectedObject.setDateUpdate( new Date() );
        selectedObject.setStatus( 1 );
        selectedObject.setAppSerId( new AplicationServlet() );
        selectedObject.getAppSerId().setId( ( int ) getObjectSession( "idAppSer" ) );
        if ( delegate.edit( selectedObject ) ) {
          idGeneralSearch = selectedObject.getId();
          
          Util.CETUS_WAR.info( "Inicia la actualizacion de los filtros..." );
          Util.CETUS_WAR.info( "idGeneralSearch:" + idGeneralSearch );
          
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter1" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter2" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter3" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter4" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter5" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter6" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter7" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter8" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter9" ), idGeneralSearch );
          filterAddUpdDel( ( FilterSearch ) getObjectSession( "filter10" ), idGeneralSearch );
          
          Util.CETUS_WAR.info( "Finaliza la actualizacion de los filtros..." );
          
          this.initElement();
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ),
                          ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ),
                         ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR,
                       Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  @SuppressWarnings ( "unchecked" )
  public void changeAplication () {
    ResponseWSDTO response = null;
    try {
      addObjectSession( idApplication, "idApplication" );
      if ( idApplication > 0 ) {
        response = delegate.handleChangeApplicationComboBoxServlet( idApplication );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listAppSer = ( List< AplicationServletDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
        }
        
        addObjectSession( listAppSer, "listAppSer" );
      } else {
        cleanObjectSession( "listAppSer" );
        cleanObjectSession( "listOptionSearch" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Change servlet. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @since cetus-vortal-war (3/05/2016)
   */
  public void changeServlet () {
    List< Integer > list = new ArrayList< Integer >();
    try {
      addObjectSession( idAppSer, "idAppSer" );
      if ( idAppSer > 0 ) {
        for ( GeneralSearch generalSearch: listRegister ) {
          if ( generalSearch.getAppSerId().getId() == idAppSer ) {
            list.add( generalSearch.getOptionSearch() );
          }
        }
        
        listOptionSearch = new ArrayList< Integer >();
        
        for ( int i = 1; i <= 5; i++ ) {
          listOptionSearch.add( i );
        }
        
        listOptionSearch.removeAll( list );
        
        selectedObject = ( GeneralSearch ) getObjectSession( "selectedObject" );
        if ( selectedObject != null && selectedObject.getId() > 0 ) {
          listOptionSearch.add( selectedObject.getOptionSearch() );
        }
        
        addObjectSession( listOptionSearch, "listOptionSearch" );
        
      } else {
        cleanObjectSession( "listOptionSearch" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Load update. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @since cetus-vortal-war (3/05/2016)
   */
  public void loadUpdate () {
    try {
      if ( selectedObject != null && !UtilCommon.isNullOrEmptyString( selectedObject.getDescription() ) && idAppSer > 0 ) {
        if ( UtilCommon.isNullOrEmptyString( filter1.getLabel() ) && UtilCommon.isNullOrEmptyString( filter2.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter3.getLabel() ) && UtilCommon.isNullOrEmptyString( filter4.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter5.getLabel() ) && UtilCommon.isNullOrEmptyString( filter6.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter7.getLabel() ) && UtilCommon.isNullOrEmptyString( filter8.getLabel() )
             && UtilCommon.isNullOrEmptyString( filter9.getLabel() ) && UtilCommon.isNullOrEmptyString( filter10.getLabel() ) ) {
          addMessageError( "msgUpd", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_NO_EXISTS_FILTER ), null );
          showConfirmUpd = false;
        } else {
          addObjectSession( selectedObject, "selectedObject" );
          addObjectSession( filter1, "filter1" );
          addObjectSession( filter2, "filter2" );
          addObjectSession( filter3, "filter3" );
          addObjectSession( filter4, "filter4" );
          addObjectSession( filter5, "filter5" );
          addObjectSession( filter6, "filter6" );
          addObjectSession( filter7, "filter7" );
          addObjectSession( filter8, "filter8" );
          addObjectSession( filter9, "filter9" );
          addObjectSession( filter10, "filter10" );
          
          showConfirmUpd = true;
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  public GeneralSearch getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( GeneralSearch addObject ) {
    this.addObject = addObject;
  }
  
  public GeneralSearch getSelectedObject () {
    GeneralSearch temp = ( GeneralSearch ) getObjectSession( "selectedObject" );
    if ( temp != null ) {
      selectedObject = temp;
    }
    return selectedObject;
  }
  
  public void setSelectedObject ( GeneralSearch selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< GeneralSearch > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< GeneralSearch > listRegister ) {
    this.listRegister = listRegister;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public boolean isStatus () {
    return status;
  }
  
  public void setStatus ( boolean status ) {
    this.status = status;
  }
  
  public FilterSearch getFilter1 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter1" );
    if ( temp != null ) {
      filter1 = temp;
    }
    return filter1;
  }
  
  public void setFilter1 ( FilterSearch filter1 ) {
    this.filter1 = filter1;
  }
  
  public FilterSearch getFilter2 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter2" );
    if ( temp != null ) {
      filter2 = temp;
    }
    return filter2;
  }
  
  public void setFilter2 ( FilterSearch filter2 ) {
    this.filter2 = filter2;
  }
  
  public FilterSearch getFilter3 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter3" );
    if ( temp != null ) {
      filter3 = temp;
    }
    return filter3;
  }
  
  public void setFilter3 ( FilterSearch filter3 ) {
    this.filter3 = filter3;
  }
  
  public FilterSearch getFilter4 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter4" );
    if ( temp != null ) {
      filter4 = temp;
    }
    return filter4;
  }
  
  public void setFilter4 ( FilterSearch filter4 ) {
    this.filter4 = filter4;
  }
  
  public FilterSearch getFilter5 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter5" );
    if ( temp != null ) {
      filter5 = temp;
    }
    return filter5;
  }
  
  public void setFilter5 ( FilterSearch filter5 ) {
    this.filter5 = filter5;
  }
  
  public FilterSearch getFilter6 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter6" );
    if ( temp != null ) {
      filter6 = temp;
    }
    return filter6;
  }
  
  public void setFilter6 ( FilterSearch filter6 ) {
    this.filter6 = filter6;
  }
  
  public FilterSearch getFilter7 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter7" );
    if ( temp != null ) {
      filter7 = temp;
    }
    return filter7;
  }
  
  public void setFilter7 ( FilterSearch filter7 ) {
    this.filter7 = filter7;
  }
  
  public FilterSearch getFilter8 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter8" );
    if ( temp != null ) {
      filter8 = temp;
    }
    return filter8;
  }
  
  public void setFilter8 ( FilterSearch filter8 ) {
    this.filter8 = filter8;
  }
  
  public FilterSearch getFilter9 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter9" );
    if ( temp != null ) {
      filter9 = temp;
    }
    return filter9;
  }
  
  public void setFilter9 ( FilterSearch filter9 ) {
    this.filter9 = filter9;
  }
  
  public FilterSearch getFilter10 () {
    FilterSearch temp = ( FilterSearch ) getObjectSession( "filter10" );
    if ( temp != null ) {
      filter10 = temp;
    }
    return filter10;
  }
  
  public void setFilter10 ( FilterSearch filter10 ) {
    this.filter10 = filter10;
  }
  
  public List< Aplicacion > getListApplication () {
    return listApplication;
  }
  
  public void setListApplication ( List< Aplicacion > listApplication ) {
    this.listApplication = listApplication;
  }
  
  public int getIdApplication () {
    idApplication = getObjectSession( "idApplication" ) != null ? ( int ) getObjectSession( "idApplication" ) : 0;
    return idApplication;
  }
  
  public void setIdApplication ( int idApplication ) {
    this.idApplication = idApplication;
  }
  
  public int getIdAppSer () {
    idAppSer = getObjectSession( "idAppSer" ) != null ? ( int ) getObjectSession( "idAppSer" ) : 0;
    return idAppSer;
  }
  
  public void setIdAppSer ( int idAppSer ) {
    this.idAppSer = idAppSer;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< AplicationServletDTO > getListAppSer () {
    listAppSer = ( List< AplicationServletDTO > ) getObjectSession( "listAppSer" );
    if ( listAppSer == null ) {
      listAppSer = new ArrayList< AplicationServletDTO >();
    }
    return listAppSer;
  }
  
  public void setListAppSer ( List< AplicationServletDTO > listAppSer ) {
    this.listAppSer = listAppSer;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< Integer > getListOptionSearch () {
    listOptionSearch = ( List< Integer > ) getObjectSession( "listOptionSearch" );
    if ( listOptionSearch == null ) {
      listOptionSearch = new ArrayList< Integer >();
    }
    return listOptionSearch;
  }
  
  public void setListOptionSearch ( List< Integer > listOptionSearch ) {
    this.listOptionSearch = listOptionSearch;
  }
  
  public boolean isShowConfirmAdd () {
    return showConfirmAdd;
  }
  
  public void setShowConfirmAdd ( boolean showConfirmAdd ) {
    this.showConfirmAdd = showConfirmAdd;
  }
  
  public boolean isShowConfirmUpd () {
    return showConfirmUpd;
  }
  
  public void setShowConfirmUpd ( boolean showConfirmUpd ) {
    this.showConfirmUpd = showConfirmUpd;
  }
  
}
