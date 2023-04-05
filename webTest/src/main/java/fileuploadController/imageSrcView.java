package fileuploadController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileuploadService.FileinfoServiceImpl;
import fileuploadService.IFileinfoService;
import vo.FileinfoVO;

@WebServlet("/images/imageSrcView.do")
public class imageSrcView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일번호 구하기 
		String strFileno = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileno);
		
		// 파일 번호를 이용하여 DB에서 해당 파일 정보 가져오기 
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		FileinfoVO fvo = service.getFileinfo(fileNo);
		
		// 파일이 저장된 폴더 경로 지정하기 
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만든다	
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
    	}
		
		String imgPath = uploadPath + File.separator + fvo.getSave_file_name();
		File imgFile = new File(imgPath);
		
		if(imgFile.exists()) {  // 이미지 파일이 있을 때 
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				// 출력용 스트림 객체 생성 
				bout = new BufferedOutputStream(response.getOutputStream());
				bin = new BufferedInputStream(new FileInputStream(imgFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				
				while((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
			} catch (Exception e) {
				System.out.println("입출력 오류 " + e.getMessage());
			} finally {
				
				if(bin != null)try {bin.close();}catch(IOException e) {};
				if(bout != null)try {bout.close();}catch(IOException e) {};
			}
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
