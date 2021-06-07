package com.icia.membership.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int bnumber;
	private String btitle;
	private String bwriter;
	private String bcontents;
	private MultipartFile bfile;
	private String bfilename;
}
