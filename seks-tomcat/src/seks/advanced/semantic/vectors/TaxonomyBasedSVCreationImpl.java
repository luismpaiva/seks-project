/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.pojos.SemanticWeight;

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
    public String checkHomologyBetweenConcepts(String concept1, String concept2, OntologyInteraction oi) {
        int depth1 = oi.getDepth(concept1) ;
        int depth2 = oi.getDepth(concept2) ;
        ArrayList<String> concept1SuperClasses = oi.getAllParentClasses(concept1) ;
        ArrayList<String> concept2SuperClasses = oi.getAllParentClasses(concept2) ;
        Iterator<String> iter1, iter2 ;
        
        if (depth1 < depth2) {
            if (concept2SuperClasses.contains(concept1)) {
                return "homologous:" + concept1 ;
            }
            else {
                iter1 = concept1SuperClasses.iterator() ;
                while (iter1.hasNext()) {
                    String class1 = iter1.next() ;
                    iter2 = concept2SuperClasses.iterator() ;
                    while(iter2.hasNext()) {
                        String class2 = iter2.next() ;
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
                    String class2 = iter2.next() ;
                    iter1 = concept1SuperClasses.iterator() ;
                    while(iter1.hasNext()) {
                        String class1 = iter1.next() ;
                        if (class2.equals(class1)) {
                            return "non-homologous:" + class1 ;
                        }
                    }
                }
                return "non-homologous:Concept" ;
            }
        }
        else if (depth1 == depth2 && !concept1.equals(concept2)) {
        	String superClass1 = oi.getDirectParentClass(concept1) ;
        	String superClass2 = oi.getDirectParentClass(concept2) ;
            while (!superClass1.equals(superClass2)) {
            	superClass1 = oi.getDirectParentClass(superClass1) ;
            	superClass2 = oi.getDirectParentClass(superClass2) ;
            }
        	return "non-homologous:" + superClass1 ;
        }
        else return "equal" ;
    }
    
    /**
     * Calculates the taxonomic similarity factor between two homologous classes.
     * 
     * @param ancestorName	The local name for the ancestor {@link com.hp.hpl.jena.ontology.OntClass} object
     * @param siblingName	The local name for the ancestor {@link com.hp.hpl.jena.ontology.OntClass} object
     * 
     * @return				The similarity factor in decimal form 
     */
    @Override
    public double calculateHomologousSimilarityFactor(String ancestorName, String siblingName, int occurrences, OntologyInteraction oi, Connection con) {
        CalculusTools ct = new CalculusToolsImpl() ;
        double beta =  ((double)occurrences / (double)this.getMaxRelationOccurences(con)) ;
        double alpha =  (1.0 - beta) ;
        int depthA = oi.getDepth(ancestorName) ;
        int depthB = oi.getDepth(siblingName) ;
        int sonsA = oi.getSubClassesNumber(ancestorName) ;
        int sonsB = oi.getSubClassesNumber(siblingName) ;
        double result = ct.homologousFactorAlgorithm(alpha, beta, depthA, depthB, sonsA, sonsB) ;
        
        return result ;
    }
    
    /**
     * Calculates the taxonomic similarity factor between two non-homologous classes.
     * 
     * @param ancestorName	The local name for the {@link com.hp.hpl.jena.ontology.OntClass} 
     * 						object that is ancestor of the objects with local names given parameters <code>concept1</code> and <code>concept2</code>  
     * @param concept1		The local name for the first non-homologous {@link com.hp.hpl.jena.ontology.OntClass} object 
     * @param concept2		The local name for the second non-homologous {@link com.hp.hpl.jena.ontology.OntClass} object
     * 
     * @return				The similarity factor in decimal form
     */
    @Override
    public double calculateNonHomologousSimilarityFactor(String ancestorName, String concept1, String concept2, int occurrences, OntologyInteraction oi, Connection con) {
        CalculusTools ct = new CalculusToolsImpl() ;
        double beta = ((double)occurrences / (double)this.getMaxRelationOccurences(con)) ;
        double alpha =  (1.0 - beta) ;
        int depthR = oi.getDepth(ancestorName) ;
        int depthA = oi.getDepth(concept1) ;
        int depthB = oi.getDepth(concept2) ;
        int sonsR = oi.getSubClassesNumber(ancestorName) ;
        int sonsA = oi.getSubClassesNumber(concept1) ;
        int sonsB = oi.getSubClassesNumber(concept2) ;
        double result = ct.nonHomologousFactorAlgorithm(alpha, beta, depthA, depthB, depthR, sonsA, sonsB, sonsR) ;
        
        return result ;
    }
    
    /**
     * Generates the document's taxonomy-based semantic vector.
     * <p>
     * This generated semantic vector of a document is based on the taxonomic relations between ontologic concepts.
     * 
	 * @param kbSemanticVector	The generated keyword-based semantic vector
	 * 
	 * @return					The generated taxonomy-based semantic vector
     */
    
    public HashMap<String, SemanticWeight> createTaxonomyBasedSemanticVector(HashMap<String, SemanticWeight> kbSemanticVector) {
    	HashMap<String, SemanticWeight> tbSemanticVector = new HashMap<String, SemanticWeight>() ;
    	OntologyInteraction oi = new OntologyInteractionImpl() ;
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	double homologousThreshold = 0.05 ;
    	double nonHomologousThreshold = 0.07 ;
    	
    	//First Step: Increase relevance of existing concepts according to taxonomic relations
    	
    	tbSemanticVector = this.boostConceptsWeightsWithinVector(kbSemanticVector, homologousThreshold, nonHomologousThreshold, oi, con) ;
    	
    	//Second Step: Add new concepts that have a strong relation with existing concepts
    	
    	tbSemanticVector = this.addImportantConceptsToVector(tbSemanticVector, homologousThreshold, nonHomologousThreshold, oi, con) ;
    	di.closeConnection(con) ;
    	return tbSemanticVector ;
    }
    
    
    public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> tbSemanticVector, double homologousThreshold, double nonHomologousThreshold, OntologyInteraction oi, Connection con) {
    	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
    	ArrayList<String> classes = oi.getAllSubclasses("Concept") ;
    	Set<String> vector = tbSemanticVector.keySet() ;
    	Iterator<String> vectorIter = vector.iterator() ; 
    	double similarity = 0.0 ;
    	boolean areHomologous = false ;
    	int idDocument, occurrences = 0 ;
    	HashMap<String, SemanticWeight> newTbSemanticVector = new HashMap<String, SemanticWeight>() ;
    	String ancestor = "" ;
    	while (vectorIter.hasNext()) {
    		String currentConcept = vectorIter.next() ;
    		idDocument = tbSemanticVector.get(currentConcept).getIdDocument() ;
	    	Iterator<String> classesIter = classes.iterator() ;
	    	while (classesIter.hasNext()) {
	    		String concept = classesIter.next() ;
	    		
	    		if (!vector.contains(concept)) {
	    			String relation = this.checkHomologyBetweenConcepts(currentConcept, concept, oi) ;
	    			if (relation.startsWith("homologous:")) {
	    				areHomologous = true ;
	    				ancestor = relation.substring(relation.indexOf(":") + 1) ;
	    				if (concept.equals(ancestor)) {
	    					occurrences = this.getTaxonomyRelationOccurrences(concept, currentConcept, true, false, con) ;
	    					if (occurrences == 0) {
	    						similarity = 0.0 ;
	    					}
	    					else {
	    						similarity = calculateHomologousSimilarityFactor(concept, currentConcept, occurrences, oi, con) ;
	    					}
	    				}
	    				else if (currentConcept.equals(ancestor)) {
	    					occurrences = this.getTaxonomyRelationOccurrences(currentConcept, concept, true, false, con) ;
	    					if (occurrences == 0) {
	    						similarity = 0.0 ;
	    					}
	    					else {
	    						similarity = calculateHomologousSimilarityFactor(currentConcept, concept, occurrences, oi, con) ;
	    					}
	    				}
	    			}
	    			else if (relation.startsWith("non-homologous:")) {
	    				areHomologous = false ;
	    				if (relation.equals("non-homologous:Concept")) {
	    					/*occurrences = this.getTaxonomyRelationOccurrences(concept, currentConcept, false, false, con) ;
	    					if (occurrences == 0) {
	    						similarity = 0.0 ;
	    					}
	    					else {
	    						similarity = calculateNonHomologousSimilarityFactor("Concept", concept, currentConcept, occurrences, oi, con) ;
	    					}*/
	    					continue;
	    				}
	    				else {
	    					ancestor = relation.substring(relation.indexOf(":") + 1) ;
	    					occurrences = this.getTaxonomyRelationOccurrences(concept, currentConcept, false, false, con) ;
	    					if (occurrences == 0) {
	    						similarity = 0.0 ;
	    					}
	    					else {
	    						similarity = calculateNonHomologousSimilarityFactor(ancestor, concept, currentConcept, occurrences, oi, con) ;
	    					}
	    				}
	    				
	    			}
	    			
	    			if ((areHomologous && (similarity < homologousThreshold)) || ((!areHomologous) && (similarity < nonHomologousThreshold))) {
    					continue ;
    				}
    				else {
    					if (areHomologous && currentConcept.equals(ancestor)) {
    						occurrences = this.getTaxonomyRelationOccurrences(currentConcept, concept, areHomologous, true, con) ;
    					}
    					else {
    						occurrences = this.getTaxonomyRelationOccurrences(concept, currentConcept, areHomologous, true, con) ;
    					}
    					CalculusTools ct = new CalculusToolsImpl() ;
    			    	//int docNum = this.getTotalDocumentsNumber() ;
    					int docNum = 6 ;
    			    	//int docNumByConcept = this.getDocumentsNumberWithConcept(concept, con) + 1 ;
    					int docNumByConcept = occurrences ;
    					double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    					if (!newTbSemanticVector.containsKey(concept)) {
    						double weight = result + ( tbSemanticVector.get(currentConcept).getWeight()*similarity) ;
    						SemanticWeight sw = new SemanticWeight(idDocument, concept, weight) ;
    						newTbSemanticVector.put(concept, sw) ;
    					}
    					else {
    						SemanticWeight sw = newTbSemanticVector.get(concept) ;
    						double newWeight = result + (sw.getWeight() - result) + ( tbSemanticVector.get(currentConcept).getWeight()*similarity) ;
    						sw.setWeight(newWeight) ;
    						newTbSemanticVector.put(concept, sw) ;
    					}
    				}
	    		}
	    		else continue ;
	    	}
    	}
    	newTbSemanticVector = kbsvCreator.semanticVectorNormalization(newTbSemanticVector) ;
    	newTbSemanticVector.putAll(tbSemanticVector) ;
    	return kbsvCreator.semanticVectorNormalization(newTbSemanticVector) ;
    }
    
    
    public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> kbSemanticVector, double homologousThreshold, double nonHomologousThreshold, OntologyInteraction oi, Connection con) {
    	HashMap<String, SemanticWeight> tbSemanticVector = new HashMap<String, SemanticWeight>() ;
    	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
    	//First Step: Increase relevance of existing concepts according to taxonomic relations
    	Set<String> concepts = kbSemanticVector.keySet() ;
    	Iterator<String> conceptsIter = concepts.iterator() ;
    	double similarity = 0.0 ;
    	boolean areHomologous = false ;
    	int occurrences = 0 ;
    	ArrayList<String> nonRelationConcepts = new ArrayList<String>() ;
    	ArrayList<String> pastConcepts = new ArrayList<String>() ;
    	
    	while (conceptsIter.hasNext()) {
    		String thisConcept = conceptsIter.next() ;
    		Iterator<String> iter = concepts.iterator() ;
    		while (iter.hasNext()) {
    			String thatConcept = iter.next() ;
    			if ((!thisConcept.equals(thatConcept)) && (!pastConcepts.contains(thatConcept))) {
	    			String taxonomicRelation = checkHomologyBetweenConcepts(thisConcept, thatConcept, oi) ;
	    			if (taxonomicRelation.startsWith("homologous:")) {			// If the relation is homologous
	    				areHomologous = true ;
	    				String concept = taxonomicRelation.substring(taxonomicRelation.indexOf(":") + 1) ;
	    				if (thisConcept.equals(concept)) {
	    					occurrences = this.getTaxonomyRelationOccurrences(thisConcept, thatConcept, areHomologous, true, con) ;
	    					similarity = calculateHomologousSimilarityFactor(thisConcept, thatConcept, occurrences, oi, con) ;
	    				}
	    				else if (thatConcept.equals(concept)) {
	    					occurrences = this.getTaxonomyRelationOccurrences(thatConcept, thisConcept, areHomologous, true, con) ;
	    					similarity = calculateHomologousSimilarityFactor(thatConcept, thisConcept, occurrences, oi, con) ;
	    				}
	    				
	    				if (similarity >= homologousThreshold) {
		    				// Calculating Co-occurrence throughout the documents' universe
			    			
			    			CalculusTools ct = new CalculusToolsImpl() ;
			    			//int docNum = this.getTotalDocumentsNumber() ;
			    			int docNum = 6 ; 
			    			double cooccurrence = ct.tfIdfAlgorithm(docNum, occurrences, 1.0, 1.0) ;
			    			
			    			// Adding the similarity factor to concepts' weights
			    			
			    			SemanticWeight thisWeight = kbSemanticVector.get(thisConcept) ;
			    			SemanticWeight thatWeight = kbSemanticVector.get(thatConcept) ;
			    			
			    			double weight1 = thisWeight.getWeight() ;
			    			double weight2 = thatWeight.getWeight() ;
			    			thisWeight.setWeight(weight1 + (weight2*similarity + cooccurrence)) ;
			    			thatWeight.setWeight(weight2 + (weight1*similarity + cooccurrence)) ;	
			    			
			    			tbSemanticVector.put(thisConcept, thisWeight) ;
			    			tbSemanticVector.put(thatConcept, thatWeight) ;
	    				}
	    			}
	    			else if (taxonomicRelation.startsWith("non-homologous:")) {	// If the relation is non-homologous
	    				areHomologous = false ;
	    				if (taxonomicRelation.equals("non-homologous:Concept")) {
	    					/*occurrences = this.getTaxonomyRelationOccurrences(thisConcept, thatConcept, areHomologous, true, con) ;
	    					similarity = calculateNonHomologousSimilarityFactor("Concept", thisConcept, thatConcept, occurrences, oi, con) ;*/
	    					continue;
	    				}
	    				else {
	    					String concept = taxonomicRelation.substring(taxonomicRelation.indexOf(":") + 1) ;
	    					occurrences = this.getTaxonomyRelationOccurrences(thisConcept, thatConcept, areHomologous, true, con) ;
	    					similarity = calculateNonHomologousSimilarityFactor(concept, thisConcept, thatConcept, occurrences, oi, con) ;
	    					
	    					if (similarity >= nonHomologousThreshold) {
		    					// Calculating Co-occurrence throughout the documents' universe
		    	    			
		    	    			CalculusTools ct = new CalculusToolsImpl() ;
		    	    			//int docNum = this.getTotalDocumentsNumber() ;
		    	    			int docNum = 6 ; 
		    	    			double cooccurrence = ct.tfIdfAlgorithm(docNum, occurrences, 1.0, 1.0) ;
		    	    			
		    	    			// Adding the similarity factor to concepts' weights
		    	    			
		    	    			SemanticWeight thisWeight = kbSemanticVector.get(thisConcept) ;
		    	    			SemanticWeight thatWeight = kbSemanticVector.get(thatConcept) ;
		    	    			
		    	    			double weight1 = thisWeight.getWeight() ;
		    	    			double weight2 = thatWeight.getWeight() ;
		    	    			thisWeight.setWeight(weight1 + (weight2*similarity + cooccurrence)) ;
		    	    			thatWeight.setWeight(weight2 + (weight1*similarity + cooccurrence)) ;	
		    	    			
		    	    			tbSemanticVector.put(thisConcept, thisWeight) ;
		    	    			tbSemanticVector.put(thatConcept, thatWeight) ;
	    					}
	    				}
	    			}
    			}
    		}
    		pastConcepts.add(thisConcept) ;
    		if (!tbSemanticVector.containsKey(thisConcept)) {
    			nonRelationConcepts.add(thisConcept) ;
    			//tbSemanticVector.put(thisConcept, kbSemanticVector.get(thisConcept)) ;
    		}
    	}
    	HashMap<String, SemanticWeight> newVector = kbsvCreator.semanticVectorNormalization(tbSemanticVector) ;
    	if (!nonRelationConcepts.isEmpty()) {
	    	for (int i = 0; i < nonRelationConcepts.size(); i++) {
	    		newVector.put(nonRelationConcepts.get(i), kbSemanticVector.get(nonRelationConcepts.get(i))) ;
	    	}
    	}
    	tbSemanticVector = kbsvCreator.semanticVectorNormalization(newVector) ;
    	return tbSemanticVector ;
    }
    
    
    public void storeTaxonomyBasedSemanticVector(HashMap<String, SemanticWeight> tbSemanticVector) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	Iterator<String> iter = tbSemanticVector.keySet().iterator() ;
    	while (iter.hasNext()) {
    		String concept = iter.next() ;
    		SemanticWeight weight = tbSemanticVector.get(concept) ;
    		di.callProcedure(con, "insertTaxonomyBasedWeight('" + weight.getConcept() + "'," + weight.getWeight() + "," + weight.getIdDocument() + ")") ;
    	}
    	di.closeConnection(con) ;
    }
    
    
    private int getMaxRelationOccurences(Connection con) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	ResultSet rs = di.callProcedure(con, "getMaxTaxonomyRelationOccurrences") ;
    	try {
			rs.next() ;
			int occurrences = rs.getInt("occurrences") ; 
			return occurrences ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return 0 ;
    }
    
    private int getTaxonomyRelationOccurrences(String concept1, String concept2, boolean areHomologous, boolean isBoost, Connection con) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	int occurrences = 0 ;
    	ResultSet rs = di.callProcedure(con, "checkTaxonomyRelationExistence('" + concept1 + "', '" + concept2 + "')") ;
    	try {
			if(rs.next()) {
				int exists = rs.getInt("relation") ;
				rs.close() ;
				if (exists > 0) {
					rs = di.callProcedure(con, "getTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "')") ;
					rs.next() ;
					occurrences = rs.getInt("occurrences") ;
					rs.close() ;
					//di.callProcedure(con, "updateTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "', " + (occurrences + 1) + ")") ;
					return occurrences ;
				}
				else {
					
						rs = di.callProcedure(con, "getRelationOccurrences('" + concept1 + "', '" + concept2 + "')") ;
						if(rs.next()) {
							occurrences = rs.getInt("occurrences") ;
							rs.close() ;
							if (isBoost) {
								rs = di.callProcedure(con, "getLastTaxonomyRelationId") ;
								rs.next() ;
								int maxId = rs.getInt("lastId") ;
								rs.close();
								if (occurrences > 0) {
									di.callProcedure(con, "insertTaxonomyRelation(" + (maxId + 1) + ", '" + concept1 + "', '" + concept2 + "', " + areHomologous + ", " + (occurrences) + ")") ;
									return occurrences ;
								}
								else {
									di.callProcedure(con, "insertTaxonomyRelation(" + (maxId + 1) + ", '" + concept1 + "', '" + concept2 + "', " + areHomologous + ", 1)") ;
									return 1;
								}
							}
							else {
								return occurrences ;
							}
						}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*ResultSet rs = di.callProcedure(con, "getRelationOccurrences('" + concept1 + "', '" + concept2 + "')") ;
    	try {
			if(rs.next()) {
				int occurrences = rs.getInt("occurrences") ;
				if (occurrences > 0) {
					ResultSet rsAux = di.callProcedure(con, "checkTaxonomyRelationExistence('" + concept1 + "', '" + concept2 + "')") ;
					if (rsAux.next()) {
						int exists = rsAux.getInt("relation") ;
						if (exists > 0) {
							if (isBoost) {
								di.callProcedure(con, "updateTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "', " + occurrences + ")") ;
							}
							else {
								di.callProcedure(con, "updateTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "', " + (occurrences + 1) + ")") ;
							}
						}
						else {
							rsAux = di.callProcedure(con, "getLastTaxonomyRelationId") ;
							rsAux.next() ;
							int maxId = rsAux.getInt("lastId") ;
							if (isBoost) {
								di.callProcedure(con, "insertTaxonomyRelation(" + (maxId + 1) + ", '" + concept1 + "', '" + concept2 + "', " + areHomologous + ", " + (occurrences) + ")") ;
							}
							else {
								di.callProcedure(con, "insertTaxonomyRelation(" + (maxId + 1) + ", '" + concept1 + "', '" + concept2 + "', " + areHomologous + ", " + (occurrences + 1) + ")") ;
							}
						}
						di.closeConnection(con) ;
						return occurrences ;
					}
				}
				else {
					di.closeConnection(con) ;
					return 0 ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	return 0 ;
    }
    
    private int getTotalDocumentsNumber(Connection con) {
        try {
            DatabaseInteraction di = new DatabaseInteractionImpl() ;
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
    
    private int getDocumentsNumberWithConcept(String concept, Connection con) {
        try {
            DatabaseInteraction di = new DatabaseInteractionImpl() ;
            ResultSet rs = di.callProcedure(con, "svdb.getDocumentNumWithConcept('" + concept + "')") ;
            rs.next() ;
            int result = rs.getInt("nDocument") ;
            rs.close() ;
            return result ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }
}
