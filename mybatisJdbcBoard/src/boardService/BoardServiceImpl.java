package boardService;

import java.util.List;

import boardDAO.BoardDAOImpl;
import boardDAO.IBoardDAO;
import boardVO.BoardVO;



public class BoardServiceImpl implements IBoardService {
	private IBoardDAO dao;
	
	// 1번
	private static BoardServiceImpl service;
	
	// 2번
	private BoardServiceImpl() {
		dao = BoardDAOImpl.getInstance();
	}
	
	// 3번
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}
	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		// 게시글 보기에서는 조회수를 증가하는 작업이 같이 수행해야 한다.
		int cnt = dao.setConutIncrement(boardNo);
		if(cnt==0) {// 조회수 증가 실패! 
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<BoardVO> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int setConutIncrement(int boardNo) {
		return dao.setConutIncrement(boardNo);
	}
	
}
