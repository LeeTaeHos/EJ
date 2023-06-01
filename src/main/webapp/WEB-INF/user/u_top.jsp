<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<%-- <%=request.getContextPath() %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>                                         
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<header>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container">
    <%-- <a class="navbar-brand" href="<c:url value"/"/>">SHOP</a> --%>
    <%-- <a class="navbar-brand" href="<%=request.getContextPath() %>">SHOP</a> --%>
    
    <a class="navbar-brand" href="${ctxPath}">SHOP</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
      <ul class="navbar-nav w-100">
        <li class="nav-item me-auto">
          <a class="nav-link" href="${ctxPath}">쇼핑홈</a>
        </li>
        <c:if test="${sessionScope.isLogin == null}">
	        <li class="nav-item">
	          <a class="nav-link" href="userLogin.do">로그인</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="userJoin.do">회원가입</a>
	        </li>
        </c:if>
        
	         <c:if test="${sessionScope.isLogin != null}">
		        <li class="nav-item" style="color:white; line-height:40px;">
					${sessionScope.name}님 즐거운 하루 되세요!
				</li>
			</c:if>
			
	         <c:if test="${sessionScope.isLogin != null &&  sessionScope.mode == 'user'}">
	        <li class="nav-item">
	          <a class="nav-link" href="userLogout.do">로그아웃</a>
	        </li>
			</c:if>	        
	         <c:if test="${sessionScope.isLogin != null &&  sessionScope.mode == 'admin'}">
	        <li class="nav-item">
	          <a class="nav-link" href="adminLogout.do">관리자 로그아웃</a>
	        </li>
			</c:if>	        
			
        <li class="nav-item">
          <a class="nav-link" href="#"></a>
        </li>  
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
          <ul class="dropdown-menu">
          
			<c:if test="${sessionScope.isLogin != null &&  sessionScope.mode == 'admin'}">
            	<li><a class="dropdown-item" href="adminMain.do">관리자 페이지</a></li>
            </c:if>
             
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</header>