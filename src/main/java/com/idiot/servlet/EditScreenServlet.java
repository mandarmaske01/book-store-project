package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")

public class EditScreenServlet extends HttpServlet {
	private static final String query = "SELECT ID ,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA where id=?";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get printwriter
    	PrintWriter pw = resp.getWriter();
    	//set content type
    	resp.setContentType("text/html");
    	
    	//CSS styles
    	pw.println("<style>");
    	pw.println("  body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; }");
    	pw.println("  table { margin-top: 20px; border-collapse: collapse; width: 50%; }");
    	pw.println("  th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
    	pw.println("  form { text-align: center; }");
    	pw.println("  input[type='text'] { padding: 8px; width: 100%; box-sizing: border-box; margin-bottom: 10px; }");
    	pw.println("  input[type='submit'], input[type='reset'] { background-color: #4caf50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }");
    	pw.println("  input[type='submit']:hover, input[type='reset']:hover { background-color: #45a049; }");
    	pw.println("  a {margin-top: 80px; display: inline-block; padding: 10px 20px; background-color: #3498db; color: white; text-decoration: none; border-radius: 4px; }");
    	pw.println("  a:hover { background-color: #2980b9; }");
    	pw.println("</style>");
    	
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
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		pw.println("<form action='editurl?id="+id+"' method='post'>");
		pw.println("<table align='center'>");
		
		pw.println("<tr>");
		pw.println("<td>Book Name </td>");
		pw.println("<td><input type= 'text' name='bookName' value='"+rs.getString(2)+"'></td>");
		pw.println("</tr>");
		
		pw.println("<tr>");
		pw.println("<td>Book Edition </td>");
		pw.println("<td><input type= 'text' name='bookEdition' value='"+rs.getString(3)+"'></td>");
		pw.println("</tr>");
		
		pw.println("<tr>");
		pw.println("<td>Book Price </td>");
		pw.println("<td><input type= 'text' name='bookPrice' value='"+rs.getFloat(4)+"'></td>");
		pw.println("</tr>");

		pw.println("<tr>");
		pw.println("<td><input type= 'submit'  value='Edit'></td>");
        pw.println("<td><input type= 'reset'  value='cancel'></td>");
        pw.println("</tr>");
		
		
		pw.println("<table>");
		pw.println("<form>");
		
		
		
		
			}
			} catch (SQLException e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"<h2/>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"<h2/>");
			
		}
	    pw.println("<a href='home.html'>Home</a>");
	
		}
    	 
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
		doGet(req,resp);
    }

}
