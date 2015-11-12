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
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Parametro;



/**
 * The Class ParameterManagedBean.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-war (3/10/2013)
 */
@ManagedBean
@RequestScoped
public class ParameterManagedBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  private Parametro         addObject;
  private Parametro         selectedObject;
  private List< Parametro > listRegister;
  private UIComponent        btnSave          = null;
  private boolean            status;
  private List< SelectItem > listComponent;
  private int                idApplication;
  private List< SelectItem > listStatus;
  private List< SelectItem > listType;
  private boolean            showPopupConfirm;
  
  private static final long  serialVersionUID = -814452095367034877L;
  
  public ParameterManagedBean () {
    addObject = new Parametro();
    addObject.setTbComponent( new Component() );
    addObject.getTbComponent().setTbAplicacion( new Aplicacion() );
    
    selectedObject = new Parametro();
    selectedObject.setTbComponent( new Component() );
    selectedObject.getTbComponent().setTbAplicacion( new Aplicacion() );
    
    idApplication = (Integer) getObjectSession( "aplicacionId" );
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @Override
  @PostConstruct
  public void initElement () {
    try {
      this.listRegister = this.delegate.findAllParameterByApplication( idApplication );
      
      loadComponent();
      
      listStatus = new ArrayList< SelectItem >();
      listStatus.add( new SelectItem( "ACTIVO", "ACTIVO" ) );
      listStatus.add( new SelectItem( "INACTIVO", "INACTIVO" ) );
      
      listType = new ArrayList< SelectItem >();
      listType.add( new SelectItem( "C", "CADENA" ) );
      listType.add( new SelectItem( "N", "NUMERO" ) );
      listType.add( new SelectItem( "M", "MODEDA" ) );
      listType.add( new SelectItem( "B", "BOLEANO" ) );
      listType.add( new SelectItem( "T", "PLANTILLA" ) );
      
      
      
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage(), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getAbreviatura() != null && selectedObject.getTbComponent() != null ) {
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
   */
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      showPopupConfirm = false;
      context = RequestContext.getCurrentInstance();
      addObject = ( Parametro ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getAbreviatura() != null && addObject.getTbComponent().getId() > 0 ) {
        addObject.setUsuarioCreacion( getUsuarioCreacion() );
        addObject.setFechaCreacion( new Date() );
        addObject.setEstado( "ACTIVO" );
        
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ), ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
          cleanObjectSession( "listComponent" );
        }
      } else {
        addMessageError( btnSave.getClientId(), ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PARAM ), ConstantView.ERROR );
      }
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(), ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  /**
   * </p> Load data. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (3/10/2013)
   */
  public void loadData () {
    boolean lSuccessfullVal = true;
    try {
      RequestContext context = RequestContext.getCurrentInstance();
      if ( addObject != null && addObject != null && addObject.getAbreviatura() != null ) {
        if( addObject.getTipoParametro().equals( "N" ) ){
          try {
            Integer.parseInt( addObject.getValor() );
          } catch ( Exception e ) {
            lSuccessfullVal = false;
            addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_NUMBER ), null );
          }
        } else if( addObject.getTipoParametro().equals( "M" ) ){
          try {
            Double.parseDouble( addObject.getValor() );
          } catch ( Exception e ) {
            lSuccessfullVal = false;
            addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_MONEY ), null );
          }
        } else if( addObject.getTipoParametro().equals( "B" ) ){
          if( !addObject.getValor().equalsIgnoreCase( "true" ) && !addObject.getValor().equalsIgnoreCase( "false" ) ){
            lSuccessfullVal = false;
            addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_BOOLEAN ), null ); 
          }
        }
        context.addCallbackParam( "lSuccessfullVal", lSuccessfullVal );
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#delete()
   */
  @Override
  public String delete () {
    try {
      selectedObject = ( Parametro ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        if ( this.delegate.remove( selectedObject ) ) {
          cleanObjectSession( "selectedObject" );
          this.initElement();
          addMessageInfo( null, ConstantView.SUCCESS_FULL + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ), ConstantView.SUCCESS_FULL );
        }
      } else {
        addMessageError( null, ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ), ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ), ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#update()
   */
  @Override
  public String update () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      selectedObject = ( Parametro ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        selectedObject.setUsuarioModificacion( getUsuarioCreacion() );
        selectedObject.setFechaModificacion( new Date() );
        
        if ( this.delegate.edit( selectedObject ) ) {
          cleanObjectSession( "selectedObject" );
          cleanObjectSession( "listComponent" );
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ), null );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR + Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ), null );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    return null;
  }
  
  /**
   * </p> Load update. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (3/10/2013)
   */
  public void loadUpdate () {
    RequestContext context = null;
    List< Component > listCom = null;
    try {
      context = RequestContext.getCurrentInstance();
      
      if ( selectedObject != null && selectedObject.getAbreviatura() != null ) {
        context.addCallbackParam( "lSelected", true );
        addObjectSession( selectedObject, "selectedObject" );
        
        listCom = delegate.findAllComponentByApplication( selectedObject.getTbComponent().getTbAplicacion().getId() );
        
        listComponent = new ArrayList< SelectItem >();
        if ( listCom != null ) {
          for ( Component obj: listCom ) {
            listComponent.add( new SelectItem( obj.getId(), obj.getName() ) );
          }
          addObjectSession( listComponent, "listComponent" );
        }
      } else {
        context.addCallbackParam( "lSelected", false );
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_PARAMETER ), null );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  /**
   * </p> Load component. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (6/10/2013)
   */
  private void loadComponent () {
    List< Component > listCom = null;
    try {
      listComponent = new ArrayList< SelectItem >();
      if ( idApplication > 0 ) {
        listCom = delegate.findAllComponentByApplication( idApplication );
        addObject.setTbComponent( new Component() );
        if ( listCom != null ) {
          for ( Component obj: listCom ) {
            listComponent.add( new SelectItem( obj.getId(), obj.getName() ) );
          }
          addObjectSession( listComponent, "listComponent" );
        }
      }else {
        listComponent.add( new SelectItem( 0, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_REGISTER_COMPONENT ) ) );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  
  /**
   * </p> Validate update. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (3/10/2013)
   */
  public void validateUpdate () {
    RequestContext context = null;
    boolean lSuccessfullVal = true;
    try {
      context = RequestContext.getCurrentInstance();
      if ( selectedObject != null && selectedObject.getTbComponent() != null && selectedObject.getTbComponent().getId() > 0 ) {
        if( selectedObject.getTipoParametro().equals( "N" ) ){
          try {
            Integer.parseInt( selectedObject.getValor() );
          } catch ( Exception e ) {
            lSuccessfullVal = false;
            addMessageError( "submitButton_Update_Menu", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_NUMBER ), null );
          }
        } else if( selectedObject.getTipoParametro().equals( "M" ) ){
          try {
            Double.parseDouble( selectedObject.getValor() );
          } catch ( Exception e ) {
            lSuccessfullVal = false;
            addMessageError( "submitButton_Update_Menu", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_MONEY ), null );
          }
        } else if( selectedObject.getTipoParametro().equals( "B" ) ){
          if( !selectedObject.getValor().equalsIgnoreCase( "true" ) && !selectedObject.getValor().equalsIgnoreCase( "false" ) ){
            lSuccessfullVal = false;
            addMessageError( "submitButton_Update_Menu", Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_VALUE_BOOLEAN ), null ); 
          }
        }
        if( lSuccessfullVal ){
          Parametro selectedObjectAux = (Parametro) getObjectSession( "selectedObject" );
          selectedObject.setFechaCreacion( selectedObjectAux.getFechaCreacion() );
          selectedObject.setUsuarioCreacion( selectedObjectAux.getUsuarioCreacion() );
          selectedObject.setId( selectedObjectAux.getId() );
          addObjectSession( selectedObject, "selectedObject" );
        }
        context.addCallbackParam( "lSuccessfull", lSuccessfullVal );
      } else {
        addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_PARAMETER ), ConstantView.ERROR );
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
  
  public Parametro getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Parametro addObject ) {
    this.addObject = addObject;
  }
  
  public Parametro getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Parametro selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< Parametro > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Parametro > listRegister ) {
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
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > getListComponent () {
    try {
      if( getObjectSession( "listComponent" ) != null ){
        listComponent = ( List< SelectItem > ) getObjectSession( "listComponent" );
      }
    } catch ( Exception e ) {
      listComponent = null;
    }
    
    return listComponent;
  }
  
  public void setListComponent ( List< SelectItem > listComponent ) {
    this.listComponent = listComponent;
  }
  
  public int getIdApplication () {
    return idApplication;
  }
  
  public void setIdApplication ( int idApplication ) {
    this.idApplication = idApplication;
  }

  public List< SelectItem > getListStatus () {
    return listStatus;
  }

  public void setListStatus ( List< SelectItem > listStatus ) {
    this.listStatus = listStatus;
  }

  public List< SelectItem > getListType () {
    return listType;
  }

  public void setListType ( List< SelectItem > listType ) {
    this.listType = listType;
  }

  public boolean isShowPopupConfirm () {
    return showPopupConfirm;
  }

  public void setShowPopupConfirm ( boolean showPopupConfirm ) {
    this.showPopupConfirm = showPopupConfirm;
  }

  
  
}
