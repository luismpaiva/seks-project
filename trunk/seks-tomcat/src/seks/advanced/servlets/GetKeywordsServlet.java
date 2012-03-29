package seks.advanced.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seks.advanced.queries.QueryTreatment;
import seks.advanced.queries.QueryTreatmentImpl;

/**
 * Servlet implementation class GetKeywordsServlet
 */
@WebServlet("/GetKeywords")
public class GetKeywordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetKeywordsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        	QueryTreatment qt = new QueryTreatmentImpl() ;
        	ArrayList<String> keywords = qt.getKeywords() ;
        	String keyword = "" ;
            for (int i = 0; i < keywords.size(); i++) {
                if (i == 0) {
                    keyword = keyword.concat(keywords.get(i)) ;
                }
                else{
                    keyword = keyword.concat("," + keywords.get(i)) ;
                }
            }
            out.println(keyword) ;
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
