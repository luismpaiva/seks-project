<%-- 
    Document   : indexation
    Created on : Dec 29, 2011, 6:50:23 PM
    Author     : RDDC
    Desctiption: Efectua a indexação dos novos knowledge Sources
--%>

<%@page import="seks.basic.pojos.SemanticWeight"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreation"%>
<%@page import="java.util.Enumeration"%>
<%@page import="org.hsqldb.lib.Collection"%>

<%@page import="seks.basic.database.*"%>
<%@page import="java.sql.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        //String documentId = "";    
        //String folderName = request.getParameter("btnIndex");
           KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                String paramValue = paramValues[0];
                if (request.getParameter("chk" + paramValue) != null)
                out.print("<br><I>Indexing</I>" + paramValue);
                HashMap<String, Double> statVector = svCreator.getStatisticalVectorByDocumentURI(paramValue) ;
                HashMap<String, ArrayList<String>> conceptsAndKeywords = svCreator.getConceptsFromKeywords(statVector) ;
                HashMap<String, Double> conceptsAndWeights = svCreator.getConceptsTotalWeights(statVector, conceptsAndKeywords) ;
                ArrayList<String> sortedList = svCreator.sortConceptsByRelevance(conceptsAndWeights) ;
                HashMap<String, SemanticWeight> semanticVector = svCreator.createKeywordBasedSemanticVector("xpto1", conceptsAndWeights, sortedList);
                //svCreator.storeSemanticVector(semanticVector, paramValue);
                Iterator<String> j = semanticVector.keySet().iterator() ;
                while (j.hasNext()) {
                    String concept = (String) j.next() ;
                    SemanticWeight sw = semanticVector.get(concept) ;
                %>
                <!-- <tr>
                        <td><%= concept%></td>
                        <td><%= sw.getWeight()%></td>
                    </tr>  -->    
                <%  
                }
            }
        %>
    </body>
</html>
