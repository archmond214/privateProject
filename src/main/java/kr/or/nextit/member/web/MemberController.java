package kr.or.nextit.member.web;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.nextit.member.service.MemberSearchVo;
import kr.or.nextit.member.service.MemberService;
import kr.or.nextit.member.service.MemberVo;


@Controller
@RequestMapping(value="/member")
public class MemberController {
	private final Logger log =  LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/memberList.list")
	public String getMemberList(
			Model model,
			@ModelAttribute(name="searchVo") MemberSearchVo searchVo
			) throws Exception{
		int totalCount = memberService.selectMemberTotalCnt(searchVo);
		searchVo.setTotalCount(totalCount);
		searchVo.setScreenSize(3);
		searchVo.setPageBlockSize(4);
		searchVo.pageSetting();
		List<MemberVo> resultList = memberService.selectMemberList(searchVo);
		model.addAttribute("memberList", resultList);


		return "member/memberList";

	}
	


	
	/**
	 * @Controller 에 @RequestMapping(value="/member")컨트롤러에 기본 url 경로 설정되어있음.
	 * 메소드의 	@RequestMapping(value="/memberList.do")경로 앞에  @RequestMapping(value="/member")작동함
	 * ex)/member/memberList.do
	 * /member/memberList.do
	 * /member/memberView.do
	 * /member/memberDelete.do
	 * @param model
	 * @param searchVo
	 * @return
	 * @throws Exception
	 */

	
	
}
