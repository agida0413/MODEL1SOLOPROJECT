<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
request.setCharacterEncoding("UTF-8");
String rno=request.getParameter("rno");
String curpage=request.getParameter("page");




%>
<body>
<jsp:useBean id="vo" class="com.sist.dao.RboardVO">
<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<jsp:useBean id="dao" class="com.sist.dao.RboardDAO"></jsp:useBean>

<%

dao.updateBoard(vo, Integer.parseInt(rno));

response.sendRedirect("Controll.jsp?mode=3&rno="+rno+"&page="+curpage);
%>


</body>
</html>