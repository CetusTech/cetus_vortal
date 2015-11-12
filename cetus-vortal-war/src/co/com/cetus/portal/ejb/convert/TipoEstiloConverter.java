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
package co.com.cetus.portal.ejb.convert;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import co.com.cetus.portal.web.bean.GeneralManagedBean;
import co.com.cetus.vortal.jpa.entity.TipoEstilo;
import co.com.cetus.common.exception.ServiceException;

@FacesConverter ( forClass = TipoEstilo.class, value = "tipoEstiloConverter" )
public class TipoEstiloConverter extends GeneralManagedBean implements Converter {
  
  /**
   * 
   */
  private static final long        serialVersionUID = -9033469808337527709L;
  public static List< TipoEstilo > recordDB;
  
  static {
    recordDB = new ArrayList< TipoEstilo >();
    
  }
  
  public Object getAsObject ( FacesContext pFacesContext, UIComponent pComponent, String pSubmittedValue ) {
    TipoEstilo temp = null;
    if ( pSubmittedValue.trim().equals( "" ) ) {
      return null;
    } else {
      try {
        int number = Integer.parseInt( pSubmittedValue );
        
        try {
          temp = this.delegate.find( TipoEstilo.class, number );
        } catch ( ServiceException e ) {
          e.printStackTrace();
        }
        
      } catch ( NumberFormatException exception ) {
        throw new ConverterException( new FacesMessage(
                                                        FacesMessage.SEVERITY_ERROR, "Conversion Error",
                                                        "Not a valid player" ) );
      }
    }
    
    return temp;
  }
  
  public String getAsString ( FacesContext facesContext, UIComponent component, Object value ) {
    if ( value == null || value.equals( "" ) ) {
      return "";
    } else {
      return String.valueOf( ( ( TipoEstilo ) value ).getId() );
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
