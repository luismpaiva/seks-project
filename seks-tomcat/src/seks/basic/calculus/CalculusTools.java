package seks.basic.calculus;

/**
 * Comprises mathematical functions and algorithms needed for the creation 
 * and comparison of semantic vectors
 * 
 * @author Paulo Figueiras
 */
public interface CalculusTools {
    
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
    public double tfIdfAlgorithm(int docNum, int docNumByConcept, double conceptWeight, double absConceptWeight) ;
    
    /**
     * Normalizes a weight to values between 0 (zero) and 1 (one), regarding the 
     * sum of the weights
     * 
     * @param weightsTotal  The total sum of the weight values
     * @param weight        The value to be normalized
     * 
     * @return              The normalized value
     */
    public double normalization(double weightsTotal, double weight) ;
    
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
    public int euclidianDistanceAlgorithm(double sharedWeightsSum, double vector1SquaredWeightsSum, double vector2SquaredWeightsSum) ;
    
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
    public double homologousFactorAlgorithm(double alpha, double beta, int depthA, int depthB, int sonsA, int sonsB) ;
    
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
    public double nonHomologousFactorAlgorithm(double alpha, double beta, int depthA, int depthB, int depthR, int sonsA, int sonsB, int sonsR) ;
}
