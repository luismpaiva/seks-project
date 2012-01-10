package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Implementation class for interface QueryTreatment
 * 
 * @author Paulo Figueiras
 */
public class QueryTreatmentImpl implements QueryTreatment {
    
    /**
     * Class constructor.
     */
    public QueryTreatmentImpl() {}
    
    /**
     * Prepares and manages query strings into separate keywords
     * 
     * @param query A query string
     * 
     * @return      An {@link java.util.ArrayList} object with keywords
     * 
     * @see java.util.ArrayList
     */
    @Override
    public ArrayList<String> getQueryKeywords(String query) {
        ArrayList<String> keywords = new ArrayList<String>() ;
        String keyword = "";
        if (query.contains(", ")) {
            int lastIndex = query.lastIndexOf(", ") ;

            int index = query.indexOf(", ") ;
            while (index < lastIndex) {
               keyword = query.substring(0, index) ;
               query = query.substring(index + 2) ;
               index = query.indexOf(", ") ;
               lastIndex = query.lastIndexOf(", ") ;
               keywords.add(keyword) ;
            }
            keyword = query.substring(0, lastIndex) ;
            keywords.add(keyword) ;
            keyword = query.substring(lastIndex + 2) ;
            keywords.add(keyword) ;
        }
        else{
            keywords.add(query);
        }
        return keywords ;
    }
    
    /**
     * Mimics the creation of statistic vectors through data-mining techniques, 
     * to generate a statistical vector for the query. The query is then 
     * considered a pseudo-document.
     * 
     * @param keywords  The query's keywords
     * 
     * @return          A statistic vector for the query, in the form of a 
     *                  {@link java.util.HashMap} object, whith the keywords as 
     *                  the set of keys and the weights for the keywords as 
     *                  values
     * 
     * @see java.util.HashMap
     */
    @Override
    public HashMap<String, Double> createQueryStatisticVector(ArrayList<String> keywords) {
        HashMap<String, Double> queryStatVector = new HashMap<String, Double>() ;
        Iterator iter = keywords.iterator() ;
        int keywordsNumber = keywords.size() ;
        double keywordWeight = 1/(double) keywordsNumber ;
        
        while (iter.hasNext()) {
            queryStatVector.put((String) iter.next(), keywordWeight) ;
        }
        return queryStatVector ;
    }
}
