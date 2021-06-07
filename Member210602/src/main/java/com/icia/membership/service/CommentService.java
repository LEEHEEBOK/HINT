package com.icia.membership.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SchemaViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dao.CommentDAO;
import com.icia.membership.dto.CommentDTO;

@Service
public class CommentService {

	@Autowired
	private CommentDAO cdao;
	private ModelAndView mav;

	//댓글
	public List<CommentDTO> commentWriter(CommentDTO comment) {
		System.out.println("service + commentwrite");
		int writeResult = cdao.commentWrite(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		commentList = cdao.commentList(comment.getCbnumber());
		return commentList;
	}

	// 댓글 삭제
	public ModelAndView commentDelete(int cnumber) {
		System.out.println("service + commentDelete");
		mav = new ModelAndView();
		cdao.commentDelete(cnumber);
		return mav;
	}
}
