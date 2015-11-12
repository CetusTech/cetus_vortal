package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

import co.com.cetus.common.dto.ParameterDTO;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Parametro;

@ManagedBean
@RequestScoped
public class ComponentManagedBean extends GeneralManagedBean {
  
  private Component         addObject;
  private Component         selectedObject;
  private List< Component > listRegister;
  private UIComponent       btnSave          = null;
  private boolean           status;
  private int               idApplication;
  
  private static final long serialVersionUID = -814452095367034877L;
  
  public ComponentManagedBean () {
    addObject = new Component();
    addObject.setTbAplicacion( new Aplicacion() );
    selectedObject = new Component();
    selectedObject.setTbAplicacion( new Aplicacion() );
    idApplication = ( Integer ) getObjectSession( "aplicacionId" );
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    try {
      this.listRegister = this.delegate.findAllComponentByApplication( idApplication );
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getName() != null && !selectedObject.getName().isEmpty() ) {
      
      addObjectSession( this.selectedObject, "selectedObject" );
    }
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Component ) getObjectSession( "addObject" );
      if ( addObject != null && addObject.getName() != null && !addObject.getName().isEmpty() ) {
        addObject.setUserCreation( getUsuarioCreacion() );
        addObject.setDateCreation( new Date() );
        addObject.getTbAplicacion().setId( idApplication );
        
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
      selectedObject = ( Component ) getObjectSession( "selectedObject" );
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
    Component lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Component ) event.getObject();
      if ( lSelectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUserUpdate( getUsuarioCreacion() );
        lSelectedObject.setDateUpdate( new Date() );
        
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
  
  /**
   * </p> Reload parameter. </p>
   *
   * @return el string
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (6/10/2013)
   */
  public String reloadParameter () {
    List< Parametro > listParameter = null;
    String wsdlAgarthi = null;
    List< ParameterDTO > listParameterDTO = null;
    try {
      selectedObject = ( Component ) getObjectSession( "selectedObject" );
      if ( selectedObject != null && selectedObject.getId() > 0 ) {
        String idCetus = ConstantView.Parameter.ID_APLICATION_CETUS;
        if ( idCetus != null && idCetus.equals( String.valueOf( selectedObject.getTbAplicacion().getId() ) ) ) {
          if ( delegate.reloadParameter( selectedObject.getTbAplicacion().getId(), selectedObject.getId() ) ) {
            addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_COMPONENT_RELOAD ), null );
          } else {
            addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_RELOAD_PARAMETER ), null );
          }
        } else {
          String idAgarthi = delegate.getValueParameter( ConstantView.Parameter.ID_APLICATION_AGARTHI );
          
          // Se debe agregar un if por cada aplicacion que exista en CETUS VORTAL para poder realizar el reload de parametros
          if ( idAgarthi != null && idAgarthi.equals( String.valueOf( selectedObject.getTbAplicacion().getId() ) ) ) {
            listParameter = delegate.findAllParameterByCompApp( selectedObject.getTbAplicacion().getId(), selectedObject.getId() );
            if ( listParameter != null && listParameter.size() > 0 ) {
              wsdlAgarthi = delegate.getValueParameter( ConstantView.Parameter.WSDL_AGARTHI_SERVICE );
              Util.CETUS_WAR.info( "wsdlAgarthi ::> " + wsdlAgarthi );
              
              //AgarthiClientWSFacade agarthiClientWSFacade = new AgarthiClientWSFacade( new URL( wsdlAgarthi ) );
              
              //ReloadParameterRequestDTO reloadParameterRequestDTO = new ReloadParameterRequestDTO();
              ParameterDTO parameterDTO = null;
              listParameterDTO = new ArrayList< ParameterDTO >();
              for ( Parametro parametro: listParameter ) {
                parameterDTO = new ParameterDTO( parametro.getAbreviatura(), parametro.getValor() );
                listParameterDTO.add( parameterDTO );
              }
//              reloadParameterRequestDTO.setComponent( selectedObject.getName() );
//              reloadParameterRequestDTO.setUser( delegate.getValueParameter( ConstantView.Parameter.USER_WS_AGARTHI_SERVICE ) );
//              reloadParameterRequestDTO.setPassword( delegate.getValueParameter( ConstantView.Parameter.PASSWORD_WS_AGARTHI_SERVICE ) );
//              String listStr = UtilCommon.toXML( listParameterDTO );
//              reloadParameterRequestDTO.setListParameter( listStr );
//              ResponseWSDTO responseWSDTO = agarthiClientWSFacade.reloadParameter( reloadParameterRequestDTO );
//              if ( responseWSDTO != null && responseWSDTO.getCode().equals( ConstantCommon.WSResponse.CODE_ONE ) ) {
//                addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_COMPONENT_RELOAD ),
//                                null );
//              } else {
//                addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_RELOAD_PARAMETER ), null );
//              }
            }
          } else {
            addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.NO_CONFIGURED_COMPONENT ), null );
          }
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_COMPONENT_RELOAD ), null );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_RELOAD_PARAMETER ), null );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }

  /**
   * </p> Reload component. </p>
   *
   * @author Jose David Salcedo Mandon - Cetus Technology
   * @since cetus-vortal-war (7/10/2013)
   */
  public void reloadComponent () {
    RequestContext context = RequestContext.getCurrentInstance();;
    if ( selectedObject != null && selectedObject.getId() > 0 ) {
      context.addCallbackParam( "lSuccessfull", true );
      addObjectSession( selectedObject, "selectedObject" );
    } else {
      context.addCallbackParam( "lSuccessfull", false );
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_COMPONENT_RELOAD ), null );
    }
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }
  
  public Component getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Component addObject ) {
    this.addObject = addObject;
  }
  
  public Component getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Component selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public List< Component > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Component > listRegister ) {
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
