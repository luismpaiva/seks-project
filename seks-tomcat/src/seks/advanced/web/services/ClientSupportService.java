package seks.advanced.web.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import seks.advanced.queries.QueryTreatment;
import seks.advanced.queries.QueryTreatmentImpl;
import seks.basic.ontology.OntologyInteraction;
import seks.basic.ontology.OntologyInteractionImpl;

@WebService(serviceName = "ClientSupportService")
public class ClientSupportService {
	
	public ClientSupportService() {}
	
	/**
     * Retrieves all keywords from ontology.
     * 
     * @return  All ontology keywords, in the form of an {@link java.util.ArrayList}
     *          object
     */
    @WebMethod(operationName = "getKeywords")
    public ArrayList<String> getKeywords() {
        QueryTreatment qt = new QueryTreatmentImpl() ;
        return qt.getKeywords() ;
    }
    
    @WebMethod(operationName = "getJSONOntologyTree")
    public String getJSONOntologyTree(@WebParam(name = "clsName")String clsName, @WebParam(name = "last")boolean last) {
    	OntologyInteraction oi = new OntologyInteractionImpl() ;
    	return this.getJSONOntologyTree2(oi, new String(clsName), last) ;
    }


	private String getJSONOntologyTree2(OntologyInteraction oi, String clsName, boolean last) {
		ArrayList<String> subclasses = new ArrayList<String>() ;
		String obj = "" ;
		if (clsName.equals("Concept") && obj.equals("")) {
			obj = "{\"title\" : \"Concept\", \"isFolder\" : \"true\", \"children\" :[" ;
			subclasses = oi.getAllSubclasses("Concept");
	    	for (int i = 0; i < subclasses.size(); i++) {
	    		clsName = subclasses.get(i) ;
	    		if (i == (subclasses.size() - 1)) {
	    			obj += this.getJSONOntologyTree2(oi, clsName, true) ;
	    		}
	    		else {
	    			obj += this.getJSONOntologyTree2(oi, clsName, false) ;
	    		}
	    	}
	    	obj += ("]}") ;
		}
		else {
			obj += "{\"title\" : \"" + clsName + "\"" ;
			if (oi.hasSubclasses(clsName)) {
				subclasses = oi.getAllSubclasses(clsName) ;
				obj += ", \"isFolder\" : \"true\", \"children\" : [" ;
				for (int i = 0; i < subclasses.size(); i++) {
	        		clsName = subclasses.get(i) ;
	        		if (i == (subclasses.size() - 1)) {
	        			obj += this.getJSONOntologyTree2(oi, clsName, true) ;
	        		}
	        		else {
	        			obj += this.getJSONOntologyTree2(oi, clsName, false) ;
	        		}
	        	}
				obj += ("]") ;
				
			}
			if (last) {
				obj += ("}") ;
			}
			else {
				obj += ("}, ") ;
			}
		}
		return obj ;
	}

}
