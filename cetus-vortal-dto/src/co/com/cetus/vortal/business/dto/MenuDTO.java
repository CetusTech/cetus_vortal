package co.com.cetus.vortal.business.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the tb_menu database table.
 * 
 */
public class MenuDTO implements Serializable {
  private static final long    serialVersionUID = 1L;
  
  private int                  id;
  
  private String               acronimo;
  
  private String               descripcion;
  
  private Date                 fechaCreacion;
  
  private String               nombre;
  
  private String               url;
  
  private String               usuarioCreacion;
  
  private AplicationServletDTO tbAplicationServlet;
  
  private MenuDTO              tbMenu;
  
  private List< MenuDTO >      tbMenus;
  
  private List< RolMenuDTO >   tbRolMenus;
  
  public MenuDTO () {
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
  
  public MenuDTO getTbMenu () {
    return this.tbMenu;
  }
  
  public void setTbMenu ( MenuDTO tbMenu ) {
    this.tbMenu = tbMenu;
  }
  
  public List< MenuDTO > getTbMenus () {
    return this.tbMenus;
  }
  
  public void setTbMenus ( List< MenuDTO > tbMenus ) {
    this.tbMenus = tbMenus;
  }
  
  public List< RolMenuDTO > getTbRolMenus () {
    return this.tbRolMenus;
  }
  
  public void setTbRolMenus ( List< RolMenuDTO > tbRolMenus ) {
    this.tbRolMenus = tbRolMenus;
  }
  
  public AplicationServletDTO getTbAplicationServlet () {
    return tbAplicationServlet;
  }
  
  public void setTbAplicationServlet ( AplicationServletDTO tbAplicationServlet ) {
    this.tbAplicationServlet = tbAplicationServlet;
  }
  
  @Override
  public boolean equals ( Object obj ) {
    if ( obj instanceof MenuDTO ) {
      if ( this.id == ( ( MenuDTO ) obj ).getId() ) {
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