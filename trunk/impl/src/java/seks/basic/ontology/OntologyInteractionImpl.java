/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.ontology;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import seks.basic.exceptions.MissingParamException;

/**
 *
 * @author Paulo Figueiras
 */
public class OntologyInteractionImpl implements OntologyInteraction {
    
    private static String namespace = ("http://www.knowspaces.com/ontology_v1.owl#") ;
    OntModel m ;
    
    /*  Constructor  */
    public OntologyInteractionImpl() {
        try {
            OntologyPersistence op = new OntologyPersistenceImpl() ;
            op.setS_reload(false) ;
            op.load() ;
            this.m = op.getModel() ;
        } catch (MissingParamException ex) {
            Logger.getLogger(OntologyInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OntologyInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
        Individual individual = this.getIndividual(individualName) ;
        return individual.getOntClass().getLocalName() ;
    }
    
    @Override
    public String getIndividualAbsoluteParentClass(String individualName) {
        String className = this.getIndividualAbsoluteParentClass(individualName) ;
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
        Property property = this.getProperty(propertyName) ;
        Resource res = m.getResource(namespace + subjectName) ;
        
        ArrayList<String> list = new ArrayList<String>() ;
        StmtIterator iter = res.listProperties(property) ;
        while (iter.hasNext()) {
            Statement stmt = iter.next() ;
            list.add(stmt.getLiteral().getString());
        }
        return list ;
    }
    
    @Override
    public ArrayList<String> getSubjectsFromTriple(String objectName, String propertyName) {
        ArrayList<String> list = new ArrayList<String>() ;
        Property property = this.getProperty(propertyName) ;
        NodeIterator iter = m.listObjectsOfProperty(property) ;
        while(iter.hasNext()) {
            Literal node = iter.next().asLiteral() ;
            if (node.getString().matches(objectName)) {
                ResIterator i = m.listResourcesWithProperty(property, m.asRDFNode(node.asNode())) ;
                while (i.hasNext()) {
                    list.add(i.nextResource().getLocalName());
                }
            }
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


