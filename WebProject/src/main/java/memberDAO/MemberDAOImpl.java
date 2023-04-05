package memberDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import memberVO.MemberVO;
import util.MybatisSqlSessionFactory;

public class MemberDAOImpl implements IMemberDAO {
	private static IMemberDAO dao;
	
	private MemberDAOImpl() {}
	
	public static IMemberDAO getInstance() {
		if(dao==null) dao = new MemberDAOImpl();
		return dao;
	}
	
	@Override
	public List<MemberVO> memberAllList() {
		SqlSession session = null;
		List<MemberVO> list = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("member.memberAllList");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int memberInsertInfo(MemberVO vo) {
		SqlSession session = null;
		int result = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			result = session.insert("member.memberInsertInfo", vo);
			System.out.println(result);
		} finally {
			session.commit();
			session.close();
		}
		return result;
	}

	@Override
	public MemberVO memberInfo(String memId) {
		SqlSession session = null;
		MemberVO vo = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			vo = session.selectOne("member.memberInfo", memId);
		} finally {
			session.commit();
			session.close();
		}
		return vo;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		SqlSession session = null;
		int result = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			result = session.update("member.memberUpdate");
		} finally {
			session.commit();
			session.close();
		}
		return result;
	}

	@Override
	public int idCheck(String memId) {
		SqlSession session = null;
		int result = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			result = session.selectOne("member.idCheck", memId);
			
		} finally {
			session.commit();
			session.close();
		}
		return result;
	}

}
