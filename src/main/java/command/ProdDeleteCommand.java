package command;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;
import model.ProductDAO;


public class ProdDeleteCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pNum = request.getParameter("pNum");
		String pImage = request.getParameter("pImage");
		
		String realPath = request.getServletContext().getRealPath("uploadedFile");
		
		File delFile = null;
		
		// 서버에 있는 이미지 파일 삭제
		if(pImage !=null) {
			// 삭제할 파일이 되는 것
			delFile = new File(realPath + "/" + pImage);
			if(delFile.exists()) {
				if(delFile.delete())System.out.println("----이미지 파일 삭제 완료----");;
			}
		}
		
		// DB 삭제
		ProductDAO dao = ProductDAO.getInstance();
		int n = dao.delProduct(pNum.trim());
		
		String viewPage = null;
		
		if(n>0) {
			request.setAttribute("msg", "상품 삭제 완료");
			viewPage = "prodList.do";
		}else {
			request.setAttribute("msg", "상품 삭제 실패");
			viewPage = "ad_temp.jsp?pg=prod_list";
		}
		return viewPage;
	}
}
