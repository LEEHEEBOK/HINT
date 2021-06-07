package com.icia.membership.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dao.BoardDAO;
import com.icia.membership.dao.CommentDAO;
import com.icia.membership.dto.BoardDTO;
import com.icia.membership.dto.CommentDTO;
import com.icia.membership.dto.PageDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bdao;
	private ModelAndView mav;

	@Autowired
	private CommentDAO cdao;

	// 글 쓰기
	public ModelAndView boardWrite(BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("service + boardWrite");
		mav = new ModelAndView();
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "-" + bfilename;
		System.out.println("boardWriteFile 메소드 " + bfilename);
		String savePath = "D:\\Source_LEE\\string\\Membership_test_210602\\src\\main\\webapp\\resources\\upload\\"
				+ bfilename;
		if (!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		bdao.boardWrite(board);
		mav.setViewName("board_home");
		return mav;
	}

	// 페이징 처리에 활용할 상수
	private static final int PAGE_LIMIT = 5; // 한페이지에 보여질 글 개수
	private static final int BLOCK_LIMIT = 5; // 한화면에 보여질 페이지 개수

	public ModelAndView boardPaging(int page) {
		mav = new ModelAndView();
		// 게시글 수
		int listCount = bdao.listCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);// <= 메소드를 다른걸 씀.
		System.out.println("paging값" + paging.toString());
		// boardlist 가져오기
		List<BoardDTO> boardList = bdao.boardPaging(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		// 10,20,30,40~~~ (BLOCK_IMIT=10 일때)
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardpage");
		System.out.println("paging" + boardList.size());
		return mav;
	}

	// 글 조회
	public ModelAndView boardView(int bnumber, int page) {
		System.out.println("service + boardView");
		mav = new ModelAndView();
		BoardDTO board = bdao.boardView(bnumber);
		mav.addObject("page", page);
		List<CommentDTO> commentList = cdao.commentList(bnumber);
		mav.addObject("commentList", commentList);
		mav.addObject("board", board);
		mav.setViewName("boardview");
		return mav;
	}

	// 글 삭제
	public ModelAndView boardDelete(int bnumber) {
		System.out.println("service + boardDelete");
		mav = new ModelAndView();
		bdao.boardDelete(bnumber);
		mav.setViewName("redirect:/paging");
		return mav;
	}

	// 글 수정 요청
	public ModelAndView boardUpdate(int bnumber) {
		System.out.println("servite + update");
		mav = new ModelAndView();
		BoardDTO board = bdao.boardUpdate(bnumber);
		mav.addObject("board", board);
		mav.setViewName("boardupdate");
		return mav;
	}

	
	// 글 수정 처리
	public ModelAndView updateProcess(BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("service + updateProcess");
		MultipartFile bfile = board.getBfile();
		String bfilename = bfile.getOriginalFilename();
		bfilename = System.currentTimeMillis() + "-" + bfilename;

		String savePath = "D:\\Source_LEE\\string\\Membership_test_210602\\src\\main\\webapp\\resources\\upload\\"
				+ bfilename;
		if (!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		}
		board.setBfilename(bfilename);
		int boardupdate = bdao.updateProcess(board);
		if (boardupdate > 0) {
			mav.setViewName("redirect:/boardview");
		} else {
			mav.setViewName("boardupdate");
		}
		return mav;
	}

	// 검색
	public ModelAndView boardSearch(String searchType, String keyword) {
		System.out.println("service + boardSearch");
		mav = new ModelAndView();
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		List<BoardDTO> board = bdao.boardSearch(searchMap);
		mav.addObject("board", board);
		mav.setViewName("boardsearch");
		return mav;

	}

	// 내가 작성한 글 보기
	public ModelAndView boardWriteList(String bwriter) {
		System.out.println("controller + boardWriteList");
		mav = new ModelAndView();
		List<BoardDTO> boardList = bdao.boardWriteList(bwriter);
		mav.addObject("boardList", boardList);
		mav.setViewName("memberwritelist");
		return mav;
	}

}
