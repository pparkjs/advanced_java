package fileuploadController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fileuploadService.FileinfoServiceImpl;
import fileuploadService.IFileinfoService;
import vo.FileinfoVO;

/*
	 - Servlet 3.0이상에서 파일 업로드를 하려면 작성하는 서블릿에@MultipartConfig 애노테이션을 설정해야 한다.
	 
	 - @MultipartConfig의 설정 값들..
	   1) location : 업로드한 파일이 임시적으로 저장될 경로를 지정한다. (기본값 : "")
	   2) fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 loication에 지정한 임시 디렉토리에 저장한다.
	   					(단위 : byte, 기본값 : 0(무조건 임시 디렉토리 사용))
	   3) maxFileSize : 1개 파일의 최대 크기 (단위 : byte, 기본값 : -1L (무제한))
	   4) maxRequestSize : 서버로 전송되는 Request데이터의 전체 최대 크기 (단위 : byte, 기본값 : -1(무제한))
 */
@WebServlet("/fileUpload.do")
@MultipartConfig(
	//1024Kbyte = 1Mbyte ,1Kbyte = 1024byte
	fileSizeThreshold = 1024 * 1024 * 10 , maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 100 ) 
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//파일 입력 폼을 보여주는 View페이지로 이동
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* System.out.println("파일 업로드 post방식"); */
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		// 업로드된 파일이 저장될 폴더 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if(!f.exists()) { // 파일이 없으면
			f.mkdirs(); // 폴더 생성
		}
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		String userName = request.getParameter("username");
		
		System.out.println("일반 파라미터 데이터 : " + userName);
		
		//---------------------------------------------------------
		
		// 수신 받은 파일 데이터 처리하기
		String fileName = ""; // 파일명이 저장될 변수 선언
		
		// 수신 받을 파일이 여러개일 때 Upload한 파일 목록이 저장될 List객체 생성
		List<FileinfoVO> fileList = new ArrayList<>();
		
		/*
		  	- Servlet 3.0 이상에서 새롭게 추가된 upload용 메서드
		  	1) request.getParts() ==> 전체 Part객체를 Collection 객체에 담아서 반환한다.
		  	2) request.getPart("part이름") ==> 지정된 'part이름'을 가진 개별  Part객체를 반환한다.
		  			'part이름' ==> <form>태그 안의 입렵 요소의 name 속성값으로 구별한다. 
		 */
		// 전체 Part 객체 개수만큼 반복 처리하기.
		for(Part part : request.getParts()) {
			fileName = extractFileName(part);
			
			// 찾은 파일명이 빈문자열("")이면 이것은 파일이 아닌 일반 파라미터 데이터라는 의미이다.
			if(!"".equals(fileName)) { //파일인지 검사
				
				// 1개의 Upload파일에 대한 정보를 저장하기 위한 VO객체 생성
				FileinfoVO fvo = new FileinfoVO(); 
				
				fvo.setFile_writer(userName);  		  // 작성자 데이터 저장
				fvo.setOrigin_file_name(fileName);    // 실제 파일명을 VO에 저장
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지 하기 위해서 UUID객체를 이용하여
				// 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString()+ "_" + fileName;
				System.out.println(saveFileName);
				// 이 값을 VO에 저장 파일명으로 저장한다.,
				fvo.setSave_file_name(saveFileName);
				
				// parrt.getSize() == > Upload된 파일의 크기를 반환한다. (단위 : byte)
				
				// 파일 크기를 구해서 KB단위로 변환해서 VO에 저장한다.
				fvo.setFile_size((long)Math.ceil(part.getSize() / 1024.0));
				
				// 수신 받은 파일 내용을 저장한다.
				try {
					// part.write()메서드 ==> Upload된 파일을 저장하는 메서드
					// File.separator -> 운영체제에 따라서 /또는 \\를 표시해주는 것
					// 이 과정을 통해서 다른 사람이 나의 파일을 다운로드 받을때 내가 지정한 파일이 존재하는
					// 폴더 경로에서 가져 가는 것 이 파일 저장된 경로가 서버 폴더라고 생각하면됨
					part.write( uploadPath + File.separator + saveFileName); // 파일 저장하기..
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				fileList.add(fvo); // upload 된 파일 정보를 list에 추가
			}
		} // for문의 끝
		
		//List에 저장된 파일 정보를 DB에 insert한다.
		
		IFileinfoService service = FileinfoServiceImpl.getInstance();
		
		for(FileinfoVO fvo : fileList) {
			service.insertFileinfo(fvo);
		}
		
		// 저장이 완료된 후에는 파일 목록을 보여주는 페이지로 이동한다.
		 response.sendRedirect(request.getContextPath() + "/fileList.do");
		
		
	} // doPost()메서드의 끝
	
	//--------------------------------------------------
	/*
	 	- part 구조
	 	1) 파일이 아닌 일반 데이터일 경우
	 	---------------------1234532awsdqwwqd12344  ==> part를 구분하는 구분선
	 	content-disposition : form-data; name="username" ==> 파라미터명
	 					==> 빈줄
	 	gildong         ==> 파라미터 값
	 	
	 	
	 	2) 파일일 경우
	 	---------------------1234532awsdqwwqd12344  ==> part를 구분하는 구분선
	 	content-disposition : form-data; name="upFile1"; filename="test1.txt"  ==> 파일 정보
	 	content-type : text/plain   만약 이미지면 image/jpg 
	 					==> 빈줄
	 	abcd1234안녕	== > 파일의 내용
	 */
	
	//--------------------------------------------------

	
	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";   // 파일명이 저장될 변수
		String headerValue = part.getHeader("content-disposition"); // 헤더의 '키값'을 이용하여 값을 구한다.
		
		String[] items = headerValue.split(";");
		for (String item : items) {
			// filename으로 시작하는 것만
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
			
		}
		//빈문자가 넘어가면 파일이 아니란것
		return fileName;
	}
}
