package fileuploadDAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MybatisSqlSessionFactory;
import vo.FileinfoVO;

public class FileinfoDAOImpl implements IFileinfoDAO {
	private static FileinfoDAOImpl dao;
	
	private FileinfoDAOImpl() {}
	
	public static FileinfoDAOImpl getInstance() {
		if(dao==null) dao = new FileinfoDAOImpl();
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		SqlSession session = null;
		int cnt = 0;  //반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVo);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null) session.commit();
			if(session!=null) session.close();
		}
		return cnt;
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		SqlSession session = null;
		List<FileinfoVO> fileList = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return fileList;
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		SqlSession session = null;
		FileinfoVO filevo = null;
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			filevo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null) session.close();
		}
		return filevo;
	}

}
