package frontController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.ShopCommand;

/**
 * Servlet implementation class FrontController
 */
//@WebServlet("*.do")
public class FrontController extends HttpServlet {
		// key value 값으로 주소를 저장
	private HashMap<String, ShopCommand> cmdMap = new HashMap<String, ShopCommand>();

	@Override
	public void init() throws ServletException {
		String config = getInitParameter("config");
		Properties prop = new Properties();
		
		// bbs.properties가 있는 실제 물리경로를 얻어오기
		String configPath = getServletContext().getRealPath(config);

		// InputStream
		FileReader fis;
		try {
			fis = new FileReader(configPath);
			// bbs.properties 내용을 모두 메모리로 로딩(key value 형태로)
			prop.load(fis);
		} catch ( Exception e) {
			e.printStackTrace();
		}

		Iterator keyIter = prop.keySet().iterator();
		
		boolean isCreate = true;
		ShopCommand cmdInstance = null, moveInstance = null;

		// key값만 가져오기
		while (keyIter.hasNext()) {
			String cmd = (String) keyIter.next();
			System.out.println("cmd : " + cmd);
			
			// key에 해당 value값 가져오기
			String cmdClassName = prop.getProperty(cmd);
			System.out.println("cmdClassName : " + cmdClassName);
			
			if(!isCreate) {
				if(!cmdClassName.equals("command.MoveCommand")) {
					isCreate = true;
				}
			}
			
			try {
				/*
				 # 일반적인 객체 생성
				  import e.A;
				  A a = new A();
				 
				 # 동적 객체 생성
//				Class<?> clazz = Class.forName("e.A");
				 A a = (A)clazz.newInstance();
				 */
				
				if(isCreate) {
					Class<?> clazz = Class.forName(cmdClassName);
					cmdInstance = (ShopCommand)clazz.getDeclaredConstructor().newInstance();
	//				Bcommand cmdInstance = (Bcommand)clazz.newInstance();
				}
				
				if(cmdClassName.equals("command.MoveCommand")) {
					if(moveInstance == null) {
						moveInstance = cmdInstance;
					}
					cmdMap.put(cmd, moveInstance);
					isCreate = false;
				}else {
					cmdMap.put(cmd, cmdInstance);
				}
				
				ShopCommand cmdInstanse2 = cmdMap.get(cmd);
				System.out.println("cmdInstanse2 : " + cmdInstanse2);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 클라이언트 요청 파악하기
		String uri = request.getRequestURI();

		// Context Path
		String ctxPath = request.getContextPath();

		// Client가 요청한 명령 구하기
		String cmd = uri.substring(ctxPath.length());
		
		request.setAttribute("cmd", cmd);
		
		ShopCommand commandInstance = cmdMap.get(cmd);
		String viewPage = commandInstance.execute(request, response);
		
		RequestDispatcher rd = null;
		if (viewPage.indexOf(".do") != -1) // ".do" 문자열이 포함되어 있으면
		{	
			rd = request.getRequestDispatcher(viewPage);
//			response.sendRedirect(viewPage);
		} else {
			rd = request.getRequestDispatcher("WEB-INF/" + viewPage + ".jsp");
		}
		rd.forward(request, response);
	}

}