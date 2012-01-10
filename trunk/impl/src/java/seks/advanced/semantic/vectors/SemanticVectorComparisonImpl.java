package seks.advanced.semantic.vectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.database.DatabaseInteraction;
import seks.basic.database.DatabaseInteractionImpl;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 *Implementation class for interface SemanticVectorComparison
 * 
 * @author Paulo Figueiras
 */
public class SemanticVectorComparisonImpl implements SemanticVectorComparison {
    
    private DatabaseInteractionImpl dii ;
    private CalculusToolsImpl cti ;
    
    /**
     * Class constructor.
     */
    public SemanticVectorComparisonImpl () {}
    
    /**
     * Infers on the concepts shared between two semantic vectors.
     * 
     * @param semanticVector1   The document's semantic vector
     * @param semanticVector2   The query's semantic vector
     * 
     * @return                  The list of shared concepts, in the form of a 
     *                          {@link ArrayList} object
     */
    @Override
    public ArrayList<String> getSharedConcepts(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2) {
        ArrayList<String> sharedConcepts = new ArrayList<String>() ;
        Iterator iter1 = semanticVector1.keySet().iterator() ;
        
        while (iter1.hasNext()) {
            String concept1 = (String) iter1.next() ;
            Iterator iter2 = semanticVector2.keySet().iterator() ;
            while (iter2.hasNext()) {
                String concept2 = (String) iter2.next() ;
                if (concept1.equals(concept2)) {
                    sharedConcepts.add(concept2) ;
                    break ;
                }
            }
        }
        
        return sharedConcepts ;
    }
    
    /**
     * Compares two semantic vectors, through the application of the Euclidean 
     * Distance Algorithm.
     * 
     * @param semanticVector1   The document's semantic vector
     * @param semanticVector2   The query's semantic vector
     * @param sharedConcepts    The list of shared concepts between the two 
     *                          vectors
     *  
     * @return                  The percentage of likeliness between the 
     *                          document and the query, in the form of a 
     *                          {@link DocumentResult} object
     * 
     * @see #getSharedConcepts(java.util.HashMap, java.util.HashMap) 
     * @see DocumentResult
     * @see CalculusTools
     * @see CalculusTools#euclidianDistanceAlgorithm(double, double, double) 
     */
    @Override
    public DocumentResult compareSemanticVectors(HashMap<String, SemanticWeight> semanticVector1, HashMap<String, SemanticWeight> semanticVector2, ArrayList<String> sharedConcepts) {
        double sharedWeightsSum = 0.0 ;
        double vector1WeightsSum = 0.0 ;
        double vector2WeightsSum = 0.0 ;
        DocumentResult docResult = new DocumentResult();
        CalculusTools ct = getCti() ;
        String documentURI = semanticVector1.get(semanticVector1.keySet().iterator().next()).getIdDocument() ;
        
        if (sharedConcepts.isEmpty()) {
            docResult.setIdDocument(documentURI) ;
            docResult.setRelevancePercentage(0) ;
        }
        else {
            Iterator iter = sharedConcepts.iterator() ;
            while (iter.hasNext()) {
                String concept = (String) iter.next() ;
                double weight1 = semanticVector1.get(concept).getWeight() ;
                double weight2 = semanticVector2.get(concept).getWeight() ;
                
                sharedWeightsSum += weight1*weight2 ;
            }
            
            iter = semanticVector1.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector1.get((String) iter.next()).getWeight() ;
                vector1WeightsSum += weight*weight ;
            }
            
            iter = semanticVector2.keySet().iterator() ;
            while (iter.hasNext()) {
                double weight = semanticVector2.get((String) iter.next()).getWeight() ;
                vector2WeightsSum += weight*weight ;
            }
            
            int result = ct.euclidianDistanceAlgorithm(sharedWeightsSum, vector1WeightsSum, vector2WeightsSum) ;
            docResult.setIdDocument(documentURI) ;
            docResult.setRelevancePercentage(result) ;
        }
        return docResult ;
    }
    
    /**
     * Retrieves from the database the set of {@link SemanticWeight} instances 
     * which have the foreign key given by the input parameter.
     * 
     * @param documentID    An unique document's URI
     * 
     * @return              A document's semantic vector, in the form of an 
     *                      {@link HashMap} object, with the most relevant 
     *                      concepts as the set of keys, and the {@link SemanticWeight}
     *                      objects as values
     * 
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see SemanticWeight
     * @see HashMap
     * @see Connection
     * @see ResultSet
     */
    @Override
    public HashMap<String, SemanticWeight> getSemanticVectorByDocumentID(String documentID) {
        DatabaseInteraction di = getDii() ;
        HashMap<String, SemanticWeight> semanticVector = new HashMap<String, SemanticWeight>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        try {
            ResultSet params = di.callProcedure(con, "svdb.getSemanticWeightsWithDocURI(\"" + documentID + "\")") ;
            while (params.next()) { 
                SemanticWeight sw = new SemanticWeight(documentID, params.getString("parentClass"), params.getString("concept"), params.getDouble("weight")) ;
                semanticVector.put(sw.getConcept(), sw) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemanticVectorComparisonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        di.closeConnection(con) ;
        return semanticVector ;
    }
    
    /**
     * Retrieves the URI's of all documents comprised in the system's document 
     * repository.
     * 
     * @return  The list of URI's in the form of an {@link ArrayList} object
     * 
     * @see DatabaseInteraction
     * @see DatabaseInteraction#openConnection(java.lang.String) 
     * @see DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see ArrayList
     */
    @Override
    public ArrayList<String> getDocumentURIs() {
        DatabaseInteraction di = getDii() ;
        ArrayList<String> URIs = new ArrayList<String>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        try {
            ResultSet rs = di.callProcedure(con, "svdb.getDocumentURIs") ;
            while(rs.next()) {
                URIs.add(rs.getString("idDocument")) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemanticVectorComparisonImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return URIs ;
    }
    
    /**
     * Sorts the documents which form the response to a specific query, 
     * according to their relevance to resolve the query.
     * 
     * @param documentResults   The list of {@link DocumentResult} objects to be 
     *                          sorted
     * @return                  The list of sorted {@link DocumentResult} 
     *                          objects
     * 
     * @see DocumentResult
     * @see ArrayList
     */
    @Override
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) {
        ArrayList<DocumentResult> sortedResults = new ArrayList<DocumentResult>() ;
        Iterator iter = documentResults.iterator() ;
        while (iter.hasNext()) {
            DocumentResult dr = (DocumentResult) iter.next() ;
            if (!(dr.getRelevancePercentage() == 0)) {
                if (sortedResults.isEmpty()) {
                    sortedResults.add(dr) ;
                }
                else {
                    int relevance = dr.getRelevancePercentage() ;
                    for (int i = 0; i < sortedResults.size(); i++) {
                        DocumentResult current = sortedResults.get(i) ;
                        int currRelevance = current.getRelevancePercentage() ;
                        if (relevance >= currRelevance) {
                            sortedResults.add(i, dr) ;
                            break ;
                        }
                        else if (relevance < currRelevance && i== sortedResults.size() - 1) {
                            sortedResults.add(sortedResults.size(), dr) ;
                            break ;
                        }
                        else continue ;
                    }

                }
            }
        }
        return sortedResults ;
    }

    /**
     * @return the dii
     */
    public DatabaseInteractionImpl getDii() {
        return new DatabaseInteractionImpl() ;
    }

    /**
     * @param dii the dii to set
     */
    public void setDii(DatabaseInteractionImpl dii) {
        this.dii = dii;
    }

    /**
     * @return the cti
     */
    public CalculusToolsImpl getCti() {
        return new CalculusToolsImpl() ;
    }

    /**
     * @param cti the cti to set
     */
    public void setCti(CalculusToolsImpl cti) {
        this.cti = cti;
    }
}
