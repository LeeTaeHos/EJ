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


public class SpecListCommand implements ShopCommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pSpec = request.getParameter("pSpec");
		
		ProductDAO pDao = ProductDAO.getInstance();
		ArrayList<ProductDTO> pDtos = null;
		
		pDtos = pDao.map.get(pSpec);
		String specValue = ProdSpec.valueOf(pSpec).getValue();
		
		if(pDtos.size() == 0) {
		request.setAttribute("msg", "상품이 존재하지 않습니다.");
		}else {
			request.setAttribute("pDtos", pDtos);
		}
		request.setAttribute("specValue", specValue);
		
		
		return "user/u_temp.jsp?pg=specList";
	}
}
