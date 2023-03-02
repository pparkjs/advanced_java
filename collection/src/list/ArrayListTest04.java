package list;

import java.util.ArrayList;
import java.util.Scanner;
/*
 *   문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 멸병의 길이가 제일 긴 별명을 출력하시오.
 *       (단, 각 별명의 길이가 같을 수 있다.)
 */
public class ArrayListTest04 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			System.out.print("별명을 입력하세요. >> ");
			list.add(scan.nextLine());
		}
		int max = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length() > max) {
				max = list.get(i).length();
			}
		}
		for (String str : list) {
			if(str.length() == max) {
				System.out.println("길이가 제일 긴 별명 : " + str);
			}
		}
		scan.close();

	}
}
