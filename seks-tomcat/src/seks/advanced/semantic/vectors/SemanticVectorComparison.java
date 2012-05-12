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
     *                          {@link java.util.ArrayList} object
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
     *                          {@link seks.basic.pojos.DocumentResult} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#getSharedConcepts(java.util.HashMap, java.util.HashMap) 
     * @see seks.basic.pojos.DocumentResult
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#euclidianDistanceAlgorithm(double, double, double) 
     */
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) ;
    
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
    public HashMap<String, SemanticWeight> getSemanticVectorByDocumentID(int documentID, int type) ;
    
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
    public ArrayList<Integer> getDocumentIds() ;
    
    /**
     * Sorts the documents which form the response to a specific query, 
     * according to their relevance to resolve the query.
     * 
     * @param documentResults   The list of {@link seks.basic.pojos.DocumentResult} objects to be 
     *                          sorted
     * @return                  The list of sorted {@link seks.basic.pojos.DocumentResult} 
     *                          objects
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#compareSemanticVectors(java.util.HashMap, java.util.HashMap, java.util.ArrayList) 
     * @see seks.basic.pojos.DocumentResult
     * @see java.util.ArrayList
     */
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) ;
    
    public HashMap<String, SemanticWeight> vectorUnion(HashMap<String, Double> statisticVector, HashMap<String, SemanticWeight> semanticVector, int idDocument, HashMap<String, SemanticWeight> kbSemanticVector) ; 
}
