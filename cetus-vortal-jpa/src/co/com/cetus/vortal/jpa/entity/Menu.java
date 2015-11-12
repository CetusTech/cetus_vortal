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

import co.com.cetus.common.validation.UniqueConstraint;
import co.com.cetus.common.validation.UniqueConstraints;
import co.com.cetus.vortal.jpa.entity.AplicationServlet;

/**
 * The persistent class for the tb_menu database table.
 * 
 */
@Entity
@Table ( name = "TB_MENU" )
//@UniqueConstraints ( uniqueConstraints = { @UniqueConstraint ( name = "URL_UNIQUE", fields = { "url" }, errorMessage = "La URL del Menu es Unico" ) } )
@NamedQueries ( {
                 @NamedQuery ( name = "findMenuByIdPadre", query = "SELECT distinct m FROM Aplicacion a , Menu m , RolMenu rm , Rol r , UsuarioRol ur, Usuario u where m.tbAplicationServlet.tbAplicacion.id = a.id and m.id = rm.tbMenu.id and rm.tbRol.id = r.id and r.id = ur.tbRol.id and u.id = ur.tbUsuario.id and m.tbMenu.id = :idPadre and u.login = :login and a.id = :aplicacion  order by m.nombre  " ),
                 @NamedQuery ( name = "findMenuByLoginAndApplication", query = " select m from Menu m where m.id in "
                                                                               + "(SELECT distinct m.id FROM Aplicacion a, Menu m, RolMenu rm, Rol r , UsuarioRol ur, Usuario u, AplicationServlet ase  where a.id = ase.tbAplicacion.id and m.tbAplicationServlet.tbAplicacion.id = a.id and m.id = rm.tbMenu.id "
                                                                               + "and rm.tbRol.id = r.id and r.id = ur.tbRol.id and u.id = ur.tbUsuario.id and a.id = :aplicacion and u.login = :login) "
                                                                               + "or m.id in (select m.id from Menu m where m.tbAplicationServlet.tbAplicacion.id = :aplicacion and m.tbMenu.id is null  ) " ),
                 @NamedQuery ( name = "Menu.findAllMenu", query = "select  m from Menu m where m.tbMenu.id is not null order by m.nombre" ),
                 @NamedQuery ( name = "Menu.handleChangeApplication", query = "select m from Menu m where m.tbAplicationServlet.tbAplicacion.id = :idApp" ),
                 @NamedQuery ( name = "Menu.findMenuByIdServlet", query = "select m from Menu m,AplicationServlet s  where s.id = m.tbAplicationServlet.id and s.id = :idServ" ),
                 @NamedQuery ( name = "Menu.handleChangeApplicationRolMenu", query = "select m from Menu m where m.tbAplicationServlet.tbAplicacion.id = :idApp and m.tbMenu.id is not null" ) } )
public class Menu implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
  
  private String            acronimo;
  
  private String            descripcion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date              fechaCreacion;
  
  private String            nombre;
  
  private String            url;
  
  @Column ( name = "USUARIO_CREACION" )
  private String            usuarioCreacion;
  
  //bi-directional many-to-one association to AplicationServlet
  @ManyToOne
  @JoinColumn ( name = "ID_APLICACION_SERVLET" )
  private AplicationServlet tbAplicationServlet;
  
  // bi-directional many-to-one association to Menu
  @ManyToOne
  @JoinColumn ( name = "ID_PADRE" )
  private Menu              tbMenu;
  
  // bi-directional many-to-one association to Menu
  @OneToMany ( mappedBy = "tbMenu" )
  private List< Menu >      tbMenus;
  
  // bi-directional many-to-one association to RolMenu
  @OneToMany ( mappedBy = "tbMenu" )
  private List< RolMenu >   tbRolMenus;
  
  public Menu () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public String getAcronimo () {
    return this.acronimo;
  }
  
  public void setAcronimo ( String acronimo ) {
    this.acronimo = acronimo;
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
  
  public String getNombre () {
    return this.nombre;
  }
  
  public void setNombre ( String nombre ) {
    this.nombre = nombre;
  }
  
  public String getUrl () {
    return this.url;
  }
  
  public void setUrl ( String url ) {
    this.url = url;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public Menu getTbMenu () {
    return this.tbMenu;
  }
  
  public void setTbMenu ( Menu tbMenu ) {
    this.tbMenu = tbMenu;
  }
  
  public List< Menu > getTbMenus () {
    return this.tbMenus;
  }
  
  public void setTbMenus ( List< Menu > tbMenus ) {
    this.tbMenus = tbMenus;
  }
  
  public List< RolMenu > getTbRolMenus () {
    return this.tbRolMenus;
  }
  
  public void setTbRolMenus ( List< RolMenu > tbRolMenus ) {
    this.tbRolMenus = tbRolMenus;
  }
  
  public AplicationServlet getTbAplicationServlet () {
    return tbAplicationServlet;
  }
  
  public void setTbAplicationServlet ( AplicationServlet tbAplicationServlet ) {
    this.tbAplicationServlet = tbAplicationServlet;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof Menu ) {
      if ( this.id == ( ( Menu ) obj ).getId() ) {
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