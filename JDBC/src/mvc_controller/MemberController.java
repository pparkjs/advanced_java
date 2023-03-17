package mvc_controller;

import java.util.Scanner;

import mvc_service.IMemberService;
import mvc_service.MemberServiceImpl;
import mvc_vo.MemberVO;

public class MemberController {
	private Scanner scan;
	private IMemberService service; // Service객체 변수 선언

	public MemberController() {
		scan = new Scanner(System.in);
		service = new MemberServiceImpl(); //Service 객체 생성
	}

	public static void main(String[] args) {
		new MemberController().startMember();
	}

	//작업을 시작하는 메소드
	public void startMember() {
		while(true) {
			int select = selectMenu();

			switch (select) {
			case 1:	insertMember();	break;  //추가

			//			case 2:	deleteMember();	break;  //삭제
			//
			//			case 3:	updateMember();	break;  //수정
			//
			//			case 4:	displayAllMember();	break;  //전체 출력
			//
			//			case 5:	updateMember2();break;      //수정2
			//
			//			case 6:	updateMember3(); break;      //수정3

			case 0:
				System.out.println("작업을 마칩니다...");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요");
			}

		}
	}


	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");

		String id = null;
		int count = 0;
		//자료 추가에서 '회원ID'는 중복되지 않는다(중복되면 다시 입력받는다)
		do {
			System.out.println("회원ID >> ");
			id = scan.next();
			count = service.getMemberCount(id);
			if(count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원 ID입니다.");
				System.out.println("다른 회원 ID를 입력하세요.");
			}

		} while (count >0);

		System.out.println("비밀번호 >> ");
		String pass = scan.next();

		System.out.println("회원이름 >> ");
		String name = scan.next();

		System.out.println("전화번호 >> ");
		String tel = scan.next();

		scan.nextLine(); // 버퍼 비우기

		System.out.println("회원주소 >> ");
		String addr = scan.nextLine();

		// 입력한 자료를 VO객체에 담는다.
		MemberVO memVo = new MemberVO();
		memVo.setMemId(id);
		memVo.setMemPass(pass);
		memVo.setMemName(name);
		memVo.setMemTel(tel);
		memVo.setMemAddr(addr);

		// Service의 insertMember()메서드를 호출해서 실행한다.
		int cnt = service.insertMember(memVo);

		if(cnt>0) {
			System.out.println(id + " 회원 정보 추가 완료!!!");
		}else {
			System.out.println(id + " 회원 정보 추가 실패~~~");
		}

	}


	public int selectMenu() {

		System.out.println();
		System.out.println("----------------------- ");
		System.out.println("     1. 자 료  추 가      ");
		System.out.println("     2. 자 료  삭 제      ");
		System.out.println("     3. 자 료  수 정 (전체 수정)     ");
		System.out.println("     4. 전 체  자 료 출 력  ");
		System.out.println("     5. 자 료  수 정 2 (선택수정) ");
		System.out.println("     6. 자 료  수 정 3 (입력항목만수정) ");
		System.out.println("     0. 작 업  끝       ");
		System.out.println("-------------------------");
		System.out.println("작업 선택 >> ");

		return scan.nextInt();
	}

}




