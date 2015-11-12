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

import co.com.cetus.common.validation.UniqueConstraint;
import co.com.cetus.common.validation.UniqueConstraints;

/***
 * \u00e1 -> á
\u00e9 -> é
\u00ed -> í
\u00f3 -> ó
\u00fa -> ú
\u00c1 -> �?
\u00c9 -> É
\u00cd -> �?
\u00d3 -> Ó
\u00da -> Ú
\u00f1 -> ñ
\u00d1 -> Ñ
 */

/**
 * The persistent class for the tb_rol database table.
 * 
 */
@Entity
@Table ( name = "TB_ROL" )
@UniqueConstraints ( uniqueConstraints = { @UniqueConstraint ( name = "DESCRIPCION", fields = { "descripcion" }, errorMessage = "La Descripci\u00f3n que esta ingresando ya existe." )
} )
public class Rol implements Serializable {
  private static final long  serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                id;
  
  private String             descripcion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date               fechaCreacion;
  
  @Column ( name = "USUARIO_CREACION" )
  private String             usuarioCreacion;
  
  // bi-directional many-to-one association to RolMenu
  @OneToMany ( mappedBy = "tbRol" )
  private List< RolMenu >    tbRolMenus;
  
  // bi-directional many-to-one association to UsuarioRol
  @OneToMany ( mappedBy = "tbRol" )
  private List< UsuarioRol > tbUsuarioRols;
  
  public Rol () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public String getDescripcion () {
    return this.descripcion;
  }
  
  public void setDescripcion ( String descripcion ) {
    this.descripcion = descripcion;
  }
  
  public Date getFechaCreacion () {
    return this.fechaCreacion;
  }
  
  public void setFechaCreacion ( Date fechaCreacion ) {
    this.fechaCreacion = fechaCreacion;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public List< RolMenu > getTbRolMenus () {
    return this.tbRolMenus;
  }
  
  public void setTbRolMenus ( List< RolMenu > tbRolMenus ) {
    this.tbRolMenus = tbRolMenus;
  }
  
  public List< UsuarioRol > getTbUsuarioRols () {
    return this.tbUsuarioRols;
  }
  
  public void setTbUsuarioRols ( List< UsuarioRol > tbUsuarioRols ) {
    this.tbUsuarioRols = tbUsuarioRols;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof Rol ) {
      if ( this.id == ( ( Rol ) obj ).getId() ) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode () {
    return ( int ) this.id;
  }
}