<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<% 
String rno=request.getParameter("rno");
String curpage=request.getParameter("page");

%>
<body>
<center>
<form method="post" action="answer_ok.jsp?rno=<%=rno%>&page=<%=curpage%>">
<h1>답변</h1>
<div>
<table width=800>
<tr>
<th width=20%>이름</th>
<td width=80%><input type=text name=name></td>
</tr>

<tr>
<th width=20%>제목</th>
<td width=80%><input type=text name=subject></td>
</tr>

<tr>
<th width=20%>내용</th>
<td width=80%><textarea name=content size=100></textarea></td>
</tr>

<tr>
<th width=20%>버튼</th>
<td width=80%><input type=submit value="글쓰기"></td>
</tr>




</table>

</form>


</div>
</center>
</body>
</html>