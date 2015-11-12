package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import co.com.cetus.common.dto.UserWSDTO;

/**
 * The Class FindParameterRequestDTO.
 *
 * @author Jose David Salcedo Mandon - Cetus Technology
 * @version cetus-vortal-dto (29/09/2013)
 */
public class FindParameterRequestDTO extends UserWSDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String            nameComponent;
  
  private int               idApplication;
  
  public FindParameterRequestDTO () {
  }
  
  public String getNameComponent () {
    return nameComponent;
  }
  
  public void setNameComponent ( String nameComponent ) {
    this.nameComponent = nameComponent;
  }
  
  public int getIdApplication () {
    return idApplication;
  }
  
  public void setIdApplication ( int idApplication ) {
    this.idApplication = idApplication;
  }
  
  @Override
  public String toString () {
    return "FindParameterRequestDTO [nameComponent=" + nameComponent + ", idApplication=" + idApplication + "]";
  }
  
}