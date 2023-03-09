package IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
  Scanner의 next(), nextInt(), nextDouble()등..
     => 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
     
  Scanner의 nextLine()
     => 한 줄 단위로 입력한다.
        (즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서 Enter키를 뺀 나머지를 반환한다.)
     - 컴퓨터의 입력 작업은 입력된 데이터를 입력 버퍼에 저장하고 이것을 차례로 꺼내가는 방법으로 처리된다.
       그래서 next(), nextInt()등과 같은 메서드를 실행한 후에 nextLine()을 사용할 때는
       입력 버퍼를 비워줘야 한다. (방법 : nextLine()을 한번 더 사용한다.
       
       
       - 추가 조건)
       1) '6' 전화번호 저장' 메뉴를 추가하고 구현한다.
       		(저장파일명은 'phoneData.bin')
       2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
       3) 프로그램을 종료할 때 Map의 데이터가의 변화가 있으면
       (데이터의 추가, 수정, 삭제작업 후 저장하지 않은 상태) 자료를 저장한 후 종료되도록 한다.
 */


public class PhoneBookTest {
	private Scanner scan;
	private String fileName = "d:/d_other/phoneData.bin";
	private HashMap<String, Phone> mem; //회원 정보를 저장할 Map
	
	//데이터의 변화가 있었는지 여부를 저장하는 변수
	//데이터의 변화가 있으면 이 변수값이 true가 된다.
	private boolean isDataChange = false; 
	
	public PhoneBookTest() {
		
		mem = load(); // 파일 내용을 읽어와 Map에 저장하기
		if(mem == null) { //파일이 없을 때
			mem = new HashMap<>();
		}
		
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new PhoneBookTest().test();
	}

	public void test() {
		while(true){
			menuPrint();
			int select = Integer.parseInt(scan.nextLine());
			switch (select) {
			case 1://전화번호 등록
				phoneInsert();
				break;
			case 2://전화번호 수정
				phoneUpdate();
				break;
			case 3://전화번호 삭제
				phoneDelete();
				break;
			case 4://전화번호 검색
				phoneSelect();
				break;
			case 5://전화번호 전체 출력
				phoneSelects();
				break;
			case 6://전화번호 저장
				phoneSave();
				break;
			case 0://프로그램 종료
				if(isDataChange==true) {
					System.out.println("변경된 자료가 있어서 저장 후 종료합니다..");
					phoneSave();
				}
				System.out.println("프로그램을 종료합니다...");
				return;

			default:
				break;
			}


		}
	}
/*
        - 추가 조건)
       1) '6' 전화번호 저장' 메뉴를 추가하고 구현한다.
       		(저장파일명은 'phoneData.bin')
       2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
       3) 프로그램을 종료할 때 Map의 데이터가의 변화가 있으면
       (데이터의 추가, 수정, 삭제작업 후 저장하지 않은 상태) 자료를 저장한 후 종료되도록 한다.
 */

	// 파일에 저장된 전화번호 정보를 읽어와서 Map에 추가한 후 반환하는 메서드
	private HashMap<String, Phone> load(){
		HashMap<String, Phone> pMap = null;   // 반환값이 저장될 변수 선언
		File file = new File(fileName);
		if(!file.exists()) { // 저장된 파일이 없으면...
			return null;
		}
		
	// 저장된 파일 있을 때 처리되는 영역...
		ObjectInputStream oin = null;
		try {
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			// 파일에 저장된 데이터를 읽어와 Map객체에 저장하기
			
//			// '방법1'로 저장했을 때 (Map 자체를 저장했을 때...)
//			pMap = (HashMap<String, Phone>)oin.readObject();
			
			// '방법2'로 저장 했을 때
			Object obj = null; // 읽어온 데이터가 저장될 변수
			pMap = new HashMap<>(); // 저장할 Map객체 생성
			while((obj=oin.readObject())!=null) {
				Phone p = (Phone) obj;
				pMap.put(p.getName(), p);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(oin!=null)  try { oin.close();}catch(IOException e) {}
		}
		
		return pMap;
	}
	
	
	//전화번호 정보를 파일로 저장하는 메서드
	public void phoneSave() {
		ObjectOutputStream oout = null;
		try {
			// 객체 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(fileName)));
			
			// Map 에 저장된 전화번호 정보를 파일로 출력한다.
//			oout.writeObject(mem); //Map객체 자체 저장하기 (방법1)
			
			//Map에 저장된 Phone객체를 하나씩 꺼내서 저장하기 (방법2)
			for(String name : mem.keySet()) {
				Phone p = mem.get(name);
				oout.writeObject(p);
			}
			oout.writeObject(null);
			
			System.out.println("저장이 완료되었습니다.");
			
			isDataChange = false;
			
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			//스트림 닫기
			if(oout!=null) try { oout.close();} catch(IOException e) {}
		}
	}

	public void menuPrint() {
		System.out.println("------------------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------------------");
		System.out.print("번호입력 >> ");
	}

	//전화번호 등록하는 메서드
	public void phoneInsert() {
		while(true){
			System.out.println();
			System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
			System.out.print("이 름 >> ");
			String name = scan.nextLine();
			if(!mem.containsKey(name)) {
				
				System.out.print("전화번호 >> ");
				String pNum = scan.nextLine();
				
				System.out.print("주 소 >> ");
				String addr = scan.nextLine();
				mem.put(name, new Phone(name, addr, pNum));
				System.out.println();
				System.out.println(name + "님의 전화번호 정보가 등록되었습니다.");
				
				isDataChange = true;
				return;
			}else {
				System.out.println();
				System.out.println(name + "님은 이미 등록된 사람입니다.");
				System.out.println();
			}
		}

	}

	//전화번호부 수정하는 메서드
	public void phoneUpdate() {
		while(true) {
			System.out.println("전화번호를 수정할 사람의 이름을 입력하세요.");
			System.out.print(">> ");
			String name = scan.nextLine();

			if(mem.containsKey(name)) {
				Phone phone = mem.get(name);
				System.out.println("수정할 전화번호를 입력하세요.");
				String pNum = scan.nextLine();
				phone.setpNumber(pNum);
				mem.put(name, phone);
				System.out.println("수정할 주소를 입력하세요.");
				String addr = scan.nextLine();
				phone.setAddr(addr);
				mem.put(name, phone);
				System.out.println();
				System.out.println("수정이 완료되었습니다.");
				System.out.println();
				
				isDataChange = true;
				
				return;
			}else {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
			}
		}

	}

	//전화번호부 삭제하는 메서드
	public void phoneDelete() {
		while(true) {
			System.out.println("전화번호부를 삭제할 사람의 이름을 입력하세요.");
			System.out.print(">> ");
			String name = scan.nextLine();
			if(mem.containsKey(name)) {
				Phone remove = mem.remove(name);
				System.out.println();
				System.out.println(name+"님의 정보가 삭제되었습니다.");
				System.out.println();
				
				isDataChange = true;
				
				return;
			}else {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
			}
		}
	}

	//전화번호부 검색 메서드
	public void phoneSelect() {
		while(true) {
			System.out.println("검색할 이름을 입력하세요.");
			System.out.print(">> ");
			String name = scan.nextLine();
			if(mem.containsKey(name)) {
					Phone phone = mem.get(name);
					System.out.println("-----------------------------------------------------------------");
					System.out.println("이 름\t전화번호\t주 소");
					System.out.println(name +"\t"+ phone.getpNumber() + "\t" +phone.getAddr());
					System.out.println("-----------------------------------------------------------------");
				return;
			}else {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
			}
		}
	}

	//전화번호부 전체 출력 메서드
	public void phoneSelects() {
		int cnt = 0;
			System.out.println("-----------------------------------------------------------------");
		for (String memInfor : mem.keySet()) {
			cnt++;
			Phone phone = mem.get(memInfor);
			System.out.println("번 호\t이 름\t전화번호\t주 소");
			System.out.println("  " + cnt +"\t"+memInfor +"\t"+ phone.getpNumber() + "\t" +phone.getAddr());
		}
		System.out.println("-----------------------------------------------------------------");

	}

}

class Phone implements Serializable{
	private String name;
	private String addr;
	private String pNumber;

	public Phone() {
	}


	public Phone(String name, String addr, String pNumber) {
		this.name = name;
		this.addr = addr;
		this.pNumber = pNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getpNumber() {
		return pNumber;
	}

	public void setpNumber(String pNumber) {
		this.pNumber = pNumber;
	}

	@Override
	public String toString() {
		return String.format("Phone [name=%s, addr=%s, pNumber=%s]", name, addr, pNumber);
	}



}
