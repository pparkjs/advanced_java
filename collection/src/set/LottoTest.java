package set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {
	
	//로또번호 생성하는 메서드
	public static List<Integer> lotto() {
		Random random = new Random();
		Set<Integer> lottoNum = new HashSet<>();
		while(lottoNum.size() < 6) {
			//로또번호 자동 생성
			lottoNum.add(random.nextInt(45)+1);
		}
		List<Integer> lottoStg = new ArrayList<>(lottoNum);
		return lottoStg;
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int lottoPay = 1000;
		while (true) {
			System.out.println("=============================");
			System.out.println("Lotto 프로그램");
			System.out.println("----------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("=============================");
			System.out.print("메뉴선택 : ");
			int select = scan.nextInt();
			switch (select) {
			case 1://로또 구입
				System.out.println();
				System.out.println(" Lotto 구입 시작");
				System.out.println();
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int money = scan.nextInt();

				if(money > 100000) {
					System.out.println();
					System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
					System.out.println();
				}else if(money >= lottoPay) {
					int lest = money%lottoPay; // 거스름돈
					int cnt = money/lottoPay; // 로또 산 개수
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					for (int i = 0; i < cnt; i++) {
						System.out.print("로또번호" + (i+1) +" : ");
						System.out.println(lotto());
					}
					System.out.println();
					System.out.println("받은 금액은" + money + "원이고 거스름돈은 " + lest + "원입니다.");
					System.out.println();
				}else {
					System.out.println();
					System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
					System.out.println();
				}

				break;
			case 2://프로그램 종료
				System.out.println("감사합니다.");
				return;
			}
		}
	}

}

class Lotto {

}
