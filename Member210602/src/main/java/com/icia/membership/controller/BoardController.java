package com.icia.membership.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dto.BoardDTO;
import com.icia.membership.dto.MemberDTO;
import com.icia.membership.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	private ModelAndView mav;
	
	// 보드 홈으로
	@RequestMapping(value = "/board_home")
	private String boardHome() {
		System.out.println("controller + boardHome");
		return "board_home";
	}
	
	// 글쓰기 페이지로 이동
	@RequestMapping(value = "/boardWritePage")
	private String boardWritePage() {
		System.out.println("controller + boardWritePage");
		return "boardwrite";
	}
	
	// 글 쓰기
	@RequestMapping (value = "/boardwrite")
	private ModelAndView boardWrite(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("controller + boardWrite");
		mav = bs.boardWrite(board);
		return mav;
	}
	
	// 페이징 처리
	@RequestMapping(value = "/paging")
	public ModelAndView boardPage(@RequestParam(value="page", required=false, defaultValue="1")int page) {
		System.out.println("controller + paging");
		mav = bs.boardPaging(page);
		return mav;
	}
	
	// 글조회
	@RequestMapping(value = "/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber,
								  @RequestParam(value="page", required=false, defaultValue="1") int page){
		System.out.println("controller + boardview");
		mav = bs.boardView(bnumber, page );
		return mav;
	}
	
	// 글 삭제
	@RequestMapping(value = "/boarddelete")
	public ModelAndView boardDelete( @RequestParam(value="bnumber" , defaultValue="1")int bnumber) {
		System.out.println("controller + boardDelete");
		mav = bs.boardDelete(bnumber);
		return mav;
	}
	
	// 글 수정 요청
	@RequestMapping(value = "/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		System.out.println("controller + boardupdate");
		mav = bs.boardUpdate(bnumber);
		return mav;
	}
	
	// 글 수정
	@RequestMapping(value = "/boardupdateprocess")
	public ModelAndView updateProcess(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("controller + updateprocess");
		mav =bs.updateProcess(board);
		return mav;
	}
	
	// 검색
	@RequestMapping(value = "/search")
	public ModelAndView boardSearch(@RequestParam(value = "search", required=false) String searchType, 
									@RequestParam("keyword") String keyword){
		System.out.println("controller + boardSearch");
		mav = bs.boardSearch(searchType, keyword);
		return mav;		
	}
	
	// 내가 작성한 글 보기
	@RequestMapping(value = "/memberwritelist")
	public ModelAndView boardWriteList(@RequestParam(value = "mid", required=false)String bwriter) {
		System.out.println("controller + boardWriteList");
		mav = bs.boardWriteList(bwriter);
		return mav;
	}
	
	
	}
