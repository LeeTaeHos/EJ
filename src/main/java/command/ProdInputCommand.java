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
import util.ProdSpec;


public class ProdInputCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryDTO> dtos = dao.categoryList();
		request.setAttribute("list", dtos);
		
		// 열거형 상수 가져오기
		 ProdSpec[] pdSpecs = ProdSpec.values();
		 request.setAttribute("pdSpecs", pdSpecs);
		
		return "admin/ad_temp.jsp?pg=prod_input";

	}

}
