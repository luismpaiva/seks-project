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
    public String checkHomologyBetweenConcepts(String concept1, String concept2) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
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
            String superClass = oi.getDirectParentClass(concept2) ;
            return "non-homologous:" + superClass ;
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
    public double calculateHomologousSimilarityFactor(String ancestorName, String siblingName) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        CalculusTools ct = new CalculusToolsImpl() ;
        double alpha = 0.5;
        double beta = (this.getTaxonomyRelationOccurrences(ancestorName, siblingName, true) / this.getMaxRelationOccurences()) ;
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
    public double calculateNonHomologousSimilarityFactor(String ancestorName, String concept1, String concept2) {
        OntologyInteraction oi = new OntologyInteractionImpl() ;
        CalculusTools ct = new CalculusToolsImpl() ;
        double alpha = 0.5 ;
        double beta = ((double)this.getTaxonomyRelationOccurrences(concept1, concept2, false) / (double)this.getMaxRelationOccurences()) ;
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
    	
    	//First Step: Increase relevance of existing concepts according to taxonomic relations
    	
    	tbSemanticVector = this.boostConceptsWeightsWithinVector(kbSemanticVector) ;
    	
    	//Second Step: Add new concepts that have a strong relation with existing concepts
    	
    	double homologousThreshold = 0.75 ;
    	double nonHomologousThreshold = 0.80 ;
    	
    	tbSemanticVector = this.addImportantConceptsToVector(tbSemanticVector, homologousThreshold, nonHomologousThreshold) ;
    	
    	return tbSemanticVector ;
    }
    
    
    public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> tbSemanticVector, double homologousThreshold, double nonHomologousThreshold) {
    	OntologyInteraction oi = new OntologyInteractionImpl() ;
    	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
    	ArrayList<String> classes = oi.getAllSubclasses("Concept") ;
    	Set<String> vector = tbSemanticVector.keySet() ;
    	Iterator<String> vectorIter = vector.iterator() ; 
    	double similarity = 0.0 ;
    	boolean areHomologous = false ;
    	int idDocument ;
    	while (vectorIter.hasNext()) {
    		String currentConcept = vectorIter.next() ;
    		idDocument = tbSemanticVector.get(currentConcept).getIdDocument() ;
	    	Iterator<String> classesIter = classes.iterator() ;
	    	while (classesIter.hasNext()) {
	    		String concept = classesIter.next() ;
	    		if (!vector.contains("concept")) {
	    			String relation = this.checkHomologyBetweenConcepts(currentConcept, concept) ;
	    			if (relation.startsWith("homologous:")) {
	    				areHomologous = true ;
	    				String ancestor = relation.substring(relation.indexOf(":") + 1) ;
	    				if (concept.equals(ancestor)) {
	    					similarity = calculateHomologousSimilarityFactor(concept, currentConcept) ;
	    				}
	    				else if (currentConcept.equals(ancestor)) {
	    					similarity = calculateHomologousSimilarityFactor(currentConcept, concept) ;
	    				}
	    			}
	    			else if (relation.startsWith("non-homologous:")) {
	    				areHomologous = false ;
	    				if (relation.equals("non-homologous:Concept")) {
	    					similarity = calculateNonHomologousSimilarityFactor("Concept", concept, currentConcept) ;
	    				}
	    				else {
	    					String ancestor = relation.substring(relation.indexOf(":") + 1) ;
	    					similarity = calculateNonHomologousSimilarityFactor(ancestor, concept, currentConcept) ;
	    				}
	    				
	    			}
	    			
	    			if ((areHomologous && (similarity < homologousThreshold)) || ((!areHomologous) && (similarity < nonHomologousThreshold))) {
    					continue ;
    				}
    				else {
    					CalculusTools ct = new CalculusToolsImpl() ;
    			    	int docNum = this.getTotalDocumentsNumber() ;
    			    	int docNumByConcept = this.getDocumentsNumberWithConcept(concept) ;
    			    	double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    			    	double weight = result + ( tbSemanticVector.get(currentConcept).getWeight()*similarity) ;
    			    	SemanticWeight sw = new SemanticWeight(idDocument, concept, weight) ;
    			    	tbSemanticVector.put(concept, sw) ;
    				}
	    		}
	    		else continue ;
	    	}
    	}
    	return kbsvCreator.semanticVectorNormalization(tbSemanticVector) ;
    }
    
    
    public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> kbSemanticVector) {
    	HashMap<String, SemanticWeight> tbSemanticVector = new HashMap<String, SemanticWeight>() ;
    	KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
    	
    	//First Step: Increase relevance of existing concepts according to taxonomic relations
    	Set<String> concepts = kbSemanticVector.keySet() ;
    	Iterator<String> conceptsIter = concepts.iterator() ;
    	double similarity = 0.0 ;
    	boolean areHomologous = false ;
    	
    	while (conceptsIter.hasNext()) {
    		String thisConcept = conceptsIter.next() ;
    		concepts.remove(thisConcept) ;
    		Iterator<String> iter = concepts.iterator() ;
    		while (iter.hasNext()) {
    			String thatConcept = iter.next() ;
    			String taxonomicRelation = checkHomologyBetweenConcepts(thisConcept, thatConcept) ;
    			if (taxonomicRelation.startsWith("homologous:")) {			// If the relation is homologous
    				areHomologous = true ;
    				String concept = taxonomicRelation.substring(taxonomicRelation.indexOf(":") + 1) ;
    				if (thisConcept.equals(concept)) {
    					similarity = calculateHomologousSimilarityFactor(thisConcept, thatConcept) ;
    				}
    				else if (thatConcept.equals(concept)) {
    					similarity = calculateHomologousSimilarityFactor(thatConcept, thisConcept) ;
    				}
    			}
    			else if (taxonomicRelation.startsWith("non-homologous:")) {	// If the relation is non-homologous
    				areHomologous = false ;
    				if (taxonomicRelation.equals("non-homologous:Concept")) {
    					similarity = calculateNonHomologousSimilarityFactor("Concept", thisConcept, thatConcept) ;
    				}
    				else {
    					String concept = taxonomicRelation.substring(taxonomicRelation.indexOf(":") + 1) ;
    					similarity = calculateNonHomologousSimilarityFactor(concept, thisConcept, thatConcept) ;
    				}
    			}
    			
    			// Calculating Co-occurrence throughout the documents' universe
    			
    			CalculusTools ct = new CalculusToolsImpl() ;
    			int docNum = this.getTotalDocumentsNumber() ; 
    			int relationOccurrences = this.getTaxonomyRelationOccurrences(thisConcept, thatConcept, areHomologous) ;
    			double cooccurrence = ct.tfIdfAlgorithm(docNum, relationOccurrences, 1.0, 1.0) ;
    			
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
    	return kbsvCreator.semanticVectorNormalization(tbSemanticVector) ;
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
    
    
    private int getMaxRelationOccurences() {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	ResultSet rs = di.callProcedure(con, "getMaxTaxonomyRelationOccurrences") ;
    	try {
			rs.next() ;
			di.closeConnection(con) ;
			return rs.getInt("occurrences") ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	di.closeConnection(con) ;
    	return 0 ;
    }
    
    private int getTaxonomyRelationOccurrences(String concept1, String concept2, boolean areHomologous) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	ResultSet rs = di.callProcedure(con, "getTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "')") ;
    	try {
			if (rs.next()) {
				di.callProcedure(con, "updateTaxonomyRelationOccurrences('" + concept1 + "', '" + concept2 + "')") ;
				di.closeConnection(con) ;
				return rs.getInt("occurrences") + 1 ;
			}
			else {
				di.callProcedure(con, "insertTaxonomyRelation('" + concept1 + "', '" + concept2 + "', " + areHomologous + ", 1)") ;
				di.closeConnection(con) ;
				return 1 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	di.closeConnection(con) ;
    	return 0 ;
    }
    
    private int getTotalDocumentsNumber() {
        try {
            DatabaseInteraction di = new DatabaseInteractionImpl() ;
            Connection con = di.openConnection("svdbConfig.xml") ;
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
    
    private int getDocumentsNumberWithConcept(String concept) {
        try {
            DatabaseInteraction di = new DatabaseInteractionImpl() ;
            Connection con = di.openConnection("svdbConfig.xml") ;
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
