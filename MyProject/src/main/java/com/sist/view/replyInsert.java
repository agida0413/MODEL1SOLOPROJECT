package com.sist.view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.ReplyDAO;
import com.sist.dao.ReplyVO;

/**
 * Servlet implementation class replyInsert
 */
@WebServlet("/replyInsert")
public class replyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String pno=request.getParameter("pno");
		String content=request.getParameter("content");
		ReplyVO vo=new ReplyVO();
		vo.setPno(Integer.parseInt(pno));
		vo.setMsg(content);
		
		HttpSession session=request.getSession();
		
		vo.setId((String)session.getAttribute("id"));
		vo.setName((String)session.getAttribute("name"));
		
		ReplyDAO dao=ReplyDAO.newInstance();
		dao.insertReply(vo);
		
		response.sendRedirect("ControlServlet?mode=2&pno="+pno);
		
	}

}
