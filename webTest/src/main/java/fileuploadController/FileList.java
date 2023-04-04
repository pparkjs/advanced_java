package fileuploadController;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileuploadService.FileinfoServiceImpl;
import fileuploadService.IFileinfoService;
import vo.FileinfoVO;

@WebServlet("/fileList.do")
public class FileList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		// DB에 등록된 전체 파일 목록을 가져온다.
		List<FileinfoVO> fileList = service.getAllFileinfo();
		
		// 구해온 전체 파일 목록을 View 페이지로 보낸다.
		request.setAttribute("fileList", fileList);
		request.getRequestDispatcher("/basic/fileupload/fileList.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
