package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StateOfThread05 {
	/*
  	10마리의 말들이 경주하는 프로그램 작성하기

  	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
  	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
  	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준을 갖고 있다.
  	(Comparable인터페이스 구현)

  	경기 구간은 1 ~ 50구간으로 되어 있다.
  	경기가 끝나면 등수 순으로 출력한다.

  	경기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
  	예시)
  	말이름01 : --->--------------------------------------------
  	말이름02 : ------->----------------------------------------
  	말이름03 : ---->-------------------------------------------
  	...
  	말이름10 : ------>-----------------------------------------


	 */
	public static void main(String[] args) {
		List<Horse> horse = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			String name = "말" + i;
//			horse.add(new Horse(name));
		}	
		
		for (Horse horse2 : horse) {
			horse2.start();
		}
		
		for (Horse horse2 : horse) {
			try {
				horse2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

class Horse extends Thread{
	public static int ranking;

	public static int nowLocat;
	Random rnd = new Random();



	@Override
	public void run() {
		for(int i=1; i <= 10; i++ ) {
			for(int j=1; j<=49; j++) {
				nowLocat = j; //현재 위치
				try {
					Thread.sleep(rnd.nextInt(400));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

}

class game extends Thread{
	private String name;
	
	public game(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(int i=1; i <= 10; i++ ) {
			System.out.print(name + " : ");
			for(int j=1; j<=49; j++) {
//				if(Horse.nowLocat) {
					System.out.println("-");
//				}
			}
		}
	}

}
