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


public class ProdListCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO dao = ProductDAO.getInstance();
		ArrayList<ProductDTO> dtos = dao.productList();
		
//		System.out.println("dtos-------"+dtos);
		
		request.setAttribute("dtos", dtos);
		
		return "admin/ad_temp.jsp?pg=prod_list";
	}
}
