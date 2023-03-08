package IO;

import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileCopyDialog {
	public static void main(String[] args) {
		new FileCopyDialog().copyStart();
	}

	public void copyStart() {
		File file = showDialog("OPEN");
		
		if(file==null) {
			System.out.println("복사할 원본 파일의 선택에 실패했습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
			
		}
		if(!file.exists()) { // 원본파일이 없으면...
			System.out.println("복사할 원본 파일 " +file.getName() + " 이(가) 없습니다.");
			System.out.println("복사할 작업을 중지합니다..");
			return;
		}
		
		File targetFile = showDialog("SAVE");
		if(targetFile==null) {
			System.out.println("복사할 대상 파일의 선택에 실패했습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
			
		}

		FileInputStream fin = null;
		FileOutputStream fout = null;

		try {
			//원본 파일을 처리할 스트림 객체 생성
			fin = new FileInputStream(file);

			//대상 파일을 처리할 스트림 객체 생성
			fout = new FileOutputStream(targetFile);

			System.out.println("복사 시작...");
			
			int data; //읽어온 데이터를 저장할 변수
				
			while((data = fin.read()) != -1) {
				// 읽어온 데이터를 복사본_펭귄.jpg에 담아준다.
				fout.write(data);
			}
			fout.flush();
			
			System.out.println("복사 완료....");
		} catch (IOException e) {

		}finally {
			//스트림 닫기
			if(fin!=null) try { fin.close();}catch(IOException e){}
			if(fout!=null) try { fout.close();}catch(IOException e){}
		}
	}
	
	//openType은 열기용일 때는 "OPEN", 저장용일 때는 "SAVE"로 지정한다.
	private File showDialog(String openType) {
		JFileChooser chooser = new JFileChooser();
		
		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter doc = new FileNameExtensionFilter("MS Word", "doc", "docx");
		FileNameExtensionFilter img = 
				new FileNameExtensionFilter("Image Files", new String[] {"png", "jpg", "gif"});
		FileNameExtensionFilter pdf = new FileNameExtensionFilter("PDF 파일", "pdf");
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text 파일", "txt");
		
		// 구성한 확장자 추가
		chooser.addChoosableFileFilter(doc);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(pdf);
		chooser.addChoosableFileFilter(txt);
		
		// 구성한 확장자 목록 중에서 현재 선택된 상태가 될 확장자 지정
		// ==> 이것을 설정하지 않으면 첫번째로 추가한 확장자가 기본적으로 선택된다.
		chooser.setFileFilter(txt);
		
		//확장자 목록에 '모든 파일' 목록을 표시할지 여부 설정
//		chooser.setAcceptAllFileFilterUsed(false);
//		chooser.setAcceptAllFileFilterUsed(true); //기본값
		
		//Dialog창이 나타날 때 기본적으로 보여줄 기본 경로 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		//Dialog창 나타내기 ( 열기용과 저장용을 구분해서 나타낸다.)
		int result;
		if("OPEN".equals(openType.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); //열기용
			
		} else if("SAVE".equals(openType.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용
		}else {
			System.out.println("openType은 'OPEN'또는 'SAVE'로 지정해야 합니다.");
			return null;
		}
		
		File selectFile = null;
		if(result == JFileChooser.APPROVE_OPTION) { // '저장' 또는 '열기'버튼을 눌렀을 경우..
			// 현재 선택한 파일 정보 구하기
			selectFile = chooser.getSelectedFile();
			
		}
		
		return selectFile;
	}
	
	
}





