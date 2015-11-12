package co.com.cetus.portal.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import co.com.cetus.common.exception.ServiceException;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.portal.web.bean.GeneralManagedBean;
import co.com.cetus.vortal.jpa.entity.TipoEstilo;

@ManagedBean ( name = "themeService", eager = true )
//@ApplicationScoped
public class ThemeService extends GeneralManagedBean {
  /**
   * 
   */
  private static final long  serialVersionUID = 4325051197077624456L;
  private List< Theme >      themes;
  private List< TipoEstilo > listTipoEstilo;
  
  @PostConstruct
  public void init () {
    this.themes = new ArrayList< Theme >();
    
    try {
      this.listTipoEstilo = this.delegate.findAllOrder( TipoEstilo.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      if ( listTipoEstilo != null ) {
        for ( TipoEstilo tipoEstilo: listTipoEstilo ) {
          this.themes.add( new Theme( tipoEstilo.getId(), tipoEstilo.getNombre(), tipoEstilo.getParamXml() ) );
        }
        
        System.out.println( "#################### ThemeServices Cargados   " + this.themes.size() );
      }
    } catch ( ServiceException e ) {
      e.printStackTrace();
    }
    
  }
  
  public List< Theme > getThemes () {
    return this.themes;
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