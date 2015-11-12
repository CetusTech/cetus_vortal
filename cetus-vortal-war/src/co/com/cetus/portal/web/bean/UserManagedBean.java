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
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.common.mail.SendMail;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class UserManagedBean.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-war (1/09/2013)
 */
@ManagedBean
@RequestScoped
public class UserManagedBean extends GeneralManagedBean {
  
  /*
   * Lista de Atributos del CRUD
   */
  /** The add object. */
  private Usuario           addObject;
  
  /** The selected object. */
  private Usuario           selectedObject;
  
  /** The list register. */
  private List< Usuario >   listRegister;
  
  /** The btn save. */
  private UIComponent       btnSave          = null;
  
  /** The status. */
  private boolean           status;
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -814452095367034877L;
  
  /**
   * </p> Instancia un nuevo user managed bean. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public UserManagedBean () {
    addObject = new Usuario();
    selectedObject = new Usuario();
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      
      this.listRegister = this.delegate.findAllOrder( Usuario.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO,
                                                      ConstantCommon.TIPO_ASC );
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n"
                                                 + e.getMessage() );
      Util.CETUS_WAR.error( "[UsuarioManagedBean.initElement]" + e.getMessage(), e );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
   */
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    String pass = null;
    try {
      if ( addObject != null ) {
        context = RequestContext.getCurrentInstance();
        addObject.setUsuarioCreacion( getUsuarioCreacion() );
        // Encryptar Clave de usuario md5
        pass = addObject.getPassword();
        addObject.setPassword( UtilCommon.encriptarClave( pass ) );
        addObject.setFechaCreacion( new Date() );
        addObject.setStatus( 1 );// 1 es ACTIVO
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          this.sendEmail( addObject.getLogin(), pass, addObject.getEmail() );
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ) );
        }
        
        context.addCallbackParam( "lSuccessfull", lSuccessfull );
      }
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(), ConstantView.ERROR,
                       Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ) );
      Util.CETUS_WAR.error( "[UsuarioManagedBean.add]" + e.getMessage(), e );
    }
    return null;
  }
  
  /**
   * </p> Send email. </p>
   *
   * @param login the login
   * @param password the password
   * @param email the email
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  private void sendEmail ( String login, String password, String email ) {
    String lParamPORT = null;
    String lParamHOST = null;
    String lParamPASS = null;
    String lParamFROM = null;
    String lParamUSER = null;
    String lParamSUBJECT = null;
    String lParamHTML = null;
    SendMail sendMail = null;
    String[] arg = null;
    String msgHtml = null;
    try {
      lParamHOST = this.delegate.getValueParameter( ConstantView.Parameter.SMTP_HOST );
      lParamSUBJECT = this.delegate.getValueParameter( ConstantView.Parameter.SUBJECT_CREATE_USER );
      lParamPORT = this.delegate.getValueParameter( ConstantView.Parameter.SMPT_PORT );
      lParamFROM = this.delegate.getValueParameter( ConstantView.Parameter.SMTP_FROM );
      lParamPASS = this.delegate.getValueParameter( ConstantView.Parameter.SMTP_PASS );
      lParamUSER = this.delegate.getValueParameter( ConstantView.Parameter.SMTP_USERNAME );
      lParamHTML = this.delegate.getValueParameter( ConstantView.Parameter.HTML_EMAIL_NEW_USER );
      
      if ( lParamSUBJECT != null && lParamSUBJECT != null && lParamPORT != null && lParamFROM != null && lParamPASS != null && lParamUSER != null
           && lParamHTML != null ) {
        msgHtml = lParamHTML.replace( "{0}", login ).replace( "{1}", password );
        arg = new String[1];
        arg[0] = email;
        sendMail = new SendMail( lParamFROM, lParamHOST, lParamPORT, lParamUSER, lParamPASS, lParamSUBJECT, msgHtml, arg, null );
        sendMail.start();
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( "[UsuarioManagedBean.sendEmail]" + e.getMessage(), e );
    }
    
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#delete()
   */
  @Override
  public String delete () {
    try {
      selectedObject = ( Usuario ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ) );
        }
      } else {
        addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ) );
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ) );
      Util.CETUS_WAR.error( "[UsuarioManagedBean.delete]" + e.getMessage(), e );
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
      
      if ( selectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        selectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        selectedObject.setFechaCreacion( new Date() );
        
        if ( this.delegate.edit( selectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
          cleanObjectSession( "selectedObject" );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( "[UsuarioManagedBean.update]" + e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    return null;
  }
  
  /**
   * </p> Edits the event. </p>
   *
   * @param event the event
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public void editEvent ( RowEditEvent event ) {
    Usuario lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Usuario ) event.getObject();
      if ( lSelectedObject != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        lSelectedObject.setFechaCreacion( new Date() );
        lSelectedObject.setStatus( status ? 1 : 0 );
        if ( this.delegate.edit( lSelectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ) );
      Util.CETUS_WAR.error( "[UsuarioManagedBean.update]" + e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
  }
  
  /**
   * </p> Load data. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (1/09/2013)
   */
  public void loadData () {
    try {
      if ( addObject != null && !addObject.getIdentificacion().isEmpty() ) {
        addObjectSession( addObject, "addObject" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public Usuario getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Usuario addObject ) {
    this.addObject = addObject;
  }
  
  public Usuario getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Usuario selectedObject ) {
    this.selectedObject = selectedObject;
    addObjectSession( this.selectedObject, "selectedObject" );
  }
  
  public List< Usuario > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Usuario > listRegister ) {
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
  
  public boolean isStatus () {
    return status;
  }
  
  public void setStatus ( boolean status ) {
    this.status = status;
  }
  
}
