package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;

public class ProductDAO {   
   private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   DataSource ds;
   
   private static ProductDAO instance = null;
   
   private ProductDAO() {
      try{
         Context ic = new InitialContext();
         Context ctx = (Context)ic.lookup("java:comp/env");
         ds = (DataSource)ctx.lookup("jdbc/Oracle11g");
      } catch (NamingException e) {         
         e.printStackTrace();
      }
   }
   
   public static ProductDAO getInstance() {
      if(instance == null) {
         instance = new ProductDAO();
      }
      
      return instance;
   }
   
   public Connection getConnection() {
      try {
         conn = ds.getConnection();
         System.out.println("DB 연결 성공!!");
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return conn;
   }
   
   
   // 자원반납
   public void dbClose() {
      try {
         if(rs!=null) rs.close();
         if(ps!=null) ps.close();
         if(conn!=null) conn.close();
      }catch(SQLException e) {
         e.printStackTrace();
      }
   }
   
   
   ////////////////////////////////////
   // 상품 등록
   public int insertProduct(MultipartRequest mr) {
      String sql = "INSERT INTO product VALUES(prod_seq.nextVal,?,?,?,?,?,?,?,?,?,sysdate)";
      
      conn = getConnection();
      
      int n = -1;
      try {
         ps = conn.prepareStatement(sql);
         
         String pName = mr.getParameter("pName");
         String pCategory_fk = mr.getParameter("pCategory_fk");
         String pCompany = mr.getParameter("pCompany");
         String pImage = mr.getFilesystemName("pImage");
         String pQty = mr.getParameter("pQty");
         String price = mr.getParameter("price");
         String pSpec = mr.getParameter("pSpec");
         String pContent = mr.getParameter("pContent");
         String pPoint = mr.getParameter("pPoint");
         
         ps.setString(1,pName);
         ps.setString(2,pCategory_fk);
         ps.setString(3,pCompany);
         ps.setString(4,pImage);
         ps.setString(5,pQty);
         ps.setString(6,price);
         ps.setString(7,pSpec);
         ps.setString(8,pContent);
         ps.setString(9,pPoint);
      
         n = ps.executeUpdate();
         
      } catch (SQLException e) {         
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return n;
   }
   
   // 상품 리스트 
   public ArrayList<ProductDTO> productList(){
      ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
      
      String sql = "select * from product order by pNum DESC";
      
      conn = getConnection();
      
      try {         
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            int pNum = rs.getInt("pNum");
            String pName = rs.getString("pName");
            String pCategory_fk = rs.getString("pCategory_fk");
            String pCompany = rs.getString("pCompany");
            String pImage = rs.getString("pImage");
            int pQty = rs.getInt("pQty");
            int price = rs.getInt("price");
            String pSpec = rs.getString("pSpec");
            String pContent = rs.getString("pContent");
            int pPoint = rs.getInt("pPoint");
            String pInputDate = rs.getString("pInputDate");
            
            
            // dto로 묶기
            ProductDTO dto = new ProductDTO(pNum, pName, pCategory_fk, pCompany, pImage, pQty, price, pSpec, pContent, pPoint, pInputDate, 0, 0);
            list.add(dto);
         }
      } catch (SQLException e) {         
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return list;
   }
   
   public HashMap<String, ArrayList<ProductDTO>> map = new HashMap<String, ArrayList<ProductDTO>>();
   
   // 상품 사양별 리스트 가져오기
   public ArrayList<ProductDTO> getProdBySpec(String pSpec){
      ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
      
      String sql = "select * from product where pSpec = ?";
      
      conn = getConnection();
      
      try {         
         ps = conn.prepareStatement(sql);
         ps.setString(1, pSpec);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            int pNum = rs.getInt("pNum");
            String pName = rs.getString("pName");
            String pCategory_fk = rs.getString("pCategory_fk");
            String pCompany = rs.getString("pCompany");
            String pImage = rs.getString("pImage");
            int pQty = rs.getInt("pQty");
            int price = rs.getInt("price");
            pSpec = rs.getString("pSpec");
            String pContent = rs.getString("pContent");
            int pPoint = rs.getInt("pPoint");
            String pInputDate = rs.getString("pInputDate");
            
            
            // dto로 묶기
            ProductDTO dto = new ProductDTO(pNum, pName, pCategory_fk, pCompany, pImage, pQty, price, pSpec, pContent, pPoint, pInputDate, 0, 0);
            list.add(dto);
         }
         // 사양별로 리스트에 담음.(상품사양별로 해쉬맵에 추가)
         map.put(pSpec, list);

      } catch (SQLException e) {         
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return list;
   }
   
   // 카테고리별 상품 리스트 가져오기
   public ArrayList<ProductDTO> getProdByCategory(String code){
	   ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
	   
	   String sql = "select * from product where pCategory_fk = ?";
	   conn = getConnection();
	   
	   try {         
		   ps = conn.prepareStatement(sql);
		   ps.setString(1, code);
		   rs = ps.executeQuery();
		   
		   while(rs.next()) {
			   int pNum = rs.getInt("pNum");
			   String pName = rs.getString("pName");
			   String pCategory_fk = rs.getString("pCategory_fk");
			   String pCompany = rs.getString("pCompany");
			   String pImage = rs.getString("pImage");
			   int pQty = rs.getInt("pQty");
			   int price = rs.getInt("price");
			   String pSpec = rs.getString("pSpec");
			   String pContent = rs.getString("pContent");
			   int pPoint = rs.getInt("pPoint");
			   String pInputDate = rs.getString("pInputDate");
			   
			   
			   // dto로 묶기
			   ProductDTO dto = new ProductDTO(pNum, pName, pCategory_fk, pCompany, pImage, pQty, price, pSpec, pContent, pPoint, pInputDate, 0, 0);
			   list.add(dto);
		   }
		   // 카테고리별로 리스트에 담음.(카테고리별 상품을 해쉬맵에 추가)
//		   map.put(code, list);
		   
	   } catch (SQLException e) {         
		   e.printStackTrace();
	   } finally {
		   dbClose();
	   }
	   
	   return list; 
   }
////   
//   관리자 정보 가져오기
   public AdminDTO getAdminInfo(String id) {
      AdminDTO dto = null;
      String sql = "SELECT * FROM tbl_admin WHERE id = ?";
      
      conn = getConnection();      
      
      try {         
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         rs = ps.executeQuery();
                  
         if(rs.next()) {
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
////   
////   상품 수정
   public int updateProduct(MultipartRequest mr) {
	   
	   String pName = mr.getParameter("pName");
       String pCategory_fk = mr.getParameter("pCategory_fk");
       String pCompany = mr.getParameter("pCompany");
       // 이미지를 수정했을 경우
       String pImage = mr.getFilesystemName("pImage");
       
       // 이미지 수정 안했을 경우
       if(pImage == null) {
    	   pImage = mr.getParameter("pImageOld");
       }
       
       String pQty = mr.getParameter("pQty");
       String price = mr.getParameter("price");
       String pSpec = mr.getParameter("pSpec");
       String pContent = mr.getParameter("pContent");
       String pPoint = mr.getParameter("pPoint");
       String pNum = mr.getParameter("pNum");
       
      String sql="UPDATE product SET pName=?, pCategory_fk=?, pCompany=?,"
      		+ "pImage=?,pQty=?,price=?,pSpec=?,pContent=?,pPoint=?, pInputDate=sysdate "
            + "WHERE pNum = ?";
      
      conn = getConnection();
      int n = -1;
      
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, pName);
         ps.setString(2, pCategory_fk);
         ps.setString(3, pCompany);
         ps.setString(4, pImage);
         ps.setString(5, pQty);
         ps.setString(6, price);
         ps.setString(7, pSpec);
         ps.setString(8, pContent);
         ps.setString(9, pPoint);
         ps.setString(10, pNum);
         
         n = ps.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return n;
   }
   
   
   
//   상품 삭제
   public int delProduct(String pNum) {
      String sql = "DELETE FROM product WHERE pNum = ?";
      conn = getConnection();
      int n = -1;
      try {
         ps = conn.prepareStatement(sql);
         ps.setString(1, pNum);
         n = ps.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return n; 
   }
   
//   
//   // 조회수 추가
//   public void plusHit(int bid) {
//      String sql = "UPDATE board SET bhit=bhit+1 WHERE bid = ?";
//      
//      conn = getConnection();
//      try {
//         ps = conn.prepareStatement(sql);
//         ps.setInt(1, bid);
//         
//         ps.executeUpdate();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } finally {
//         dbClose();
//      }
//   }
/////////////////// 상품 정보 가져오기 ////////////////
   public ProductDTO getproduct(String pNum) {
	   ProductDTO dto = null;
      String sql = "SELECT * FROM Product WHERE pNum = ?";
      conn = getConnection();      
      
      try {         
         ps = conn.prepareStatement(sql);
         ps.setString(1, pNum);
         rs = ps.executeQuery();
         
         // 한회원에 대한 정보만 가져오기 때문에
         // while문 필요없음
         if(rs.next()) {
             int pNo = rs.getInt("pNum");
             String pName = rs.getString("pName");
             String pCategory_fk = rs.getString("pCategory_fk");
             String pCompany = rs.getString("pCompany");
             String pImage = rs.getString("pImage");
             int pQty = rs.getInt("pQty");
             int price = rs.getInt("price");
             String pSpec = rs.getString("pSpec");
             String pContent = rs.getString("pContent");
             int pPoint = rs.getInt("pPoint");
             String pInputDate = rs.getString("pInputDate");
             
             
             // dto로 묶기
             dto = new ProductDTO(pNo, pName, pCategory_fk, pCompany, pImage, pQty, price, pSpec, pContent, pPoint, pInputDate, 0, 0);
          }
      } catch (SQLException e) {         
         e.printStackTrace();
      } finally {
         dbClose();
      }
      
      return dto;
   }
//   
//   // 댓글 등록
//   public int addReply(String bid, String bwriter, String btitle, 
//         String bcontent, String bgroup, String bstep,String bindent) {
//      
//      replyOrder(bgroup, bstep);
//      
//      String sql = "INSERT INTO board VALUES(bbs_seq.nextVal,?,?,?,sysdate,0,?,?,?)";
//      
//      conn = getConnection();
//      
//      int cnt = -1;
//      try {
//         ps = conn.prepareStatement(sql);
//         
//         ps.setString(1,btitle);
//         ps.setString(2,bwriter);
//         ps.setString(3,bcontent);
//         ps.setString(4,bgroup);
//         ps.setInt(5,Integer.parseInt(bstep)+1);
//         ps.setInt(6,Integer.parseInt(bindent)+1);
//      
//         cnt = ps.executeUpdate();
//         
//      } catch (SQLException e) {         
//         e.printStackTrace();
//      } finally {
//         dbClose();
//      }
//      
//      return cnt;
//   }   
//   
//   // 댓글 정렬
//   public void replyOrder(String bgroup, String bstep) {
//      String sql = "UPDATE board SET bstep=bstep+1 WHERE bgroup=? and bstep > ?";
//      conn = getConnection();
//      
//      try {
//         ps = conn.prepareStatement(sql);
//         
//         ps.setString(1,bgroup);
//         ps.setInt(2,Integer.parseInt(bstep));         
//      
//         ps.executeUpdate();
//         
//      } catch (SQLException e) {         
//         e.printStackTrace();
//      } finally {
//         dbClose();
//      }
//      
//   }

   
}