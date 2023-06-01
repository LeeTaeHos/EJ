<%@page import="util.ProdSpec"%>
<%@page import="model.CategoryDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
ul {
	list-style: none;
}

li>a {
	text-decoration: none;
}
</style>


<aside class="border-end" style="width: 200px">
	<h4>카테고리</h4>
	<ul>
		<%
	CategoryDAO cDao = CategoryDAO.getInstance();
	ArrayList<CategoryDTO> cDtos = cDao.categoryList();
	
//	if(cDtos != null || cDtos.size() != 0){
	if(cDtos.size() != 0){
		
//		System.out.println(cDtos.size());
//		System.out.println("cDtos"+ cDtos);
		
		for(CategoryDTO cDto:cDtos){
			String catName = cDto.getCatName();
			String code = cDto.getCode();
			pageContext.setAttribute("code", code);
			pageContext.setAttribute("catName", catName);
	%>
		<li><a href="categoryList.do?code=${code}&catName=${catName}">${catName}</a></li>
		<%}// for문
	}else{//cDtos == null && cDtos.size() == 0
			out.print("<li>카테고리 없음</li>");
		} // if문
		out.print("</ul><ul class='mt-3'>");
		ProdSpec[] pdSpecs = ProdSpec.values();
		for(ProdSpec ps : pdSpecs){
			pageContext.setAttribute("ps", ps);
		%>
			<li>
				<a href="specList.do?pSpec=${ps.name()}">${ps.getValue()}상품</a>
			</li>
		<%
		}// for문			
		%>
	</ul>
</aside>

