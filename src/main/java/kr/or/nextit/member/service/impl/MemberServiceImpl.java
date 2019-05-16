package kr.or.nextit.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.member.service.MemberSearchVo;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.MemberVo;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	
	@Autowired
	private MemberMapper memberMapper;
	
	
	@Override
	public List<MemberVo> selectMemberList(MemberSearchVo searchVo) throws Exception {
		
		List<MemberVo> listResult = memberMapper.selectMemberList(searchVo);
		
		return listResult;
	}


	@Override
	public int selectMemberTotalCnt(MemberSearchVo searchVo) throws Exception {
		
		try {
			return  memberMapper.selectMemberTotalCnt(searchVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}


	@Override
	public MemberVo selectMemberInfo(String seqNo) throws Exception {
		
		
		
		return null;
	}

	
}
