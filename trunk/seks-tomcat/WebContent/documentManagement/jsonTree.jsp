<%@page import="seks.basic.ontology.OntologyInteractionImpl"%>
<%@page import="seks.basic.ontology.OntologyInteraction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="seks.advanced.web.services.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JsonTree Test</title>
</head>
<body>
	<%
		ClientSupportService service = new ClientSupportService() ;
		String json = service.getJSONOntologyTree("", "Concept", false) ;
	%>
	<p><%=json%></p>
</body>
</html>