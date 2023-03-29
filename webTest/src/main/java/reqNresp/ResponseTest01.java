package reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
		 - forward
		   ==> 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
		       이 때 처음 요청할 때 생성됐던 REquest객체와 Response객체를 같이 사용할 수 있다.
		       또 클라이언트의 URL주소는 처음 요청할 때의 주소가 바뀌지 않고 redirect보다 성능이 좋다.
		       단, 서버 내부에서만 접근이 가능하다.
	*/
		
		// 만약 현재 문서에서 만든 데이터를 forward로 이동한 문서에서 사용하려면
		// Request객체의 setAttribute()메서드를 이용해서 데이터를 셋팅해서 보내면 된다.
		// 형식) request.setAttribute("키값", 데이터)
		request.setAttribute("tel", "010-1234-5678");
		
		// forward로 이동하기 ==> 이동할 주소를 지정해 준다.
		// (이동할 주소는 전체 URL주소 중 'ContextPath' 이후의 경로를 기술하면 된다.)
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTest.do");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
