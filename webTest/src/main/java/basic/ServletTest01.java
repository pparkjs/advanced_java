package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	서블릿 ==> 컨테이너(서블릿 엔진)에 의해 관리되는 자바 기반 웹 컴포넌트로서
 	           동적인 웹 컨텐츠 생성을 가능하게 해 준다.
 */

// 이 예제 배포 서술자(web.xml)을 이용해서 실행할 Servlet을 설정하여 처리하는 예제이다
public class ServletTest01 extends HttpServlet {
	
	// 이 곳에서는 대부분 service()메서드 또는 doGet()메서드나 doPost()메서드를 재정의해서 작성한다.
	
	/*
	   전체 주소 : http://localhost:8080/webTest/servletTest01.do
	    - http : 프로토콜
	    - localhost : 컴퓨터이름(도메인명) 또는 IP주소
	    - 8080 : PORT번호 (포트번호가 80번일 경우에는 생략 가능)
	    - /webTest : 컨텍스트 패스 (보통 프로젝트 이름으로 지정한다.)
	    - servletTest01.do ==> 서블릿 요청 URL패턴
	 
	*/
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
	// HttpServletRequest객체 ==>  서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 설정하기.
		response.setContentType("text/html; charset=utf-8"); // 응답 문서의 Content-Type 설정
		
		
		// 처리한 내용을 응답으로 보내기 위해 PrintWriter객체를 생성한다. (response객체 이용)
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 출력한다.
		// 방법1 : append()메서드 이용하기
		out.append("<html>")
		   .append("<head>")
		   .append("<meta charset='utf-8'><title>첫번째 Servlet연습</title></head>")
		   .append("<body>")
		   .append("<h1 style='text-align:center;'>안녕하세요. 첫번째 서블릿 프로그램입니다.</h1>")
		   .append("</body></html>");
		
		}
	
	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
