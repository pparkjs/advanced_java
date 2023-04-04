package fileuploadService;

import java.util.List;

import fileuploadDAO.FileinfoDAOImpl;
import fileuploadDAO.IFileinfoDAO;
import vo.FileinfoVO;

public class FileinfoServiceImpl implements IFileinfoService {
	private IFileinfoDAO dao;
	
	private static FileinfoServiceImpl service;
	
	private FileinfoServiceImpl() {
		dao = FileinfoDAOImpl.getInstance();
	}
	
	public static FileinfoServiceImpl getInstance() {
		if(service==null) service = new FileinfoServiceImpl();
		return service;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		return dao.insertFileinfo(fileVo);
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		return dao.getAllFileinfo();
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		return dao.getFileinfo(fileNo);
	}

}
