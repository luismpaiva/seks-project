/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.ontology;

import com.hp.hpl.jena.ontology.OntModel;

/**
 *
 * @author Paulo Figueiras
 */
public interface OntologyPersistence {
    public void load() ;

    public OntModel getModel() ;
    
    public void setS_reload(boolean s_reload) ;

    public void closeCon() ;

    public void reopenCon() ;
}
