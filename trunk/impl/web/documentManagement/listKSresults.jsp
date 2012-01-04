<%-- 
    Document   : listKSresults
    Created on : Dec 30, 2011, 2:49:25 PM
    Author     : RDDC
    Desctiption: Lista os resultados dos KSs em funçao da query
--%>

<%@page import="java.util.HashMap"%>
<%@page import="seks.advanced.queries.QueryTreatment"%>
<%@page import="seks.advanced.queries.QueryTreatmentImpl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String query = request.getParameter("txtQuery") ;
            QueryTreatment qt = new QueryTreatmentImpl() ;
            ArrayList<String> keywords = qt.getQueryKeywords(query) ;
        %>
        
        <h1>Keyword Division</h1>
        <table>
        <%
            Iterator iter = keywords.iterator() ;
            while (iter.hasNext()) {
        %>
            <tr>       
                <td><%= (String) iter.next()%></td>
            </tr>
        <% 
            }
        %>
        </table>
        <br/>
        <h1>Query Statistic Vector</h1>
        <table>
        <%
            HashMap<String, Double> queryStatVector = qt.createQueryStatisticVector(keywords) ;
            iter = queryStatVector.keySet().iterator() ;
            while (iter.hasNext()) {
               String keyword = (String) iter.next() ; 
               double weight = queryStatVector.get(keyword) ;
        %>
            <tr>
                <td><%= keyword%></td>
                <td><%= weight%></td>
            </tr>
        <%
            }
        %>
        </table>
    </body>
</html>
