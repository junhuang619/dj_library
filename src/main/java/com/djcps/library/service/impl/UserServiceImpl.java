package com.djcps.library.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.djcps.library.model.vo.BookVo;
import com.djcps.library.model.vo.BorrowingBooksVo;
import com.djcps.library.model.vo.PageVo;
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
		user.setScore(0.0);
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
	public int updateUser(String userName, String password, String phone) {
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
		User user = (User) request.getSession().getAttribute("user");
		// 测试代码
		Integer isAllowBorrow = user.getIsAllowBorrow();
		if (isAllowBorrow < 1) {
			return 0;
		}
		BorrowingBooks borrowingBooks = new BorrowingBooks();
		// 存储bookid
		borrowingBooks.setBookId(Integer.valueOf(bookId));
		// 存储用户id
		borrowingBooks.setUserId(user.getUserId());
		// 改行为测试代码行
		/*borrowingBooks.setUserId(1);*/
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
		int score = 1;
		int row2 = userMapper.addScoreByUid(borrowingBooks.getUserId(), score);
		if (row2 < 1) {
			return 0;
		}
		int isBorrowed = 1;
		return bookMapper.updateBookByid(borrowingBooks.getBookId(), isBorrowed);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int returnBook(Integer brrowingbookid, int i) {
		// TODO Auto-generated method stub
		BorrowingBooks borrowingBooks = borrowingBooksMapper.getBorrowMsgByid(brrowingbookid);
		int isBorrowed = 0;
		int row = borrowingBooksMapper.retunBook(i, brrowingbookid);
		//int row1 = borrowingBooksMapper.delBorrowingRecordByisreturn();
		if (row < 1 ) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		int score = 3;
		userMapper.addScoreByUid(borrowingBooks.getUserId(), score);
		return bookMapper.updateBookByid(borrowingBooks.getBookId(), isBorrowed);
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
		int score = 2;
		userMapper.addScoreByUid(borrowingBooks.getUserId(), score);
		return bookMapper.updateBookborrowCountByid(borrowingBooks.getBookId());
	}

	@Override
	public List<Book> findBookBybookName(String bookName) {
		return bookMapper.findBookBybookName(bookName);
	}

	@Override
	public BookVo getBorrowedMsg(Integer bookId) {
		// TODO Auto-generated method stub
		BorrowingBooks borrowingBooks = borrowingBooksMapper.getBorrowMsgBybookId(Integer.valueOf(bookId));
		Book book = bookMapper.getBookMsgByid(Integer.valueOf(bookId));
		// 创建一个工具类封装数据
		BookVo bookVo = new BookVo();
		// 封装书籍的id
		bookVo.setBookId(Integer.valueOf(bookId));
		// 封装书籍的名称
		bookVo.setBookName(book.getBookName());
		if (book.getIsborrowedout() == 1) {
			// 封装书籍的借书起始日期
			bookVo.setBeginDate(borrowingBooks.getDate());
			Date today = new Date();
			Date enddate = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
			try {
				today = format.parse(format.format(today));
				enddate = format.parse(format.format(borrowingBooks.getLastdate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long  remainDayCount = (enddate.getTime() - today.getTime()) / (24 * 60 * 60 * 1000);
			bookVo.setRemainDayCount(remainDayCount);
			bookVo.setUser(userMapper.findUserById(borrowingBooks.getUserId()));
		} else {
			// 封装书籍的借书起始日期
			bookVo.setBeginDate(null);
			bookVo.setRemainDayCount(0l);
			bookVo.setUser(null);
		}
		return bookVo;
	}

	@Override
	public PageVo getBorrowBookMsg(Integer pageNum, Integer userId) {
		Integer borrowBookTotals = borrowingBooksMapper.getBorrowingBookTotalCountsByUserId(userId);
		PageVo pageVo =new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize ;
			pageSize = pageNum * 10;
		}
		if (borrowBookTotals > 0) {
			totalPage = (borrowBookTotals - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<BorrowingBooks> borrowingBooksList =borrowingBooksMapper.getBorrowingBookMsgByUserId(userId, pageIndex, pageSize);
		List< BorrowingBooksVo> borrowingBooksVoList = new ArrayList<BorrowingBooksVo>();
		for(BorrowingBooks borrowingBooks:borrowingBooksList) {
			BorrowingBooksVo borrowingBooksVo = new BorrowingBooksVo();
			borrowingBooksVo.setBorrowBookId(borrowingBooks.getId());
			borrowingBooksVo.setIsReturn(borrowingBooks.getIsreturn());
			Date beginDate=borrowingBooks.getDate();
			Date toDay=new Date();
			long borrowedDays=(toDay.getTime()-beginDate.getTime())/(24*3600*1000);
			borrowingBooksVo.setBorrowedDays(borrowedDays);
			Book book=bookMapper.getBookMsgByid(borrowingBooks.getBookId());
			borrowingBooksVo.setDateCount(book.getDateCount());
			borrowingBooksVo.setBookName(book.getBookName());
			borrowingBooksVo.setBookPrice(book.getBookPrice());
			borrowingBooksVoList.add(borrowingBooksVo);
		}
		pageVo.setBorrowingBooksVoList(borrowingBooksVoList);
		return pageVo;
	}

}
