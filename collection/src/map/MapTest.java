package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class MapTest {
	public static void main(String[] args) {
		/*
	  - Map ==> key값과 value값을 한 쌍으로 관리하는 객체
	     - key값은 중복을 허용하지 않고 순서(index)가 없다. (Set의 특징을 갖는다.)
	     - value값은 중복을 허용한다.
		 */
		Map<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map ==> " + map);
		
		// 자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 입력한 값으로 덮어쓴다.
		map.put("addr", "서울");
		System.out.println("map ==> " + map);
		
		// 자료 삭제 ==> remove(key값) : key값이 같은 자료를 찾아 삭제한다.
        //               반환값 : 삭제된 자료의 value값이 반환된다.		       
//		String sTemp = map.remove("tel");
//		System.out.println("map ==> " + map);
//		System.out.println("sTemp ==> " + sTemp);
		
		// 자료 읽기 ==> get(key값) : key값과 짝이되는 value값이 반환된다.
		//               주어진 'key값'이 없으면 null이 반환된다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println();
		
		// key값의 존재 여부 확인 ==> containsKey(key값)
		//        ==> 해당 'key값'이 있으면 true, 없으면 false
		System.out.println("tel 키값의 존재 여부 :" + map.containsKey("tel"));
		System.out.println("tel 키값의 존재 여부 :" + map.containsKey("age"));
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println();
		
		//Map에 저장된 모든 데이터를 읽어와 사용하기
		
		// 방법1) 모든 key값을 가져와 처리하기 ==> keySet() 메서드 이용
		//       keySet()메서드는 Map에 있는 모든 key값을 읽어와 set형으로반환하는 메서드이다.
		
		// 방법 1-1) keySet()정보를 Iterator로 처리하기
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next(); //key값 1개 가져오기
			String value = map.get(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("---------------------------------");
		
		// 방법1-2) keySet()정보를 '향상된 for문'으로 처리하기
		for (String key : map.keySet()) {
			String value = map.get(key);
			
			System.out.println(key + " : " + value);
		}
		System.out.println("---------------------------------");
		
		// 방법2) value값만 가져와 처리하기 ==> values()메서드 이용
		for (String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("---------------------------------");
		
	
		
	}
	
	
}
