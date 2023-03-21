package mvc_dao;

import java.util.List;

import mvc_vo.BoardVO;

public interface IBoardDAO {
	/**
	 * IBoardVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 *   
	 * @param boardVo DB에 insert할 데이터가 저장된 BoardVO객체
	 * @return 작업성공:1, 작업실패:0
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업성공:1 작업실패:0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 수정할 데이터가 저장된 BoardVO객체를 매개변수로 받아서 수정하는 메서드
	 * 
	 * @param boardVo 수정할 데이터가 저장될 BoardVo객체 
	 * @return 작업성공:1 작업실패:0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 가져오는 메서드
	 * 
	 * @param boardNo 가져올 게시글 번호
	 * @return 해당 게시글 자료가 있으면 해당 게시글 정보가 저장된 BoardVO객체,
	 * 		   자료가 없으면 null
	 */
	public BoardVO getBoard(int boardNo);
	
	/**
	 * 전체 게시글 정보를 가져와 List에 담아서 반환하는 메서드
	 * @return BoardVO객체를 갖고 있는 List객체
	 */
	public List<BoardVO> getAllBoardList();
	
	/**
	 * 게시글의 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글 제목
	 * @return검색된 결과가 저장된 List 객체
	 */
	public List<BoardVO> getSearchBoardList(String title);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo 조회수를 증가시킬 게시글의 번호
	 * @return 작업성공:1, 작업실패:0
	 */
	public int setConutIncrement(int boardNo);
	
	
}
















