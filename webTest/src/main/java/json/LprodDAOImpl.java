package json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;

public class LprodDAOImpl implements ILprodDAO {
	private static ILprodDAO dao;
	
	private LprodDAOImpl() {}
	
	public static ILprodDAO getInstance() {
		if(dao == null) dao = new LprodDAOImpl();
		return dao;
	}
	
	@Override
	public List<LprodVO> lprodAllList() {
		SqlSession session = null;
		List<LprodVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("lprod.lprodAllList");
			
		} finally {
			session.close();
		}
		return list;
	}

}
