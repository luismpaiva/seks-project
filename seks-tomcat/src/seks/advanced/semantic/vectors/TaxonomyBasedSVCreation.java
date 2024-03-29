/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.sql.Connection;
import java.util.HashMap;

import seks.basic.ontology.OntologyInteraction;
import seks.basic.pojos.SemanticWeight;

/**
 *
 * @author Paulo Figueiras
 */
public interface TaxonomyBasedSVCreation {
    
    /**
     * Checks if <code>concept1</code> and <code>concept2</code> input parameters are
     * homologous or non-homologous concepts.
     * <p>
     * Definition 1: In the hierarchical tree structure of the ontology, concept A and concept 
     * B are homology concepts if the node of concept A is the ancestor node of 
     * concept B. 
     * <p>
     * Definition 2: In the hierarchical tree structure of the ontology, concept 
     * A and concept B are non-homology concepts if concept A is neither the ancestor 
     * node nor the descendant node of concept B, even though they are related by kin.
     * 
     * @param concept1  The local name for the first ontology concept
     * 
     * @param concept2  The local name for the second ontology concept
     * 
     * @return          If the concepts are homologous, retrieves the concept with 
     *                  the smallest depth
     *                  <p>
     *                  If the concepts are non-homologous, retrieves the nearest parent class
     *                  shared by both concepts
     */
    public String checkHomologyBetweenConcepts(String concept1, String concept2, OntologyInteraction oi) ;
    
    /**
     * 
     * @param ancestorName
     * @param siblingName
     * @return 
     */
    public double calculateHomologousSimilarityFactor(String ancestorName, String siblingName, int occurrences, OntologyInteraction oi, Connection con) ;
    
    /**
     * 
     * @param ancestorName
     * @param concept1
     * @param concept2
     * @return 
     */
    public double calculateNonHomologousSimilarityFactor(String ancestorName, String concept1, String concept2, int occurrences, OntologyInteraction oi, Connection con) ;
    
    public HashMap<String, SemanticWeight> createTaxonomyBasedSemanticVector(HashMap<String, SemanticWeight> kbSemanticVector) ;
    
    public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> tbSemanticVector, double homologousThreshold, double nonHomologousThreshold, OntologyInteraction oi, Connection con) ;
    
    public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> kbSemanticVector, double homologousThreshold, double nonHomologousThreshold, OntologyInteraction oi, Connection con) ;
    
    public void storeTaxonomyBasedSemanticVector(HashMap<String, SemanticWeight> tbSemanticVector) ;
    
}
