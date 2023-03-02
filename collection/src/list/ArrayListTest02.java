package list;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
 *     이 ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 찾아 모두 출력하시오.
 *     (단, 입력은 Scanner객체를 이용한다.)
 */
public class ArrayListTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			list.add(scan.nextLine());
		}
		System.out.println(list);
		for (String str : list) {
			if(str.indexOf("김") == 0) {
				System.out.println(str);
			}
		}
		
		for (String string : list) {
			if(string.startsWith("김")) {
				System.out.println(string);
			}
		}
		
		for (String string : list) {
			if(string.substring(0,1).equals("김")) {
				System.out.println(string);
			}
		}
		scan.close();
		
	}

}
