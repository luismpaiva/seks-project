/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import seks.advanced.queries.QueryTreatment;
import seks.advanced.queries.QueryTreatmentImpl;
import seks.advanced.semantic.vectors.KeywordBasedSVCreation;
import seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl;
import seks.basic.pojos.SemanticWeight;
import seks.basic.serialization.SerializationTools;
import seks.basic.serialization.SerializationToolsImpl;

/**
 *
 * @author Paulo Figueiras
 */
@WebService(serviceName = "querySemanticVectorsService")
public class querySemanticVectorsService {
    
    /**
     * Retrieves all keywords from ontology.
     * 
     * @return  All ontology keywords, in the form of an {@link java.util.ArrayList}
     *          object
     */
    @WebMethod(operationName = "getKeywords")
    public ArrayList<String> getKeywords() {
        QueryTreatment qt = new QueryTreatmentImpl() ;
        return qt.getKeywords() ;
    }
    
    /**
     * Prepares and manages query strings into separate keywords
     * 
     * @param query A query string
     * 
     * @return      An {@link java.util.ArrayList} object with keywords
     * 
     * @see seks.advanced.queries.QueryTreatment
     * @see seks.advanced.queries.QueryTreatment#getQueryKeywords(java.lang.String) 
     */
    @WebMethod(operationName = "getQueryKeywords")
    public ArrayList<String> getQueryKeywords(@WebParam(name = "query") String query) {
        QueryTreatment qt = new QueryTreatmentImpl() ;
        return qt.getQueryKeywords(query) ;
    }

    /**
     * Mimics the creation of statistic vectors through data-mining techniques, 
     * to generate a statistical vector for the query. The query is then 
     * considered a pseudo-document.
     * 
     * @param queryKeywords The query's keywords
     * 
     * @return              A statistic vector for the query, in the form of a 
     *                      {@link java.util.HashMap} object, whith the keywords as 
     *                      the set of keys and the weights for the keywords as 
     *                      values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.queries.QueryTreatment
     * @see seks.advanced.queries.QueryTreatment#createQueryStatisticVector(java.util.ArrayList) 
     */
    @WebMethod(operationName = "createQueryStatisticVector")
    public String createQueryStatisticVector(@WebParam(name = "queryKeywords") ArrayList<String> queryKeywords) {
        QueryTreatment qt = new QueryTreatmentImpl() ;
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, Double> map = qt.createQueryStatisticVector(queryKeywords) ;
        return st.Serialize(map) ;
    }
    
    /**
     * Searches the ontology and retrieves ontological concepts that have, as 
     * value for the ontological property "has_Keyword", the keywords that 
     * define a document's statistic vector.
     * 
     * @param statisticVector   The document's statistic vector in the form of the
     *                          {@link HashMap} object given as input 
     *                          parameter, serialized as a {@link java.lang.String} object
     * 
     * @return                  A {@link HashMap} object, with the 
     *                          ontological concepts as the set of keys and
     *                          {@link ArrayList} objects with the 
     *                          concepts' matched keywords as values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     */
    @WebMethod(operationName = "getConceptsFromKeywords")
    public String getConceptsFromKeywords(@WebParam(name = "statisticVector") String statisticVector) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, Double> statVector = st.Deserialize(statisticVector) ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        return st.Serialize(svCreator.getConceptsFromKeywords(statVector)) ;
    }
    
    /**
     * Calculates the total weight (relevance) assigned to each ontological 
     * concept, through the sum of the weights of the document's keywords 
     * matched to each concept.
     * 
     * @param statisticVector       The document's statistic vector in the form 
     *                              of the {@link HashMap} object given as input
     *                              parameter, serialized as a {@link java.lang.String} object
     * 
     * @param conceptsAndKeywords   A {@link HashMap} object, with the 
     *                              ontological concepts as the set of keys and
     *                              {@link ArrayList} objects with the concepts' 
     *                              matched keywords as values, serialized as a {@link java.lang.String} object
     * 
     * @return                      The semantic vector's first implementation, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap)     
     */
    @WebMethod(operationName = "getConceptsTotalWeights")
    public String getConceptsTotalWeights(@WebParam(name = "statisticVector") String statisticVector, @WebParam(name = "conceptsAndKeywords") String conceptsAndKeywords) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, Double> statVector = st.Deserialize(statisticVector) ;
        HashMap<String, ArrayList<String>> conceptsAndKeywordsMap = st.Deserialize(conceptsAndKeywords) ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        return st.Serialize(svCreator.getConceptsTotalWeights(statVector, conceptsAndKeywordsMap)) ;
    }

    /**
     * Mimics the creation of semantic vectors through the application of the 
     * tf-idf algorithm, to generate a semantic vector for the query. The query 
     * is considered a pseudo-document.
     * 
     * @param conceptsAndWeights    The query's concepts and respective weights, 
     *                              in the form of a {@ link java.util.HashMap} 
     *                              object, with the concepts as a set of keys
     *                              and the weights as values, serialized as a {@link java.lang.String} object
     * 
     * @return                      A semantic vector for the query, in the form 
     *                              of a {@ link java.util.HashMap} object, whith 
     *                              the concepts as the set of keys and 
     *                              {@link seks.basic.pojos.SemanticWeight} 
     *                              objects as values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.queries.QueryTreatment
     * @see seks.advanced.queries.QueryTreatment#createQuerySemanticVector(java.util.HashMap) 
     */
    @WebMethod(operationName = "createQuerySemanticVector")
    public String createQuerySemanticVector(@WebParam(name = "conceptsAndWeights") String conceptsAndWeights) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, Double> conceptsMap = st.Deserialize(conceptsAndWeights) ;
        QueryTreatment qt = new QueryTreatmentImpl() ;
        return st.Serialize(qt.createQuerySemanticVector(conceptsMap)) ;
    }
}
