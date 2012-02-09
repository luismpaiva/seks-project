<%-- 
    Document   : taxonomySVCreationTest
    Created on : 13/Jan/2012, 1:00:20
    Author     : Paulo Figueiras
--%>

<%@page import="seks.advanced.semantic.vectors.TaxonomyBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.TaxonomyBasedSVCreation"%>
<%@page import="seks.basic.ontology.OntologyInteractionImpl"%>
<%@page import="seks.basic.ontology.OntologyInteraction"%>
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
            OntologyInteraction oi = new OntologyInteractionImpl() ;
            int j = oi.getSubClassesNumber("Actor");
            TaxonomyBasedSVCreation tbsvCreator = new TaxonomyBasedSVCreationImpl() ;
            String i = tbsvCreator.checkHomologyBetweenConcepts("Architect", "Project") ;
        %>
        <p><%= i%></p>
        <p><%= j%></p>
    </body>
</html>
