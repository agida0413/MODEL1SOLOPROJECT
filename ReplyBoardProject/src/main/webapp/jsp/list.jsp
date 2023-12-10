<%@page import="com.sist.dao.RboardVO"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.RboardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String curpage2=request.getParameter("page");
if(curpage2==null){
	
	curpage2="1";
}
RboardDAO dao= RboardDAO.newInstance();
int totalpage=dao.totalPage();

int curpage=Integer.parseInt(curpage2);

%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>

<h1>게시판</h1>
<hr>
<table style="text-align:left" width=800>
<tr>
<th><a href="Controll.jsp?mode=2">새글</a></th>
</tr>
</table>
<hr>
<table width=800 border=1 black solid>
<th width=15%>게시물 번호</th>
<th width=40%>제목</th>
<th width=15%>이름</th>
<th width=15%>작성일</th>
<th width=15%>조회수</th>

<% 
	List<RboardVO> list=dao.boardListDate(curpage);
	
	for(RboardVO vo:list){
		
	
%>
<tr>
<td width=15%><%=vo.getRno() %></td>
<td width=40% >
<%
if(vo.getGtap()>0){
	for(int i=0;i<vo.getGtap();i++){
		%>
		&nbsp;&nbsp;
		
		<% 
	}
	%>
	<img src="re_icon.png">
	<% 
}

%>
<a href="Controll.jsp?mode=3&page=<%=curpage %>&rno=<%=vo.getRno()%>"><%=vo.getSubject() %></a></td>
<td width=15%><%=vo.getName() %></td>
<td width=15%><%=vo.getDbday() %></td>
<td width=15%><%=vo.getHit() %></td>


</tr>
<%
	}
%>

</table>

<table>
<tr>
<td>
<%
final int block=10;

int start= //1 // 11 //21  
((curpage-1)/block*block)+1;
int end = //10 //20//30
((curpage-1)/block*block)+block;

if(totalpage<end)
{
	end=totalpage;
}

if(start>1){
	out.write("<a href=\"Controll.jsp?mode=1&page="+(start-1)+"\">&lt</a>&nbsp;&nbsp;&nbsp;");
}
for(int i=start;i<=end;i++){
	out.write("<a href=\"Controll.jsp?mode=1&page="+(i)+"\">"+i+"</a>&nbsp;&nbsp;&nbsp;");
}

if(end<totalpage){
	out.write("<a href=\"Controll.jsp?mode=1&page="+(end+1)+"\">&gt</a>&nbsp;&nbsp;&nbsp;");
	
}
%>





</td>
</tr>
</table>

</center>


</body>
</html>