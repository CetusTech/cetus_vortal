package co.com.cetus.portal.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet ( description = "Recibira la peticion de las aplicaciones para iniciar con Cetus", urlPatterns = { "/Home" } )
public class Home extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public Home () {
    super();
  }
  
  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    String idApp = null;
    ServletContext sc = getServletContext();
    
    if ( request != null ) {
      idApp = request.getParameter( "idApp" );
      HttpSession session = request.getSession( true );
      session.invalidate();
      session = request.getSession( true );
      session.setAttribute( "referringPage", request.getHeader( "Referer" ) );
      session.setAttribute( "idApp", idApp );
      response.sendRedirect( sc.getContextPath() );
    }
  }
  
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    String idApp = null;
    ServletContext sc = getServletContext();
    if ( request != null ) {
      idApp = request.getParameter( "idApp" );
      HttpSession session = request.getSession( true );
      session.setAttribute( "referringPage", request.getHeader( "Referer" ) );
      session.setAttribute( "idApp", idApp );
      response.sendRedirect( sc.getContextPath() );
    }
  }
}
