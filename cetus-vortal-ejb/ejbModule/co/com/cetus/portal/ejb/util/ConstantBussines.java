package co.com.cetus.portal.ejb.util;

public interface ConstantBussines
{
  String NAME_BUNDLE_NEGOCIO = "portalBundle";
  int    OP_CREATE           = 1;
  int    OP_UPDATE           = 2;
  int    OP_DELETE           = 3;
  int    ACTIVO              = 1;
  int    INACTIVO            = 0;
  String ACTIVO_STR          = "ACTIVO";
  String INACTIVO_STR        = "INACTIVO";
  
  interface Columns
  {
    String ATRIBUTO_DESCRIPCION = "ATRIBUTO_DESCRIPCION";
  }
  
  interface Internalizacion
  {
    String GENERAL_PROCESS           = "GENERAL_PROCESS";
    String PORTAL_PROCESS            = "PORTAL_PROCESS";
    String PORTAL_SERVICE            = "PORTAL_SERVICE";
    String NOT_RESULT_EXCEPTION      = "not_result_exception";
    String ERROR_EJB                 = "error_ejb";
    String error_eliminando_registro = "error_eliminando_registro";
    String PCK_CLASS_DTO             = Util.getProperty( NAME_BUNDLE_NEGOCIO, "PCK_CLASS_DTO" );
    String PCK_CLASS_JPA             = Util.getProperty( NAME_BUNDLE_NEGOCIO, "PCK_CLASS_JPA" );
    String ACRONYM_DTO               = Util.getProperty( NAME_BUNDLE_NEGOCIO, "ACRONYM_DTO" );
    String NO_PERMISSION_SERVICE     = Util.getProperty( NAME_BUNDLE_NEGOCIO, "NO_PERMISSION_SERVICE" );
  }
  
  interface Operation {
    String CREATE          = "create";
    String EDIT            = "editr";
    String REMOVE          = "remove";
    
    String FIND            = "find";
    String FIND_ALL        = "findAll";
    String FIND_ALL_FILTER = "findAllFilter";
    String FIND_RANGE      = "findRange";
    String COUNT           = "count";
    
  }
  
  interface Parameter {
    String SMTP_HOST              = "SMTP_HOST";
    String SMPT_PORT              = "SMPT_PORT";
    String SMTP_PASS              = "SMTP_PASS";
    String SMTP_FROM              = "SMTP_FROM";
    String SMTP_USERNAME          = "SMTP_USERNAME";
    String SUBJECT_CREATE_USER    = "SUBJECT_CREATE_USER";
    String HTML_EMAIL_NEW_USER    = "HTML_EMAIL_NEW_USER";
    String SUBJECT_UPDATE_USER    = "SUBJECT_UPDATE_USER";
    String HTML_EMAIL_UPDATE_USER = "HTML_EMAIL_UPDATE_USER";
    String SUBJECT_DELETE_USER    = "SUBJECT_DELETE_USER";
    String HTML_EMAIL_DELETE_USER = "HTML_EMAIL_DELETE_USER";
  }
}
