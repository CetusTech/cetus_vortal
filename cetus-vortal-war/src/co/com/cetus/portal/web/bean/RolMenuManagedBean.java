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
import co.com.cetus.vortal.business.dto.MenuDTO;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.RolMenu;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.Converter;
import co.com.cetus.common.util.UtilCommon;

/**
 * The Class RolMenuManagedBean.
 *
 * @author Andres Herrera - Cetus Technology
 * @version cetus-vortal-war (22/07/2013)
 */
@ManagedBean
@RequestScoped
public class RolMenuManagedBean extends GeneralManagedBean {
  
  /*
   * Lista de Atributos del CRUD
   */
  
  private List< Rol >              listRegisterRol;
  
  private List< SelectItem >       listSelectItemRol;
  
  private List< SelectItem >       listSelectItemAplicacion;
  
  private DualListModel< MenuDTO > productEnabled;
  
  private List< Menu >             listMenuSelected;
  
  private String                   idRolSelected;
  
  private List< Menu >             listMenuEnabled;
  
  private List< Aplicacion >       listApp;
  
  private static final long        serialVersionUID = -814452095367034877L;
  
  private int                      idApp            = 0;
  
  private List< MenuDTO >          listMenuDTO      = null;
  
  protected Converter              converter        = null;
  
  private boolean                  flag             = false;
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @SuppressWarnings ( "unchecked" )
  @Override
  @PostConstruct
  public void initElement () {
    // Inicializar listas de registros existentes
    try {
      converter = new Converter( "co.com.cetus.vortal.business.dto.", "co.com.cetus.vortal.jpa.entity." );
      //Listar las Aplicaciones Existentes
      this.listApp = this.delegate.findAllOrder( Aplicacion.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      this.convertToComboBoxAplicacion( listApp );
      //Listar Roles existentes
      this.listRegisterRol = this.delegate.findAllOrder( Rol.class, ConstantView.ColumnaEntityProperties.DESCRIPCION_PROPERTIES_NEGOCIO, ConstantCommon.TIPO_ASC );
      this.convertToComboBoxRol( this.listRegisterRol );
      try {
        listMenuEnabled = ( List< Menu > ) getObjectSession( "listMenuEnabled" );
      } catch ( Exception e ) {
        listMenuEnabled = new ArrayList< Menu >();
      }
      cleanObjectSession( "productEnabled" );
      productEnabled = new DualListModel< MenuDTO >( new ArrayList< MenuDTO >(), new ArrayList< MenuDTO >() );
    } catch ( Exception e ) {
      addMessageError( null, ConstantView.ERROR, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_GENERAL ) + "\n" + e.getMessage() );
      Util.CETUS_WAR.error( "[RolMenuManagedBean.initElement]" + e.getMessage(), e );
    }
  }
  
  /**
   * </p> Handle change application. Permite buscar menos por aplicaciones seleccionada </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  @SuppressWarnings ( "unchecked" )
  public void handleChangeApplication () {
    ResponseWSDTO response = null;
    Menu lMenu = null;
    try {
      if ( idApp > 0 ) {
        addObjectSession( idApp, "idApp" );
        response = delegate.handleChangeApplicationRolMenu(  idApp );
        if ( Util.validateResponseSuccessXMLOutput( response ) ) {
          this.listMenuDTO = ( List< MenuDTO > ) UtilCommon.fromXML( response.getDataResponseXML() );
          if ( !listMenuDTO.isEmpty() ) {
            productEnabled = new DualListModel< MenuDTO >( listMenuDTO, new ArrayList< MenuDTO >() );
            addObjectSession( listMenuDTO, "listMenuDTO" );
            addObjectSession( productEnabled, "productEnabled" );
            flag = true;
            addObjectSession( flag, "flag" );
            if ( listMenuDTO != null && !listMenuDTO.isEmpty() ) {
              listMenuEnabled = new ArrayList< Menu >();
              for ( MenuDTO menuDTO: listMenuDTO ) {
                lMenu = new Menu();
                converter.convertDtoToEntity( menuDTO, lMenu );
                listMenuEnabled.add( lMenu );
                addObjectSession( listMenuEnabled, "listMenuEnabled" );
              }
            }
          }
        }
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  /**
   * </p> Save rol menu. </p>
   *
   * @return el string
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  public String save () {
    boolean lSuccessfull = false;
    RequestContext context = null;
    RolMenu lRolMenu = null;
    Rol lRol = null;
    Menu lMenu = null;
    try {
      context = RequestContext.getCurrentInstance();
      if ( productEnabled != null && ( !productEnabled.getSource().isEmpty() || !productEnabled.getTarget().isEmpty() ) ) {
        
        //Lista con los menus Seleccionados por el usuario 
        listMenuDTO = productEnabled.getTarget();
        if ( listMenuDTO != null && !listMenuDTO.isEmpty() ) {
          lSuccessfull = true;
          //Eliminar todos los menus asociados al rol seleccionado y a la aplicacion
          idApp = ( Integer ) getObjectSession( "idApp" );
          idRolSelected = ( String ) getObjectSession( "idRolSelected" );
          this.removerPackageProductAll( Integer.parseInt( idRolSelected ), idApp );
          //Obtener Objeto Rol
          lRol = this.delegate.find( Rol.class, Integer.parseInt( idRolSelected ) );
          for ( MenuDTO data: listMenuDTO ) {
            lRolMenu = new RolMenu();
            lMenu = new Menu();
            converter.convertDtoToEntity( data, lMenu );
            lRolMenu.setTbMenu( lMenu );
            lRolMenu.setTbRol( lRol );
            this.delegate.create( lRolMenu );
          }
          lSuccessfull = true;
          addMessageInfo( null, ConstantView.SUCCESS_FULL, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SUCCESFULL_INSERT ) );
          cleanObjectSession( "productEnabled" );
          //cleanObjectSession( "idApp" );
          //cleanObjectSession( "idRolSelected" );
          
        } else {
          //Eliminar todos los menus asociados al rol seleccionado y a la aplicacion
          idApp = ( Integer ) getObjectSession( "idApp" );
          idRolSelected = ( String ) getObjectSession( "idRolSelected" );
          this.removerPackageProductAll( Integer.parseInt( idRolSelected ), idApp );
        }
        context.addCallbackParam( "lSuccessfull", lSuccessfull );
      } else {
        addMessageError( null, ConstantView.ERROR,
                         Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.ERROR_SELECTED_MENU ) );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
      context.addCallbackParam( "lSuccessfull", lSuccessfull );
    }
    
    return null;
  }
  
  public void limpiarCampos () {
    productEnabled = null;
    cleanObjectSession( "productEnabled" );
    cleanObjectSession( "idApp" );
    idApp = 0;
    idRolSelected = null;
    cleanObjectSession( "idRolSelected" );
  }
  
  /**
   * </p> Handle rol change. </p>
   *
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  @SuppressWarnings ( "unchecked" )
  public void handleRolChange () {
    List< Menu > lListTempMenu = null;
    Menu lMenu = null;
    try {
      this.listMenuDTO = ( List< MenuDTO > ) getObjectSession( "listMenuDTO" );
      if ( idRolSelected != null && !idRolSelected.isEmpty() ) {
        if ( listMenuDTO != null && !listMenuDTO.isEmpty() ) {
          lListTempMenu = new ArrayList< Menu >();
          for ( MenuDTO menuDTO: listMenuDTO ) {
            lMenu = new Menu();
            converter.convertDtoToEntity( menuDTO, lMenu );
            lListTempMenu.add( lMenu );
          }
        }
        idApp = ( Integer ) getObjectSession( "idApp" );
        listMenuSelected = this.delegate.findAllByIdRol( Integer.parseInt( idRolSelected ), idApp );
        if ( listMenuSelected != null && !listMenuSelected.isEmpty() ) {
          lListTempMenu.removeAll( listMenuSelected );
          productEnabled = new DualListModel< MenuDTO >( convertListEntityToListDTO( lListTempMenu ), convertListEntityToListDTO( listMenuSelected ) );
          addObjectSession( productEnabled, "productEnabled" );
        } else {
          listMenuSelected = new ArrayList< Menu >();
        }
      } else {
        listMenuSelected = new ArrayList< Menu >();
      }
      addObjectSession( listMenuSelected, "listMenuSelected" );
      //      productEnabled = new DualListModel< MenuDTO >( listMenuEnabled, listMenuSelected );
    } catch ( Exception e1 ) {
      Util.CETUS_WAR.error( e1.getMessage(), e1 );
    }
  }
  
  /**
   * </p> Convert list dto to list entity. </p>
   *
   * @param pList the p list
   * @return el list
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (23/07/2013)
   */
  public List< Menu > convertListDTOToListEntity ( List< MenuDTO > pList ) {
    Menu lMenu = null;
    List< Menu > lList = null;
    try {
      if ( pList != null && !pList.isEmpty() ) {
        lList = new ArrayList< Menu >();
        for ( MenuDTO menu: pList ) {
          lMenu = new Menu();
          converter.convertDtoToEntity( menu, lMenu );
          lList.add( lMenu );
        }
      } else {
        lList = new ArrayList< Menu >();
      }
    } catch ( Exception e ) {
      lList = new ArrayList< Menu >();
    }
    
    return lList;
  }
  
  /**
   * </p> Convert list entity to list dto. </p>
   *
   * @param pList the p list
   * @return el list
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (23/07/2013)
   */
  public List< MenuDTO > convertListEntityToListDTO ( List< Menu > pList ) {
    MenuDTO lMenu = null;
    List< MenuDTO > lList = null;
    try {
      if ( pList != null && !pList.isEmpty() ) {
        lList = new ArrayList< MenuDTO >();
        for ( Menu menu: pList ) {
          lMenu = new MenuDTO();
          converter.convertEntityToDto( menu, lMenu, false );
          lList.add( lMenu );
        }
      } else {
        lList = new ArrayList< MenuDTO >();
      }
    } catch ( Exception e ) {
      lList = new ArrayList< MenuDTO >();
    }
    
    return lList;
  }
  
  /**
   * </p> Remover package product all. </p>
   *
   * @param pIdRolSelected the p id rol selected
   * @return el int
   * @throws Exception the exception
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  public int removerPackageProductAll ( int pIdRolSelected, int pIdApp ) throws Exception {
    try {
      return this.delegate.removerRolMenuByRol( pIdRolSelected, pIdApp );
    } catch ( Exception e ) {
      throw e;
    }
  }
  
  /**
   * </p> Convert to combo box rol. </p>
   *
   * @param pListRegister the p list register
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  public void convertToComboBoxRol ( List< Rol > pListRegister ) {
    listSelectItemRol = new ArrayList< SelectItem >();
    for ( Rol obj: pListRegister ) {
      listSelectItemRol.add( new SelectItem( obj.getId(), obj.getDescripcion() ) );
    }
  }
  
  @SuppressWarnings ( "unchecked" )
  public void filterMenuByAppAndRol () {
    ArrayList< Menu > lListTempMenu = null;
    Menu lMenu = null;
    try {
      
      if ( idApp > 0 && idRolSelected != null ) {
        addObjectSession( idApp, "idApp" );
        addObjectSession( idRolSelected, "idRolSelected" );
        this.listMenuDTO = ( List< MenuDTO > ) getObjectSession( "listMenuDTO" );
        if ( listMenuDTO != null && !listMenuDTO.isEmpty() ) {
          lListTempMenu = new ArrayList< Menu >();
          for ( MenuDTO menuDTO: listMenuDTO ) {
            lMenu = new Menu();
            converter.convertDtoToEntity( menuDTO, lMenu );
            lListTempMenu.add( lMenu );
          }
        }
        
        listMenuSelected = this.delegate.findAllByIdRol( Integer.parseInt( idRolSelected ), idApp );
        if ( listMenuSelected != null && !listMenuSelected.isEmpty() ) {
          lListTempMenu.removeAll( listMenuSelected );
          productEnabled = new DualListModel< MenuDTO >( convertListEntityToListDTO( lListTempMenu ), convertListEntityToListDTO( listMenuSelected ) );
        } else {
          listMenuSelected = new ArrayList< Menu >();
          if ( listMenuDTO != null && !listMenuDTO.isEmpty() ) {
            productEnabled = new DualListModel< MenuDTO >( listMenuDTO, new ArrayList< MenuDTO >() );
          } else {
            productEnabled = new DualListModel< MenuDTO >( new ArrayList< MenuDTO >(), new ArrayList< MenuDTO >() );
          }
        }
        addObjectSession( productEnabled, "productEnabled" );
      }
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
    
  }
  
  /**
   * </p> Convert to combo box aplicacion. </p>
   *
   * @param pList the p list
   * @author Andres Herrera - Cetus Technology
   * @since cetus-vortal-war (22/07/2013)
   */
  public void convertToComboBoxAplicacion ( List< Aplicacion > pList ) {
    listSelectItemAplicacion = new ArrayList< SelectItem >();
    listSelectItemAplicacion.add( new SelectItem( null, Util.getProperty( ConstantView.NAME_BUNDLE_VIEW, ConstantView.Internalizacion.SELECTED_APP_COMBO ) ) );
    for ( Aplicacion obj: pList ) {
      listSelectItemAplicacion.add( new SelectItem( obj.getId(), obj.getNombre() ) );
    }
  }
  
  public List< Menu > getListMenuEnabled () {
    return listMenuEnabled;
  }
  
  public void setListMenuEnabled ( List< Menu > listMenuEnabled ) {
    this.listMenuEnabled = listMenuEnabled;
  }
  
  /* (non-Javadoc)
    * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
    */
  
  @Override
  public String add () {
    return null;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#delete()
   */
  @Override
  public String delete () {
    return null;
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#update()
   */
  @Override
  public String update () {
    return null;
  }
  
  public List< Rol > getListRegisterRol () {
    return listRegisterRol;
  }
  
  public void setListRegisterRol ( List< Rol > listRegisterRol ) {
    this.listRegisterRol = listRegisterRol;
  }
  
  public List< SelectItem > getListSelectItemRol () {
    return listSelectItemRol;
  }
  
  public void setListSelectItemRol ( List< SelectItem > listSelectItemRol ) {
    this.listSelectItemRol = listSelectItemRol;
  }
  
  @SuppressWarnings ( "unchecked" )
  public DualListModel< MenuDTO > getProductEnabled () {
    productEnabled = ( DualListModel< MenuDTO > ) getObjectSession( "productEnabled" );
    if ( productEnabled == null ) {
      productEnabled = new DualListModel< MenuDTO >( new ArrayList< MenuDTO >(), new ArrayList< MenuDTO >() );
    }
    return productEnabled;
  }
  
  public void setProductEnabled ( DualListModel< MenuDTO > productEnabled ) {
    this.productEnabled = productEnabled;
  }
  
  public List< Menu > getListProductSelected () {
    return listMenuSelected;
  }
  
  public void setListProductSelected ( List< Menu > listProductSelected ) {
    this.listMenuSelected = listProductSelected;
  }
  
  public String getIdRolSelected () {
    return idRolSelected;
  }
  
  public void setIdRolSelected ( String idRolSelected ) {
    this.idRolSelected = idRolSelected;
  }
  
  public List< Aplicacion > getListApp () {
    return listApp;
  }
  
  public void setListApp ( List< Aplicacion > listApp ) {
    this.listApp = listApp;
  }
  
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  public List< SelectItem > getListSelectItemAplicacion () {
    return listSelectItemAplicacion;
  }
  
  public void setListSelectItemAplicacion ( List< SelectItem > listSelectItemAplicacion ) {
    this.listSelectItemAplicacion = listSelectItemAplicacion;
  }
  
  public int getIdApp () {
    return idApp;
  }
  
  public void setIdApp ( int idApp ) {
    this.idApp = idApp;
  }
  
  public boolean isFlag () {
    try {
      flag = ( Boolean ) getObjectSession( "flag" );
    } catch ( Exception e ) {
      flag = false;
    }
    return flag;
  }
  
  public void setFlag ( boolean flag ) {
    this.flag = flag;
  }
  
}
