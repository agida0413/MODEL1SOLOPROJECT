package com.sist.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControlServlet
 */
@WebServlet("/ControlServlet")
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	String mode=request.getParameter("mode");
	if (mode==null) {
		mode="1";
	}
	String name="";
	
	switch(mode) {
	case "1" :name="MainServlet";
	break;
	case "2" :name="DetailServlet";
	break;
	case "3" :name="LoginServlet";
	
	
	}
	

	RequestDispatcher rs = request.getRequestDispatcher("LoginServlet");
	rs=request.getRequestDispatcher(name);
	
	rs.include(request, response);
	


		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
