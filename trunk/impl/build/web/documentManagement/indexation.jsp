<%-- 
    Document   : indexation
    Created on : Dec 29, 2011, 6:50:23 PM
    Author     : RDDC
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="org.hsqldb.lib.Collection"%>
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
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                String paramValue = paramValues[0];
                if (request.getParameter("chk" + paramValue) != null)
                out.print("<br><I>Indexing</I>" + paramValue);
            }
        %>
    </body>
</html>
