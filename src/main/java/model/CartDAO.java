package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CartDAO {

	// 장바구니
	private ArrayList<ProductDTO> cartList;

	public CartDAO() {
		cartList = new ArrayList<ProductDTO>();
	}

	// 장바구니에 상품을 추가
	public void addProduct(ProductDAO pDao, String pNum, String pQty, String pSpec) {
		// 장바구니에 추가하고자하는 상품이 있는 경우 --> 수량만 추가

		// 수량
		int addQty = Integer.parseInt(pQty.trim());
		for (ProductDTO pDto : cartList) {
			if (pNum.equals(String.valueOf(pDto.getpNum()))) {
				pDto.setpQty(pDto.getpQty() + addQty);
				return;
			}
		}

		// 장바구니에 추가하고자하는 상품이 없는 경우
		ProductDTO pDto = pDao.getproduct(pNum);

		if (pDto != null) {
			pDto.setpQty(addQty);
			cartList.add(pDto);
		}

	}

	// 장바구니 모든 상품 가져오기
	public ArrayList<ProductDTO> getCartList() {

		return cartList;
	}

	// 장바구니 상품 삭제
	public void deleteProduct(String pNum) {
		for (ProductDTO pDto : cartList) {
			if (pNum.equals(String.valueOf(pDto.getpNum()))) {
				cartList.remove(pDto);
				break; // 해당 상품 삭제시 루프 탈출
			}
		}

	}

	// 장바구니 상품 수량 수정
	public void modifyProduct(String pNum, String pQty) {
		int qty = Integer.parseInt(pQty.trim());
		for (ProductDTO pDto : cartList) {
			if (pNum.equals(String.valueOf(pDto.getpNum()))) {
				if(qty <= 0) {
					// 수량이 0이하인 경우 
					cartList.remove(pDto);
					break;
				}else {
					// 수정된 수량으로 다시 세팅
					pDto.setpQty(qty);
					break;
				}
			}
		}
	}
}