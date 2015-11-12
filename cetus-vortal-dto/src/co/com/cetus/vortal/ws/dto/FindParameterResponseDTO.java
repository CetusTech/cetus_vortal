package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;
import java.util.List;

import co.com.cetus.common.dto.ParameterDTO;
import co.com.cetus.common.dto.ResponseWSDTO;

/**
 * The Class FindParameterResponseDTO.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-dto (29/09/2013)
 */
public class FindParameterResponseDTO implements Serializable {
  
  private static final long    serialVersionUID = 1L;
  
  private ResponseWSDTO        responseWSDTO;
  private List< ParameterDTO > parameters;
  
  public FindParameterResponseDTO () {
  }
  
  public ResponseWSDTO getResponseWSDTO () {
    return responseWSDTO;
  }
  
  public void setResponseWSDTO ( ResponseWSDTO responseWSDTO ) {
    this.responseWSDTO = responseWSDTO;
  }
  
  public List< ParameterDTO > getParameters () {
    return parameters;
  }
  
  public void setParameters ( List< ParameterDTO > parameters ) {
    this.parameters = parameters;
  }

}
