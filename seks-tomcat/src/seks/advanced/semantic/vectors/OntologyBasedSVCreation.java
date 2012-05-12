package seks.advanced.semantic.vectors;

import java.sql.Connection;
import java.util.HashMap;

import seks.basic.pojos.SemanticWeight;

public interface OntologyBasedSVCreation {
	
	public HashMap<String, SemanticWeight> createOntologyBasedSemanticVector(HashMap<String, SemanticWeight> semanticVector) ;
	
	public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> obSemanticVector, double threshold, Connection con) ;
	
	public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> semanticVector, Connection con) ;
	
	public void storeOntologyBasedSemanticVector(HashMap<String, SemanticWeight> obSemanticVector) ;
}
