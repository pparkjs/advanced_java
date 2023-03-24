package memberDAO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import basic.MybatisSqlSessionFactory;
import memberVO.MemberVO;

public class MemberDAOImpl implements IMemberDAO{
	private static MemberDAOImpl dao;
	
	//생성자
	private MemberDAOImpl() {
		
	}
	
	public static MemberDAOImpl getInstance() {
		if(dao==null) dao = new MemberDAOImpl();
		return dao;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.insert("member.insertMember", memVo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.delete("member.deleteMember", memId);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("member.updateMember", memVo);
		}catch(Exception e) {
			e.printStackTrace();
		} 
		finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> selectList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			selectList = session.selectList("getAllMember");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return selectList;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.selectOne("member.getMemberCount", memId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("member.updateMember2", paramMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("member.updateMember3", dataMap);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return cnt;
	}
	
}
