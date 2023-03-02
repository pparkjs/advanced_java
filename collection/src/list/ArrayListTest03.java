package list;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *   문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 멸병의 길이가 제일 긴 별명을 출력하시오.
 *       (단, 각 별명의 길이는 모두 다르게 입력한다.)
 */
public class ArrayListTest03 {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		for (int i = 0; i < 5; i++) {
			System.out.print("별명을 입력하세요. >> ");
			list.add(scan.nextLine());
		}
		int max = Integer.MIN_VALUE;
		String maxName = "";
		for (String str : list) {
			if(str.length() > max) {
				max = str.length();
				maxName = str;
			}
		}
		System.out.println("길이가 제일 긴 별명 : " + maxName);
		scan.close();
	}
}
