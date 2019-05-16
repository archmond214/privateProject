package kr.or.nextit.member.service;

import java.util.List;

public interface MemberService {

	/**
	 * memberSearchVo 값에 해당하는 List<MemberVo> 값을 반환..
	 * memberVo 에 memberSearchVo값에 있는 정보  searchVo변수에담아서 가져옴
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<MemberVo> selectMemberList(
			MemberSearchVo searchVo
			
			) throws Exception;
	
	
	public int selectMemberTotalCnt(
			MemberSearchVo searchVo
			) throws Exception;
	
	
	public MemberVo selectMemberInfo(
			String seqNo
			
			) throws Exception;
	
	
}
