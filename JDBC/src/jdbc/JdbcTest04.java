package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	
	// bankinfo 테이블에 계좌번호 정보를 추가하는 예제
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement state = null;
		PreparedStatement pState = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			
			System.out.println("계좌 번호 정보 추가하기...");
			System.out.print("계좌 번호 입력 >>");
			String bankNo = scan.next();
			
			System.out.print("은행명 입력 >>");
			String bankName = scan.next();
			
			System.out.print("예금주명 입력 >>");
			String userName = scan.next();
			
			//values('11-22-333-444', '하나은행', '홍길동', sysdate);
//			StringBuilder sb = new StringBuilder();
//			sb.append("INSERT INTO bankinfo ( ");
//			sb.append("    bank_no, ");
//			sb.append("    bank_name, ");
//			sb.append("    bank_user_name, ");
//			sb.append("    bank_date ");
//			sb.append(") VALUES ( ");
//			sb.append("    '" + bankNo + "', ");
//			sb.append("    '" + bankName + "', ");
//			sb.append("    '" + userName + "', ");
//			sb.append("    sysdate ");
//			sb.append(") ");
//			String sql = sb.toString();
//			
//			System.out.println("SQL문 ==> " + sql);
//			
//			state = conn.createStatement();
			
			// select문을 실행할 때는 executeQuery()메서드를 사용하고,
			// select문이 아닌 문장(insert, update, delete 등)을
			// 실행할 때는 executeUpdate()메서드를 사용한다.
			// executeUpdate() 메서드의 반환값 ==> 작업에 성공한 레코드 수
//			int cnt = state.executeUpdate(sql);
//			
//			System.out.println("반환값 : " + cnt);
			
//			-----------------------------------------------------------------------------------------
			
			//PreparedStatement객체를 이용하여 처리하기
			
			//SQL문을 작성할 때 SQL문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO bankinfo ( ");
			sb.append("    bank_no, ");
			sb.append("    bank_name, ");
			sb.append("    bank_user_name, ");
			sb.append("    bank_date ");
			sb.append(") VALUES ( ");
			sb.append("    ?, ");
			sb.append("    ?, ");
			sb.append("    ?, ");
			sb.append("    sysdate ");
			sb.append(") ");
			String sql = sb.toString();
			// PreparedStatement객체 생성 => 사용할 SQL문을 인수값으로 넘겨준다.
			pState = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pState.set자료형이름(물음표번호, 셋팅할데이터)
			pState.setString(1, bankNo);
			pState.setString(2, bankName);
			pState.setString(3, userName);
			
			// 데이터의 셋팅이 완료되면 실행한다.
			// select문일 경우 -> executeQuery()메서드 이용
			// select문이 아닐경우 => executeUpdate()메서드 이용
			int cnt = pState.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(state!=null) try {state.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
}













