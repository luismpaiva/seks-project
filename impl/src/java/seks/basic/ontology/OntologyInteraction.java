package seks.basic.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import java.util.ArrayList;

/**
 * Comprises all functions and algorithms that manage ontology interaction, 
 * ontology getters and setters included.
 * 
 * @author Paulo Figueiras
 */
public interface OntologyInteraction {
    
    /*  Semantic Getters  */
    
    /**
     * Retrieves the {@link Individual} object that has the input parameter as 
     * local name.
     * 
     * @param individualName    The {@link Individual}'s local name
     * 
     * @return                  A {@link Individual} object with the input 
     *                          parameter as local name
     * 
     * @see Individual
     */
    public Individual getIndividual(String individualName) ;
    
    /**
     * Retrieves the local name of the first (direct) ontology class that is 
     * parent of the individual who's local name is the input parameter.
     * 
     * @param individualName    The {@link Individual}'s local name
     * 
     * @return                  The {@link Individual}'s direct parent class local name
     * 
     * @see #getIndividualAbsoluteParentClass(java.lang.String) 
     * @see #getIndividual(java.lang.String) 
     * @see Individual
     */
    public String getIndividualDirectParentClass(String individualName) ;
    
    /**
     * Retrieves the local name of the last (absolute) ontology class that is 
     * parent of the individual who's local name is the input parameter.
     * 
     * @param individualName    The {@link Individual}'s local name
     * 
     * @return                  The {@link Individual}'s parent absolute class local name
     * 
     * @see #getIndividualDirectParentClass(java.lang.String) 
     * @see OntModel
     */
    public String getIndividualAbsoluteParentClass(String individualName) ;
    
    /**
     * Retrieves the {@link Property} object that has the input parameter as 
     * local name.
     * 
     * @param propertyName  The {@link Property}'s local name
     * 
     * @return              A {@link Property} object with the input parameter 
     *                      as local name
     * 
     * @see Property
     */
    public Property getProperty(String propertyName) ;
    
    /**
     * Retrieves the {@link OntClass} object that has the input parameter as 
     * local name.
     * 
     * @param className     The {@link OntClass}'s local name
     * 
     * @return              A {@link OntClass} object with the input parameter 
     *                      as local name
     * 
     * @see OntClass
     */
    public OntClass getClass(String className) ;
    
    /**
     * Retrieves all object values from an OWL/RDF triple, with subject ({@link OntClass} or
     * {@link Individual}) and {@link Property} local names given by the input 
     * parameters.
     * 
     * @param subjectName   The local name for the subject in the OWL/RDF triple 
     * @param propertyName  The {@link Property}'s local name in the OWL/RDF 
     *                      triple 
     * 
     * @return              All object values from the OWL triple, with subject 
     *                      and property given by the input parameters, in the 
     *                      form of an {@link ArrayList}
     * 
     * @see #getProperty(java.lang.String) 
     * @see Property
     * @see Resource
     * @see ArrayList
     */
    public ArrayList<String> getObjectsFromTriple(String subjectName, String propertyName) ;
    
    /**
     * Retrieves all subject values from an OWL/RDF triple, with object 
     * ({@link Literal}) and {@link Property} local names given by the input 
     * parameters.
     * 
     * @param objectName    The local name for the object in the OWL/RDF triple
     * @param propertyName  The {@link Property}'s local name in the OWL/RDF 
     *                      triple
     * 
     * @return              All subject values from the OWL triple, with object 
     *                      and property given by the input parameters, in the 
     *                      form of an {@link ArrayList}
     * 
     * @see #getProperty(java.lang.String) 
     * @see Property
     * @see Literal
     * @see ArrayList
     */
    public ArrayList<String> getSubjectsFromTriple(String objectName, String propertyName) ;
    
    /**
     * Retrieves all object values from an OWL/RDF triple, with {@link Property} 
     * local name given by the input parameter.
     * 
     * @param propertyName  The {@link Property}'s local name in the OWL/RDF 
     *                      triple
     * 
     * @return              All object values from the OWL triple, with property
     *                      given by the input parameter, in the form of an 
     *                      {@link ArrayList}
     * 
     * @see #getProperty(java.lang.String) 
     * @see Property
     * @see ArrayList
     */
    public ArrayList<String> getAllValuesFromProperty(String propertyName) ;
    
    
    /*  Semantic Boolean Operators  */
    
    /**
     * Checks if the object ({@link OntResource}) with local name given by the 
     * input parameter is an {@link Individual} object.
     * 
     * @param objectName    The {@link OntResource}'s local name
     * 
     * @return              <code>true</code> if {@link OntResource} object is 
     *                      an {@link Individual} object; <code>false</code> 
     *                      otherwise
     * 
     * @see OntResource
     * @see Individual
     */
    public boolean isIndividual(String objectName) ;
    
    /**
     * Checks if the object ({@link OntResource}) with local name given by the 
     * input parameter is an {@link OntClass} object.
     * 
     * @param objectName    The {@link OntResource}'s local name
     * 
     * @return              <code>true</code> if {@link OntResource} object is 
     *                      an {@link OntClass} object; <code>false</code> 
     *                      otherwise
     * 
     * @see OntResource
     * @see OntClass
     */
    public boolean isClass (String objectName) ;
    
    
    /*  Semantic Setters  */
    
    /**
     * Creates a new {@link Individual} in the persistence model with local name 
     * given by <code>individualName</code> as child of the ontology class with
     * local name given by <code>parentClassName</code>.
     * 
     * @param individualName    The new {@link Individual}'s local name
     * @param parentClassName   The new {@link Individual} parent class's local 
     *                          name
     * 
     * @see OntClass
     * @see Individual
     */
    public void setIndividual(String individualName, String parentClassName) ;
    
    /**
     * Creates a new {@link OntClass} in the persistence model with local name 
     * given by <code>newClassName</code> as child of the ontology class with
     * local name given by <code>parentClassName</code>.
     * 
     * @param newClassName      The new {@link OntClass}'s local name
     * @param parentClassName   The new {@link OntClass} parent class's local 
     *                          name
     * 
     * @see OntClass
     */
    public void setClass(String newClassName, String parentClassName) ;
}
