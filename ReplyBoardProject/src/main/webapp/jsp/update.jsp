<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String rno=request.getParameter("rno");
String curpage=request.getParameter("page");
%>
<jsp:useBean id="vo" class="com.sist.dao.RboardVO"></jsp:useBean>
<jsp:useBean id="dao" class="com.sist.dao.RboardDAO"></jsp:useBean>

<%
vo=dao.updateDataBoard(Integer.parseInt(rno));
%>
<center>
<form method="post" action="update_ok.jsp?rno=<%=rno%>&page=<%=curpage%>">
<h1>새글</h1>
<div>
<table width=800>
<tr>
<th width=20%>이름</th>
<td width=80%><input type=text name=name value=<%=vo.getName() %>></td>
</tr>

<tr>
<th width=20%>제목</th>
<td width=80%><input type=text name=subject value=<%=vo.getSubject() %>></td>
</tr>

<tr>
<th width=20%>내용</th>
<td width=80%><textarea name=content size=100><%=vo.getContent() %></textarea></td>

</tr>

<tr>
<th width=20% rowspan=2>버튼</th>
<td width=80%><input type=submit value="수정"></td>

</tr>
<tr>

<td width=80%><a href="Controll.jsp?mode=3&rno=<%=rno%>&page=<%=curpage%>">취소</a>"</td>

</tr>




</table>

</form>


</div>
</center>
</body>
</html>