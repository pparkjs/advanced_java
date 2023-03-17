package mvc_service;

import java.util.List;

import mvc_dao.IMemberDAO;
import mvc_dao.MemberDAOImpl;
import mvc_vo.MemberVO;

public class MemberServiceImpl implements IMemberService{
	// 일을 시킬 DAO객체 변수 선언
	private IMemberDAO dao;
	
	//생성자
	public MemberServiceImpl() {
	//선언은 인터페이스로 하고 실제 객체 생성은 자식인 클래스로 해라
		dao = new MemberDAOImpl(); // DAO객체 생성
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


}
