package boardDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;


import boardVO.BoardVO;
import util.MybatisSqlSessionFactory;

public class BoardDAOImpl implements IBoardDAO{
	private static IBoardDAO dao;
	
	private BoardDAOImpl() {};
	
	public static IBoardDAO getInstance() {
		if(dao==null) dao = new BoardDAOImpl();
		return dao;
	}
	
	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.insert("board.insertBoard", boardVo);
			
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
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.insert("board.deleteBoard", boardNo);
			
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
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("board.updateBoard", boardVo);
			
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
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO vo = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			vo = session.selectOne("board.getBoard", boardNo);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return vo;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		SqlSession session = null;
		List<BoardVO> list = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("board.getAllBoardList");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return list;
	}

	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		SqlSession session = null;
		List<BoardVO> list = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("board.getSearchBoardList", title);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.commit();
			session.close();
		}
		return list;
	}

	@Override
	public int setConutIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			cnt = session.update("board.setConutIncrement", boardNo);
			
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
