package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;


public class CatListCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryDTO> dtos = dao.categoryList();
		
		request.setAttribute("dtos", dtos);
		
		return "admin/ad_temp.jsp?pg=cat_list";
	}
}
