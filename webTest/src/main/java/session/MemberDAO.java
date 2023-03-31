package session;

import org.apache.ibatis.session.SqlSession;

import memberVO.MemberVO;
import util.MybatisSqlSessionFactory;

public class MemberDAO {
	private static MemberDAO dao;
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if(dao == null) dao = new MemberDAO();
		return dao;
	}
	
	public MemberVO getMember(MemberVO memVo) {
		SqlSession session = null;
		
		MemberVO vo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			vo = session.selectOne("member.getMember", memVo);
			
		} finally {
			session.close();
		}
		
		return vo;
	}
}
