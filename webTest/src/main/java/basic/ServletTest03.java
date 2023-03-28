package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	Servlet의 동작 과정...
 	1. 사용자(클라이언트)가 URL을 클릭하면 Http의 Request를 Servlet Container로 전송
 	2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할 지를 검색한다.
 		(해당 서블릿이 로딩이 안 된 경우에는 로딩하고 처음 로딩시 init()메서드를 호출한다.)
 		(Servlet1버전 3.0이상에서는 애노테이션(@WebServlet)으로 설정할 수 있다.)
 	3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성하여 해당 서블릿 객체의 servlet()메서드를 호출한다.
 	4. service()메서드는 클라이언트의 요청 방법(method)을 체크하여 적절한 메서드를 호출한다.
 	   (doGet, doPost, doDelete, doPut 등…)
 	5. 요청 및 응답 처리가 완료되면 HttpServletRequest객체와 HttpServletResponse객체는 자동으로 소멸된다.
	6. 컨테이너로부터 서블릿이 제거되는 경우에는 `destroy()`메서드가 호출된다.
 	 
 */

/**
 * Servlet implementation class ServletTest03
 */
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet : " + this.getServletName() + "에서의 init()메서드 입니다.");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()메서드 시작...");
		
		// GET방식과 POST방식을 구분하여 요청 방식에 맞는 메서드를 호출한다.
		
		// 방법1 ==> HttpServlet(부모객체)의 service()메서드로 위임하기
		// super.service(request, response);
		
		// 방법2 ==> 클라이언트의 전송 방식(GET, POST)을 구분해서 직접 메서드 호출하기
		String method = request.getMethod(); //클라이언트의 전송 방식 구하기
		System.out.println("method : " + method);
		
		if("GET".equals(method)){
			this.doGet(request, response);
//		}else if("POST".equals(method)){
		}else {
			this.doPost(request, response);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head>"
				  + "<body><h1 style='color : red;'>doGet()메서드 처리 내용</h1></body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='utf-8'></head>"
				  + "<body><h1 style='color : blue;'>doPost()메서드 처리 내용</h1></body></html>");
	}
	
	private void destory() {
		System.out.println("Servlet : " + this.getServletName() + "에서의 destory()메서드 실행...");
	}

}
