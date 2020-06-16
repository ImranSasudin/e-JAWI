package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.setContentType("text/html");  
      PrintWriter out = response.getWriter();  
      String filename = "pk.jpg";   
      String filepath = "d:\\uploads\\";   
      response.setContentType("APPLICATION/OCTET-STREAM");   
      response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
      
      //use inline if you want to view the content in browser, helpful for pdf file
      //response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");
      FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
                  
      int i;   
      while ((i=fileInputStream.read()) != -1) {  
      out.write(i);   
      }   
      fileInputStream.close();   
      out.close();   
      }  
}