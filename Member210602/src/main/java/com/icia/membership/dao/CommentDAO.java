package com.icia.membership.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membership.dto.CommentDTO;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	// 댓글 등록
	public int commentWrite(CommentDTO comment) {
		System.out.println("dao.commentWrite");
		return sql.insert("cm.commentwrite", comment);
	}
	
	// 댓글 목록보가
	public List<CommentDTO> commentList(int cbnumber) {
		System.out.println("dao.commentList");
		return sql.selectList("cm.commentlist",cbnumber);
	}

	// 댓글 삭제
	public void commentDelete(int cnumber) {
		System.out.println("dao.commentDelete");
		sql.delete("cm.commentdelete", cnumber);
	}

	
}
