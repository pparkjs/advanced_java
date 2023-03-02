package stackQueue;

import java.util.Stack;

public class StackTest {

	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.history();
		b.goURL("1. 네이버");
		b.history();
		
		b.goURL("2. 구글");
		b.history();
		
		b.goURL("3. 다음");
		b.goURL("4. 네이트");
		b.history();
		
		System.out.println("뒤로가기 후...");
		b.goBack();
		b.history();
		
		System.out.println("뒤로가기 후...");
		b.goBack();
		b.history();
		
		System.out.println("앞으로가기 후...");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트로 접속한 후...");
		b.goURL("5. DDIT");
		b.history();
	}
	
	
}

//웹브라우저의 앞으로가기, 뒤로가기 기능 구현하기(스택 이용)
class Browser{
	private Stack<String> back;    //이전 방문 내역이 저장될 스택
	private Stack<String> forward; //다음 방문 내역이 저장될 스택
	private String currentURl; 	   //현재 페이지가 저장될 변수
	
	//생성자
	public Browser() {
		back = new Stack<>();
		forward = new Stack<>();
		currentURl = "";
	}
	
	// 사이트를 방문하는 메서드 ==> 매개변수에는 방문할 URL이 저장된다.
	public void goURL(String url) {
		System.out.println(url + "사이트로 이동합니다...");
		
		if(currentURl != null && !"".equals(currentURl)) { // 현재 페이지가 있으면...
			back.push(currentURl);	// 현재 페이지를 back스택에 추가한다.
		}
		currentURl = url; // 현재 페이지를 이동할 페이지로 변경한다.
		forward.clear();  // forward스택 비우기
		
	}
	// 뒤로 가기 메서드
	public void goBack() {
		// isEmpty() ==> 컬렉션이 비었는지 여부를 검사하는 메서드 
		if(!back.isEmpty()) { // back스택이 비어있지 않으면
			forward.push(currentURl);   // 현재 페이지를 forward스택에 추가한다.
			currentURl = back.pop();    // back스택에서 꺼내온 데이터를 현재 페이지로 한다.
		}
	}
	
	// 앞으로 가기 메서드
	public void goForward() {
		if(!forward.isEmpty()){ // forward스택이 비어있지 않으면... 
			back.push(currentURl);  // 현재 페이지를 back스택에 추가한다.
			currentURl = forward.pop();  //forward스택에서 꺼내온 데이터를 현재 페이지로 한다.
		}
	}
	
	//방문 기록 확인하는 메서드
	public void history() {
		System.out.println();
		System.out.println("   방 문 기 록");
		System.out.println("---------------");
		System.out.println("back     => " + back);
		System.out.println("현재     => " + currentURl);
		System.out.println("forward  => " + forward);
		System.out.println();
	}
}
