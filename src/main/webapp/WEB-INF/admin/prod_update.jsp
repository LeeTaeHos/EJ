<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
   <div class="container w-50 mt-5">
      <h3>상품 수정</h3>
      <form action="prodUpdateOk.do" method="post" enctype="multipart/form-data">
      <table class="table table-borderless">
         <tbody>
            <tr>
               <td>상품번호</td>
               <td><input  class="form-control form-control-sm" type="text" name="pNum" value="${dto.pNum}" readonly/></td>
            </tr>
            <tr>
               <td>카테고리</td>
               <td>
                  <select class="form-select form-select-sm" name="pCategory_fk">                     
                        <c:if test="${list != null || list.size != 0}">
                        	<c:forEach var="cDto" items="${list}">
		                        <option value="${cDto.code}"
		                         ${(cDto.code == dto.pCategory_fk) ? 'selected':''}>${cDto.catName}[${cDto.code}]</option>                                                            
                        	</c:forEach>
                        </c:if>
                  </select>
               </td>
            </tr>
            <tr>
               <td>상품명</td>
               <td><input type="text" class="form-control form-control-sm" name="pName" value="${dto.pName}"/></td>
            </tr>
            <tr>
               <td>제조회사</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="pCompany" value="${dto.pCompany}"/></td>
            </tr>
            <tr>
               <td>상품이미지</td>
               <td>
					<img src="<c:url value="/uploadedFile/${dto.pImage}"/>"width="100px"/>	
					<input type="file" class="form-control form-control-sm" 
               name="pImage"/>
               <!-- 이미지를 수정하지 않는 경우에는 지금 현재 이미지를 넘겨야 함. -->
					<input type="hidden" class="form-control form-control-sm" 
               name="pImageOld" value="${dto.pImage}"/>
               </td>
            </tr>
            <tr>
               <td>상품수량</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="pQty"value="${dto.pQty}"/></td>
            </tr>
            <tr>
               <td>상품가격</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="price"value="${dto.price}"/></td>
            </tr>
            <tr>
               <td>상품사양</td>
               <td>
                  <input type="text" class="form-control form-control-sm" 
               name="pSpec" value="${dto.pSpec}"/>
               </td>
            </tr>
            <tr>
               <td>상품소개</td>
               <td>
                  <textarea class="form-control" name="pContent" rows="3" >${dto.pContent}</textarea>
               </td>
            </tr>
            <tr>
               <td>상품포인트</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="pPoint"value="${dto.pPoint}"/></td>
            </tr>
            <tr>
               <td colspan="2" class="text-center">
                  <input type="submit" class="btn btn-sm btn-primary" value="상품수정"/>   
                  <input type="reset" class="btn btn-sm btn-secondary" value="취소"/>   
               </td>
            </tr>
         </tbody>         
      </table>
      </form>   
   </div>
</body>
</html>