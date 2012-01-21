/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.semantic.vectors;

import java.util.ArrayList;
import java.util.Iterator;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;

/**
 *
 * @author Paulo Figueiras
 */
public class TaxonomyBasedSVCreationImpl implements TaxonomyBasedSVCreation {
    
    public TaxonomyBasedSVCreationImpl() {}
    
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
     *                  <p>
     *                  If the concepts are equal, retrieves the {@link java.lang.String} object
     *                  "equal" -> Similarity = 1
     */
    @Override
    public String checkHomologyBetweenConcepts(String concept1, String concept2) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        int depth1 = oi.getDepth(concept1) ;
        int depth2 = oi.getDepth(concept2) ;
        ArrayList<String> concept1SuperClasses = oi.getAllParentClasses(concept1) ;
        ArrayList<String> concept2SuperClasses = oi.getAllParentClasses(concept2) ;
        Iterator iter1, iter2 ;
        
        if (depth1 < depth2) {
            if (concept2SuperClasses.contains(concept1)) {
                return "homologous:" + concept1 ;
            }
            else {
                iter1 = concept1SuperClasses.iterator() ;
                while (iter1.hasNext()) {
                    String class1 = (String) iter1.next() ;
                    iter2 = concept2SuperClasses.iterator() ;
                    while(iter2.hasNext()) {
                        String class2 = (String) iter2.next() ;
                        if (class1.equals(class2)) {
                            return "non-homologous:" + class2 ;
                        }
                    }
                }
                return "non-homologous:Concept" ;
            }
        }
        else if (depth1 > depth2) {
            if (concept1SuperClasses.contains(concept2)) {
                return "homologous:" + concept2 ;
            }
            else {
                iter2 = concept2SuperClasses.iterator() ;
                while (iter2.hasNext()) {
                    String class2 = (String) iter2.next() ;
                    iter1 = concept1SuperClasses.iterator() ;
                    while(iter1.hasNext()) {
                        String class1 = (String) iter1.next() ;
                        if (class2.equals(class1)) {
                            return "non-homologous:" + class1 ;
                        }
                    }
                }
                return "non-homologous:Concept" ;
            }
        }
        else if (depth1 == depth2 && !concept1.equals(concept2)) {
            String superClass = oi.getDirectParentClass(concept2) ;
            return "non-homologous:" + superClass ;
        }
        else return "equal" ;
    }
    
    /**
     * 
     * @param ancestorName
     * @param siblingName
     * @return 
     */
    @Override
    public double calculateHomologousSimilarityFactor(String ancestorName, String siblingName) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        CalculusTools ct = new CalculusToolsImpl() ;
        double alpha = 0.5, beta = 0.5 ;
        int depthA = oi.getDepth(ancestorName) ;
        int depthB = oi.getDepth(siblingName) ;
        int sonsA = oi.getSubClassesNumber(ancestorName) ;
        int sonsB = oi.getSubClassesNumber(siblingName) ;
        double result = ct.homologousFactorAlgorithm(alpha, beta, depthA, depthB, sonsA, sonsB) ;
        
        return result ;
    }
    
    /**
     * 
     * @param ancestorName
     * @param concept1
     * @param concept2
     * @return 
     */
    @Override
    public double calculateNonHomologousSimilarityFactor(String ancestorName, String concept1, String concept2) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        CalculusTools ct = new CalculusToolsImpl() ;
        double alpha = 0.5, beta = 0.5 ;
        int depthR = oi.getDepth(ancestorName) ;
        int depthA = oi.getDepth(concept1) ;
        int depthB = oi.getDepth(concept2) ;
        int sonsR = oi.getSubClassesNumber(ancestorName) ;
        int sonsA = oi.getSubClassesNumber(concept1) ;
        int sonsB = oi.getSubClassesNumber(concept2) ;
        double result = ct.nonHomologousFactorAlgorithm(alpha, beta, depthA, depthB, depthR, sonsA, sonsB, sonsR) ;
        
        return result ;
    }
}
