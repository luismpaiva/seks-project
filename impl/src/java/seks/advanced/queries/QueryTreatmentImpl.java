/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Paulo Figueiras
 */
public class QueryTreatmentImpl implements QueryTreatment {
    
    public QueryTreatmentImpl() {}
    
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
