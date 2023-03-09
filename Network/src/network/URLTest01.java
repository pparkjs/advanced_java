package network;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest01 {
	public static void main(String[] args) throws MalformedURLException {
		// URL클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 다루는 클래스
		
		// URL 주소 구조 => 프로토콜://호스트명:포트번호/경로명/파일명?쿼리리스트#참조
		//    https://ddit.or.kr:80/index.php?name=hong&age=20
		
		URL url = new URL("https://ddit.or.kr:80/index.php?name=hong&age=20");
//		URL url2 = new URL("https://ddit.or.kr:80", "/index.php?name=hong&age=20");
//		URL url2 = new URL("https","//ddit.or.kr",80, "/index.php?name=hong&age=20");
		
		System.out.println("Protocol : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("Port : " + url.getPort());
		System.out.println("File : " + url.getFile());
		System.out.println("Path : " + url.getPath());
		System.out.println("Query : " + url.getQuery());
		System.out.println();
	}
}
