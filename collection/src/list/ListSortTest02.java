package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest02 {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------");
		
		//내부정렬로 오름차순 정렬 
		Collections.sort(memList);
		
		System.out.println("정렬후...");
		
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("---------------------------------------------");
		
		//회원 번호를 기준으로 내림차순 정렬하여 출력하시오.
		// ==> 외부 정렬 기준 클래스를 작성하여 처리한다. (클래스 이름 : SortNumDesc)
		Collections.sort(memList, new SortNumDesc());

		for(Member mem : memList) {
			System.out.println(mem);
		}
	}
}

class SortNumDesc implements Comparator<Member>{
 
	@Override
	public int compare(Member mem1, Member mem2) {
		 /* 
		if(mem1.getNum() > mem2.getNum()) {
		return -1;
		}else if(mem1.getNum() < mem2.getNum()) {
			return 1;
		}else {
			return 0;
		}
		*/
		
		// Wrapper클래스를 이용하는 방식1
		// *-1하면 원래 오름차순 기준이였던게 내림차순 됨
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		// Wrapper클래스를 이용하는 방식2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}
	
	 
}

// Member클래스의 '회원이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
// ==> Comparable인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num;		//회원번호
	private String name;    //회원이름
	private String tel;     //전화번호
	
	//생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {
		// 회원이름의 오름차순
		//this가 앞의 데이타 mem이 뒤의 데이터
		return this.getName().compareTo(mem.getName());
	}
		
	
}
	
