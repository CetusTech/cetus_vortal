package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Permission;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.vortal.jpa.entity.UserW;
import co.com.cetus.common.util.ConstantCommon;

/**
 * The Class PermissionManagedBean.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-war (14/09/2013)
 */
@ManagedBean
@RequestScoped
public class PermissionManagedBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  private Permission         addObject;
  private Permission         selectedObject;
  private List< Permission > listRegister;
  private UIComponent        btnSave          = null;
  private boolean            status;
  private List< SelectItem > listAplicacion;
  private List< SelectItem > listService;
  private List< SelectItem > listUser;
  private int                idApplication;
  
  /**
   * 
   */
  private static final long  serialVersionUID = -814452095367034877L;
  
  public PermissionManagedBean () {
    addObject = new Permission();
    addObject.setTbUserW( new UserW() );
    addObject.setTbService( new Service() );
    
    selectedObject = new Permission();
    selectedObject.setTbUserW( new UserW() );
    selectedObject.setTbService( new Service() );
    selectedObject.getTbService().setTbAplicacion( new Aplicacion() );
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    List< Aplicacion > lAplicacion = null;
    List< UserW > lUserW = null;
    try {
      this.listRegister = this.delegate.findAllOrder( Permission.class, ConstantView.ColumnaEntityProperties.ID_PROPERTIES_NEGOCIO,
                                                      ConstantCommon.TIPO_ASC );
      lAplicacion = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                                ConstantCommon.TIPO_ASC );
      listAplicacion = new ArrayList< SelectItem >();
      for ( Aplicacion obj: lAplicacion ) {
        listAplicacion.add( new SelectItem( obj.getId(), obj.getNombre() ) );
      }
      
      listService = new ArrayList< SelectItem >();
      listService.add( new SelectItem( 0, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_REGISTER_SERVICE ) ) );
      
      lUserW = this.delegate.findAllOrder( UserW.class, ConstantView.ColumnaEntityProperties.USER_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      listUser = new ArrayList< SelectItem >();
      for ( UserW obj: lUserW ) {
        listUser.add( new SelectItem( obj.getId(), obj.getUser() ) );
      }
      
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage(),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getTbService() != null && selectedObject.getTbService().getName() != null
         && selectedObject.getTbUserW() != null && selectedObject.getTbUserW().getUser() != null ) {
      
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Permission ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getTbService() != null && addObject.getTbService().getId() > 0
           && addObject.getTbUserW() != null && addObject.getTbUserW().getId() > 0 ) {
        addObject.setUserCreation( getUsuarioCreacion() );
        addObject.setDateCreation( new Date() );
        
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null,
                          ConstantView.SUCCESS_FULL
                              + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ),
                          ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
          cleanObjectSession( "listService" );
        }
      } else {
        addMessageError( btnSave.getClientId(),
                         ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PARAM ),
                         ConstantView.ERROR );
      }
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(),
                       ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  public void loadData () {
    try {
      if ( addObject != null && addObject != null && addObject.getTbService() != null && addObject.getTbUserW() != null ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( Permission ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        if ( this.delegate.remove( selectedObject ) ) {
          cleanObjectSession( "selectedObject" );
          this.initElement();
          addMessageInfo( null,
                          ConstantView.SUCCESS_FULL
                              + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ),
                          ConstantView.SUCCESS_FULL );
        }
      } else {
        addMessageError( null,
                         ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ),
                         ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null,
                       ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  @Override
  public String update () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      selectedObject = ( Permission ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        selectedObject.setUserUpdate( getUsuarioCreacion() );
        selectedObject.setDateUpdate( new Date() );
        
        if ( this.delegate.edit( selectedObject ) ) {
          cleanObjectSession( "selectedObject" );
          cleanObjectSession( "listService" );
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null,
                          ConstantView.SUCCESS_FULL
                              + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ), null );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null,
                       ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ),
                       null );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    return null;
  }
  
  public void loadUpdate () {
    RequestContext context = null;
    List< Service > listSer = null;
    try {
      context = RequestContext.getCurrentInstance();
      
      if ( selectedObject != null && selectedObject.getTbService() != null && selectedObject.getTbService().getName() != null ) {
        context.addCallbackParam( "lSelected", true );
        addObjectSession( selectedObject, "selectedObject" );
        
        listSer = delegate.findAllServiceByApplication( selectedObject.getTbService().getTbAplicacion().getId() );
        
        listService = new ArrayList< SelectItem >();
        if ( listSer != null ) {
          for ( Service obj: listSer ) {
            listService.add( new SelectItem( obj.getId(), obj.getName() ) );
          }
          addObjectSession( listService, "listService" );
        }
      } else {
        context.addCallbackParam( "lSelected", false );
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_SERVICE ) );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  public void changeApplication () {
    List< Service > listSer = null;
    try {
      if ( idApplication > 0 ) {
        listSer = delegate.findAllServiceByApplication( idApplication );
        addObject.setTbService( new Service() );
        listService = new ArrayList< SelectItem >();
        if ( listSer != null ) {
          for ( Service obj: listSer ) {
            listService.add( new SelectItem( obj.getId(), obj.getName() ) );
          }
          addObjectSession( listService, "listService" );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void changeApplicationUpdate () {
    List< Service > listSer = null;
    try {
      if ( selectedObject != null ) {
        listSer = delegate.findAllServiceByApplication( selectedObject.getTbService().getTbAplicacion().getId() );
        int idApp = selectedObject.getTbService().getTbAplicacion().getId();
        selectedObject = ( Permission ) getObjectSession( "selectedObject" );
        selectedObject.getTbService().getTbAplicacion().setId( idApp );
        addObjectSession( selectedObject, "selectedObject" );
        
        listService = new ArrayList< SelectItem >();
        if ( listSer != null ) {
          for ( Service obj: listSer ) {
            listService.add( new SelectItem( obj.getId(), obj.getName() ) );
          }
          if ( listService.size() == 0 ) {
            listService.add( new SelectItem( 0,
                                             Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_REGISTER_SERVICE ) ) );
          }
          addObjectSession( listService, "listService" );
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void validateUpdate () {
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      if ( selectedObject != null && selectedObject.getTbService() != null && selectedObject.getTbService().getId() > 0 ) {
        int idSer = selectedObject.getTbService().getId();
        int idUser = selectedObject.getTbUserW().getId();
        selectedObject = ( Permission ) getObjectSession( "selectedObject" );
        selectedObject.getTbService().setId( idSer );
        selectedObject.getTbUserW().setId( idUser );
        addObjectSession( selectedObject, "selectedObject" );
        context.addCallbackParam( "lSuccessfull", true );
      } else {
        addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_SERVICE ),
                         ConstantView.ERROR );
      }
      
    } catch ( Exception e ) {
    }
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }
  
  public Permission getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Permission addObject ) {
    this.addObject = addObject;
  }
  
  public Permission getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Permission selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< Permission > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Permission > listRegister ) {
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
  
  public List< SelectItem > getListAplicacion () {
    return listAplicacion;
  }
  
  public void setListAplicacion ( List< SelectItem > listAplicacion ) {
    this.listAplicacion = listAplicacion;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > getListService () {
    try {
      if ( getObjectSession( "listService" ) != null ) {
        listService = ( List< SelectItem > ) getObjectSession( "listService" );
      }
    } catch ( Exception e ) {
      listService = null;
    }
    
    return listService;
  }
  
  public void setListService ( List< SelectItem > listService ) {
    this.listService = listService;
  }
  
  public List< SelectItem > getListUser () {
    return listUser;
  }
  
  public void setListUser ( List< SelectItem > listUser ) {
    this.listUser = listUser;
  }
  
  public int getIdApplication () {
    return idApplication;
  }
  
  public void setIdApplication ( int idApplication ) {
    this.idApplication = idApplication;
  }
  
}
