<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
  
</head>

<body>
  
 				
             <div class="container" style="float:right;">
             <div class="row">
             
             
             <c:forEach var="vo" items="${plist }">
             
             	<div class="col-md-6">
             	<div><img src="${vo.p_image } "></div>
             	<div> <c:choose>
    <c:when test="${fn:length(vo.p_name) > 20}">
        <h6>${fn:substring(vo.p_name, 0, 20)}...</h6>
    </c:when>
    <c:otherwise>
       <h6>${vo.p_name}</h6>
    </c:otherwise>
</c:choose>
    <h5>$30.00</h5></div>
             	
             	</div>
             	</c:forEach>
             	</div>
             	</div>
             
             
     <c:choose>
    <c:when test="${fn:length(vo.p_name) > 20}">
        <h6>${fn:substring(vo.p_name, 0, 20)}...</h6>
    </c:when>
    <c:otherwise>
       <h6>${vo.p_name}</h6>
    </c:otherwise>
</c:choose>
    <h5>$30.00</h5>
                              
                       
                                    
                        
                       
                        
                        
                        
                        
                        
                        
                        
                         <div class="product__pagination">
                         <c:if test="${start > 1 }">
                          <a href="main.do?ct=${ct }&page=${start-1}"><i class="">&lt;</i></a>
                       
                         </c:if>
                         <c:forEach var="i" begin="${start }" end="${end }" >
                        <a href="main.do?ct=${ct }&page=${i}">${i }</a>
                        </c:forEach>
                     
                        <c:if test="${end < totalpage }">
                     <a href="main.do?ct=${ct }&page=${end +1}"><i class="fa fa-long-arrow-right" ></i></a>
                        </c:if>
                    </div>
           
    
 
    <!-- Blog Section End -->

   

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>



</body>

</html>