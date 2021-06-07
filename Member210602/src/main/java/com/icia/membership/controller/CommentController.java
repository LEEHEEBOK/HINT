package com.icia.membership.controller;

import java.util.List;

import javax.naming.directory.SchemaViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membership.dto.CommentDTO;
import com.icia.membership.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService cs;
	private ModelAndView mav;
	
	// 댓글
	@RequestMapping(value = "/comment/commentwrite")
	public @ResponseBody List<CommentDTO> commentWriter(@ModelAttribute CommentDTO comment) {
		System.out.println(" comment commeentWriter");
		List<CommentDTO> commentList=cs.commentWriter(comment);
		return commentList;
	}
	
	// 댓글 삭제
	@RequestMapping(value = "/commentdelete")
	public ModelAndView commentDelete( @RequestParam(value="cnumber" , defaultValue="1")int cnumber) {
		System.out.println("controller + commentDelete");
		mav = cs.commentDelete(cnumber);
		return mav;
	}
}
