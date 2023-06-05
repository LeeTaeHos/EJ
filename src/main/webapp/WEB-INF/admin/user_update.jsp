<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>
   <div class="container w-50 mt-5">
      <h3>유저 수정</h3>
      <form action="prodUpdateOk.do" method="post" enctype="multipart/form-data">
      <table class="table table-borderless">
         <tbody>
            <tr>
               <td>아이디 : ${dto.id}</td>
            </tr>
            <tr>
               <td>이름 : ${dto.name}</td>
            </tr>
            <tr>
               <td>패스워드</td>
               <td>
               <td><input type="text" class="form-control form-control-sm" name="pw" value="${dto.pw}"/></td>
            </tr>
            
            <tr>
               <td>전화번호</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="pCompany" value="${dto.pCompany}"/></td>
            </tr>
            <tr>
               <td>이메일</td>
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
               <td>주소</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="pQty"value="${dto.pQty}"/></td>
            </tr>
            <tr>
               <td>수정날짜</td>
               <td><input type="text" class="form-control form-control-sm" 
               name="price"value="${dto.price}"/></td>
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