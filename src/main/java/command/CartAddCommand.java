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

public class CartAddCommand implements ShopCommand{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      HttpSession session = request.getSession();
      CartDAO shopCart = (CartDAO)session.getAttribute("shopCart");
      
      String pNum = request.getParameter("pNum");
      String pQty = request.getParameter("pQty");
      String pSpec = request.getParameter("pSpec");
      
      ProductDAO pDao = ProductDAO.getInstance();
      
      shopCart.addProduct(pDao, pNum, pQty, pSpec);
      
      System.out.println("장바구니 담기 성궁");

      return "cartList.do";

   
   
   }

}