package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제 ) 사용자로부터 Lprod_id값을 입력 받아
//        입력한 값보다 Lprod_id가 큰 자료들을 출력하시오

public class JdbcTest02 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Lprod_id값 입력 > ");
		int id = scan.nextInt();
		System.out.println();
		
		Statement state = null;
		Connection conn = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT");
			sb.append("     *");
			sb.append(" FROM");
			sb.append("     lprod");
			sb.append(" where lprod_id > " + id);
			String sql = sb.toString();
			state = conn.createStatement();
			resultSet = state.executeQuery(sql);
			
			while(resultSet.next()) {
					System.out.println("lprod_id : " + resultSet.getInt("lprod_id"));
					System.out.println("lprod_gu : " + resultSet.getString("lprod_gu"));
					System.out.println("lprod_nm : " + resultSet.getString("lprod_nm"));
					System.out.println("----------------------------------------");
				}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet!=null) try {resultSet.close();}catch(SQLException e) {}
			if(state!=null) try {resultSet.close();}catch(SQLException e) {}
			if(conn!=null) try {resultSet.close();}catch(SQLException e) {}
		}
		
	}
}
