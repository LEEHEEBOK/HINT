package com.icia.membership.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dto.MemberDTO;
import com.icia.membership.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	// 회원 가입 페이지 이동212312
	@RequestMapping(value = "/memberjoinpage")
	public String memberJoinPage() {
		System.out.println("controller + memberjoinpage");
		return "memberjoin";
	}
	
	// 회원 가입
	@RequestMapping(value = "/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
		System.out.println("controller + memberJoin");
		mav = ms.memberJoin(member);
		return mav;
	}
	
	// 아이디 중복확인
	@RequestMapping(value = "/idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid")String mid) {
		System.out.println("controller + idcheck" + mid);
		String result = ms.idCheck(mid);
		return result;
	}
	
	// 로그인 페이지 출력
	@RequestMapping(value = "/memberloginpage")
	public String memberLohinPage() {
		System.out.println("controller + memberloginpage");
		return "memberlogin";
	}
	
	// 로그인 
	@RequestMapping (value = "/login")
	public ModelAndView memberLogin(@ModelAttribute MemberDTO member) {
		System.out.println("controller + login" + member.toString());
		mav = ms.memberLogin(member);
		return mav;
	}
	
	// 마이 페이지 이동
	@RequestMapping (value = "/mypage")
	public ModelAndView memberMyPage(@RequestParam("mid") String mid) {
		System.out.println("controller + memberMaPage");
		mav = ms.memberMaPage(mid);
		return mav;
	}
	
	// 매니저 페이지 회원 목록
	@RequestMapping(value = "/membermanagerpage")
	public ModelAndView memberManager() {
		System.out.println("controller + membermanagerpage");
		mav = ms.memberManager();
		return mav;
	}
	
	// 회원 삭제
	@RequestMapping (value = "/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		System.out.println("controller + memberdelete");
		mav = ms.memberDelete(mid);
		return mav;
	}
	
	// 매니저 회원 조회
	@RequestMapping (value = "/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		System.out.println("controller + memberview");
		mav = ms.memberView(mid);
		return mav;
	}
	
	// 매니저 회원 조회 ajax
	@RequestMapping (value = "/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("controller + memberviewajax");
		MemberDTO member = ms.memberViewAjax(mid);
		System.err.println(member);
		return member;
	}
	
	// 로그아웃
	@RequestMapping (value = "/logout")
	public String logout() {
		System.out.println("controller + logout");
		session.invalidate();
		return "home";
	}
	
	// 회원 정보 수정 요청
	@RequestMapping(value = "/memberupdate")
	public ModelAndView memberUpdate() {
		System.out.println("controller + memberupdate");
		mav = ms.memberUpdate();
		return mav;
	}
	
	//회원 정보 수정 처리
	@RequestMapping(value = "/updateprocess")
	public ModelAndView updateProcess(@ModelAttribute MemberDTO member) {
		System.out.println("controller + updateprocess");
		mav =ms.updateProcess(member);
		return mav;
	}
	
}
