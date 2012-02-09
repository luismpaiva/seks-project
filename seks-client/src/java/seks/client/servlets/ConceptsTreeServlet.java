package seks.client.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seks.client.services.ConceptsTreeService;

/**
 * Servlet implementation class conceptsTreeServlet
 */
@WebServlet("/ConceptsTree")
public class ConceptsTreeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConceptsTreeServlet() {
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
            //String json = "{\"title\" : \"Concept\", \"isFolder\": \"true\", \"children\":[{\"title\": \"Actor\"}, {\"title\": \"Project\"}]}" ;
            ConceptsTreeService cts = new ConceptsTreeService() ;
            String json = cts.getJSONOntologyTree("", "Concept", false) ;
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            System.out.println(json) ;    
            response.getWriter().println(json) ;
            return ;
		
	}
}
