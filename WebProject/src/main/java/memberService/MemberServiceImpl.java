package memberService;

import java.util.List;

import memberDAO.IMemberDAO;
import memberDAO.MemberDAOImpl;
import memberVO.MemberVO;

public class MemberServiceImpl implements IMemberService {
	
	private IMemberDAO dao;
	
	private static IMemberService service;
	
	private MemberServiceImpl() {
		dao = MemberDAOImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	

	@Override
	public List<MemberVO> memberAllList() {
		return dao.memberAllList();
	}

	@Override
	public int memberInsertInfo(MemberVO vo) {
		return dao.memberInsertInfo(vo);
	}

	@Override
	public MemberVO memberInfo(String memId) {
		return dao.memberInfo(memId);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		return dao.memberUpdate(vo);
	}

	@Override
	public int idCheck(String memId) {
		return dao.idCheck(memId);
	}

}
