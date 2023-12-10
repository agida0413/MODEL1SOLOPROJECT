<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="vo" class="com.sist.dao.ProductVO"></jsp:useBean>
<jsp:useBean id="dao" class="com.sist.dao.ProductDAO"></jsp:useBean>
<%
request.setCharacterEncoding("UTF-8");
String curpage=request.getParameter("page");
String search=request.getParameter("search");
String fd=request.getParameter("fd");

String pno=request.getParameter("pno");
Cookie cookie =new Cookie("pno_"+pno,pno);
cookie.setPath("/");
cookie.setMaxAge(60*60*24);

response.addCookie(cookie);

response.sendRedirect("detail.jsp?page="+curpage+"&pno="+pno+"&fd="+fd+"&search="+URLEncoder.encode(search,"UTF-8"));

%>