<%@page import="com.sist.dao.RboardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>


<%
String rno=request.getParameter("rno");
String curpage=request.getParameter("page");

RboardDAO dao=RboardDAO.newInstance();
dao.deleteBoard(Integer.parseInt(rno));
System.out.print("메소드 수행");


response.sendRedirect("Controll.jsp");
%>

