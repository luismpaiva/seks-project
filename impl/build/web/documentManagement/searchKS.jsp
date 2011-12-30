<%-- 
    Document   : searchKS
    Created on : Dec 30, 2011, 2:44:33 PM
    Author     : RDDC
    Desctiption: Página de pesquisa de KSs
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script> 
        <script src="js/jquery.autocomplete.js"></script> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form name="queryKS" action="listKSresults.jsp">
            Please type your query here:<input type="text" id="txtQuery" name="txtQuery" size="100">
            <script> 
                $("#txtQuery").autocomplete("getdata.jsp");
            </script> 
            <input type="submit" name="btnSubmit" value="Search!">
        </form>
    </body>
</html>
