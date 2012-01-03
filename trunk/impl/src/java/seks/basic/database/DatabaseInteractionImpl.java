/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.database;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import seks.basic.exceptions.MissingParamException;


/**
 *
 * @author Paulo Figueiras
 */
public class DatabaseInteractionImpl implements DatabaseInteraction {
    
    private HashMap<String, String> params = new HashMap<String, String>() ;
    private String dbUrl, dbUser, dbPasswd, dbDriver ;
    private String configFilePath ;
    private String sParams[] = {"db_url", "db_user", "db_passwd", "db_driver"} ;
    
    public DatabaseInteractionImpl() {}
    
    /***    Database Connection     ***//**/
    
    @Override
    public Connection openConnection(String configFileName) {
        try {
            this.loadConfigParams(configFileName) ;
            Class.forName(this.dbDriver);
            String connectionUrl = this.dbUrl + "?"
                              + "user=" + this.dbUser + "&password=" + this.dbPasswd ;
            Connection con = DriverManager.getConnection(connectionUrl);
            return con ;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public void closeConnection(Connection con) {
        try {
            con.close() ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /***    Liferay Document Repository Interaction     ***//**/
    
    @Override
    public ResultSet listFolders(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("call lportal.list_folders");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet listMetadata(Connection con, String fileId) {
        try {
            CallableStatement statement = con.prepareCall("call lportal.list_tags(" + fileId + ")");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet listFiles(Connection con, String folderId) {
        try {
            CallableStatement statement = con.prepareCall("call lportal.list_files(" + folderId + ")");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    /***    Semantic Vectors Database Interaction     ***//**/
    
    @Override
    public ResultSet getDocumentNumWithConcept(Connection con, String concept) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.getDocumentNumWithConcept('" + concept + "')");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet getListNonIndexed(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.getListNonIndexed");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet getSemanticWeightsWithDocURI(Connection con, String concept) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.getSemanticWeightsWithDocURI('" + concept + "')");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet getStatisticWeightsWithDocURI(Connection con, String concept) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.getStatisticWeightsWithDocURI('" + concept + "')");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public ResultSet getTotalDocumentNum(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.getTotalDocumentNum");
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    public void insertSemanticWeight (Connection con, String parentClass, String concept, double weight, String documentURI) {
        try {
            CallableStatement statement = con.prepareCall("call svdb.insertSemanticWeight('" + parentClass + "','" + concept + "'," + weight + ",'" + documentURI + "')") ;
            statement.execute();
            return ;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /***    Auxiliary Functions     ***//**/
    
    @Override
    public ResultSet callProcedure(Connection con, String procedure) throws SQLException {
        CallableStatement prepareCall = null;
        try {
            prepareCall = con.prepareCall("call " + procedure);
        } catch (Exception e) {}
        return prepareCall.getResultSet();
    }
    
    private void loadConfigParams (String configFileName) {
        try {
            configFilePath = this.getClass().getClassLoader().getResource(configFileName).toString() ;
            DOMParser domp = new DOMParser();
            domp.parse(this.configFilePath);
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
            this.dbUrl = params.get(sParams[0]) ;
            this.dbUser = params.get(sParams[1]) ;
            //this.dbPasswd = params.get(sParams[2]) ;
            this.dbPasswd = "" ;
            this.dbDriver = params.get(sParams[3]) ;
            
        } catch (MissingParamException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseInteractionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
