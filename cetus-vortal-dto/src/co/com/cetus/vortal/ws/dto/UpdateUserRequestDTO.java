package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import co.com.cetus.common.dto.UserWSDTO;
import co.com.cetus.vortal.business.dto.UserDTO;

/**
 * The Class UpdateUserRequestDTO.
 *
 * @author Andres Herrera - Cetus Technology
 * @version cetus-vortal-dto (4/04/2015)
 */
public class UpdateUserRequestDTO extends UserWSDTO implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The usuario dto. */
  private UserDTO           usuarioDTO;
  
  /** The rol. */
  private String            rol;
  
  /**
   * </p> Instancia un nuevo update user request dto. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-dto (4/04/2015)
   */
  public UpdateUserRequestDTO () {
  }
  
  /**
   * </p> Gets the usuario dto. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @return el user dto
   * @since cetus-vortal-dto (4/04/2015)
   */
  public UserDTO getUsuarioDTO () {
    return usuarioDTO;
  }
  
  /**
   * </p> Sets the usuario dto. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @param usuarioDTO the usuario dto
   * @since cetus-vortal-dto (4/04/2015)
   */
  public void setUsuarioDTO ( UserDTO usuarioDTO ) {
    this.usuarioDTO = usuarioDTO;
  }
  
  /**
   * </p> Gets the rol. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @return el string
   * @since cetus-vortal-dto (4/04/2015)
   */
  public String getRol () {
    return rol;
  }
  
  /**
   * </p> Sets the rol. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @param rol the rol
   * @since cetus-vortal-dto (4/04/2015)
   */
  public void setRol ( String rol ) {
    this.rol = rol;
  }
  
}