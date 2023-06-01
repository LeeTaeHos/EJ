package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryDAO;
import model.CategoryDTO;
import model.ProductDAO;
import model.ProductDTO;
import util.ProdSpec;


public class ProdViewCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pNum = request.getParameter("pNum");
		String pSpec = request.getParameter("pSpec");
		
		// ProdSpec.valueOf(pSpec) --> HIT, NEW, RECOMMEND 객체 가져오기
		// specValue --> 인기, 최신, 추천
		String specValue = ProdSpec.valueOf(pSpec).getValue();
		
		ProductDAO pDao = ProductDAO.getInstance();
		ProductDTO pDto = pDao.getproduct(pNum);
		
		
		request.setAttribute("pNum", pNum);
		request.setAttribute("specValue", specValue);
		request.setAttribute("pDto", pDto);
		
		return "user/u_temp.jsp?pg=prodView";
	}
}
