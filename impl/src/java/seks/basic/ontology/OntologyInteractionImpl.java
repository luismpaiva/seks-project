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
 * Implementation class of interface {@link OntologyInteraction}
 * 
 * @author Paulo Figueiras
 */
public class OntologyInteractionImpl implements OntologyInteraction {
    
    private static String namespace = ("http://www.knowspaces.com/ontology_v1.owl#") ;
    OntModel m ;
    
    /**
     * Class constructor
     * 
     * @see OntologyPersistence
     * @see OntologyPersistenceImpl
     * @see OntologyPersistence#setS_reload(boolean) 
     * @see OntologyPersistence#load() 
     * @see OntologyPersistence#getModel() 
     */
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
    @Override
    public Individual getIndividual(String individualName) {
       return m.getIndividual(namespace + individualName) ;
    }
    
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
    @Override
    public String getIndividualDirectParentClass(String individualName) {
        Individual individual = this.getIndividual(individualName) ;
        return individual.getOntClass().getLocalName() ;
    }
    
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
    @Override
    public String getIndividualAbsoluteParentClass(String individualName) {
        String className = this.getIndividualAbsoluteParentClass(individualName) ;
        while(!m.getOntClass(namespace + className).getSuperClass().getLocalName().equals("Concept")) {
            className = m.getOntClass(namespace + className).getSuperClass().getLocalName() ;
        }
        return className ;
    }
    
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
    @Override
    public Property getProperty(String propertyName) {
        return m.getProperty(namespace + propertyName) ;
    }
    
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
    @Override
    public OntClass getClass(String className) {
        return m.getOntClass(namespace + className) ;
    }
    
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
    @Override
    public ArrayList<String> getAllValuesFromProperty(String propertyName) {
        ArrayList<String> list = new ArrayList<String>() ;
        Property property = this.getProperty(propertyName) ;
        NodeIterator iter = m.listObjectsOfProperty(property) ;
        
        while (iter.hasNext()) {
            list.add(iter.next().asLiteral().getString()) ;
        }
        
        return list ;
    }
    
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
    @Override
    public boolean isIndividual(String objectName) {
        OntResource resource = m.getOntResource(namespace + objectName) ;
        return resource.isIndividual() ;
    }
    
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
    @Override
    public boolean isClass (String objectName) {
        OntResource resource = m.getOntResource(namespace + objectName) ;
        return resource.isClass() ;
    }
    
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
    @Override
    public void setIndividual(String individualName, String parentClassName) {
        OntClass parentClass = m.getOntClass(namespace + parentClassName) ;
        parentClass.createIndividual(namespace + individualName) ;
    }
    
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
    @Override
    public void setClass(String newClassName, String parentClassName) {
        OntClass parentClass = m.getOntClass(namespace + parentClassName) ;
        OntClass newClass = m.createClass(namespace + newClassName) ;
        parentClass.setSubClass(newClass) ;
    }
    
    /**
     * Creates a new {@link com.hp.hpl.jena.ontology.ObjectProperty} in the 
     * persistence model with local name given by <code>propertyName</code>.
     * 
     * @param propertyName  The new {@link com.hp.hpl.jena.ontology.ObjectProperty}'s 
     *                      local name
     * 
     * @see com.hp.hpl.jena.ontology.ObjectProperty
     */
    public void setObjectProperty(String propertyName) {
        m.createObjectProperty(namespace + propertyName) ;
    }
    
    /**
     * Creates a new {@link com.hp.hpl.jena.ontology.DatatypeProperty} in the 
     * persistence model with local name given by <code>propertyName</code>.
     * 
     * @param propertyName  The new {@link com.hp.hpl.jena.ontology.DatatypeProperty}'s 
     *                      local name
     * 
     * @see com.hp.hpl.jena.ontology.DatatypeProperty
     */
    public void setDatatypeProperty(String propertyName) {
        m.createDatatypeProperty(namespace + propertyName) ;
    }
}


