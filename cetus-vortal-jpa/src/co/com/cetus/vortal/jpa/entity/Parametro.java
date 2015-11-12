package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import co.com.cetus.vortal.jpa.entity.Component;

import java.util.Date;

/**
 * The persistent class for the tb_parametro database table.
 * 
 */
@Entity
@Table ( name = "TB_PARAMETRO" )
@NamedQueries ( {
   @NamedQuery ( name = "Parametro.QueryParamByAbreviature", query = "select p from Parametro p where p.abreviatura = :pAbreviature and p.estado = :pStatus" ),
   @NamedQuery ( name = "Parametro.findAllByType", query = "select p from Parametro p where p.tipoParametro = :type  " ),
   @NamedQuery ( name = "Parametro.QueryParamByComponentApp", query = "select p from Parametro p where p.tbComponent.name = :nameComponent " +
   		                  "and p.tbComponent.tbAplicacion.id = :idAppication and p.estado = :status" ),
                 @NamedQuery ( name = "Parametro.findAllParameterByApp", query = "select p from Parametro p where p.tbComponent.tbAplicacion.id = :idApp and p.tipoParametro <> 'T' " ),
   @NamedQuery ( name = "Parametro.findAllParamByCompApp", query = "select p from Parametro p where p.tbComponent.id = :idComponent " +
                        "and p.tbComponent.tbAplicacion.id = :idAppication and p.estado = :status" )
                 } )

public class Parametro implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
  
  private String            abreviatura;
  
  private String            descripcion;
  
  private String            estado;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_CREACION" )
  private Date              fechaCreacion;
  
  @Temporal ( TemporalType.TIMESTAMP )
  @Column ( name = "FECHA_MODIFICACION" )
  private Date              fechaModificacion;
  
  @Column ( name = "TIPO_PARAMETRO" )
  private String            tipoParametro;
  
  @Column ( name = "USUARIO_CREACION" )
  private String            usuarioCreacion;
  
  @Column ( name = "USUARIO_MODIFICACION" )
  private String            usuarioModificacion;
  
  private String            valor;
  
  //bi-directional many-to-one association to Component
  @ManyToOne
  @JoinColumn ( name = "COMPONENT_ID" )
  private Component         tbComponent;
  
  public Parametro () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public String getAbreviatura () {
    return this.abreviatura;
  }
  
  public void setAbreviatura ( String abreviatura ) {
    this.abreviatura = abreviatura;
  }
  
  public String getDescripcion () {
    return this.descripcion;
  }
  
  public void setDescripcion ( String descripcion ) {
    this.descripcion = descripcion;
  }
  
  public String getEstado () {
    return this.estado;
  }
  
  public void setEstado ( String estado ) {
    this.estado = estado;
  }
  
  public Date getFechaCreacion () {
    return this.fechaCreacion;
  }
  
  public void setFechaCreacion ( Date fechaCreacion ) {
    this.fechaCreacion = fechaCreacion;
  }
  
  public Date getFechaModificacion () {
    return this.fechaModificacion;
  }
  
  public void setFechaModificacion ( Date fechaModificacion ) {
    this.fechaModificacion = fechaModificacion;
  }
  
  public String getTipoParametro () {
    return this.tipoParametro;
  }
  
  public void setTipoParametro ( String tipoParametro ) {
    this.tipoParametro = tipoParametro;
  }
  
  public String getUsuarioCreacion () {
    return this.usuarioCreacion;
  }
  
  public void setUsuarioCreacion ( String usuarioCreacion ) {
    this.usuarioCreacion = usuarioCreacion;
  }
  
  public String getUsuarioModificacion () {
    return this.usuarioModificacion;
  }
  
  public void setUsuarioModificacion ( String usuarioModificacion ) {
    this.usuarioModificacion = usuarioModificacion;
  }
  
  public String getValor () {
    return this.valor;
  }
  
  public void setValor ( String valor ) {
    this.valor = valor;
  }
  
  public Component getTbComponent () {
    return this.tbComponent;
  }
  
  public void setTbComponent ( Component tbComponent ) {
    this.tbComponent = tbComponent;
  }
  
}