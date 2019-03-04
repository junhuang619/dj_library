package com.djcps.library.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.library.mapper.BookMapper;
import com.djcps.library.mapper.BorrowingBooksMapper;
import com.djcps.library.mapper.UserMapper;
import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;
import com.djcps.library.model.User;
import com.djcps.library.service.UserService;

@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Resource
	private BookMapper bookMapper;

	@Resource
	private BorrowingBooksMapper borrowingBooksMapper;

	public int registerUser(User user) {
		int row = userMapper.register(user);
		return row;
	}

	@Override
	public User userLogin(Integer phone, String password) {
		User user = userMapper.findUser(phone, password);
		return user;
	}

	@Override
	public int updateuser(String userName, String password, Integer phone) {
		User user = new User();
		user.setUser_name(userName);
		user.setUser_phone(phone);
		user.setUser_pwd(password);
		int row = userMapper.updateUser(user);
		return row;
	}

	@Override
	public User findUserByUserPhone(Integer phone) {
		User users = userMapper.findUserByUserPhone(phone);
		return users;
	}

	@Override
	public User findUserById(Integer id) {
		User users = userMapper.findUserById(id);
		return users;
	}

	@Override
	public int UserBorrowBook(HttpServletRequest request) {
		//获取前端传来的数据
		String bookid = request.getParameter("bookid");
		String firstBordate = request.getParameter("broDate");
		String lastDate = request.getParameter("lastDate");
		//User user = (User) request.getSession().getAttribute("user");
		BorrowingBooks books = new BorrowingBooks();
		//存储bookid
		books.setBookId(Integer.valueOf(bookid));
		//存储用户id
		//books.setUserId(user.getUser_id());
		//改行为测试代码行 
		books.setUserId(1);
		books.setIsreturn(0);
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		Date fDate;
		Date lDate;
		try {
			fDate = format.parse(firstBordate);
			lDate = format.parse(lastDate);
			books.setDate(fDate);
			books.setLastdate(lDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return borrowingBooksMapper.BorrowBook(books);
	}

	@Override
	public int returnBook(Integer brrowingbookid,int i) {
		// TODO Auto-generated method stub
		return borrowingBooksMapper.retunBook(i,brrowingbookid);
		
		
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
