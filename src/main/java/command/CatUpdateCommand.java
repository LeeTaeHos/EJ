package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.AdminDTO;
import model.CategoryDAO;
import model.CategoryDTO;
import model.ProductDAO;
import model.ProductDTO;


public class CatUpdateCommand implements ShopCommand{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      String catNum = request.getParameter("catNum");
      
      CategoryDAO cDao = CategoryDAO.getInstance();
      CategoryDTO cDto = cDao.getCategory(catNum);
      
      request.setAttribute("dto", cDto);
      
      
      return "admin/ad_temp.jsp?pg=cat_update";
   }

}