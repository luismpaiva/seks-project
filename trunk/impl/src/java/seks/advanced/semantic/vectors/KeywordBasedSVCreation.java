package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;
import seks.basic.pojos.SemanticWeight;

/**
 * Comprises all functions and algorithms needed to create a keyword-based 
 * semantic vector, from the ontological match between keywords and concepts to 
 * the storage of the vector on the database
 * 
 * @author Paulo Figueiras
 */
public interface KeywordBasedSVCreation {
    
    
    /**
     * Retrieves all statistical weights from database belonging to the document
     * that has the unique URI given by the input parameter.
     * 
     * @param documentURI   The unique URI for the document
     * 
     * @return              The document's statistical vector as a {@link java.util.HashMap} 
     *                      object, comprising a set of keywords as keys and 
     *                      their respective weights as values
     * 
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see Connection
     * @see ResultSet
     * @see HashMap
     */
    public HashMap<String, Double> getStatisticalVectorByDocumentURI(String documentURI) ;
    
    
    /**
     * Searches the ontology and retrieves ontological concepts that have, as 
     * value for the ontological property "has_Keyword", the keywords that 
     * define a document's statistic vector.
     * 
     * @param statVector    The document's statistic vector in the form of the
     *                      {@link HashMap} object given as input 
     *                      parameter.
     * 
     * @return              A {@link HashMap} object, with the 
     *                      ontological concepts as the set of keys and
     *                      {@link ArrayList} objects with the 
     *                      concepts' matched keywords as values
     * 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see OntologyInteraction
     * @see OntologyInteraction#getSubjectsFromTriple(java.lang.String, java.lang.String) 
     * @see HashMap
     * @see ArrayList
     */
    public HashMap<String, ArrayList<String>> getConceptsFromKeywords(HashMap<String, Double> statVector) ;
    
    
    /**
     * Calculates the total weight (relevance) assigned to each ontological 
     * concept, through the sum of the weights of the document's keywords 
     * matched to each concept.
     * 
     * @param statVector            The document's statistic vector in the form 
     *                              of the {@link HashMap} object given as input
     *                              parameter.
     * 
     * @param conceptsAndKeywords   A {@link HashMap} object, with the 
     *                              ontological concepts as the set of keys and
     *                              {@link ArrayList} objects with the concepts' 
     *                              matched keywords as values
     * 
     * @return                      The semantic vector's first implementation, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @see #getConceptsFromKeywords(java.util.HashMap) 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, Double> statVector, HashMap<String, ArrayList<String>> conceptsAndKeywords) ;
    
    
    /**
     * Creates a hierarchical array of concepts, sorting them by their relevance 
     * (weight) on a specific document, from the most relevant concept to the 
     * most insignificant concept to the document's knowledge representation.
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @return                      The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @see #getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see #getConceptsFromKeywords(java.util.HashMap) 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) ;
    
    
    /**
     * Generates the document's keyword-based semantic vector, through the 
     * application of the {@link CalculusTools#tfIdfAlgorithm(int, int, double, double)}.
     * <p>
     * This generated semantic vector of a document is based not only on the 
     * most relevant concepts of that document, but also on the importance that 
     * those concepts have on all documents comprised by the database.
     * 
     * @param documentURI           The unique URI for the document
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, in 
     *                              the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @param sortedConcepts        The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @return                      The document's semantic vector, in the form 
     *                              of a {@link HashMap} object, with the 
     *                              relevant concepts as the set of keys, and a 
     *                              {@link SemanticWeight} object with the 
     *                              concept's weight, ontological parent class, 
     *                              and other data
     * 
     * @see #sortConceptsByRelevance(java.util.HashMap) 
     * @see #getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see #getConceptsFromKeywords(java.util.HashMap) 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see SemanticWeight
     * @see CalculusTools
     * @see CalculusTools#tfIdfAlgorithm(int, int, double, double)
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see OntologyInteraction
     * @see OntologyInteraction#getIndividualDirectParentClass(java.lang.String) 
     * @see Connection
     * @see ResultSet
     * @see HashMap
     * @see ArrayList
     */
    public HashMap<String, SemanticWeight> createKeywordBasedSemanticVector (String documentURI, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) ;
    
    
    /**
     * Normalizes the weights of a document's semantic vector to values between 
     * 0 (zero) and 1(one).
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @return                  The document's semantic vector with the weights 
     *                          normalized
     * 
     * @see #createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see #sortConceptsByRelevance(java.util.HashMap) 
     * @see #getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see #getConceptsFromKeywords(java.util.HashMap) 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see SemanticWeight
     * @see CalculusTools
     * @see CalculusTools#normalization(double, double) 
     */
    public HashMap<String, SemanticWeight> semanticVectorNormalization(HashMap<String, SemanticWeight> semanticVector) ;
    
    
    /**
     * Creates the required instances of SemanticWeight on the database, with 
     * the document's URI as foreign key
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @see #semanticVectorNormalization(java.util.HashMap) 
     * @see #createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see #sortConceptsByRelevance(java.util.HashMap) 
     * @see #getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see #getConceptsFromKeywords(java.util.HashMap) 
     * @see #getStatisticalVectorByDocumentURI(java.lang.String)
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     */
    public void storeSemanticVector(HashMap<String, SemanticWeight> semanticVector) ;
}
