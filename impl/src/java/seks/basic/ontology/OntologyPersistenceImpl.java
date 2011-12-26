/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.ontology;

/**
 *
 * @author Paulo Figueiras
 */

import java.io.*;
import com.hp.hpl.jena.db.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import seks.basic.exceptions.MissingParamException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OntologyPersistenceImpl implements OntologyPersistence {
    
    private HashMap<String, String> params = new HashMap<String, String>() ;
    private String configFilePath = "./src" ;
    private static final String configFileName = "jena.conf.xml" ;
    private String sParams[] = {"ont_file", "db_url", "db_user", "db_passwd", "db", "db_driver"} ;
    private String sDbURL ;
    private String sDbUser ;
    private String sDbPw ;
    private String sDbType ;
    private String sDbDriver ;

    // if true, reload the data
    private boolean sReload = false;

    // source URL to load data from; if null, use default
    private String sSource;

    private ModelMaker maker ;

    private OntModel m ;

    public OntologyPersistenceImpl(String configFilePath) throws MissingParamException, IOException, ClassNotFoundException {
        
        this.configFilePath = configFilePath ;
        loadConfigParams();
    }

    public OntologyPersistenceImpl() throws MissingParamException, IOException, ClassNotFoundException {
        loadConfigParams();
    }

    private void loadConfigParams() throws MissingParamException, IOException, ClassNotFoundException {
        DOMParser domp = new DOMParser();
        try {
            domp.parse(configFilePath + "/" + configFileName);
            Document doc = domp.getDocument() ;
            Element root = doc.getDocumentElement() ;
            for(String param: sParams){
                NodeList paramNode = root.getElementsByTagName(param) ;
                if(paramNode != null){
                    Element el = (Element) paramNode.item(0) ;
                    String paramVal = el.getFirstChild().getNodeValue() ;
                    if(paramVal != null)
                        params.put(param, paramVal) ;
                    else
                        throw new MissingParamException("Ficheiro de configuração incompleto.") ;
                }
            }
            sSource = configFilePath + "/" + params.get(sParams[0]) ;
            sDbURL = params.get(sParams[1]) ;
            sDbUser = params.get(sParams[2]) ;
            //sDbPw = params.get(sParams[3]) ;
            sDbPw = "" ;
            sDbType = params.get(sParams[4]) ;
            sDbDriver = params.get(sParams[5]) ;
            Class.forName( sDbDriver );
        } catch (SAXException ex) {
            Logger.getLogger(OntologyPersistenceImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1) ;
        } 
    }

    @Override
    public void load() {
        loadMaker() ;
    }

    private OntModel loadMaker() {
         if (isSReload()) {

            // we pass cleanDB=true to clear out existing models
            // NOTE: this will remove ALL Jena models from the named persistent store, so
            // use with care if you have existing data stored
            maker = getRDBMaker( sDbURL, sDbUser, sDbPw, sDbType, true ) ;

            // now load the source data into the newly cleaned db
            loadDB( maker, sSource );
            sReload = false ;
        }

        // now we list the classes in the database, to show that the persistence worked
        maker = getRDBMaker( sDbURL, sDbUser, sDbPw, sDbType, false );

        Model base = maker.createModel( sSource, false );

        m = ModelFactory.createOntologyModel( getModelSpec( maker ), base );
        return m ;
    }

    private void loadDB( ModelMaker maker, String source ) {
        // use the model maker to get the base model as a persistent model
        // strict=false, so we get an existing model by that name if it exists
        // or create a new one
        Model base = maker.createModel( source, false );

        // now we plug that base model into an ontology model that also uses
        // the given model maker to create storage for imported models
        m = ModelFactory.createOntologyModel( getModelSpec( maker ), base );

        InputStream in = FileManager.get().open( source );
        if (in == null) {
            throw new IllegalArgumentException(
                                         "File: " + source + " not found");
        }

        // now load the source document, which will also load any imports
        m.read( in,  "" );
    }

    private ModelMaker getRDBMaker( String dbURL, String dbUser, String dbPw, String dbType, boolean cleanDB ) {
        try {
            // Create database connection
            IDBConnection conn  = new DBConnection( dbURL, dbUser, dbPw, dbType );

            // do we need to clean the database?
            if (cleanDB) {
                conn.cleanDB();
            }

            // Create a model maker object
            return ModelFactory.createModelRDBMaker( conn );
        }
        catch (Exception e) {
            e.fillInStackTrace() ;
            System.exit( 1 );
        }

        return null;
    }

    public OntModelSpec getModelSpec( ModelMaker maker ) {
        // create a spec for the new ont model that will use no inference over models
        // made by the given maker (which is where we get the persistent models from)
        OntModelSpec spec = new OntModelSpec( OntModelSpec.OWL_MEM );
        spec.setImportModelMaker( maker );

        return spec;
    }

    @Override
    public OntModel getModel() {
        return loadMaker() ;
    }

    /**
     * @return the sReload
     */
    public boolean isSReload() {
        return sReload;
    }

    /**
     * @param sReload 
     */
    @Override
    public void setSReload(boolean sReload) {
        this.sReload = sReload;
    }

    @Override
    public void closeCon() {
        m = null ;
        this.maker.close();
    }

    @Override
    public void reopenCon() {
        /*this.maker.openModel(s_source, s_reload) ;
        Model base = maker.createModel( s_source, false );
        m = ModelFactory.createOntologyModel( getModelSpec( maker ), base );*/
        loadMaker();
    }
}
