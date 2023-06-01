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

public class CategoryDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	DataSource ds;
	
	private static CategoryDAO instance = null;
	
	private CategoryDAO() {
		try{
	         Context ic = new InitialContext();
	         Context ctx = (Context)ic.lookup("java:comp/env");
	         ds = (DataSource)ctx.lookup("jdbc/Oracle11g");
	      } catch (NamingException e) {         
	         e.printStackTrace();
	      }
			
	}
	
	public static CategoryDAO getInstance() {
		if(instance == null) {
			instance = new CategoryDAO();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	// 자원반납
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/////////////////////////////////////////

	// 카테고리 저장
	public int insertCatgory(String code, String cname) {
		String sql = "insert into category values(cat_seq.nextVal,?,?)";

		getConnection();

		int n = -1;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, code);
			ps.setString(2, cname);

			// 정상적으로 수행되면 리턴값은 0보다 큼
			n = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return n;
	}

	// 카테고리 리스트
	public ArrayList<CategoryDTO> categoryList() {
		ArrayList<CategoryDTO> list = new ArrayList<CategoryDTO>();

		String sql = "select * from category order by cat_num desc";
		
		getConnection();

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int catNum = rs.getInt("cat_num");
				String code = rs.getString("code");
				String catName = rs.getString("cat_name");

				// dto로 묶기
				CategoryDTO dto = new CategoryDTO(catNum, code, catName);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}
//
////	관리자 정보 가져오기
//	public AdminDTO getAdminInfo(String id) {
//		AdminDTO dto = null;
//
//		String sql = "select * from tbl_admin where id = ?";
//		conn = getConnection();
//
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, id);
//			rs = ps.executeQuery();
//
//			if (rs.next()) {
//				dto = new AdminDTO();
//				dto.setId(rs.getString("id"));
//				dto.setPassword(rs.getString("password"));
//				dto.setName(rs.getString("name"));
//				dto.setEmail(rs.getString("email"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//		return dto;
//	}
//
//	// 회원 수정
//	public int update(String bid, String bwriter, String btitle, String bcontent) {
//		String sql = "Update board set bwriter=?, btitle=?, bcontent=? where bid=?";
//		getConnection();
//		int cnt = -1;
//
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, bwriter);
//			ps.setString(2, btitle);
//			ps.setString(3, bcontent);
//			ps.setString(4, bid);
//
//			cnt = ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//
//		return cnt;
//	}
//
	// 게시글 삭제
	public int categoryDel(String catNum) {
		String sql = "delete from category where cat_num = ?";
		getConnection();
		int n = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, catNum);
			n = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return n;
	}
//
//	// 조회수 추가
//	public void plusHit(int bid) {
//		String sql = "update board set bhit=bhit+1 where bid = ?";
//
//		getConnection();
//
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, bid);
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//	}
//	// -------------------------------------------------------------------
//
//	// 답글 처리
//	public AdminDTO replyView(int bid) {
//
//		AdminDTO dto = null;
//		String sql = "select * from board where bid = ?";
//		getConnection();
//
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, bid);
//			rs = ps.executeQuery();
//
//			if (rs.next()) {
//				bid = rs.getInt("bid");
//				String btitle = rs.getString("btitle");
//				String bwriter = rs.getString("bwriter");
//				String bcontent = rs.getString("bcontent");
//				Timestamp regDate = rs.getTimestamp("regDate");
//
//				int bhit = rs.getInt("bhit");
//				int bgroup = rs.getInt("bgroup");
//				int bstep = rs.getInt("bstep");
//				int bindent = rs.getInt("bindent");
//
//				// dto로 묶기
//				dto = new AdminDTO();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//		return dto;
//	}
//
//	// 답글 등록
//	public int addReply(String bid, String bwriter, String btitle, String bcontent, String bgroup, String bstep,
//			String bindent) {
//
//		replyOrder(bgroup, bstep);
//
//		String sql = "insert into board values" + "(bbs_seq.nextVal,?,?,?,sysdate,0,?,?,?)";
//
//		getConnection();
//
//		int cnt = -1;
//		try {
//			ps = conn.prepareStatement(sql);
//
//			ps.setString(1, btitle);
//			ps.setString(2, bwriter);
//			ps.setString(3, bcontent);
//			ps.setString(4, bgroup);
//			ps.setInt(5, Integer.parseInt(bstep) + 1);
//			ps.setInt(6, Integer.parseInt(bindent) + 1);
//
//			// 정상적으로 수행되면 리턴값은 0보다 큼
//			cnt = ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//		return cnt;
//	}
//
//	// 댓글 정렬
//	public void replyOrder(String bgroup, String bstep) {
//		String sql = "update board set bstep = bstep+1 where bgroup=? and bstep > ?";
//		getConnection();
//
//		try {
//			ps = conn.prepareStatement(sql);
//
//			ps.setString(1, bgroup);
//			ps.setInt(2, Integer.parseInt(bstep));
//
//			// 정상적으로 수행되면 리턴값은 0보다 큼
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//	}
//
}
