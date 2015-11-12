package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_component database table.
 * 
 */
public class ComponentDTO implements Serializable {
  private static final long    serialVersionUID = 1L;
  
  private int                  id;
  
  private Date                 dateCreation;
  
  private Date                 dateUpdate;
  
  private String               description;
  
  private String               name;
  
  private String               userCreation;
  
  private String               userUpdate;
  
  private AplicacionDTO        tbAplicacion;
  
  private List< ParametroDTO > tbParametros;
  
  public ComponentDTO () {
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
  
  public Date getDateUpdate () {
    return this.dateUpdate;
  }
  
  public void setDateUpdate ( Date dateUpdate ) {
    this.dateUpdate = dateUpdate;
  }
  
  public String getDescription () {
    return this.description;
  }
  
  public void setDescription ( String description ) {
    this.description = description;
  }
  
  public String getName () {
    return this.name;
  }
  
  public void setName ( String name ) {
    this.name = name;
  }
  
  public String getUserCreation () {
    return this.userCreation;
  }
  
  public void setUserCreation ( String userCreation ) {
    this.userCreation = userCreation;
  }
  
  public String getUserUpdate () {
    return this.userUpdate;
  }
  
  public void setUserUpdate ( String userUpdate ) {
    this.userUpdate = userUpdate;
  }
  
  public AplicacionDTO getTbAplicacion () {
    return this.tbAplicacion;
  }
  
  public void setTbAplicacion ( AplicacionDTO tbAplicacion ) {
    this.tbAplicacion = tbAplicacion;
  }
  
  public List< ParametroDTO > getTbParametros () {
    return this.tbParametros;
  }
  
  public void setTbParametros ( List< ParametroDTO > tbParametros ) {
    this.tbParametros = tbParametros;
  }
  
}