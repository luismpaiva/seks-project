/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.calculus;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Paulo Figueiras
 */
public class CalculusToolsImpl implements CalculusTools {
    
    public CalculusToolsImpl() {}
    
    @Override
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, ArrayList<Double> conceptWeights, ArrayList<Double> absConceptWeights) {
        double wdx = 0 ;
        double maxywdy = 0 ;
        double dx = 0 ;
        Iterator iter = conceptWeights.iterator() ;
        
        while (iter.hasNext()) {
            wdx += (Double) iter.next() ;
        }
        iter = absConceptWeights.iterator() ;
        while (iter.hasNext()) {
            maxywdy += (Double) iter.next() ;
        }
        dx = (wdx/maxywdy)*Math.log(docNum/docNumByConcept) ;
        return dx ;
    }
    
    
}
