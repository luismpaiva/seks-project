/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.database;

import java.sql.*;


/**
 *
 * @author Paulo Figueiras
 */
public class DatabaseInteraction {
    
    public DatabaseInteraction() {}

    public ResultSet callProcedure(Connection con, String procedure) throws SQLException {
        CallableStatement prepareCall = null;
        try {
            prepareCall = con.prepareCall("call " + procedure);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prepareCall.getResultSet();
    }
    
    public int numDocumentsByConcept(String concept) {
        return 1 ;
    }
    
    
}
