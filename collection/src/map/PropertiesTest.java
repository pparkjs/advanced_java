package map;

import java.util.Properties;

public class PropertiesTest {
	/*
	    Properties객체는 Map보다 축소된 기능의 객체라고 할 수 있다.
	    
	    Map은 key값과 value값에 모든 종류의 객체를 사용할 수 있지만
	    Properties객체는 key와 value에 String만 사용할 수 있다.
	    
	    Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
	    Properties객체는 setProperty(), getProperty()메서드를 사용하여 입출력한다.
	    
	    Properties객체는 데이터를 파일로 입출력할 수 있다.
	    
	 */
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		int age = 25;
		prop.setProperty("age2", "" + age); //문자열로 바꾸는 방법1
		prop.setProperty("age3", String.valueOf(age)); //문자열로 바꾸는 방법2
		prop.setProperty("tel", "010-1234-0987");
		prop.setProperty("addr", "대전");
		
		// ----------------------------------
		String name = prop.getProperty("name");
		String strAge = prop.getProperty("age");
		int iAge = Integer.parseInt(prop.getProperty("age2")); //숫자형으로 변환
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 :" + name);
		System.out.println("나이1 : " + strAge);
		System.out.println("나이2 : " + iAge);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
		
	}
}
