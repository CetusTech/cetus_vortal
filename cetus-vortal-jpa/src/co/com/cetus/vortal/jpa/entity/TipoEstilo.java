package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_tipo_estilo database table.
 * 
 */
@Entity
@Table ( name = "TB_TIPO_ESTILO" )
public class TipoEstilo implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public TipoEstilo ( int id ) {
    this.id = id;
  }
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int                id;
  
  private String             descripcion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date               fechaCreacion;
  
  private String             nombre;
  
  @Column ( name = "PARAM_XML" )
  private String             paramXml;
  
  @Column ( name = "USUARIO_CREACION" )
  private String             usuarioCreacion;
  
  // bi-directional many-to-one association to Aplicacion
  @OneToMany ( mappedBy = "tbTipoEstilo" )
  private List< Aplicacion > tbAplicacions;
  
  private String             imagen;
  
  public TipoEstilo () {
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
  
  public String getParamXml () {
    return this.paramXml;
  }
  
  public void setParamXml ( String paramXml ) {
    this.paramXml = paramXml;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public List< Aplicacion > getTbAplicacions () {
    return this.tbAplicacions;
  }
  
  public void setTbAplicacions ( List< Aplicacion > tbAplicacions ) {
    this.tbAplicacions = tbAplicacions;
  }
  
  public String getImagen () {
    return imagen;
  }
  
  public void setImagen ( String imagen ) {
    this.imagen = imagen;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof TipoEstilo ) {
      if ( this.id == ( ( TipoEstilo ) obj ).getId() ) {
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