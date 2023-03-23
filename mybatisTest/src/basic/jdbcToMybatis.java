package basic;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 	LPROD테이블에 새로운 데이터 추가하기

 	Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
 	Lprod_id는 현재의 Lprod_id중에서 제일 큰 값보다 1 크게 한다.

 	입력 받은  Lprod_gu(pk)가 이미 등록되어 있으면 다시 입력 받아서 처리한다.


    --> JDBC예제 중 JdbcTest05.java를 myBatis용으로 변경하시오..
    --> mapper파일명은 'jdbc-mapper.xml'로 한다.
 */

public class jdbcToMybatis {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
//		// 환경설정 파일 읽어와서 실행
//		Reader rd = null;
//		SqlSessionFactory sqlSessionFactory = null;
//		try {
//			rd = Resources.getResourceAsReader("config/mybatis-config.xml");
//			
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
//		} catch (Exception e) {
//			System.out.println("myBatis 초기화 실패!");
//			e.printStackTrace();
//		}finally {
//			if(rd!=null)try { rd.close(); }catch(IOException e) {}
//		}
		
		//sql 작업 시작
		SqlSession session = null;
		
		try {
//			session = sqlSessionFactory.openSession();
			//환경설정 클래스를 따로 만들어서 사용함
			session = MybatisSqlSessionFactory.getSqlSession();
			
			// 제일 큰 값 보다 1 큰 값 구하기
			int nextId = session.selectOne("jdbc.getNextId");
			// LPROD_GU값이 입력 받고 중복되면 다시 입력 받기
			String gu;
			int count = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 >>");
				gu = scan.next();
				
				count = session.selectOne("jdbc.getLprodCount", gu);
				
				if(count > 0) {
					System.out.println("입력한 상품 분류 코드" + gu + "은(는) 이미 등록된 코드입니다." );
					System.out.println("다른 상품 분류 코드를 다시 입력하세요..");
					System.out.println();
				}
				
			}while(count > 0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 >> ");
			String nm = scan.next();
			
			// 입력값들을 VO에 저장하기
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(nextId);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = session.insert("jdbc.insertLprod", lvo);
			
			if(cnt > 0 ) {
				System.out.println("등록 성공!!");
			}else {
				System.out.println("등록 실패!!");
			}
			
			} finally {
			//sqlSession을 생성할 때 AutoCommit이 비활성화된 상태일 때는 commit을 직접 실행해야 한다.
			session.commit();
			// 작업이 완료되면 SqlSession객체를 닫아준다.
			session.close();
		}
	}
}	
