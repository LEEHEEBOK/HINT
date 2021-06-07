package com.icia.membership.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membership.dto.BoardDTO;
import com.icia.membership.dto.PageDTO;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	// 글 쓰기
	public void boardWrite(BoardDTO board) {
		System.out.println("dao + boardwrite");
		sql.insert("bm.boardwrite", board);
	}

	// 페이징 처리 목록
	// 게시글 갯수 가지고 오기
	public int listCount() {
		System.out.println("dao + listCount");
		return sql.selectOne("bm.listcount");
	}
	
	//boardlist 가져오기
	public List<BoardDTO> boardPaging(PageDTO paging) {
		System.out.println("dao + boardPaging");
		return sql.selectList("bm.boardpaging",paging);
	}

	// 글 조회
	public BoardDTO boardView(int bnumber) {
		System.out.println("dao + boardView");
		return sql.selectOne("bm.boardview",bnumber);
	}

	// 글 삭제
	public void boardDelete(int bnumber) {
		System.out.println("dao + boardDelete");
		sql.delete("bm.boarddelete",bnumber);
	}
	
	// 글 수정 요청
	public BoardDTO boardUpdate(int bnumber) {
		System.out.println("dao + boardUpdate");
		return sql.selectOne("bm.boardupdate",bnumber);
	}

	// 글 수정 처리
	public int updateProcess(BoardDTO board) {
		System.out.println("dao + boardprocess");
		return sql.update("bm.updateProcess",board);
	}

	// 검색
	public List<BoardDTO> boardSearch(Map<String, String> searchMap) {
		System.out.println("dao + boardSearch");
		return sql.selectList("bm.boardsearch",searchMap);
	}

	// 내가 작성한 글 보기
	public List<BoardDTO> boardWriteList(String bwriter) {
		System.out.println("controller + boardWriteList");
		return sql.selectList("bm.boardwritelist",bwriter);
	}
	
}
