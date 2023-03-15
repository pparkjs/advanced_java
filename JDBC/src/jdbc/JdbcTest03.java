package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제 ) Lprod_id값을 2개를 입력 받아서
//       두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오.
public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 lprod_id 값 입력 >>");
		int id1 = scan.nextInt();
		System.out.print("두번째 lprod_id 값 입력 >>");
		int id2 = scan.nextInt();
		
		int max = Math.max(id1, id2);
		int min = Math.min(id1, id2);
		
		Connection conn = null;
		Statement state = null;
		ResultSet resultSet = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT");
			sb.append("     *");
			sb.append(" FROM");
			sb.append("     lprod");
			sb.append(" WHERE");
			sb.append("     lprod_id >= " + min);
			sb.append("     AND   lprod_id <= " + max);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(resultSet!=null) try {resultSet.close();}catch(SQLException e) {}
			if(state!=null) try {resultSet.close();}catch(SQLException e) {}
			if(conn!=null) try {resultSet.close();}catch(SQLException e) {}
		}
	}
}
