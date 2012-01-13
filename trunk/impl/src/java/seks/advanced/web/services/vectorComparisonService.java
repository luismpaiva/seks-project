/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.advanced.web.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.HashMap ;
import java.util.ArrayList ;
import seks.advanced.semantic.vectors.SemanticVectorComparison;
import seks.advanced.semantic.vectors.SemanticVectorComparisonImpl;
import seks.basic.pojos.SemanticWeight ;
import seks.basic.pojos.DocumentResult ;
import seks.basic.serialization.SerializationTools;
import seks.basic.serialization.SerializationToolsImpl;

/**
 *
 * @author Paulo Figueiras
 */
@WebService(serviceName = "vectorComparisonService")
public class vectorComparisonService {
    
    /**
     * Infers on the concepts shared between two semantic vectors.
     * 
     * @param documentSemanticVector    The document's semantic vector
     * @param querySemanticVector       The query's semantic vector
     * 
     * @return                          The list of shared concepts, in the form of a 
     *                                  {@link java.util.ArrayList} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#getSharedConcepts(java.util.HashMap, java.util.HashMap) 
     */
    @WebMethod(operationName = "getSharedConcepts")
    public ArrayList<String> getSharedConcepts(@WebParam(name = "documentSemanticVector") String documentSemanticVector, @WebParam(name = "querySemanticVector") String querySemanticVector) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, SemanticWeight> documentSV = st.Deserialize(documentSemanticVector) ;
        HashMap<String, SemanticWeight> querySV = st.Deserialize(querySemanticVector) ;
        SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
        return svc.getSharedConcepts(documentSV, querySV) ;
    }

    /**
     * Compares two semantic vectors, through the application of the Euclidean 
     * Distance Algorithm.
     * 
     * @param documentSemanticVector    The document's semantic vector
     * @param querySemanticVector       The query's semantic vector
     * @param sharedConcepts            The list of shared concepts between the two 
     *                                  vectors
     *  
     * @return                          The percentage of likeliness between the 
     *                                  document and the query, in the form of a 
     *                                  {@link DocumentResult} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#compareSemanticVectors(java.util.HashMap, java.util.HashMap, java.util.ArrayList) 
     */
    @WebMethod(operationName = "compareSemanticVectors")
    public DocumentResult compareSemanticVectors(@WebParam(name = "documentSemanticVector") String documentSemanticVector, @WebParam(name = "querySemanticVector") String querySemanticVector, @WebParam(name = "sharedConcepts") ArrayList<String> sharedConcepts) {
        SerializationTools st = new SerializationToolsImpl() ;
        HashMap<String, SemanticWeight> documentSV = st.Deserialize(documentSemanticVector) ;
        HashMap<String, SemanticWeight> querySV = st.Deserialize(querySemanticVector) ;
        SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
        return svc.compareSemanticVectors(documentSV, querySV, sharedConcepts) ;
    }

    /**
     * Retrieves from the database the set of {@link seks.basic.pojos.SemanticWeight} instances 
     * which have the foreign key given by the input parameter.
     * 
     * @param documentId    An unique document's URI
     * 
     * @return              A document's semantic vector, in the form of an 
     *                      {@link java.util.HashMap} object, with the most relevant 
     *                      concepts as the set of keys, and the {@link seks.basic.pojos.SemanticWeight}
     *                      objects as values, serialized as a {@link java.lang.String} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#getSemanticVectorByDocumentID(java.lang.String) 
     */
    @WebMethod(operationName = "getSemanticVectorByDocumentId")
    public String getSemanticVectorByDocumentId(@WebParam(name = "documentId") String documentId) {
        SerializationTools st = new SerializationToolsImpl() ;
        SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
        HashMap<String, SemanticWeight> map = svc.getSemanticVectorByDocumentID(documentId) ;
        String serialized = st.Serialize(map) ;
        return serialized ;
    }

    /**
     * Retrieves the URI's of all documents comprised in the system's document 
     * repository.
     * 
     * @return  The list of URI's in the form of an {@link java.util.ArrayList} object
     * 
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison
     * @see seks.advanced.semantic.vectors.SemanticVectorComparison#getDocumentURIs() 
     */
    @WebMethod(operationName = "getDocumentIds")
    public ArrayList<String> getDocumentIds() {
        SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
        return svc.getDocumentURIs() ;
    }
    
    /**
     * Sorts the documents which form the response to a specific query, 
     * according to their relevance to resolve the query.
     * 
     * @param documentResults   The list of {@link seks.basic.pojos.DocumentResult} objects to be 
     *                          sorted
     * @return                  The list of sorted {@link seks.basic.pojos.DocumentResult} 
     *                          objects
     * 
     * @see seks.basic.pojos.DocumentResult
     * @see java.util.ArrayList
     */
    @WebMethod(operationName = "sortDocumentResultsByRelevance")
    public ArrayList<DocumentResult> sortDocumentResultsByRelevance(@WebParam(name = "documentResults")ArrayList<DocumentResult> documentResults) {
        SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
        return svc.sortDocumentResultsByRelevance(documentResults) ;
    }
}
