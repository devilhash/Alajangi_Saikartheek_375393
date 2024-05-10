package com.download;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("files");
		ServletContext  c = getServletContext();
		String path = c.getRealPath( "/")+"files"+"\\"+s;
		String filename = "dsa.pdf";
//		System.out.println(path);
		
		response.setContentType("appication/octet-stream");
		String headerKey = "Content-Disposition";
		String header = String.format("attachment; filename=\\%s",filename);
		response.setHeader(headerKey, header);
		 
		
		File file = new File(path);
//		  System.out.println(file.isFile());
		
		  FileInputStream fs = new FileInputStream(file);
		  ServletOutputStream sos = response.getOutputStream();
		  
		  int bytes;
		  byte[] buffer = new byte[4096];
		  while((bytes = fs.read(buffer))!= -1) {
			  sos.write(buffer,0,bytes);
		  }
		  
		  fs.close();
		  sos.close();
		  
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
