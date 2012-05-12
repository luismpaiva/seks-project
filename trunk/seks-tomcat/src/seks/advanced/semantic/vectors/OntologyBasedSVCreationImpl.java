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
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		Connection con = di.openConnection("svdbConfig.xml") ;
		//First Step: If two concepts of the vector have a semantic relation between them, boost their weights
		obSemanticVector = this.boostConceptsWeightsWithinVector(semanticVector, con) ;
		
		//Second step: Add a concept to the vector that is semantically linked to a concept already present on the vector
		
		double threshold = 0.7 ;
		
		obSemanticVector = this.addImportantConceptsToVector(obSemanticVector, threshold, con) ;
		
		return obSemanticVector ;
	}
	
	public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> semanticVector, Connection con) {
		Iterator<String> concepts = semanticVector.keySet().iterator() ;
		Set<String> conceptSet = semanticVector.keySet() ; 
		ArrayList<String> pastConcepts = new ArrayList<String>() ;
		
		//First Step: If two concepts of the vector have a semantic relation between them
		while (concepts.hasNext()) {
			String concept = concepts.next() ;
			Iterator<String> iter = conceptSet.iterator() ;
			while (iter.hasNext()) {
				String currentConcept = iter.next() ;
				if ((!concept.equals(currentConcept)) && (!pastConcepts.contains(currentConcept))) {
					SemanticRelation relation = getRelationImportance(concept, currentConcept, con) ;
					if (!relation.getSubject().equals("")) {
						String subject = relation.getSubject() ;
						String object = relation.getObject() ;
						SemanticWeight subjectWeight = semanticVector.get(subject) ;
						double weight1 = subjectWeight.getWeight() ;
						SemanticWeight objectWeight = semanticVector.get(object) ;
						double weight2 = objectWeight.getWeight() ;
						
						// Calculating Co-occurrence throughout the documents' universe
		    			
		    			CalculusTools ct = new CalculusToolsImpl() ;
		    			//int docNum = this.getTotalDocumentsNumber() ;
		    			int docNum = 6 ;
		    			int relationOccurrences = this.getOntologyRelationOccurrences(subject, object, relation.getRelation(), con) ;
		    			double cooccurrence = ct.tfIdfAlgorithm(docNum, relationOccurrences, 1.0, 1.0) ;
						
						subjectWeight.setWeight(weight1 + (weight2*relation.getImportanceThreshold() + cooccurrence)) ;
						objectWeight.setWeight(weight2 + (weight1*relation.getImportanceThreshold() + cooccurrence)) ;
						
						semanticVector.put(subject, subjectWeight) ;
						semanticVector.put(object, objectWeight) ;
					}
					else continue ;
				}
			}
			pastConcepts.add(concept) ;
		}
		return semanticVector ;
	}
	
	public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> obSemanticVector, double threshold, Connection con) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		Iterator<String> vectorIter = obSemanticVector.keySet().iterator() ;
		int idDocument ;
		HashMap<String, SemanticWeight> newObSemanticVector = new HashMap<String, SemanticWeight>() ;
		newObSemanticVector.putAll(obSemanticVector) ;
		ResultSet rs = di.callProcedure(con, "getRelationImportanceWithMinimumThreshold(" + threshold + ")") ;
		//Second step: Add a concept to the vector that is semantically linked to a concept already present on the vector
		try {
			while (rs.next()) {
				String subject = rs.getString("subject") ;
				String object = rs.getString("object") ;
				String relation = rs.getString("property") ;
				double importanceThreshold = rs.getDouble("importanceThreshold") ;
				while (vectorIter.hasNext()) {
					String concept = vectorIter.next() ;
					idDocument = obSemanticVector.get(concept).getIdDocument() ;
					if (concept.equals(subject) && (!obSemanticVector.containsKey(object))) {
						CalculusTools ct = new CalculusToolsImpl() ;
    			    	//int docNum = this.getTotalDocumentsNumber(con) ;
    			    	int docNum = 6 ;
						//int docNumByConcept = this.getDocumentsNumberWithConcept(object, con) ;
    			    	int docNumByConcept = this.getOntologyRelationOccurrences(subject, object, relation, con) ;
    			    	double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    			    	if (newObSemanticVector.containsKey(object)) {
    			    		SemanticWeight sw = obSemanticVector.get(subject) ;
    			    		double weight = result + (sw.getWeight() - result) + (obSemanticVector.get(concept).getWeight()*importanceThreshold) ;
    			    		sw.setWeight(weight) ;
    			    		newObSemanticVector.put(subject, sw) ;
    			    	}
    			    	else {
        			    	double weight = result + ( obSemanticVector.get(concept).getWeight()*importanceThreshold) ;
    			    		SemanticWeight sw = new SemanticWeight(idDocument, subject, weight) ;
    			    		newObSemanticVector.put(subject, sw) ;
    			    	}
					}
					else if (concept.equals(object) && (!obSemanticVector.containsKey(subject))) {
						CalculusTools ct = new CalculusToolsImpl() ;
    			    	//int docNum = this.getTotalDocumentsNumber(con) ;
    			    	int docNum = 6 ;
						//int docNumByConcept = this.getDocumentsNumberWithConcept(subject, con) ;
    			    	int docNumByConcept = this.getOntologyRelationOccurrences(subject, object, relation, con) ;
    			    	double result = ct.tfIdfAlgorithm(docNum, docNumByConcept, 1.0, 1.0) ;
    			    	if (newObSemanticVector.containsKey(subject)) {
    			    		SemanticWeight sw = obSemanticVector.get(subject) ;
    			    		double weight = result + (sw.getWeight() - result) + (obSemanticVector.get(concept).getWeight()*importanceThreshold) ;
    			    		sw.setWeight(weight) ;
    			    		newObSemanticVector.put(subject, sw) ;
    			    	}
    			    	else {
        			    	double weight = result + ( obSemanticVector.get(concept).getWeight()*importanceThreshold) ;
    			    		SemanticWeight sw = new SemanticWeight(idDocument, subject, weight) ;
    			    		newObSemanticVector.put(subject, sw) ;
    			    	}
					}
					else continue ;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newObSemanticVector ;
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
	
	private int getOntologyRelationOccurrences(String subject, String object, String relation, Connection con) {
    	DatabaseInteraction di = new DatabaseInteractionImpl() ;
    	ResultSet rs = di.callProcedure(con, "getOntologyRelationOccurrences('" + subject + "','" + object + "')") ;
    	try {
			if (rs.next()) {
				//di.callProcedure(con, "updateOntologyRelationOccurrences('" + subject + "','" + object + "','" + rs.getString("relation") + "')") ;
				return rs.getInt("occurrences") ;
			}
			else {
				di.callProcedure(con, "insertOntologyRelation('" + subject + "','" + object + "','" + relation + "',1)") ;
				return 1 ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	private ArrayList<String> getConceptsWithMinimumRelationThreshold(String concept, double minThreshold, Connection con) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		ArrayList<String> importantConcepts = new ArrayList<String>() ;
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
	
	private SemanticRelation getRelationImportance(String concept1, String concept2, Connection con) {
		DatabaseInteraction di = new DatabaseInteractionImpl() ;
		SemanticRelation relation = new SemanticRelation() ;
		relation.setSubject("") ;
		try {
			ResultSet rs = di.callProcedure(con, "checkRelationImportanceExistence('" + concept1 + "','" + concept2 + "')") ;
			rs.next() ;
			if (rs.getInt("relation") > 0) {
				rs.close();
				rs = di.callProcedure(con, "getRelationImportanceWithConcepts('" + concept1 + "','" + concept2 + "')") ;
				rs.next();
				relation.setSubject(rs.getString("subject")) ;
				relation.setRelation(rs.getString("property")) ;
				relation.setObject(rs.getString("object")) ;
				relation.setImportanceThreshold(rs.getDouble("importanceThreshold")) ;
			}
			rs.close() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return relation ;
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
