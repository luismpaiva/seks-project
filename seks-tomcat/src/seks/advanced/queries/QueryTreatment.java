package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;
import seks.basic.pojos.SemanticWeight;

/**
 * Comprises functions and algorithms to manage and treat the system's input 
 * queries, such as dividing a query into keywords, or creating a statistical 
 * vector to the query.
 * 
 * @author Paulo Figueiras
 */
public interface QueryTreatment {
    
    /**
     * Prepares and manages query strings into separate keywords
     * 
     * @param query A query string
     * 
     * @return      An {@link java.util.ArrayList} object with keywords
     * 
     * @see java.util.ArrayList
     */
    public ArrayList<String> getQueryKeywords(String query) ;
    
    /**
     * Mimics the creation of statistic vectors through data-mining techniques, 
     * to generate a statistical vector for the query. The query is then 
     * considered a pseudo-document.
     * 
     * @param keywords  The query's keywords
     * 
     * @return          A statistic vector for the query, in the form of a 
     *                  {@ link java.util.HashMap} object, whith the keywords as the set 
     *                  of keys and the weights for the keywords as values
     * 
     * @see java.util.HashMap
     */
    public HashMap<String, Double> createQueryStatisticVector(ArrayList<String> keywords) ;
    
    
    /**
     * Mimics the creation of semantic vectors through the application of the 
     * tf-idf algorithm, to generate a semantic vector for the query. The query 
     * is considered a pseudo-document.
     * 
     * @param conceptsAndWeights    The query's concepts and respective weights, 
     *                              in the form of a {@ link java.util.HashMap} 
     *                              object, with the concepts as a set of keys
     *                              and the weights as values
     * 
     * @return                      A semantic vector for the query, in the form 
     *                              of a {@ link java.util.HashMap} object, whith 
     *                              the concepts as the set of keys and 
     *                              {@link seks.basic.pojos.SemanticWeight} 
     *                              objects as values
     * 
     * @see seks.basic.pojos.SemanticWeight
     * @see java.util.HashMap
     */
    public HashMap<String, SemanticWeight> createQuerySemanticVector(HashMap<String, Double> conceptsAndWeights) ;
    
    /**
     * Retrieves all keywords from ontology.
     * 
     * @return  All ontology keywords, in the form of an {@link java.util.ArrayList}
     *          object
     */
    public ArrayList<String> getKeywords() ;
}
