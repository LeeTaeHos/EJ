package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;
import model.ProductDAO;
import model.ProductDTO;
import util.ProdSpec;


public class CategoryListCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String catName = request.getParameter("catName");
		
		ProductDAO pDao = ProductDAO.getInstance();
		ArrayList<ProductDTO> pDtos = null;
		
		pDtos = pDao.getProdByCategory(code);
		
		request.setAttribute("pDtos", pDtos);
		request.setAttribute("catName", catName);
		
		
		return "user/u_temp.jsp?pg=categoryList";
	}
}
