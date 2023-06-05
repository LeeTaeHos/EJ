package command;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import model.AdminDAO;
import model.AdminDTO;
import model.CategoryDAO;
import model.CategoryDTO;
import model.MemberDAO;
import model.MemberDTO;
import model.ProductDAO;
import model.ProductDTO;


public class UserUpdateCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		
		MultipartRequest mr;
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberDTO mDto = mDao.MemberUpdate(id);
		
		request.setAttribute("dto", mDto);
		
		CategoryDAO dao = CategoryDAO.getInstance();
		ArrayList<CategoryDTO> dtos = dao.categoryList();
		request.setAttribute("list", dtos);
		
		return "admin/ad_temp.jsp?pg=user_update";
	}
}	
