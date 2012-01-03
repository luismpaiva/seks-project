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

    private HashMap<String, String> params = new HashMap<String, String>();
    private String dbUrl, dbUser, dbPasswd, dbDriver;
    private String configFilePath = "../../";
    private String lportalConfigFile = "lportalConfig.xml";
    private String sParams[] = {"db_url", "db_user", "db_passwd", "db_driver"};

    public DatabaseInteractionImpl() {
        loadConfigParams(this.lportalConfigFile) ;
    }

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
            dbPasswd = params.get(sParams[2]);
            dbDriver = params.get(sParams[3]);

        } catch (MissingParamException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    @Override
    public ResultSet callProcedure(Connection con, String procedure) {
        try {
            CallableStatement statement = con.prepareCall("call " + procedure);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
