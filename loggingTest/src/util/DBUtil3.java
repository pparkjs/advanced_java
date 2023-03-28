package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드
// 구성된 class 작성하기
// (dbinfo.properties파일의 내용으로 설정하는 방법 )

// resourceBundle객체 이용하기
public class DBUtil3 {
	//로그기록 남는 준비완료
	static final Logger logger = Logger.getLogger(DBUtil3.class);
	
	private static ResourceBundle bundle; // ResourceBundle 객체 변수 선언
	
	static {
		bundle = ResourceBundle.getBundle("config.dbinfo"); //객체 생성
		logger.info("ResourceBuncdle 객체 생성 - dbinfo.properties파일 읽기");
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			//레벨은 내가 정하는것
			logger.debug("DB 드라이버 로딩 성공!!!");
		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패!");
			logger.error("드라이버 로딩 실패", e);
			e.printStackTrace();
		}
	}
	
	// Connection객체를 반환하는 메서드
	public static Connection getConnetion() {
		Connection conn = null;
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
			logger.info("DB 연결 성공 - Connection객체 생성");
			
		} catch (SQLException e) {
//			System.out.println("DB연결 실패!!");
			logger.error("DB 연결 실패", e);
			return null;
		}
		return conn;
	}
}
