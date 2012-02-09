
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

    private final static QName _CompareSemanticVectorsResponse_QNAME = new QName("http://services.web.advanced.seks/", "compareSemanticVectorsResponse");
    private final static QName _GetSemanticVectorByDocumentId_QNAME = new QName("http://services.web.advanced.seks/", "getSemanticVectorByDocumentId");
    private final static QName _SortDocumentResultsByRelevanceResponse_QNAME = new QName("http://services.web.advanced.seks/", "sortDocumentResultsByRelevanceResponse");
    private final static QName _GetDocumentIdsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getDocumentIdsResponse");
    private final static QName _GetDocumentIds_QNAME = new QName("http://services.web.advanced.seks/", "getDocumentIds");
    private final static QName _GetSharedConceptsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getSharedConceptsResponse");
    private final static QName _GetSemanticVectorByDocumentIdResponse_QNAME = new QName("http://services.web.advanced.seks/", "getSemanticVectorByDocumentIdResponse");
    private final static QName _GetSharedConcepts_QNAME = new QName("http://services.web.advanced.seks/", "getSharedConcepts");
    private final static QName _SortDocumentResultsByRelevance_QNAME = new QName("http://services.web.advanced.seks/", "sortDocumentResultsByRelevance");
    private final static QName _CompareSemanticVectors_QNAME = new QName("http://services.web.advanced.seks/", "compareSemanticVectors");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: seks.advanced.web.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSharedConceptsResponse }
     * 
     */
    public GetSharedConceptsResponse createGetSharedConceptsResponse() {
        return new GetSharedConceptsResponse();
    }

    /**
     * Create an instance of {@link GetSemanticVectorByDocumentIdResponse }
     * 
     */
    public GetSemanticVectorByDocumentIdResponse createGetSemanticVectorByDocumentIdResponse() {
        return new GetSemanticVectorByDocumentIdResponse();
    }

    /**
     * Create an instance of {@link GetDocumentIdsResponse }
     * 
     */
    public GetDocumentIdsResponse createGetDocumentIdsResponse() {
        return new GetDocumentIdsResponse();
    }

    /**
     * Create an instance of {@link GetSemanticVectorByDocumentId }
     * 
     */
    public GetSemanticVectorByDocumentId createGetSemanticVectorByDocumentId() {
        return new GetSemanticVectorByDocumentId();
    }

    /**
     * Create an instance of {@link GetDocumentIds }
     * 
     */
    public GetDocumentIds createGetDocumentIds() {
        return new GetDocumentIds();
    }

    /**
     * Create an instance of {@link CompareSemanticVectors }
     * 
     */
    public CompareSemanticVectors createCompareSemanticVectors() {
        return new CompareSemanticVectors();
    }

    /**
     * Create an instance of {@link SortDocumentResultsByRelevance }
     * 
     */
    public SortDocumentResultsByRelevance createSortDocumentResultsByRelevance() {
        return new SortDocumentResultsByRelevance();
    }

    /**
     * Create an instance of {@link DocumentResult }
     * 
     */
    public DocumentResult createDocumentResult() {
        return new DocumentResult();
    }

    /**
     * Create an instance of {@link SortDocumentResultsByRelevanceResponse }
     * 
     */
    public SortDocumentResultsByRelevanceResponse createSortDocumentResultsByRelevanceResponse() {
        return new SortDocumentResultsByRelevanceResponse();
    }

    /**
     * Create an instance of {@link GetSharedConcepts }
     * 
     */
    public GetSharedConcepts createGetSharedConcepts() {
        return new GetSharedConcepts();
    }

    /**
     * Create an instance of {@link CompareSemanticVectorsResponse }
     * 
     */
    public CompareSemanticVectorsResponse createCompareSemanticVectorsResponse() {
        return new CompareSemanticVectorsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompareSemanticVectorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "compareSemanticVectorsResponse")
    public JAXBElement<CompareSemanticVectorsResponse> createCompareSemanticVectorsResponse(CompareSemanticVectorsResponse value) {
        return new JAXBElement<CompareSemanticVectorsResponse>(_CompareSemanticVectorsResponse_QNAME, CompareSemanticVectorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSemanticVectorByDocumentId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getSemanticVectorByDocumentId")
    public JAXBElement<GetSemanticVectorByDocumentId> createGetSemanticVectorByDocumentId(GetSemanticVectorByDocumentId value) {
        return new JAXBElement<GetSemanticVectorByDocumentId>(_GetSemanticVectorByDocumentId_QNAME, GetSemanticVectorByDocumentId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDocumentResultsByRelevanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "sortDocumentResultsByRelevanceResponse")
    public JAXBElement<SortDocumentResultsByRelevanceResponse> createSortDocumentResultsByRelevanceResponse(SortDocumentResultsByRelevanceResponse value) {
        return new JAXBElement<SortDocumentResultsByRelevanceResponse>(_SortDocumentResultsByRelevanceResponse_QNAME, SortDocumentResultsByRelevanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentIdsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getDocumentIdsResponse")
    public JAXBElement<GetDocumentIdsResponse> createGetDocumentIdsResponse(GetDocumentIdsResponse value) {
        return new JAXBElement<GetDocumentIdsResponse>(_GetDocumentIdsResponse_QNAME, GetDocumentIdsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentIds }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getDocumentIds")
    public JAXBElement<GetDocumentIds> createGetDocumentIds(GetDocumentIds value) {
        return new JAXBElement<GetDocumentIds>(_GetDocumentIds_QNAME, GetDocumentIds.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSharedConceptsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getSharedConceptsResponse")
    public JAXBElement<GetSharedConceptsResponse> createGetSharedConceptsResponse(GetSharedConceptsResponse value) {
        return new JAXBElement<GetSharedConceptsResponse>(_GetSharedConceptsResponse_QNAME, GetSharedConceptsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSemanticVectorByDocumentIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getSemanticVectorByDocumentIdResponse")
    public JAXBElement<GetSemanticVectorByDocumentIdResponse> createGetSemanticVectorByDocumentIdResponse(GetSemanticVectorByDocumentIdResponse value) {
        return new JAXBElement<GetSemanticVectorByDocumentIdResponse>(_GetSemanticVectorByDocumentIdResponse_QNAME, GetSemanticVectorByDocumentIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSharedConcepts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getSharedConcepts")
    public JAXBElement<GetSharedConcepts> createGetSharedConcepts(GetSharedConcepts value) {
        return new JAXBElement<GetSharedConcepts>(_GetSharedConcepts_QNAME, GetSharedConcepts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SortDocumentResultsByRelevance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "sortDocumentResultsByRelevance")
    public JAXBElement<SortDocumentResultsByRelevance> createSortDocumentResultsByRelevance(SortDocumentResultsByRelevance value) {
        return new JAXBElement<SortDocumentResultsByRelevance>(_SortDocumentResultsByRelevance_QNAME, SortDocumentResultsByRelevance.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompareSemanticVectors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "compareSemanticVectors")
    public JAXBElement<CompareSemanticVectors> createCompareSemanticVectors(CompareSemanticVectors value) {
        return new JAXBElement<CompareSemanticVectors>(_CompareSemanticVectors_QNAME, CompareSemanticVectors.class, null, value);
    }

}
