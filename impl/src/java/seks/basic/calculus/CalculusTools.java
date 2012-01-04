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
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, Double conceptWeight, Double absConceptWeight) ;
    
    public double euclidianDistanceAlgorithm(double sharedWeightsSum, double vector1SquaredWeightsSum, double vector2SquaredWeightsSum) ;
}
