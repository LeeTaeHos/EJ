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

public class AdminDAO {
	public static final int ADMIN_LOGIN_SUCCESS = 1;
	public static final int ADMIN_LOGIN_PW_FAIL = 0;
	public static final int ADMIN_LOGIN_ID_NOT = -1;
	
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	DataSource ds;
	
	private static AdminDAO instance = null;
	
	private AdminDAO() {
		try{
	         Context ic = new InitialContext();
	         Context ctx = (Context)ic.lookup("java:comp/env");
	         ds = (DataSource)ctx.lookup("jdbc/Oracle11g");
	      } catch (NamingException e) {         
	         e.printStackTrace();
	      }
			
	}
	
	public static AdminDAO getInstance() {
		if(instance == null) {
			instance = new AdminDAO();
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
	// 관리자 로그인 체크
	public int adminCheck(String id, String pw) {
		int n = 0;
		String dbpw;
		String sql = "select password from tbl_admin where id = ?";

		getConnection();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if(rs.next()) { // 아이디 존재
				dbpw = rs.getString("password");
				if(dbpw.equals(pw)) { // 로그인 성공 (비번일치)
					n = ADMIN_LOGIN_SUCCESS;
				}else { // 비번 불일치
					n = ADMIN_LOGIN_PW_FAIL;
				}
			}else {// 아이디 없음
				n = ADMIN_LOGIN_ID_NOT;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return n;
	}


//	/////////////////////////////////////////
//
//	// 글 저장
//	public int write(String bwriter, String btitle, String bcontent) {
//		String sql = "insert into board values(bbs_seq.nextVal,?,?,?,sysdate,0,bbs_seq.currVal,0,0)";
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
//	// 게시글 리스트
////	public ArrayList<AdminDTO> list() {
//	public ArrayList<AdminDTO> list(int currentPage, int limit) {
//		ArrayList<AdminDTO> list = new ArrayList<AdminDTO>();
//
////		String sql = "select * from board order by bid desc";
////		String sql = "select * from board order by bgroup DESC, bstep ASC";
//		String sql = "select * from(select bbs.*,rownum rnum "
//				+ "from (select * from board order by bgroup DESC, bstep ASC) bbs) "
//				+ "where rnum between ? and ?";
//		
//		getConnection();
//
//		try {
//			// 페이지 시작 게시글번호
//			int startRow = (currentPage-1) * limit +1;
//			
//			// 페이지 마지막 게시글번호
//			int endRow = startRow + limit -1;
//			
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, startRow);
//			ps.setInt(2, endRow);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				int bid = rs.getInt("bid");
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
//				AdminDTO dto = new AdminDTO();
//				list.add(dto);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			dbClose();
//		}
//		return list;
//	}
//
////	관리자 정보 가져오기
	public AdminDTO getAdminInfo(String id) {
		AdminDTO dto = null;

		String sql = "select * from tbl_admin where id = ?";
		conn = getConnection();

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto = new AdminDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return dto;
	}
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
//	// 게시글 삭제
//	public int delete(String bid) {
//		String sql = "delete from board where bid = ?";
//		getConnection();
//		int cnt = -1;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, bid);
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
