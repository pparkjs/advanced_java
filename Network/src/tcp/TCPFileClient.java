package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 	클라이언트의 작업 순서
	1. 전송할 파일 정보를 갖는 File객체를 생성한다.
	2. 전송할 파일이 있는지 검사해서 없으면 프로그램을 종료한다.
	3. 전송할 파일이 있으면 Socket객체를 생성해서 서버와 연결한다.
	4. 연결이 완료되면 첫번째로 '파일명'을 서버로 전송한다.
	5. 파일의 내용을 읽기 위한 입력용 스트림 객체를 생성한다.
	6. 파일을 읽어서 소켓으로 출력하는 작업을 파일의 모든 내용이 
   	전부 전송될 때까지 반복한다.
	7. 전송이 완료되면 작업 끝...
 */

// 서버에 접속하면 'd:/d_other/펭귄.jpg' 파일을 서버로 전송한다.
public class TCPFileClient {
	public static void main(String[] args) throws UnknownHostException, IOException {


		/*	
		// 전송할 파일 정보를 갖는 File객체를 생성한다.
		File file = new File("d:/d_other/펭귄.jpg");

		// 전송할 파일이 있는지 검사해서 없으면 프로그램을 종료한다.
		if(!file.exists()) {
			System.out.println(file.getName() + "이(가) 없습니다.");
			System.out.println("전송 작업을 중단합니다.");
			return;
		}

		//전송할 파일이 있으면 Socket객체를 생성해서 서버와 연결한다.
		System.out.println("서버에 연결 중입니다.");
		Socket socket = new Socket("localhost", 7777); 

		System.out.println("서버에 연결되었습니니다.");


		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		//연결이 완료되면 첫번째로 '파일명'을 서버로 전송한다.
		dout.writeUTF(file.getName());

		//파일의 내용을 읽기 위한 입력용 스트림 객체를 생성한다.
		FileInputStream fin = null;

		try {
			//펭귄이 들어있는 file객체를 fin에 담는다
			fin = new FileInputStream(file);

		// 파일을 읽어서 소켓으로 출력하는 작업을 파일의 모든 내용이 
		// 전부 전송될 때까지 반복한다.
			int data;
			while((data = fin.read()) != -1){
				dout.write(data);
			}

			System.out.println("전송 완료...");

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fin!=null) try { fin.close(); }catch(IOException e) {}
			if(dout!=null) try { dout.close(); }catch(IOException e) {}
		}
		 */
		
		new TCPFileClient().clientStart();
	}

	
	public void clientStart() {
		Socket socket = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		DataOutputStream dout = null;
		
		// 전송할 파일 정보를 갖는 File객체 생성
		File file = new File("d:/d_other/펭귄.jpg");
		String fileName = file.getName();
		
		if(!file.exists()) {
			System.out.println("전송할" + fileName + " 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost",7777);
			
			System.out.println("파일 전송 시작...");
			dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(fileName);
			
			// 파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			// 서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 파일 내용을 읽어서 서버로 전송한다.
			while((len = bin.read(temp))>0);{
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 전송 완료....");
			
			
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패...");
			e.printStackTrace();
		} finally {
			if(dout!=null) try{dout.close();}catch(IOException e) {}
			if(bout!=null) try{bout.close();}catch(IOException e) {}
			if(bin!=null) try{bin.close();}catch(IOException e) {}
			if(socket!=null) try{socket.close();}catch(IOException e) {}
		}
		
		
	}
}



















