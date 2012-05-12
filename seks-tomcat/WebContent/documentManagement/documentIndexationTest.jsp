<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreation"%>
<%@page import="seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.TaxonomyBasedSVCreation"%>
<%@page import="seks.advanced.semantic.vectors.TaxonomyBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.OntologyBasedSVCreation"%>
<%@page import="seks.advanced.semantic.vectors.OntologyBasedSVCreationImpl"%>
<%@page import="seks.advanced.semantic.vectors.SemanticVectorComparison"%>
<%@page import="seks.advanced.semantic.vectors.SemanticVectorComparisonImpl"%>
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
	/*Connection con = di.openConnection("svdbConfig.xml") ;
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
		kbsvCreator.storeKeywordBasedSemanticVector(normalizedKbVector, conceptsKeywordsAndWeights);
	}*/
	
	//di.closeConnection(con) ;
	TaxonomyBasedSVCreation tbsvCreator = new TaxonomyBasedSVCreationImpl() ;
	OntologyBasedSVCreation obsvCreator = new OntologyBasedSVCreationImpl() ;
	ArrayList<Integer> docs = new ArrayList<Integer>() ;
	SemanticVectorComparison comp = new SemanticVectorComparisonImpl() ;
	
	//docs.add(145) ;
	docs.add(146) ;
	//docs.add(147) ;
	//docs.add(148) ;
	//docs.add(149) ;
	//docs.add(150) ;
	//docs.add(151) ;
	Iterator<Integer> iter = docs.iterator() ;
	while (iter.hasNext()) {
		int documentId = iter.next() ;
		HashMap<String, SemanticWeight> kbSemanticVector = comp.getSemanticVectorByDocumentID(documentId, 2) ; 
		//HashMap<String, Double> statVector = kbsvCreator.getStatisticalVectorByDocumentID(documentId) ;
		
		//Taxonomy-based Semantic Vector Creation
		
		//HashMap<String, SemanticWeight> tbSemanticVector = tbsvCreator.createTaxonomyBasedSemanticVector(kbSemanticVector) ;
		//tbsvCreator.storeTaxonomyBasedSemanticVector(tbSemanticVector) ;
		
		//Ontology-based Semantic Vector Creation
		
		HashMap<String, SemanticWeight> obSemanticVector = obsvCreator.createOntologyBasedSemanticVector(kbSemanticVector) ;
		obsvCreator.storeOntologyBasedSemanticVector(kbsvCreator.semanticVectorNormalization(obSemanticVector)) ;
		
		//Vector Union
		
		//HashMap<String, SemanticWeight> unifiedTestVector = comp.vectorUnion(statVector, obSemanticVector, documentId, kbSemanticVector) ;
	}
%>
</body>
</html>