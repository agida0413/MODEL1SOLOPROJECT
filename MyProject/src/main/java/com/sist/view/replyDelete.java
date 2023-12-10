package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;

import com.sist.dao.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class replyDelete
 */
@WebServlet("/replyDelete")
public class replyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out= response.getWriter();
		String pno=request.getParameter("pno");
		String rno=request.getParameter("rno");
		
	
		ReplyDAO dao=ReplyDAO.newInstance();
		
		dao.deleteReply(Integer.parseInt(rno));
		
		
		
		
		response.sendRedirect("ControlServlet?mode=2&pno="+pno);
		
		
		
	}

}
