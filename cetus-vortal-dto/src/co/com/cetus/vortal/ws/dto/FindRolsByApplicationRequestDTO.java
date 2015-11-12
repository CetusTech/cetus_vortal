package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import co.com.cetus.common.dto.UserWSDTO;


/**
 * The Class FindRolsByApplicationRequestDTO.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-dto (6/10/2013)
 */
public class FindRolsByApplicationRequestDTO extends UserWSDTO implements Serializable {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The id application. */
  private int               idApplication;
  
  /**
   * </p> Instancia un nuevo find rols by application request dto. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-dto (6/10/2013)
   */
  public FindRolsByApplicationRequestDTO () {
  }
  
  public int getIdApplication () {
    return idApplication;
  }
  
  public void setIdApplication ( int idApplication ) {
    this.idApplication = idApplication;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString () {
    return "FindRolsByApplicationRequestDTO [ idApplication=" + idApplication + "]";
  }
  
}