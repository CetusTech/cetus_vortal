package co.com.cetus.portal.web.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.common.exception.ServiceException;
import co.com.cetus.common.util.ConstantCommon;


/**
 * The Class RolManagedBean.
 */
@ManagedBean
@RequestScoped
public class RolManagedBean extends GeneralManagedBean {
  
  /*
   * Lista de Atributos del CRUD
   */
  private Rol                addObject;
  private Rol                selectedObject;
  private List< Rol >        listRegister;
  private List< SelectItem > listSelectItemApplication;
  private List< SelectItem > listSelectItemRol;
  private UIComponent        btnSave          = null;
  /**
   * 
   */
  private static final long  serialVersionUID = -814452095367034877L;
  
  public RolManagedBean () {
    addObject = new Rol();
    selectedObject = new Rol();
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      this.listRegister = this.delegate.findAllOrder( Rol.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Rol ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getDescripcion() != null && !addObject.getDescripcion().isEmpty() ) {
        
        addObject.setUsuarioCreacion( getUsuarioCreacion() );
        addObject.setFechaCreacion( new Date() );
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ) );
          cleanObjectSession( "addObject" );
        }
        
      } else {
        addMessageError( btnSave.getClientId(), ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PARAM ) );
      }
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( ServiceException s ) {
      addMessageError( btnSave.getClientId(), ConstantView.ERROR, s.getMessage() );
      Util.CETUS_WAR.error( s.getMessage(), s );
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(), ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    }
    return null;
  }
  
  public void loadData () {
    try {
      if ( addObject != null && !addObject.getDescripcion().isEmpty() ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( Rol ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ) );
        }
      } else {
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ) );
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ) );
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
        selectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        selectedObject.setFechaCreacion( new Date() );
        
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
    Rol lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Rol ) event.getObject();
      if ( lSelectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        lSelectedObject.setFechaCreacion( new Date() );
        
        if ( this.delegate.edit( lSelectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
  }
  
  public List< SelectItem > getListSelectItemApplication () {
    return listSelectItemApplication;
  }
  
  public void setListSelectItemApplication (
                                             List< SelectItem > listSelectItemApplication ) {
    this.listSelectItemApplication = listSelectItemApplication;
  }
  
  public List< SelectItem > getListSelectItemRol () {
    return listSelectItemRol;
  }
  
  public void setListSelectItemRol ( List< SelectItem > listSelectItemRol ) {
    this.listSelectItemRol = listSelectItemRol;
  }
  
  public Rol getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Rol addObject ) {
    this.addObject = addObject;
  }
  
  public Rol getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Rol selectedObject ) {
    this.selectedObject = selectedObject;
    addObjectSession( this.selectedObject, "selectedObject" );
  }
  
  public List< Rol > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Rol > listRegister ) {
    this.listRegister = listRegister;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }
  
}
