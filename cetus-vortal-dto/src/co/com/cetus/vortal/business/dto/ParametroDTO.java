package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the tb_parametro database table.
 * 
 */
public class ParametroDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private int               id;
  
  private String            abreviatura;
  
  private String            descripcion;
  
  private String            estado;
  
  private Date              fechaCreacion;
  
  private Date              fechaModificacion;
  
  private String            tipoParametro;
  
  private String            usuarioCreacion;
  
  private String            usuarioModificacion;
  
  private String            valor;
  
  public ParametroDTO () {
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
  
  @Override
  public String toString () {
    return null;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof ParametroDTO ) {
      if ( this.id == ( ( ParametroDTO ) obj ).getId() ) {
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