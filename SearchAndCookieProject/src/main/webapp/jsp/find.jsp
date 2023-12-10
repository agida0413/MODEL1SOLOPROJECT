<%@page import="java.util.List"%>
<%@page import="com.sist.dao.ProductVO"%>
<%@page import="com.sist.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String fd=request.getParameter("fd");
String search=request.getParameter("search");
String curpage=request.getParameter("page");


if(fd==null){
	fd="p_name";
	
}
if(search==null){
	search="이츠독";
}

if(curpage==null){
	curpage="1";
}
%>
<center>
<h1>용품찾기</h1>
</center>


<table>
<tr>
<td>
<form method="post" action="find.jsp">
<select name="fd">
<option value="p_name" <%=fd.equals("p_name")?"selected":"" %>>이름으로</option>
<option value="p_category" <%=fd.equals("p_categoey")?"selected":"" %>>카테고리로</option>
<input type="text" name="search" value=<%=search %>>
<input type="submit" value="검색">
</form>
</select>
</td>
</tr>
</table>

		
		<%
		
		
		ProductDAO dao=ProductDAO.newInstance();
	
		List<ProductVO>list= dao.searchListData(Integer.parseInt(curpage), fd, search);
System.out.print(list.size());
		for(ProductVO vo:list) {%>
 		<div class="col-md-3">
           <div class="thumbnail">
           	<a href="cookie_before.jsp?page=<%=curpage%>&pno=<%=vo.getPno()%>&fd=<%=fd%>&search=<%=search%>">
           	<img src=<%=vo.getP_image() %>>
            <div class="caption">
            <p><%=vo.getP_name() %></p>
                  <p><h3 style="text-decoration:line-through"><%=vo.getP__price() %></h3>&nbsp;&nbsp;<sup style ="color:red"><%=vo.getP_percent() %></sup></p>
               
                  <p style="color:red"><h2><%=vo.getP_lowerprice() %></h2></p>
            </div>
            </a>
           </div>
         </div>
         				<center>
       				  <div>
					<table width=800>
					<tr>
					<%}int totalpage=dao.toatalPage(fd, search);
					System.out.println("총페이지:"+totalpage);
						final int block=10;
						int inpage=Integer.parseInt(curpage);
					int startpage= ((inpage-1)/block*block)+1;
					int endpage=((inpage-1)/block*block)+10;
					
					if(endpage>totalpage){
						endpage=totalpage;
					}
					
					if(startpage>1){
					%>	
					<td><a href="find.jsp?fd=<%=fd%>&search=<%=search%>&page=<%=startpage-1%>">이전</a></td>
					
					<% 
					}%>
					
					<%
					for(int i=startpage;i<=endpage;i++){
					%>
					<td><a href="find.jsp?fd=<%=fd%>&search=<%=search%>&page=<%=i%>"><%=i %></a></td>
					
					<%} %>
					
					<%
					if(endpage<totalpage){
					%>
						<td><a href="find.jsp?fd=<%=fd%>&search=<%=search%>&page=<%=endpage+1%>">다음</a></td>
					
					<%} %>
					
				
					
					
					
					</tr>
					</table>
						</center>
					
					
					</div>



</body>
</html>