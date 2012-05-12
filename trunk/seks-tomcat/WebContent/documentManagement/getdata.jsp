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

        <%
            //DatabaseInteraction di = new DatabaseInteractionImpl();
            //Connection con = di.openConnection("jenaConfig.xml");
            //String query = request.getParameter("q");
           /*ResultSet rs = di.callProcedure(con, "ontomap.list_keywords('" + query + "')");
           //ResultSet rs = stmt.executeQuery("SELECT description FROM svdb.document WHERE description LIKE '" + query + "%'");
           while (rs.next()) {
               out.println(rs.getString("keyword"));
           }
           di.closeConnection(con);*/
            OntologyInteraction oi = new OntologyInteractionImpl() ;
            String keyword = "" ;
            ArrayList<String> list = oi.getAllValuesFromProperty("has_Keyword") ;
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    keyword = keyword.concat(list.get(i)) ;
                }
                else{
                    keyword = keyword.concat("," + list.get(i)) ;
                }
            }
            out.println(keyword) ;
            
        %>

