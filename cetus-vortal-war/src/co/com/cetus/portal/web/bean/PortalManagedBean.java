package co.com.cetus.portal.web.bean;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import co.com.cetus.common.encriptor.Encriptor;
import co.com.cetus.common.encriptor.EncriptorFactory;
import co.com.cetus.common.encriptor.EncriptorType;
import co.com.cetus.common.util.ConstantCommon;
import co.com.cetus.common.util.UtilCommon;
import co.com.cetus.portal.web.security.UsuarioPortalPrincipal;
import co.com.cetus.portal.web.util.ConstantView;
import co.com.cetus.portal.web.util.Util;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.AplicationServlet;
import co.com.cetus.vortal.jpa.entity.FilterSearch;
import co.com.cetus.vortal.jpa.entity.GeneralSearch;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Servlet;

/**
 * The Class PortalManagedBean.
 *
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-war (10/11/2013)
 */
@ManagedBean ( name = "portalManagedBean" )
@SessionScoped
public class PortalManagedBean extends GeneralManagedBean {
  
  /** The Constant serialVersionUID. */
  private static final long               serialVersionUID = 5852872097452516214L;
                                                           
  /** The principal. */
  private UsuarioPortalPrincipal          principal        = null;
                                                           
  /** The page. */
  private String                          page;
                                          
  /** The root. */
  private DefaultTreeNode                 root;
                                          
  /** The selected node. */
  private TreeNode                        selectedNode;
                                          
  /** The acronimo. */
  private String                          acronimo;
                                          
  /** The aplicacion. */
  private Aplicacion                      aplicacion;
                                          
  private boolean                         showGeneralSearch;
                                          
  private List< SelectItem >              listFilter;
  private String                          filterSelected;
  private String                          inputSearch;
                                          
  private HashMap< String, FilterSearch > mapFilterSearch;
                                          
  /**
   * </p> Instancia un nuevo portal managed bean. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public PortalManagedBean () {
    this.page = ConstantView.ViewPage.PAGE_WELCOME;
  }
  
  /**
   * </p> Generar tree. </p>
   *
   * @param pRoot the p root
   * @param pPadre the p padre
   * @param pListChild the p list child
   * @return el default tree node
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public DefaultTreeNode generarTree ( TreeNode pRoot, Menu pPadre, List< Menu > pListChild ) {
    String idAppSeleted;
    try {
      
      //Obtener el ID de la aplicacion para obtener los menos asociados a esta aplicacion
      idAppSeleted = ( String ) getObjectSession( "idApp" );
      
      int pIdAplicacion = Integer.parseInt( ( String ) ( idAppSeleted != null && !idAppSeleted.isEmpty() ? idAppSeleted : -1 ) );
      
      aplicacion = this.delegate.find( Aplicacion.class, pIdAplicacion );
      if ( aplicacion != null ) {
        if ( pIdAplicacion > 0 ) {
          return this.generateNode( pRoot, pPadre, pListChild, pIdAplicacion, this.principal.getUserGeneralDTO().getUser().getLogin() );
        } else {
          return null;
        }
      }
      
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( "[PortalManagedBean.generarTree]. Traza ["
                            + e.getMessage() + "]" );
    }
    return null;
  }
  
  /**
   * <p>
   * Obtiene el page.
   * </p>
   * 
   * @return the page
   * @author Andres Herrera - Cetus Technology
   */
  public String getPage () {
    return page;
  }
  
  /**
   * </p> Inits the. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  @PostConstruct
  public void init () {
    this.inicializarUsuario();
    cleanObjectSessionInit();
    root = this.generarTree( null, null, null );
    loadGeneralSearch();
  }
  
  private void cleanObjectSessionInit(){
    try {
      cleanObjectSession( "selectedObject" );
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( "Error: " + e.getMessage(), e );
    }
  }
  
  /**
   * <p>
   * Asigna el page.
   * </p>
   * 
   * @param page
   *            the new page
   * @author Andres Herrera - Cetus Technology
   */
  public void setPage ( String page ) {
    this.page = page;
  }
  
  /**
   * <p>
   * Obtiene el serialversionuid.
   * </p>
   * 
   * @return the serialversionuid
   * @author Andres Herrera - Cetus Technology
   */
  public static long getSerialversionuid () {
    return serialVersionUID;
  }
  
  /**
   * </p> Inicializar usuario. </p>
   *
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  private void inicializarUsuario () {
    HttpServletRequest httpServletRequest = null;
    principal = ( UsuarioPortalPrincipal ) getRequest().getUserPrincipal();
    
    if ( principal != null && principal.getUserGeneralDTO() != null ) {
      httpServletRequest = ( HttpServletRequest ) FacesContext.getCurrentInstance().getExternalContext().getRequest();
      principal.getUserGeneralDTO().setIp( httpServletRequest.getRemoteAddr() );
      principal.getUserGeneralDTO().setHostName( httpServletRequest.getRemoteUser() );
    }
    
  }
  
  /**
   * <p>
   * Obtiene el principal.
   * </p>
   * 
   * @return the principal
   * @author Andres Herrera - Cetus Technology
   */
  public UsuarioPortalPrincipal getPrincipal () {
    return principal;
  }
  
  /**
   * <p>
   * Asigna el principal.
   * </p>
   * 
   * @param principal
   *            the new principal
   * @author Andres Herrera - Cetus Technology
   */
  public void setPrincipal ( UsuarioPortalPrincipal principal ) {
    this.principal = principal;
  }
  
  /**
   * <p>
   * Obtiene el root.
   * </p>
   * 
   * @return the root
   * @author Andres Herrera - Cetus Technology
   */
  public TreeNode getRoot () {
    return root;
  }
  
  /**
   * </p> Generate node. </p>
   *
   * @param pRoot the p root
   * @param pPadre the p padre
   * @param pListChild the p list child
   * @param pIdAplicacion the p id aplicacion
   * @param pLogin the p login
   * @return el default tree node
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public DefaultTreeNode generateNode ( TreeNode pRoot, Menu pPadre, List< Menu > pListChild, int pIdAplicacion, String pLogin ) {
    List< Menu > listNodo = null;
    List< Menu > listNodoChild = null;
    TreeNode nodeAux = null;
    
    try {
      if ( pRoot == null ) {
        root = new DefaultTreeNode( "Root", null );
        root.setExpanded( true );
      }
      // Consultar todos los menus
      if ( pPadre == null ) {
        // aca solo debe entrar una sola vez        
        listNodo = this.delegate.findMenuByLoginAndApplication( pIdAplicacion, pLogin );
        
      }
      
      if ( pListChild != null ) {
        // Establecer listaNodo igual a la lista de Hijos del padre
        listNodo = pListChild;
      }
      // Si existen nodos debe iterar para identificar el tipo de cada
      // nodo
      if ( listNodo != null ) {
        for ( Menu nodoMenuObj: listNodo ) {
          
          if ( nodoMenuObj.getTbMenu() == null ) {
            // El nodo es hijo de la raiz del arbol
            nodeAux = new DefaultTreeNode("gfather", nodoMenuObj, root );
            nodeAux.setExpanded( true );
            
            listNodoChild = this.delegate.findAllMenuByIdPadre( nodoMenuObj.getId(), pLogin, pIdAplicacion );
            
            if ( listNodoChild != null && !listNodoChild.isEmpty() ) {
              // Tiene Hijos el Nodo debe llamar al metodo
              // recursivamente
              generateNode( ( DefaultTreeNode ) nodeAux, nodoMenuObj, listNodoChild, pIdAplicacion, pLogin );
            }
          }
          if ( pPadre != null ) {
            if ( nodoMenuObj.getTbMenu().getId() == pPadre.getId() ) {
              // El nodo de turno es hijo del nodo padre
              nodeAux = new DefaultTreeNode("son", nodoMenuObj, pRoot );
              
              listNodoChild = this.delegate.findAllMenuByIdPadre( nodoMenuObj.getId(), pLogin, pIdAplicacion );
              
              if ( listNodoChild != null
                   && !listNodoChild.isEmpty() ) {
                nodeAux.setType( "father" );
                // Tiene Hijos el Nodo debe llamar al metodo
                // recursivamente
                generateNode( ( DefaultTreeNode ) nodeAux, nodoMenuObj, listNodoChild, pIdAplicacion, pLogin );
              }
            }
          }
          
        }
      }
      
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( "[LoginManagedBean.login]. Error al generar el arbol del portal  :" + e.getMessage() );
    }
    
    return ( DefaultTreeNode ) root;
  }
  
  public TreeNode getSelectedNode () {
    return selectedNode;
  }
  
  public void setSelectedNode ( TreeNode selectedNode ) {
    this.selectedNode = selectedNode;
  }
  
  /**
   * </p> On node select. </p>
   *
   * @param event the event
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  public void onNodeSelect ( NodeSelectEvent event ) {
    Menu menu = null;
    String parameters = null;
    AplicationServlet aplicationServlet = null;
    Servlet servlet = null;
    StringBuffer urlPage = null;
    String token = null;
    StringBuilder params = null;
    try {
      menu = ( Menu ) event.getTreeNode().getData();
      menu = this.delegate.find( Menu.class, menu.getId() );
      if ( menu != null ) {
        aplicationServlet = menu.getTbAplicationServlet();
        
        if ( aplicationServlet != null && aplicationServlet.getTbServlet() != null ) {
          servlet = aplicationServlet.getTbServlet();
          
          if ( servlet != null && servlet.getName() != null && servlet.getParameter() != null ) {
            if ( aplicacion != null && aplicacion.getUrlServer() != null && validarUsuario() ) {
              
              if ( servlet.getParameter().contains( "?" ) ) {
                // los parametros que se enviaran son los siguientes
                // parameters -> Este parametro identifica la cadena de parametros separados por | (encriptado)
                // token -> identificador aleatorio (encriptado)                
                
                if ( servlet.getParameter().contains( "parameters" ) && servlet.getParameter().contains( "token" ) ) {
                  
                  token = UtilCommon.getRandomUUID( 16 );
                  Encriptor encriptor = EncriptorFactory.createEncriptor( EncriptorType.AES128 );
                  
                  params = new StringBuilder();
                  params.append( aplicacion.getId() );
                  params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                  params.append( getPrincipal().getUserGeneralDTO().getUser().getLogin() );
                  params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                  params.append( menu.getAcronimo() );
                  params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                  params.append( menu.getUrl() );
                  
                  Util.CETUS_WAR.debug( "params ----> " + params.toString() );
                  
                  parameters = servlet.getParameter();
                  parameters = parameters.replace( "$1", URLEncoder.encode( encriptor.getValueEncripted( params.toString(), token ), "UTF-8" ) );
                  parameters = parameters.replace( "$2", URLEncoder.encode( encriptor.getValueEncripted( token, null ), "UTF-8" ) );
                  
                  //Concatenar la url del servidor de la aplicacion el nombre del contexto y los parametros del servlet
                  urlPage = new StringBuffer();
                  urlPage.append( aplicacion.getUrlServer() );
                  urlPage.append( ConstantView.BARRA_INCLINADA );
                  urlPage.append( servlet.getName() );
                  urlPage.append( parameters );
                  
                  Util.CETUS_WAR.debug( "URL DEL MENU ----> " + urlPage.toString() );
                  
                  //Establecer la pagina que debe pintar
                  this.page = urlPage.toString();
                  
                  //Si se utiliza el objeto de la arquitectura propuesta SelectObject se debe limpiar antes de cargar el nuevo menu
                  cleanObjectSession( "selectedObject" );
                }
              } else {
                this.page = aplicacion.getUrlServer().concat( menu.getUrl() );
              }
            } else {
              this.page = "/";
            }
          }
        }
        
      }
      this.acronimo = menu.getAcronimo();
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
  }
  
  /**
   * </p> Validar usuario. </p>
   *
   * @return true, si el proceso fue exitoso
   * @author Andres Herrera Hdez - Cetus Technology
   * @since cetus-vortal-war (10/11/2013)
   */
  private boolean validarUsuario () {
    if ( getPrincipal() != null && getPrincipal().getUserGeneralDTO() != null && getPrincipal().getUserGeneralDTO().getUser() != null ) {
      return true;
    }
    return false;
  }
  
  /**
   * </p> Load general search. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @since cetus-vortal-war (11/05/2016)
   */
  @SuppressWarnings ( "unchecked" )
  private void loadGeneralSearch () {
    String idAppSeleted = null;
    List< GeneralSearch > listGeneralSearch;
    SelectItem[] selectItems = null;
    int i = 0;
    List< FilterSearch > filters = null;
    try {
      listGeneralSearch = ( List< GeneralSearch > ) getObjectSession( "listGeneralSearch" );
      if ( listGeneralSearch == null || listGeneralSearch.size() == 0 ) {
        idAppSeleted = ( String ) getObjectSession( "idApp" );
        int idApp = Integer.parseInt( ( String ) ( idAppSeleted != null && !idAppSeleted.isEmpty() ? idAppSeleted : -1 ) );
        listGeneralSearch = delegate.findGenSearchByApp( idApp );
        if ( listGeneralSearch == null || listGeneralSearch.size() == 0 ) {
          listGeneralSearch = new ArrayList< >();
        } else {
          showGeneralSearch = true;
          listFilter = new ArrayList< SelectItem >();
          mapFilterSearch = new HashMap< >();
          for ( GeneralSearch generalSearch: listGeneralSearch ) {
            SelectItemGroup grupo = new SelectItemGroup( generalSearch.getDescription() );
            filters = delegate.findFilterByGenSearch( generalSearch.getId() );
            if ( filters != null && filters.size() > 0 ) {
              selectItems = new SelectItem[filters.size()];
              i = 0;
              for ( FilterSearch filterSearch: filters ) {
                selectItems[i] = new SelectItem( String.valueOf( filterSearch.getId() ), filterSearch.getLabel() );
                i++;
                mapFilterSearch.put( String.valueOf( filterSearch.getId() ), filterSearch );
              }
              grupo.setSelectItems( selectItems );
              listFilter.add( grupo );
              
            }
          }
          addObjectSession( listFilter, "listFilter" );
          addObjectSession( mapFilterSearch, "mapFilterSearch" );
        }
        addObjectSession( listGeneralSearch, "listGeneralSearch" );
      } else {
        showGeneralSearch = true;
      }
      
      addObjectSession( showGeneralSearch, "showGeneralSearch" );
      
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /**
   * </p> General search. </p>
   *
   * @author Jose David Salcedo M. - Cetus Technology
   * @since cetus-vortal-war (12/05/2016)
   */
  @SuppressWarnings ( "unchecked" )
  public void generalSearch () {
    FilterSearch filterSearch = null;
    String parameters = null;
    Servlet servlet = null;
    Aplicacion aplicacion = null;
    StringBuffer urlPage = null;
    String token = null;
    StringBuilder params = null;
    try {
      Util.CETUS_WAR.info( "filterSelected: " + filterSelected );
      Util.CETUS_WAR.info( "inputSearch: " + inputSearch );
      this.page = ConstantView.ViewPage.PAGE_WELCOME;
      if ( !UtilCommon.isNullOrEmptyString( inputSearch ) && !UtilCommon.isNullOrEmptyString( filterSelected ) && validarUsuario() ) {
        mapFilterSearch = ( HashMap< String, FilterSearch > ) getObjectSession( "mapFilterSearch" );
        if ( mapFilterSearch != null && mapFilterSearch.containsKey( filterSelected ) ) {
          filterSearch = mapFilterSearch.get( filterSelected );
          aplicacion = filterSearch.getGeneralSearch().getAppSerId().getTbAplicacion();
          servlet = filterSearch.getGeneralSearch().getAppSerId().getTbServlet();
          
          if ( servlet != null && aplicacion != null && !UtilCommon.isNullOrEmptyString( servlet.getParameter() )
               && !UtilCommon.isNullOrEmptyString( aplicacion.getUrlServer() ) ) {
               
            if ( servlet.getParameter().contains( "?" ) ) {
              // los parametros que se enviaran son los siguientes
              // parameters -> Este parametro identifica la cadena de parametros separados por | (encriptado)
              // token -> identificador aleatorio (encriptado)                
              
              if ( servlet.getParameter().contains( "parameters" ) && servlet.getParameter().contains( "token" ) ) {
                
                token = UtilCommon.getRandomUUID( 16 );
                Encriptor encriptor = EncriptorFactory.createEncriptor( EncriptorType.AES128 );
                
                params = new StringBuilder();
                params.append( getPrincipal().getUserGeneralDTO().getUser().getLogin() );
                params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                params.append( filterSearch.getGeneralSearch().getOptionSearch() );
                params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                params.append( filterSearch.getFilter() );
                params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                params.append( inputSearch );
                params.append( ConstantCommon.PARAMETRES_SEPARATOR );
                params.append( ConstantCommon.ACRONYM_GENERAL_SEARCH );
                
                Util.CETUS_WAR.debug( "params ----> " + params.toString() );
                
                parameters = servlet.getParameter();
                parameters = parameters.replace( "$1", URLEncoder.encode( encriptor.getValueEncripted( params.toString(), token ), "UTF-8" ) );
                parameters = parameters.replace( "$2", URLEncoder.encode( encriptor.getValueEncripted( token, null ), "UTF-8" ) );
                
                //Concatenar la url del servidor de la aplicacion el nombre del contexto y los parametros del servlet
                urlPage = new StringBuffer();
                urlPage.append( aplicacion.getUrlServer() );
                urlPage.append( ConstantView.BARRA_INCLINADA );
                urlPage.append( servlet.getName() );
                urlPage.append( parameters );
                
                Util.CETUS_WAR.debug( "URL DEL MENU ----> " + urlPage.toString() );
                
                //Establecer la pagina que debe pintar
                this.page = urlPage.toString();
                
                //Si se utiliza el objeto de la arquitectura propuesta SelectObject se debe limpiar antes de cargar el nuevo menu
                cleanObjectSession( "selectedObject" );
              }
            }
          }
        }
      }
      
    } catch ( Exception e ) {
      Util.CETUS_WAR.error( e.getMessage(), e );
    }
  }
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#initElement()
   */
  @Override
  public void initElement () {
  
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
  
  /* (non-Javadoc)
   * @see co.com.cetus.portal.web.bean.GeneralManagedBean#add()
   */
  @Override
  public String add () {
    return null;
  }
  
  public String getAcronimo () {
    return acronimo;
  }
  
  public void setAcronimo ( String acronimo ) {
    this.acronimo = acronimo;
  }
  
  public void setRoot ( DefaultTreeNode root ) {
    this.root = root;
  }
  
  public Aplicacion getAplicacion () {
    return aplicacion;
  }
  
  public void setAplicacion ( Aplicacion aplicacion ) {
    this.aplicacion = aplicacion;
  }
  
  public boolean isShowGeneralSearch () {
    return showGeneralSearch;
  }
  
  public void setShowGeneralSearch ( boolean showGeneralSearch ) {
    this.showGeneralSearch = showGeneralSearch;
  }
  
  @SuppressWarnings ( "unchecked" )
  public List< SelectItem > getListFilter () {
    listFilter = ( List< SelectItem > ) getObjectSession( "listFilter" );
    if ( listFilter == null ) {
      listFilter = new ArrayList< >();
    }
    return listFilter;
  }
  
  public void setListFilter ( List< SelectItem > listFilter ) {
    this.listFilter = listFilter;
  }
  
  public String getFilterSelected () {
    return filterSelected;
  }
  
  public void setFilterSelected ( String filterSelected ) {
    this.filterSelected = filterSelected;
  }
  
  public String getInputSearch () {
    return inputSearch;
  }
  
  public void setInputSearch ( String inputSearch ) {
    this.inputSearch = inputSearch;
  }
  
}
