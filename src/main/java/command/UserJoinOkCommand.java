package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;


public class UserJoinOkCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		
		MemberDTO mDto = new MemberDTO(id, pw, name, tel, email, addr, null);
		
		MemberDAO dao = MemberDAO.getInstance();
		int n = dao.insertMember(mDto);
		
		String viewPage = null;
		HttpSession session = request.getSession();
		
		if(n == MemberDAO.MEMBER_JOIN_SUCCESS) { 
			session.setAttribute("id", mDto.getId());
			request.setAttribute("msg", "회원가입이 정상 처리 되었습니다.");
			viewPage = "user/u_temp.jsp?pg=u_login";
		}else {
			request.setAttribute("msg", "회원가입에 문제가 있습니다.");
			viewPage = "user/u_temp.jsp?pg=u_join";
		}
		
		return viewPage;
	}
}
