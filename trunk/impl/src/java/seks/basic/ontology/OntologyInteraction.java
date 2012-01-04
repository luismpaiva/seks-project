/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;
import java.util.ArrayList;

/**
 *
 * @author Paulo Figueiras
 */
public interface OntologyInteraction {
    
    public Individual getIndividual(String individualName) ;
    
    public String getIndividualDirectParentClass(String individualName) ;
    
    public String getIndividualAbsoluteParentClass(String individualName) ;
    
    public Property getProperty(String propertyName) ;
    
    public OntClass getClass(String className) ;
    
    public ArrayList<String> getObjectsFromTriple(String subjectName, String propertyName) ;
    
    public ArrayList<String> getSubjectsFromTriple(String objectName, String propertyName) ;
    
    public ArrayList<String> getAllValuesFromProperty(String propertyName) ;
    
    public boolean isIndividual(String objectName) ;
    
    public boolean isClass (String objectName) ;
    
    public void setIndividual(String individualName, String parentClassName) ;
    
    public void setClass(String newClassName, String parentClassName) ;
}
