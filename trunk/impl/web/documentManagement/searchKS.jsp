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
        <script src="js/jquery-1.7.1.js"></script>
        <script src="js/jquery.autocomplete.js"></script> 
        <script type="text/javascript">
            function loadAutocomplete() {
                var counter = 0 ;
                var keywords = '' ;
                $.ajax({
                    type: "GET",
                    url: "getdata.jsp" ,
                    success: function(data) {
                        keywords = data.split(',') ;
                        $('#query').autocomplete(keywords).result(function() {
                            var keywords = $('#keywords').val() ;
                            if (counter == 0) {
                                $('#keywords').val($(this).val()) ;
                            }
                            else {
                                $('#keywords').val(keywords + ', ' + $(this).val()) ;
                            }
                            $(this).val('') ;
                            $(this).focus() ;
                            counter++ ;
                        }) ;
                    }
                }) ;
            }
        </script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="javascript:loadAutocomplete();"  >
        <h1>Hello World!</h1>
        <form name="queryKS" action="listKSresults.jsp" method="POST" >
            <h2>Please type your query here, one keyword at a time:</h2>
            <input type="text" id="query" name="query" size="40" autocomplete="off" />
            <h3>Keywords:</h3>
            <input type="text" id="keywords" name="keywords" size="100" readonly="true" />
            <input type="submit" name="btnSubmit" value="Search!" />
        </form>
    </body>
</html>
