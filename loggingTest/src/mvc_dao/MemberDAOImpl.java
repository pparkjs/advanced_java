package mvc_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import mvc_vo.MemberVO;
import util.DBUtil3;

public class MemberDAOImpl implements IMemberDAO {
	private final Logger logger = Logger.getLogger(MemberDAOImpl.class);
	
	// 1번
	private static MemberDAOImpl dao;
	
	// 2번
	private MemberDAOImpl(){
		System.out.println("DAO생성자 입니다.");
	}
	
	// 3번
	public static MemberDAOImpl getInstance() {
		if(dao==null) dao = new MemberDAOImpl();
		return dao;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pState = null;
		int cnt = 0; //반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnetion();
			logger.info("Connection객체 생성 성공....");
			
			String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ " values(?, ?, ?, ?, ? ) ";

			pState = conn.prepareStatement(sql);
			pState.setString(1, memVo.getMemId());
			pState.setString(2, memVo.getMemPass());
			pState.setString(3, memVo.getMemName());
			pState.setString(4, memVo.getMemTel());
			pState.setString(5, memVo.getMemAddr());
			
			logger.debug("PreparedStatement객체 생성");
			logger.debug("실행 SQL : " + sql);
			logger.debug("사용 데이터 : [" + memVo.getMemId()+ "," + memVo.getMemPass() + ", "
					+ memVo.getMemName() + ", " + memVo.getMemTel() + ", " + memVo.getMemAddr() + "]");
			
			cnt = pState.executeUpdate();
			logger.info("쿼리문 실행 성공~~~");

		} catch (SQLException e) {
			logger.error("insert 작업 실패!!");
			e.printStackTrace();
		} finally {
			if(pState!=null)try {pState.close(); logger.info("PreparedStatement객체 반납..");}catch(SQLException e) {}
			if(conn!=null)try {conn.close(); logger.info("Connection 객체 반납..");}catch(SQLException e) {}
		}

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pState = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnetion();

			String sql="delete from mymember where mem_id = ?";

			pState = conn.prepareStatement(sql);
			pState.setString(1, memId);

			count = pState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return count;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pState = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnetion();

			String sql = "update mymember set mem_pass = ?, "
					+ " mem_name = ?, mem_tel = ?, mem_addr = ?"
					+ " where mem_id = ?";

			pState = conn.prepareStatement(sql);
			pState.setString(1, memVo.getMemPass());
			pState.setString(2, memVo.getMemName());
			pState.setString(3, memVo.getMemTel());
			pState.setString(4, memVo.getMemAddr());
			pState.setString(5, memVo.getMemId());

			count = pState.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		return count;

	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pState = null;
		ResultSet rs = null;
		List<MemberVO> memList = null; //반환값이 저장될 변수

		try {
			conn = DBUtil3.getConnetion();

			String sql = "select * from mymember";

			pState = conn.prepareStatement(sql);

			rs = pState.executeQuery();

			memList = new ArrayList<>(); // List객체 생성
			while(rs.next()) {
				MemberVO memVo = new MemberVO(); //1개의 레코드가 저장될 VO객체 생성

				// ResultSet의 데이터를 꺼내와 VO 객체에 셋팅한다.
				memVo.setMemId(rs.getString("mem_id"));
				memVo.setMemPass(rs.getString("mem_pass"));
				memVo.setMemName(rs.getString("mem_name"));
				memVo.setMemTel(rs.getString("mem_tel"));
				memVo.setMemAddr(rs.getString("mem_addr"));

				//VO객체를 List에 추가한다.
				memList.add(memVo);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}

		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pState = null;
		ResultSet rs = null;

		int count = 0; // 반환값이 저장될 변수 선언

		try {
			conn = DBUtil3.getConnetion();

			String sql = "select count(*) cnt from mymember where mem_id = ?";

			pState = conn.prepareStatement(sql);
			pState.setString(1, memId);

			rs = pState.executeQuery();

			if(rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return count;
	}



	@Override
	public int updateMember3(Map<String, String> dataMap) {
		Connection conn = null;
		PreparedStatement pState = null;
		int cnt = 0; //반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnetion();

			String temp =""; // SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수

			for (String fieldName : dataMap.keySet()) {
// Map의 key값 중 'memId'는 검색할 Id값에 대한 정보이기 때문에 수정할 컬럼을 설정할 때는 포함하지 않는다.				
				if(!"memId".equals(fieldName)) { 
					if(!"".equals(temp)) {
						temp += ", ";
					}
					temp += fieldName + " = ?";
				}
			}

			String sql = "update mymember set " + temp+ " where mem_id = ? ";

			pState = conn.prepareStatement(sql);
			int num = 1;
			for (String fieldName : dataMap.keySet()) {
				if(!"memId".equals(fieldName)) { 
						pState.setString(num++, dataMap.get(fieldName));
					}
			}
			pState.setString(num, dataMap.get("memId"));
			cnt = pState.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}

		return cnt;
	}

//Map의 정보 ==> key값 : 수정할컬럼명(field), 수정할 데이터(data), 검색할 회원ID(memId)
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pState = null;
		int cnt = 0; //반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnetion();
			String sql = "update mymember set " + paramMap.get("field") +" = ? where mem_id = ?";
			pState = conn.prepareStatement(sql);
			pState.setString(1, paramMap.get("data"));
			pState.setString(2, paramMap.get("memId"));
			
			cnt = pState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}


}








