package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*

 */


public class TCPMultiChatServer {
	//접속한 클라이언트 정보를 저장할 Map객체 선언
	//    -> Map의 key값은 '대화명', value값은 '클라이언트와 연결되 소켓객체'
	private Map<String, Socket> clientMap;
	
	//생성자
	public TCPMultiChatServer() {
		//clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
		
	}
	
	public static void main(String[] args) {
		new TCPMultiChatServer().serverStart();
	}
	
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다...");
			
			while(true) {
				socket = server.accept(); //클라이언트의 접속을 기다린다.
				System.out.println("[" + socket.getInetAddress() + " : "
							+ socket.getPort() + "]에서 접속했습니다.");
				System.out.println();
				//-----------------------------------
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(server!=null)try {server.close();}catch(IOException e) {}
		}
	}// 시작 메서드 끝...
	
	//clientMap에 저장된 전체 사용자에게 메시지르르 전송하는 메서드
	private void sendToAll(String msg) {
		//clientMap의 데이터 개수만큼 반복처리
		for(String name : clientMap.keySet()) {
			
			try {
				//key값에 대응하는 Socket객체를 이용하여 출력용 스트림 객체를 구한다.
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				
				dout.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}// sendAll메서드 끝
	
	//----------------------------------------------------------------------------
	
	//서버에서 클라이어트로 메시지를 전송하는 Thread를 Inner Class형식으로 작성한다.
	//    =>Inner Class형식으로 만드는 이유 : outer Class의 멤버들을 자유롭게 사용할 수 있다.
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream din ;
		private DataOutputStream dout;
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				// 사용할 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream()); // 수신용
				dout = new DataOutputStream(this.socket.getOutputStream()); // 송신용
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // 생성자 끝...
		
		@Override
		public void run() {
			String name = "";
			
			try {
				// 클라이언트가 연결이 성공하면 첫번째로 '대화명'을 입력받아 보낸다.
				// 서버에서는 이 '대화명'을 받아서 중복여부를 검사하여 그 결과를 응답으로 보낸다.
				
				while(true) { // '대화명'이 중복되지 않을 때까지 반복한다.
					name = din.readUTF();       // '대화명' 수신
					
					// 중복 검사
					if(clientMap.containsKey(name)) { // 중복되면...
						dout.writeUTF("대화명중복");
					}else {// 중복되지 않으면..
						dout.writeUTF("OK");
						break;
					}
				}// while문 
				
				// 이미 접속한 다른 모든 접속자들에게 지금 접속한 사람의 대화명을 이용하여
				// 대화방 참여 메시지를 전송한다.
				sendToAll("[" + name + "]님이 대화방에 입장했습니다.");
				
				// '대화명'과 현재 접속한 클라이언트의 Socket객체를 Map에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				
				// 현재 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
				while(din!=null) {
					sendToAll(din.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// 이 finally영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미이다.
				sendToAll("[" + name + "]님이 접속을 종료했습니다.");
				
				//사용자 목록(Map)에서 대화명을 삭제한다.
				clientMap.remove(name);
				
				System.out.println();
				System.out.println("[" + socket.getInetAddress() + " : "
						+ socket.getPort() + "]에서 접속을 종료 했습니다.");
				System.out.println();
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}
		}
		
		
	} // Thread 끝...
	
}








