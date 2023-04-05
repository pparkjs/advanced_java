package memberService;

import java.util.List;

import memberVO.MemberVO;

public interface IMemberService {
	// 모든 회원 목록 보기
	public List<MemberVO> memberAllList();
	
	// 회원을 추가하는 메서드
	public int memberInsertInfo(MemberVO vo);
	
	// 회원의 상세정보 보기
	public MemberVO memberInfo(String memId);
	
	// 회원 정보 수정하기
	public int memberUpdate(MemberVO vo);
	
	// 아이디 중복체크
	public int idCheck(String memId);
	
}
