package co.com.cetus.vortal.ws.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import co.com.cetus.common.dto.UserWSDTO;

@XmlRootElement
@XmlAccessorType ( XmlAccessType.FIELD )
public class ValidPermServiceRequestDTO extends UserWSDTO implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  private String            service;
  
  private int               application;
  
  public ValidPermServiceRequestDTO () {
  }
  
  public String getService () {
    return service;
  }
  
  public void setService ( String service ) {
    this.service = service;
  }
  
  public int getApplication () {
    return application;
  }
  
  public void setApplication ( int application ) {
    this.application = application;
  }
  
  @Override
  public String toString () {
    return "ValidPermServiceRequestDTO [service=" + service + ", application=" + application + "]";
  }
  
}