/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.basic.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Paulo Figueiras
 */
public interface DatabaseInteraction {
    
    /***    Database Connection     ***//**/
    
    public Connection openConnection(String configFileName) ;
    
    public void closeConnection(Connection con) ;
    
    /***    Auxiliary Functions     ***//**/
    
    public ResultSet callProcedure(Connection con, String procedure) throws SQLException ;
    
}
