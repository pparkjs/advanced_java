package argTest;

public class ArgTest {
	// 메서드 만들기
	public int sumArr(int[] data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	
	// 가변형 인수용 메서드 만들기 
	public int sumArg(int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨 점수 :" + sum;
	}
	
	
	public static void main(String[] args) {
		ArgTest test = new ArgTest();
		
		int[] nums = {100, 200, 300};
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5}));
		System.out.println();
		System.out.println(test.sumArg(100,200,300));
		System.out.println(test.sumArg2("홍길동", 90,80,95));
	}
}	
