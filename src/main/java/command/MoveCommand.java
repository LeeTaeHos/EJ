package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MoveCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = (String)request.getAttribute("cmd");
		
		String viewPage = null;
		if(cmd.equals("/adminLogin.do")) {
			viewPage = "admin/ad_temp.jsp?pg=ad_login";
		}else if(cmd.equals("/adminMain.do")) {
			viewPage = "admin/ad_temp.jsp?pg=ad_main";
		}else if(cmd.equals("/adminCatInput.do")) {
			viewPage = "admin/ad_temp.jsp?pg=cat_input";
		}else if(cmd.equals("/userLogin.do")) {
			viewPage = "user/u_temp.jsp?pg=u_login";
		}else if(cmd.equals("/userJoin.do")) {
			viewPage = "user/u_temp.jsp?pg=u_join";
		}
		
		return viewPage;
	}
}
