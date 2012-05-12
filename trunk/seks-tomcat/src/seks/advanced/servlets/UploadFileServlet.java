package seks.advanced.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.*;
import org.apache.tomcat.util.http.fileupload.disk.*;	

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFile")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                if (item.getFieldName().equals("file")) {
                    InputStream content = item.getInputStream();
                    // ... Do your job here.
                    BufferedReader buff = new BufferedReader(new InputStreamReader(content));
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    //response.getWriter().write(content.toString());
                    PrintWriter writer = response.getWriter();
                    String text = "";
                    while ((text = buff.readLine()) != null) {
                        writer.println(text);
                    }
                    buff.close();
                    writer.close();
                    return;
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Parsing file upload failed.", e);
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
