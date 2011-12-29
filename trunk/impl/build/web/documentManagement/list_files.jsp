<%-- 
    Document   : list_files
    Created on : 28/Abr/2011, 16:11:15
    Author     : Administrador
    Desctiption: Lista os vários ficheiros dentro de uma pasta
--%>
<%@page import="java.sql.*"%>
<%@page import="seks.basic.database.DatabaseInteraction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 align="center">List of files!</h1>
        <form name="list_files" action="indexation.jsp">
            <table align="center">
                <tbody>

                    <%
                        //Cria Ligação à BD
                        //Invoca o procedimento de list_files (lista os ficeiros)
                        try {
                            String folderName = request.getParameter("btnListFiles");
                            String folderId = request.getParameter("folderId" + folderName);
                            DatabaseInteraction di = new DatabaseInteraction();
                            Connection con = di.openConnection();
                            ResultSet rs = di.callProcedure(con, "list_files(" + folderId + ")");
                            while (rs.next()) {
                    %>
                    <tr>
                        <td align="center">
                            <%
                                if (rs.getString("extension").equals("txt")) {
                            %><img src="/images/notepad.gif" width="50%" height="50%"><%
                                } else if (rs.getString("extension").equals("pdf")) {
                                  %><img src="/images/pdf.png" width="50%" height="50%"><%
                                }
                            %>
                            <br>
                            <input type="submit" value="<%= rs.getString("title")%>" name="btnListTags"  />
                        </td>
                        <td>
                            <i><%= rs.getString("description")%></i>
                            <br>
                            <p>Version <%= rs.getString("version")%></p>
                        </td>
                        <td><input type="hidden" value="<%= rs.getString("fileEntryId")%>" name="fileId<%= rs.getString("title")%>"/></td>
                    </tr>
                    <%
                            }
                            di.closeConnection(con);
                        } catch (SQLException e) {
                            System.out.println("SQL Exception: " + e.toString());
                        }
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>
