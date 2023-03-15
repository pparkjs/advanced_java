package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	LPROD테이블에 새로운 데이터 추가하기

 	Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 	Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.

 	입력 받은  Lprod_gu(pk)가 이미 등록되어 있으면 다시 입력 받아서 처리한다.

 */
public class JdbcTest05 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String gu = "";
		int count = 0;

		Connection conn = null;
		PreparedStatement pState = null;
		Statement state = null;
		ResultSet resultSet = null;

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PJS98", "java");
			
			//따로 만든 DBUtil클래스에서 호출
			conn = DBUtil.getConnetion();
			
			
			while (true) {
				System.out.print("lprod_gu 입력 >> ");
				gu = scan.next();

				StringBuilder sb = new StringBuilder();
				sb.append("SELECT ");
				sb.append("    COUNT(*) cnt ");
				sb.append("FROM ");
				sb.append("    lprod ");
				sb.append("WHERE ");
				sb.append("    lprod_gu = ? ");
				String sql = sb.toString();
				pState = conn.prepareStatement(sql);
				pState.setString(1, gu);
				resultSet = pState.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt("cnt");
				} 
				if(count == 1) System.out.println("입력한 상품 분류 코드 "+ gu + 
								"는(은) 이미 등록된 코드입니다.");
				else break;
				
			}

			System.out.print("lprod_nm 입력 >>");
			String nm = scan.next();

			StringBuilder sb2 = new StringBuilder();
			sb2.append("insert into lprod(lprod_id, lprod_gu, lprod_nm) values((select max(lprod_id)+1 ");
			sb2.append("   from lprod), ");
			sb2.append("?, ");
			sb2.append("?)  ");
			String sql2 = sb2.toString();
			pState = conn.prepareStatement(sql2);
			pState.setString(1, gu);
			pState.setString(2, nm);
			int result = pState.executeUpdate();

			System.out.println("result : " + result);

			if(result > 0) {
				System.out.println("상품이 정상적으로 등록 되었습니다.");
			}else {
				System.out.println("상품 등록 실패");
			}

//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(SQLException e){}
			if(state!=null)try {state.close();}catch(SQLException e){}
			if(pState!=null)try {pState.close();}catch(SQLException e){}
			if(resultSet!=null)try {resultSet.close();}catch(SQLException e){}

		}
	}
}














