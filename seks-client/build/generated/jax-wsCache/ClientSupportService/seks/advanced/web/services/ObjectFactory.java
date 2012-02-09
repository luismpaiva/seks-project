
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

    private final static QName _GetKeywordsResponse_QNAME = new QName("http://services.web.advanced.seks/", "getKeywordsResponse");
    private final static QName _GetJSONOntologyTree_QNAME = new QName("http://services.web.advanced.seks/", "getJSONOntologyTree");
    private final static QName _GetKeywords_QNAME = new QName("http://services.web.advanced.seks/", "getKeywords");
    private final static QName _GetJSONOntologyTreeResponse_QNAME = new QName("http://services.web.advanced.seks/", "getJSONOntologyTreeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: seks.advanced.web.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetKeywordsResponse }
     * 
     */
    public GetKeywordsResponse createGetKeywordsResponse() {
        return new GetKeywordsResponse();
    }

    /**
     * Create an instance of {@link GetKeywords }
     * 
     */
    public GetKeywords createGetKeywords() {
        return new GetKeywords();
    }

    /**
     * Create an instance of {@link GetJSONOntologyTreeResponse }
     * 
     */
    public GetJSONOntologyTreeResponse createGetJSONOntologyTreeResponse() {
        return new GetJSONOntologyTreeResponse();
    }

    /**
     * Create an instance of {@link GetJSONOntologyTree }
     * 
     */
    public GetJSONOntologyTree createGetJSONOntologyTree() {
        return new GetJSONOntologyTree();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getKeywordsResponse")
    public JAXBElement<GetKeywordsResponse> createGetKeywordsResponse(GetKeywordsResponse value) {
        return new JAXBElement<GetKeywordsResponse>(_GetKeywordsResponse_QNAME, GetKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJSONOntologyTree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getJSONOntologyTree")
    public JAXBElement<GetJSONOntologyTree> createGetJSONOntologyTree(GetJSONOntologyTree value) {
        return new JAXBElement<GetJSONOntologyTree>(_GetJSONOntologyTree_QNAME, GetJSONOntologyTree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getKeywords")
    public JAXBElement<GetKeywords> createGetKeywords(GetKeywords value) {
        return new JAXBElement<GetKeywords>(_GetKeywords_QNAME, GetKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetJSONOntologyTreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.advanced.seks/", name = "getJSONOntologyTreeResponse")
    public JAXBElement<GetJSONOntologyTreeResponse> createGetJSONOntologyTreeResponse(GetJSONOntologyTreeResponse value) {
        return new JAXBElement<GetJSONOntologyTreeResponse>(_GetJSONOntologyTreeResponse_QNAME, GetJSONOntologyTreeResponse.class, null, value);
    }

}
