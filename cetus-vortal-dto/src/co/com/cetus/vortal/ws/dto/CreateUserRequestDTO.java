package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import co.com.cetus.common.dto.UserWSDTO;
import co.com.cetus.vortal.business.dto.UserDTO;

public class CreateUserRequestDTO extends UserWSDTO implements Serializable {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private UserDTO usuarioDTO;
  
  private String     rol;
  
  public CreateUserRequestDTO () {
  }
  
  public UserDTO getUsuarioDTO () {
    return usuarioDTO;
  }
  
  public void setUsuarioDTO ( UserDTO usuarioDTO ) {
    this.usuarioDTO = usuarioDTO;
  }
  
  public String getRol () {
    return rol;
  }
  
  public void setRol ( String rol ) {
    this.rol = rol;
  }
  
}