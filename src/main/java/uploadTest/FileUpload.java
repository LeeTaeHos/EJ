package uploadTest;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String savePath = "/uploadedFile";
      // 물리적 경로
      String realPath = request.getServletContext().getRealPath(savePath);
      
      System.out.println(realPath);
      // 사진 크기 제한
      int maxSize = 1024*1024*10; // 1kb * 1kb = 1MB*10 = 10MB

      // MultipartRequestsms request에 담겨 있는 파일(데이터)들을 실제 서버에 저장하고
      // 파일의 정보를 제공하는 클래스
                                                                               			// 파일 중복처리객체
      MultipartRequest mr = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
      
      String title = mr.getParameter("title");
      File file = mr.getFile("image");
      
      String fileName = "";
      if(file !=null) {
         // 첨부된 파일명
         fileName = file.getName();
      }
      System.out.println("title"+title);
      System.out.println("fileName"+fileName);
      
      request.setAttribute("title", title);
      request.setAttribute("fileName", fileName);
      
      RequestDispatcher rd = request.getRequestDispatcher("fileUploadTest/result.jsp");
      rd.forward(request, response);
   }

   
   
   
   
   
}