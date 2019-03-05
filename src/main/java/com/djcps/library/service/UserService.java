package com.djcps.library.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.djcps.library.model.Book;
import com.djcps.library.model.User;

public interface UserService {
 
	int  registerUser(User user);

	User userLogin(Integer phone, String password);

	int updateuser(String userName, String password, Integer phone);

	User findUserByUserPhone(Integer phone);

	User findUserById(Integer id);

	int UserBorrowBook(HttpServletRequest request);

	int returnBook(Integer brrowingbookid, int isreturn);

	List<Book> findBookBybookName(String bookName);

	List<Book> findBookByTheOnsaleDate();

	int ContinueBorrowBook(Integer borrowBookid);
}
