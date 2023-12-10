package com.sist.view;
import com.sist.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.ProductDAO;
import com.sist.dao.ProductVO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// <%@ page contentType="text/html;charset=UTF-8" %>
		PrintWriter out=response.getWriter();
		String ct=request.getParameter("ct");
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("name");
		
		if (ct==null||ct.equals("")) {
			ct="'간식'";
		}
		if (page==null||page.equals("")) {
			page="1";
		}
		
		int inPage=Integer.parseInt(page);
		ProductDAO dao =ProductDAO.newInstance();
		int totalPage = dao.totalPage(ct);
	
		List<ProductVO>list2=dao.productCategoryList(ct,Integer.parseInt(page));
		
		List<ProductVO>clist =new ArrayList<ProductVO>();
		Cookie[] cookies =request.getCookies();
		
		if (cookies==null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if (cookies[i].getName().startsWith("pno")) {
					String pno=cookies[i].getValue();
					ProductVO vo =dao.productDetail(Integer.parseInt(pno));
					clist.add(vo);
				}
				
			}
		}
		
		out.write("<html>");
		out.write("<head>");
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("<style>");
		out.write("ul{list-style:none;}");
		out.write("li{float:left; margin:20px}");
		out.write(".container{text-align:center;}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		if (name!=null) {
			out.write("<h1>"+name+"님 환영합니다.</h1>");
		}
		out.write("<a href=MainServlet?ct='사료'&page=1&mode=1>사료</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='간식'&page=1&mode=1>간식</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='배변/위생'&page=1&mode=1>배변</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='산책/이동장'&page=1&mode=1>산책/이동장</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='건강관리'&page=1&mode=1>건강관리</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='식기'&page=1&mode=1>식기</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='의류/악세사리'&page=1&mode=1>의류/악세사리</a>");out.write("<br>");
		out.write("<a href=MainServlet?ct='장난감'&page=1&mode=1>장난감</a>");
		out.write("<br>");
		
		
		
	
		
			for (ProductVO vo : list2) {
			
				
				 out.write("<div class=\"col-md-3\">");
				   out.write("<div class=\"thumbnail\">");
				   out.write("<a href=ControlServlet?pno="+vo.getPno()+"&mode=2>");
					out.write("<img src = "+vo.getP_image()+" alt=\"Lights\" style=\"width:100%\">");
				   out.write("<div class=\"caption\">");
				   out.write("<p>"+vo.getP_name()+"</p>");
				   out.write("</div>");
				   
				   out.write("</a>");
				   out.write("</div>");
				   out.write("</div>");
				

			}
			final int block=10;// 1~10 , 11~20 , 21~30
			int startPage=((inPage-1)/block*block)+1;
			int endPage=((inPage-1)/block*block)+block;
			if (endPage>totalPage) {
				endPage=totalPage;
			}
			
			out.write("<div class=container>");
			out.write("<ul>");
			
			if (startPage>1) {
				out.write("<li><a href=MainServlet?ct="+ct+"&page="+(startPage-1)+"&mode=1>&lt</li>");
			}
			for(int i=startPage;i<=endPage;i++) {
				out.write("<li><a href=MainServlet?ct="+ct+"&page="+i+"&mode=1>"+i+"</li>");
			}
			
			if (endPage<totalPage) {
				out.write("<li><a href=MainServlet?ct="+ct+"&page="+(endPage+1)+"&mode=1>&gt</li>");
			}
			
			
			out.write("</ul>");
			out.write("</div>");
			
			
		
	
		out.write("<body>");
		out.write("</html>");
		
		
		
		
		
	
		
		
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
