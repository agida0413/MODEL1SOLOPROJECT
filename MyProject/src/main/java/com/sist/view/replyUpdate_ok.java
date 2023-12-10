package com.sist.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.ReplyDAO;


@WebServlet("/replyUpdate_ok")
public class replyUpdate_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
	String rno=	request.getParameter("rno");
		String content=request.getParameter("content");
		String pno=request.getParameter("pno");
		ReplyDAO dao=ReplyDAO.newInstance();
		
		dao.replyUpdate(Integer.parseInt(rno), content);
		
		response.sendRedirect("ControlServlet?mode=2&pno="+pno);
	}

}
