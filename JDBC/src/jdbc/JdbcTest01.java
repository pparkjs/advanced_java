package jdbc;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC(Java DataBase Connectivity) 라이브러리를 이용한 DB자료 처리하기
public class JdbcTest01 {
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement state = null;
		ResultSet resultSet = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","PJS98","java");
			
			// 3. 질의
			// 3-1. SQL문 작성하기
			String sql = " select * from lprod";
			
			// 3-2. Statement객체 또는 PreparedStatement 객체 생성
			state = conn.createStatement();
			
			// 3-3. SQL문을 서버로 보내서 결과를 얻어온다.
			//      (실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			resultSet = state.executeQuery(sql);
			
			// 4. 결과 처리
			// 한 레코드씩 화면에 출력하기
			System.out.println(" == SQL문 처리 결과 ==");
			
			// ResultSet객체에 저장된 데이터를 꺼내오려면 반복문과 next()메서드를 이용한다.
			
			// resultSet.next() => ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드 위치로
			//                     이동 시키고 그 곳에 데이터가 있으면 true 없으면 false를 반환한다.
			while(resultSet.next()) {
				// ResultSet객체의 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식1) resultSet.get자료형이름("컬럼명 또는 alias명")
				// 형식2) resultSet.get자료형이름(컬럼번호) => 컬럼번호는 1부터 시작
				System.out.println("Lprod_id : " + resultSet.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + resultSet.getString(2));
				System.out.println("Lprod_nm : " + resultSet.getString("LPROD_NM"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//5. 자원반납
			if(resultSet!=null)try {resultSet.close();} catch(SQLException e) {}
			if(state!=null)try {state.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
	}
	
}

















