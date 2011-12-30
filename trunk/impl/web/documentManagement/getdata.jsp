<%-- 
    Document   : getdata
    Created on : Dec 30, 2011, 3:13:43 PM
    Author     : RDDC
    Description:
--%>


<%@page import="seks.basic.database.DatabaseInteraction"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            DatabaseInteraction di = new DatabaseInteraction();
            Connection con = di.openConnection();
            String query = request.getParameter("q");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT description FROM svdb.document WHERE description LIKE '" + query + "%'");
            while (rs.next()) {
                out.println(rs.getString("description"));
            }
            di.closeConnection(con);
        %> 
    </body>
</html>
