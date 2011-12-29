package org.apache.jsp.documentManagement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import seks.basic.database.DatabaseInteraction;

public final class list_005fnonIndexed_005ffiles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>List of Non-Indexed Files!</h1>\n");
      out.write("        <form name=\"list_nonIndex_files\" action=\"indexation.jsp\">\n");
      out.write("            <table align=\"center\">\n");
      out.write("                <tbody>\n");
      out.write("                    ");

                        //Cria Ligação à BD
                        //Invoca o procedimento de list_tags (lista as tags associadas a cada ficheiro)
                        try {
                            DatabaseInteraction di = new DatabaseInteraction();
                            Connection con = di.openConnection();
                            ResultSet rs = di.callProcedure(con, "svdb.getListNonIndexed");
                            while (rs.next()) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>");

                                if (rs.getString("extension").equals("txt")) {
                            
      out.write("<img src=\"/images/notepad.gif\" width=\"25%\" height=\"25%\">");

                                } else if (rs.getString("extension").equals("pdf")) {
                                  
      out.write("<img src=\"/images/pdf.png\" width=\"25%\" height=\"25%\">");

                                }
                            
      out.write("\n");
      out.write("                            <br>\n");
      out.write("                            ");
      out.print( rs.getString("title"));
      out.write("\n");
      out.write("                        </td>\n");
      out.write("                        <td><input type=\"hidden\" value=\"");
      out.print( rs.getString("idDocument"));
      out.write("\" name=\"documentId");
      out.print( rs.getString("idDocument"));
      out.write("\"/></td>\n");
      out.write("                        <td>");
      out.print( rs.getString("description"));
      out.write("</td>\n");
      out.write("                        <td><input type=\"checkbox\" name=\"chk");
      out.print( rs.getString("idDocument"));
      out.write("\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("                <input type=\"submit\" value=\"Indexation\" name=\"btnIndex\"  />\n");
      out.write("        </form>\n");
      out.write("        ");

                di.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.toString());
            }
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
