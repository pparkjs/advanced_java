package list;

import java.io.Console;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
    문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
         Student클래스를 만든다.
         이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 인수로 받아서
         초기화 처리를 한다.

         이 Student객체는 List에 저장하여 관리한다.

         List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
         총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부정렬 기준도
         구현하여 정렬된 결과를 출력하시오.

         (등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.)
 */
public class StudentTest {

	//등수를 구하는 메서드
	public static void setRanking(List<Student> stuList) {
		for (Student std1 : stuList) { //등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1; //처음에는 1등으로 초기화 해 놓고 시작한다.
			
			for(Student std2 : stuList) { // 비교대상을 나타내는 반복문
				//기준값보다 큰 값을 만나면 rank변수값을 증가 시킨다.
				if(std1.getSumScore() < std2.getSumScore()) {
					rank++;
				}
			}
			
			//구해진 등수를 Student객체의 rank변수에 저장한다.
			std1.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();

		stuList.add(new Student(20177152, "박정수", 90, 90, 100));
		stuList.add(new Student(20177154, "홍길동", 95, 91, 100));
		stuList.add(new Student(20177147, "배문기", 91, 95, 100));
		stuList.add(new Student(20177125, "오미나", 97, 94, 100));
		stuList.add(new Student(20177111, "박승우", 100, 92, 100));
		
		// 등수 구하는 메서드 호출
		setRanking(stuList);
		
		System.out.println("정렬전...");
		for (Student std : stuList) {
			System.out.println(std);
		}
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("학번의 오름차순 정렬후...");
		
		Collections.sort(stuList);
		for (Student student : stuList) {
			System.out.println(student);
		}
		System.out.println("--------------------------------------------------------------------------------------------");
		Collections.sort(stuList, new SortByTotal());
		
		for (Student std2 : stuList) {
			System.out.println(std2);
		}
	}

}

//총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스
class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getSumScore() == std2.getSumScore()) {
			return std1.getName().compareTo(std2.getName());
		}else {
			return Integer.compare(std1.getSumScore(), std2.getSumScore()) * -1;
		}
	}
}

class Student implements Comparable<Student>{
	private int stuNum;
	private String name;
	private int koreanScore;
	private int engScore;
	private int mathScore;
	private int sumScore;
	private int rank;

	public Student(int stuNum, String name, int koreanScore, int engScore, int mathScore) {
		super();
		this.stuNum = stuNum;
		this.name = name;
		this.koreanScore = koreanScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
	}


	public int getStuNum() {
		return stuNum;
	}
	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKoreanScore() {
		return koreanScore;
	}
	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}
	public int getEngScore() {
		return engScore;
	}
	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	public int getSumScore() {
		sumScore = koreanScore+engScore+mathScore;
		return sumScore;
	}
	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return String.format(
				"Student [stuNum=%s, name=%s, koreanScore=%s, engScore=%s, mathScore=%s, sumScore=%s, rank=%s]", stuNum,
				name, koreanScore, engScore, mathScore, getSumScore(), rank);
	}

	//학번의 오름차순으로 정렬하기
	@Override
	public int compareTo(Student stu) {
		//오름차순
		return new Integer(this.getStuNum()).compareTo(stu.getStuNum());
	}

}