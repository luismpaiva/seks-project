<%-- 
    Document   : indexation
    Created on : 29/Jun/2011, 10:03:09
    Author     : Administrador
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
        <h1>List of Tags</h1>
        <table border="1" align="center">
            <thead>
                <tr>
                    <th>Relevant Keywords</th>
                </tr>
            </thead>
            <tbody>
                <%
                    //Cria Ligação à BD
                    //Invoca o procedimento de list_tags (lista as tags associadas a cada ficheiro)
                    try {
                        String fileName = request.getParameter("btnListTags");
                        String fileId = request.getParameter("fileId" + fileName);
                        DatabaseInteraction di = new DatabaseInteraction();
                        Connection con = di.openConnection();
                        ResultSet rs = di.callProcedure(con, "list_tags(" + fileId + ")");
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("name")%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        rs = null;
        rs = di.callProcedure(con, "get_document_url(" + fileId + ")");
        rs.next();
        %>
        <p align="center"><a href=<%=rs.getString("documentUrl")%> > View Document</a><p>
        <%
                di.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.toString());
            }
        %>
    </body>
</html>
