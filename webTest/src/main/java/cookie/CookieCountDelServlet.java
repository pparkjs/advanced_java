package cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		Cookie[] cookieArr = request.getCookies();

		// 쿠키이름이 'count'라는 쿠키 찾기
		if(cookieArr!=null) {
			for (Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) {
					// 'count'라는 쿠키를 찾아서 유지시간을 0으로 한 후에 다시 저장한다.
					cookie.setMaxAge(0);
					
					response.addCookie(cookie); //
				}
				
			}
		}
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>count가 초기화 되었습니다.</h2><br><br>");
		
		out.println("<a href='" + request.getContextPath() + 
				"/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
