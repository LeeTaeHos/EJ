<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<section class="w-75 ps-5">
         <h3>상품 정보[${requestScope.specValue}]</h3>
         <ul class="d-flex p-0" style="list-style:none;">
            <!-- 상품 이미지 -->
            <li class="me-5">
               <img src="<c:url value="/uploadedFile/${requestScope.pDto.pImage}"/>" width="220px"/>
            </li>
            <!-- 상품 상세내용 -->
            <li>
               <form name="prodForm" method="post">
                  
                  상품번호 : <br/>
                  상품이름 : <br/>
                  상품가격 : <br/>
                  상품포인트 :<br/>
                  상품수량 : <input type="text" name="pQty" size="3" value="1"/>개<br/>
                  <p class="mt-3"><b>[상품 소개]</b><br/>
                     
                  </p>                  
                  <c:if test="${sessionScope.isLogin != null}">
                  <a href="" class="btn btn-sm btn-primary">장바구니 담기</a>
                  </c:if>                  
                  <a href="javascript:history.back();" class="btn btn-sm btn-info">계속 쇼핑하기</a>
               </form>
            </li>
         </ul>
      </section>