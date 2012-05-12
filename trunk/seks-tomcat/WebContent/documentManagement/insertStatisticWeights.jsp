<%-- 
    Document   : indexation
    Created on : Dec 29, 2011, 6:50:23 PM
    Author     : RDDC
    Desctiption: Efectua a indexação dos novos knowledge Sources
--%>
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
        DatabaseInteraction di = new DatabaseInteractionImpl() ;
		Connection con = di.openConnection("svdbConfig.xml") ;
		ResultSet files = di.callProcedure(con, "getDocumentMetadataFiles") ;
		ResultSet paths = di.callProcedure(con, "getDocumentMetadataPaths") ;
		files.next() ;
		paths.next() ;
		for (int i = 0; i < 71; i++) {
			String file = files.getString("att_" + (i+1)) ;
			String path = files.getString("att_" + (i+1)) ;
			di.callProcedure(con, "svdb.insertDocument('" + file + "', '" + path + "', '.txt', false, 'att_" + (i+1) + "')") ;
		}
		files.close() ;
		paths.close() ;
		
		for (int i = 0 ; i < 71; i++) {
			ResultSet rs = di.callProcedure(con, "svdb.getTempStatisticWeight") ;
			ResultSet rsAux = di.callProcedure(con, "getDocumentIdByLiferayId('att_" + (i+1) + "')") ;
			int idDocument ;
			if (rsAux.next()) {
				idDocument = rsAux.getInt("idDocument") ;
				try {
					while(rs.next()) {
						String keyword = rs.getString("id") ;
						if (!(keyword.startsWith("metadata"))) {
							Double weight = rs.getDouble("att_" + (i+1)) ;
							if (!(weight == 0.0)) {
								di.callProcedure(con, "insertStatisticWeight('" + keyword + "', " + weight + ", " + idDocument + ")") ;
							}
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			rsAux.close() ;
		}
		di.closeConnection(con) ;
		%>
</body>
</html>