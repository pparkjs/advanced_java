package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDel.do")
public class SessionDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//Session 삭제하기
		
		// 1. Session객체 구하기
		HttpSession session = request.getSession();
		
		// 2. 개별적인 Session값 삭제하기 : Session객체의 removeAttribute()메서드 이용하기
		//  ==> session자체는 삭제되지 않고 지정한 key값에 해당하는 개별적인 세션값만 삭제한다.
		// 형식) session객체.removeAttribute("삭제할 key값");
		session.removeAttribute("testSession");
		
		// 3. Session자체 삭제하기 : Session객체의 invalidate()메서드 이용
		// 형식) session객체.invalidate()
		session.invalidate();
		
		out.println("<html><head><meta charset='utf-8'><title>Session 삭제 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>저장된 Session 데이터 삭제하기</h2><hr>");		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
