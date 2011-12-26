/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import seks.basic.exceptions.MissingParamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Figueiras
 */
public class OntologyInteractionImpl implements OntologyInteraction {
    
    private static String namespace = ("http://www.knowspaces.com/ontology_v1.owl#") ;
    private OntologyPersistence op ;
    private OntModel m ;
    
    /*  Constructor  */
    public OntologyInteractionImpl(){
            try {
                op = new OntologyPersistenceImpl() ;
                m = op.getModel() ;
            } catch (IOException ex) {
                Logger.getLogger(OntologyInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OntologyInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MissingParamException ex) {
                Logger.getLogger(OntologyInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /*  Semantic Getters  */
    
    @Override
    public Individual getIndividual(String individualName) {
       return m.getIndividual(namespace + individualName) ;
    }
    
    @Override
    public String getIndividualDirectParentClass(String individualName) {
        Individual individual = getIndividual(individualName) ;
        return individual.getOntClass().getLocalName() ;
    }
    
    @Override
    public String getIndividualAbsoluteParentClass(String individualName) {
        String className = getIndividualAbsoluteParentClass(individualName) ;
        while(!m.getOntClass(namespace + className).getSuperClass().getLocalName().equals("Concept")) {
            className = m.getOntClass(namespace + className).getSuperClass().getLocalName() ;
        }
        return className ;
    }
    
    @Override
    public Property getProperty(String propertyName) {
        return m.getProperty(namespace + propertyName) ;
    }
    
    @Override
    public OntClass getClass(String className) {
        return m.getOntClass(namespace + className) ;
    }
    
    @Override
    public ArrayList<String> getObjectsFromTriple(String subjectName, String propertyName) {
        Individual individual = getIndividual(subjectName) ;
        Property property = getProperty(propertyName) ;
        NodeIterator iter = m.listObjectsOfProperty(individual, property) ;
        ArrayList<String> list = new ArrayList<String>() ;
        while (iter.hasNext()) {
            RDFNode node = iter.nextNode() ;
            list.add(node.toString()) ;
        }
        return list ;
    }
    
    @Override
    public ArrayList<String> getSubjectsFromTriple(String objectName, String propertyName) {
        Property property = getProperty(propertyName) ;
        ResIterator iter = m.listSubjectsWithProperty(property, objectName) ;
        ArrayList<String> list = new ArrayList<String>() ;
        while (iter.hasNext()) {
            Resource res = iter.next() ;
            list.add(res.getLocalName()) ;
        }
        return list ;
    }
    
    /*  Semantic Boolean Operators  */
    
    @Override
    public boolean isIndividual(String objectName) {
        OntResource resource = m.getOntResource(namespace + objectName) ;
        return resource.isIndividual() ;
    }
    
    @Override
    public boolean isClass (String objectName) {
        OntResource resource = m.getOntResource(namespace + objectName) ;
        return resource.isClass() ;
    }
    
    /*  Semantic Setters  */
    
    @Override
    public void setIndividual(String individualName, String parentClassName) {
        OntClass parentClass = m.getOntClass(namespace + parentClassName) ;
        parentClass.createIndividual(namespace + individualName) ;
    }
    
    @Override
    public void setClass(String newClassName, String parentClassName) {
        OntClass parentClass = m.getOntClass(namespace + parentClassName) ;
        OntClass newClass = m.createClass(namespace + newClassName) ;
        parentClass.setSubClass(newClass) ;
    }
    
    public void setObjectProperty(String propertyName) {
        m.createObjectProperty(namespace + propertyName) ;
    }
    
    public void setDatatypeProperty(String propertyName) {
        m.createDatatypeProperty(namespace + propertyName) ;
    }
}


