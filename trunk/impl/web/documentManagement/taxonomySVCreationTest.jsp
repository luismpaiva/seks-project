<%-- 
    Document   : taxonomySVCreationTest
    Created on : 13/Jan/2012, 1:00:20
    Author     : Paulo Figueiras
--%>

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
            int i = oi.getClassDepth("Design_Actor") ;
        %>
        <p><%= i%></p>
    </body>
</html>
