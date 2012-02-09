/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seks.client.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seks.advanced.web.services.DocumentResult;
import seks.client.services.QueriesService;

/**
 *
 * @author Paulo Figueiras
 */
public class KeywordsSearchServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("search") ;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        QueriesService qs = new QueriesService() ;
        try {
            ArrayList<String> keywords = qs.getQueryKeywords(query) ;
            String statisticVector = qs.createQueryStatisticVector(keywords) ;
            String conceptsAndKeywords = qs.getConceptsFromKeywords(statisticVector) ;
            String conceptsAndWeights = qs.getConceptsTotalWeights(statisticVector, conceptsAndKeywords) ;
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
            return;
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
}
