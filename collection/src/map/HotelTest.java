package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelTest {
	private Map<Integer, Room> roomMg;
	private Scanner scan;
	private Room room;

	public HotelTest() {
		roomMg = new HashMap<>();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new HotelTest().run();
	}

	public void printStart() {
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
	}

	public void run() {
		printStart();
		addRoom(); //객실 초기화  
		while (true) {
			System.out.println("-----------------------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
			System.out.println("-----------------------------------------------------------");
			System.out.print("선택>> ");
			int select = Integer.parseInt(scan.nextLine());
			System.out.println();
			switch (select) {
			case 1://체크인
				checkIn();
				break;
			case 2://체크아웃
				checkOut();
				break;
			case 3://객실상태
				roomState();
				break;
			case 4://업무종료
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;

			default:
				break;
			}
		}
	}

	private void roomState() {
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	   방 종류  투숙객 이름");
		System.out.println("----------------------------------------------");
		for(int key : roomMg.keySet()){
			System.out.println(roomMg.get(key));
		}
	}

	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방번호 입력 >> ");
		int num = Integer.parseInt(scan.nextLine());
		Room room2 = roomMg.get(num);

		// 객실이 존재하지 않는경우
		if(room2 == null) {
			System.out.println(num+"호 객실은 존재하지 않습니다.");
			System.out.println();
		}
		// 체크인 한 사람이 없는경우
		else if(room2.getName() == "-") {
			System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다.");
			System.out.println();
		}
		// 체크인 한 사람이 있는 경우
		else {
			String name = room2.getName();
			room2.setName("-");
			roomMg.put(num, room2);
			System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료하였습니다." );
			System.out.println();
		}
	}

	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int num = Integer.parseInt(scan.nextLine());
		Room room2 = roomMg.get(num);//해당 방에 객체를 불러옴

		if(room2 == null) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			System.out.println();
		}else if(room2.getName() != "-") {
			System.out.println();
			System.out.println(num + "호 객실은 이미 손님이 있습니다.");
			System.out.println();
		}else {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 >> ");
			String name = scan.nextLine();

			room2.setName(name); // 해당 객체에 이름 추가
			roomMg.put(num, room2); // 최종 Map에 추가 수정함
			System.out.println("체크인이 완료되었습니다.");
			System.out.println();
		}
	}   

	private void addRoom() {
		//201~209 싱글룸 채워 넣기
		for (int i = 201; i <= 209; i++) {
			room = new Room(i, " 싱글룸 ");
			roomMg.put(i, room);
		}
		//301~309 더블룸 채워 넣기
		for (int i = 301; i <= 309; i++) {
			room = new Room(i, " 더블룸 ");
			roomMg.put(i, room);
		}
		//401~409 스위트룸 채워 넣기
		for (int i = 401; i <= 409; i++) {
			room = new Room(i, "스위트룸");
			roomMg.put(i, room);
		}
	}
}

class Room{
	private int roomNum;
	private String roomType;
	private String name;

	public Room(int roomNum, String roomType) {
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.name = "-";
	}

	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return String.format("  %s     %s     %s", roomNum, roomType, name);
	}
}
