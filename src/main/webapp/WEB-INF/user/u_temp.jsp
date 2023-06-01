<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	String pg = request.getParameter("pg");
	if(pg == null) pg="u_main.jsp";

	//if(session.getAttribute("isLogin") == null) pg="u_main.jsp";
%>

<jsp:include page="u_top.jsp" />
<main>
	<div class="container mt-5 d-flex">
	<%@ include file="u_left.jsp"%>
	<jsp:include page="<%=pg%>" />
	</div>
</main>
<jsp:include page="u_bottom.jsp" />
