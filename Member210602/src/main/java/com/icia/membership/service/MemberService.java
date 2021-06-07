package com.icia.membership.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dao.MemberDAO;
import com.icia.membership.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO mdao;
	private ModelAndView mav;

	@Autowired
	private HttpSession session;

	// 회원 가입
	public ModelAndView memberJoin(MemberDTO member) throws IllegalStateException, IOException {
		System.out.println("service + memberJoin");
		mav = new ModelAndView();

		MultipartFile mfile = member.getMfile();
		String mfilename = mfile.getOriginalFilename();
		mfilename = System.currentTimeMillis() + "-" + mfilename;

		String savePath = "D:\\Source_LEE\\string\\Membership_test_210602\\src\\main\\webapp\\resources\\upload\\"
				+ mfilename;
		if (!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		member.setMfilename(mfilename);
		mdao.memberJoin(member);
		mav.setViewName("redirect:/memberloginpage");
		return mav;

	}

	// 아이디 중복확인
	public String idCheck(String mid) {
		String checkResult = mdao.idCheck(mid);

		String result = "";
		if (checkResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		System.out.println("service + idCheck" + result);
		return result;
	}

	// 로그인
	public ModelAndView memberLogin(MemberDTO member) {
		System.out.println("service + login");
		mav = new ModelAndView();
		String loginId = mdao.memberLogin(member);

		if (loginId != null) {
			if (loginId.equals("admin")) {
				session.setAttribute("loginMember", loginId);
				mav.setViewName("redirect:/membermanagerpage");
			} else {
				session.setAttribute("loginMember", loginId);
				mav.addObject("member", member);
				mav.setViewName("board_home");
			}
		} else {
			mav.setViewName("memberlogin");
		}
		return mav;
	}

	// 마이 페이지 이동
	public ModelAndView memberMaPage(String mid) {
		System.out.println("service + membermanager");
		mav = new ModelAndView();
		MemberDTO member = mdao.memberMyPage(mid);
		mav.addObject("member", member);
		mav.setViewName("membermypage");
		return mav;
	}

	// 매니저 페이지 회원 목록
	public ModelAndView memberManager() {
		System.out.println("service + membermanager");
		mav = new ModelAndView();
		List<MemberDTO> memberList = mdao.memberManager();
		mav.addObject("memberList", memberList);
		mav.setViewName("membermanager");
		return mav;
	}

	// 회원 삭제
	public ModelAndView memberDelete(String mid) {
		System.out.println("service + memberdelete");
		mav = new ModelAndView();
		mdao.memberDelete(mid);
		mav.setViewName("redirect:/membermanagerpage");
		return mav;
	}

	// 매니저 회원 조회
	public ModelAndView memberView(String mid) {
		System.out.println("service + memberview");
		mav = new ModelAndView();
		MemberDTO member = mdao.memberView(mid);
		mav.addObject("member", member);
		mav.setViewName("memberview");
		return mav;
	}

	// 매니저 회원 조회 ajax
	public MemberDTO memberViewAjax(String mid) {
		System.out.println("service + memberViewAjax");
		MemberDTO member = mdao.memberViewAjax(mid);
		return member;
	}

	// 회원 정보 수정 요청
	public ModelAndView memberUpdate() {
		System.out.println("service + memberupdate");
		mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginMember");
		MemberDTO memberUpdate = mdao.memberUpdate(loginId);
		mav.addObject("member", memberUpdate);
		mav.setViewName("memberupdate");

		return mav;
	}

	//회원 정보 수정 처리
	public ModelAndView updateProcess(MemberDTO member) {
		System.out.println("service + updateprocess");
		mav = new ModelAndView();
		int updateResult =  mdao.updateProcess(member);
		if (updateResult > 0) {
			mav.setViewName("membermypage");
		} else {
			mav.setViewName("memberview");
		}
		return mav;
	}

}
