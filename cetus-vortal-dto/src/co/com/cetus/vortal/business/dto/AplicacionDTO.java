package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_aplicacion database table.
 * 
 */
public class AplicacionDTO implements Serializable {
  private static final long            serialVersionUID = 1L;
  
  private int                          id;
  
  private String                       descripcion;
  private Date                         fechaCreacion;
  
  private String                       nombre;
  
  private String                       urlServer;
  
  private String                       usuarioCreacion;
  
  private TipoEstiloDTO                tbTipoEstilo;
  
  private byte[]                       imagenCuerpo;
  
  private byte[]                       logo;
  
  private String                       colorRgb;
  
  private List< ServiceDTO >           tbService;
  
  private List< ComponentDTO >         tbComponents;
  
  private List< AplicationServletDTO > tbAplicationServlets;
  
  public AplicacionDTO () {
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
  
  public TipoEstiloDTO getTbTipoEstilo () {
    return this.tbTipoEstilo;
  }
  
  public void setTbTipoEstilo ( TipoEstiloDTO tbTipoEstilo ) {
    this.tbTipoEstilo = tbTipoEstilo;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj == null ) return false;
    if ( ! ( obj instanceof AplicacionDTO ) ) return false;
    
    return ( ( AplicacionDTO ) obj ).getId() == this.id;
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
  
  public List< ServiceDTO > getTbService () {
    return tbService;
  }
  
  public void setTbService ( List< ServiceDTO > tbService ) {
    this.tbService = tbService;
  }
  
  public List< ComponentDTO > getTbComponents () {
    return tbComponents;
  }
  
  public void setTbComponents ( List< ComponentDTO > tbComponents ) {
    this.tbComponents = tbComponents;
  }
  
  public List< AplicationServletDTO > getTbAplicationServlets () {
    return tbAplicationServlets;
  }
  
  public void setTbAplicationServlets ( List< AplicationServletDTO > tbAplicationServlets ) {
    this.tbAplicationServlets = tbAplicationServlets;
  }
  
}