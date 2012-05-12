package seks.advanced.servlets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import seks.advanced.semantic.vectors.KeywordBasedSVCreation;
import seks.advanced.semantic.vectors.KeywordBasedSVCreationImpl;
import seks.advanced.semantic.vectors.OntologyBasedSVCreation;
import seks.advanced.semantic.vectors.OntologyBasedSVCreationImpl;
import seks.advanced.semantic.vectors.TaxonomyBasedSVCreation;
import seks.advanced.semantic.vectors.TaxonomyBasedSVCreationImpl;
import seks.basic.database.DatabaseInteraction;
import seks.basic.database.DatabaseInteractionImpl;
import seks.basic.pojos.SemanticWeight;

/**
 * Servlet implementation class InitDocumentIndexationServlet
 */
@WebServlet("/InitDocumentIndexation")
public class InitDocumentIndexationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDocumentIndexationServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		
		/*Timer timer = new Timer() ;
		Date now = new Date() ;
		timer.scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				
				
				KeywordBasedSVCreation kbsvCreator = new KeywordBasedSVCreationImpl() ;
				TaxonomyBasedSVCreation tbsvCreator = new TaxonomyBasedSVCreationImpl() ;
				OntologyBasedSVCreation obsvCreator = new OntologyBasedSVCreationImpl() ;
				Connection con = di.openConnection("svdbConfig.xml") ;
				ResultSet set = di.callProcedure(con, "svdb.getNotIndexedDocumentIDs") ;
				try {
					while (set.next()) {
						int documentId = set.getInt("idDocument") ;
						
						//Keyword-based Semantic Vector Creation
						
						HashMap<String, Double> statVector = kbsvCreator.getStatisticalVectorByDocumentID(documentId) ;
						HashMap<String, ArrayList<String>> conceptsAndKeywords = kbsvCreator.getConceptsFromKeywords(statVector) ;
						HashMap<String, Double> conceptsAndWeights = kbsvCreator.getConceptsTotalWeights(statVector, conceptsAndKeywords) ;
						ArrayList<String> sortedList = kbsvCreator.sortConceptsByRelevance(conceptsAndWeights) ;
						HashMap<String, SemanticWeight> kbSemanticVector = kbsvCreator.createKeywordBasedSemanticVector(documentId, conceptsAndWeights, sortedList);
						kbsvCreator.storeKeywordBasedSemanticVector(kbSemanticVector);
						
						//Taxonomy-based Semantic Vector Creation
						
						HashMap<String, SemanticWeight> tbSemanticVector = tbsvCreator.createTaxonomyBasedSemanticVector(kbSemanticVector) ;
						tbsvCreator.storeTaxonomyBasedSemanticVector(tbSemanticVector) ;
						
						//Ontology-based Semantic Vector Creation
						
						HashMap<String, SemanticWeight> obSemanticVector = obsvCreator.createOntologyBasedSemanticVector(tbSemanticVector) ;
						obsvCreator.storeOntologyBasedSemanticVector(obSemanticVector) ;
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}, now, (1000*60*60*24)) ; // Repeats every day*/
	}

}
