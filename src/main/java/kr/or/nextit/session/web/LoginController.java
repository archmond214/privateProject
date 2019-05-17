package kr.or.nextit.session.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.com.utils.CookieBox;
import kr.or.nextit.session.service.LoginInfoVo;
import kr.or.nextit.session.service.SessionService;

@Controller
public class LoginController {

	private final Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="propertyService")
	private Properties propertiesService;
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping(value="/session/loginFront.login")
	public String actionLoginFront(
			ModelMap modelMap,
			HttpServletRequest request
			) throws Exception{
		CookieBox cookieBox  = new CookieBox(request);
		
		log.debug("idSave : {}",cookieBox.exists("idSave"));
		//쿠키 값이 등록(true) / 미등록 (false) 반환
		if(cookieBox.exists("idSave")) {
			String idSave = cookieBox.getValue("idSave");
			modelMap.addAttribute("idSave",idSave);
		}
		
		modelMap.addAttribute("cUserId",cookieBox.getValue("cUsrId"));
		
		return "session/LoginFront";
	}
	
	@RequestMapping(value="/session/actionLoginProc.login")
	public String actionLoginProc(
		Model model,
		@RequestParam HashMap<String, Object>params,
		HttpSession session,
		HttpServletRequest request,
		HttpServletResponse response
			) throws Exception {
		log.debug("로그인 시도시 params : {}",params);
		
		//idSave : checked = "checked"
		//request 객체에서 cookies 쿠키 배열을 가지고 와서 cookieBox 객체 생성... 
		CookieBox cookieBox = new CookieBox(request.getCookies());
		
		log.debug("remember : {}",params.containsKey("remember"));
		
		if(params.containsKey("remember")) {
			//쿠키 생성								쿠키명    쿠키조건		      경로 유지시간
			Cookie idSave = CookieBox.createCookie("idSave", "checked=\"checked\"","/",5000000);
			//쿠키 등록
			response.addCookie(idSave);
			//쿠키 remember 값이 넘어오면 아이디를 기억 해야 하닌까 쿠키에 추가
			//(String) params.get("userId") == null? "": (String) params.get("userId")
			//사용자가 등록된 ID값(input name)의value값 == 널이면? 빈값을 반환: params.get("userId")넘어온 값을 쿠키에 저장
			Cookie cUsrId = CookieBox.createCookie("cUsrId",params.get("userId") == null ? "": (String)params.get("userId"),"/",5000000);
			response.addCookie(cUsrId);
			log.debug("쿠키 생성 완료 {}",idSave);
		}else {
			//삭제할 쿠키 생성
			Cookie idSave = CookieBox.createCookie("idSave", "","/",0);
			//삭제할 쿠키 등록
			response.addCookie(idSave);
			//삭제할 쿠키 생성
			Cookie cUsrId = CookieBox.createCookie("cUsrId","","/",0);
			//삭제할 쿠키 등록
			response.addCookie(cUsrId);
		}
		
		
		
		
		
		
		
		//ID와 PWD값을 DB에서 where 로 조회 
		//조회 건수가 1개 있음 => 로그인 성공
		//null => 로그인 실패.,.
		LoginInfoVo loginInfoResult= sessionService.selectMemberInfo(params);
		
		log.debug("loginInfo : {} ",loginInfoResult );
		log.debug("loginInfo : {} ",loginInfoResult );
		log.debug("loginInfo : {} ",loginInfoResult );
		
		if(loginInfoResult!=null) {
			//로그인 처리 
			session.setAttribute("loginInfo", loginInfoResult);
			//public ModelAndView actionLoginProc() 사용
			//RedirectView redirectView = new RedirectView("/");
			
			//public String actionLoginProc() 사용시
			return "redirect:/";
		}else {
			//로그인 실패  처리
			//로그인 실패를 하면 Session 초기화 
			session.invalidate();
			model.addAttribute("message"," 아이디 또는 패스워드가 틀렸습니다.");
			
			//return "session/LoginFront";
			return "redirect:/session/loginFront.do?message=" + URLEncoder.encode("아이디 또는 패스워드가 틀렸습니다.","UTF-8");
			
		}
		
	}
	
	
	
}
