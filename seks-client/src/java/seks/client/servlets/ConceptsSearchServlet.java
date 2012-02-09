package seks.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seks.advanced.web.services.DocumentResult;
import seks.client.services.QueriesService;

/**
 * Servlet implementation class ConceptsSearchServlet
 */
@WebServlet("/ConceptsSearch")
public class ConceptsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConceptsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String query = request.getParameter("search") ;
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            QueriesService qs = new QueriesService() ;
            try {
                ArrayList<String> keywords = qs.getQueryKeywords(query) ;
                String conceptsAndWeights = qs.createQueryStatisticVector(keywords) ;
                String querySemanticVector = qs.createQuerySemanticVector(conceptsAndWeights) ;
                ArrayList<String> documentIds = qs.getDocumentIds() ;
                ArrayList<DocumentResult> documentResults = new ArrayList<DocumentResult>() ;
                Iterator iter = documentIds.iterator() ;
                while (iter.hasNext()) {
                    String documentId = (String) iter.next() ;
                    String documentSemanticVector = qs.getSemanticVectorByDocumentId(documentId) ;
                    ArrayList<String> sharedConcepts = qs.getSharedConcepts(documentSemanticVector, querySemanticVector) ;
                    DocumentResult result = qs.compareSemanticVectors(documentSemanticVector, querySemanticVector, sharedConcepts) ;
                    documentResults.add(result) ;
                }
                ArrayList<DocumentResult> sortedResults = qs.sortDocumentResultsByRelevance(documentResults) ;
                out.println("<table><thead><tr><td>Document Id</td><td>Search Relevance</td></tr></thead><tbody>") ;
                for (int i = 0; i < sortedResults.size(); i++) {
                    DocumentResult result = sortedResults.get(i) ;
                    out.println("<tr><td>" + result.getIdDocument() + "</td><td>" + result.getRelevancePercentage() + "</td></tr>");
                }
                out.println("</tbody></table>") ;

                // TODO: Pedir à BD a info e a relevancia do documento e apresentar no browser

            } finally {            
                out.close();
            }
	}

}
