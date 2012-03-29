package seks.advanced.semantic.vectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.database.DatabaseInteraction;
import seks.basic.database.DatabaseInteractionImpl;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 *Implementation class for interface SemanticVectorComparison
 * 
 * @author Paulo Figueiras
 */
public class SemanticVectorComparisonImpl implements SemanticVectorComparison {
    
    /**
     * Class constructor.
     */
    public SemanticVectorComparisonImpl () {}
    
    /**
     * Infers on the concepts shared between two semantic vectors.
     * 
     * @param semanticVector1   The document's semantic vector
     * @param semanticVector2   The query's semantic vector
     * 
     * @return                  The list of shared concepts, in the form of a 
     *                          {@link java.util.ArrayList} object
     */
    @Override
    public ArrayList<String> getSharedConcepts(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2) {
        ArrayList<String> sharedConcepts = new ArrayList<String>() ;
        Iterator<String> iter1 = semanticVector1.keySet().iterator() ;
        
        while (iter1.hasNext()) {
            String concept1 = (String) iter1.next() ;
            Iterator<String> iter2 = semanticVector2.keySet().iterator() ;
            while (iter2.hasNext()) {
                String concept2 = (String) iter2.next() ;
                if (concept1.equals(concept2)) {
                    sharedConcepts.add(concept2) ;
                    break ;
                }
            }
        }
        
        return sharedConcepts ;
    }
    
    /**
     * Compares two semantic vectors, through the application of the Euclidean 
     * Distance Algorithm.
     * 
     * @param semanticVector1   The document's semantic vector
     * @param semanticVector2   The query's semantic vector
     * @param sharedConcepts    The list of shared concepts between the two 
     *                          vectors
     *  
     * @return                  The percentage of likeliness between the 
     *                          document and the query, in the form of a 
     *                          {@link DocumentResult} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#getSharedConcepts(java.util.HashMap, java.util.HashMap) 
     * @see seks.basic.pojos.DocumentResult
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#euclidianDistanceAlgorithm(double, double, double) 
     */
    @Override
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) {
        double sharedWeightsSum = 0.0 ;
        double vector1WeightsSum = 0.0 ;
        double vector2WeightsSum = 0.0 ;
        DocumentResult docResult = new DocumentResult();
        CalculusTools ct = new CalculusToolsImpl() ;
        int idDocument = semanticVector1.get(semanticVector1.keySet().iterator().next()).getIdDocument() ;
        
        if (sharedConcepts.isEmpty()) {
            docResult.setIdDocument(idDocument) ;
            docResult.setRelevancePercentage(0) ;
        }
        else {
            Iterator<String> iter = sharedConcepts.iterator() ;
            while (iter.hasNext()) {
                String concept = (String) iter.next() ;
                double weight1 = semanticVector1.get(concept).getWeight() ;
                double weight2 = semanticVector2.get(concept).getWeight() ;
                
                sharedWeightsSum += weight1*weight2 ;
            }
            
            iter = semanticVector1.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector1.get((String) iter.next()).getWeight() ;
                vector1WeightsSum += weight*weight ;
            }
            
            iter = semanticVector2.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector2.get((String) iter.next()).getWeight() ;
                vector2WeightsSum += weight*weight ;
            }
            
            int result = ct.euclidianDistanceAlgorithm(sharedWeightsSum, vector1WeightsSum, vector2WeightsSum) ;
            docResult.setIdDocument(idDocument) ;
            docResult.setRelevancePercentage(result) ;
        }
        return docResult ;
    }
    
    /**
     * Retrieves from the database the set of {@link seks.basic.pojos.SemanticWeight} instances 
     * which have the foreign key given by the input parameter.
     * 
     * @param documentID    An unique document's URI
     * 
     * @return              A document's semantic vector, in the form of an 
     *                      {@link java.util.HashMap} object, with the most relevant 
     *                      concepts as the set of keys, and the {@link seks.basic.pojos.SemanticWeight}
     *                      objects as values
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see seks.basic.pojos.SemanticWeight
     * @see java.util.HashMap
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
    @Override
    public HashMap<String, SemanticWeight> getSemanticVectorByDocumentID(int documentID) {
        DatabaseInteraction di = new DatabaseInteractionImpl() ;
        HashMap<String, SemanticWeight> semanticVector = new HashMap<String, SemanticWeight>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        try {
            ResultSet params = di.callProcedure(con, "svdb.getKeywordBasedWeightsWithDocID(" + documentID + ")") ;
            while (params.next()) { 
                SemanticWeight sw = new SemanticWeight(documentID, params.getString("concept"), params.getDouble("weight")) ;
                semanticVector.put(sw.getConcept(), sw) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemanticVectorComparisonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        di.closeConnection(con) ;
        return semanticVector ;
    }
    
    /**
     * Retrieves the URI's of all documents comprised in the system's document 
     * repository.
     * 
     * @return  The list of URI's in the form of an {@link java.util.ArrayList} object
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.util.ArrayList
     */
    @Override
    public ArrayList<Integer> getDocumentIds() {
        DatabaseInteraction di = new DatabaseInteractionImpl() ;
        ArrayList<Integer> Ids = new ArrayList<Integer>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        try {
            ResultSet rs = di.callProcedure(con, "svdb.getAllDocumentIDs") ;
            while(rs.next()) {
                Ids.add(rs.getInt("idDocument")) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemanticVectorComparisonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Ids ;
    }
    
    /**
     * Sorts the documents which form the response to a specific query, 
     * according to their relevance to resolve the query.
     * 
     * @param documentResults   The list of {@link seks.basic.pojos.DocumentResult} objects to be 
     *                          sorted
     * @return                  The list of sorted {@link seks.basic.pojos.DocumentResult} 
     *                          objects
     * 
     * @see seks.basic.pojos.DocumentResult
     * @see java.util.ArrayList
     */
    @Override
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) {
        ArrayList<DocumentResult> sortedResults = new ArrayList<DocumentResult>() ;
        Iterator<DocumentResult> iter = documentResults.iterator() ;
        while (iter.hasNext()) {
            DocumentResult dr = (DocumentResult) iter.next() ;
            if (!(dr.getRelevancePercentage() == 0)) {
                if (sortedResults.isEmpty()) {
                    sortedResults.add(dr) ;
                }
                else {
                    int relevance = dr.getRelevancePercentage() ;
                    for (int i = 0; i < sortedResults.size(); i++) {
                        DocumentResult current = sortedResults.get(i) ;
                        int currRelevance = current.getRelevancePercentage() ;
                        if (relevance >= currRelevance) {
                            sortedResults.add(i, dr) ;
                            break ;
                        }
                        else if (relevance < currRelevance && i== sortedResults.size() - 1) {
                            sortedResults.add(sortedResults.size(), dr) ;
                            break ;
                        }
                        else continue ;
                    }

                }
            }
        }
        return sortedResults ;
    }
    
    /**
     * 
     */
    
    public HashMap<String, SemanticWeight> vectorUnion(HashMap<String, Double> statisticVector, HashMap<String, SemanticWeight> semanticVector, int idDocument) {
    	HashMap<String, SemanticWeight> unionVector = semanticVector ;
    	OntologyInteraction oi = new OntologyInteractionImpl() ;
    	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
    	Iterator<String> iter = statisticVector.keySet().iterator() ;
    	HashMap<String, ArrayList<String>> missingConceptsAndKeywords = new HashMap<String, ArrayList<String>>() ;
    	
    	while(iter.hasNext()) {
            String keyword = (String) iter.next() ;
            ArrayList<String> concepts = oi.getSubjectsFromTriple(keyword, "has_Keyword") ;
            if (!concepts.isEmpty()) {
            	for (int i = 0 ; i < concepts.size(); i++) {
	                String concept = concepts.get(i) ;
	                if (!semanticVector.containsKey(concept)) {
	                	ArrayList<String> keywords = new ArrayList<String>() ;
	                	if (missingConceptsAndKeywords.containsKey(concept)) {
	                        keywords = (ArrayList<String>) missingConceptsAndKeywords.get(concept) ;
	                        keywords.add(keyword);
	                        missingConceptsAndKeywords.put(concept, keywords) ;
	                    }
	                    else {
	                        keywords = new ArrayList<String>() ;
	                        keywords.add(keyword);
	                        missingConceptsAndKeywords.put(concept, keywords) ;
	                    }
	                }
            	}
            }
            else {
            	SemanticWeight weight = new SemanticWeight(idDocument, keyword, statisticVector.get(keyword)) ;
            	unionVector.put(keyword, weight) ;
            }
        }
    	return kbsvCreator.semanticVectorNormalization(unionVector) ;
    }
}
