package seks.basic.calculus;

/**
 * Implementation class for interface CalculusTools
 * 
 * @author Paulo Figueiras
 */
public class CalculusToolsImpl implements CalculusTools {
    
    /**
     * Class constructor.
     */
    public CalculusToolsImpl() {}
    
    /**
     * Calculates an approximation to the TF-IDF algorithm, in order to get the 
     * weight, or relevance, of an ontological concept in a specific document, 
     * regarding not only its significance in the present document, but also in 
     * its overall relevance in the entire document corpus.
     * 
     * @param docNum            The total number of documents  stored in the 
     *                          system's document repository
     * @param docNumByConcept   The total number of documents in which the 
     *                          concept has some relevance
     * @param conceptWeight     The current concept's statistical weight on the 
     *                          present document
     * @param absConceptWeight  The statistical weight for the most relevant 
     *                          concept of the present document
     * 
     * @return                  The result of the application of the TF-IDF 
     *                          Algorithm
     */
    @Override
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, double conceptWeight, double absConceptWeight) {
        double wdx = conceptWeight ;
        double maxywdy = absConceptWeight ;
        double dx = 0 ;
       
        dx = (wdx/maxywdy)*Math.log(((double)docNum)/((double)docNumByConcept)) ;
        return dx ;
    }
    
    /**
     * Normalizes a weight to values between 0 (zero) and 1 (one), regarding the 
     * sum of the weights
     * 
     * @param weightsTotal  The total sum of the weight values
     * @param weight        The value to be normalized
     * 
     * @return              The normalized value
     */
    @Override
    public double normalization(double weightsTotal, double weight) {
        double normalizedWeight = weight/weightsTotal ;
        return normalizedWeight ;
    }
    
    /**
     * Compares two documents by calculating the angle between their two 
     * semantic vectors, through the application of the Cosine, or Euclidian 
     * Distance, Algorithm.
     * 
     * @param sharedWeightsSum          The sum of weights from concepts shared 
     *                                  between the two vertors
     * @param vector1SquaredWeightsSum  The sum of all weights from the first 
     *                                  vector
     * @param vector2SquaredWeightsSum  The sum of all weights from the second 
     *                                  vector
     * 
     * @return                          The resulting Euclidian Distance or 
     *                                  angle, defining the likeliness between 
     *                                  the two vectors
     */
    @Override
    public int euclidianDistanceAlgorithm(double sharedWeightsSum, double vector1SquaredWeightsSum, double vector2SquaredWeightsSum) {
        int resultInt = 0 ;
        double result = Math.abs(sharedWeightsSum/(Math.sqrt(vector1SquaredWeightsSum*vector2SquaredWeightsSum)))*100 ;
        double pattern = Math.floor(result) ;
        
        if ((result - pattern) < 0.5) {
            resultInt = (int) Math.floor(result) ;
        }
        else {
            resultInt = (int) Math.ceil(result) ;
        }
        return resultInt ;
    }
    
    /**
     * Calculates the similarity factor between two homologous concepts with depths given, respectively, by
     * <code>depthA</code> and <code>depthB</code>, and total numbers of sub-classes, respectively, by
     * <code>sonsA</code> and <code>sonsB</code>.
     * 
     * @param alpha     Adjusts the weight of the ancestor concept (<code>depthA</code>)
     * 
     * @param beta      Adjusts the weight of the distance between the concepts (<code>dAB</code>)
     * 
     * @param depthA    Taxonomical depth of the ancestor concept
     * 
     * @param depthB    Taxonomical depth of the sibling concept
     * 
     * @param sonsA     Total number of sub-concepts of the ancestor concept
     * 
     * @param sonsB     Total number of sub-concepts of the sibling concept
     * 
     * @return          The value of the taxonomical similarity factor between the homologous concepts
     */
    @Override
    public double homologousFactorAlgorithm(double alpha, double beta, int depthA, int depthB, int sonsA, int sonsB) {
        double result = 0.0 ;
        /*
         A - Ancestor node
         B - Sibling node
         */
        double dAB = (double)depthB - (double)depthA ;
        result = (1-(alpha/((double)depthA + 1)))*(beta/dAB)*((double)sonsB/(double)sonsA) ;
        return result ;
    }
    
    /**
     * Calculates the similarity factor between two non-homologous concepts with depths given, respectively, by
     * <code>depthA</code> and <code>depthB</code>, and total numbers of sub-classes, respectively, by
     * <code>sonsA</code> and <code>sonsB</code>.
     * 
     * @param alpha     Adjusts the weight of the ancestor concept (<code>depthR</code>)
     * 
     * @param beta      Adjusts the weight of the distance between the concepts (<code>dAB</code>)
     * 
     * @param depthA    Taxonomical depth of the first concept
     * @param depthB    Taxonomical depth of the second concept
     * @param depthR    Taxonomical depth of the ancestor concept
     * @param sonsA     Total number of sub-concepts of the first concept
     * @param sonsB     Total number of sub-concepts of the second concept
     * @param sonsR     Total number of sub-concepts of the ancestor concept
     * @return          The value of the taxonomical similarity factor between the non-homologous concepts
     */
    @Override
    public double nonHomologousFactorAlgorithm(double alpha, double beta, int depthA, int depthB, int depthR, int sonsA, int sonsB, int sonsR) {
        double result = 0.0 ;
        // In the paper the two values below are inverse-calculated, which means that they would be negative, so I changed the parameters of both subtractions
        double dRA = (double) depthA - (double) depthR ;
        double dRB = (double) depthB - (double) depthR ;
        double dAB = dRA + dRB ;
        
        result = (1-(alpha/((double)depthR + 1)))*(beta/dAB)*(((double)sonsA + (double)sonsB)/sonsR) ;
        
        return result ;
    }
}
