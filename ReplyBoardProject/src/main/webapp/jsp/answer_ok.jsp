<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="vo" class="com.sist.dao.RboardVO">
<jsp:setProperty name="vo" property="*"/>
</jsp:useBean >
<jsp:useBean id="dao" class="com.sist.dao.RboardDAO"></jsp:useBean>

<%
String rno=request.getParameter("rno");
String curpage=request.getParameter("page");
dao.replyBoardInsert(Integer.parseInt(rno), vo);

response.sendRedirect("Controll.jsp?page="+curpage+"&rno="+rno);
%>