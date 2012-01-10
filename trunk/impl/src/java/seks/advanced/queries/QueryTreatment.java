package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;

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
     * @return      An {@link ArrayList} object with keywords
     * 
     * @see ArrayList
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
     *                  {@ link HashMap} object, whith the keywords as the set 
     *                  of keys and the weights for the keywords as values
     * 
     * @see HashMap
     */
    public HashMap<String, Double> createQueryStatisticVector(ArrayList<String> keywords) ;
}
