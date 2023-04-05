package memberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberService.IMemberService;
import memberService.MemberServiceImpl;
import memberVO.MemberVO;

@WebServlet("/memberInfo.do")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("memId");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		MemberVO vo = service.memberInfo(id);
		
		request.setAttribute("voVal", vo);
		
		request.getRequestDispatcher("/view/memberInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
