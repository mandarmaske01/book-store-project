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

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final String query = "SELECT ID ,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get printwriter
        PrintWriter pw = resp.getWriter();
        
     // CSS Styles
        pw.println("<style>");
        pw.println("  body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; text-align: center; }");
        pw.println("  table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
        pw.println("  th, td { padding: 12px; text-align: center; border: 1px solid #ddd; }");
        pw.println("  th { background-color: green; }");
        pw.println("  a { display: inline-block; padding: 10px 20px; margin: 5px; text-decoration: none; border-radius: 4px; }");
        pw.println("  a:hover { background-color: #90EE90; }");
        pw.println("</style>");
        // set content type
        resp.setContentType("text/html");
        
     

        // LOAD JDBC DRIVER
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // generate the connection
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "Mandar@2002");) {
            PreparedStatement ps = con.prepareStatement(query);
            {
                ResultSet rs = ps.executeQuery();
                pw.println("<style>");
                pw.println("table { border-collapse: collapse; width: 80%; margin: 20px auto; }");
                pw.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
                pw.println("th { background-color: #f2f2f2; }");
                pw.println("a { text-decoration: none; color: #007BFF; margin-right: 10px; }");
                pw.println("</style>");

                pw.println("<table>");
                pw.println("<tr>");
                pw.println("<th>Book Id</th>");
                pw.println("<th>Book Name</th>");
                pw.println("<th>Book Edition</th>");
                pw.println("<th>Book Price</th>");
                pw.println("<th>Edit</th>");
                pw.println("<th>Delete</th>");
                pw.println("</tr>");

                while (rs.next()) {
                    pw.println("<tr>");
                    pw.println("<td>" + rs.getInt(1) + "</td>");
                    pw.println("<td>" + rs.getString(2) + "</td>");
                    pw.println("<td>" + rs.getString(3) + "</td>");
                    pw.println("<td>" + rs.getFloat(4) + "</td>");
                    pw.println("<td><a href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                    pw.println("<td><a href='deleteurl?id=" + rs.getInt(1) + "'>Delete</a></td>");
                    pw.println("</tr>");
                }
                pw.println("</table>");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h1 class='error'>" + e.getMessage() + "<h2/>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1 class='error'>" + e.getMessage() + "<h2/>");
        }
        pw.println("<a href='home.html' style=' color: white ; background-color: #2980b9;' class='btn'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}