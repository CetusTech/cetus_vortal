package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_tipo_estilo database table.
 * 
 */
public class TipoEstiloDTO implements Serializable {
  private static final long     serialVersionUID = 1L;
  
  private int                   id;
  
  private String                descripcion;
  
  private Date                  fechaCreacion;
  
  private String                nombre;
  
  private String                paramXml;
  
  private String                usuarioCreacion;
  
  private String                imagen;
  
  private List< AplicacionDTO > tbAplicacions;
  
  public TipoEstiloDTO () {
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
  
  public List< AplicacionDTO > getTbAplicacions () {
    return this.tbAplicacions;
  }
  
  public void setTbAplicacions ( List< AplicacionDTO > tbAplicacions ) {
    this.tbAplicacions = tbAplicacions;
  }
  
  @Override
  public String toString () {
    return null;
  }
  
  public String getImagen () {
    return imagen;
  }
  
  public void setImagen ( String imagen ) {
    this.imagen = imagen;
  }
  
}