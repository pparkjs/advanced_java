package threadTest;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 	
 	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
 	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 	
 	입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 	5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 	
 	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 	
 	결과 예시)
 	1) 5초 안에 입력하지 못했을 경우
 	  --- 결 과 ---
 	  시간 초과로 당신이 졌습니다.
 	  
 	2) 5초 안에 입력했을 경우
 	  --- 결 과 ---
 	  컴퓨터 : 가위
 	  사용자 : 바위
 	  결  과 : 당신이 이겼습니다.
 	
 */
public class ThreadTest07 {
	public static void main(String[] args) {
		Thread th1 = new GameStart();
		Thread th2 = new TimeAttack();
		
		th1.start();
		th2.start();
	}
}

// 가위바위보게임을 진행하는 메서드
class GameStart extends Thread{
	public static boolean inputCheck = false;
	Random ran = new Random();
	String[] com = {"가위", "바위", "보"};
	String result = "";
	@Override
	public void run() {
		String user = JOptionPane.showInputDialog("무엇을 낼지 입력하시오.");
		if(TimeAttack.endCheck == true) {
			return;
		}
		inputCheck = true; //입력이 완료되면 true로 변환
		String comResult = com[ran.nextInt(2)];
		System.out.println(" --- 결 과 ---");
		System.out.println("컴퓨터 : " + comResult);
		System.out.println("사용자 : " + user);
		//비겼을 경우
		
		try {
			if(comResult.equals(user)) {
				result = "비겼습니다.";
			}//내가 이겼을때
			else if(user.equals("가위") && comResult.equals("보") ||
					user.equals("바위") && comResult.equals("가위") ||
					user.equals("보") && comResult.equals("바위")) {
				result = "당신이 이겼습니다.";
			}//컴퓨터가 이겼을때
			else {
				result = "컴퓨터가 이겼습니다.";
			}
			System.out.println("결 과 : " + result);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("결 과 : 컴퓨터가 이겼습니다. ");
		}
	}
	
}
// 가위바위보입력 시간 재는 메서드
class TimeAttack extends Thread{
	public static boolean endCheck = false;
	
	@Override
	public void run() {
		for (int i = 5; i>=1; i--) {
			if(GameStart.inputCheck == true) {
				return;  // 사용자가 5초안에 입력 했으면 해당 메소드 종료
			}
			try {
				// 1초 간격으로 5 ~ 1까지 카운트 샘
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
		System.out.println(" --- 결 과 ---");
		System.out.println("시간 초과로 당신이 졌습니다.");
		endCheck = true;
	}
	
}
