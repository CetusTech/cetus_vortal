package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;
import java.util.List;

import co.com.cetus.vortal.business.dto.RolDTO;
import co.com.cetus.common.dto.ResponseWSDTO;

public class FindRolsByApplicationResponseDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private ResponseWSDTO     responseWSDTO;
  private List< RolDTO >    rol;
  
  public FindRolsByApplicationResponseDTO () {
  }
  
  public ResponseWSDTO getResponseWSDTO () {
    return responseWSDTO;
  }
  
  public void setResponseWSDTO ( ResponseWSDTO responseWSDTO ) {
    this.responseWSDTO = responseWSDTO;
  }
  
  public List< RolDTO > getRol () {
    return rol;
  }
  
  public void setRol ( List< RolDTO > rol ) {
    this.rol = rol;
  }
  
}
