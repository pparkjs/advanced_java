package genericTest;

// 제네릭을 사용하지 않는 class
class NonGeneric{
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

class MyGeneric<T>{
	private T Value; //변수 선언

	public T getValue() { //반환값 타입
		return Value;
	}

	public void setValue(T value) { // 매개변수
		Value = value;
	}


}


public class GenericTest {
	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라");

		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);

		String sTemp = (String)ng1.getValue();
		int iTemp = (int)ng2.getValue();
		System.out.println("문자열 반환값 : " + sTemp);
		System.out.println("정수형 반환값 : " + iTemp);

//		String sTemp2 = (String)ng2.getValue();
		System.out.println("--------------------------------");

		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("안녕하세요.");

		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);

		sTemp = mg1.getValue();
		iTemp = mg2.getValue();
		System.out.println("제네릭 문자열 반환값 : " + sTemp);
		System.out.println("제네릭 문자열 반환값 : " + iTemp);

//		String sTemp3 = mg2.getValue();
	}
}	
