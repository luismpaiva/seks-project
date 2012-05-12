package seks.basic.pojos;

public class SemanticRelation {

	int idRelation ;
	String subject  = "", object  = "", relation = "" ;
	double importanceThreshold ;
	
	/**
	 * Class Constructor
	 */
	public SemanticRelation() {}
	
	/**
	 * Class Constructor with parameter insertion.
	 * 
	 * @param subject				The local name of the subject for the relation
	 * @param object				The local name of the object for the relation
	 * @param relation				The local name of the relation
	 * @param importanceThreshold	The importance of the semantic relation
	 */
	public SemanticRelation(String subject, String object, String relation, double importanceThreshold) {
		this.subject = subject ;
		this.object = object ;
		this.relation = relation;
		this.importanceThreshold = importanceThreshold ;
	}
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public double getImportanceThreshold() {
		return importanceThreshold;
	}

	public void setImportanceThreshold(double importanceThreshold) {
		this.importanceThreshold = importanceThreshold;
	}

	public int getIdRelation() {
		return idRelation;
	}
	
	public void setIdRelation(int idRelation) {
		this.idRelation = idRelation;
	}
}
