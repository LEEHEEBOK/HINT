package com.icia.membership.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private String mphon;
	private String memail;
	private MultipartFile mfile;
	private String mfilename;
}
