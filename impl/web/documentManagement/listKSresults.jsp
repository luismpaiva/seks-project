<%-- 
    Document   : listKSresults
    Created on : Dec 30, 2011, 2:49:25 PM
    Author     : RDDC
    Desctiption: Lista os resultados dos KSs em funçao da query
--%>

<%@page import="seks.basic.pojos.DocumentResult"%>
<%@page import="seks.advanced.semantic.vectors.SemanticVectorComparison"%>
<%@page import="seks.advanced.semantic.vectors.SemanticVectorComparisonImpl"%>
<%@page import="seks.basic.pojos.SemanticWeight"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreation"%>
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
            String query = request.getParameter("keywords") ;
            QueryTreatment qt = new QueryTreatmentImpl() ;
            ArrayList<String> keywords = qt.getQueryKeywords(query) ;
        %>
        <h1>Query: </h1>
        <p><%= query%></p>
        
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
        
        <%
                    KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        %>
            <h2>Ontology Concepts matched with Keywords</h2> 
            <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Keywords</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    HashMap<String, ArrayList<String>> conceptsAndKeywords = svCreator.getConceptsFromKeywords(queryStatVector) ;
                    Iterator conceptsIter = conceptsAndKeywords.keySet().iterator() ;
                    while (conceptsIter.hasNext()) {
                        String concept = (String) conceptsIter.next() ;
                        keywords = conceptsAndKeywords.get(concept) ;
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
                    <br/>
                     <h2>Ontology Concepts and Weights</h2> 
                <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Total weights</th>
                        </tr>
                    </thead>
                    <tbody>    
                    <%
                    HashMap<String, Double> conceptsAndWeights = svCreator.getConceptsTotalWeights(queryStatVector, conceptsAndKeywords) ;
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
                <h2>Sorted Concepts By Relevance</h2>
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
                    HashMap<String, SemanticWeight> semanticVector = svCreator.createKeywordBasedSemanticVector("query", conceptsAndWeights, sortedList);
                    Iterator<String> j = semanticVector.keySet().iterator() ;
                    while (j.hasNext()) {
                        String concept = (String) j.next() ;
                        Double weight = semanticVector.get(concept).getWeight() ;
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
                <br/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Concept</th>
                            <th>Weights after Normalization</th>
                        </tr>
                    </thead>
                    <tbody>    
                <%
                    HashMap<String, SemanticWeight> normalizedSemanticVector = svCreator.semanticVectorNormalization(semanticVector) ;
                    j = normalizedSemanticVector.keySet().iterator() ;
                    while (j.hasNext()) {
                        String concept = (String) j.next() ;
                        Double weight = semanticVector.get(concept).getWeight() ;
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
                    <br/>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Document</th>
                            <th>Relevance in relation to the Query</th>
                        </tr>
                    </thead>
                    <tbody>    
                <%
                    SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
                    ArrayList<String> uris = svc.getDocumentURIs() ;
                    ArrayList<DocumentResult> results = new ArrayList<DocumentResult>() ;
                    ArrayList<DocumentResult> sortedResults = new ArrayList<DocumentResult>() ;
                    iter = uris.iterator() ;
                    while (iter.hasNext()) {
                        HashMap<String, SemanticWeight> docSV = svc.getSemanticVectorByDocumentID((String) iter.next()) ;
                        ArrayList<String> sharedConcepts = svc.getSharedConcepts(normalizedSemanticVector, docSV) ;
                        //IMPORTANTE:  O primeiro vector é o do documento, o segundo é o da query
                        DocumentResult result = svc.compareSemanticVectors(docSV, normalizedSemanticVector, sharedConcepts) ;
                        results.add(result) ;
                    }
                    if (!results.isEmpty()) {
                        sortedResults = svc.sortDocumentResultsByRelevance(results) ;
                        iter = sortedResults.iterator() ;
                        while (iter.hasNext()) {
                            DocumentResult res = (DocumentResult) iter.next() ;
                            %>
                            <tr>
                                <td><%= res.getIdDocument()%></td>
                                <td><%= res.getRelevancePercentage()%>%</td>
                            </tr>
                            <%
                        }
                        %>
                          </tbody>
                        </table>  
                       <%    
                    }
                    else {
                  %>
                        </tbody>
                  </table>
                  <h2>No documents were found!!!</h2>
                  <%
                    }
                %>
    </body>
</html>
