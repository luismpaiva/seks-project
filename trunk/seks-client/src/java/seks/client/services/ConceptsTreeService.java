/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.client.services;

import javax.xml.ws.WebServiceRef;
import seks.advanced.web.services.ClientSupportService_Service;

/**
 *
 * @author Paulo Figueiras
 */
public class ConceptsTreeService {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/seks-tomcat/ClientSupportService.wsdl")
    private static ClientSupportService_Service service ;
    
    public ConceptsTreeService() {}
    
    public String getJSONOntologyTree(String obj, String clsName, boolean last) {
        return ConceptsTreeService.getJSONOntologyTreeWS(obj, clsName, last) ;
    }
    
    private static String getJSONOntologyTreeWS(java.lang.String obj, java.lang.String clsName, boolean last) {
        service = new seks.advanced.web.services.ClientSupportService_Service();
        seks.advanced.web.services.ClientSupportService port = service.getClientSupportServicePort();
        return port.getJSONOntologyTree(obj, clsName, last);
    }
}
