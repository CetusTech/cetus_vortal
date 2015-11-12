/*
 * Copyright 2009 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.com.cetus.portal.web.converter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import co.com.cetus.portal.web.delegate.GeneralDelegate;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.common.util.ConstantCommon;

@FacesConverter ( value = "menu" )
public class MenuConverter implements Converter {
  
  public static GeneralDelegate delegate;
  
  public static List< Menu >    objecto;
  
  static {
    delegate = new GeneralDelegate();
    
    try {
      objecto = delegate.findAllOrder( Menu.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
  }
  
  public Object getAsObject ( FacesContext facesContext, UIComponent component, String submittedValue ) {
    if ( submittedValue.trim().equals( "" ) ) {
      return null;
    } else {
      try {
        int number = Integer.parseInt( submittedValue );
        for ( Menu p: objecto ) {
          if ( p.getId() == number ) {
            return p;
          }
        }
      } catch ( NumberFormatException exception ) {
        throw new ConverterException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player" ) );
      }
    }
    return null;
  }
  
  public String getAsString ( FacesContext facesContext, UIComponent component, Object value ) {
    if ( value == null || value.equals( "" ) ) {
      return "";
    } else {
      return String.valueOf( ( ( Menu ) value ).getId() );
    }
  }
}
