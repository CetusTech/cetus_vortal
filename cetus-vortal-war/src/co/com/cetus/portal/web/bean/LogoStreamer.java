package co.com.cetus.portal.web.bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.common.exception.ServiceException;

@ManagedBean
@RequestScoped
public class LogoStreamer extends GeneralManagedBean {
  
  /**
   * 
   */
  private static final long      serialVersionUID = 7869641805185723474L;
  private static StreamedContent defaultFileContent;
  private StreamedContent        fileContent;
  private static Aplicacion      app              = null;
  private static String          idApp            = null;
  
  public StreamedContent getFileContent ()
  {
    try {
      //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      idApp = ( String ) getObjectSession( "idApp" );
      if ( idApp == null || idApp.equals( "" ) )
      {
        fileContent = defaultFileContent;
      }
      else
      {
        int parsedId = Integer.parseInt( idApp );
        if ( parsedId < 0 )
        {
          fileContent = defaultFileContent;
        }
        
        app = this.delegate.find( Aplicacion.class, parsedId );
        
        if ( app != null && app.getLogo() != null && idApp.equals( String.valueOf( app.getId() ) ) ) {
          InputStream inputStream = new ByteArrayInputStream( app.getLogo() );
          fileContent = new DefaultStreamedContent( inputStream);
        }
        
      }
    } catch ( ServiceException e ) {
      e.printStackTrace();
    }
    return fileContent;
  }
  
  public void setFileContent ( StreamedContent fileContent )
  {
    this.fileContent = fileContent;
  }
  
  static
  {
    app = ( Aplicacion ) getObjectSession( "aplicacion" );
    if ( app != null && app.getLogo() != null && idApp.equals( String.valueOf( app.getId() ) ) ) {
      InputStream inputStream = new ByteArrayInputStream( app.getLogo() );
      defaultFileContent = new DefaultStreamedContent( inputStream );
    }
  }
  
  @Override
  public void initElement () {
    
  }
  
  @Override
  public String delete () {
    return null;
  }
  
  @Override
  public String update () {
    return null;
  }
  
  @Override
  public String add () {
    return null;
  }
  
}