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
import seks.basic.pojos.SemanticRelation;
import seks.basic.pojos.SemanticWeight;



public class OntologyBasedSVCreationImpl implements OntologyBasedSVCreation {
	
	public OntologyBasedSVCreationImpl() {}
	
	public HashMap<String, SemanticWeight> createOntologyBasedSemanticVector(HashMap<String, SemanticWeight> semanticVector) {
		HashMap<String, SemanticWeight> obSemanticVector = new HashMap<String, SemanticWeight>() ;
		
		//First Step: If two concepts of the vector have a semantic relation between them, boost their weights
		obSemanticVector = this.boostConceptsWeightsWithinVector(semanticVector) ;
		
		//Second step: Add a concept to the vector that is semantically linked to a concept already present on the vector
		
		double threshold = 0.8 ;
		
		obSemanticVector = this.addImportantConceptsToVector(obSemanticVector, threshold) ;
		
		return obSemanticVector ;
	}
	
	public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> semanticVector) {
		Iterator<String> concepts = semanticVector.keySet().iterator() ;
		Set<String> conceptSet = semanticVector.keySet() ; 
		KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
		
		//First Step: If two concepts of the vector have a semantic relation between them
		while (concepts.hasNext()) {
			String concept = concepts.next() ;
			conceptSet.remove(concept) ;
			Iterator<String> iter = conceptSet.iterator() ;
			while (iter.hasNext()) {
				SemanticRelation relation = getRelationImportance(concept, iter.next()) ;
				if (!relation.getSubject().equals("")) {
					String subject = relation.getSubject() ;
					String object = relation.getObject() ;
					SemanticWeight subjectWeight = semanticVector.get(subject) ;
					double weight1 = subjectWeight.getWeight() ;
					SemanticWeight objectWeight = semanticVector.get(object) ;
					double weight2 = objectWeight.getWeight() ;
					
					// Calculating Co-occurrence throughout the documents' universe
	    			
	    			CalculusTools ct = new CalculusToolsImpl() ;
	    			int docNum = this.getTotalDocumentsNumber() ; 
	    			int relationOccurrences = this.getOntologyRelationOccurrences(subject, object, relation.getRelation()) ;
	    			double cooccurrence = ct.tfIdfAlgorithm(docNum, relationOccurrences, 1.0, 1.0) ;
					
					subjectWeight.setWeight(weight1 + (weight2*relation.getImportanceThreshold() + cooccurrence)) ;
					objectWeight.setWeight(weight2 + (weight1*relation.getImportanceThreshold() + cooccurrence)) ;
					
					semanticVector.remove(subject) ;
					semanticVector.remove(object) ;
					semanticVector.put(subject, subjectWeight) ;
					semanticVector.put(object, objectWeight) ;
				}
				else continue ;
			}
		}
		return kbsvCreator.semanticVectorNormalization(semanticVector) ;
	}
	
	public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> obSemanticVector, double threshold) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
		Connection con = di.openConnection("svdbConfig.xml") ;
		ResultSet rs = di.callProcedure(con, "getRelationImportanceWithMinimumThreshold(" + threshold + ")") ;
		Iterator<String> vectorIter = obSemanticVector.keySet().iterator() ;
		int idDocument ;
		
		//Second step: Add a concept to the vector that is semantically linked to a concept already present on the vector
		try {
			while (rs.next()) {
				while (vectorIter.hasNext()) {
					String concept = vectorIter.next() ;
					idDocument = obSemanticVector.get(concept).getIdDocument() ;
					if (concept.equals(rs.getString("subject"))) {
						String object = rs.getString("object") ;
						CalculusTools ct = new CalculusToolsImpl() ;
    			    	int docNum = this.getTotalDocumentsNumber() ;
    			    	int docNumByConcept = this.getDocumentsNumberWithConcept(object) ;
    			    	double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    			    	double weight = result + ( obSemanticVector.get(concept).getWeight()*rs.getDouble("importanceThreshold")) ;
    			    	SemanticWeight sw = new SemanticWeight(idDocument, object, weight) ;
    			    	obSemanticVector.put(object, sw) ;
					}
					else if (concept.equals(rs.getString("object"))) {
						String subject = rs.getString("subject") ;
						CalculusTools ct = new CalculusToolsImpl() ;
    			    	int docNum = this.getTotalDocumentsNumber() ;
    			    	int docNumByConcept = this.getDocumentsNumberWithConcept(subject) ;
    			    	double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    			    	double weight = result + ( obSemanticVector.get(concept).getWeight()*rs.getDouble("importanceThreshold")) ;
    			    	SemanticWeight sw = new SemanticWeight(idDocument, subject, weight) ;
    			    	obSemanticVector.put(subject, sw) ;
					}
					else continue ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kbsvCreator.semanticVectorNormalization(obSemanticVector) ;
	} 
	
	public void storeOntologyBasedSemanticVector(HashMap<String, SemanticWeight> obSemanticVector) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	Iterator<String> iter = obSemanticVector.keySet().iterator() ;
    	while (iter.hasNext()) {
    		String concept = iter.next() ;
    		SemanticWeight weight = obSemanticVector.get(concept) ;
    		di.callProcedure(con, "insertOntologyBasedWeight('" + weight.getConcept() + "'," + weight.getWeight() + "," + weight.getIdDocument() + ")") ;
    	}
    	di.closeConnection(con) ;
	}
	
	private int getOntologyRelationOccurrences(String subject, String object, String relation) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	Connection con = di.openConnection("svdbConfig.xml") ;
    	ResultSet rs = di.callProcedure(con, "getOntologyRelationOccurrences('" + subject + "','" + object + "')") ;
    	try {
			if (rs.next()) {
				di.callProcedure(con, "updateOntologyRelationOccurrences('" + subject + "','" + object + "','" + rs.getString("relation") + "')") ;
				di.closeConnection(con) ;
				return rs.getInt("occurrences") + 1 ;
			}
			else {
				di.callProcedure(con, "insertOntologyRelation('" + subject + "','" + object + "','" + relation + "',1)") ;
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
	
	private ArrayList<String> getConceptsWithMinimumRelationThreshold(String concept, double minThreshold) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		ArrayList<String> importantConcepts = new ArrayList<String>() ;
		Connection con = di.openConnection("svdbConfig.xml") ;
		ResultSet rs = di.callProcedure(con, "getRelationImportanceWithMinimumThreshold(" + minThreshold + ")") ;
		try {
			while (rs.next()) {
				if (rs.getString("subject").equals(concept)) {
					importantConcepts.add(rs.getString("object")) ;
				}
				else if (rs.getString("object").equals(concept)) {
					importantConcepts.add(rs.getString("subject")) ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return importantConcepts ;
	}
	
	private SemanticRelation getRelationImportance(String concept1, String concept2) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		SemanticRelation relation = new SemanticRelation() ;
		Connection con = di.openConnection("svdbConfig.xml") ;
		ResultSet rs = di.callProcedure(con, "getRelationImportanceWithConcepts('" + concept1 + "','" + concept2 + "')") ;
		try {
			if (rs.next()) {
				relation.setIdRelation(rs.getInt("idRelationImportance")) ;
				relation.setSubject(rs.getString("subject")) ;
				relation.setRelation(rs.getString("property")) ;
				relation.setObject(rs.getString("object")) ;
				relation.setImportanceThreshold(rs.getDouble("importanceThreshold")) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		di.closeConnection(con) ;
		return relation ;
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
