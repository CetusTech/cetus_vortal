package co.com.cetus.portal.web.bean;


import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.UserW;
import co.com.cetus.common.util.ConstantCommon;


/**
 * The Class UserWSManagedBean.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-war (9/09/2013)
 */
@ManagedBean
@RequestScoped
public class UserWSManagedBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  private UserW         addObject;
  private UserW         selectedObject;
  private List< UserW > listRegister;
  private UIComponent        btnSave          = null;
  private boolean           status;
  
  /**
   * 
   */
  private static final long  serialVersionUID = -814452095367034877L;
  
  public UserWSManagedBean () {
    addObject = new UserW();
    selectedObject = new UserW();
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      
      this.listRegister = this.delegate.findAllOrder( UserW.class, ConstantView.ColumnaEntityProperties.USER_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getUser() != null && !selectedObject.getUser().isEmpty() 
        && selectedObject.getPassword() != null && !selectedObject.getPassword().isEmpty() ) {
      
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( UserW ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getUser() != null && !addObject.getUser().isEmpty() 
          && addObject.getPassword() != null && !addObject.getPassword().isEmpty() ) {
        addObject.setUserCreation( getUsuarioCreacion() );
        addObject.setDateCreation( new Date() );
        addObject.setStatus( 1 );
        
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ), ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
        }
      } else {
        addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PARAM ), ConstantView.ERROR );
      }
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  public void loadData () {
    try {
      if ( addObject != null && addObject.getUser() != null && !addObject.getUser().isEmpty() ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( UserW ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ), ConstantView.SUCCESS_FULL );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ), ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  @Override
  public String update () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      
      if ( selectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        selectedObject.setUserUpdate( getUsuarioCreacion() );
        selectedObject.setDateUpdate( new Date() );
        
        if ( this.delegate.edit( selectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
          cleanObjectSession( "selectedObject" );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    return null;
  }
  
  public void editEvent ( RowEditEvent event ) {
    UserW lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( UserW ) event.getObject();
      if ( lSelectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUserUpdate( getUsuarioCreacion() );
        lSelectedObject.setDateUpdate( new Date() );
        lSelectedObject.setStatus( status ? 1 : 0 );
        
        if ( this.delegate.edit( lSelectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ), ConstantView.SUCCESS_FULL );
          context.addCallbackParam( "lSuccessfull", lSuccessfull );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_APP ), ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }

  public UserW getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( UserW addObject ) {
    this.addObject = addObject;
  }
  
  public UserW getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( UserW selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< UserW > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< UserW > listRegister ) {
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
  
}
