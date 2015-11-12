package co.com.cetus.portal.web.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.TipoEstilo;

@ManagedBean
@RequestScoped
public class ApplicationManagedBean extends GeneralManagedBean {
  /*
   * Lista de Atributos del CRUD
   */
  private Aplicacion         addObject;
  private Aplicacion         selectedObject;
  private List< Aplicacion > listRegister;
  private List< TipoEstilo > listTipoEstilo;
  private List< SelectItem > listTipoEstiloItem;
  private String             idStyle;
  private TipoEstilo         estilo;
  private UIComponent        btnSave          = null;
  private StreamedContent    logo;
  private StreamedContent    imageBody;
  private UploadedFile       file             = null;
  
  /**
   * 
   */
  private static final long  serialVersionUID = -814452095367034877L;
  
  public ApplicationManagedBean () {
    addObject = new Aplicacion();
    addObject.setTbTipoEstilo( new TipoEstilo() );
    selectedObject = new Aplicacion();
  }
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      
      this.listRegister = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                                      ConstantCommon.TIPO_ASC );
      
      this.listTipoEstilo = this.delegate.findAllOrder( TipoEstilo.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                                        ConstantCommon.TIPO_ASC );
      
      this.convertToComboBox( listTipoEstilo );
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n"
                                                 + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  private void convertToComboBox ( List< TipoEstilo > pListRegister ) {
    listTipoEstiloItem = new ArrayList< SelectItem >();
    for ( TipoEstilo obj: pListRegister ) {
      listTipoEstiloItem.add( new SelectItem( obj.getId(), obj.getNombre() ) );
    }
  }
  
  public void handleStyleChange () {
    addObjectSession( idStyle, "idStyle" );
  }
  
  public Aplicacion getAddObject () {
    return addObject;
  }
  
  public void setAddObject ( Aplicacion addObject ) {
    this.addObject = addObject;
  }
  
  public Aplicacion getSelectedObject () {
    return selectedObject;
  }
  
  public void setSelectedObject ( Aplicacion selectedObject ) {
    this.selectedObject = selectedObject;
  }
  
  public void loadSelected () {
    if ( selectedObject != null && selectedObject.getNombre() != null && !selectedObject.getNombre().isEmpty() ) {
      if ( selectedObject.getLogo() != null ) {
        InputStream inputStream = new ByteArrayInputStream( selectedObject.getLogo() );
        logo = new DefaultStreamedContent( inputStream, "image/png" );
        addObjectSession( logo, "logo" );
      }
      
      if ( selectedObject.getImagenCuerpo() != null ) {
        InputStream inputStream = new ByteArrayInputStream( selectedObject.getImagenCuerpo() );
        imageBody = new DefaultStreamedContent( inputStream, "image/png" );
        addObjectSession( imageBody, "imageBody" );
      }
      
      addObjectSession( this.selectedObject, "selectedObject" );
      
    }
  }
  
  public List< Aplicacion > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Aplicacion > listRegister ) {
    this.listRegister = listRegister;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  @Override
  public String add () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      context = RequestContext.getCurrentInstance();
      addObject = ( Aplicacion ) getObjectSession( "addObject" );
      estilo = ( TipoEstilo ) getObjectSession( "estilo" );
      if ( addObject != null && estilo != null && !addObject.getNombre().isEmpty() && !addObject.getUrlServer().isEmpty() ) {
        addObject.setUsuarioCreacion( getUsuarioCreacion() );
        addObject.setFechaCreacion( new Date() );
        addObject.setTbTipoEstilo( estilo );
        addObject.setLogo( getObjectSession( "logoByte" ) != null ? ( byte[] ) getObjectSession( "logoByte" ) : null );
        addObject.setImagenCuerpo( getObjectSession( "imageBodyByte" ) != null ? ( byte[] ) getObjectSession( "imageBodyByte" ) : null );
        if ( this.delegate.create( addObject ) != null ) {
          this.initElement();
          lSuccessfull = true;
          cleanObjectSession( "logoByte" );
          cleanObjectSession( "imageByte" );
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ),
                          ConstantView.SUCCESS_FULL );
          cleanObjectSession( "addObject" );
        }
      } else {
        addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INPUT_PARAM ),
                         ConstantView.ERROR );
      }
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    } catch ( Exception e ) {
      addMessageError( btnSave.getClientId(), Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    return null;
  }
  
  public void loadData () {
    try {
      if ( addObject != null && !addObject.getNombre().isEmpty() && estilo != null ) {
        addObjectSession( addObject, "addObject" );
        addObjectSession( estilo, "estilo" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  @Override
  public String delete () {
    try {
      selectedObject = ( Aplicacion ) getObjectSession( "selectedObject" );
      if ( selectedObject != null ) {
        
        if ( this.delegate.remove( selectedObject ) ) {
          this.initElement();
          addMessageInfo( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_DELETE ),
                          ConstantView.SUCCESS_FULL );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_OBJECT_NULL ), ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_DELETE_REGISTER ),
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
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    return null;
  }
  
  public void editEvent ( RowEditEvent event ) {
    Aplicacion lSelectedObject = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    try {
      lSelectedObject = ( Aplicacion ) event.getObject();
      if ( lSelectedObject != null && estilo != null ) {
        context = RequestContext.getCurrentInstance();
        lSelectedObject.setUsuarioCreacion( getUsuarioCreacion() );
        lSelectedObject.setFechaCreacion( new Date() );
        lSelectedObject.setTbTipoEstilo( estilo );
        lSelectedObject.setLogo( getObjectSession( "logoByte" ) != null ? ( byte[] ) getObjectSession( "logoByte" )
                                                                       : ( lSelectedObject.getLogo() != null ? lSelectedObject.getLogo() : null ) );
        lSelectedObject.setImagenCuerpo( getObjectSession( "imageBodyByte" ) != null
                                                                                    ? ( byte[] ) getObjectSession( "imageBodyByte" )
                                                                                    : ( lSelectedObject.getImagenCuerpo() != null
                                                                                                                                 ? lSelectedObject.getImagenCuerpo()
                                                                                                                                 : null ) );
        
        if ( this.delegate.edit( lSelectedObject ) ) {
          this.initElement();
          lSuccessfull = true;
          cleanObjectSession( "logoByte" );
          cleanObjectSession( "imageByte" );
          addMessageInfo( null,
                          Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ),
                          ConstantView.SUCCESS_FULL );
          context.addCallbackParam( "lSuccessfull", lSuccessfull );
        }
      } else {
        addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECT_APP ), ConstantView.ERROR );
      }
    } catch ( Exception e ) {
      addMessageError( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_UPDATE_REGISTER ),
                       ConstantView.ERROR );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  public void handleFileUpload ( FileUploadEvent event ) {
    try {
      file = event.getFile();
      addObjectSession( file.getContents(), "logoByte" );
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void handleFileUploadImageBody ( FileUploadEvent event ) {
    try {
      file = event.getFile();
      addObjectSession( file.getContents(), "imageBodyByte" );
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public List< SelectItem > getListTipoEstiloItem () {
    return listTipoEstiloItem;
  }
  
  public void setListTipoEstiloItem ( List< SelectItem > listTipoEstiloItem ) {
    this.listTipoEstiloItem = listTipoEstiloItem;
  }
  
  public String getIdStyle () {
    return idStyle;
  }
  
  public void setIdStyle ( String idStyle ) {
    this.idStyle = idStyle;
  }
  
  public List< TipoEstilo > getListTipoEstilo () {
    return listTipoEstilo;
  }
  
  public void setListTipoEstilo ( List< TipoEstilo > listTipoEstilo ) {
    this.listTipoEstilo = listTipoEstilo;
  }
  
  public TipoEstilo getEstilo () {
    return estilo;
  }
  
  public void setEstilo ( TipoEstilo estilo ) {
    this.estilo = estilo;
  }
  
  public UIComponent getBtnSave () {
    return btnSave;
  }
  
  public void setBtnSave ( UIComponent btnSave ) {
    this.btnSave = btnSave;
  }
  
  public StreamedContent getLogo () {
    try {
      logo = ( StreamedContent ) getObjectSession( "logo" );
    } catch ( Exception e ) {
      logo = null;
    }
    return logo;
  }
  
  public void setLogo ( StreamedContent logo ) {
    this.logo = logo;
  }
  
  public UploadedFile getFile () {
    return file;
  }
  
  public void setFile ( UploadedFile file ) {
    this.file = file;
  }
  
  public StreamedContent getImageBody () {
    try {
      imageBody = ( StreamedContent ) getObjectSession( "imageBody" );
    } catch ( Exception e ) {
      imageBody = null;
    }
    return imageBody;
  }
  
  public void setImageBody ( StreamedContent imageBody ) {
    this.imageBody = imageBody;
  }
  
}
