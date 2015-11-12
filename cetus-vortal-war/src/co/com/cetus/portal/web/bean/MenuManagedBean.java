package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.business.dto.AplicationServletDTO;
import co.com.cetus.vortal.business.dto.MenuDTO;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.AplicationServlet;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ServiceException;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class MenuManagedBean.
 */
@ManagedBean
@RequestScoped
public class MenuManagedBean extends GeneralManagedBean {
  
  /*
   * Lista de Atributos del CRUD
   */
  private Menu                         addObject;
  private Menu                         selectedObject;
  private List< Menu >                 listRegister;
  private List< Aplicacion >           listRegisterApplication;
  private List< SelectItem >           listSelectItemApplication;
  private List< SelectItem >           listSelectItemMenu;
  private List< SelectItem >           listSelectItemApplicationServlet;
  private List< MenuDTO >              listMenuDTO              = null;
  private List< AplicationServletDTO > listAplicationServletDTO = null;
  private int                          idMenuPadre;
  private int                          idServlet;
  
  /**
   * 
   */
  private static final long            serialVersionUID         = -814452095367034877L;
  
  public MenuManagedBean () {
    addObject = new Menu();
    addObject.setTbAplicationServlet( new AplicationServlet() );
    addObject.getTbAplicationServlet().setTbAplicacion( new Aplicacion() );
    addObject.setTbMenu( new Menu() );
    selectedObject = new Menu();
    selectedObject.setTbAplicationServlet( new AplicationServlet() );
    selectedObject.getTbAplicationServlet().setTbAplicacion( new Aplicacion() );
    selectedObject.setTbMenu( new Menu() );
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    Aplicacion appTemp = null;
    try {
      // Consultar Menus
      this.listRegister = this.delegate.findAllOrder( Menu.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      // Consultar Aplicaciones Existentes
      appTemp = new Aplicacion();
      appTemp.setId( 0 );
      appTemp.setNombre( Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_SELECT_APPLICATION ) );
      this.listRegisterApplication = new ArrayList< Aplicacion >();
      this.listRegisterApplication.add( appTemp );
      this.listRegisterApplication.addAll( this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                                                       ConstantCommon.TIPO_ASC ) );
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    Menu lMenu = null;
    try {
      addObject = ( Menu ) getObjectSession( "addObject" );
      idMenuPadre = getObjectSession( "idMenuPadre" ) == null ? 0 : ( Integer ) getObjectSession( "idMenuPadre" );
      if ( addObject != null ) {
        
        if ( idMenuPadre > 0 ) {
          lMenu = new Menu();
          Util.CETUS_WAR.info( "idMenuPadre " + idMenuPadre );
          lMenu.setId( idMenuPadre );
        } else {
          lMenu = null;
        }
        context = RequestContext.getCurrentInstance();
        addObject.setUsuarioCreacion( getUsuarioCreacion() );
        addObject.setFechaCreacion( new Date() );
        addObject.setTbMenu( lMenu );
        if ( addObject.getTbMenu() == null || ( addObject.getTbMenu() != null && addObject.getTbMenu().getId() <= 0 ) ) {
          // IS NODO RAIZ O NODO PADRE
          addObject.setTbMenu( null );
        }
        
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ) );
          context.addCallbackParam( "lSuccessfull", lSuccessfull );
          cleanObjectSession( "addObject" );
          cleanObjectSession( "listSelectItemMenu" );
          idMenuPadre = 0;
        }
      }
    } catch ( Exception e ) {
      addMessageError( "errorNegocio", ConstantView.ERROR,
                       Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ).concat( e.getMessage() ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
      context.addCallbackParam( "error", lSuccessfull );
    }
    
    return null;
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( Menu ) getObjectSession( "selectedObject" );
      
      if ( selectedObject != null ) {
        
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ) );
        }
      } else {
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ) );
      }
    } catch ( ServiceException e ) {
      addMessageError( null, e.getMessage(), e.getProcess() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ) );
    }
    return null;
  }
  
  @Override
  public String update () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    Menu lMenu = null;
    try {
      selectedObject = ( Menu ) getObjectSession( "selectedObject" );
      idMenuPadre = ( Integer ) getObjectSession( "idMenuPadre" );
      
      if ( selectedObject != null ) {
        if ( idMenuPadre > 0 ) {
          lMenu = new Menu();
          lMenu.setId( idMenuPadre );
        }
        context = RequestContext.getCurrentInstance();
        selectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        selectedObject.setFechaCreacion( new Date() );
        selectedObject.setTbMenu( lMenu );
        if ( this.delegate.edit( selectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
          context.addCallbackParam( "lSuccessfull", lSuccessfull );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
    } finally {
      cleanObjectSession( "selectedObject" );
    }
    
    return null;
  }
  
  /**
   * </p> Handle change application. Este metodo permite consultar segun la aplicacion seleccionada los servlets asociados </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (14/01/2014)
   */
  @SuppressWarnings ( "unchecked" )
  public void handleChangeServletComboBox () {
    ResponseWSDTO response = null;
    int id = 0;
    try {
      if ( addObject != null & addObject.getTbAplicationServlet() != null ) {
        id = addObject.getTbAplicationServlet().getId();
      }
      
      if ( id > 0 ) {
        response = delegate.handleChangeServletComboBox( id );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listMenuDTO = ( List< MenuDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listMenuDTO.isEmpty() ) {
            listSelectItemMenu = new ArrayList< SelectItem >();
            
            listSelectItemMenu.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_ONE_MENU_EMPTY ) ) );
            for ( MenuDTO element: listMenuDTO ) {
              listSelectItemMenu.add( new SelectItem( element.getId(), element.getNombre() ) );
            }
          }
          
          addObjectSession( listSelectItemMenu, "listSelectItemMenu" );
        }
      } else {
        cleanObjectSession( "listSelectItemMenu" );
      }
      
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  @SuppressWarnings ( "unchecked" )
  public void handleChangeApplicationComboBoxServlet () {
    ResponseWSDTO response = null;
    int id = 0;
    try {
      cleanObjectSession( "listSelectItemMenu" );
      if ( addObject != null & addObject.getTbAplicationServlet() != null && addObject.getTbAplicationServlet().getTbAplicacion() != null ) {
        id = addObject.getTbAplicationServlet().getTbAplicacion().getId();
      }
      
      Util.CETUS_WAR.info("id=" + id);
      if ( id > 0 ) {
        response = delegate.handleChangeApplicationComboBoxServlet( id );
        Util.CETUS_WAR.info("response=" + response);
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listAplicationServletDTO = ( List< AplicationServletDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listAplicationServletDTO.isEmpty() ) {
            listSelectItemApplicationServlet = new ArrayList< SelectItem >();
            
            listSelectItemApplicationServlet.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_SELECT_SERVLET ) ) );
            for ( AplicationServletDTO element: listAplicationServletDTO ) {
              listSelectItemApplicationServlet.add( new SelectItem( element.getId(), element.getTbServlet().getName() ) );
            }
          }
          
          addObjectSession( listSelectItemApplicationServlet, "listSelectItemApplicationServlet" );
        }
      } else {
        listSelectItemApplicationServlet = new ArrayList< SelectItem >();
        listSelectItemMenu = new ArrayList< SelectItem >();
        cleanObjectSession( "listSelectItemMenu" );
        cleanObjectSession( "listSelectItemApplicationServlet" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  @SuppressWarnings ( "unchecked" )
  public void handleChangeApplicationServletUpdateComboBox () {
    ResponseWSDTO response = null;
    int id = 0;
    try {
      if ( selectedObject != null & selectedObject.getTbAplicationServlet().getTbAplicacion() != null ) {
        id = selectedObject.getTbAplicationServlet().getTbAplicacion().getId();
      }
      listSelectItemMenu = new ArrayList< SelectItem >();
      cleanObjectSession( "listSelectItemMenu" );
      
      if ( id > 0 ) {
        response = delegate.handleChangeApplicationComboBoxServlet( id );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listAplicationServletDTO = ( List< AplicationServletDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listAplicationServletDTO.isEmpty() ) {
            listSelectItemApplicationServlet = new ArrayList< SelectItem >();
            
            listSelectItemApplicationServlet.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_SELECT_SERVLET ) ) );
            for ( AplicationServletDTO element: listAplicationServletDTO ) {
              listSelectItemApplicationServlet.add( new SelectItem( element.getId(), element.getTbServlet().getName() ) );
            }
          }
          addObjectSession( listSelectItemApplicationServlet, "listSelectItemApplicationServlet" );
        }
      } else {
        listSelectItemApplicationServlet = new ArrayList< SelectItem >();
        listSelectItemMenu = new ArrayList< SelectItem >();
        cleanObjectSession( "listSelectItemMenu" );
        cleanObjectSession( "listSelectItemApplicationServlet" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
      listSelectItemMenu = new ArrayList< SelectItem >();
      addObjectSession( listSelectItemMenu, "listSelectItemMenu" );
    }
    
  }
  
  @SuppressWarnings ( "unchecked" )
  public void handleChangeServletComboBoxUpdate () {
    ResponseWSDTO response = null;
    int id = 0;
    try {
      if ( selectedObject != null & selectedObject.getTbAplicationServlet() != null ) {
        id = selectedObject.getTbAplicationServlet().getId();
      }
      if ( id > 0 ) {
        response = delegate.handleChangeServletComboBox( id );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listMenuDTO = ( List< MenuDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listMenuDTO.isEmpty() ) {
            listSelectItemMenu = new ArrayList< SelectItem >();
            
            listSelectItemMenu.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_ONE_MENU_EMPTY ) ) );
            for ( MenuDTO element: listMenuDTO ) {
              listSelectItemMenu.add( new SelectItem( element.getId(), element.getNombre() ) );
            }
          }
          
          addObjectSession( listSelectItemMenu, "listSelectItemMenu" );
        }
      } else {
        listSelectItemMenu = new ArrayList< SelectItem >();
        addObjectSession( listSelectItemMenu, "listSelectItemMenu" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
      listSelectItemMenu = new ArrayList< SelectItem >();
      addObjectSession( listSelectItemMenu, "listSelectItemMenu" );
    }
    
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > findMenuByAppServlet ( int pIdAppServ ) {
    List< SelectItem > lListSelectItemMenu = null;
    ResponseWSDTO response = null;
    try {
      if ( pIdAppServ > 0 ) {
        response = delegate.handleChangeServletComboBox( pIdAppServ );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listMenuDTO = ( List< MenuDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listMenuDTO.isEmpty() ) {
            lListSelectItemMenu = new ArrayList< SelectItem >();
            
            lListSelectItemMenu.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_ONE_MENU_EMPTY ) ) );
            for ( MenuDTO element: listMenuDTO ) {
              lListSelectItemMenu.add( new SelectItem( element.getId(), element.getNombre() ) );
            }
          }
          
          addObjectSession( lListSelectItemMenu, "listSelectItemMenu" );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
    return lListSelectItemMenu;
  }
  
  @SuppressWarnings ( "unchecked" )
  public void loadUpdate () {
    RequestContext context = null;
    ResponseWSDTO response = null;
    try {
      cleanObjectSession( "idMenuPadre" );
      context = RequestContext.getCurrentInstance();
      
      if ( selectedObject != null && !selectedObject.getNombre().isEmpty() ) {
        response = delegate.handleChangeApplicationComboBoxServlet( selectedObject.getTbAplicationServlet().getTbAplicacion().getId() );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listAplicationServletDTO = ( List< AplicationServletDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          
          if ( !listAplicationServletDTO.isEmpty() ) {
            listSelectItemApplicationServlet = new ArrayList< SelectItem >();
            listSelectItemApplicationServlet.add( new SelectItem( "", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_SELECT_SERVLET ) ) );
            for ( AplicationServletDTO element: listAplicationServletDTO ) {
              listSelectItemApplicationServlet.add( new SelectItem( element.getId(), element.getTbServlet().getName() ) );
            }
          }
          
          addObjectSession( listSelectItemApplicationServlet, "listSelectItemApplicationServlet" );
        }
        context.addCallbackParam( "lSelected", true );
        this.listSelectItemMenu = findMenuByAppServlet( selectedObject.getTbAplicationServlet().getId() );
        if ( selectedObject.getTbMenu() != null ) {
          idMenuPadre = selectedObject.getTbMenu().getId();
          addObjectSession( idMenuPadre, "idMenuPadre" );
        } else {
          idMenuPadre = 0;
        }
      } else {
        context.addCallbackParam( "lSelected", false );
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_MENU ) );
      }
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) );
    }
    
  }
  
  public void validateAdd () {
    RequestContext context = null;
    try {
      if ( addObject != null && !addObject.getNombre().isEmpty() ) {
        addObjectSession( addObject, "addObject" );
        addObjectSession( idMenuPadre, "idMenuPadre" );
      }
      context = RequestContext.getCurrentInstance();
      context.addCallbackParam( "lSuccessfull", true );
    } catch ( Exception e ) {
    }
  }
  
  public void validateUpdate () {
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      if ( selectedObject != null && !selectedObject.getNombre().isEmpty() && idMenuPadre > 0 ) {
        addObjectSession( selectedObject, "selectedObject" );
        addObjectSession( idMenuPadre, "idMenuPadre" );
        context.addCallbackParam( "lSuccessfull", true );
      } else {
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_MENU ) );
      }
      
    } catch ( Exception e ) {
    }
  }
  
  public Menu getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Menu addObject ) {
    this.addObject = addObject;
  }
  
  public Menu getSelectedObject () {
    try {
      selectedObject = ( Menu ) getObjectSession( "selectedObject" );
      if ( selectedObject == null ) {
        selectedObject = new Menu();
      }
      
    } catch ( Exception e ) {
      selectedObject = new Menu();
    }
    
    return selectedObject;
  }
  
  public void setSelectedObject ( Menu selectedObject ) {
    cleanObjectSession( "selectedObject" );
    this.selectedObject = selectedObject;
    addObjectSession( this.selectedObject, "selectedObject" );
  }
  
  public List< Menu > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Menu > listRegister ) {
    this.listRegister = listRegister;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public List< SelectItem > getListSelectItemApplication () {
    return listSelectItemApplication;
  }
  
  public void setListSelectItemApplication ( List< SelectItem > listSelectItemApplication ) {
    this.listSelectItemApplication = listSelectItemApplication;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > getListSelectItemMenu () {
    try {
      listSelectItemMenu = ( List< SelectItem > ) getObjectSession( "listSelectItemMenu" );
      
      if ( listSelectItemMenu == null ) {
        listSelectItemMenu = new ArrayList< SelectItem >();
      }
    } catch ( Exception e ) {
      listSelectItemMenu = new ArrayList< SelectItem >();
    }
    
    return listSelectItemMenu;
  }
  
  public void setListSelectItemMenu ( List< SelectItem > listSelectItemMenu ) {
    this.listSelectItemMenu = listSelectItemMenu;
  }
  
  public List< Aplicacion > getListRegisterApplication () {
    return listRegisterApplication;
  }
  
  public void setListRegisterApplication ( List< Aplicacion > listRegisterApplication ) {
    this.listRegisterApplication = listRegisterApplication;
  }
  
  public List< MenuDTO > getListMenuDTO () {
    return listMenuDTO;
  }
  
  public void setListMenuDTO ( List< MenuDTO > listMenuDTO ) {
    this.listMenuDTO = listMenuDTO;
  }
  
  public int getIdMenuPadre () {
    try {
      idMenuPadre = ( Integer ) getObjectSession( "idMenuPadre" );
    } catch ( Exception e ) {
      idMenuPadre = 0;
    }
    
    return idMenuPadre;
  }
  
  public void setIdMenuPadre ( int idMenuPadre ) {
    this.idMenuPadre = idMenuPadre;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > getListSelectItemApplicationServlet () {
    try {
      listSelectItemApplicationServlet = ( List< SelectItem > ) getObjectSession( "listSelectItemApplicationServlet" );
      
      if ( listSelectItemApplicationServlet == null ) {
        listSelectItemApplicationServlet = new ArrayList< SelectItem >();
      }
    } catch ( Exception e ) {
      listSelectItemApplicationServlet = new ArrayList< SelectItem >();
    }
    
    return listSelectItemApplicationServlet;
  }
  
  public void setListSelectItemApplicationServlet ( List< SelectItem > listSelectItemApplicationServlet ) {
    this.listSelectItemApplicationServlet = listSelectItemApplicationServlet;
  }
  
  public int getIdServlet () {
    try {
      idServlet = ( Integer ) getObjectSession( "idServlet" );
    } catch ( Exception e ) {
      idServlet = 0;
    }
    
    return idServlet;
  }
  
  public void setIdServlet ( int idServlet ) {
    this.idServlet = idServlet;
  }
  
}
