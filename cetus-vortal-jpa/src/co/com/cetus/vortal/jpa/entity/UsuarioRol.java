package co.com.cetus.vortal.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tb_usuario_rol database table.
 * 
 */
@Entity
@Table ( name = "TB_USUARIO_ROL" )
@NamedQueries ( {
                 @NamedQuery ( name = "UsuarioRol.findAllByIdUser", query = "select rm from UsuarioRol rm where rm.tbUsuario.id = :user " ),
                 @NamedQuery ( name = "UsuarioRol.removerAllByIdUser", query = "delete from UsuarioRol rm where rm.tbUsuario.id = :user" ) } )
public class UsuarioRol implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue ( strategy = GenerationType.IDENTITY )
  private int               id;
  
  // bi-directional many-to-one association to Rol
  @ManyToOne
  @JoinColumn ( name = "ROL_ID" )
  private Rol               tbRol;
  
  // bi-directional many-to-one association to Usuario
  @ManyToOne
  @JoinColumn ( name = "USUARIO_ID" )
  private Usuario           tbUsuario;
  
  public UsuarioRol () {
  }
  
  public int getId () {
    return this.id;
  }
  
  public void setId ( int id ) {
    this.id = id;
  }
  
  public Rol getTbRol () {
    return this.tbRol;
  }
  
  public void setTbRol ( Rol tbRol ) {
    this.tbRol = tbRol;
  }
  
  public Usuario getTbUsuario () {
    return this.tbUsuario;
  }
  
  public void setTbUsuario ( Usuario tbUsuario ) {
    this.tbUsuario = tbUsuario;
  }
  
}