package co.com.cetus.portal.ejb.runnables;

import co.com.cetus.messageservice.ejb.service.SendMailRequestDTO;
import co.com.cetus.portal.ejb.delegate.CetusMessageServiceDelegate;
import co.com.cetus.portal.ejb.util.AppConstants;
import co.com.cetus.portal.ejb.util.Util;

public class ThereadMailLocal implements Runnable {
  private String              lParamSUBJECT;
  private String              mail;
  private String[]            cc;
  private String              template;
  private String[]            parameters;
  CetusMessageServiceDelegate messageServiceDelegate;
                              
  public ThereadMailLocal ( String lParamSUBJECT, String mail, String[] cc, String template, String[] parameters ) {
    super();
    this.lParamSUBJECT = lParamSUBJECT;
    this.mail = mail;
    this.cc = cc;
    this.template = template;
    this.parameters = parameters;
  }
  
  @Override
  public void run () {
    try {
      SendMailRequestDTO sendMailRequestDTO = new SendMailRequestDTO();
      sendMailRequestDTO.setSubject( lParamSUBJECT );
      sendMailRequestDTO.setRecipients( new String[]{ mail } );
      sendMailRequestDTO.setCopyToRecipients( cc );
      sendMailRequestDTO.setUser( AppConstants.USER_WS_MESSAGE_SERVICE );
      sendMailRequestDTO.setPassword( AppConstants.PASSWORD_WS_MESSAGE_SERVICE );
      sendMailRequestDTO.setNameTemplateHTML( template );
      sendMailRequestDTO.setParametersTemplateHTML( parameters );
      messageServiceDelegate = new CetusMessageServiceDelegate( AppConstants.WSDL_CETUS_MESSAGE_SERVICE );
      messageServiceDelegate.sendEmail( sendMailRequestDTO );
    } catch ( Exception e ) {
      Util.CETUS_CORE.error( "Error ::> " + e.getMessage(), e );
      throw e;
    }
  }
  
}
