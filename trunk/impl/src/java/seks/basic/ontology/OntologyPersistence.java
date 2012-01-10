package seks.basic.ontology;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;

/**
 * Comprises all functions and algorithms necessary to achieve ontology 
 * persistence, and to create connection links to the persisted ontology's model.
 * 
 * @author Paulo Figueiras
 */
public interface OntologyPersistence {
    
    /**
     * Loads the ontology's persistence model.
     * 
     * @see seks.basic.ontology.OntologyPersistenceImpl#loadMaker() 
     */
    public void load() ;

    /**
     * Retrieves the ontology's persistence model
     * 
     * @return the model
     * 
     * @see OntModel
     */
    public OntModel getModel() ;
    
    /**
     * @param s_reload the s_reload to set
     */
    public void setS_reload(boolean s_reload) ;

    /**
     * Closes the connection with the persistence model.
     * 
     * @see OntModel
     * @see ModelMaker
     */
    public void closeCon() ;

    /**
     * Establishes a new connection with the persistence model.
     * 
     * @see OntModel
     * @see ModelMaker
     * @see ModelFactory
     */
    public void reopenCon() ;
}
