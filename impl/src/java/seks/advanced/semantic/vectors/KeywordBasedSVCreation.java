/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;
import seks.basic.pojos.SemanticWeight;

/**
 *
 * @author Paulo Figueiras
 */
public interface KeywordBasedSVCreation {
    public HashMap<String, Double> getStatisticalVectorByDocumentURI(String documentURI) ;
    
    public HashMap<String, ArrayList<String>> getConceptsFromKeywords(HashMap<String, Double> statVector) ;
    
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, Double> statVector, HashMap<String, ArrayList<String>> conceptsAndKeywords) ;
    
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) ;
    
    public HashMap<String, SemanticWeight> createKeywordBasedSemanticVector (String documentURI, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) ;
    
    public HashMap<String, SemanticWeight> semanticVectorNormalization(HashMap<String, SemanticWeight> semanticVector) ;
    
    public void storeSemanticVector(HashMap<String, SemanticWeight> semanticVector) ;
}
