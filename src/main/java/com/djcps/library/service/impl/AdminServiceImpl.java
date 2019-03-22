package com.djcps.library.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.djcps.library.common.BarCodeUtil;
import com.djcps.library.common.RandomUtil;
import com.djcps.library.common.FileUpLoadUtil;
import com.djcps.library.mapper.AdminMapper;
import com.djcps.library.mapper.BookMapper;
import com.djcps.library.mapper.BorrowingBooksMapper;
import com.djcps.library.mapper.UserMapper;
import com.djcps.library.model.Admin;
import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;
import com.djcps.library.model.User;
import com.djcps.library.model.vo.BookVo;
import com.djcps.library.model.vo.BorrowingBooksVo;
import com.djcps.library.model.vo.PageVo;
import com.djcps.library.service.AdminService;

/**
 * @author djsxs
 *
 */
@Service("adminservice")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper adminMapper;
	@Resource
	private BookMapper bookMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private BorrowingBooksMapper borrowingBooksMapper;

	/**
	 * 确认管理员登录名是否重复
	 */
	@Override
	public boolean adminIsExist(String phone) {
		Admin admin = adminMapper.adminIsExist(phone);
		boolean flag = false;
		if (admin != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 管理员登录
	 */
	@Override
	public Admin adminLogin(String phone, String password, HttpServletRequest request) {
		Admin admin = adminMapper.adminLogin(phone, password);
		if (admin == null) {
			return null;
		} else {
			request.getSession().setAttribute("admin", admin);
			return admin;
		}
	}

	/**
	 * 根据书籍ID删除书籍
	 */
	@Override
	public int delBookByid(Integer bookId) {
		int row = bookMapper.delBookByid(bookId);
		return row;
	}

	/**
	 * 
	 * 书籍信息添加
	 */
	@Override
	public int addBookMsg(MultipartFile file, HttpServletRequest request) {
		String filePath = "F:\\eclipse-workspace\\newworkspace\\dj_library\\src\\main\\resources\\static\\upload\\barCode\\";
		String bookName = request.getParameter("bookName");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		String datecount = request.getParameter("datecount");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPublish = request.getParameter("bookPublish");
		String bookCategory = request.getParameter("bookCategory");
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		book.setBookAuthor(bookAuthor);
		book.setBookPublish(bookPublish);
		book.setBorrowCount(0);
		book.setIsborrowedout(0);
		book.setBookCategory(Integer.valueOf(bookCategory));
		SimpleDateFormat formata = new SimpleDateFormat("yy-MM-dd");
		Date bookDate = new Date();
		try {
			bookDate = formata.parse(formata.format(bookDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		book.setBookDate(bookDate);
		File dest = FileUpLoadUtil.fileUpLoad(file);
		book.setBookImage(dest.getName());
		String barCode = "DJCPS" + RandomUtil.generateString(11);
		BarCodeUtil.generateFile(barCode, filePath + barCode + ".png");
		book.setBarCode(barCode);
		return bookMapper.addBook(book);
	}

	/**
	 * 获取书籍信息
	 */
	@Override
	public Book getBookMsg(Integer bookId) {
		Book book = bookMapper.getBookMsgByid(bookId);
		String imgPath = "upload//" + book.getBookImage();
		book.setBookImage(imgPath);
		return book;
	}

	/**
	 * 更新书籍信息
	 */
	@Override
	public int updateBookMsg(MultipartFile file, HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("bookName");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		String datecount = request.getParameter("datecount");
		String bookAuthor = request.getParameter("bookAuthor");
		String bookPublish = request.getParameter("bookPulish");
		String bookCategory = request.getParameter("bookCategory");
		Book book = new Book();
		book.setBookId(Integer.valueOf(bookId));
		book.setBookName(bookName);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		book.setBookAuthor(bookAuthor);
		book.setBookPublish(bookPublish);
		book.setBookCategory(Integer.valueOf(bookCategory));
		File dest = FileUpLoadUtil.fileUpLoad(file);
		book.setBookImage(dest.getName());
		return bookMapper.updateBook(book);
	}

	@Override
	public PageVo selectAllUser(int pageNum) {
		int userTotalCounts = userMapper.getUserTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (userTotalCounts > 0) {
			totalPage = (userTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<User> list = userMapper.selectAllByCondition(pageIndex, pageSize);
		pageVo.setUserList(list);
		return pageVo;
	}

	@Override
	public int isAllowBorrow(Integer id, Integer power) {
		return userMapper.isAllowBorrowByid(id, power);
	}

	@Override
	public Admin findAdminByPhone(String phone) {
		return adminMapper.findAdminByPhone(phone);
	}

	@Override
	public Book findBookByBarCode(String barCode) {
		return bookMapper.findBookByBarCode(barCode);
	}

	@Override
	public PageVo autoSortScore(int pageNum) {
		int userTotalCounts = userMapper.getUserTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (userTotalCounts > 0) {
			totalPage = (userTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<User> list = userMapper.autoSortScore(pageIndex, pageSize);
		pageVo.setUserList(list);
		return pageVo;
	}

	@Override
	public PageVo getBorrowBookMsg(Integer pageNum) {
		int borrowBookTotal = borrowingBooksMapper.getBorrowingTotalCount();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (borrowBookTotal > 0) {
			totalPage = (borrowBookTotal - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<BorrowingBooks> borrowingBookList = borrowingBooksMapper.selectAllByCondition(pageIndex, pageSize);
		List<BorrowingBooksVo> borrowingBooksVoList = new ArrayList<BorrowingBooksVo>();
		for (BorrowingBooks borrowingBooks : borrowingBookList) {
			BorrowingBooksVo borrowingBooksVo = new BorrowingBooksVo();
			borrowingBooksVo.setBorrowBookId(borrowingBooks.getId());
			borrowingBooksVo.setIsReturn(borrowingBooks.getIsreturn());
			Date beginDate = borrowingBooks.getDate();
			Date toDay = new Date();
			long borrowedDays = (toDay.getTime() - beginDate.getTime()) / (24 * 3600 * 1000);
			borrowingBooksVo.setBorrowedDays(borrowedDays);
			Book book = bookMapper.getBookMsgByid(borrowingBooks.getBookId());
			borrowingBooksVo.setBookId(book.getBookId());
			borrowingBooksVo.setDateCount(book.getDateCount());
			borrowingBooksVo.setBookName(book.getBookName());
			borrowingBooksVo.setBookPrice(book.getBookPrice());
			borrowingBooksVoList.add(borrowingBooksVo);
		}
		pageVo.setBorrowingBooksVoList(borrowingBooksVoList);
		return pageVo;
	}

	@Override
	public PageVo selectAllBook(int pageNum) {
		int bookTotalCounts = bookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<Book> bookList = bookMapper.selectAllByCondition(pageIndex, pageSize);
		List<BookVo> bookVoList=new ArrayList<BookVo>();
		for(Book book:bookList) {
			BookVo bookVo=new BookVo();
			//BorrowingBooks borrowingBooks = borrowingBooksMapper.getBorrowRecordByBookId(book.getBookId());
			bookVo.setBookId(book.getBookId());
			bookVo.setBookName(book.getBookName());
			/*bookVo.setBeginDate(borrowingBooks.getDate());
			Date beginDate = borrowingBooks.getDate();
			Date toDay = new Date();
			long borrowedDays = (toDay.getTime() - beginDate.getTime()) / (24 * 3600 * 1000);
			bookVo.setBorrowedDays(borrowedDays);
			Date lastDate = borrowingBooks.getLastdate();
			Date toDay2 = new Date();
			long remainDayCount = (lastDate.getTime() - toDay2.getTime()) / (24 * 3600 * 1000);
			bookVo.setRemainDayCount(remainDayCount);*/
			bookVoList.add(bookVo);
		}
		pageVo.setBookVoList(bookVoList);
		return pageVo;
	}

}
