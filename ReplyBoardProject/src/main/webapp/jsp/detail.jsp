<%@page import="com.sist.dao.RboardDAO"%>
<%@page import="com.sist.dao.RboardVO"%>
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
String rno =request.getParameter("rno");

String curpage=request.getParameter("page");
%>

<%
	
	RboardDAO dao=RboardDAO.newInstance();
RboardVO vo=dao.detailBoard(Integer.parseInt(rno));

%>


<body>
<center>
<h1>새글</h1>
<div>
<table width=800 border=1 black solid>
<tr>
<th width=20%>이름</th>
<td width=80%><%=vo.getName() %></td>
</tr>

<tr>
<th width=20%>제목</th>
<td width=80%><%=vo.getSubject() %></td>
</tr>

<tr>
<th width=20%>내용</th>
<td width=80%><%=vo.getContent() %></td>
</tr>

<tr>
<th width=20% rowspan=5>버튼</th>
<td><a href="Controll.jsp?mode=4&rno=<%=rno%>&page=<%=curpage%>">수정</a></td>
</tr>
<tr>

<td><a href="Controll.jsp?mode=6&rno=<%=rno%>&page=<%=curpage%>">삭제</a></td>
</tr>
<tr>

<tr>

<td><a href="Controll.jsp?mode=5&rno=<%=rno%>&page=<%=curpage%>">답변</a></td>
</tr>
<tr>

<td><a href="Controll.jsp?mode=1&page=<%=curpage%>">목록</a></td>
</tr>
</center>
</body>
</html>