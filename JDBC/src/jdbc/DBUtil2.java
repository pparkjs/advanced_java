package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection객체를 생성해서 반환하는 메서드
// 구성된 class 작성하기 
// ( dbinfo.properties파일의 내용으로 설정하는 방법
public class DBUtil2 {
	private static Properties prop; // Properties객체 변수 선언
	
	
	static {
		prop = new Properties(); //Properties객체 생성
		File f = new File("res/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			
			fin = new FileInputStream(f);
			prop.load(fin);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("입출력 오류... : 드라이버 로딩 실패!!!");
			e.printStackTrace();
		} finally {
			if(fin!=null) try { fin.close();} catch(IOException e) {}
		}
	}
	
	
	// Connection객체를 반환하는 메서드
	public static Connection getConnetion() {
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB연결 실패!!");
			return null;
		}
	}
}
