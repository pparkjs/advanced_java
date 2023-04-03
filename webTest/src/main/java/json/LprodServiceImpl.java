package json;

import java.util.List;

public class LprodServiceImpl implements ILprodService {
	private ILprodDAO dao;
	private static ILprodService service;
	
	private LprodServiceImpl() {
		dao = LprodDAOImpl.getInstance();
	}
	
	public static ILprodService getInstance() {
		if(service == null) service = new LprodServiceImpl();
		return service;
	}
	
	@Override
	public List<LprodVO> lprodAllList() {
		return dao.lprodAllList();
	}

}
