package cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int count = 0;  // 현재 count값이 저장될 변수	
		
		// 쿠키이름을 count로 한다. ==> 현재 이 쿠키가 있는지 검사한다.
		Cookie[] cookieArr = request.getCookies();
		if(cookieArr!=null) {
			for(Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) { // 'count'라는 쿠키가 있는지 검사
					String value = cookie.getValue(); //현재의 쿠키값 가져오기
					count = Integer.parseInt(value);
				}
			}
		}
		
		count++; // count 증가
		
		//증가된 값을 쿠키값으로 갖는 Cookie객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		
		//생성된 쿠키정보 저장하기.
		response.addCookie(countCookie);
		
		out.println("<html><head><meta charset='utf-8'><title>쿠키연습</title></head>");
		out.println("<body>");

		out.println("<h2>안녕하세요. 당신은" + count + "번째 방문객입니다.</h2><br><br>");
		
		out.println("<a href='" + request.getContextPath() + 
				"/cookieCountServlet.do'>카운터 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + 
				"/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");

		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


//
//		int cnt = 0;
//
//		boolean check = false;
//
//		Cookie[] cookieArr = request.getCookies(); 
//		Cookie cntCookie = null; 
//		
//		for(Cookie cookie : cookieArr) {
//			if(cookie.getName().equals("count")) {
//				// 쿠키값 중에 한글로 저장된 데이터는 데이터를 가져와서 디코딩 후 사용해야 한다.
//				cntCookie = cookie;
//				check = true;
//			}
//		}
//		
//		if(check==false) {
//			cntCookie = new Cookie("count", "1");
//			cnt = 1;
//			response.addCookie(cntCookie);
//		}else {
//			int res = Integer.parseInt(cntCookie.getValue());
//			res++;
//			cnt = res;
//			cntCookie.setValue("" + cnt);
//			response.addCookie(cntCookie);
//		}