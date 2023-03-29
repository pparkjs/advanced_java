package reqNresp;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 	-redirect
	 	   ==> 다른 페이지로 넘어가도록 하는 기능을 말한다.
	 	       이 기능은 응답시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 URL로 재요청하여
	 	       이동하는 방식을 말한다. (재 요청할 때는 GET방식으로 요청이기 때문에 URL의 길이에 제한이 있다.)
	 	   ==> redirect는 Request객체와 Response객체를 공유하지 못한다.
	 	   
	 	   ==> redirect는 response객체의 sendRedirect()메서드를 이용한다.
	 	       이 때 이동할 URL주소를 지정해 주는데 주소는 전체 주소를 모두 기술해야 한다.
	 */
		
		//request.setAttribute("tel", "010-9999-9999"); //redirect에서는 이런식으로 데이터를 보낼 수 없다.
		
		//이 문서에서 만들어진 데이터를 Redirect문서로 보내려면 Get방식의 파라미터로 재 구성해서 보내야 한다.
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("username");
		String tel = "010-9999-9999";
		
		//한글을 넣을때 인코딩해서 넣어줘야함.
		name = URLEncoder.encode(name, "utf-8");
		
		// Redirect를 하면서 request객체와 response객체를 삭제함 그래서 그 전에 값들 없어짐
		response.sendRedirect(request.getContextPath() +
				       "/redirectTest.do?username=" + name + "&tel=" + tel);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
