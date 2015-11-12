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
import org.primefaces.event.RowEditEvent;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.common.util.ConstantCommon;


/**
 * The Class ServiceManagedBean.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-war (9/09/2013)
 */
@ManagedBean
@RequestScoped
public class ServiceManagedBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  private Service           addObject;
  private Service           selectedObject;
  private List< Service >   listRegister;
  private UIComponent       btnSave          = null;
  private boolean           status;
  private List< SelectItem > listAplicacion;
  
  /**
   * 
   */
  private static final long serialVersionUID = -814452095367034877L;
  
  public ServiceManagedBean () {
    addObject = new Service();
    addObject.setTbAplicacion( new Aplicacion() );
    selectedObject = new Service();
    selectedObject.setTbAplicacion( new Aplicacion() );
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    List< Aplicacion > lAplicacion =  null;
    try {
      
      this.listRegister = this.delegate.findAllOrder( Service.class, ConstantView.ColumnaEntityProperties.NAME_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      lAplicacion = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      listAplicacion = new ArrayList< SelectItem >();
      for ( Aplicacion obj: lAplicacion ) {
        listAplicacion.add( new SelectItem( obj.getId(), obj.getNombre() ) );
      }
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getName() != null && !selectedObject.getName().isEmpty()
         && selectedObject.getWsdl() != null && !selectedObject.getWsdl().isEmpty() ) {
      
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Service ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getName() != null && !addObject.getName().isEmpty()
           && addObject.getWsdl() != null && !addObject.getWsdl().isEmpty() ) {
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
      if ( addObject != null && addObject.getName() != null && !addObject.getName().isEmpty() ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( Service ) getObjectSession( "selectedObject" );
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
    Service lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Service ) event.getObject();
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
  
  public Service getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Service addObject ) {
    this.addObject = addObject;
  }
  
  public Service getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Service selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< Service > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Service > listRegister ) {
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

  
  
}
