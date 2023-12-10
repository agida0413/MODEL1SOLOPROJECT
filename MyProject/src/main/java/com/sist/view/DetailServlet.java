package com.sist.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sist.dao.ProductDAO;
import com.sist.dao.ProductVO;
import com.sist.dao.ReplyDAO;
import com.sist.dao.ReplyVO;


@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// <%@ page contentType="text/html;charset=UTF-8" %>
		PrintWriter out=response.getWriter();
		String pno = request.getParameter("pno");
		int pno1 = Integer.parseInt(pno);
		ProductDAO dao = new ProductDAO();
		
		ProductVO vo =dao.productDetail(pno1);
		
		
		out.write("<html>");
		out.write("<head>");
		out.write("<body>");
		out.write("<div>");
		out.write("<table>");
		
		
		out.write("<tr>");
		out.write("<td rowspan=7><img src="+vo.getP_image()+"></td>");
		out.write("</tr>");
		
		
		out.write("<tr>");
		out.write("<td>"+vo.getP_hit()+"</td>");
		
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td>"+vo.getP_name()+"</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td>"+vo.getP_price()+"</td><br>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td>"+vo.getP_percent()+"</td><br>");
		out.write("</tr>");
		
		
		out.write("<tr>");
		out.write("<td>"+vo.getP_lowerprice()+"</td><br>");
		out.write("</tr>");
		out.write("<img src="+vo.getP_detail_image()+">");
		out.write("<tr>");
		out.write("<td>"+vo.getP_shipment()+"</td><br>");
		out.write("</tr>");
		out.write("</div>");
		
	

		
		ReplyDAO dao2=ReplyDAO.newInstance();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		List<ReplyVO>list2=dao2.replyListData(pno1);
		//댓글출력 
		System.out.println(list2.size());
		out.write("<div>");
		for (ReplyVO vo2 : list2) {
			
		
		
		out.write("<table width=800 border=1 solid black>");
		out.write("<tr>");
		out.write("<th width=20%>"+vo2.getName()+"</th>");
		out.write("<th width=60%><pre>"+vo2.getMsg()+"</pre></th>");
		out.write("<th width=20%>"+vo2.getDbday()+"</th>");
		if (vo2.getId().equals(id)) {
			
			out.write("<td><a href=replyDelete?pno="+vo2.getPno()+"&rno="+vo2.getRno()+">삭제</a></td>");
			out.write("<form method=post action=replyUpdate?pno="+pno+">");
			out.write("<input type=hidden name=content value="+vo2.getMsg()+">");
			out.write("<input type=hidden name=no value="+vo2.getRno()+">");
			out.write("<input type=submit value=수정>");
			
			out.write("</form>");
		}
		
		out.write("</tr>");
		out.write("</table>");
		
		}
		out.write("</div>");
		
		
	
		out.write("<div>");
		if(id!=null) {
			out.write("<form method=post action=replyInsert?pno="+pno+">");
			out.write("<table width=800 border=1 solid black>");
			out.write("<tr>");
			out.write("<td width=90%><input type=text size=50 name=content></textarea></td>");
			out.write("<td width=10%><input type=submit value=작성></td>");
			out.write("</tr>");
			out.write("</table>");
			out.write("</form>");
			
		}
		

		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
