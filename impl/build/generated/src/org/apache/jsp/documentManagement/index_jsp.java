package org.apache.jsp.documentManagement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.sql.*;
import seks.basic.database.DatabaseInteraction;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1 align=\"center\">List of Folders!</h1>\n");
      out.write("        <form name=\"list_folders\" action=\"list_files.jsp\">\n");
      out.write("            <table align=\"center\">\n");
      out.write("                <tbody>\n");
      out.write("\n");
      out.write("                    ");

                        //Cria Ligação à BD
                        //Invoca o procedimento de list_folders (lista as pastas)
                        try {
                            DatabaseInteraction di = new DatabaseInteraction();
                            Connection con = di.openConnection();
                            ResultSet rs = di.callProcedure(con, "lportal.list_folders");
                            while (rs.next()) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td align=\"center\">\n");
      out.write("                            <img src=\"/images/folder.png\" alt=\"");
      out.print( rs.getString("name"));
      out.write("\" width=\"20%\" height=\"20%\" >\n");
      out.write("                            <br>\n");
      out.write("                            <input type=\"submit\" value=\"");
      out.print( rs.getString("name"));
      out.write("\" name=\"btnListFiles\">\n");
      out.write("                        </td>\n");
      out.write("                        <td><i>");
      out.print( rs.getString("description"));
      out.write("</i></td>\n");
      out.write("                        <td><input type=\"hidden\" value=\"");
      out.print( rs.getString("folderId"));
      out.write("\" name=\"folderId");
      out.print( rs.getString("name"));
      out.write("\"></td>\n");
      out.write("                    </tr>    \n");
      out.write("                    ");

                        ResultSet rsSub = di.callProcedure(con, "lportal.list_subfolders(" + rs.getString("folderId") + ")");
                        while (rsSub.next()) {
                    
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td align=\"right\">\n");
      out.write("                            <img src=\"/images/folder.png\" alt=\"");
      out.print( rsSub.getString("name"));
      out.write("\" width=\"20%\" height=\"20%\" >\n");
      out.write("                            <br>\n");
      out.write("                            <input type=\"submit\" value=\"");
      out.print( rsSub.getString("name"));
      out.write("\" name=\"btnListFiles\">\n");
      out.write("                        </td>\n");
      out.write("                        <td><i>");
      out.print( rsSub.getString("description"));
      out.write("</i></td>\n");
      out.write("                        <td><input type=\"hidden\" value=\"");
      out.print( rsSub.getString("folderId"));
      out.write("\" name=\"folderId");
      out.print( rsSub.getString("name"));
      out.write("\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    ");

                                }
                            }
                            di.closeConnection(con);
                        } catch (SQLException e) {
                            System.out.println("SQL Exception: " + e.toString());
                        }
                    
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("            <input type=\"hidden\" name=\"nome\" value=\"123\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
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
