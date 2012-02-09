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
import seks.basic.pojos.SemanticWeight;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.ontology.OntologyPersistenceImpl;

/**
 * Implementation class of interface {@link KeywordBasedSVCreation}
 * 
 * @author Paulo Figueiras
 * 
 * @see KeywordBasedSVCreation
 */

public class KeywordBasedSVCreationImpl implements KeywordBasedSVCreation {
    private OntologyPersistenceImpl opi ;
    private DatabaseInteractionImpl dii ;
    private OntologyInteractionImpl oii ;
    private CalculusToolsImpl cti ;
    
    /**
     * Class constructor
     */
    public KeywordBasedSVCreationImpl() {}
    
    /**
     * Retrieves all statistical weights from database belonging to the document
     * that has the unique URI given by the input parameter.
     * 
     * @param documentURI   The unique URI for the document
     * 
     * @return              The document's statistical vector as a {@link java.util.HashMap} 
     *                      object, comprising a set of keywords as keys and 
     *                      their respective weights as values
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     * @see java.util.HashMap
     */
    @Override
    public HashMap<String, Double> getStatisticalVectorByDocumentURI(String documentURI) {
        try {
            DatabaseInteraction di = getDii() ;
            Connection con = di.openConnection("svdbConfig.xml") ;
            ResultSet rs = di.callProcedure(con, "svdb.getStatisticWeightsWithDocID('" + documentURI + "')") ;
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
    
    /**
     * Searches the ontology and retrieves ontological concepts that have, as 
     * value for the ontological property "has_Keyword", the keywords that 
     * define a document's statistic vector.
     * 
     * @param statVector    The document's statistic vector in the form of the
     *                      {@link HashMap} object given as input 
     *                      parameter.
     * 
     * @return              A {@link HashMap} object, with the 
     *                      ontological concepts as the set of keys and
     *                      {@link ArrayList} objects with the 
     *                      concepts' matched keywords as values
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.ontology.OntologyInteraction
     * @see seks.basic.ontology.OntologyInteraction#getSubjectsFromTriple(java.lang.String, java.lang.String) 
     * @see HashMap
     * @see ArrayList
     */
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
            if (!concepts.isEmpty()) {
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
        }
        return conceptsAndKeywords ;
    }
    
    /**
     * Calculates the total weight (relevance) assigned to each ontological 
     * concept, through the sum of the weights of the document's keywords 
     * matched to each concept.
     * 
     * @param statVector            The document's statistic vector in the form 
     *                              of the {@link HashMap} object given as input
     *                              parameter.
     * 
     * @param conceptsAndKeywords   A {@link HashMap} object, with the 
     *                              ontological concepts as the set of keys and
     *                              {@link ArrayList} objects with the concepts' 
     *                              matched keywords as values
     * 
     * @return                      The semantic vector's first implementation, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public HashMap<String, Double> getConceptsTotalWeights(HashMap<String, Double> statVector, HashMap<String, ArrayList<String>> conceptsAndKeywords) {
        Set concepts = conceptsAndKeywords.keySet() ;
        Iterator iter = concepts.iterator() ;
        ArrayList<String> keywords = new ArrayList<String>() ;
        HashMap<String, Double> conceptsAndWeights = new HashMap<String, Double>() ;
        
        HashMap<String, Integer> conceptsPerKeyword = new HashMap<String, Integer>() ;
        
        if (!conceptsAndKeywords.isEmpty()) {
            
            while (iter.hasNext()) {
                String concept = (String) iter.next() ;
                keywords = conceptsAndKeywords.get(concept) ;
                
                for (int i = 0; i < keywords.size(); i++) {
                    if (conceptsPerKeyword.containsKey(keywords.get(i))) {
                        int value = conceptsPerKeyword.get(keywords.get(i)) + 1 ;
                        conceptsPerKeyword.put(keywords.get(i), value) ;
                    }
                    else {
                        conceptsPerKeyword.put(keywords.get(i), 1) ;
                    }
                }
            }
            
            iter = conceptsPerKeyword.keySet().iterator() ;
            
            while (iter.hasNext()) {
                String keyword = (String) iter.next() ;
                if (conceptsPerKeyword.get(keyword) > 1) {
                    double conceptsNum = (double)conceptsPerKeyword.get(keyword) ;
                    double value = statVector.get(keyword) ;
                    statVector.put(keyword, (value/conceptsNum)) ;
                }
            }
            
            iter = concepts.iterator() ;
            
            while (iter.hasNext()) {
                String concept = (String) iter.next() ;
                Double totalWeight = 0.0 ;
                keywords = (ArrayList<String>) conceptsAndKeywords.get(concept) ;
                
                for (int i = 0; i < keywords.size(); i++) {
                    totalWeight += (Double) statVector.get(keywords.get(i)) ;
                }
                conceptsAndWeights.put(concept, totalWeight) ;
            }
        }
        return conceptsAndWeights ;
    }
    
    /**
     * Creates a hierarchical array of concepts, sorting them by their relevance 
     * (weight) on a specific document, from the most relevant concept to the 
     * most insignificant concept to the document's knowledge representation.
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, 
     *                              in the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @return                      The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public ArrayList<String> sortConceptsByRelevance(HashMap<String, Double> conceptsAndWeights) {
        ArrayList<String> sortedConcepts = new ArrayList<String>() ;
        if (!conceptsAndWeights.isEmpty()) {
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
        }
        return sortedConcepts ;
    }
    
    /**
     * Generates the document's keyword-based semantic vector, through the 
     * application of the {@link seks.basic.calculus.CalculusTools#tfIdfAlgorithm(int, int, double, double)}.
     * <p>
     * This generated semantic vector of a document is based not only on the 
     * most relevant concepts of that document, but also on the importance that 
     * those concepts have on all documents comprised by the database.
     * 
     * @param documentURI           The unique URI for the document
     * 
     * @param conceptsAndWeights    The semantic vector's first prototype, in 
     *                              the form of a {@link HashMap} object with 
     *                              the ontological concepts as the set of keys 
     *                              and the calculated total weights for each 
     *                              concept as values
     * 
     * @param sortedConcepts        The hierarchical array of concepts, in the 
     *                              form of a {@link ArrayList} object
     * 
     * @return                      The document's semantic vector, in the form 
     *                              of a {@link HashMap} object, with the 
     *                              relevant concepts as the set of keys, and a 
     *                              {@link seks.basic.pojos.SemanticWeight} object with the 
     *                              concept's weight, ontological parent class, 
     *                              and other data
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.pojos.SemanticWeight
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#tfIdfAlgorithm(int, int, double, double)
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see seks.basic.ontology.OntologyInteraction
     * @see seks.basic.ontology.OntologyInteraction#getIndividualDirectParentClass(java.lang.String) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     * @see HashMap
     * @see ArrayList
     */
    @Override
    public HashMap<String, SemanticWeight> createKeywordBasedSemanticVector (String documentURI, HashMap<String, Double> conceptsAndWeights, ArrayList<String> sortedConcepts) {
        DatabaseInteraction di = getDii() ;
        OntologyInteraction oi = getOii() ;
        CalculusTools ct = getCti() ;
        
        HashMap<String, SemanticWeight> semanticVector = new HashMap<String, SemanticWeight>() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        String mostRelevantConcept = sortedConcepts.get(0) ;
        Double maxWeight = (Double) conceptsAndWeights.get(mostRelevantConcept) ; 
        int totalDocNum = this.getTotalDocumentsNumber(con) ;
        if (!documentURI.equals("query"))
        {
            totalDocNum++ ; // IMPORTANTE: para já tem que se somar o documento actual porque ele ainda não está na BD. Mais tarde terá de se tirar este 1 daqui
        }
        
        for (int i = 0; i < sortedConcepts.size(); i++) {
            
            String concept = sortedConcepts.get(i) ;
            Double weight = (Double) conceptsAndWeights.get(concept) ;
            Double result = 0.0 ;
            int docNumWithConcept = this.getDocumentsNumberWithConcept(concept, con) ; // We have to take into accounnt the current document (+1)
            if (!documentURI.equals("query")){
                docNumWithConcept++ ;
            }
            
            if (docNumWithConcept == 0 && documentURI.equals("query")) {
                result = 0.0 ;
            }
            else {
                result = ct.tfIdfAlgorithm(totalDocNum, docNumWithConcept, weight, maxWeight) ;
            }
            String parentClass = oi.getDirectParentClass(concept) ;
            SemanticWeight sw = new SemanticWeight(documentURI, parentClass, concept, result) ;
            semanticVector.put(concept, sw) ;
        }
        di.closeConnection(con) ;
        return semanticVector;
    }
    
    /**
     * Normalizes the weights of a document's semantic vector to values between 
     * 0 (zero) and 1(one).
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @return                  The document's semantic vector with the weights 
     *                          normalized
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.pojos.SemanticWeight
     * @see seks.basic.calculus.CalculusTools
     * @see seks.basic.calculus.CalculusTools#normalization(double, double) 
     */
    @Override
    public HashMap<String, SemanticWeight> semanticVectorNormalization(HashMap<String, SemanticWeight> semanticVector) {
        Iterator itr = semanticVector.keySet().iterator() ;
        HashMap<String, SemanticWeight> normalizedSemanticVector = new HashMap<String, SemanticWeight>() ;
        
        CalculusTools ct = getCti() ;
        double weightsTotal = 0 ;
        
        while(itr.hasNext()) {
            String concept = (String) itr.next() ;
            SemanticWeight sw = semanticVector.get(concept) ;
            weightsTotal += sw.getWeight() ;
        }
        
        itr = semanticVector.keySet().iterator() ;
        
        while (itr.hasNext()) {
            String concept = (String) itr.next() ;
            SemanticWeight sw = semanticVector.get(concept) ;
            double normalizedWeight = ct.normalization(weightsTotal, sw.getWeight()) ;
            sw.setWeight(normalizedWeight) ;
            normalizedSemanticVector.put(concept, sw) ;
        }
        
        return normalizedSemanticVector ;
    }
    
    /**
     * Creates the required instances of SemanticWeight instances on the database, with 
     * the document's URI as foreign key
     * 
     * @param semanticVector    The document's semantic vector, in the form of a
     *                          {@link HashMap} object, with the relevant 
     *                          concepts as the set of keys, and a {@link seks.basic.pojos.SemanticWeight} 
     *                          object with the concept's weight, ontological 
     *                          parent class, and other data
     * 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#semanticVectorNormalization(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#createKeywordBasedSemanticVector(java.lang.String, java.util.HashMap, java.util.ArrayList) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#sortConceptsByRelevance(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsTotalWeights(java.util.HashMap, java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getConceptsFromKeywords(java.util.HashMap) 
     * @see seks.advanced.semantic.vectors.KeywordBasedSVCreation#getStatisticalVectorByDocumentURI(java.lang.String)
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     */
    @Override
    public void storeSemanticVector(HashMap<String, SemanticWeight> semanticVector) {
        DatabaseInteraction di = getDii() ;
        Connection con = di.openConnection("svdbConfig.xml") ;
        Iterator iter = semanticVector.keySet().iterator() ;
        while (iter.hasNext()) {
            SemanticWeight sw = semanticVector.get((String) iter.next()) ;
            di.callProcedure(con, "svdb.insertSemanticWeight('" + sw.getParentClass() + "','" + sw.getConcept() + "'," + sw.getWeight() + ",'" + sw.getIdDocument() + "')") ;
        }
        di.closeConnection(con) ;
    }
    
    
    /**
     * Retrieves the total number of documents contained within the database
     * 
     * @param con   A {@link java.sql.Connection} object, that manages the database 
     *              connection
     * 
     * @return      The total number of documents in the database
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
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
    
    
    /**
     * Retrieves the number of documents contained within the database which 
     * have the concept within their semantic vectors.
     * 
     * @param concept   The concept
     * 
     * @param con       A {@link java.sql.Connection} object, that manages the database 
     *                  connection
     * 
     * @return          The total number of documents in the database
     * 
     * @see seks.basic.database.DatabaseInteraction
     * @see seks.basic.database.DatabaseInteraction#openConnection(java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#callProcedure(java.sql.Connection, java.lang.String) 
     * @see seks.basic.database.DatabaseInteraction#closeConnection(java.sql.Connection) 
     * @see java.sql.Connection
     * @see java.sql.ResultSet
     */
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
