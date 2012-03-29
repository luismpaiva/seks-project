package seks.advanced.semantic.vectors;

import java.util.HashMap;

import seks.basic.pojos.SemanticWeight;

public interface OntologyBasedSVCreation {
	
	public HashMap<String, SemanticWeight> createOntologyBasedSemanticVector(HashMap<String, SemanticWeight> semanticVector) ;
	
	public HashMap<String, SemanticWeight> addImportantConceptsToVector(HashMap<String, SemanticWeight> obSemanticVector, double threshold) ;
	
	public HashMap<String, SemanticWeight> boostConceptsWeightsWithinVector(HashMap<String, SemanticWeight> semanticVector) ;
	
	public void storeOntologyBasedSemanticVector(HashMap<String, SemanticWeight> obSemanticVector) ;
}
