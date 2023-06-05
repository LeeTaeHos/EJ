<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <c:if test="${requestScope.msg != null}">
   <script>
      alert("${requestScope.msg}");
   </script>
</c:if>
    
   <div class="container w-50 mt-5">
      <h3>카테고리 수정</h3>
      <form action="catUpdateOk.do" method="post" enctype="multipart/form-data"> 
      <table class="table table-borderless">
         <tbody>

         <tr>
               <td>번호</td>
               <td><input  class="form-control form-control-sm" type="text" name="cat_num" value="${dto.catNum}" readonly/></td>
            </tr>

            <tr>
               <td>카테고리 코드</td>
               <td><input type="text" class="form-control form-control-sm" name=code value="${dto.code}"/></td>
            </tr>
            
            <tr>
               <td>카테고리명</td>
               <td><input type="text" class="form-control form-control-sm" name="cat_name" value="${dto.catName}"/></td>
            </tr>

            <tr>
               <td colspan="2" class="text-center">
                  <input type="submit" class="btn btn-sm btn-primary" value="카테고리 수정"/>   
                  <input type="reset" class="btn btn-sm btn-secondary" value="취소"/>   
               </td>
            </tr>
         </tbody>         
      </table>
      </form>   
   </div>