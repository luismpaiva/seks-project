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
 * Implementation class for interface DatabaseInteraction
 * 
 * @author Paulo Figueiras
 */
public class DatabaseInteractionImpl implements DatabaseInteraction {

    private HashMap<String, String> params = new HashMap<String, String>();
    private String dbUrl, dbUser, dbPasswd = "", dbDriver;
    private String configFilePath = "../../";
    private String lportalConfigFile = "lportalConfig.xml";
    private String sParams[] = {"db_url", "db_user", "db_passwd", "db_driver"};

    
    /**
     * Class constructor.
     * 
     * @see #loadConfigParams(java.lang.String) 
     */
    public DatabaseInteractionImpl() {
        loadConfigParams(this.lportalConfigFile) ;
    }
    
    /**
     * Loads a XML file with the database URL, user, password and MySQL driver
     * used to oper a connection link to the database.
     * 
     * @param configFileName    The name and path of the XML configuration file
     * 
     * @see seks.basic.exceptions.MissingParamException
     * @see DOMParser
     * @see Document
     * @see Element
     */
    private void loadConfigParams(String configFileName) {
        try {
            configFilePath = this.getClass().getClassLoader().getResource(configFileName).toString() ;
            configFilePath = configFilePath.substring(0, configFilePath.indexOf(configFileName)) ;
            DOMParser domp = new DOMParser();
            domp.parse(this.configFilePath + "/" + configFileName);
            Document doc = domp.getDocument();
            Element root = doc.getDocumentElement();
            for (String param : sParams) {
                NodeList paramNode = root.getElementsByTagName(param);
                if (paramNode != null) {
                    Element el = (Element) paramNode.item(0);
                    String paramVal = el.getFirstChild().getNodeValue();
                    if (paramVal != null) {
                        params.put(param, paramVal);
                    } else {
                        throw new MissingParamException("Ficheiro de configuração incompleto.");
                    }
                }
            }
            dbUrl = params.get(sParams[0]);
            dbUser = params.get(sParams[1]);
            //dbPasswd = params.get(sParams[2]);
            dbDriver = params.get(sParams[3]);

        } catch (MissingParamException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Establishes a new connection with the database.
     * 
     * @param configFileName    The name and path of the XML configuration file
     * @return                  A {@link java.sql.Connection} object
     * 
     * @see seks.basic.database.DatabaseInteraction#loadConfigParams(java.lang.String) 
     * @see java.sql.Connection
     */
    @Override
    public Connection openConnection(String configFileName) {
        try {
            this.loadConfigParams(configFileName);
            Class.forName(this.dbDriver);
            String connectionUrl = this.dbUrl + "?"
                    + "user=" + this.dbUser + "&password=" + this.dbPasswd;
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Executes a MySQL database routine/procedure with name given by the input 
     * parameter <code>procedure</code>.
     * 
     * @param con       A {@link java.sql.Connection} object to manage the link to the 
     *                  database
     * @param procedure The name of the routine
     * 
     * @return          The result of the routine execution, in the form of a
     *                  {@link java.sql.ResultSet} object
     * 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
    @Override
    public ResultSet callProcedure(Connection con, String procedure) {
        try {
            CallableStatement statement = con.prepareCall("call " + procedure);
            if (statement.execute()) {       
            	ResultSet rs = statement.getResultSet();
            	return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Kills the input {@link java.sql.Connection} link object.
     * 
     * @param con   A {@link java.sql.Connection} object
     * 
     * @see java.sql.Connection
     */
    @Override
    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
