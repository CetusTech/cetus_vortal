package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table ( name = "TB_USUARIO" )
@NamedQueries ( {
                 @NamedQuery ( name = "validatedLogin", query = "select u from Usuario u where u.login = :login and u.password = :password and u.status = 1" ),// 1 activo 2 inactivo
                 @NamedQuery ( name = "Usuario.findByLogin", query = "select u from Usuario u where u.login = :login" ),
                 @NamedQuery ( name = "Usuario.findRolByApplication", query = "select DISTINCT r from Aplicacion a, AplicationServlet ass, RolMenu rm, Menu m, Rol r where a.id = ass.tbAplicacion.id and ass.id = m.tbAplicationServlet.id and m.id = rm.tbMenu.id and r.id = rm.tbRol.id and a.id = :idAppication" ),
                 @NamedQuery ( name = "Usuario.findRolByLogin", query = "select r from Usuario u , UsuarioRol ur, Rol r where u.id = ur.tbUsuario.id and r.id = ur.tbRol.id and u.login = :login  " ),
                 @NamedQuery ( name = "Usuario.getUserSessio", query = "select u from Usuario u where u.id = :id" ) } )
@SqlResultSetMappings ( { @SqlResultSetMapping ( name = "alertMap", columns = { @ColumnResult ( name = "id" ) } ) } )
@NamedNativeQueries ( {
                       @NamedNativeQuery ( name = "Usuario.validateUserApp", query = "select distinct count(m.id) as id\n"
                                                                                     + "from\n"
                                                                                     + "TB_APLICACION a\n"
                                                                                     + "inner join TB_APLICATION_SERVLET tas on tas.id_application = a.id "
                                                                                     + "inner join TB_MENU m on m.id_aplicacion_servlet = tas.id\n"
                                                                                     + "inner join TB_ROL_MENU rm on rm.menu_id = m.id\n"
                                                                                     + "inner join TB_ROL r on r.id = rm.rol_id\n"
                                                                                     + "inner join TB_USUARIO_ROL ur on ur.rol_id = r.id\n"
                                                                                     + "inner join TB_USUARIO u on u.id = ur.usuario_id\n"
                                                                                     + "where u.login = :login and a.id = :idApp and u.status = 1", resultSetMapping = "alertMap" ),
                       @NamedNativeQuery ( name = "Usuario.validateUserApp2", query = "select distinct count(m.id) as id\n"
                                                                                      + "from\n"
                                                                                      + "TB_APLICACION a\n"
                                                                                      + "inner join TB_APLICATION_SERVLET tas on tas.id_application = a.id "
                                                                                      + "inner join TB_MENU m on m.id_aplicacion_servlet = tas.id\n"
                                                                                      + "inner join TB_ROL_MENU rm on rm.menu_id = m.id\n"
                                                                                      + "inner join TB_ROL r on r.id = rm.rol_id\n"
                                                                                      + "inner join TB_USUARIO_ROL ur on ur.rol_id = r.id\n"
                                                                                      + "inner join TB_USUARIO u on u.id = ur.usuario_id\n"
                                                                                      + "where u.login = :login and u.status = 1", resultSetMapping = "alertMap" ),
} )
public class Usuario implements Serializable {
  private static final long  serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                id;
  
  private String             descripcion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date               fechaCreacion;
  
  private String             identificacion;
  
  private String             login;
  
  private String             password;
  
  @Column ( name = "USUARIO_CREACION" )
  private String             usuarioCreacion;
  
  // bi-directional many-to-one association to UsuarioRol
  @OneToMany ( mappedBy = "tbUsuario" )
  private List< UsuarioRol > tbUsuarioRols;
  
  private String             email;
  private String             phone;
  private String             address;
  
  private int                status;
  
  public Usuario () {
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
  
  public String getIdentificacion () {
    return this.identificacion;
  }
  
  public void setIdentificacion ( String identificacion ) {
    this.identificacion = identificacion;
  }
  
  public String getLogin () {
    return this.login;
  }
  
  public void setLogin ( String login ) {
    this.login = login;
  }
  
  public String getPassword () {
    return this.password;
  }
  
  public void setPassword ( String password ) {
    this.password = password;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public List< UsuarioRol > getTbUsuarioRols () {
    return this.tbUsuarioRols;
  }
  
  public void setTbUsuarioRols ( List< UsuarioRol > tbUsuarioRols ) {
    this.tbUsuarioRols = tbUsuarioRols;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof Usuario ) {
      if ( this.id == ( ( Usuario ) obj ).getId() ) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public int hashCode () {
    return ( int ) this.id;
  }
  
  public String getEmail () {
    return email;
  }
  
  public void setEmail ( String email ) {
    this.email = email;
  }
  
  public String getPhone () {
    return phone;
  }
  
  public void setPhone ( String phone ) {
    this.phone = phone;
  }
  
  public String getAddress () {
    return address;
  }
  
  public void setAddress ( String address ) {
    this.address = address;
  }
  
  public int getStatus () {
    return status;
  }
  
  public void setStatus ( int status ) {
    this.status = status;
  }
  
}