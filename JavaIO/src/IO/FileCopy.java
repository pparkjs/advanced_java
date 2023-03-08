package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 	'd:/d_other' 폴더에 있는 '펭귄.jpg' 파일을 
 	'd:/d_other/연습용' 폴더에 '복사본_펭귄.jpg'파일로 복사하는 프로그램을 작성하시오.
 */
public class FileCopy {
	public static void main(String[] args) {

		//			FileInputStream fin = null;
		//			FileOutputStream fout = null;
		//			try {
		//				fin = new FileInputStream("d:/d_other/펭귄.jpg");
		//				fout = new FileOutputStream("d:/d_other/복사본_펭귄.jpg");
		//				
		//				int c; // 읽어온 데이터를 저장할 변수
		//				while((c=fin.read()) != -1) {
		//					// 읽어온 데이터를 복사본_펭귄.jpg에 담아준다.
		//					fout.write(c);
		//				}
		//				
		//			} catch (IOException e) {
		//				// TODO: handle exception
		//			}finally {
		//				//스트림 닫기
		//				if(fin!=null) try { fin.close();}catch(IOException e){}
		//				if(fout!=null) try { fout.close();}catch(IOException e){}
		//				

		//			}

		File file = new File("d:/d_other/펭귄.jpg");
		if(!file.exists()) { // 원본파일이 없으면...
			System.out.println("복사할 원본 파일 " +file.getName() + " 이(가) 없습니다.");
			System.out.println("복사할 작업을 중지합니다..");
			return;
		}

		FileInputStream fin = null;
		FileOutputStream fout = null;

		try {
			//원본 파일을 처리할 스트림 객체 생성
			fin = new FileInputStream(file);

			//대상 파일을 처리할 스트림 객체 생성
			fout = new FileOutputStream("d:/d_other/복사본_펭귄.jpg");

			System.out.println("복사 시작...");
			
			int data;
				
			int c; // 읽어온 데이터를 저장할 변수
			while((c=fin.read()) != -1) {
				// 읽어온 데이터를 복사본_펭귄.jpg에 담아준다.
				fout.write(c);
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
}
