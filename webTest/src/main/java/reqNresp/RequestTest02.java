package reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String cal = request.getParameter("cal");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		double result = 0; // 계산 결과가 저장될 변수 선언
		boolean calOK = true;
		
		switch(cal) {
			case "+" : result = num1 + num2; break;
			case "-" : result = num1 - num2; break;
			case "*" : result = num1 * num2; break;
			case "/" : 
				if(num2==0) {
					calOK = false;
				}else {
					result = (double)num1 / num2;
				}
				break;
			case "%" : 
				if(num2==0) {
					calOK = false;
				}else {
					result = (double)num1 % num2;
				}
				break;
		}
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset='utf-8'><title>Request객체 연습(계산기)</title></head>");
		out.println("body");
		
		out.println("<h3> 계산 결과 </h3><hr>");
		out.println(num1 + cal + num2 + " = ");
		
		if(calOK == true) {
			out.println(result);
		}else {
			out.println("계산 불능 (0으로 나누기)");
		}
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
