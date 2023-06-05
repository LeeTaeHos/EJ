package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;
import model.MemberDAO;
import model.MemberDTO;
import model.ProductDAO;
import model.ProductDTO;


public class UserListCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO dao = MemberDAO.getInstance();
		ArrayList<MemberDTO> dtos = dao.getUserInfo();
		
		request.setAttribute("dtos", dtos);
		
		return "admin/ad_temp.jsp?pg=user_list";
	}
}
