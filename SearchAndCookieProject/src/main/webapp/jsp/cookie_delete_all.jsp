<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Cookie cookies[] =request.getCookies();
String pno=request.getParameter("pno");
String curpage=request.getParameter("page");
String fd=request.getParameter("fd");
String search=request.getParameter("search");
if(cookies!=null){
	
	for(int i=cookies.length-1; i>=0;i--){
		cookies[i].setPath("/");
		cookies[i].setMaxAge(0);
		response.addCookie(cookies[i]);
		
	}
	
}

response.sendRedirect("detail.jsp?page="+curpage+"&pno="+pno+"&fd="+fd+"&search="+URLEncoder.encode(search,"UTF-8"));


%>