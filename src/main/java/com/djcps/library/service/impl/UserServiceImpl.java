package com.djcps.library.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.DigestUtils;

import com.djcps.library.mapper.BookMapper;
import com.djcps.library.mapper.BorrowingBooksMapper;
import com.djcps.library.mapper.UserMapper;
import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;
import com.djcps.library.model.User;
import com.djcps.library.service.UserService;

/**
 * @author djsxs
 *
 */
@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Resource
	private BookMapper bookMapper;

	@Resource
	private BorrowingBooksMapper borrowingBooksMapper;

	@Override
	public int registerUser(String userName, String password, String phone) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPwd(password);
		user.setUserPhone(phone);
		user.setIsAllowBorrow(1);
		String md5Password = DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes());
		user.setUserPwd(md5Password);
		int row = userMapper.register(user);
		return row;
	}

	@Override
	public User userLogin(String userPhone, String userPwd, HttpServletRequest request) {
		String md5Password = DigestUtils.md5DigestAsHex(userPwd.getBytes());
		User user = userMapper.findUser(userPhone, md5Password);
		if (user == null) {
			return null;
		} else {
			request.getSession().setAttribute("user", user);
			return user;
		}

	}

	@Override
	public int updateuser(String userName, String password, String phone) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPhone(phone);
		user.setUserPwd(password);
		int row = userMapper.updateUser(user);
		return row;
	}

	@Override
	public User findUserByUserPhone(String phone) {
		User users = userMapper.findUserByUserPhone(phone);
		return users;
	}

	@Override
	public User findUserById(Integer id) {
		User users = userMapper.findUserById(id);
		return users;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int userBorrowBook(HttpServletRequest request) {
		// 获取前端传来的数据
		String bookId = request.getParameter("bookId");
		String bookNumber=request.getParameter("bookNumber");
		if(Integer.valueOf(bookNumber)==0) {
			return 0;
		}
		/** User user = (User) request.getSession().getAttribute("user"); */
		// 测试代码
		User user = new User();
		user.setIsAllowBorrow(1);
		Integer isAllowBorrow = user.getIsAllowBorrow();
		if (isAllowBorrow != 1) {
			return 0;
		}
		BorrowingBooks borrowingBooks = new BorrowingBooks();
		// 存储bookid
		borrowingBooks.setBookId(Integer.valueOf(bookId));
		// 存储用户id
		/** books.setUserId(user.getUserId()); */
		// 改行为测试代码行
		borrowingBooks.setUserId(1);
		borrowingBooks.setIsreturn(0);
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		Date fDate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(fDate);
		int datecount = bookMapper.getBookdateCount(Integer.valueOf(bookId));
		rightNow.add(Calendar.DAY_OF_YEAR, datecount);
		Date lDate = rightNow.getTime();
		try {
			fDate = format.parse(format.format(fDate));
			borrowingBooks.setDate(fDate);
			lDate = format.parse(format.format(lDate));
			borrowingBooks.setLastdate(lDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int row = borrowingBooksMapper.borrowBook(borrowingBooks);
		if (1 > row) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		int isBorrowed = 1;
		return bookMapper.borrowingBookByid(borrowingBooks.getBookId(), isBorrowed);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int returnBook(Integer brrowingbookid, int i) {
		// TODO Auto-generated method stub
		BorrowingBooks books = borrowingBooksMapper.getBorrowMsgByid(brrowingbookid);
		int isBorrowed = 0;
		int row = borrowingBooksMapper.retunBook(i, brrowingbookid);
		if (row < 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return bookMapper.returnBookByid(books.getBookId(), isBorrowed);
	}

	@Override
	public int continueBorrowBook(HttpServletRequest request) {
		String borrowBookid = request.getParameter("borrowBookid");
		/** User user = (User) request.getSession().getAttribute("user"); */
		User user = new User();
		user.setIsAllowBorrow(1);
		Integer isAllowBorrow = user.getIsAllowBorrow();
		if (isAllowBorrow != 1) {
			return 0;
		}
		BorrowingBooks borrowingBooks = borrowingBooksMapper.getBorrowMsgByid(Integer.valueOf(borrowBookid));
		Date newdate = borrowingBooks.getLastdate();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(newdate);
		int datecount = bookMapper.getBookdateCount(borrowingBooks.getBookId());
		rightNow.add(Calendar.DAY_OF_YEAR, datecount);
		newdate = rightNow.getTime();
		borrowingBooks.setLastdate(newdate);
		int row = borrowingBooksMapper.updatelastdateByid(borrowingBooks);
		if (row == 0) {
			return 0;
		}
		return bookMapper.updateBookborrowCountByid(borrowingBooks.getBookId());
	}

	@Override
	public List<Book> findBookBybookName(String bookName) {
		return bookMapper.findBookBybookName(bookName);
	}

	@Override
	public List<Book> findBookByTheOnsaleDate() {
		return bookMapper.findBookByTheOnsaleDate();
	}

	@Override
	public List<Book> findBookByOnRecently() {
		SimpleDateFormat formata = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		try {
			date = formata.parse(formata.format(date));
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookMapper.getBookOnRecently(date);
	}

}
