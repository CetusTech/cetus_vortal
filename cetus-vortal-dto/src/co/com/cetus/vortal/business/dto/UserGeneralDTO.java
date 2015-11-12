package co.com.cetus.vortal.business.dto;

import java.io.Serializable;


public class UserGeneralDTO implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 86623191451430453L;
  private UserDTO           user;
  private String            ip;
  private String            hostName;
  
  public UserDTO getUser () {
    return user;
  }
  
  public void setUser ( UserDTO user ) {
    this.user = user;
  }
  
  public String getIp () {
    return ip;
  }
  
  public void setIp ( String ip ) {
    this.ip = ip;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public String getHostName () {
    return hostName;
  }
  
  public void setHostName ( String hostName ) {
    this.hostName = hostName;
  }
  
  @Override
  public String toString () {
    return null;
  }
  
}
