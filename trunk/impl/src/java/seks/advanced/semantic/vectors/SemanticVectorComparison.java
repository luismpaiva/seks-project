package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 * Comprises functions and algorithms needed to preform semantic vector 
 * comparison.
 * 
 * @author Paulo Figueiras
 */
public interface SemanticVectorComparison {
    
    /**
     * Infers on the concepts shared between two semantic vectors.
     * 
     * @param semanticVector1   The document's semantic vector
     * @param semanticVector2   The query's semantic vector
     * 
     * @return                  The list of shared concepts, in the form of a 
     *                          {@link ArrayList} object
     */
    public ArrayList<String> getSharedConcepts(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2) ;
    
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
     * @see #getSharedConcepts(java.util.HashMap, java.util.HashMap) 
     * @see DocumentResult
     * @see CalculusTools
     * @see CalculusTools#euclidianDistanceAlgorithm(double, double, double) 
     */
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) ;
    
    /**
     * Retrieves from the database the set of {@link SemanticWeight} instances 
     * which have the foreign key given by the input parameter.
     * 
     * @param documentID    An unique document's URI
     * 
     * @return              A document's semantic vector, in the form of an 
     *                      {@link HashMap} object, with the most relevant 
     *                      concepts as the set of keys, and the {@link SemanticWeight}
     *                      objects as values
     * 
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see SemanticWeight
     * @see HashMap
     * @see Connection
     * @see ResultSet
     */
    public HashMap<String, SemanticWeight> getSemanticVectorByDocumentID(String documentID) ;
    
    /**
     * Retrieves the URI's of all documents comprised in the system's document 
     * repository.
     * 
     * @return  The list of URI's in the form of an {@link ArrayList} object
     * 
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see ArrayList
     */
    public ArrayList<String> getDocumentURIs() ;
    
    /**
     * Sorts the documents which form the response to a specific query, 
     * according to their relevance to resolve the query.
     * 
     * @param documentResults   The list of {@link DocumentResult} objects to be 
     *                          sorted
     * @return                  The list of sorted {@link DocumentResult} 
     *                          objects
     * 
     * @see #compareSemanticVectors(java.util.HashMap, java.util.HashMap, java.util.ArrayList) 
     * @see DocumentResult
     * @see ArrayList
     */
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) ;
}
