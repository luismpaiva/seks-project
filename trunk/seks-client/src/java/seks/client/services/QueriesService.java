/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.client.services;

import java.util.ArrayList;
import javax.xml.ws.WebServiceRef;
import seks.advanced.web.services.DocumentResult;
import seks.advanced.web.services.QuerySemanticVectorsService_Service;
import seks.advanced.web.services.VectorComparisonService_Service;

/**
 *
 * @author Paulo Figueiras
 */
public class QueriesService {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/seks-tomcat/VectorComparisonService.wsdl")
    private static VectorComparisonService_Service vectorService;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/seks-tomcat/QuerySemanticVectorsService.wsdl")
    private static QuerySemanticVectorsService_Service queryService;
    
    public QueriesService() {
        QueriesService.queryService = new seks.advanced.web.services.QuerySemanticVectorsService_Service();
        QueriesService.vectorService = new seks.advanced.web.services.VectorComparisonService_Service();
    }
    
    public ArrayList<String> getQueryKeywords(String query) {
        return (ArrayList<String>) QueriesService.getQueryKeywordsWS(query) ;
    }
    
    public String createQueryStatisticVector(ArrayList<String> queryKeywords) {
        return QueriesService.createQueryStatisticVectorWS(queryKeywords) ;
    }
    
    public String getConceptsFromKeywords(String statisticVector) {
        return QueriesService.getConceptsFromKeywordsWS(statisticVector) ;
    }
    
    public String getConceptsTotalWeights(String statisticVector, String conceptsAndKeywords){
        return QueriesService.getConceptsTotalWeightsWS(statisticVector, conceptsAndKeywords) ;
    }
    
    public String createQuerySemanticVector(String conceptsAndWeights) {
        return QueriesService.createQuerySemanticVectorWS(conceptsAndWeights) ;
    }
    
    public String getSemanticVectorByDocumentId(String documentId) {
        return QueriesService.getSemanticVectorByDocumentIdWS(documentId) ;
    }
    
    public ArrayList<String> getDocumentIds() {
        return (ArrayList<String>) QueriesService.getDocumentIdsWS() ;
    }
    
    public ArrayList<String> getSharedConcepts(String documentSemanticVector, String querySemanticVector) {
        return (ArrayList<String>) QueriesService.getSharedConceptsWS(documentSemanticVector, querySemanticVector) ;
    }
    
    public DocumentResult compareSemanticVectors(String documentSemanticVector, String querySemanticVector, ArrayList<String> sharedConcepts) {
        return QueriesService.compareSemanticVectorsWS(documentSemanticVector, querySemanticVector, sharedConcepts) ;
    }
    
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(ArrayList<DocumentResult> documentResults) {
        return (ArrayList<DocumentResult>) QueriesService.sortDocumentResultsByRelevanceWS(documentResults) ;
    }
    
    private static java.util.List<java.lang.String> getQueryKeywordsWS(java.lang.String query) {
        seks.advanced.web.services.QuerySemanticVectorsService port = queryService.getQuerySemanticVectorsServicePort();
        return port.getQueryKeywords(query);
    }

    private static String createQueryStatisticVectorWS(java.util.List<java.lang.String> queryKeywords) {
        seks.advanced.web.services.QuerySemanticVectorsService port = queryService.getQuerySemanticVectorsServicePort();
        return port.createQueryStatisticVector(queryKeywords);
    }

    private static String getConceptsFromKeywordsWS(java.lang.String statisticVector) {
        seks.advanced.web.services.QuerySemanticVectorsService port = queryService.getQuerySemanticVectorsServicePort();
        return port.getConceptsFromKeywords(statisticVector);
    }

    private static String getConceptsTotalWeightsWS(java.lang.String statisticVector, java.lang.String conceptsAndKeywords) {
        seks.advanced.web.services.QuerySemanticVectorsService port = queryService.getQuerySemanticVectorsServicePort();
        return port.getConceptsTotalWeights(statisticVector, conceptsAndKeywords);
    }

    private static String createQuerySemanticVectorWS(java.lang.String conceptsAndWeights) {
        seks.advanced.web.services.QuerySemanticVectorsService port = queryService.getQuerySemanticVectorsServicePort();
        return port.createQuerySemanticVector(conceptsAndWeights);
    }

    private static String getSemanticVectorByDocumentIdWS(java.lang.String documentId) {
        seks.advanced.web.services.VectorComparisonService port = vectorService.getVectorComparisonServicePort();
        return port.getSemanticVectorByDocumentId(documentId);
    }

    private static java.util.List<java.lang.String> getDocumentIdsWS() {
        seks.advanced.web.services.VectorComparisonService port = vectorService.getVectorComparisonServicePort();
        return port.getDocumentIds();
    }

    private static java.util.List<java.lang.String> getSharedConceptsWS(java.lang.String documentSemanticVector, java.lang.String querySemanticVector) {
        seks.advanced.web.services.VectorComparisonService port = vectorService.getVectorComparisonServicePort();
        return port.getSharedConcepts(documentSemanticVector, querySemanticVector);
    }

    private static DocumentResult compareSemanticVectorsWS(java.lang.String documentSemanticVector, java.lang.String querySemanticVector, java.util.List<java.lang.String> sharedConcepts) {
        seks.advanced.web.services.VectorComparisonService port = vectorService.getVectorComparisonServicePort();
        return port.compareSemanticVectors(documentSemanticVector, querySemanticVector, sharedConcepts);
    }

    private static java.util.List<seks.advanced.web.services.DocumentResult> sortDocumentResultsByRelevanceWS(java.util.List<seks.advanced.web.services.DocumentResult> documentResults) {
        seks.advanced.web.services.VectorComparisonService port = vectorService.getVectorComparisonServicePort();
        return port.sortDocumentResultsByRelevance(documentResults);
    }
}
