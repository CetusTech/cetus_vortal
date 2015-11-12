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
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.common.util.ConstantCommon;

@FacesConverter ( "applicationConvert" )
public class AplicacionConverter implements Converter {
  
  public static GeneralDelegate    delegate;
  
  public static List< Aplicacion > application;
  
  static {
    delegate = new GeneralDelegate();
    try {
      if ( delegate != null ) {
        application = delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.NOMBRE_PROPERTIES_NEGOCIO,
                                             ConstantCommon.TIPO_ASC );
      }
      else {
        System.out.println( "Error: EL Delegate es nulo, Revisar el log para validar que ha pasado" );
      }
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
  }
  
  public Object getAsObject ( FacesContext facesContext, UIComponent component, String submittedValue ) {
    Aplicacion app = new Aplicacion();
    if ( submittedValue.trim().equals( "" ) ) {
      return null;
    } else {
      try {
        int number = Integer.parseInt( submittedValue );
        for ( Aplicacion p: application ) {
          if ( p.getId() == number ) {
            return p;
          }
        }
        if ( number == 0 ) {
          //Es una aplicacion nula 
          app.setId( 0 );
          app.setNombre( Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.LABEL_SELECT_APPLICATION ) );
          return app;
        }
      } catch ( NumberFormatException exception ) {
        throw new ConverterException( new FacesMessage( FacesMessage.SEVERITY_ERROR, "Conversion Error", "Aplicacion no valida" ) );
      }
    }
    
    return null;
  }
  
  public String getAsString ( FacesContext facesContext, UIComponent component, Object value ) {
    if ( value == null || value.equals( "" ) ) {
      return "";
    } else {
      return String.valueOf( ( ( Aplicacion ) value ).getId() );
    }
  }
}
