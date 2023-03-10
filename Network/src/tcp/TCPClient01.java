package tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// TCP소켓 방식의 통신 프로그램으로 클라이언트 역할을 한다.
// 서버에 접속을 시도하고 접속이 완료되어
// 서버에서 보낸 환영 메시지를 받아서 화면에 출력한다.

public class TCPClient01 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		/*
		 	통신에서 현재의 컴퓨터를 나타내는 방법
		  1. 원래의 IP주소 : 예) 192.168.146.77
		  2. 지정된 IP주소 : 127.0.0.1
		  3. 원래의 컴퓨터 이름 : 예) DESKTOP-VGTGR32
		  4. 지정된 컴퓨터 이름 : localhost 	
		 */
		
		// TCP소켓 방식으로 서버에 연결하기 위해서 Scoket객체를 생성한다.
		System.out.println("서버에 연결 중입니다...");
//		Socket socekt = new Socket("상대방의 IP주소" , 상대방의 Port번호)
		Socket socket = new Socket("localhost" , 7777);
		
		// 이 부분부터는 서버와 연결이 완료된 상태이여야만 실행되는 코드다.
		System.out.println("서버에 연결되었습니다...");
		
		// 상대방(서버)에서 보내온 메시지를 받아서 출력하기
		// Socket객체의 InputStream객체를 구해서 데이터를 수신한다.
		// (Socket의 getInputstream()메서드를 이용한다.)
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		// 메시지를 받아서 화면에 출력하기
		System.out.println("서버에서 보내온 메시지 : " + din.readUTF());
		System.out.println();
		
		// 스트림과 소켓 닫기
		din.close();
		socket.close();
		
				
	}
}
