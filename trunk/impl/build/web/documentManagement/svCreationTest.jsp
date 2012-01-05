<%-- 
    Document   : index
    Created on : 14/Abr/2011, 16:57:14
    Author     : Administrador
--%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.Writer"%>
<%@page import="com.hp.hpl.jena.rdf.model.NodeIterator"%>
<%@page import="com.hp.hpl.jena.rdf.model.Resource"%>
<%@page import="com.hp.hpl.jena.util.iterator.ExtendedIterator"%>
<%@page import="com.hp.hpl.jena.ontology.OntModel"%>
<%@page import="seks.basic.ontology.OntologyPersistenceImpl"%>
<%@page import="seks.basic.ontology.OntologyPersistence"%>
<%@page import="com.hp.hpl.jena.ontology.OntClass"%>
<%@page import="com.hp.hpl.jena.rdf.model.Property"%>
<%@page import="com.hp.hpl.jena.ontology.Individual"%>
<%@page import="seks.basic.ontology.OntologyInteractionImpl"%>
<%@page import="seks.basic.ontology.OntologyInteraction"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Semantic Vector Creation Test</h1>
            <form name="list_folders" action="list_files.jsp">

                 <table border="1">
                   <thead>
                        <tr>
                            <th>Keyword</th>
                            <th>Weight</th>
                        </tr>
                    </thead>
                    <tbody>

        <%
                    KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
                    HashMap<String, Double> statVector = svCreator.getStatisticalVectorByDocumentURI("xpto1") ;
                    Iterator keywordIter = statVector.keySet().iterator() ;
                    
                    while (keywordIter.hasNext()) {
                        String keyword = (String) keywordIter.next() ;
                        Double weight = statVector.get(keyword) ;
        %>
                        <tr>
                            <td><%= keyword%></td>
                            <td><%= weight.toString()%></td>
                        </tr>
        <%
                        
                    }
                    %>
                        </tbody>
                    </table>

                 <%
                
                %>
             <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Keywords</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    HashMap<String, ArrayList<String>> conceptsAndKeywords = svCreator.getConceptsFromKeywords(statVector) ;
                    Iterator conceptsIter = conceptsAndKeywords.keySet().iterator() ;
                    while (conceptsIter.hasNext()) {
                        String concept = (String) conceptsIter.next() ;
                        ArrayList<String> keywords = conceptsAndKeywords.get(concept) ;
                        String keywordList = "" ;
                        for(int i = 0; i < keywords.size(); i++) {
                            if (i == 0) {
                                keywordList = keywords.get(i) ;
                            }
                            else {
                                keywordList = keywordList.concat(", " + keywords.get(i)) ;
                            }
                        }
                        %>
                        <tr>
                            <td><%= concept%></td>
                            <td><%= keywordList%></td>
                        </tr>
                        <%
                        
                    }
                    %>
                    </tbody>
                    </table>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Total weights</th>
                        </tr>
                    </thead>
                    <tbody>    
                    <%
                    HashMap<String, Double> conceptsAndWeights = svCreator.getConceptsTotalWeights(statVector, conceptsAndKeywords) ;
                    conceptsIter = conceptsAndWeights.keySet().iterator() ;
                    while (conceptsIter.hasNext()) {
                        String concept = (String) conceptsIter.next() ;
                        Double weight = conceptsAndWeights.get(concept) ;
                        %>
                        <tr>
                            <td><%= concept%></td>
                            <td><%= weight%></td>
                        </tr>
                        <%
                        
                    }
        %>
                </tbody>
            </table>
                
                <%
                 ArrayList<String> sortedList = svCreator.sortConceptsByRelevance(conceptsAndWeights) ;
                 for(int i = 0; i < sortedList.size();i++) {
                     String concept = sortedList.get(i) ;
                     String res = (i+1) + " - " + concept ;
                     %>
                     <p><%= res%></p>
                    <%
                 }
                %>
                <br/>
                <h1>Keyword-based Semantic Vector</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Weights after TF-IDF</th>
                        </tr>
                    </thead>
                    <tbody>    
                <%
                    HashMap<String, Double> semanticVector = svCreator.createKeywordBasedSemanticVector("xpto1", conceptsAndWeights, sortedList);
                    Iterator<String> j = semanticVector.keySet().iterator() ;
                    while (j.hasNext()) {
                        String concept = (String) j.next() ;
                        Double weight = semanticVector.get(concept) ;
                    %>
                        <tr>
                            <td><%= concept%></td>
                            <td><%= weight%></td>
                        </tr>
                    <%  
                    }
                %>
                    </tbody>
                </table>
            </form>

        </body>
</html>