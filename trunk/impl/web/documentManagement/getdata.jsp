<%-- 
    Document   : getdata
    Created on : Dec 30, 2011, 3:13:43 PM
    Author     : RDDC
    Description:
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="seks.basic.ontology.OntologyInteractionImpl"%>
<%@page import="seks.basic.ontology.OntologyInteraction"%>
<%@page import="seks.basic.database.DatabaseInteractionImpl"%>
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
            //DatabaseInteraction di = new DatabaseInteractionImpl();
            //Connection con = di.openConnection("jenaConfig.xml");
            String query = request.getParameter("q");
           /*ResultSet rs = di.callProcedure(con, "ontomap.list_keywords('" + query + "')");
           //ResultSet rs = stmt.executeQuery("SELECT description FROM svdb.document WHERE description LIKE '" + query + "%'");
           while (rs.next()) {
               out.println(rs.getString("keyword"));
           }
           di.closeConnection(con);*/
            OntologyInteraction oi = new OntologyInteractionImpl() ;
            ArrayList<String> list = oi.getAllValuesFromProperty("has_Keyword") ;
            Iterator iter = list.iterator() ;
            while (iter.hasNext()) {
                String keyword = (String) iter.next() ;
                if (keyword.startsWith(query)) {
                    out.println(keyword) ;
                }
            }
        %>
    </body>
</html>
