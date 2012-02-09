<%-- 
    Document   : list_nonIndexed_files
    Created on : Dec 29, 2011, 5:48:18 PM
    Author     : RDDC
    Desctiption: Lista os ficheiros que ainda não foram indexados
--%>

<%@page import="seks.basic.database.DatabaseInteractionImpl"%>
<%@page import="java.sql.*"%>
<%@page import="seks.basic.database.DatabaseInteraction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of Non-Indexed Files!</h1>
        <form name="list_nonIndex_files" action="indexation.jsp">
            <table align="center">
                <tbody>
                    <%
                        //Cria Ligação à BD
                        //Invoca o procedimento de list_tags (lista as tags associadas a cada ficheiro)
                        try {
                            DatabaseInteraction di = new DatabaseInteractionImpl();
                            Connection con = di.openConnection("svdbConfig.xml");
                            ResultSet rs = di.callProcedure(con, "svdb.getListNonIndexed");
                            while (rs.next()) {
                    %>
                    <tr>
                        <td><%
                                if (rs.getString("extension").equals("txt")) {
                            %><img src="/images/notepad.gif" width="25%" height="25%"><%
                                } else if (rs.getString("extension").equals("pdf")) {
                                  %><img src="/images/pdf.png" width="25%" height="25%"><%
                                }
                            %>
                            <br>
                            <%= rs.getString("title")%>
                        </td>
                        <td><input type="hidden" value="<%= rs.getString("idDocument")%>" name="documentId<%= rs.getString("idDocument")%>"/></td>
                        <td><%= rs.getString("description")%></td>
                        <td><input type="checkbox" name="chk<%= rs.getString("idDocument")%>"></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
                <input type="submit" value="Indexation" name="btnIndex"  />
        </form>
        <%
                di.closeConnection(con);
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.toString());
            }
        %>
    </body>
</html>
