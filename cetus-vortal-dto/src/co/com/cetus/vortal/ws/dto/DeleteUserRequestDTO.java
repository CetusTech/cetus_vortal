package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import co.com.cetus.common.dto.UserWSDTO;

public class DeleteUserRequestDTO extends UserWSDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private int               idUsuario;
  
  private String            login;
  
  private String            mail;
  
  public DeleteUserRequestDTO () {
  }
  
  public int getIdUsuario () {
    return idUsuario;
  }
  
  public void setIdUsuario ( int idUsuario ) {
    this.idUsuario = idUsuario;
  }
  
  public String getLogin () {
    return login;
  }
  
  public void setLogin ( String login ) {
    this.login = login;
  }
  
  public String getMail () {
    return mail;
  }
  
  public void setMail ( String mail ) {
    this.mail = mail;
  }
  
}