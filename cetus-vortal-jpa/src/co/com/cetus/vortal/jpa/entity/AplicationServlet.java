package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_aplication_servlet database table.
 * 
 */
@Entity
@NamedQueries ( { @NamedQuery ( name = "AplicationServlet.findAplicationServletByIdApp", query = "select s from AplicationServlet s, Aplicacion a where a.id = s.tbAplicacion.id and a.id = :idApp" ) } )
@Table ( name = "TB_APLICATION_SERVLET" )
public class AplicationServlet implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_CREATION" )
  private Date              dateCreation;
  
  @Column ( name = "USER_CREATION" )
  private String            userCreation;
  
  //bi-directional many-to-one association to Servlet
  @ManyToOne
  @JoinColumn ( name = "ID_SERVLET" )
  private Servlet           tbServlet;
  
  //bi-directional many-to-one association to Aplicacion
  @ManyToOne
  @JoinColumn ( name = "ID_APPLICATION" )
  private Aplicacion        tbAplicacion;
  
  //bi-directional many-to-one association to TbMenu
  @OneToMany ( mappedBy = "tbAplicationServlet" )
  private List< Menu >      tbMenus;
  
  @OneToMany ( mappedBy = "appSerId" )
  private List< GeneralSearch >      generalSearch;
  
  public AplicationServlet () {
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
  
  public Servlet getTbServlet () {
    return this.tbServlet;
  }
  
  public void setTbServlet ( Servlet tbServlet ) {
    this.tbServlet = tbServlet;
  }
  
  public Aplicacion getTbAplicacion () {
    return this.tbAplicacion;
  }
  
  public void setTbAplicacion ( Aplicacion tbAplicacion ) {
    this.tbAplicacion = tbAplicacion;
  }
  
  public List< Menu > getTbMenus () {
    return this.tbMenus;
  }
  
  public void setTbMenus ( List< Menu > tbMenus ) {
    this.tbMenus = tbMenus;
  }

  public List< GeneralSearch > getGeneralSearch () {
    return generalSearch;
  }

  public void setGeneralSearch ( List< GeneralSearch > generalSearch ) {
    this.generalSearch = generalSearch;
  }
  
  
}