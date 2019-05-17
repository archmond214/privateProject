package kr.or.nextit.session.service;

import java.util.HashMap;

public interface SessionService {
	public LoginInfoVo selectMemberInfo(
			HashMap<String, Object> params
			
			) throws Exception;
}
