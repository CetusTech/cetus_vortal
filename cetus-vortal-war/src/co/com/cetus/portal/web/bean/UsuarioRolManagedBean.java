package co.com.cetus.portal.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.Usuario;
import co.com.cetus.vortal.jpa.entity.UsuarioRol;
import co.com.cetus.common.util.ConstantCommon;


/**
 * The Class RolManagedBean.
 */
@ManagedBean ( name = "usuarioRolManagedBean" )
@RequestScoped
public class UsuarioRolManagedBean extends GeneralManagedBean {
  private List< Rol >          listRegister;
  private List< Usuario >      listRegisterUsuario;
  private List< SelectItem >   listSelectItemUser;
  private DualListModel< Rol > productEnabled;
  private List< Rol >          listRolSelected;
  private String               idUserSelected;
  private List< Rol >          listRolEnabled;
  private static final long    serialVersionUID = -814452095367034877L;
  private boolean              flag;
  
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      
      this.listRolEnabled = this.delegate.findAllOrder( Rol.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      this.listRegisterUsuario = this.delegate.findAllOrder( Usuario.class, ConstantView.ColumnaEntityProperties.LOGIN_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      this.convertToComboBoxUser( this.listRegisterUsuario );
      
      if ( idUserSelected != null && !idUserSelected.isEmpty() ) {
        listRolSelected = this.delegate.findAllRolByIdUser( Integer.parseInt( idUserSelected ) );
        
        if ( listRolSelected != null && !listRolSelected.isEmpty() ) {
          listRolEnabled.removeAll( listRolSelected );
        } else {
          listRolSelected = new ArrayList< Rol >();
        }
        productEnabled = new DualListModel< Rol >( listRolEnabled, listRolSelected );
      } else {
        
        productEnabled = new DualListModel< Rol >( new ArrayList< Rol >(), new ArrayList< Rol >() );
        
        if ( listRolEnabled != null && !listRolEnabled.isEmpty() ) {
          productEnabled = new DualListModel< Rol >( listRolEnabled, new ArrayList< Rol >() );
        }
      }
      
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( "[RolManagedBean.initElement]" + e.getMessage(), e );
    }
  }
  
  public String saveUsuarioRol () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    UsuarioRol lUsuarioRol = null;
    Usuario lUsuario = null;
    int lStatusRemoveAlla = 0;
    try {
      if ( productEnabled != null && idUserSelected != null && !idUserSelected.isEmpty() ) {
        context = RequestContext.getCurrentInstance();
        listRolSelected = productEnabled.getTarget();
        // selectedObject = (Rol) getObjectSession("selectedObject");
        // Validar que la duracion de cada producto seleccionado sea
        // el mismo para poder calcular la fecha de expiracion del
        // paquete
        
        lSuccessfull = true;
        if ( lSuccessfull ) {
          lSuccessfull = true;
          if ( lSuccessfull ) {
            lStatusRemoveAlla = this.removerPackageProductAll( Integer.parseInt( idUserSelected ) );
            Util.CETUS_WAR.info( "Estado Eliminacion --> " + lStatusRemoveAlla );
            
            lUsuario = this.delegate.find( Usuario.class, Integer.parseInt( idUserSelected ) );
            
            for ( Rol data: listRolSelected ) {
              lUsuarioRol = new UsuarioRol();
              lUsuarioRol.setTbRol( data );
              lUsuarioRol.setTbUsuario( lUsuario );
              this.delegate.create( lUsuarioRol );
            }
            lSuccessfull = true;
            cleanObjectSession( "flag" );
            this.initElement();
            addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ) );
          } else {
            addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ) );
          }
        } else {
          addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_INSERT_REGISTER ) );
        }
      }
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    context.addCallbackParam( "lSuccessfull", lSuccessfull );
    
    return null;
  }
  
  public void handleRolChange () {
    try {
      this.listRolEnabled = this.delegate.findAllOrder( Rol.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      
      if ( idUserSelected != null && !idUserSelected.isEmpty() ) {
        flag = true;
        //addObjectSession( flag, "flag" );
        listRolSelected = this.delegate.findAllRolByIdUser( Integer.parseInt( idUserSelected ) );
        if ( listRolSelected != null && !listRolSelected.isEmpty() ) {
          listRolEnabled.removeAll( listRolSelected );
        } else {
          listRolSelected = new ArrayList< Rol >();
        }
      } else {
        listRolSelected = new ArrayList< Rol >();
      }
      productEnabled = new DualListModel< Rol >( listRolEnabled, listRolSelected );
    } catch ( Exception e1 ) {
      Util.CETUS_WAR.error( e1.getMessage(), e1 );
    }
  }
  
  private int removerPackageProductAll ( int pIdUserSelected ) throws Exception {
    try {
      return this.delegate.removerUserRolByUser( pIdUserSelected );
    } catch ( Exception e ) {
      throw e;
    }
  }
  
  private void convertToComboBoxUser ( List< Usuario > pListRegister ) {
    listSelectItemUser = new ArrayList< SelectItem >();
    for ( Usuario obj: pListRegister ) {
      listSelectItemUser.add( new SelectItem( obj.getId(), obj.getLogin() ) );
    }
  }
  
  public List< Rol > getListRegister () {
    return listRegister;
  }
  
  public void setListRegister ( List< Rol > listRegister ) {
    this.listRegister = listRegister;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  @Override
  public String add () {
    return null;
  }
  
  @Override
  public String delete () {
    return null;
  }
  
  @Override
  public String update () {
    
    return null;
  }
  
  public List< Rol > getListRolEnabled () {
    return listRolEnabled;
  }
  
  public void setListRolEnabled ( List< Rol > listRolEnabled ) {
    this.listRolEnabled = listRolEnabled;
  }
  
  public DualListModel< Rol > getProductEnabled () {
    return productEnabled;
  }
  
  public void setProductEnabled ( DualListModel< Rol > productEnabled ) {
    this.productEnabled = productEnabled;
  }
  
  public List< Rol > getListProductSelected () {
    return listRolSelected;
  }
  
  public void setListProductSelected ( List< Rol > listProductSelected ) {
    this.listRolSelected = listProductSelected;
  }
  
  public String getIdRolSelected () {
    return idUserSelected;
  }
  
  public void setIdRolSelected ( String idRolSelected ) {
    this.idUserSelected = idRolSelected;
  }
  
  public List< Usuario > getListRegisterUsuario () {
    try {
      this.listRegisterUsuario = this.delegate.findAllOrder( Usuario.class, ConstantView.ColumnaEntityProperties.LOGIN_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      this.listRegisterUsuario = this.delegate.findAllOrder( Usuario.class, ConstantView.ColumnaEntityProperties.LOGIN_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      this.convertToComboBoxUser( this.listRegisterUsuario );
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    this.convertToComboBoxUser( this.listRegisterUsuario );
    return listRegisterUsuario;
  }
  
  public void setListRegisterUsuario ( List< Usuario > listRegisterUsuario ) {
    this.listRegisterUsuario = listRegisterUsuario;
  }
  
  public List< SelectItem > getListSelectItemUser () {
    return listSelectItemUser;
  }
  
  public void setListSelectItemUser ( List< SelectItem > listSelectItemUser ) {
    this.listSelectItemUser = listSelectItemUser;
  }
  
  public List< Rol > getListRolSelected () {
    return listRolSelected;
  }
  
  public void setListRolSelected ( List< Rol > listRolSelected ) {
    this.listRolSelected = listRolSelected;
  }
  
  public String getIdUserSelected () {
    return idUserSelected;
  }
  
  public void setIdUserSelected ( String idUserSelected ) {
    this.idUserSelected = idUserSelected;
  }
  
  public boolean isFlag () {
//    try {
//      flag = ( Boolean ) getObjectSession( "flag" );
//    } catch ( Exception e ) {
//      flag = false;
//    }
    return flag;
  }
  
  public void setFlag ( boolean flag ) {
    this.flag = flag;
  }
  
}
