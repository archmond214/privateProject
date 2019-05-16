package kr.or.nextit.member.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.member.service.MemberSearchVo;
import kr.or.nextit.member.service.MemberVo;
@Mapper
public interface MemberMapper {

	/**
	 * 검색 조건에 해당하는 데이터를 조회 하는 기능을 하는 함수를 만듬(목록 반환)
	 * return List<MemberVo>
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */
	public List<MemberVo> selectMemberList(
			//param 객체는 1단 1개만 넘길수 있음 
			// Map,Vo,String,int,등...param2개이상을 넣을 수 없음.
			MemberSearchVo searchVo
			) throws Exception;
	
	public int selectMemberTotalCnt(MemberSearchVo searchVo) throws Exception;
	
	
	
	
}
