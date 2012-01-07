/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.calculus;

/**
 *
 * @author Paulo Figueiras
 */
public interface CalculusTools {
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, double conceptWeight, double absConceptWeight) ;
    
    public double normalization(double weightsTotal, double weight) ;
    
    public double euclidianDistanceAlgorithm(double sharedWeightsSum, double vector1SquaredWeightsSum, double vector2SquaredWeightsSum) ;
}
