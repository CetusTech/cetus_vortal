package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_servlet database table.
 * 
 */
@Entity
@Table ( name = "TB_SERVLET" )
public class Servlet implements Serializable {
  private static final long         serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                       id;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_CREATION" )
  private Date                      dateCreation;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_UPDATE" )
  private Date                      dateUpdate;
  
  private String                    description;
  
  private String                    name;
  
  private String                    parameter;
  
  private String                    status;
  
  @Column ( name = "USER_CREATION" )
  private String                    userCreation;
  
  @Column ( name = "USER_UPDATE" )
  private String                    userUpdate;
  
  //bi-directional many-to-one association to AplicationServlet
  @OneToMany ( mappedBy = "tbServlet" )
  private List< AplicationServlet > tbAplicationServlets;
  
  public Servlet () {
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
  
  public String getParameter () {
    return this.parameter;
  }
  
  public void setParameter ( String parameter ) {
    this.parameter = parameter;
  }
  
  public String getStatus () {
    return this.status;
  }
  
  public void setStatus ( String status ) {
    this.status = status;
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
  
  public List< AplicationServlet > getTbAplicationServlets () {
    return this.tbAplicationServlets;
  }
  
  public void setTbAplicationServlets ( List< AplicationServlet > tbAplicationServlets ) {
    this.tbAplicationServlets = tbAplicationServlets;
  }
  
}