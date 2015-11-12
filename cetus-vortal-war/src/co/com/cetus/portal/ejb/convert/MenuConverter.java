package co.com.cetus.portal.ejb.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.com.cetus.portal.web.bean.GeneralManagedBean;
import co.com.cetus.vortal.business.dto.MenuDTO;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.common.exception.ServiceException;

@FacesConverter ( forClass = MenuDTO.class, value = "menuPickListConverter" )
public class MenuConverter extends GeneralManagedBean implements Converter {
  protected co.com.cetus.common.util.Converter converter        = null;
  /**
   * 
   */
  private static final long                        serialVersionUID = -8570888805597056868L;
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
   * , javax.faces.component.UIComponent, java.lang.String)
   */
  @Override
  public Object getAsObject ( FacesContext arg0, UIComponent arg1, String value ) {
    MenuDTO dto = new MenuDTO();
    dto.setId( Integer.parseInt( value ) );
    
    try {
      converter = new co.com.cetus.common.util.Converter( "co.com.cetus.vortal.business.dto.", "co.com.cetus.vortal.jpa.entity." );
      converter.convertEntityToDto( this.delegate.find( Menu.class, dto.getId() ), dto, false );
      
    } catch ( ServiceException e ) {
      e.printStackTrace();
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
    return dto;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see
   * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
   * , javax.faces.component.UIComponent, java.lang.Object)
   */
  @Override
  public String getAsString ( FacesContext arg0, UIComponent arg1, Object value ) {
    return String.valueOf( ( ( MenuDTO ) value ).getId() );
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
