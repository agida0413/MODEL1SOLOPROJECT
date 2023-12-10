package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sist.dao.UserDAO;
import com.sist.dao.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		out.write("<html>");
		out.write("<head>");
		out.write("</head>");
		out.write("<body>");
		out.write("<center>");
		out.write("<div>");
		out.write("<form action=LoginServlet method=post>");
		out.write("<table width=300 height=150 border=1 solid black>");
		out.write("<label>로그인</label>");
		out.write("<tr>");
		out.write("<td text-align=center>");
		out.write("아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text name=id size=15>");out.write("<br>");
		out.write("비밀번호&nbsp;&nbsp;<input type=password name=pwd size=20>");out.write("<br>");
		out.write("<input type=submit value=로그인 margin-right:50px>");
		out.write("</td>");
		out.write("</tr>");
		
	
		out.write("</table>");
		out.write("</form>");
		out.write("</div>");
		out.write("</center>");
		
		out.write("</body>");
		out.write("</html>");
		
		
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemotePort());
		System.out.println(request.getServerPort());




		

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id= request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		UserDAO dao=UserDAO.newInstance();
		
		
	UserVO vo =dao.loginCheck(id, pwd);
	
	if (vo.getMsg().equals("NO")) {
		response.sendRedirect("ControlServlet?mode=3");
		System.out.println(vo.getMsg());
	}
	else if (vo.getMsg().equals("YES")) {
		HttpSession session =request.getSession();
		session.setAttribute("id", vo.getId()); // 
		session.setAttribute("name", vo.getName());
		response.sendRedirect("ControlServlet?mode=1");
		System.out.println(vo.getMsg());
	}
	else {
		
	}
	
		
	}

}
