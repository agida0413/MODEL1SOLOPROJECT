<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.sist.dao.ProductDAO"%>
<%@page import="com.sist.dao.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
request.setCharacterEncoding("UTF-8");
String pno=request.getParameter("pno");
String curpage=request.getParameter("page");
String fd=request.getParameter("fd");
String search=request.getParameter("search");

ProductDAO dao=ProductDAO.newInstance();
ProductVO vo=dao.productDetail(Integer.parseInt(pno));

Cookie cookies[]=request.getCookies();

List<ProductVO>clist =new ArrayList<ProductVO>();

if(cookies!=null){
for(int i=cookies.length-1;i>=0;i--){
		
	if(cookies[i].getName().startsWith("pno_")){
		String cpno=cookies[i].getValue();
		ProductVO cvo=dao.productCookieData(Integer.parseInt(cpno));
		
		clist.add(cvo);
		
	}
	

}


}
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<span>
<h1>최근본상품</h1>
<div>
<h3><a href="cookie_delete_all.jsp?page=<%=curpage%>&fd=<%=fd%>&search=<%=search%>&pno=<%=pno%>">전체삭제</a></h3>

<form method="post" action="cookie_delete.jsp?page=<%=curpage%>&fd=<%=fd%>&search=<%=search%>&pno=<%=pno%>">
<table>


<% 
int i=0;
for(ProductVO vo3:clist){
	if(i>4){break;}
	%>

<tr>
<td><input type="checkbox" name="del" value="<%=vo3.getPno()%>"></td>
<td><img src="<%=vo3.getP_image()%>"></td>
</tr>
<tr>
<td><%=vo3.getP_name() %></td>
</tr>

<% 

i++;

} %>

</table>
<input type=submit value="삭제">
</form>
</div>

</span>
<center>
<table width=800  solid black>
<tr>

<td rowspan=10 width=60%><img src="<%=vo.getP_image()%>"></td>
</tr>

<tr>
<th width=10%>이름</th>
<td colspan=2><%=vo.getP_name() %></td>
</tr>

<tr>
<th width=10%>원가</th>
<td style=" text-decoration:line-through" colspan=2><%=vo.getP__price() %></td>
</tr>

<tr>
<th width=10%>할인율</th>
<td style="color:red" colspan=2><%=vo.getP_percent() %></td>
</tr>

<tr>
<th width=10%>할인가</th>
<td style="color:blue" colspan=2><%=vo.getP_lowerprice() %></td>
</tr>

<tr>
<th width=10%>배송비</th>
<td colspan=2><%=vo.getP_shipment() %></td>
</tr>

<tr>
<th width=10%>재고</th>
<td colspan=2> <%=vo.getP_stack() %></td>
</tr>

<tr>
<th width=10%>카테고리</th>
<td colspan=2><%=vo.getP_category() %></td>
</tr>

<tr>
<th width=10%>유통기한</th>
<td colspan=2><%=vo.getP_expiredate() %></td>
</tr>

<tr>
<th width=10%>조회수</th>
<td style="color:blue"><%=vo.getP_hit() %></td>
<td  style="text-align:right"><a href="find.jsp?page=<%=curpage%>&fd=<%=fd%>&search=<%=search%>">목록</a></td>
</tr>

<tr>

<td colspan=4>
<img src="<%=vo.getP_detail_image() %>">
</td>
</tr>

</table>

</center>

</body>
</html>