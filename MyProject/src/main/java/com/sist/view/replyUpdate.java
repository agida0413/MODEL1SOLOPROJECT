package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/replyUpdate")
public class replyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		
		String content=request.getParameter("content");
		String rno=request.getParameter("no");
		String pno=request.getParameter("pno");
		
		
		out.write("<form method=post action=replyUpdate_ok?rno="+rno+"&pno="+pno+">");
		out.write("<table width=800 border=1 solid black>");
		out.write("<tr>");
		out.write("<td width=90%><input type=text size=50 name=content value="+content+"></textarea></td>");
		out.write("<td width=10%><input type=submit value=작성></td>");
		out.write("</tr>");
		out.write("</table>");
		out.write("</form>");
	}

}
