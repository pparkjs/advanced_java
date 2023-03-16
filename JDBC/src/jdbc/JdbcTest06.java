package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	회원을 관리하는 프로그램을 작성하시오.
 	(MYMEMBER 테이블 이용)

 	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
 	메뉴 예시)
 	-----------------
 	1. 자료 추가        --> insert (C) 
 	2. 자료 삭제		--> delete (D)
 	3. 자료 수정   		--> update (U)
 	4. 전체 자료 출력	--> select (R)
 	0. 작업 끝.
 	-----------------

 	조건)
 	1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력 받는다.)
 	2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 	3) 자료 수정에서 '회원ID'는 변경되지 않는다.

 */
public class JdbcTest06 {
	private Connection conn;
	private Statement state;
	private PreparedStatement pState;
	private ResultSet resultSet;
	Scanner scan;

	public JdbcTest06() {
		scan = new Scanner(System.in);
		conn = null;
		state = null;
		resultSet = null;
		pState = null;

	}

	public static void main(String[] args) {
		new JdbcTest06().jdbcRun();
	}

	public void jdbcRun() {

		System.out.println("======== 회원 관리 시스템 ========");
		System.out.println("");
		while(true) {
			System.out.println(" 	--------------------");
			System.out.println(" 	1. 자료 추가        ");
			System.out.println(" 	2. 자료 삭제		");
			System.out.println(" 	3. 전체 자료 수정   ");
			System.out.println("	4. 부분 자료 수정   ");
			System.out.println(" 	5. 전체 자료 출력	");
			System.out.println(" 	0. 작업 끝.");
			System.out.println(" 	--------------------");
			System.out.print("메뉴 선택 > ");
			int sel = Integer.parseInt(scan.nextLine());
			
			// 해당 작업 끝날 때 마다 반복 되서 드라이버 새로 열어주기 때문에
			conn = DBUtil.getConnetion();
			switch (sel) {
			case 1://자료 추가
				dataInsert(conn);
				break;
			case 2://자료 삭제
				dataDelete(conn);
				break;
			case 3://전체 자료 수정
				dataUpdate(conn);
				break;
			case 4://부분 자료 수정
				partDataUpdate(conn);
				break;
			case 5://전체 자료 출력
				dataSelects(conn);
				break;
			case 0: //작업 종료
				System.out.println("작업을 종료 합니다.");
				return;

			default:
				break;
			}

		}
	}
//부분데이터 수정하는 메소드
	public void partDataUpdate(Connection conn) {
		try {
			String id = "";
			String userType = "";
			String updateData = "";
			
			System.out.print("수정할 id 입력 > ");
			id = scan.nextLine();

			//id체크 메서드
			int count = idCheck(id, conn);

			if (count == 1) {
				System.out.println();
				System.out.println(id + "의 정보 수정을 진행하겠습니다.");
				System.out.println();
			}else {
				System.out.println();
				System.out.println("존재하지 않는 id입니다.");
				System.out.println("수정 작업을 마치겠습니다.");
				return;
			}
			
		while(true) {
			
			System.out.println(" 	--------------------");
			System.out.println(" 	1. 비밀번호 수정        ");
			System.out.println(" 	2. 이름 수정		");
			System.out.println(" 	3. 전화번호 수정   ");
			System.out.println("	4. 주소 수정   ");
			System.out.println(" 	0. 뒤로가기.");
			System.out.println(" 	--------------------");
			System.out.print("메뉴 선택 > ");
			int sel = Integer.parseInt(scan.nextLine());
			
			switch (sel) {
			case 1:
				userType = "mem_pass";
				System.out.print("수정할 비밀번호 입력 : ");
				String pw = scan.nextLine();
				updateData = pw;
				break;
			case 2:
				userType = "mem_name";
				System.out.print("수정할 이름 입력 : ");
				String name = scan.nextLine();
				updateData = name;
				break;
				
			case 3:
				userType = "mem_tel";
				System.out.print("수정할 전화번호 입력 : ");
				String phone = scan.nextLine();
				updateData = phone;
				break;
				
			case 4:
				userType = "mem_addr";
				System.out.print("수정할 주소 입력 : ");
				String addr = scan.nextLine();
				updateData = addr;
				break;
				
			case 0:
				return;
			}			

			StringBuilder sb2 = new StringBuilder();
			sb2.append("update MYMEMBER  ");
			sb2.append("   set ");
			sb2.append(userType);
			sb2.append(" = ? ");
			sb2.append(" where mem_id = ? ");
			String sql = sb2.toString();
			pState  = conn.prepareStatement(sql);
			pState.setString(1, updateData);
			pState.setString(2, id);

			int result = pState.executeUpdate();
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}else {
				System.out.println("수정 실패");
			}
		}
		
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}
	
	//연결을 끊어줄 메서드
	private void disConnect() {
		if(state!=null)try {state.close();}catch(SQLException e){}
		if(pState!=null)try {pState.close();}catch(SQLException e){}
		if(resultSet!=null)try {resultSet.close();}catch(SQLException e){}
		if(conn!=null)try {conn.close();}catch(SQLException e){}
	}

	//id 체크하는 메서드
	public int idCheck(String id, Connection conn) {
		int count = 0;

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("    COUNT(*) cnt ");
			sb.append("FROM ");
			sb.append("    mymember ");
			sb.append("WHERE ");
			sb.append("    mem_id = ? ");
			String sql = sb.toString();
			pState = conn.prepareStatement(sql);
			pState.setString(1, id);
			resultSet = pState.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;

	}


	public void dataSelects(Connection conn) {
		try {

			StringBuilder sb2 = new StringBuilder();
			sb2.append("select *  ");
			sb2.append("  from mymember ");
			String sql = sb2.toString();
			state = conn.createStatement();
			ResultSet resultSet = state.executeQuery(sql);

			while(resultSet.next()) {
				System.out.println("-----------" + resultSet.getString("mem_name") + "님의 정보-----------" );
				System.out.println("id : " + resultSet.getString("mem_id"));
				System.out.println("pw : " + resultSet.getString("mem_pass"));
				System.out.println("이름 : " + resultSet.getString("mem_name"));
				System.out.println("전화번호 : " + resultSet.getString("mem_tel"));
				System.out.println("주소 : " + resultSet.getString("mem_addr"));
				System.out.println("--------------------------------------");

			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}

	//전체항목 수정하는 메서드
	public void dataUpdate(Connection conn) {
		try {
			String id = "";

			System.out.print("수정할 id 입력 > ");
			id = scan.nextLine();

			//id체크 메서드
			int count = idCheck(id, conn);

			if (count == 1) {
				System.out.println();
				System.out.println(id + "의 정보 수정을 진행하겠습니다.");
				System.out.println();
			}else {
				System.out.println();
				System.out.println("존재하지 않는 id입니다.");
				System.out.println("수정 작업을 마치겠습니다.");
				return;
			}


			System.out.print("수정할 비밀번호 입력 : ");
			String pw = scan.nextLine();
			System.out.print("수정할 이름 입력 : ");
			String name = scan.nextLine();
			System.out.print("수정할 전화번호 입력 : ");
			String phone = scan.nextLine();
			System.out.print("수정할 주소 입력 : ");
			String addr = scan.nextLine();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("update MYMEMBER  ");
			sb2.append("   set mem_pass = ?, ");
			sb2.append("       mem_name = ?, ");
			sb2.append("       mem_tel = ?, ");
			sb2.append("       mem_addr =? ");
			sb2.append(" where mem_id = ? ");
			String sql = sb2.toString();
			pState  = conn.prepareStatement(sql);
			pState.setString(1, pw);
			pState.setString(2, name);
			pState.setString(3, phone);
			pState.setString(4, addr);
			pState.setString(5, id);

			int result = pState.executeUpdate();
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}else {
				System.out.println("수정 실패");
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}

	public void dataDelete(Connection conn) {
		try {

			String id = "";

			System.out.print("삭제할 id 입력 > ");
			id = scan.nextLine();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("DELETE FROM mymember WHERE ");
			sb2.append("    mem_id =? ");
			String sql = sb2.toString();
			pState = conn.prepareStatement(sql);
			pState.setString(1, id);

			int result = pState.executeUpdate();

			if(result > 0) {
				System.out.println("정상적으로 삭제 되었습니다. ");
			}else {
				System.out.println("존재하지 않는 id 입니다.");
			}



		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}

	public void dataInsert(Connection conn) {
		String id = "";
		try {	

			while(true){
				System.out.print("id 입력 > ");
				id = scan.nextLine();

				//id체크 메서드
				int count = idCheck(id, conn);

				if (count == 1) {
					System.out.println("입력한 id " + id + "는(은) 이미 등록된 id입니다.");
					System.out.println("다시 입력하세요.");
				}else {
					System.out.println("사용 가능한 id입니다.");
					break;
				}
			}

			System.out.print("비밀번호 입력 >>");
			String pw = scan.nextLine();
			System.out.print("회원이름 입력 >>");
			String name = scan.nextLine();
			System.out.print("전화번호 입력 >>");
			String phone = scan.nextLine();
			System.out.print("주소 입력 >>");
			String addr = scan.nextLine();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("INSERT INTO mymember VALUES ( ");
			sb2.append("    ?, ");
			sb2.append("    ?, ");
			sb2.append("    ?, ");
			sb2.append("    ?, ");
			sb2.append("    ? ");
			sb2.append(") ");
			String sql = sb2.toString();
			pState = conn.prepareStatement(sql);
			pState.setString(1, id);
			pState.setString(2, pw);
			pState.setString(3, name);
			pState.setString(4, phone);
			pState.setString(5, addr);

			int result = pState.executeUpdate();

			if(result > 0) {
				System.out.println(name + "님의 정보가 정삭적으로 추가되었습니다.");
			}else {
				System.out.println("정보 추가 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}


}
