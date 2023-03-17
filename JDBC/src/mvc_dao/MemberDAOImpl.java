package mvc_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import jdbc.DBUtil;
import jdbc.DBUtil3;
import mvc_vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO {

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pState = null;
		int cnt = 0; //반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnetion();
			 conn = DBUtil.getConnetion();
	         String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
	               + " values(?, ?, ?, ?, ? ) ";
	         
	         pState = conn.prepareStatement(sql);
	         pState.setString(1, memVo.getMemId());
	         pState.setString(2, memVo.getMemPass());
	         pState.setString(3, memVo.getMemName());
	         pState.setString(4, memVo.getMemTel());
	         pState.setString(5, memVo.getMemAddr());

	         cnt = pState.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMember() {
		// TODO Auto-generated method stub
		return null;
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
		} finally {
			if(pState!=null)try {pState.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return count;
	}

}








