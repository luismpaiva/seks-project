<%-- 
    Document   : index
    Created on : 14/Abr/2011, 16:57:14
    Author     : Administrador
    Desctiption: Lista as várias pastas do sistema de gestão documental
--%>
<%@page import="seks.basic.database.DatabaseInteractionImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="seks.basic.database.DatabaseInteractionImpl"%>
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
        <h1 align="center">List of Folders!</h1>
        <form name="list_folders" action="list_files.jsp">
            <table align="center">
                <tbody>

                    <%
                        //Cria Ligação à BD
                        //Invoca o procedimento de list_folders (lista as pastas)
                        try {
                            DatabaseInteraction di = new DatabaseInteractionImpl();
                            Connection con = di.openConnection("lportalConfig.xml");
                            ResultSet rs = di.callProcedure(con, "lportal.list_folders");
                            while (rs.next()) {
                    %>
                    <tr>
                        <td align="center">
                            <img src="/images/folder.png" alt="<%= rs.getString("name")%>" width="20%" height="20%" >
                            <br>
                            <input type="submit" value="<%= rs.getString("name")%>" name="btnListFiles">
                        </td>
                        <td><i><%= rs.getString("description")%></i></td>
                        <td><input type="hidden" value="<%= rs.getString("folderId")%>" name="folderId<%= rs.getString("name")%>"></td>
                    </tr>    
                    <%
                        ResultSet rsSub = di.callProcedure(con, "lportal.list_subfolders(" + rs.getString("folderId") + ")");
                        while (rsSub.next()) {
                    %>
                    <tr>
                        <td align="right">
                            <img src="/images/folder.png" alt="<%= rsSub.getString("name")%>" width="20%" height="20%" >
                            <br>
                            <input type="submit" value="<%= rsSub.getString("name")%>" name="btnListFiles">
                        </td>
                        <td><i><%= rsSub.getString("description")%></i></td>
                        <td><input type="hidden" value="<%= rsSub.getString("folderId")%>" name="folderId<%= rsSub.getString("name")%>"></td>
                    </tr>
                    <%
                                }
                            }
                            di.closeConnection(con);
                        } catch (SQLException e) {
                            System.out.println("SQL Exception: " + e.toString());
                        }
                    %>
                </tbody>
            </table>
            <input type="hidden" name="nome" value="123">
        </form>







    </body>
</html>
