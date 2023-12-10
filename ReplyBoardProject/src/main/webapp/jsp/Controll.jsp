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
String name="";
String mode=request.getParameter("mode");
System.out.print("mode값:"+mode);
if(mode==null){
	mode="1";
}
if(mode.equals("1")){
name="list.jsp";
System.out.print("리스트로 이동완료");
}
else if(mode.equals("2")){
	name="insert.jsp";
	
}
else if(mode.equals("3")){
	name="detail.jsp";
}
else if(mode.equals("4")){
	name="update.jsp";
	
}
else if(mode.equals("5")){
	name="answer.jsp";
	
}
else if(mode.equals("6")){
	name="delete.jsp";
	System.out.print("삭제로 이동");
}
%>
<jsp:include page="<%=name %>"></jsp:include>

</body>
</html>