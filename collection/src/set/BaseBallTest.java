package set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 	     (컴퓨터의 숫자는 난수를 이용하여 구한다. (1~9사이의 값 3개)
 	     (스트라이크는 S, 볼은  B로 나타낸다.)
	예시)
		 컴퓨터난수 ==> 9 5 7

	실행예시)
	   숫자입력 >> 3 5 6
       3 5 6 ==> 1S 0B
       숫자입력 >> 7 8 9
       7 8 9 ==> 0S 2B
       숫자입력 >> 9 7 5
       9 7 5 ==> 1S 2B
       숫자입력 >> 9 5 7
       9 5 7 ==> 3S 0B

       축하합니다...
       당신은 4번째 만에 맞췄습니다...
 */

public class BaseBallTest {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scan = new Scanner(System.in);
		HashSet<Integer> set = new HashSet<>();
		while(set.size() < 3) {
			set.add(random.nextInt(9)+1);
		}
		//set을 이용해서 중복없이 저장한 3개의 난수를 List에 저장
		List<Integer> ranNum = new ArrayList<>(set);
		Collections.shuffle(ranNum);
		int cnt = 0;
		System.out.println("< 숫자야구 시작 >");
		System.out.println();
		
		while(true) {
			int sCnt = 0;
			int bCnt = 0; 
			System.out.print("숫자 입력 >> ");
			String num = scan.nextLine();
			String[] userNum = num.split(" ");
			cnt++;
			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(ranNum.get(i) == Integer.parseInt(userNum[j]) 
						&& i == j) {
						sCnt++;
						break;
					}else if(ranNum.get(i) == Integer.parseInt(userNum[j]) 
							&& i != j) {
						bCnt++;
						break;
					}
				}
			}
			
			for (int i = 0; i < 3; i++) {
				System.out.print(userNum[i]+ " ");
			}			
			
			System.out.print(" ==> " + sCnt+ "S " + bCnt + "B\n" );
			if(sCnt == 3) {
				System.out.println();
				System.out.println("축하합니다..");
				System.out.println("당신은" + cnt + "번째 만에 맞췄습니다.");
				return;
			}
			

		}


	}
}
