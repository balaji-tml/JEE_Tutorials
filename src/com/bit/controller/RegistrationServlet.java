package com.bit.controller;

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

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		//Get the request parameters	
		String sno = request.getParameter("sno");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		System.out.println("fname: "+fname);
		System.out.println("lname: "+lname);
		System.out.println("email: "+email);
		//Create DB connection and insert values into DB
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system","balaji");
			System.out.println("Connected");
			PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?)");
			//ps.setInt(1, 100);			
			ps.setString(1, sno);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, email);
			//int sqlStatus = ps.executeUpdate();
			int sqlStatus = ps.executeUpdate();
			
			if(sqlStatus>=0)
			{				
				System.out.println("Student Registeration is Successfull");				
			}
			else
			{
				System.out.println("Student Registeration is not Successfully");
			}
			out.println("<h3>Student Registered Successfully!!!</h3>");
			response.sendRedirect("homepage_registration.html");
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect("homepage_registration_error1.html");			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
