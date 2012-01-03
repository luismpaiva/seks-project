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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import seks.basic.calculus.CalculusTools;
import seks.basic.calculus.CalculusToolsImpl;
import seks.basic.database.DatabaseInteraction;
import seks.basic.database.DatabaseInteractionImpl;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.ontology.OntologyPersistenceImpl;

/**
 *
 * @author Paulo Figueiras
 */

public class KeywordBasedSVCreationImpl implements KeywordBasedSVCreation {
    private OntologyPersistenceImpl opi ;
    private DatabaseInteractionImpl dii ;
    private OntologyInteractionImpl oii ;
    private CalculusToolsImpl cti ;
    
    public KeywordBasedSVCreationImpl() {}
    
    @Override
    public HashMap<String, Double> getStatisticalVectorByDocumentURI(String documentURI) {
        try {
            DatabaseInteraction di = getDii() ;
            Connection con = di.openConnection("svdbConfig.xml") ;
            ResultSet rs = di.callProcedure(con, "svdb.getStatisticWeightsWithDocURI('" + documentURI + "')") ;
            HashMap<String, Double> statVector = new HashMap<String, Double>() ;
            
            while (rs.next()) {
                statVector.put(rs.getString("keyword"), rs.getDouble("weight")) ;
            }
            rs.close() ;
            di.closeConnection(con) ;
            return statVector ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
    
    @Override
    public HashMap<String, ArrayList<String>> getConceptsFromKeywords(HashMap<String, Double> statVector) {
        OntologyInteraction oi = getOii() ;
        Set set = statVector.keySet() ;
        Iterator iter = set.iterator() ;
        ArrayList<String> keywords ;
        HashMap conceptsAndKeywords = new HashMap<String, ArrayList<String>>() ;
        
        while(iter.hasNext()) {
            String keyword = (String) iter.next() ;
            ArrayList<String> concepts = oi.getSubjectsFromTriple(keyword, "has_Keyword") ;
            for(int i = 0; i < concepts.size(); i++) {
                if (conceptsAndKeywords.containsKey(concepts.get(i))) {
                    keywords = (ArrayList<String>) conceptsAndKeywords.get(concepts.get(i)) ;
                    keywords.add(keyword);
                    conceptsAndKeywords.put(concepts.get(i), keywords) ;
                }
                else {
                    keywords = new ArrayList<String>() ;
                    keywords.add(keyword);
                    conceptsAndKeywords.put(concepts.get(i), keywords) ;
                }
            }
        }
        return conceptsAndKeywords ;
    }
    
    @Override
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, Double> statVector, HashMap<String, ArrayList<String>> conceptsAndKeywords) {
        Set concepts = conceptsAndKeywords.keySet() ;
        Iterator iter = concepts.iterator() ;
        ArrayList<String> keywords = new ArrayList<String>() ;
        HashMap<String, Double> conceptsAndWeights = new HashMap<String, Double>() ;
        
        while (iter.hasNext()) {
            String concept = (String) iter.next() ;
            Double totalWeight = 0.0 ;
            keywords = (ArrayList<String>) conceptsAndKeywords.get(concept) ;
            for (int i = 0; i < keywords.size(); i++) {
                totalWeight += (Double) statVector.get(keywords.get(i)) ;
            }
            conceptsAndWeights.put(concept, totalWeight) ;
        }
        return conceptsAndWeights ;
    }
    
    @Override
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) {
        ArrayList<String> sortedConcepts = new ArrayList<String>() ;
        Iterator iter = conceptsAndWeights.keySet().iterator() ;
        
        while(iter.hasNext()) {
            String concept = (String) iter.next() ;
            if (sortedConcepts.isEmpty()) {
                sortedConcepts.add(concept) ;
            }
            else {
                Double weight = conceptsAndWeights.get(concept) ;
                for (int i = 0; i < sortedConcepts.size(); i++) {
                    String current = sortedConcepts.get(i) ;
                    Double currentWeight = conceptsAndWeights.get(current) ;
                    if (weight >= currentWeight) {
                        sortedConcepts.add(i, concept);
                        break ;
                    }
                    else if (weight < currentWeight && i == sortedConcepts.size() - 1) {
                        sortedConcepts.add(sortedConcepts.size(), concept);
                        break ;
                    }
                    else continue ;
                }
            }
        }
        return sortedConcepts ;
    }
    
    @Override
    public HashMap<String, Double> createKeywordBasedSemanticVector (String documentURI, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) {
        DatabaseInteraction di = getDii() ;
        OntologyInteraction oi = getOii() ;
        CalculusTools ct = getCti() ;
        
        HashMap<String, Double> semanticVector = new HashMap<String, Double>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        String mostRelevantConcept = sortedConcepts.get(0) ;
        Double maxWeight = (Double) conceptsAndWeights.get(mostRelevantConcept) ; 
        int totalDocNum = this.getTotalDocumentsNumber(con) ;
        
        for (int i = 0; i < sortedConcepts.size(); i++) {
            String concept = sortedConcepts.get(i) ;
            Double weight = (Double) conceptsAndWeights.get(concept) ;
            int docNumWithConcept = this.getDocumentsNumberWithConcept(concept, con) + 1 ; // We have to take into accounnt the current document (+1)
            Double result = ct.tfIdfAlgorithm(totalDocNum, docNumWithConcept, weight, maxWeight) ;
            String parentClass = oi.getIndividualDirectParentClass(concept) ;
            try {
                di.callProcedure(con, "svdb.insertSemanticWeight('" + parentClass + "','" + concept + "'," + result + ",'" + documentURI + "')") ;
                semanticVector.put(concept, result) ;
                
            } catch (SQLException ex) {
                Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        di.closeConnection(con) ;
        return semanticVector;
    }
    
    private int getTotalDocumentsNumber(Connection con) {
        try {
            DatabaseInteraction di = getDii() ;
            ResultSet rs = di.callProcedure(con, "svdb.getTotalDocumentNum") ;
            rs.next() ;
            int result = rs.getInt("nDocument") ;
            rs.close() ;
            return result ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }
    
    private int getDocumentsNumberWithConcept(String concept, Connection con) {
        try {
            DatabaseInteraction di = getDii() ;
            ResultSet rs = di.callProcedure(con, "svdb.getDocumentNumWithConcept('" + concept + "')") ;
            rs.next() ;
            int result = rs.getInt("nDocument") ;
            rs.close() ;
            return result ;
        } catch (SQLException ex) {
            Logger.getLogger(KeywordBasedSVCreationImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
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
     * @return the oii
     */
    public OntologyInteractionImpl getOii() {
        return new OntologyInteractionImpl() ;
    }

    /**
     * @param oii the oii to set
     */
    public void setOii(OntologyInteractionImpl oii) {
        this.oii = oii;
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
