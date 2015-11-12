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
import co.com.cetus.vortal.jpa.entity.Servlet;
import co.com.cetus.common.util.ConstantCommon;

/**
 * The Class ServletMBean.
 * 
 * @author Andres Herrera - Cetus Technology
 * @version cetus-vortal-war (12/03/2014)
 */
@ManagedBean
@RequestScoped
public class ServletMBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  /** The add object. */
  private Servlet           addObject;
  
  /** The selected object. */
  private Servlet           selectedObject;
  
  /** The list register. */
  private List< Servlet >   listRegister;
  
  /** The btn save. */
  private UIComponent       btnSave          = null;
  
  /** The status. */
  private boolean           status           = true;
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -814452095367034877L;
  
  /**
   * </p> Instancia un nuevo servlet m bean. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (12/03/2014)
   */
  public ServletMBean () {
    addObject = new Servlet();
    selectedObject = new Servlet();
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      this.listRegister = this.delegate.findAllOrder( Servlet.class, ConstantView.ColumnaEntityProperties.NAME_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> Load selected. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (12/03/2014)
   */
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getName() != null && !selectedObject.getName().isEmpty() ) {
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
   */
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Servlet ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getName() != null && !addObject.getName().isEmpty() ) {
        addObject.setUserCreation( getUsuarioCreacion() );
        addObject.setDateCreation( new Date() );
        addObject.setStatus( ConstantView.ACTIVO );
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
  
  /**
   * </p> Load data. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (12/03/2014)
   */
  public void loadData () {
    try {
      if ( addObject != null ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#delete()
   */
  @Override
  public String delete () {
    try {
      selectedObject = ( Servlet ) getObjectSession( "selectedObject" );
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
  
  /*
   * (non-Javadoc)
   * 
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#update()
   */
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
  
  /**
   * </p> Edits the event. </p>
   *
   * @param event the event
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (12/03/2014)
   */
  public void editEvent ( RowEditEvent event ) {
    Servlet lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Servlet ) event.getObject();
      if ( lSelectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUserUpdate( getUsuarioCreacion() );
        lSelectedObject.setDateUpdate( new Date() );
        lSelectedObject.setStatus( status ? ConstantView.ACTIVO : ConstantView.INACTIVO );
        
        if ( this.delegate.edit( lSelectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ), ConstantView.SUCCESS_FULL );
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
  
  public Servlet getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Servlet addObject ) {
    this.addObject = addObject;
  }
  
  public Servlet getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Servlet selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< Servlet > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Servlet > listRegister ) {
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
