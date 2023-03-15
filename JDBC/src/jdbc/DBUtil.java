package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드
// 구성된 class 작성하기
public class DBUtil {
	
	//static초기화 블럭 : 객체가 실행되면 무조건 처음 딱 한번 읽어들임
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	// Connection객체를 반환하는 메서드
	public static Connection getConnetion() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");

		} catch (SQLException e) {
			System.out.println("DB연결 실패!!");
			return null;
		}
	}
}
