package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdminDAO;
import model.AdminDTO;


public class AdminLoginOkCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		AdminDAO dao = AdminDAO.getInstance();
		int chkNum = dao.adminCheck(id, pw);
		
		// 세션 객체 만들기
		HttpSession session = request.getSession();
		
		
		if(chkNum == dao.ADMIN_LOGIN_ID_NOT) { // 아이디 존재
			request.setAttribute("loginErr", "idErr");
			
		}else if(chkNum == dao.ADMIN_LOGIN_PW_FAIL) { // 비번틀림
			request.setAttribute("loginErr", "pwErr");
			
		}else if (chkNum == dao.ADMIN_LOGIN_SUCCESS) { // 로그인 성공
			AdminDTO dto = dao.getAdminInfo(id);
			
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("isLogin", "yes");
			session.setAttribute("mode", "admin");
			
		}
		String viewPage = "ad_layout";
		
		// 로그인 실패
		if(request.getAttribute("loginErr") != null) {
			viewPage = "admin/ad_temp.jsp?pg=ad_login";
		}else {// 로그인 성공
			viewPage = "admin/ad_temp.jsp?pg=ad_main";
		}
		
		return viewPage;
	}
}
