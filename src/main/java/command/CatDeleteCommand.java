package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;


public class CatDeleteCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String catNum = request.getParameter("catNum");
		
		String viewPage = null;
		
		// 유효성 검사
				if(catNum == null || catNum.trim().equals("")) {
					return "admin/ad_temp.jsp?pg=cat_list";
				}
		
		CategoryDAO dao = CategoryDAO.getInstance();
		int n = dao.categoryDel(catNum);
		
		if(n>0) {
			request.setAttribute("msg", "카테고리삭제");
			viewPage = "catList.do";
		}else {
			request.setAttribute("msg", "카테고리삭제 실패");
			viewPage = "admin/ad_temp.jsp?pg=cat_list";
		}
		return viewPage;
	}
}
