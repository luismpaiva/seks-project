<%-- 
    Document   : list_files
    Created on : 28/Abr/2011, 16:11:15
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
        <h1>Hello World!</h1>

        <p>List Files!</p>
        <form name="list_files" action="indexation.jsp">

            <table border="1">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Extension</th>
                        <th>Version</th>
                    </tr>
                </thead>
                <tbody>

                    <%
                                //Cria Ligação à BD
                                try {
                                    String folderName = request.getParameter("btnListFiles");
                                    String folderId = request.getParameter("folderId" + folderName);
                                    DatabaseInteraction di = new DatabaseInteraction() ;
                                    Connection con = di.openConnection() ;
                                    ResultSet rs = di.listFiles(con, folderId) ;
                                    //Class.forName("com.mysql.jdbc.Driver");
                                    //String connectionUrl = "jdbc:mysql://172.16.3.139:3306/lportal?"
                                    //        + "user=root&password=gris";
                                    //Connection con = DriverManager.getConnection(connectionUrl);
                                    //CallableStatement statement = con.prepareCall("call lportal.list_files(" + folderId + ")");
                                    //statement.execute();
                                    //ResultSet rs = statement.getResultSet();


                                    //KnowledgeSource ks = new KnowledgeSource();

                                    //ResultSet rs = con.prepareCall("{call " + "list_folders}").getResultSet();
                                    //ResultSet rs =  ks.callProcedure(con, "list_folders");

                                    while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("title")%></td>
                        <td><%= rs.getString("description")%></td>
                        <td><%= rs.getString("extension")%></td>
                        <td><%= rs.getString("version")%></td>
                        <td><input type="submit" value="<%= rs.getString("title")%>" name="btnListTags"  /></td>
                        <td><input type="text" value="<%= rs.getString("fileEntryId")%>" name="fileId<%= rs.getString("title")%>"/></td>
                    </tr>
                    <%
                                    }
                                    di.closeConnection(con) ;
                                } catch (SQLException e) {
                                    System.out.println("SQL Exception: " + e.toString());
                                }
                    %>
                </tbody>
            </table>
        </form>
    </body>
</html>
