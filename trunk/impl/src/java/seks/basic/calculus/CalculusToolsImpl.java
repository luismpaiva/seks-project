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
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, Double conceptWeight, Double absConceptWeight) {
        double wdx = conceptWeight ;
        double maxywdy = absConceptWeight ;
        double dx = 0 ;
       
        dx = (wdx/maxywdy)*Math.log(docNum/docNumByConcept) ;
        return dx ;
    }
    
    
}
