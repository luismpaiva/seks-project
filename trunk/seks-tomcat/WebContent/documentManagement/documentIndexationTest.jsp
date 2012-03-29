<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreation"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl"%>
<%@page import="java.sql.*"%>
<%@page import="seks.basic.database.*"%>
<%@page import="java.util.*"%>
<%@page import="seks.basic.pojos.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	DatabaseInteraction di = new DatabaseInteractionImpl() ;
	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
	Connection con = di.openConnection("svdbConfig.xml") ;
	ResultSet set = di.callProcedure(con, "svdb.getNotIndexedDocumentIDs") ;
	while (set.next()) {
		int documentId = set.getInt("idDocument") ;
		
		//Keyword-based Semantic Vector Creation
		
		HashMap<String, Double> statVector = kbsvCreator.getStatisticalVectorByDocumentID(documentId) ;
		HashMap<String, HashMap<String, Double>> conceptsKeywordsAndWeights = kbsvCreator.getConceptsAndWeightsFromKeywords(statVector) ;
		HashMap<String, Double> conceptsAndWeights = kbsvCreator.getConceptsTotalWeights(conceptsKeywordsAndWeights) ;
		ArrayList<String> sortedList = kbsvCreator.sortConceptsByRelevance(conceptsAndWeights) ;
		HashMap<String, SemanticWeight> kbSemanticVector = kbsvCreator.createKeywordBasedSemanticVector(documentId, conceptsAndWeights, sortedList);
		HashMap<String, SemanticWeight> normalizedKbVector = kbsvCreator.semanticVectorNormalization(kbSemanticVector) ;
		kbsvCreator.storeKeywordBasedSemanticVector(normalizedKbVector);
	}
	di.closeConnection(con) ;
	String stop = "" ;
		
%>
</body>
</html>