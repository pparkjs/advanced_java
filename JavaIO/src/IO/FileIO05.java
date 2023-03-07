package IO;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIO05 {
	public static void main(String[] args) {
		//파일의 인코딩 방식을 지정해서 읽어오기
		try {
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt"); //얘로하면 한글 깨져서 나옴
//			FileReader fr = new FileReader("d:/d_other/test_utf8.txt"); // 얘는 한글 잘나옴
			
			// 인코딩 방식을 지정해서 입출력하는 보조 스트림
			// InputStreamReader ==> 입력용
			// OuputStreamWriter ==> 출력용
			
			FileInputStream fin = new FileInputStream("d:/d_other/test_ansi.txt"); // 얘도 앞에 꺼랑 같이 ansi는 깨짐
//			FileInputStream fin = new FileInputStream("d:/d_other/test_utf8.txt"); // 얘는 안깨짐
			
			// 기본 인코딩 방식으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fin); 
			
			// 인코딩 방식을 지정해서 읽어오기
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			
//			InputStreamReader isr = new InputStreamReader(fin, "ms949"); // 이제 이걸 해주면 ansi는 한글 안깨져서 나오지만 utf-8이 깨져서 나옴
			InputStreamReader isr = new InputStreamReader(fin, "utf-8"); // ansi는 깨지고 utf-8은 나옴
			
			int c;
			
			while( ( c=isr.read()) != -1) {
				System.out.print((char)c);
				
			}
			
			isr.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
