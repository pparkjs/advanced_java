package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 실제 유저가 입력한 아이디 비번 값 가져오기
		String id = request.getParameter("userid");
		String pw = request.getParameter("userpass");
		
		HttpSession session = request.getSession();
		
		if("admin".equals(id) && "1234".equals(pw)) { // 로그인 성공
			session.setAttribute("loginId", id);
		}
		
		//sessionLogin.jsp 로 이동
		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
