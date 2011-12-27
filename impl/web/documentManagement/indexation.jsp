<%-- 
    Document   : indexation
    Created on : 29/Jun/2011, 10:03:09
    Author     : Administrador
--%>
<%@page import="java.sql.*"%>
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

         <table border="1">
                <thead>
                    <tr>
                        <th>Relevant Keywords</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                                //Cria Ligação à BD
                                try {
                                    String fileName = request.getParameter("btnListTags");
                                    String fileId = request.getParameter("fileId" + fileName);
                                    Class.forName("com.mysql.jdbc.Driver");
                                    String connectionUrl = "jdbc:mysql://localhost:3306/lportal?"
                                            + "user=root&password=gris";
                                    Connection con = DriverManager.getConnection(connectionUrl);
                                    CallableStatement statement = con.prepareCall("call lportal.list_tags(" + fileId + ")");
                                    statement.execute();
                                    ResultSet rs = statement.getResultSet();


                                    //KnowledgeSource ks = new KnowledgeSource();

                                    //ResultSet rs = con.prepareCall("{call " + "list_folders}").getResultSet();
                                    //ResultSet rs =  ks.callProcedure(con, "list_folders");

                                    while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("name")%></td>
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

    </body>
</html>
