/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.calculus;

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
    
    @Override
    public double euclidianDistanceAlgorithm(double sharedWeightsSum, double vector1SquaredWeightsSum, double vector2SquaredWeightsSum) {
        
        double result = sharedWeightsSum/(Math.sqrt(vector1SquaredWeightsSum*vector2SquaredWeightsSum)) ;
        
        return result ;
    }
}
