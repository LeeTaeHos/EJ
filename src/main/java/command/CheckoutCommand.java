 package command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import model.MemberDAO;
import model.MemberDTO;
import model.ProductDAO;
import model.ProductDTO;

public class CheckoutCommand implements ShopCommand{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      HttpSession session = request.getSession();
      CartDAO shopCart = (CartDAO)session.getAttribute("shopCart");
      
      ArrayList<ProductDTO> pDtos = shopCart.getCartList();
      
      // 구매자(로그인한 유저) 정보 가져오기
      String id = (String)session.getAttribute("id");
      MemberDAO mDao = MemberDAO.getInstance();
      MemberDTO mDto = mDao.getMemberInfo(id);
      
      
      request.setAttribute("dtos", pDtos);
      request.setAttribute("mDto", mDto);
      
      return "user/u_temp.jsp?pg=checkout";

   
   
   }

}