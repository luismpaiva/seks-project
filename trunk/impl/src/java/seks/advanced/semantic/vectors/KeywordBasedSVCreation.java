/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Paulo Figueiras
 */
public interface KeywordBasedSVCreation {
    public HashMap<String, Double> getStatisticalVectorByDocumentURI(String documentURI) ;
    
    public HashMap<String, ArrayList<String>> getConceptsFromKeywords(HashMap<String, Double> statVector) ;
    
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, Double> statVector, HashMap<String, ArrayList<String>> conceptsAndKeywords) ;
    
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) ;
    
    public void createKeywordBasedSemanticVector (String documentURI, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) ;
}
