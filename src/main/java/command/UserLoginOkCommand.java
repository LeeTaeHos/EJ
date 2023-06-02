package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.MemberDAO;
import model.MemberDTO;


public class UserLoginOkCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		int chkNum = dao.memberCheck(id, pw);
		
		// 세션 객체 만들기
		HttpSession session = request.getSession();
		
		
		if(chkNum == dao.MEMBER_LOGIN_ID_NOT) { // 아이디 존재
			request.setAttribute("loginErr", "idErr");
			
		}else if(chkNum == dao.MEMBER_LOGIN_PW_FAIL) { // 비번틀림
			request.setAttribute("loginErr", "pwErr");
			
		}else if (chkNum == dao.MEMBER_LOGIN_SUCCESS) { // 로그인 성공
			MemberDTO dto = dao.getMemberInfo(id);
			
			String name = dto.getName();
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("isLogin", "yes");
			session.setAttribute("mode", "user");
			
			// 장바구니 생성
			CartDAO shopCart = new CartDAO();
			session.setAttribute("shopCart", shopCart);
			
		}
		String viewPage = null;
		
		// 로그인 실패
		if(request.getAttribute("loginErr") != null) {
			viewPage = "user/u_temp.jsp?pg=u_login";
		}else {// 로그인 성공
			viewPage = "user/u_temp.jsp?pg=u_main";
		}
		
		return viewPage;
	}
}
