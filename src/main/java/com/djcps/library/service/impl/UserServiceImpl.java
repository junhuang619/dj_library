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
import org.springframework.test.annotation.Rollback;
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
	public int registerUser(User user) {
		String md5Password = DigestUtils.md5DigestAsHex(user.getUserPwd().getBytes());
		user.setUserPwd(md5Password);
		int row = userMapper.register(user);
		return row;
	}

	@Override
	public User userLogin(String userPhone, String userPwd) {
		String md5Password = DigestUtils.md5DigestAsHex(userPwd.getBytes());
		User user = userMapper.findUser(userPhone, md5Password);
		return user;
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
		/**User user = (User) request.getSession().getAttribute("user");*/
		BorrowingBooks books = new BorrowingBooks();
		// 存储bookid
		books.setBookId(Integer.valueOf(bookId));
		// 存储用户id
		/**books.setUserId(user.getUserId());*/
		// 改行为测试代码行
		books.setUserId(1);
		books.setIsreturn(0);
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		Date fDate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(fDate);
		int datecount = bookMapper.getBookdateCount(Integer.valueOf(bookId));
		rightNow.add(Calendar.DAY_OF_YEAR, datecount);
		Date lDate = rightNow.getTime();
		try {
			fDate = format.parse(format.format(fDate));
			books.setDate(fDate);
			lDate = format.parse(format.format(lDate));
			books.setLastdate(lDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int row = borrowingBooksMapper.borrowBook(books);
		if (1 > row) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		int isBorrowed = 1;
		return bookMapper.updateBookByid(books.getBookId(), isBorrowed);
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
		return bookMapper.updateBookByid(books.getBookId(), isBorrowed);
	}
	
	@Override
	public int continueBorrowBook(Integer borrowBookid) {
		BorrowingBooks books = borrowingBooksMapper.getBorrowMsgByid(borrowBookid);
		Date newdate = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(newdate);
		int datecount = bookMapper.getBookdateCount(books.getBookId());
		rightNow.add(Calendar.DAY_OF_YEAR, datecount);
		newdate = rightNow.getTime();
		books.setLastdate(newdate);
		int row = borrowingBooksMapper.updatelastdateByid(books);
		if(row ==0){
			return 0;
		}
		return bookMapper.updateBookborrowCountByid(books.getBookId());
	}

	@Override
	public List<Book> findBookBybookName(String bookName) {
		// TODO Auto-generated method stub
		return bookMapper.findBookBybookName(bookName);
	}

	@Override
	public List<Book> findBookByTheOnsaleDate() {
		// TODO Auto-generated method stub
		return bookMapper.findBookByTheOnsaleDate();
	}

}
