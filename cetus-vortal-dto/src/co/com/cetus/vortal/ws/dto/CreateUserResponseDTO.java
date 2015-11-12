package co.com.cetus.vortal.ws.dto;

import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.vortal.business.dto.UserDTO;

public class CreateUserResponseDTO {
  
  private UserDTO       usuarioDTO;
  private ResponseWSDTO response;
  
  public UserDTO getUsuarioDTO () {
    return usuarioDTO;
  }
  
  public void setUsuarioDTO ( UserDTO usuarioDTO ) {
    this.usuarioDTO = usuarioDTO;
  }
  
  public ResponseWSDTO getResponse () {
    return response;
  }
  
  public void setResponse ( ResponseWSDTO response ) {
    this.response = response;
  }
  
}
