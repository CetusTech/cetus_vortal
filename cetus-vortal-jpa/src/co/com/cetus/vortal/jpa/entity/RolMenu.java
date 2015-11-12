package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_rol_menu database table.
 * 
 */
@Entity
@Table ( name = "TB_ROL_MENU" )
@NamedNativeQueries ( @NamedNativeQuery ( name = "RolMenu.removerAllByIdRol", query = "delete from  TB_ROL_MENU where rol_id = :rol and menu_id in (select m.id from TB_MENU m, TB_APLICATION_SERVLET s , TB_APLICACION ap where m.ID_APLICACION_SERVLET = s.id and s.ID_APPLICATION = ap.id and ap.id = :idApp)", resultClass = RolMenu.class ) )
/*@NamedNativeQueries ( @NamedNativeQuery ( name = "RolMenu.removerAllByIdRol", query = "delete from  tb_rol_menu where rol_id = :rol and menu_id not in (select m.id from tb_menu m, tb_aplication_servlet s , tb_aplicacion ap where m.ID_APLICACION_SERVLET = s.id and s.ID_APPLICATION = ap.id and m.id_padre is null or ap.id <> :idApp)", resultClass = RolMenu.class ) )*/
@NamedQueries ( { @NamedQuery ( name = "RolMenu.findAllByIdRol", query = "select rm from RolMenu rm where rm.tbRol.id = :rol and rm.tbMenu.tbMenu.id is not null and rm.tbMenu.tbAplicationServlet.tbAplicacion.id = :idApp" ) } )
public class RolMenu implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
  
  // bi-directional many-to-one association to Menu
  @ManyToOne
  @JoinColumn ( name = "MENU_ID" )
  private Menu              tbMenu;
  
  // bi-directional many-to-one association to Rol
  @ManyToOne
  @JoinColumn ( name = "ROL_ID" )
  private Rol               tbRol;
  
  public RolMenu () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public Menu getTbMenu () {
    return this.tbMenu;
  }
  
  public void setTbMenu ( Menu tbMenu ) {
    this.tbMenu = tbMenu;
  }
  
  public Rol getTbRol () {
    return this.tbRol;
  }
  
  public void setTbRol ( Rol tbRol ) {
    this.tbRol = tbRol;
  }
  
}