/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Paulo Figueiras
 */
public class SemanticVectorComparisonImpl implements SemanticVectorComparison {
    
    private DatabaseInteractionImpl dii ;
    private CalculusToolsImpl cti ;
    
    public SemanticVectorComparisonImpl () {}
    
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
            docResult.setRelevancePercentage(0.0) ;
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
            
            double result = Math.abs(ct.euclidianDistanceAlgorithm(sharedWeightsSum, vector1WeightsSum, vector2WeightsSum)) ;
            docResult.setIdDocument(documentURI) ;
            docResult.setRelevancePercentage(result*100) ;
        }
        return docResult ;
    }
    
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
    
    @Override
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) {
        ArrayList<DocumentResult> sortedResults = new ArrayList<DocumentResult>() ;
        Iterator iter = documentResults.iterator() ;
        while (iter.hasNext()) {
            DocumentResult dr = (DocumentResult) iter.next() ;
            if (!dr.getRelevancePercentage().equals(0.0)) {
                if (sortedResults.isEmpty()) {
                    sortedResults.add(dr) ;
                }
                else {
                    double relevance = dr.getRelevancePercentage() ;
                    for (int i = 0; i < sortedResults.size(); i++) {
                        DocumentResult current = sortedResults.get(i) ;
                        double currRelevance = current.getRelevancePercentage() ;
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
