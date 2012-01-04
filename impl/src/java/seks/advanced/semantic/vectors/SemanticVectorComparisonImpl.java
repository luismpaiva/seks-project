/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 *
 * @author Paulo Figueiras
 */
public class SemanticVectorComparisonImpl implements SemanticVectorComparison {
    
    public SemanticVectorComparisonImpl () {}
    
    public ArrayList<String> getSharedConcepts(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2) {
        ArrayList<String> sharedConcepts = new ArrayList<String>() ;
        
        Iterator iter1 = semanticVector1.keySet().iterator() ;
        Iterator iter2 = semanticVector2.keySet().iterator() ;
        
        while (iter1.hasNext()) {
            String concept1 = (String) iter1.next() ;
            while (iter2.hasNext()) {
                String concept2 = (String) iter2.next() ;
                if (concept1.equals(concept2)) {
                    sharedConcepts.add(concept2) ;
                    break ;
                }
            }
        }
        
        return sharedConcepts ;
    }
    
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) {
        double sharedWeightsSum = 0.0 ;
        double vector1WeightsSum = 0.0 ;
        double vector2WeightsSum = 0.0 ;
        DocumentResult docResult = new DocumentResult();
        CalculusTools ct = new CalculusToolsImpl() ;
        String documentURI = semanticVector1.get(semanticVector1.keySet().iterator().next()).getIdDocument() ;
        
        if (sharedConcepts.isEmpty()) {
            docResult.setIdDocument(documentURI) ;
            docResult.setRelevancePercentage(0.0) ;
        }
        else {
            Iterator iter = sharedConcepts.iterator() ;
            while (iter.hasNext()) {
                String concept = (String) iter.next() ;
                double weight1 = semanticVector1.get(concept).getWeight() ;
                double weight2 = semanticVector2.get(concept).getWeight() ;
                
                sharedWeightsSum += weight1*weight2 ;
            }
            
            iter = semanticVector1.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector1.get((String) iter.next()).getWeight() ;
                vector1WeightsSum += weight*weight ;
            }
            
            iter = semanticVector2.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector2.get((String) iter.next()).getWeight() ;
                vector2WeightsSum += weight*weight ;
            }
            
            double result = Math.abs(ct.euclidianDistanceAlgorithm(sharedWeightsSum, vector1WeightsSum, vector2WeightsSum)) ;
            docResult.setIdDocument(documentURI) ;
            docResult.setRelevancePercentage(result*100) ;
        }
        return docResult ;
    }
}
