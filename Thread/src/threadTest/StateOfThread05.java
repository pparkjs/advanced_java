package threadTest;

import java.util.ArrayList;
import java.util.Arrays;
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
		Horse[] horseArr = new Horse[] {
				new Horse("01번말"),new Horse("02번말"),new Horse("03번말"),
				new Horse("04번말"),new Horse("05번말"),new Horse("06번말"),
				new Horse("07번말"),new Horse("08번말"),new Horse("09번말"),new Horse("10번말")
		};
		GameState gs = new GameState(horseArr);

		for(Horse h : horseArr) {
			h.start();
		}
		gs.start();

		for(Horse h : horseArr) {
			try {
				h.join(); //말의 경기가 다 끝날 떄 까지 기다린다.
			} catch (InterruptedException e) {
				// TODO: handle exception
			}

		}

		try {
			gs.join(); // 끝날 때 까지 기다린다.
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝");
		System.out.println();

		//등수의 오름차순으로 정렬하기
		Arrays.sort(horseArr);
		
		for (Horse h : horseArr) {
			System.out.println(h);
		}

	}

}


class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank=0; //말의 등수를 구할 떄 사용할 변수
	private String horseName; //말이름
	private int rank; //등수
	private int location; //현재 위치

	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return String.format("경주마 %s는(은) %s등 입니다.", horseName, rank);
	}

	// 등수의 오름차순 정렬의 내부 기준
	@Override
	public int compareTo(Horse horse) {

		return Integer.compare(this.rank, horse.getRank());
	}

	@Override
	public void run() {
		Random rnd = new Random();
		for (int i = 1; i <= 50; i++) {
			this.location = i;  //말의 현재위치 저장
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
			}
		}
		// 한 마리의 말의 경주가 끝나면 현재의 등수를 구해서 저장한다.
		currentRank++;
		this.rank = currentRank;
	}
}

/*	경기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
	예시)
	말이름01 : --->--------------------------------------------
	말이름02 : ------->----------------------------------------
	말이름03 : ---->-------------------------------------------
	...
	말이름10 : ------>----------------------------------------- */
class GameState extends Thread{
	private Horse[] horseArr; // 경주에 참가한 말들의 정보가 저장될 배열 변수 선언

	// 생성자
	public GameState(Horse[] horseArr) {
		this.horseArr = horseArr;
	}

	@Override
	public void run() {
		while(true) {
			//모든 말의 경주가 끝났는지 여부 검사.
			if(Horse.currentRank == horseArr.length) {
				break;
			}
			
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
			for(int i=0; i<horseArr.length; i++) {
				System.out.println(horseArr[i].getHorseName() + " : ");

				for(int j=1; j<=50; j++) {
					if(horseArr[i].getLocation()==j) { //말의 현재위치 찾기
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}



}




