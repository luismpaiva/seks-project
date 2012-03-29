package seks.advanced.semantic.vectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.database.DatabaseInteraction;
import seks.basic.database.DatabaseInteractionImpl;
import seks.basic.pojos.SemanticWeight;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.ontology.OntologyPersistenceImpl;

/**
 * Implementation class of interface {@link KeywordBasedSVCreation}
 * 
 * @author Paulo Figueiras
 * 
 * @see KeywordBasedSVCreation
 */
@SuppressWarnings("unused")
public class KeywordBasedSVCreationImpl implements KeywordBasedSVCreation {
    
    /**
     * Class constructor
     */
    public KeywordBasedSVCreationImpl() {}
    
    /**
     * Retrieves all statistical weights from database belonging to the document
     * that has the unique URI given by the input parameter.
     * 
     * @param documentURI   The unique URI for the document
     * 
     * @return              The document's statistical vector as a {@link java.util.HashMap} 
     *                      object, comprising a set of keywords as keys and 
     *                      their respective weights as values
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     * @see java.util.HashMap
     */
    @Override
    public HashMap<String, Double> getStatisticalVectorByDocumentID(int documentID) {
    	try {
    		DatabaseInteraction di = new DatabaseInteractionImpl() ;
            Connection con = di.openConnection("svdbConfig.xml") ;
            ResultSet rs = di.callProcedure(con, "svdb.getStatisticWeightsWithDocID(" + documentID + ")") ;
            HashMap<String, Double> statVector = new HashMap<String, Double>() ;
            
            while (rs.next()) {
                statVector.put(rs.getString("keyword"), rs.getDouble("weight")) ;
            }
            rs.close() ;
            di.closeConnection(con) ;
            return statVector ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    /**
     * Searches the ontology and retrieves ontological concepts that have, as 
     * value for the ontological property "has_Keyword", the keywords that 
     * define a document's statistic vector.
     * 
     * @param statVector    The document's statistic vector in the form of the
     *                      {@link HashMap} object given as input 
     *                      parameter.
     * 
     * @return              A {@link HashMap} object, with the 
     *                      ontological concepts as the set of keys and
     *                      {@link ArrayList} objects with the 
     *                      concepts' matched keywords as values
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.ontology.OntologyInteraction
     * @see seks.basic.ontology.OntologyInteraction#getSubjectsFromTriple(java.lang.String, java.lang.String) 
     * @see HashMap
     * @see ArrayList
     */
	@Override
	public HashMap<String, HashMap<String, Double>> getConceptsAndWeightsFromKeywords(HashMap<String, Double> statVector) {
		OntologyInteraction oi = new OntologyInteractionImpl() ;
		Iterator<String> iter = statVector.keySet().iterator() ;
		HashMap<String, HashMap<String, Double>> conceptsKeywordsAndWeights = new HashMap<String, HashMap<String, Double>>() ;
		ArrayList<String> usedKeywords = new ArrayList<String>() ;
		ArrayList<String> ontoKeywords = oi.getAllValuesFromProperty("has_Keyword") ;
		double threshold = 0.01 ;
		
		while (iter.hasNext()) {
			Iterator<String> ontoIter = ontoKeywords.iterator() ;
			String keyword = iter.next() ;
			double weight = statVector.get(keyword) ;
			
			if (weight >= threshold) {
				if (keyword.contains("_") || keyword.contains(" ")) {
					ArrayList<String> words = this.splitKeyword(keyword) ;
					while (ontoIter.hasNext()) {
						String ontoKeyword = ontoIter.next() ;
						if (ontoKeyword.contains(" ")) {
							ArrayList<String> ontoWords = this.splitKeyword(ontoKeyword) ;
							int counter = 0 ;
							if (ontoWords.size() == words.size()) {
								for (int i = 0; i < words.size(); i++) {
									if ((ontoWords.get(i).startsWith(words.get(i))) || (ontoWords.get(i).equals(words.get(i)))) {
										counter++ ;
									}	
								}
								if ((counter == words.size()) && (!usedKeywords.contains(ontoKeyword))) {
									usedKeywords.add(ontoKeyword) ;
									conceptsKeywordsAndWeights = this.getConceptsKeywordsAndWeights(conceptsKeywordsAndWeights, ontoKeyword, weight, oi) ;
								}
							}
						}
					}
				}
				else {
					while (ontoIter.hasNext()) {
						String ontoKeyword = ontoIter.next() ;
						if ((!usedKeywords.contains(ontoKeyword)) && (!ontoKeyword.contains(" "))) {
							if (ontoKeyword.equals(keyword) || ontoKeyword.startsWith(keyword)) {
								usedKeywords.add(ontoKeyword) ;
								conceptsKeywordsAndWeights = this.getConceptsKeywordsAndWeights(conceptsKeywordsAndWeights, ontoKeyword, weight, oi) ;
							}
						}
					}
				}
			}
		}
		return conceptsKeywordsAndWeights ;
	}
    
    /**
     * Calculates the total weight (relevance) assigned to each ontological
     * concept, through the sum of the weights of the document's keywords 
     * matched to each concept.
     * 
     * @param statVector            The document's statistic vector in the form 
     *                              of the {@link HashMap} object given as input
     *                              parameter.
     * 
     * @param conceptsAndKeywords   A {@link HashMap} object, with the 
     *                              ontological concepts as the set of keys and
     *                              {@link ArrayList} objects with the concepts' 
     *                              matched keywords as values
     * 
     * @return                      The semantic vector's first implementation, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, HashMap<String, Double>> conceptsKeywordsAndWeights) {
        Set<String> concepts = conceptsKeywordsAndWeights.keySet() ;
        Iterator<String> iter = concepts.iterator() ;
        HashMap<String, Double> keywordsAndWeights = new HashMap<String, Double>() ;
        HashMap<String, Double> conceptsAndWeights = new HashMap<String, Double>() ;
        double totalWeight = 0.0 ;
        while (iter.hasNext()) {
        	String concept = iter.next() ;
        	keywordsAndWeights = conceptsKeywordsAndWeights.get(concept) ;
        	Iterator<String> keywordIter = keywordsAndWeights.keySet().iterator() ;
        	while (keywordIter.hasNext()) {
        		totalWeight += keywordsAndWeights.get(keywordIter.next()) ;
        	}
        	conceptsAndWeights.put(concept, totalWeight) ;
        	totalWeight = 0.0 ;
        }
        return conceptsAndWeights ;
    }
    
    /**
     * Creates a hierarchical array of concepts, sorting them by their relevance 
     * (weight) on a specific document, from the most relevant concept to the 
     * most insignificant concept to the document's knowledge representation.
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @return                      The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) {
        ArrayList<String> sortedConcepts = new ArrayList<String>() ;
        if (!conceptsAndWeights.isEmpty()) {
            Iterator<String> iter = conceptsAndWeights.keySet().iterator() ;

            while(iter.hasNext()) {
                String concept = iter.next() ;
                if (sortedConcepts.isEmpty()) {
                    sortedConcepts.add(concept) ;
                }
                else {
                    Double weight = conceptsAndWeights.get(concept) ;
                    for (int i = 0; i < sortedConcepts.size(); i++) {
                        String current = sortedConcepts.get(i) ;
                        Double currentWeight = conceptsAndWeights.get(current) ;
                        if (weight >= currentWeight) {
                            sortedConcepts.add(i, concept);
                            break ;
                        }
                        else if (weight < currentWeight && i == sortedConcepts.size() - 1) {
                            sortedConcepts.add(sortedConcepts.size(), concept);
                            break ;
                        }
                        else continue ;
                    }
                }
            }
        }
        return sortedConcepts ;
    }
    
    /**
     * Generates the document's keyword-based semantic vector, through the 
     * application of the {@link seks.basic.calculus.CalculusTools#tfIdfAlgorithm(int, int, double, double)}.
     * <p>
     * This generated semantic vector of a document is based not only on the 
     * most relevant concepts of that document, but also on the importance that 
     * those concepts have on all documents comprised by the database.
     * 
     * @param documentID           The unique ID for the document
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, in 
     *                              the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @param sortedConcepts        The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @return                      The document's semantic vector, in the form 
     *                              of a {@link HashMap} object, with the 
     *                              relevant concepts as the set of keys, and a 
     *                              {@link seks.basic.pojos.SemanticWeight} object with the 
     *                              concept's weight, ontological parent class, 
     *                              and other data
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.pojos.SemanticWeight
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#tfIdfAlgorithm(int, int, double, double)
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see seks.basic.ontology.OntologyInteraction
     * @see seks.basic.ontology.OntologyInteraction#getIndividualDirectParentClass(java.lang.String) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public HashMap<String, SemanticWeight> createKeywordBasedSemanticVector (int documentID, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) {
        DatabaseInteraction di = new DatabaseInteractionImpl() ;
        CalculusTools ct = new CalculusToolsImpl() ;
        
        HashMap<String, SemanticWeight> semanticVector = new HashMap<String, SemanticWeight>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        String mostRelevantConcept = sortedConcepts.get(0) ;
        Double maxWeight = conceptsAndWeights.get(mostRelevantConcept) ; 
        int totalDocNum = this.getTotalDocumentsNumber(con) ;
        if (!(documentID == 0))
        {
            totalDocNum++ ; // IMPORTANTE: para já tem que se somar o documento actual porque ele ainda não está na BD. Mais tarde terá de se tirar este 1 daqui
        }
        
        for (int i = 0; i < sortedConcepts.size(); i++) {
            
            String concept = sortedConcepts.get(i) ;
            Double weight = conceptsAndWeights.get(concept) ;
            Double result = 0.0 ;
            int docNumWithConcept = this.getDocumentsNumberWithConcept(concept, con) ; // We have to take into accounnt the current document (+1)
            if (!(documentID == 0)){
                docNumWithConcept++ ;
            }
            
            if (docNumWithConcept == 0 && (documentID == 0)) {
                result = 0.0 ;
            }
            else {
                result = ct.tfIdfAlgorithm(totalDocNum, docNumWithConcept, weight, maxWeight) ;
            }
            SemanticWeight sw = new SemanticWeight(documentID, concept, result) ;
            semanticVector.put(concept, sw) ;
        }
        di.closeConnection(con) ;
        return semanticVector;
    }
    
    /**
     * Normalizes the weights of a document's semantic vector to values between 
     * 0 (zero) and 1(one).
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @return                  The document's semantic vector with the weights 
     *                          normalized
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.pojos.SemanticWeight
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#normalization(double, double) 
     */
    @Override
    public HashMap<String, SemanticWeight> semanticVectorNormalization(HashMap<String, SemanticWeight> semanticVector) {
        Iterator<String> itr = semanticVector.keySet().iterator() ;
        HashMap<String, SemanticWeight> normalizedSemanticVector = new HashMap<String, SemanticWeight>() ;
        
        CalculusTools ct = new CalculusToolsImpl() ;
        double weightsTotal = 0 ;
        
        while(itr.hasNext()) {
            String concept = itr.next() ;
            SemanticWeight sw = semanticVector.get(concept) ;
            weightsTotal += sw.getWeight() ;
        }
        
        itr = semanticVector.keySet().iterator() ;
        
        while (itr.hasNext()) {
            String concept = itr.next() ;
            SemanticWeight sw = semanticVector.get(concept) ;
            double normalizedWeight = ct.normalization(weightsTotal, sw.getWeight()) ;
            sw.setWeight(normalizedWeight) ;
            normalizedSemanticVector.put(concept, sw) ;
        }
        
        return normalizedSemanticVector ;
    }
    
    /**
     * Creates the required instances of SemanticWeight instances on the database, with 
     * the document's URI as foreign key
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link seks.basic.pojos.SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#semanticVectorNormalization(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     */
    @Override
    public void storeKeywordBasedSemanticVector(HashMap<String, SemanticWeight> semanticVector) {
        DatabaseInteraction di = new DatabaseInteractionImpl() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        Iterator<String> iter = semanticVector.keySet().iterator() ;
        while (iter.hasNext()) {
            SemanticWeight sw = semanticVector.get(iter.next()) ;
            di.callProcedure(con, "svdb.insertKeywordBasedWeight('" + sw.getConcept() + "'," + sw.getWeight() + "," + sw.getIdDocument() + ")") ;
        }
        di.closeConnection(con) ;
    }
    
    
    /**
     * Retrieves the total number of documents contained within the database
     * 
     * @param con   A {@link java.sql.Connection} object, that manages the database 
     *              connection
     * 
     * @return      The total number of documents in the database
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
    private int getTotalDocumentsNumber(Connection con) {
    	 DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	try {
            ResultSet rs = di.callProcedure(con, "svdb.getTotalDocumentNum") ;
            rs.next() ;
            int result = rs.getInt("nDocument") ;
            rs.close() ;
            return result ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }
    
    
    /**
     * Retrieves the number of documents contained within the database which 
     * have the concept within their semantic vectors.
     * 
     * @param concept   The concept
     * 
     * @param con       A {@link java.sql.Connection} object, that manages the database 
     *                  connection
     * 
     * @return          The total number of documents in the database
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
    private int getDocumentsNumberWithConcept(String concept, Connection con) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	try {
            ResultSet rs = di.callProcedure(con, "svdb.getDocumentNumWithConcept('" + concept + "')") ;
            if (rs != null && rs.next()) {
	            int result = rs.getInt("nDocument") ;
	            rs.close() ;
	            return result ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }
    
    private HashMap<String, HashMap<String, Double>> getConceptsKeywordsAndWeights(HashMap<String, HashMap<String, Double>> conceptsKeywordsAndWeights, String ontoKeyword, double weight, OntologyInteraction oi) {
		
		ArrayList<String> concepts = oi.getSubjectsFromTriple(ontoKeyword, "has_Keyword") ;
		HashMap<String, Double> keywordsAndWeights = new HashMap<String, Double>() ;
		if (!concepts.isEmpty()) {
            for(int i = 0; i < concepts.size(); i++) {
            	if (conceptsKeywordsAndWeights.containsKey(concepts.get(i))) {
                    keywordsAndWeights = conceptsKeywordsAndWeights.get(concepts.get(i)) ;
                    if (!keywordsAndWeights.containsKey(ontoKeyword)) {
                    	keywordsAndWeights.put(ontoKeyword, weight);
                    	conceptsKeywordsAndWeights.put(oi.getDirectParentClass(concepts.get(i)), keywordsAndWeights) ;
                    }
                }
                else {
                    keywordsAndWeights = new HashMap<String, Double>() ;
                    keywordsAndWeights.put(ontoKeyword, weight);
                    conceptsKeywordsAndWeights.put(oi.getDirectParentClass(concepts.get(i)), keywordsAndWeights) ;
                }
            }
		}
		return conceptsKeywordsAndWeights ;
	}
	
	private ArrayList<String> splitKeyword(String keyword) {
		ArrayList<String> words = new ArrayList<String>() ;
		String separator = "" ;
		if (keyword.contains("_")) {
			separator = "_" ;
		}
		else if (keyword.contains(" ")) {
			separator = " " ;
		}
		if (!separator.equals("")) {
			int last = keyword.lastIndexOf(separator) ;
			int current = keyword.indexOf(separator) ;
			int counter = 0 ;
			words.add(counter, keyword.substring(0, current)) ;
			counter++ ;
			while (last > current) {
				keyword = keyword.substring(current + 1) ;
				current = keyword.indexOf(separator) ;
				last = keyword.lastIndexOf(separator) ;
				words.add(counter, keyword.substring(0, current - 1)) ;
				counter++ ;
			}
			if (last <= current) {
				words.add(counter, keyword.substring(last + 1)) ;
			}
		}
		return words ;
	}
}
