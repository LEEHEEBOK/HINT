package com.icia.membership.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membership.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 회원 가입
	public void memberJoin(MemberDTO member) {
		System.out.println("dao + memberJoin");
		sql.insert("mm.memberjoin", member);
		
	}

	// 아이디 중복확인
	public String idCheck(String mid) {
		System.out.println("dao + idcheck");
		return sql.selectOne("mm.idcheck",mid);
	}

	// 로그인
	public String memberLogin(MemberDTO member) {
		System.out.println("dao + login");
		return sql.selectOne("mm.memberlogin",member);
	}

	// 마이 페이지 이동
	public MemberDTO memberMyPage(String mid) {
		System.out.println("dao + membermypage");
		return sql.selectOne("mm.membermypage",mid);
	}
	
	// 매니저 페이지 회원 목록
	public List<MemberDTO> memberManager() {
		System.out.println("dao + membermanager");
		return sql.selectList("mm.memberlist");
	}

	// 회원 삭제
	public void memberDelete(String mid) {
		System.out.println("dao + memberDelete");
		sql.delete("mm.memberdelete",mid);
	}

	// 매니저 회원 조회
	public MemberDTO memberView(String mid) {
		System.out.println("dao + memberview");
		return sql.selectOne("mm.memberview",mid);
	}

	// 매니저 회원 조회 ajax
	public MemberDTO memberViewAjax(String mid) {
		System.out.println("dao + memberViewAjax");
		return sql.selectOne("mm.memberviewajax", mid);
	}

	// 회원 정보 수정 요청
	public MemberDTO memberUpdate(String loginId) {
		System.out.println("dao + memberupdate");
		return sql.selectOne("mm.memberupdate", loginId);
	}

	//회원 정보 수정 처리
	public int updateProcess(MemberDTO member) {
		System.out.println("dao + updateprocess");
		return sql.update("mm.updateprocess",member);
	}


	


}
