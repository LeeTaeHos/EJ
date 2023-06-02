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
import model.CartDAO;
import model.CategoryDAO;
import model.ProductDAO;

public class CatDeleteCommand implements ShopCommand{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      HttpSession session = request.getSession();
      CartDAO shopCart = (CartDAO)session.getAttribute("shopCart");
      
      String pNum = null;
      
      if(request.getMethod().equals("POST")) {
         pNum = request.getParameter("pNum");      
      }
      
      if(pNum == null || pNum.trim().equals("")) {
         request.setAttribute("msg", "잘못된 경로 입니다");
         return "user/u_temp.jsp?pg=u_main";
      }
      
      shopCart.deleteProduct(pNum);
      
      return "cartList.do";
   }

}