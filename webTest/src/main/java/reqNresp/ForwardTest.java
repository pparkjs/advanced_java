package reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forwardTest.do")
public class ForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		//파라미터 데이터 받기
		String userName = request.getParameter("username");
		
		//이전 문서에서 setAttribute()로 셋팅해서 보낸 데이터는 getAttribute()메서드로 받는다.
		// 형식) request.getAttribute("키값")
		String tel = (String)request.getAttribute("tel");
		
		out.println("<html><head><meta charset='utf-8'><title>Forward 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Forward 결과</h3><hr>");
		
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		out.println("<tr><td>전화</td>");
		out.println("<td>" + tel + "</td></tr>");
		out.println("</table>");
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
