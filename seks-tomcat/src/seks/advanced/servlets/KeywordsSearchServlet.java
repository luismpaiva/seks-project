package seks.advanced.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seks.advanced.queries.QueryTreatment;
import seks.advanced.queries.QueryTreatmentImpl;
import seks.advanced.semantic.vectors.KeywordBasedSVCreation;
import seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl;
import seks.advanced.semantic.vectors.SemanticVectorComparison;
import seks.advanced.semantic.vectors.SemanticVectorComparisonImpl;
import seks.basic.pojos.DocumentResult;
import seks.basic.pojos.SemanticWeight;

/**
 * Servlet implementation class KeywordsSearchServlet
 */
@WebServlet("/KeywordsSearch")
public class KeywordsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KeywordsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String query = request.getParameter("search") ;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	QueryTreatment qt = new QueryTreatmentImpl() ;
    	KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
    	SemanticVectorComparison svc = new SemanticVectorComparisonImpl() ;
    	ArrayList<DocumentResult> results = new ArrayList<DocumentResult>() ;
    	try{
    		ArrayList<String> keywords = qt.getQueryKeywords(query) ;
    		HashMap<String, Double> statVector = qt.createQueryStatisticVector(keywords) ;
    		HashMap<String, HashMap<String, Double>> conceptsKeywordsAndWeights = svCreator.getConceptsAndWeightsFromKeywords(statVector) ;
    		HashMap<String, Double> conceptsAndWeights = svCreator.getConceptsTotalWeights(conceptsKeywordsAndWeights) ;
    		HashMap<String, SemanticWeight> querySemanticVector = qt.createQuerySemanticVector(conceptsAndWeights) ;
    		ArrayList<Integer> documentIDs = svc.getDocumentIds() ;
    		for (int documentID : documentIDs) {
    			HashMap<String, SemanticWeight> documentSemanticVector = svc.getSemanticVectorByDocumentID(documentID) ;
    			ArrayList<String> sharedConcepts = svc.getSharedConcepts(documentSemanticVector, querySemanticVector) ;
    			DocumentResult result = svc.compareSemanticVectors(documentSemanticVector, querySemanticVector, sharedConcepts) ;
    			results.add(result) ;
    		}
    		ArrayList<DocumentResult> sortedResults = svc.sortDocumentResultsByRelevance(results) ;
    		out.println("<table><thead><tr><td>Document Id</td><td>Search Relevance</td></tr></thead><tbody>") ;
            for (int i = 0; i < sortedResults.size(); i++) {
                DocumentResult result = sortedResults.get(i) ;
                out.println("<tr><td>" + result.getIdDocument() + "</td><td>" + result.getRelevancePercentage() + "</td></tr>");
            }
            out.println("</tbody></table>") ;
    	} finally {            
            out.close();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
