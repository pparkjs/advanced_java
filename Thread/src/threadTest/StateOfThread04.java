package threadTest;

import java.util.Random;

/*
 	3개의 쓰레드가 각각 알파벳 A~Z까지를 출력하는데
 	출력을 끝낸 순서대로 결과를 나타내는 프로그램 만들기
 */
public class StateOfThread04 {
	public static void main(String[] args) {
		DisplayCharacter[] charArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter dc : charArr) {
			dc.start();
		}
		
		for(DisplayCharacter dc : charArr) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.ranking);
	}
}

//A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String ranking = "";
	private String name;
	
	// 생성자
	public DisplayCharacter(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		Random rnd = new Random();
		for(char c='A'; c<='z'; c++) {
			System.out.println(name + "의 출력 문자 : " + c);
			try {
				Thread.sleep(rnd.nextInt(400)); //일시 정지 시간을 난수로 지정한다.
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + "의 출력 끝...................");
		// 출력을 끝낸 순서대로 이름을 배치한다.
		DisplayCharacter.ranking += name + "  ";
	}
	
	
}
