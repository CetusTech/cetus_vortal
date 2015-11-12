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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tb_aplicacion database table.
 * 
 */
@Entity
@Table ( name = "TB_APLICACION" )
@NamedQueries ( { @NamedQuery ( name = "Aplicacion.findAllAplicacionesByLogin", query = "SELECT distinct a FROM Aplicacion a , "
                                                                                        + "Menu m , RolMenu rm , Rol r , UsuarioRol ur, Usuario u where m.tbAplicationServlet.tbAplicacion.id = a.id and m.id = rm.tbMenu.id "
                                                                                        + "and rm.tbRol.id = r.id and r.id = ur.tbRol.id "
                                                                                        + "and u.id = ur.tbUsuario.id and u.login = :login  order by a.nombre" ) } )
public class Aplicacion implements Serializable {
  private static final long         serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                       id;
  
  private String                    descripcion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date                      fechaCreacion;
  
  private String                    nombre;
  
  @Column ( name = "URL_SERVER" )
  private String                    urlServer;
  
  @Column ( name = "USUARIO_CREACION" )
  private String                    usuarioCreacion;
  
  // bi-directional many-to-one association to TipoEstilo
  @ManyToOne
  @JoinColumn ( name = "ID_ESTILO" )
  private TipoEstilo                tbTipoEstilo;
  
  @Column ( name = "IMAGEN_CUERPO" )
  @Lob
  private byte[]                    imagenCuerpo;
  
  @Lob
  private byte[]                    logo;
  
  @Column ( name = "COLOR_RGB" )
  private String                    colorRgb;
  
  @OneToMany ( mappedBy = "tbAplicacion" )
  private List< Service >           tbService;
  
  //bi-directional many-to-one association to Component
  @OneToMany ( mappedBy = "tbAplicacion" )
  private List< Component >         tbComponents;
  
  //bi-directional many-to-one association to AplicationServlet
  @OneToMany ( mappedBy = "tbAplicacion" )
  private List< AplicationServlet > tbAplicationServlets;
  
  public Aplicacion () {
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
  
  public String getNombre () {
    return this.nombre;
  }
  
  public void setNombre ( String nombre ) {
    this.nombre = nombre;
  }
  
  public String getUrlServer () {
    return this.urlServer;
  }
  
  public void setUrlServer ( String urlServer ) {
    this.urlServer = urlServer;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public TipoEstilo getTbTipoEstilo () {
    return this.tbTipoEstilo;
  }
  
  public void setTbTipoEstilo ( TipoEstilo tbTipoEstilo ) {
    this.tbTipoEstilo = tbTipoEstilo;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj == null ) return false;
    if ( ! ( obj instanceof Aplicacion ) ) return false;
    
    return ( ( Aplicacion ) obj ).getId() == this.id;
  }
  
  @Override
  public int hashCode () {
    int hash = 1;
    return hash * 31 + nombre.hashCode();
  }
  
  @Override
  public String toString () {
    return ( tbTipoEstilo != null ? tbTipoEstilo.getParamXml() : null );
  }
  
  public byte[] getLogo () {
    return logo;
  }
  
  public void setLogo ( byte[] logo ) {
    this.logo = logo;
  }
  
  public String getColorRgb () {
    return colorRgb;
  }
  
  public void setColorRgb ( String colorRgb ) {
    this.colorRgb = colorRgb;
  }
  
  public byte[] getImagenCuerpo () {
    return imagenCuerpo;
  }
  
  public void setImagenCuerpo ( byte[] imagenCuerpo ) {
    this.imagenCuerpo = imagenCuerpo;
  }
  
  public List< Service > getTbService () {
    return tbService;
  }
  
  public void setTbService ( List< Service > tbService ) {
    this.tbService = tbService;
  }
  
  public List< Component > getTbComponents () {
    return tbComponents;
  }
  
  public void setTbComponents ( List< Component > tbComponents ) {
    this.tbComponents = tbComponents;
  }
  
  public List< AplicationServlet > getTbAplicationServlets () {
    return tbAplicationServlets;
  }
  
  public void setTbAplicationServlets ( List< AplicationServlet > tbAplicationServlets ) {
    this.tbAplicationServlets = tbAplicationServlets;
  }
  
}