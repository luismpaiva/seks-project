package org.apache.jsp.documentManagement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.OutputStream;
import java.io.Writer;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.ontology.OntModel;
import seks.basic.ontology.OntologyPersistenceImpl;
import seks.basic.ontology.OntologyPersistence;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.ontology.Individual;
import seks.basic.ontology.OntologyInteractionImpl;
import seks.basic.ontology.OntologyInteraction;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;
import java.sql.*;
import seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl;
import seks.advanced.semantic.vectors.KeywordBasedSVCreation;

public final class svCreationTest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("    \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Semantic Vector Creation Test</h1>\n");
      out.write("            <form name=\"list_folders\" action=\"list_files.jsp\">\n");
      out.write("\n");
      out.write("                 <table border=\"1\">\n");
      out.write("                   <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Keyword</th>\n");
      out.write("                            <th>Weight</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("\n");
      out.write("        ");

                    KeywordBasedSVCreation svCreator = new KeywordBasedSVCreationImpl() ;
                    HashMap<String, Double> statVector = svCreator.getStatisticalVectorByDocumentURI("xpto1") ;
                    Iterator keywordIter = statVector.keySet().iterator() ;
                    
                    while (keywordIter.hasNext()) {
                        String keyword = (String) keywordIter.next() ;
                        Double weight = statVector.get(keyword) ;
        
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( keyword);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( weight.toString());
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("        ");

                        
                    }
                    
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("\n");
      out.write("                 ");

                
                
      out.write("\n");
      out.write("             <table border=\"1\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Concept</th>\n");
      out.write("                            <th>Keywords</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("                    ");

                    HashMap<String, ArrayList<String>> conceptsAndKeywords = svCreator.getConceptsFromKeywords(statVector) ;
                    Iterator conceptsIter = conceptsAndKeywords.keySet().iterator() ;
                    while (conceptsIter.hasNext()) {
                        String concept = (String) conceptsIter.next() ;
                        ArrayList<String> keywords = conceptsAndKeywords.get(concept) ;
                        String keywordList = "" ;
                        for(int i = 0; i < keywords.size(); i++) {
                            if (i == 0) {
                                keywordList = keywords.get(i) ;
                            }
                            else {
                                keywordList = keywordList.concat(", " + keywords.get(i)) ;
                            }
                        }
                        
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( concept);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( keywordList);
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");

                        
                    }
                    
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                    </table>\n");
      out.write("                <table border=\"1\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Concept</th>\n");
      out.write("                            <th>Total weights</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>    \n");
      out.write("                    ");

                    HashMap<String, Double> conceptsAndWeights = svCreator.getConceptsTotalWeights(statVector, conceptsAndKeywords) ;
                    conceptsIter = conceptsAndWeights.keySet().iterator() ;
                    while (conceptsIter.hasNext()) {
                        String concept = (String) conceptsIter.next() ;
                        Double weight = conceptsAndWeights.get(concept) ;
                        
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( concept);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( weight);
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");

                        
                    }
        
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("                \n");
      out.write("                ");

                 ArrayList<String> sortedList = svCreator.sortConceptsByRelevance(conceptsAndWeights) ;
                 for(int i = 0; i < sortedList.size();i++) {
                     String concept = sortedList.get(i) ;
                     String res = (i+1) + " - " + concept ;
                     
      out.write("\n");
      out.write("                     <p>");
      out.print( res);
      out.write("</p>\n");
      out.write("                    ");

                 }
                
      out.write("\n");
      out.write("                <br/>\n");
      out.write("                <h1>Keyword-based Semantic Vector</h1>\n");
      out.write("                <table border=\"1\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Concept</th>\n");
      out.write("                            <th>Weights after TF-IDF</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>    \n");
      out.write("                ");

                    HashMap<String, Double> semanticVector = svCreator.createKeywordBasedSemanticVector("xpto1", conceptsAndWeights, sortedList);
                    Iterator<String> j = semanticVector.keySet().iterator() ;
                    while (j.hasNext()) {
                        String concept = (String) j.next() ;
                        Double weight = semanticVector.get(concept) ;
                    
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print( concept);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print( weight);
      out.write("</td>\n");
      out.write("                        </tr>\n");
      out.write("                    ");
  
                    }
                
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
