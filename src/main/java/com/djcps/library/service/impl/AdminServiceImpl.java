package com.djcps.library.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.djcps.library.mapper.AdminMapper;
import com.djcps.library.mapper.BookMapper;
import com.djcps.library.model.Admin;
import com.djcps.library.model.Book;
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

	/**
	 * 确认管理员登录名是否重复
	 */
	@Override
	public boolean adminIsExist(String phone) {
		// TODO Auto-generated method stub
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
	public Admin adminLogin(String phone, String password) {
		Admin admin = adminMapper.adminLogin(phone, password);
		return admin;
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
		String filePath = "F:\\eclipse-workspace\\newworkspace\\dj-library-1\\src\\main\\resources\\static\\upload\\";
		String bookname = request.getParameter("bookName");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		String datecount = request.getParameter("datecount");
		Book book = new Book();

		book.setBookName(bookname);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名,比如图片的jpeg,png
		String suffixName = fileName.substring(fileName.lastIndexOf("."));

		// 文件上传后的路径
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		book.setBookImage(dest.getName());
		try {
			file.transferTo(dest);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookmapper.addBook(book);
	}

	/**
	 * 获取书籍信息
	 */
	@Override
	public Book getBookMsg(Integer bookId) {
		return bookmapper.getBookMsgByid(bookId);
	}

	@Override
	public int updateBookMsg(MultipartFile file, HttpServletRequest request) {
		String filePath = "F:\\eclipse-workspace\\newworkspace\\dj-library-1\\src\\main\\resources\\static\\upload\\";
		String bookId = request.getParameter("bookId");
		String bookname = request.getParameter("bookName");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		String datecount = request.getParameter("datecount");
		Book book = new Book();
		book.setBookId(Integer.valueOf(bookId));
		book.setBookName(bookname);
		book.setBookIntroduction(desc);
		book.setDateCount(Integer.valueOf(datecount));
		book.setBookPrice(Double.valueOf(price));
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名,比如图片的jpeg,png
		String suffixName = fileName.substring(fileName.lastIndexOf("."));

		// 文件上传后的路径
		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);
		book.setBookImage(dest.getName());
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookmapper.updateBook(book);
	}

}
