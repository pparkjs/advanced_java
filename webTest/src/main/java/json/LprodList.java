package json;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("appplication/json; charset=utf-8");
		
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> lprodList = service.lprodAllList();
		
		
		Gson gson = new Gson();
		
		String jsonData = gson.toJson(lprodList);
		
		response.getWriter().write(jsonData);
		response.flushBuffer();
		
//		request.setAttribute("lprodListValue", lprodList);
//		
//		request.getRequestDispatcher("/view/lprodList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
