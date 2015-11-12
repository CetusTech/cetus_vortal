package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.portal.ejb.util.ConstantBussines;
import co.com.cetus.vortal.business.dto.ParametroDTO;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.util.Converter;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class RolManagedBean.
 */
@ManagedBean
@RequestScoped
public class MailTemplateManagedBean extends GeneralManagedBean {
  private Parametro            param;
  private List< SelectItem >   listTemplates;
  private List< ParametroDTO > listParamTemplate;
  private String               idParam;
  private String               html;
  /**
   * 
   */
  private static final long    serialVersionUID = -814452095367034877L;
  /** The converter. */
  private Converter            converter;
  
  @SuppressWarnings ( "unchecked" )
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    ResponseWSDTO response = null;
    try {
      converter = new Converter( ConstantBussines.Internalizacion.PCK_CLASS_DTO, ConstantBussines.Internalizacion.PCK_CLASS_JPA );
      response = this.delegate.findAllByTypeParameter( ConstantView.TYPE_TEMPLATE );
      if ( Util.validateResponseSuccessXMLOutput( response ) ) {
        this.listParamTemplate = ( List< ParametroDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
        if ( listParamTemplate != null && !listParamTemplate.isEmpty() ) {
          convertDTOToSelectItem( listParamTemplate );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void convertDTOToSelectItem ( List< ParametroDTO > pList ) {
    SelectItem item = null;
    try {
      listTemplates = new ArrayList< SelectItem >();
      listTemplates.add( new SelectItem( null, ConstantView.Internalizacion.SEL_TEMPLATE_MAIL ) );
      for ( ParametroDTO parametroDTO: pList ) {
        item = new SelectItem( parametroDTO.getId(), parametroDTO.getAbreviatura() );
        listTemplates.add( item );
      }
    } catch ( Exception e ) {
    }
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
  
  public void handleChangeTemplate () {
    int id;
    try {
      if ( idParam != null && listParamTemplate != null && !listParamTemplate.isEmpty() ) {
        for ( ParametroDTO param: listParamTemplate ) {
          id = param.getId();
          if ( id == Integer.parseInt( idParam ) ) {
            html = param.getValor();
            addObjectSession( param, "param" );
            break;
          }
        }
      }
    } catch ( Exception e ) {
    }
  }
  
  public void save () {
    ParametroDTO param = null;
    boolean lSuccessfull = false;
    RequestContext context = null;
    Parametro parametro = null;
    Component component = null;
    try {
      parametro = new Parametro();
      component = new Component();
      if ( html != null ) {
        context = RequestContext.getCurrentInstance();
        param = ( ParametroDTO ) getObjectSession( "param" );
        param.setId( Integer.parseInt( idParam ) );
        param.setValor( html );
        component.setId( Integer.parseInt( ConstantView.Parameter.ID_COMPONENT_CETUS_VORTAL ) );
        parametro.setTbComponent( component );
        converter.convertDtoToEntity( param, parametro );
        if ( this.delegate.edit( parametro ) ) {
          this.initElement();
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_UPDATE ) );
          context.addCallbackParam( "lSuccessfull", lSuccessfull );
        }
      }
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  public void limpiar () {
    this.html = "";
    this.idParam = null;
  }
  
  public List< SelectItem > getListTemplates () {
    return listTemplates;
  }
  
  public void setListTemplates ( List< SelectItem > listTemplates ) {
    this.listTemplates = listTemplates;
  }
  
  public String getIdParam () {
    return idParam;
  }
  
  public void setIdParam ( String idParam ) {
    this.idParam = idParam;
  }
  
  public Parametro getParam () {
    return param;
  }
  
  public void setParam ( Parametro param ) {
    this.param = param;
  }
  
  public String getHtml () {
    return html;
  }
  
  public void setHtml ( String html ) {
    this.html = html;
  }
  
}
