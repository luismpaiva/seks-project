package seks.advanced.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.pojos.SemanticWeight;

/**
 * Implementation class for interface QueryTreatment
 * 
 * @author Paulo Figueiras
 */
public class QueryTreatmentImpl implements QueryTreatment {
    
    private OntologyInteractionImpl oii ;
    
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
    @Override
    public HashMap<String, SemanticWeight> createQuerySemanticVector(HashMap<String, Double> conceptsAndWeights) {
        HashMap<String, SemanticWeight> semanticVector = new HashMap<String, SemanticWeight>() ;
        OntologyInteraction oi = getOii() ;
        Iterator iter = conceptsAndWeights.keySet().iterator() ;
        
        while (iter.hasNext()) {
            String concept = (String) iter.next() ;
            double weight = conceptsAndWeights.get(concept) ;
            String parentClass = oi.getIndividualDirectParentClass(concept) ;
            SemanticWeight sw = new SemanticWeight("query", parentClass, concept, weight) ;
            semanticVector.put(concept, sw) ;
        }
        return semanticVector ;
    }
    
    /**
     * Retrieves all keywords from ontology.
     * 
     * @return  All ontology keywords, in the form of an {@link java.util.ArrayList}
     *          object
     */
    @Override
    public ArrayList<String> getKeywords() {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        String keyword = "" ;
        ArrayList<String> list = oi.getAllValuesFromProperty("has_Keyword") ;
        return list ;
    }

    /**
     * @return the oii
     */
    public OntologyInteractionImpl getOii() {
        return new OntologyInteractionImpl() ;
    }

    /**
     * @param oii the oii to set
     */
    public void setOii(OntologyInteractionImpl oii) {
        this.oii = oii;
    }
}
