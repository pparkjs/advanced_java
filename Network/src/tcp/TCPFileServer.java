package tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*
 	서버의 작업 순서
	1. 파일이 저장될 폴더 정보를 갖는 File객체를 생성한다.
	2. 해당 폴더가 없으면 새로 생성한다.
	3. ServerSocket객체를 생성한 후 클라이언트의 접속을 기다린다.
	4. 접속이 완료되면 클라이언트가 첫번째로 보낸 '파일명'을 받는다.
	5. 1번의 File객체와 전송 받은 '파일명'을 조합하여 저장할 파일 정보를 갖는
   		File객체를 생성한다.
	6. 저장할 파일 정보를 이용한 파일 출력용 스트림 객체를 생성한다.
	7. 클라이언트가 보낸 파일 데이터를 소켓에서 읽어서 파일로 출력하는 작업을
   		수신 받은 내용이 전부 저장될 때가지 반복한다.
	8. 저장이 완료되면 작업 끝...

 */

// 클라이언트가 보내온 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.
public class TCPFileServer {
	public static void main(String[] args) throws IOException {

	/*
		//파일이 저장될 폴더 정보를 갖는 File객체를 생성한다.
		File targetDir = new File("d:/d_other/연습용");
		//해당 폴더가 없으면 새로 생성한다.
		if(!targetDir.exists()) {  // 저장할 폴더가 없으면...
			targetDir.mkdirs();
		}
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("서버가 준비 중 입니다.");
		
		//ServerSocket객체를 생성한 후 클라이언트의 접속을 기다린다.
		Socket socket = server.accept();
		
		System.out.println("클라이언트와 연결되었습니다.");
		
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		//접속이 완료되면 클라이언트가 첫번째로 보낸 '파일명'을 받는다.
		String fileName = din.readUTF();
		System.out.println("클라이언트에서 온 파일명 : " + fileName);
		
		//1번의 File객체와 전송 받은 '파일명'을 조합하여 저장할 파일 정보를 갖는
   		//File객체를 생성한다.
		File file = new File("d:/d_other/연습용/" + fileName);
		
		//저장할 파일 정보를 이용한 파일 출력용 스트림 객체를 생성한다.
		FileOutputStream fout = null;
		
		try {
			fout = new FileOutputStream(file);
			
			int data;
			//클라이언트가 보낸 파일 데이터를 소켓에서 읽어서 파일로 출력하는 작업을
	   		//수신 받은 내용이 전부 저장될 때가지 반복한다.
			while((data = din.read()) != -1){
				fout.write(data);
			}
			System.out.println("저장 완료..");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// 사용했던 스트림 닫기
			if(fout!=null) try { fout.close(); }catch(IOException e) {}
			if(din!=null) try { din.close(); }catch(IOException e) {}
		}
		*/
		new TCPFileServer().serverStart();
	}
	
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		DataInputStream din = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		//저장할 폴더 정보를 갖는 File객체 생성
		File saveDir = new File("d:/d_other/연습용");
		if(!saveDir.exists()) { //저장할 폴더가 없으면 새로 만든다.
			saveDir.mkdir();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다..");
			socket = server.accept(); //클라리언트의 요청 기다리기..
			
			System.out.println("파일 수신 시작...");
			
			// 클라이언트가 첫번째로 보낸 데이터(파일명)를 받을 스트림 객체 생성
			din = new DataInputStream(socket.getInputStream());
			
			String fileName = din.readUTF(); //파일명 받기
			
			// 저장할 폴더와 수신받은 파일명을 결합한 File객체 생성
			File saveFile = new File(saveDir, fileName);
			
			bin = new BufferedInputStream(socket.getInputStream()); // 수신용
			bout = new BufferedOutputStream(new FileOutputStream(saveFile)); // 파일 저장용
			
			byte[] temp = new byte[1024];
			int len = 0;
			while((len=bin.read(temp))>0) {
				bout.write(temp, 0 ,len);
			}
			bout.flush();
			System.out.println("파일 수신 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 수신 실패!!!");
			e.printStackTrace();
		} finally {
			if(din!=null) try {din.close();} catch(IOException e) {}
			if(bin!=null) try {bin.close();} catch(IOException e) {}
			if(bout!=null) try {bout.close();} catch(IOException e) {}
			if(socket!=null) try {socket.close();} catch(IOException e) {}
			if(server!=null) try {server.close();} catch(IOException e) {}
		}
	}
}












