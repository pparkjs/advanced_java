package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import memberVO.MemberVO;

@WebServlet("/sessionLoginDB.do")
public class SessionLoginDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("userpass");
		
		// 입력받은 데이터를 VO에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(userId);
		memVo.setMem_pass(userPass);
		
		// DAO객체 생성
		MemberDAO dao = MemberDAO.getInstance();
		
		// DB에서 id와  password가 일치하는 데이터 검색해서 가져오기
		MemberVO loginMemberVo = dao.getMember(memVo);
		HttpSession session = request.getSession();
		
		if(loginMemberVo!=null) { //로그인 성공
			session.setAttribute("loginMember", loginMemberVo);
		}
		
		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLoginDB.jsp");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
