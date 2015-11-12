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
 * The persistent class for the tb_service database table.
 * 
 */
@Entity
@Table ( name = "TB_SERVICE" )

@NamedQueries ( { @NamedQuery ( name = "Service.findAllServiceByApplication", query = "select s from Service s where s.tbAplicacion.id = :idApp" ) } )

public class Service implements Serializable {
  private static final long  serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                id;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_CREATION" )
  private Date               dateCreation;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "DATE_UPDATE" )
  private Date               dateUpdate;
  
  private String             name;
  
  private int                status;
  
  @Column ( name = "USER_CREATION" )
  private String             userCreation;
  
  @Column ( name = "USER_UPDATE" )
  private String             userUpdate;
  
  private String             wsdl;
  
  @ManyToOne
  @JoinColumn ( name = "APLICACION_ID" )
  private Aplicacion         tbAplicacion;
  
  //bi-directional many-to-one association to Permission
  @OneToMany ( mappedBy = "tbService" )
  private List< Permission > tbPermissions;
  
  public Service () {
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
  
  public String getName () {
    return this.name;
  }
  
  public void setName ( String name ) {
    this.name = name;
  }
  
  public int getStatus () {
    return this.status;
  }
  
  public void setStatus ( int status ) {
    this.status = status;
  }
  
  public Aplicacion getTbAplicacion () {
    return tbAplicacion;
  }
  
  public void setTbAplicacion ( Aplicacion tbAplicacion ) {
    this.tbAplicacion = tbAplicacion;
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
  
  public String getWsdl () {
    return this.wsdl;
  }
  
  public void setWsdl ( String wsdl ) {
    this.wsdl = wsdl;
  }
  
  public List< Permission > getTbPermissions () {
    return this.tbPermissions;
  }
  
  public void setTbPermissions ( List< Permission > tbPermissions ) {
    this.tbPermissions = tbPermissions;
  }
  
  public Permission addTbPermission ( Permission tbPermission ) {
    getTbPermissions().add( tbPermission );
    tbPermission.setTbService( this );
    
    return tbPermission;
  }
  
  public Permission removeTbPermission ( Permission tbPermission ) {
    getTbPermissions().remove( tbPermission );
    tbPermission.setTbService( null );
    
    return tbPermission;
  }
  
}