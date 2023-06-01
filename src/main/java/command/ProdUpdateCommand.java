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


public class ProdUpdateCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pNum = request.getParameter("pNum");
		
		ProductDAO pDao = ProductDAO.getInstance();
		ProductDTO pDto = pDao.getproduct(pNum);
		
//		System.out.println("dto-----"+ dto);
		request.setAttribute("dto", pDto);
		
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryDTO> dtos = dao.categoryList();
		request.setAttribute("list", dtos);
		
		return "admin/ad_temp.jsp?pg=prod_update";
	}
}	
