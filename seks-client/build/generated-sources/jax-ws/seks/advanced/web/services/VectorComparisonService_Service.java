
package seks.advanced.web.services;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "VectorComparisonService", targetNamespace = "http://services.web.advanced.seks/", wsdlLocation = "http://localhost:8080/seks-tomcat/VectorComparisonService?wsdl")
public class VectorComparisonService_Service
    extends Service
{

    private final static URL VECTORCOMPARISONSERVICE_WSDL_LOCATION;
    private final static WebServiceException VECTORCOMPARISONSERVICE_EXCEPTION;
    private final static QName VECTORCOMPARISONSERVICE_QNAME = new QName("http://services.web.advanced.seks/", "VectorComparisonService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/seks-tomcat/VectorComparisonService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        VECTORCOMPARISONSERVICE_WSDL_LOCATION = url;
        VECTORCOMPARISONSERVICE_EXCEPTION = e;
    }

    public VectorComparisonService_Service() {
        super(__getWsdlLocation(), VECTORCOMPARISONSERVICE_QNAME);
    }

    public VectorComparisonService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), VECTORCOMPARISONSERVICE_QNAME, features);
    }

    public VectorComparisonService_Service(URL wsdlLocation) {
        super(wsdlLocation, VECTORCOMPARISONSERVICE_QNAME);
    }

    public VectorComparisonService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, VECTORCOMPARISONSERVICE_QNAME, features);
    }

    public VectorComparisonService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public VectorComparisonService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns VectorComparisonService
     */
    @WebEndpoint(name = "VectorComparisonServicePort")
    public VectorComparisonService getVectorComparisonServicePort() {
        return super.getPort(new QName("http://services.web.advanced.seks/", "VectorComparisonServicePort"), VectorComparisonService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns VectorComparisonService
     */
    @WebEndpoint(name = "VectorComparisonServicePort")
    public VectorComparisonService getVectorComparisonServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://services.web.advanced.seks/", "VectorComparisonServicePort"), VectorComparisonService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (VECTORCOMPARISONSERVICE_EXCEPTION!= null) {
            throw VECTORCOMPARISONSERVICE_EXCEPTION;
        }
        return VECTORCOMPARISONSERVICE_WSDL_LOCATION;
    }

}
