<%-- 
    Document   : index
    Created on : 14/Abr/2011, 16:57:14
    Author     : Administrador
--%>
<%@page import="java.util.ArrayList"%>
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
        <h1>Hello World!</h1>
        <p>List Folders!</p>
                <form name="list_folders" action="list_files.jsp">

                <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>

        <%
                    //Cria Ligação à BD
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String connectionUrl = "jdbc:mysql://localhost:3306/lportal?"
                                + "user=root&password=gris";
                        Connection con = DriverManager.getConnection(connectionUrl);
                        CallableStatement statement = con.prepareCall("call lportal.list_folders");
                        statement.execute();
                        ResultSet rs = statement.getResultSet();


                        //KnowledgeSource ks = new KnowledgeSource();

                        //ResultSet rs = con.prepareCall("{call " + "list_folders}").getResultSet();
                        //ResultSet rs =  ks.callProcedure(con, "list_folders");

                        while (rs.next()) {
        %>
                <tr>
                    <td><%= rs.getString("name")%></td>
                    <td><%= rs.getString("description")%></td>
                    <td><input type="submit" value="<%= rs.getString("name")%>" name="btnListFiles"  /></td>
                    <td><input type="text" value="<%= rs.getString("folderId")%>" name="folderId<%= rs.getString("name")%>"/></td>
                </tr>
        <%
                        }
rs.close();
statement.close();

                    } catch (SQLException e) {
                        System.out.println("SQL Exception: " + e.toString());
                    } catch (ClassNotFoundException cE) {
                        System.out.println("Class Not Found Exception: " + cE.toString());
                    }
        %>
    </tbody>
        </table>
    <input type="text" name="nome" value="123" />
        </form>
    

   



   

        </body>
</html>
