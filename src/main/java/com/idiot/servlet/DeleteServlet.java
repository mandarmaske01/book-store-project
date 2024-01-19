package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")

public class DeleteServlet extends HttpServlet {
	private static final String query = "delete from BOOKDATA where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get printwriter
    	PrintWriter pw = resp.getWriter();
    	
    	// CSS Styles
        pw.println("<style>");
        pw.println("  body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; text-align: center; }");
        pw.println("  .btn { display: inline-block; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 4px; margin: 10px; }");
        pw.println("  .btn:hover { background-color: #2980b9; }");
        pw.println("  .success { color: #27ae60; }");
        pw.println("  .error { color: #c0392b; }");
    	pw.println("  a {margin-top: 80px; display: inline-block; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 4px; }");
    	pw.println("  a:hover { background-color: #2980b9; }");
        pw.println("</style>");
        
    	//set content type
    	resp.setContentType("text/html");
    	
    	//get id of record
    	int id = Integer.parseInt(req.getParameter("id"));
    	
     	//LOAD JDBC DRIVER
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
    	//generate the connection
    	try (Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Mandar@2002");) {
			PreparedStatement ps = con.prepareStatement(query);{
				ps.setInt(1, id);
			
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h2>Record is deleted sucessfully</h2>");
				
			}else {
				pw.println("<h2>Record is not deleted Sucessfully</h2>");
				
			}
			    }
			} catch (SQLException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"<h2/>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"<h2/>");
			
		}
	    pw.println("<a href='home.html'>Home</a>");
	    pw.println("<br>");
		pw.println("<a href='bookList'>Book List</a>");
	
	
		}
    	 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		doGet(req,resp);
    }


}
