<%@page import="util.ProdSpec"%>
<%@page import="model.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- 숫자를 천단위로 끊는다 -->

<section class="w-75 ps-5 ">
	<h4>쇼핑몰에 오신 것을 환영합니다.</h4>
	<%
	ProductDAO pDao = ProductDAO.getInstance();
	ArrayList<ProductDTO> pDtos = null;

	ProdSpec[] pdSpecs = ProdSpec.values();
	for (ProdSpec ps : pdSpecs) {
		String key = ps.name(); // HIT, NEW, RECOMMEND
		// 상품 사양별 리스트 가져오기
		pDtos = pDao.getProdBySpec(key);
		System.out.println(pDtos);

		pageContext.setAttribute("ps", ps);
		pageContext.setAttribute("pDtos", pDtos);
	%>
	<c:if test="${pDtos.size() == 0}">
		${ps.getValue()} 상품이 없습니다...
	</c:if>

	<c:if test="${pDtos.size() != 0}">
		<h4 class="mt-3">${ps.getValue()}상품</h4>
		<div class="d-flex">
			<c:set var="cnt" value="0" />

			<c:forEach var="pDto" items="${pDtos}">
			<%@include file="cart.jsp"%>
	</c:forEach>
	</div>
	</c:if>

	<%
	} // for문
	%>
</section>

