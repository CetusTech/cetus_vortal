package co.com.cetus.portal.ejb.process;

import java.util.List;

import javax.ejb.Local;

import co.com.cetus.common.dto.LoginDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.exception.ProcessException;
import co.com.cetus.vortal.jpa.entity.Aplicacion;
import co.com.cetus.vortal.jpa.entity.Component;
import co.com.cetus.vortal.jpa.entity.Menu;
import co.com.cetus.vortal.jpa.entity.Parametro;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.vortal.jpa.entity.Service;
import co.com.cetus.vortal.jpa.entity.Usuario;

@Local
public interface PortalProcessLocal {
  public ResponseWSDTO authenticationUser ( LoginDTO obj ) throws ProcessException;
  
  public List< Menu > findAllMenuByIdPadre ( int pIdPadre, String pLogin, int pIdAplicacion ) throws ProcessException;
  
  public List< Menu > findMenuByLoginAndApplication ( int pIdAplicacion, String pLogin ) throws ProcessException;
  
  public int removerRolMenuByRol ( int pIdRol, int pIdApp ) throws ProcessException;
  
  public List< Menu > findAllByIdRol ( int pIdRol, int pIdApp ) throws ProcessException;
  
  public int removerUserRolByUser ( int pIdUser ) throws ProcessException;
  
  public List< Rol > findAllRolByIdUser ( int pIdUser ) throws ProcessException;
  
  public List< Menu > findAllMenu () throws ProcessException;
  
  public List< Aplicacion > findAllAplicacionesByLogin ( String login ) throws ProcessException;
  
  public ResponseWSDTO getUserSessionCetus ( String pIdUser ) throws ProcessException;
  
  public boolean validateUserApp ( String pLogin, String pIdApp ) throws ProcessException;
  
  public boolean validateUserApp ( String pLogin ) throws ProcessException;
  
  public < E > ResponseWSDTO handleChangeServletComboBox ( int pIdApp ) throws ProcessException;
  
  public < E > ResponseWSDTO queryParamByAbreviature ( String pAbreviature ) throws ProcessException;
  
  public < E > ResponseWSDTO findAllByTypeParameter ( String pType ) throws ProcessException;
  
  public < E > ResponseWSDTO handleChangeApplicationRolMenu ( int pIdApp ) throws ProcessException;
  
  public List< Service > findAllServiceByApplication ( int pIdApp ) throws ProcessException;
  
  public Usuario findByLogin ( String pLogin ) throws ProcessException;
  
  public List< Component > findAllComponentByApplication ( int pIdApp ) throws ProcessException;
  
  public List< Parametro > findAllParameterByApplication ( int pIdApp ) throws ProcessException;
  
  public List< Parametro > findAllParameterByCompApp ( int pIdApp, int idComponent ) throws ProcessException;
  
  public String getValueParameter ( String name ) throws ProcessException;
  
  public boolean reloadParameter ( int pIdApp, int idComponent ) throws ProcessException;
  
  public < E > ResponseWSDTO handleChangeApplicationComboBoxServlet ( int pIdApp ) throws ProcessException;
  
  public boolean reloadParameterComponent ( int pIdApp, int idComponent, String nameComponent ) throws ProcessException;
  
  public boolean createUser ( Usuario user ) throws ProcessException;
}
