package enumTest;

public class EnumTest {
	public enum Color{RED, GREEN, BLUE}
	
	public enum Count{ONE, TWO, THREE}
	
	public enum Season{
		//상수명(값들...)형식의 선언
		봄("3월부터 5월까지", 15),
		여름("6월부터 8월까지", 32),
		가을("9월부터 11월까지", 17),
		겨울("12월부터 2월까지", -4);
		
		//'값들'이 저장될 변수들 선언
		private String span;
		private int temp;
		
		//생성자
		Season(String span, int temp){ // -> private Season(String span, int temp){}와 같다
			this.span = span;
			this.temp = temp;
		}
		
		//getter 메서드 만들기
		public String getSpan() {
			return this.span;
		}
		
		public int getTemp() {
			return this.temp;
		}
		
	}
	
	public static void main(String[] args) {

		Color mycol = Color.valueOf("RED"); //Color.RED와 같다.
		Count mycnt = Count.THREE; // Count.valueOf("THREE")와 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		System.out.println();
		
		System.out.println("mycol ordinal : "+ mycol.ordinal());
		System.out.println("mycnt ordinal : "+ mycnt.ordinal());
		System.out.println();
		
		// 서로 다른 종류의 열거형끼리의 비교는 불가하다.
//		if(mycol==mycnt) {
//			System.out.println("....");
//		}
		
		if(mycol == Color.RED) {
			System.out.println("같다...");
		}
		System.out.println();
		
		//switch의 case문에 열거형을 배치할 때는 '열거형이름'을 생략하고
		//상수명만 기술해야 한다.
		switch(mycnt) {
			case ONE : System.out.println("ONE"); break;
			case TWO : System.out.println("TWO"); break;
			case THREE : System.out.println("THREE"); break;
		}
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		
		for(Color col : Color.values()) {
			System.out.println(col + " ==> " + col.ordinal());
		}
		System.out.println();
		
		for (Season s : Season.values()) {
			System.out.println(s.name() + " == " + s + " ==> " + s.getSpan()
					+ "의 평균온도 : " + s.getTemp() + "도");
			
		}
	}
}
