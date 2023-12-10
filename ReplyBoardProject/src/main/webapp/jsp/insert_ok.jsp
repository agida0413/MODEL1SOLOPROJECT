<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%request.setCharacterEncoding("UTF-8"); %>
<body>
<jsp:useBean id="vo" class="com.sist.dao.RboardVO">
<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<jsp:useBean id="dao" class="com.sist.dao.RboardDAO"/>


<%
dao.insertBoard(vo);

response.sendRedirect("Controll.jsp?mode=1");

%>


</body>
</html>