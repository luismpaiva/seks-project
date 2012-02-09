/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.client.services;

import java.util.ArrayList;
import javax.xml.ws.WebServiceRef;
import seks.advanced.web.services.ClientSupportService_Service;

/**
 *
 * @author Paulo Figueiras
 */
public class GetKeywordsService {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/seks-tomcat/ClientSupportService.wsdl")
    private static ClientSupportService_Service service ;

    public GetKeywordsService() {}
    
    public ArrayList<String> getKeywords() {
        return (ArrayList<String>) GetKeywordsService.getKeywordsWS() ;
    }
    
    private static java.util.List<java.lang.String> getKeywordsWS() {
        service = new seks.advanced.web.services.ClientSupportService_Service();
        seks.advanced.web.services.ClientSupportService port = service.getClientSupportServicePort();
        return port.getKeywords();
    }
}
