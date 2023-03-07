package IO;

import java.io.FileReader;
import java.io.IOException;

public class FileIO03 {
	public static void main(String[] args) {
		//문자 기반 스트림을 이용하여 파일 내용 읽어와 출력하기
		FileReader fr = null; // 문자 기반 파일 입력용 스트림 객체 변수 선언
		try {
			// 스트림 객체 생성
			fr = new FileReader("d:/d_other/test.txt");
					
			int c;
			
			while ((c=fr.read()) != -1)  {
				System.out.print( (char)c );
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			//스트림 닫기
			if(fr!=null) try { fr.close();}catch(IOException e){}
		}
	}
}
