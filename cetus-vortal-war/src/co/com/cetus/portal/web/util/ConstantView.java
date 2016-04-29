package co.com.cetus.portal.web.util;

import org.apache.log4j.Logger;

public interface ConstantView {
  
  // Nombre del servicio del EJB Bean
  String                         ERROR                  = "ERROR:";
  String                         SUCCESS_FULL           = "EXITO:";
  String                         NAME_BUNDLE_VIEW       = "Message_es";
  String                         SEPARATOR              = "_";
  String                         ACTIVO                 = "1";
  String                         INACTIVO               = "0";
  String                         TYPE_TEMPLATE          = "T";
  String                         APLICATION_ID          = "aplicacion_id";
  String                         BARRA_INCLINADA        = "/";
  // Paquete Java donde esta el archivo de propiedades con la informacion para
  // ubicar el ejb remotamente
  
  public static Logger           log                    = Logger.getLogger( "CetusVortalLog" );
                                                        
  public static PropertiesLoader properties             = PropertiesLoader.getInstance();
  String                         CONTEXT_SERVICE_REMOTE = properties.getProperty( "CONTEXT_CETUS_CORE", "" );
                                                        
  public interface ViewPage {
    String PAGE_WELCOME              = "pages/portal/welcome.xhtml";
    String SUBJECT_CREATE_USER_CETUS = "SUBJECT_CREATE_USER_CETUS";
    String HTML_EMAIL_NEW_USER       = "HTML_EMAIL_NEW_USER";
  }
  
  public interface Internalizacion {
    String ERROR_GENERAL               = "error_general";
    String ERROR_INSERT_REGISTER       = "error_insert_register";
    String ERROR_UPDATE_REGISTER       = "error_update_register";
    String ERROR_DELETE_REGISTER       = "error_delete_register";
    String ERROR_OBJECT_NULL           = "error_object_null";
    String SUCCESFULL_INSERT           = "succesfull_insert";
    String SUCCESFULL_UPDATE           = "succesfull_update";
    String SUCCESFULL_DELETE           = "succesfull_delete";
    String JAVA_NAMING_PROVIDER_URL    = properties.getProperty( "JNDI_CETUS_CORE", "remote://127.0.0.1:4447/" );
    String SELECT_APP                  = "select_app";
    String SUCCESFULL_LOGIN            = "succesfull_login";
    String USER_NOT_APP                = "user_not_app";
    String USER_NOT_LOGIN              = "user_not_login";
    String ERROR_INIT_SESSION          = "error_init_session";
    String ERROR_INPUT_PASS_DIF        = "error_password_diferent";
    String ERROR_PASS_NEW_PASS_OLD_DIF = "error_pass_new_pass_old_dif";
    String ERROR_OLD_NEW_EQUALS        = "ERROR_OLD_NEW_EQUALS";
    String ERROR_EMAIL_CURRENT         = "error_email_current";
    String ERROR_INPUT_PARAM           = "ERROR_INPUT_PARAM";
    String LABEL_ONE_MENU_EMPTY        = "LABEL_ONE_MENU_EMPTY";
    String SELECTED_MENU               = "SELECTED_MENU";
    String SELECTED_APP_COMBO          = "select_register_applicaation";
    String ERROR_SELECTED_MENU         = "ERROR_SELECTED_MENU";
    String SEL_TEMPLATE_MAIL           = Util.getProperty( NAME_BUNDLE_VIEW, "SEL_TEMPLATE_MAIL" );
    String SELECTED_SERVICE            = "SELECTED_SERVICE";
    String SELECT_REGISTER_SERVICE     = "select_register_service";
    String SELECT_REGISTER_COMPONENT   = "select_register_component";
    String SELECTED_PARAMETER          = "SELECTED_PARAMETER";
    String ERROR_VALUE_NUMBER          = "error_value_number";
    String ERROR_VALUE_MONEY           = "error_value_money";
    String ERROR_VALUE_BOOLEAN         = "error_value_boolean";
    String ERROR_RELOAD_PARAMETER      = "error_reload_parameter";
    String SELECTED_COMPONENT_RELOAD   = "SELECTED_COMPONENT_RELOAD";
    String SUCCESFULL_COMPONENT_RELOAD = "succesfull_component_reload";
    String NO_CONFIGURED_COMPONENT     = "no_configured_component";
    String LABEL_SELECT_SERVLET        = "select_servlet";
    String LABEL_SELECT_APPLICATION    = "select_register_applicaation";
    String ERROR_NO_EXISTS_FILTER      = "ERROR_NO_EXISTS_FILTER";
  }
  
  public interface ColumnaEntityProperties {
    String NOMBRE_PROPERTIES_NEGOCIO      = "NOMBRE";
    String DESCRIPCION_PROPERTIES_NEGOCIO = "DESCRIPCION";
    String LOGIN_PROPERTIES_NEGOCIO       = "LOGIN";
    String USER_PROPERTIES_NEGOCIO        = "USER";
    String NAME_PROPERTIES_NEGOCIO        = "NAME";
    String ID_PROPERTIES_NEGOCIO          = "ID";
    String DESCRIPTION_PROPERTIES_NEGOCIO = "DESCRIPTION";
    String ID_APPLICATION_PROPERTIES_NEGOCIO = "ID_APPLICATION";
                                          
  }
  
  public interface Parameter {
    String ID_APLICATION_AGARTHI      = "ID_APLICATION_AGARTHI";
    String ID_APLICATION_CETUS        = properties.getProperty( "ID_APPLICATION_CETUS_VORTAL", "1" );
    String ID_COMPONENT_CETUS_VORTAL  = properties.getProperty( "ID_COMPONENT_CETUS_VORTAL", "1" );
    String SMTP_HOST                  = "SMTP_HOST";
    String SMPT_PORT                  = "SMPT_PORT";
    String SMTP_PASS                  = "SMTP_PASS";
    String SMTP_FROM                  = "SMTP_FROM";
    String SMTP_USERNAME              = "SMTP_USERNAME";
    String SUBJECT_CREATE_USER        = "SUBJECT_CREATE_USER";
    String HTML_EMAIL_NEW_USER        = "HTML_EMAIL_NEW_USER";
    String SUBJECT_UPDATE_USER        = "SUBJECT_UPDATE_USER";
    String HTML_EMAIL_UPDATE_USER     = "HTML_EMAIL_UPDATE_USER";
    String SUBJECT_DELETE_USER        = "SUBJECT_DELETE_USER";
    String HTML_EMAIL_DELETE_USER     = "HTML_EMAIL_DELETE_USER";
    String COMPONENT_CETUS_VORTAL     = "cetus-vortal-ejb";
  }
}
