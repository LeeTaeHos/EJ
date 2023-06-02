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
				<%@include file="cart.jsp"%>
	</c:forEach>
	</div>
	</c:if>
</section>

