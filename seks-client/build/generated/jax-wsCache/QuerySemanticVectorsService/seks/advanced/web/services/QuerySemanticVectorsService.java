
package seks.advanced.web.services;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebService(name = "QuerySemanticVectorsService", targetNamespace = "http://services.web.advanced.seks/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface QuerySemanticVectorsService {


    /**
     * 
     * @param query
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getQueryKeywords", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetQueryKeywords")
    @ResponseWrapper(localName = "getQueryKeywordsResponse", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetQueryKeywordsResponse")
    @Action(input = "http://services.web.advanced.seks/QuerySemanticVectorsService/getQueryKeywordsRequest", output = "http://services.web.advanced.seks/QuerySemanticVectorsService/getQueryKeywordsResponse")
    public List<String> getQueryKeywords(
        @WebParam(name = "query", targetNamespace = "")
        String query);

    /**
     * 
     * @param queryKeywords
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createQueryStatisticVector", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.CreateQueryStatisticVector")
    @ResponseWrapper(localName = "createQueryStatisticVectorResponse", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.CreateQueryStatisticVectorResponse")
    @Action(input = "http://services.web.advanced.seks/QuerySemanticVectorsService/createQueryStatisticVectorRequest", output = "http://services.web.advanced.seks/QuerySemanticVectorsService/createQueryStatisticVectorResponse")
    public String createQueryStatisticVector(
        @WebParam(name = "queryKeywords", targetNamespace = "")
        List<String> queryKeywords);

    /**
     * 
     * @param conceptsAndWeights
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createQuerySemanticVector", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.CreateQuerySemanticVector")
    @ResponseWrapper(localName = "createQuerySemanticVectorResponse", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.CreateQuerySemanticVectorResponse")
    @Action(input = "http://services.web.advanced.seks/QuerySemanticVectorsService/createQuerySemanticVectorRequest", output = "http://services.web.advanced.seks/QuerySemanticVectorsService/createQuerySemanticVectorResponse")
    public String createQuerySemanticVector(
        @WebParam(name = "conceptsAndWeights", targetNamespace = "")
        String conceptsAndWeights);

    /**
     * 
     * @param statisticVector
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getConceptsFromKeywords", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetConceptsFromKeywords")
    @ResponseWrapper(localName = "getConceptsFromKeywordsResponse", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetConceptsFromKeywordsResponse")
    @Action(input = "http://services.web.advanced.seks/QuerySemanticVectorsService/getConceptsFromKeywordsRequest", output = "http://services.web.advanced.seks/QuerySemanticVectorsService/getConceptsFromKeywordsResponse")
    public String getConceptsFromKeywords(
        @WebParam(name = "statisticVector", targetNamespace = "")
        String statisticVector);

    /**
     * 
     * @param statisticVector
     * @param conceptsAndKeywords
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getConceptsTotalWeights", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetConceptsTotalWeights")
    @ResponseWrapper(localName = "getConceptsTotalWeightsResponse", targetNamespace = "http://services.web.advanced.seks/", className = "seks.advanced.web.services.GetConceptsTotalWeightsResponse")
    @Action(input = "http://services.web.advanced.seks/QuerySemanticVectorsService/getConceptsTotalWeightsRequest", output = "http://services.web.advanced.seks/QuerySemanticVectorsService/getConceptsTotalWeightsResponse")
    public String getConceptsTotalWeights(
        @WebParam(name = "statisticVector", targetNamespace = "")
        String statisticVector,
        @WebParam(name = "conceptsAndKeywords", targetNamespace = "")
        String conceptsAndKeywords);

}
