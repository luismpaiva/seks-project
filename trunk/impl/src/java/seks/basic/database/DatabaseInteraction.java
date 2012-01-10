package seks.basic.database;

import java.sql.*;

/**
 * Comprises all functions and algorithms that manage database interaction, such 
 * as managing connections to the database and calling database routines.
 * 
 * @author Paulo Figueiras
 */
public interface DatabaseInteraction {
    
    
    /***    Database Connection     ***/
    
    /**
     * Establishes a new connection with the database.
     * 
     * @param configFileName    The name and path of the XML configuration file
     * @return                  A {@link Connection} object
     * 
     * @see #loadConfigParams(java.lang.String) 
     * @see Connection
     */
    public Connection openConnection(String configFileName) ;
    
    /**
     * Kills the input {@link Connection} link object.
     * 
     * @param con   A {@link Connection} object
     * 
     * @see Connection
     */
    public void closeConnection(Connection con) ;
    
    
    /***    Auxiliary Functions     ***/
    
    /**
     * Executes a MySQL database routine/procedure with name given by the input 
     * parameter <code>procedure</code>.
     * 
     * @param con       A {@link Connection} object to manage the link to the 
     *                  database
     * @param procedure The name of the routine
     * 
     * @return          The result of the routine execution, in the form of a
     *                  {@link ResultSet} object
     * 
     * @see Connection
     * @see ResultSet
     */
    public ResultSet callProcedure(Connection con, String procedure) ;
    
}
