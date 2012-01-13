package seks.advanced.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import seks.advanced.semantic.vectors.KeywordBasedSVCreation;
import seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl;
import seks.basic.pojos.SemanticWeight;
import seks.basic.serialization.SerializationTools;
import seks.basic.serialization.SerializationToolsImpl;

/**
 *
 * @author Paulo Figueiras
 */
@WebService(serviceName = "documentSemanticVectorsService")
public class documentSemanticVectorsService {
    
    /**
     * Retrieves all statistical weights from database belonging to the document
     * that has the unique URI given by the input parameter.
     * 
     * @param documentId    The unique URI for the document
     * 
     * @return              The document's statistical vector as a {@link java.util.HashMap} 
     *                      object, comprising a set of keywords as keys and 
     *                      their respective weights as values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String) 
     */
    @WebMethod(operationName = "getStatisticalVectorByDocumentId")
    public String getStatisticalVectorByDocumentId(@WebParam(name = "documentId") String documentId) {
        SerializationTools st = new SerializationToolsImpl() ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        return st.Serialize(svCreator.getStatisticalVectorByDocumentURI(documentId)) ;
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
     * Creates a hierarchical array of concepts, sorting them by their relevance 
     * (weight) on a specific document, from the most relevant concept to the 
     * most insignificant concept to the document's knowledge representation.
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values, serialized as a {@link java.lang.String} object
     * 
     * @return                      The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     */
    @WebMethod(operationName = "sortConceptsByRelevance")
    public ArrayList<String> sortConceptsByRelevance(@WebParam(name = "conceptsAndWeights") String conceptsAndWeights) {
        SerializationTools st = new SerializationToolsImpl() ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        HashMap<String, Double> conceptsAndWeightsMap = st.Deserialize(conceptsAndWeights) ;
        return svCreator.sortConceptsByRelevance(conceptsAndWeightsMap) ;
    }

    /**
     * Generates the document's keyword-based semantic vector, through the 
     * application of the {@link seks.basic.calculus.CalculusTools#tfIdfAlgorithm(int, int, double, double)}.
     * <p>
     * This generated semantic vector of a document is based not only on the 
     * most relevant concepts of that document, but also on the importance that 
     * those concepts have on all documents comprised by the database.
     * 
     * @param documentId            The unique URI for the document
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, in 
     *                              the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values, serialized as a {@link java.lang.String} object
     * 
     * @param sortedConcepts        The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @return                      The document's semantic vector, in the form 
     *                              of a {@link HashMap} object, with the 
     *                              relevant concepts as the set of keys, and a 
     *                              {@link seks.basic.pojos.SemanticWeight} object with the 
     *                              concept's weight, ontological parent class, 
     *                              and other data, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#semanticVectorNormalization(java.util.HashMap)  
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     */
    @WebMethod(operationName = "createKeywordBasedSemanticVector")
    public String createKeywordBasedSemanticVector(@WebParam(name = "documentId") String documentId, @WebParam(name = "conceptsAndWeights") String conceptsAndWeights, @WebParam(name = "sortedConcepts") ArrayList<String> sortedConcepts) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, Double> conceptsAndWeightsMap = st.Deserialize(conceptsAndWeights) ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        return st.Serialize(svCreator.semanticVectorNormalization(svCreator.createKeywordBasedSemanticVector(documentId, conceptsAndWeightsMap, sortedConcepts))) ;
    }

    /**
     * Creates the required instances of SemanticWeight instances on the database, with 
     * the document's URI as foreign key
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link seks.basic.pojos.SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#storeSemanticVector(java.util.HashMap) 
     */
    @WebMethod(operationName = "storeSemanticVector")
    @Oneway
    public void storeSemanticVector(@WebParam(name = "semanticVector") String semanticVector) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, SemanticWeight> semVector = st.Deserialize(semanticVector) ;
        KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
        svCreator.storeSemanticVector(semVector) ;
    }
}
