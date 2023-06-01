<%@page import="util.ProdSpec"%>
<%@page import="model.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 숫자를 천단위로 끊는다 -->

<section class="w-75 ps-5 ">
	<c:if test="${pDtos.size() == 0}">
		<p>상품이 존재하지 않습니다.</p>
	</c:if>
	<c:if test="${pDtos.size() != 0}">
		<h3>[${requestScope.catName}] 상품</h3>

		<div class="d-flex">
			<c:set var="cnt" value="0" />

			<c:forEach var="pDto" items="${requestScope.pDtos}">
				<c:set var="cnt" value="${cnt+1}" />

				<!-- Card -->

				<div class="card me-2" style="width: 200px">
					<a href="prodView.do?pNum=${pDto.pNum}&pSpec=${pDto.pSpec}"
						style="height: 120px; overflow: hidden;"> <img
						class="card-img-top"
						src="<c:url value="/uploadedFile/${pDto.pImage}"/>"
						alt="Card image" style="width: 100%;">
					</a>
					<div class="card-body">
						<h4 class="card-title" style="font-size: 15px;">
							<b>상품명 : ${pDto.pName}</b>
						</h4>
						<!-- 천단위로 끊음 -->
						<p class="card-text">
							가격 :
							<fmt:formatNumber value="${pDto.price}" />
						</p>
						<p class="card-text">
							포인트 :
							<fmt:formatNumber value="${pDto.pPoint}" />
						</p>
						<a href="#" class="btn btn-primary" style="width: 100%">담기</a>
					</div>
				</div>
				<!-- Card End -->
				<c:if test="${cnt%4 == 0}">
		</div>
		<div class="d-flex mt-3">
	</c:if>
	</c:forEach>
	</div>
	</c:if>
</section>

