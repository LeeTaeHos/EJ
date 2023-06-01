package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.AdminDTO;
import model.CategoryDAO;


public class CatRegOkCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String code = request.getParameter("code");
		String cname = request.getParameter("cname");
		
		// 유효성 검사
		if(code == null || cname == null || code.trim().equals("") ||
				cname.trim().equals("")) {
			return "admin/ad_temp.jsp?pg=cat_list";
		}
		
		CategoryDAO dao = CategoryDAO.getInstance();
		int n = dao.insertCatgory(code, cname);
		
		
		String viewPage = null;
		
		if(n > 0) {
			request.setAttribute("msg", "카테고리 등록 완료");
//			viewPage = "cat_list";
			viewPage = "catList.do";
		}else {
			request.setAttribute("msg", "카테고리 등록 실패");
			viewPage = "ad_temp.jsp?pg=cat_input";
		}
		
		return viewPage;
	}
}
