package mvc_service;

import java.util.List;
import java.util.Map;

import mvc_dao.IMemberDAO;
import mvc_dao.MemberDAOImpl;
import mvc_vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDAO dao;
	// 1번
	private static MemberServiceImpl service;
	
	// 2번 생성자
	private MemberServiceImpl() {
	//선언은 인터페이스로 하고 실제 객체 생성은 자식인 클래스로 해라
		dao = MemberDAOImpl.getInstance(); // DAO객체 생성
	}
	// 3번
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return dao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}
	

	@Override
	public int updateMember3(Map<String, String> dataMap) {
		return dao.updateMember3(dataMap);
	}


	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return dao.updateMember2(paramMap);
	}


}
