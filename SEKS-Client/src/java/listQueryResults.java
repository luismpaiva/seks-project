/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import seks.advanced.web.services.DocumentResult;
import seks.advanced.web.services.QuerySemanticVectorsService_Service;
import seks.advanced.web.services.VectorComparisonService_Service;

/**
 *
 * @author Paulo Figueiras
 */
@WebServlet(name = "listQueryResults", urlPatterns = {"/listQueryResults"})
public class listQueryResults extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_45071/vectorComparisonService.wsdl")
    private VectorComparisonService_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_45071/querySemanticVectorsService.wsdl")
    private QuerySemanticVectorsService_Service service;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            /*  Create the query's semantic vector  */
            
            String query = request.getParameter("keywords") ;
            ArrayList<String> keywords = (ArrayList<String>) this.getQueryKeywords(query) ;
            String statisticVector = this.createQueryStatisticVector(keywords) ;
            String conceptsAndKeywords = this.getConceptsFromKeywords(statisticVector) ;
            String conceptsAndWeights = this.getConceptsTotalWeights(statisticVector, conceptsAndKeywords) ;
            String semanticVector = this.createQuerySemanticVector(conceptsAndWeights) ;
            
            /*  Compare the query's semantic vector with documents' semantic vectors    */
            
            ArrayList<String> documentIds = (ArrayList<String>) this.getDocumentIds() ;
            Iterator iter = documentIds.iterator() ;
            ArrayList<DocumentResult> results = new ArrayList<DocumentResult>() ;
            
            while (iter.hasNext()) {
                String id = (String) iter.next() ;
                String docSemanticVector = this.getSemanticVectorByDocumentId(id) ;
                ArrayList<String> sharedConcepts = (ArrayList<String>) this.getSharedConcepts(docSemanticVector, semanticVector) ;
                DocumentResult result = this.compareSemanticVectors(docSemanticVector, semanticVector, sharedConcepts) ;
                results.add(result) ;
            }
            if (!results.isEmpty()) {
                out.println("<html>");
                out.println("<head>");

                //Display the report's name as a title in the browser's titlebar:
                out.println("<title>Query results</title>");
                out.println("</head>");
                out.println("<body>");
                
                ArrayList<DocumentResult> sortedResults = (ArrayList<DocumentResult>) this.sortDocumentResultsByRelevance(results) ;
                iter = sortedResults.iterator() ;
                out.println("<table border=\"1\">") ;
                out.println("<thead>") ;
                out.println("<tr>") ;
                out.println("<th>Document</th>") ;
                out.println("<th>Relevance in relation to the Query</th>") ;
                out.println("</tr>") ;
                out.println("</thead>") ;
                out.println("<tbody>") ;
                while (iter.hasNext()) {
                            DocumentResult res = (DocumentResult) iter.next() ;
                            out.println("<tr>") ; 
                            out.println("<td>" + res.getIdDocument() + "</td>") ;
                            out.println("<td>" + res.getRelevancePercentage() + "</td>") ;
                            out.println("</tr>") ;
                }
                out.println("</tbody>") ;
                out.println("</table>") ;
            }
            else {
                out.println("<p>No results were found for your query...</p>");
            }
            out.println("</body>") ;
            out.println("</html>") ;
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.util.List<java.lang.String> getQueryKeywords(java.lang.String query) {
        seks.advanced.web.services.QuerySemanticVectorsService port = service.getQuerySemanticVectorsServicePort();
        return port.getQueryKeywords(query);
    }

    private String createQueryStatisticVector(java.util.List<java.lang.String> queryKeywords) {
        seks.advanced.web.services.QuerySemanticVectorsService port = service.getQuerySemanticVectorsServicePort();
        return port.createQueryStatisticVector(queryKeywords);
    }

    private String getConceptsFromKeywords(java.lang.String statisticVector) {
        seks.advanced.web.services.QuerySemanticVectorsService port = service.getQuerySemanticVectorsServicePort();
        return port.getConceptsFromKeywords(statisticVector);
    }

    private String getConceptsTotalWeights(java.lang.String statisticVector, java.lang.String conceptsAndKeywords) {
        seks.advanced.web.services.QuerySemanticVectorsService port = service.getQuerySemanticVectorsServicePort();
        return port.getConceptsTotalWeights(statisticVector, conceptsAndKeywords);
    }

    private String createQuerySemanticVector(java.lang.String conceptsAndWeights) {
        seks.advanced.web.services.QuerySemanticVectorsService port = service.getQuerySemanticVectorsServicePort();
        return port.createQuerySemanticVector(conceptsAndWeights);
    }

    private java.util.List<java.lang.String> getDocumentIds() {
        seks.advanced.web.services.VectorComparisonService port = service_1.getVectorComparisonServicePort();
        return port.getDocumentIds();
    }

    private String getSemanticVectorByDocumentId(java.lang.String documentId) {
        seks.advanced.web.services.VectorComparisonService port = service_1.getVectorComparisonServicePort();
        return port.getSemanticVectorByDocumentId(documentId);
    }

    private java.util.List<java.lang.String> getSharedConcepts(java.lang.String documentSemanticVector, java.lang.String querySemanticVector) {
        seks.advanced.web.services.VectorComparisonService port = service_1.getVectorComparisonServicePort();
        return port.getSharedConcepts(documentSemanticVector, querySemanticVector);
    }

    private DocumentResult compareSemanticVectors(java.lang.String documentSemanticVector, java.lang.String querySemanticVector, java.util.List<java.lang.String> sharedConcepts) {
        seks.advanced.web.services.VectorComparisonService port = service_1.getVectorComparisonServicePort();
        return port.compareSemanticVectors(documentSemanticVector, querySemanticVector, sharedConcepts);
    }

    private java.util.List<seks.advanced.web.services.DocumentResult> sortDocumentResultsByRelevance(java.util.List<seks.advanced.web.services.DocumentResult> documentResults) {
        seks.advanced.web.services.VectorComparisonService port = service_1.getVectorComparisonServicePort();
        return port.sortDocumentResultsByRelevance(documentResults);
    }
}
