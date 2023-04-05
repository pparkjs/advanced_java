package memberController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import memberService.IMemberService;
import memberService.MemberServiceImpl;
import memberVO.MemberVO;

@WebServlet("/insertMember.do")
@MultipartConfig(
	//1024Kbyte = 1Mbyte ,1Kbyte = 1024byte
	fileSizeThreshold = 1024 * 1024 * 10 , maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 100 ) 
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uploadPath = "d:/d_other/imageFiles";
		
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if(!f.exists()) { // 파일이 없으면
			f.mkdirs(); // 폴더 생성
		}
		
		// 수신 받은 파일 데이터 처리하기
		String fileName = "";
		
		// 추가한 회원의 정보를 넣기 위한 객체 생성
		MemberVO vo = new MemberVO();
		
		for(Part part : request.getParts()) {
			fileName = extractFileName(part); // 파일명 추출해옴
			System.out.println(fileName);
			if(!"".equals(fileName)) { //파일인지 검사
				
				// 입력한 회원 정보 vo에 저장
				vo.setMem_id(request.getParameter("mem_id"));
				vo.setMem_pass(request.getParameter("mem_pass"));
				vo.setMem_name(request.getParameter("mem_name"));
				vo.setMem_tel(request.getParameter("mem_tel"));
				vo.setMem_addr(request.getParameter("mem_addr"));
				vo.setMem_photo(fileName);
				
				System.out.println(vo.getMem_id());
				System.out.println(vo.getMem_pass());
				System.out.println(vo.getMem_name());
				System.out.println(vo.getMem_tel());
				System.out.println(vo.getMem_addr());
				System.out.println(vo.getMem_photo());
				try {
					// 파일 저장하기..
					part.write(uploadPath + File.separator + fileName);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		IMemberService service = MemberServiceImpl.getInstance();
		
		service.memberInsertInfo(vo); // DB에 회원 정보 저장
		
		// 저장이 완료된 후에는 회원 목록을 보여주는 페이지로 이동한다.
		response.sendRedirect(request.getContextPath() + "/memberList.do");
		
	}
	
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
