package com.djcps.library.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.djcps.library.mapper.UserMapper;
import com.djcps.library.model.Admin;
import com.djcps.library.model.Book;
import com.djcps.library.model.User;
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
	private BookMapper bookmapper;
	@Resource
	private UserMapper userMapper;

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
	public Admin adminLogin(String phone, String password,HttpServletRequest request) {
		Admin admin = adminMapper.adminLogin(phone, password);
		if (admin == null) {
			return null;
		}else{
			request.getSession().setAttribute("admin", admin);
			return admin;
		}
	}

	/**
	 * 根据书籍ID删除书籍
	 */
	@Override
	public int delBookByid(Integer bookId) {
		int row = bookmapper.delBookByid(bookId);
		return row;
	}

	/**
	 * 
	 * 书籍信息添加
	 */
	@Override
	public int addBookMsg(MultipartFile file, HttpServletRequest request) {
		String filePath = "F:\\eclipse-workspace\\newworkspace\\dj_library\\src\\main\\resources\\static\\upload\\";
		String bookName = request.getParameter("bookName");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		String datecount = request.getParameter("datecount");
		String bookAuthor=request.getParameter("bookAuthor");
		String bookPublish=request.getParameter("bookPublish");
		String bookCategory=request.getParameter("bookCategory");
		String bookNumber=request.getParameter("bookNumber");
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		book.setBookAuthor(bookAuthor);
		book.setBookPublish(bookPublish);
		book.setBorrowCount(0);
		book.setIsborrowedout(0);
		book.setBookNumber(Integer.valueOf(bookNumber));
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
		File dest=FileUpLoadUtil.fileUpLoad(file);
		book.setBookImage(dest.getName());
		String barCode="DJCPS"+RandomUtil.generateString(11);
		BarCodeUtil.generateFile(barCode, filePath+barCode+".png");
		book.setBarCode(barCode);
		return bookmapper.addBook(book);
	}
	/**
	 * 获取书籍信息
	 */
	@Override
	public Book getBookMsg(Integer bookId) {
		return bookmapper.getBookMsgByid(bookId);
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
		String bookAuthor=request.getParameter("bookAuthor");
		String bookPublish=request.getParameter("bookPulish");
		String bookCategory=request.getParameter("bookCategory");
		String bookNumber=request.getParameter("bookNumber");
		Book book = new Book();
		book.setBookId(Integer.valueOf(bookId));
		book.setBookName(bookName);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		book.setBookAuthor(bookAuthor);
		book.setBookPublish(bookPublish);
		book.setBookNumber(Integer.valueOf(bookNumber));
		book.setBookCategory(Integer.valueOf(bookCategory));
		File dest=FileUpLoadUtil.fileUpLoad(file);
		book.setBookImage(dest.getName());
		return bookmapper.updateBook(book);
	}

	@Override
	public PageVo selectAllUser(int pageNum) {
		int userTotalCounts = userMapper.getUserTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 8;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 8;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 8;
		}
		if (userTotalCounts > 0) {
			totalPage = (userTotalCounts - 1) / 8 + 1;
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
		return bookmapper.findBookByBarCode(barCode);
	}


}
