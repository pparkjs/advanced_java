package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIO {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		ByteArrayInputStream input = null; // 입력용 스트림 객체 변수 선언
		ByteArrayOutputStream output = null; // 출력용 스트림 객체 변수 선언
		
		try {
			input = new ByteArrayInputStream(inSrc);  // 입력용 스트림 객체 생성
			output = new ByteArrayOutputStream();
			
			int data;   // 읽어온 데이터가 저장될 변수 선언
			
			// read()메서드 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
			while(( data = input.read()) != -1 ) {
				//읽어온 데이터를 사용한다.
				output.write(data); //데이터 출력하기
			}
			
			//출력된 스트림 값들을 배열로 변환해서 저장하기
			outSrc = output.toByteArray();
			
			// 입출력 작업이 모두 끝나면 사용했던 스트림을 닫아준다.
			
			input.close();
			output.close();
			
			System.out.println(" inSrc =>" + Arrays.toString(inSrc));
			System.out.println(" outSrc =>" + Arrays.toString(outSrc));
			
		}catch(IOException e) {
			
		}
		
	}
}
