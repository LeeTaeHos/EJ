package command;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.AdminDAO;
import model.AdminDTO;
import model.CategoryDAO;
import model.ProductDAO;

public class ProdRegisterOkCommand implements ShopCommand{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      String savePath = "/uploadedFile";
      String realPath = request.getServletContext().getRealPath(savePath);
      
      System.out.println(realPath);
      
      int maxSize = 1024*1024*10; // 1kb * 1kb = 1MB*10 = 10MB
      
      // MultipartRequestsms  request에 담겨 있는 파일(데이터)들을 실제 서버에 저장하고
      // 파일의 정보를 제공하는 클래스
                                                                               // 파일 중복처리객체
      String viewPage = null;
      MultipartRequest mr = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

      ProductDAO dao = ProductDAO.getInstance();
      int n = dao.insertProduct(mr);
      
      // 
      if(n>0) {
         request.setAttribute("msg", "상품 등록완료");
//         viewPage = "cat_list";
         viewPage = "prodList.do";
      }else { 
         request.setAttribute("msg", "상품 등록 실풰");
         viewPage = "ad_temp.jsp?pg=prod_input";
      }
      
      return viewPage;
   }

}