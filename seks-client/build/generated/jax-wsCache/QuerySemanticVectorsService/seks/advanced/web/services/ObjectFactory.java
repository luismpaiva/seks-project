
package seks.advanced.web.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the seks.advanced.web.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateQuerySemanticVector_QNAME = new QName("http://services.web.advanced.seks/", "createQuerySemanticVector");
    private final static QName _GetQueryKeywords_QNAME = new QName("http://services.web.advanced.seks/", "getQueryKeywords");
    private final static QName _CreateQueryStatisticVectorResponse_QNAME = new QName("http://services.web.advanced.seks/", "createQueryStatisticVectorResponse");
    private final static QName _CreateQueryStatisticVector_QNAME = new QName("http://services.web.advanced.seks/", "createQueryStatisticVector");
    private final static QName _CreateQuerySemanticVectorResponse_QNAME = new QName("http://services.web.advanced.seks/", "createQuerySemanticVectorResponse");
    private final static QName _GetQueryKeywordsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getQueryKeywordsResponse");
    private final static QName _GetConceptsFromKeywordsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getConceptsFromKeywordsResponse");
    private final static QName _GetConceptsTotalWeightsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getConceptsTotalWeightsResponse");
    private final static QName _GetConceptsTotalWeights_QNAME = new QName("http://services.web.advanced.seks/", "getConceptsTotalWeights");
    private final static QName _GetConceptsFromKeywords_QNAME = new QName("http://services.web.advanced.seks/", "getConceptsFromKeywords");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: seks.advanced.web.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateQuerySemanticVector }
     * 
     */
    public CreateQuerySemanticVector createCreateQuerySemanticVector() {
        return new CreateQuerySemanticVector();
    }

    /**
     * Create an instance of {@link GetConceptsFromKeywordsResponse }
     * 
     */
    public GetConceptsFromKeywordsResponse createGetConceptsFromKeywordsResponse() {
        return new GetConceptsFromKeywordsResponse();
    }

    /**
     * Create an instance of {@link CreateQuerySemanticVectorResponse }
     * 
     */
    public CreateQuerySemanticVectorResponse createCreateQuerySemanticVectorResponse() {
        return new CreateQuerySemanticVectorResponse();
    }

    /**
     * Create an instance of {@link CreateQueryStatisticVector }
     * 
     */
    public CreateQueryStatisticVector createCreateQueryStatisticVector() {
        return new CreateQueryStatisticVector();
    }

    /**
     * Create an instance of {@link GetConceptsTotalWeightsResponse }
     * 
     */
    public GetConceptsTotalWeightsResponse createGetConceptsTotalWeightsResponse() {
        return new GetConceptsTotalWeightsResponse();
    }

    /**
     * Create an instance of {@link GetQueryKeywords }
     * 
     */
    public GetQueryKeywords createGetQueryKeywords() {
        return new GetQueryKeywords();
    }

    /**
     * Create an instance of {@link GetConceptsFromKeywords }
     * 
     */
    public GetConceptsFromKeywords createGetConceptsFromKeywords() {
        return new GetConceptsFromKeywords();
    }

    /**
     * Create an instance of {@link GetConceptsTotalWeights }
     * 
     */
    public GetConceptsTotalWeights createGetConceptsTotalWeights() {
        return new GetConceptsTotalWeights();
    }

    /**
     * Create an instance of {@link CreateQueryStatisticVectorResponse }
     * 
     */
    public CreateQueryStatisticVectorResponse createCreateQueryStatisticVectorResponse() {
        return new CreateQueryStatisticVectorResponse();
    }

    /**
     * Create an instance of {@link GetQueryKeywordsResponse }
     * 
     */
    public GetQueryKeywordsResponse createGetQueryKeywordsResponse() {
        return new GetQueryKeywordsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuerySemanticVector }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "createQuerySemanticVector")
    public JAXBElement<CreateQuerySemanticVector> createCreateQuerySemanticVector(CreateQuerySemanticVector value) {
        return new JAXBElement<CreateQuerySemanticVector>(_CreateQuerySemanticVector_QNAME, CreateQuerySemanticVector.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQueryKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getQueryKeywords")
    public JAXBElement<GetQueryKeywords> createGetQueryKeywords(GetQueryKeywords value) {
        return new JAXBElement<GetQueryKeywords>(_GetQueryKeywords_QNAME, GetQueryKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQueryStatisticVectorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "createQueryStatisticVectorResponse")
    public JAXBElement<CreateQueryStatisticVectorResponse> createCreateQueryStatisticVectorResponse(CreateQueryStatisticVectorResponse value) {
        return new JAXBElement<CreateQueryStatisticVectorResponse>(_CreateQueryStatisticVectorResponse_QNAME, CreateQueryStatisticVectorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQueryStatisticVector }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "createQueryStatisticVector")
    public JAXBElement<CreateQueryStatisticVector> createCreateQueryStatisticVector(CreateQueryStatisticVector value) {
        return new JAXBElement<CreateQueryStatisticVector>(_CreateQueryStatisticVector_QNAME, CreateQueryStatisticVector.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateQuerySemanticVectorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "createQuerySemanticVectorResponse")
    public JAXBElement<CreateQuerySemanticVectorResponse> createCreateQuerySemanticVectorResponse(CreateQuerySemanticVectorResponse value) {
        return new JAXBElement<CreateQuerySemanticVectorResponse>(_CreateQuerySemanticVectorResponse_QNAME, CreateQuerySemanticVectorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetQueryKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getQueryKeywordsResponse")
    public JAXBElement<GetQueryKeywordsResponse> createGetQueryKeywordsResponse(GetQueryKeywordsResponse value) {
        return new JAXBElement<GetQueryKeywordsResponse>(_GetQueryKeywordsResponse_QNAME, GetQueryKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConceptsFromKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getConceptsFromKeywordsResponse")
    public JAXBElement<GetConceptsFromKeywordsResponse> createGetConceptsFromKeywordsResponse(GetConceptsFromKeywordsResponse value) {
        return new JAXBElement<GetConceptsFromKeywordsResponse>(_GetConceptsFromKeywordsResponse_QNAME, GetConceptsFromKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConceptsTotalWeightsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getConceptsTotalWeightsResponse")
    public JAXBElement<GetConceptsTotalWeightsResponse> createGetConceptsTotalWeightsResponse(GetConceptsTotalWeightsResponse value) {
        return new JAXBElement<GetConceptsTotalWeightsResponse>(_GetConceptsTotalWeightsResponse_QNAME, GetConceptsTotalWeightsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConceptsTotalWeights }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getConceptsTotalWeights")
    public JAXBElement<GetConceptsTotalWeights> createGetConceptsTotalWeights(GetConceptsTotalWeights value) {
        return new JAXBElement<GetConceptsTotalWeights>(_GetConceptsTotalWeights_QNAME, GetConceptsTotalWeights.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetConceptsFromKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getConceptsFromKeywords")
    public JAXBElement<GetConceptsFromKeywords> createGetConceptsFromKeywords(GetConceptsFromKeywords value) {
        return new JAXBElement<GetConceptsFromKeywords>(_GetConceptsFromKeywords_QNAME, GetConceptsFromKeywords.class, null, value);
    }

}
