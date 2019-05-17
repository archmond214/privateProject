package kr.or.nextit.session.service.impl;

import java.util.HashMap;

import kr.or.nextit.session.service.LoginInfoVo;

public interface SessionMapper {
	public LoginInfoVo selectMemberInfo(
			HashMap<String, Object>params //파라미터를 하나만 받기때문에 VO에담거나 Map에담아서 보내줘야좋겠지요
			
			) throws Exception;
}
