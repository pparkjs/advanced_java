package fileuploadDAO;

import java.util.List;

import vo.FileinfoVO;

public interface IFileinfoDAO {
	/**
	 * FileuinfoVO에 저장된 자료를 DB에 insert하는 메서드
	 * 
	 * @param fileVo insert할 자료가 저장된 FileinfoVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertFileinfo(FileinfoVO fileVo);
	
	/**
	 * DB에 저장된 전체 v파일 정보들을 가져와 List에 담아서 가져오는 메서드
	 * 
	 * @return 파일 정보가 저장된 List객체
	 */
	public List<FileinfoVO> getAllFileinfo();
	
	/**
	 * 파일번호를 인수값으로 받아서 해당 파일 정보를 검색하여 반호나하는 메서드
	 * 
	 * @param fileNo 검색할 파일 번호
	 * @return 검색된 파일 정보가 저장된 FileinfoVO객체
	 */
	public FileinfoVO getFileinfo(int fileNo);
}
