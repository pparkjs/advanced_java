package IO;

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
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new PhoneBookTest().test();
	}

	public void test() {
		HashMap<String, Phone> mem = new HashMap<>(); //회원 정보를 저장할 Map
		
		while(true){
			menuPrint();
			int select = Integer.parseInt(scan.nextLine());
			switch (select) {
			case 1://전화번호 등록
				phoneInsert(mem);
				break;
			case 2://전화번호 수정
				phoneUpdate(mem);
				break;
			case 3://전화번호 삭제
				phoneDelete(mem);
				break;
			case 4://전화번호 검색
				phoneSelect(mem);
				break;
			case 5://전화번호 전체 출력
				phoneSelects(mem);
				break;
			case 6://전화번호 저장
				phoneSave(mem);
				return;
			case 7://프로그램 종료
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

	public void phoneSave(HashMap<String, Phone> mem) {
		
	}

	public void menuPrint() {
		System.out.println("------------------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------------------");
		System.out.print("번호입력 >> ");
	}

	//전화번호 등록하는 메서드
	public void phoneInsert(HashMap<String, Phone> mem) {
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
				new Phone(name); //이건 따로 Phone 클래스에 저장
				mem.put(name, new Phone(addr, pNum));
				System.out.println();
				System.out.println(name + "님의 전화번호 정보가 등록되었습니다.");
				return;
			}else {
				System.out.println();
				System.out.println(name + "님은 이미 등록된 사람입니다.");
				System.out.println();
			}
		}

	}

	//전화번호부 수정하는 메서드
	public void phoneUpdate(HashMap<String, Phone> mem) {
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
				return;
			}else {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
			}
		}

	}

	//전화번호부 삭제하는 메서드
	public void phoneDelete(HashMap<String, Phone> mem) {
		while(true) {
			System.out.println("전화번호부를 삭제할 사람의 이름을 입력하세요.");
			System.out.print(">> ");
			String name = scan.nextLine();
			if(mem.containsKey(name)) {
				Phone remove = mem.remove(name);
				System.out.println();
				System.out.println(name+"님의 정보가 삭제되었습니다.");
				System.out.println();
				return;
			}else {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
			}
		}
	}

	//전화번호부 검색 메서드
	public void phoneSelect(HashMap<String, Phone> mem) {
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
	public void phoneSelects(HashMap<String, Phone> mem) {
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

class Phone{
	private String name;
	private String addr;
	private String pNumber;

	public Phone() {
	}

	public Phone(String name) {
		this.name = name;
	}

	public Phone(String addr, String pNumber) {
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
