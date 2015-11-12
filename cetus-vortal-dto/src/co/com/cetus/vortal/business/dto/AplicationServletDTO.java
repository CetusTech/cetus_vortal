package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_aplication_servlet database table.
 * 
 */
public class AplicationServletDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int               id;
  
  private Date              dateCreation;
  
  private String            userCreation;
  
  private ServletDTO        tbServlet;
  
  private AplicacionDTO     tbAplicacion;
  
  private List< MenuDTO >   tbMenus;
  
  public AplicationServletDTO () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public Date getDateCreation () {
    return this.dateCreation;
  }
  
  public void setDateCreation ( Date dateCreation ) {
    this.dateCreation = dateCreation;
  }
  
  public String getUserCreation () {
    return this.userCreation;
  }
  
  public void setUserCreation ( String userCreation ) {
    this.userCreation = userCreation;
  }
  
  public ServletDTO getTbServlet () {
    return this.tbServlet;
  }
  
  public void setTbServlet ( ServletDTO tbServlet ) {
    this.tbServlet = tbServlet;
  }
  
  public AplicacionDTO getTbAplicacion () {
    return this.tbAplicacion;
  }
  
  public void setTbAplicacion ( AplicacionDTO tbAplicacion ) {
    this.tbAplicacion = tbAplicacion;
  }
  
  public List< MenuDTO > getTbMenus () {
    return this.tbMenus;
  }
  
  public void setTbMenus ( List< MenuDTO > tbMenus ) {
    this.tbMenus = tbMenus;
  }
  
}