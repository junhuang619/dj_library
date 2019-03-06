package com.djcps.library.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.djcps.library.model.Book;

public interface AdminService {

	boolean adminIsExist(String adminName);

	boolean adminLogin(String adminName, String password);

	int delBookByid(Integer bookId);

	int addBookMsg(MultipartFile file, HttpServletRequest request);
	
	Book getBookMsg(Integer bookId);

	int updateBookMsg1(MultipartFile file, HttpServletRequest request);

	int updateBookMsg2(HttpServletRequest request);

}
