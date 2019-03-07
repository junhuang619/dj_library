package com.djcps.library.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Book;
import com.djcps.library.model.User;
import com.djcps.library.service.UserService;

/**
 * @author djsxs
 *
 */
@RestController
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
	@Resource(name = "userservice")
	private UserService userService;

	/**
	 * 用户注册功能
	 * 
	 * @param name
	 * @param pwd
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public RetResult<String> registerUser(@Param(value = "userName") String userName,
			@Param(value = "password") String password, @Param(value = "phone") String phone) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPwd(password);
		user.setUserPhone(phone);
		int row = userService.registerUser(user);
		if (row == 0) {
			return RetResponse.makeErrRsp("用户注册失败！");		
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 用户登录功能的实现
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public RetResult<String> userLogin(@Param("phone") String userPhone, 
			@Param("password") String userPwd,
			HttpServletRequest request) {
		User user = userService.userLogin(userPhone, userPwd);
		if (null == user) {
			return RetResponse.makeErrRsp("login failed");			
		}
		request.getSession().setAttribute("user", user);
		return RetResponse.makeOKRsp();
	}
	/**
	 * 返回个人信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/userMessagePage")
	public RetResult<User> userMessagePage(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		User user = userService.findUserById(sessionUser.getUserId());
		return RetResponse.makeOKRsp(user);
	}

	/**
	 * 用户更新信息
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/userSubmit")
	public RetResult<String> updateUserByphone(@Param("userName") String userName, @Param("password") String password,
			@Param("phone") String phone) {
		int row = userService.updateuser(userName, password, phone);
		if (row == 0) {
			return RetResponse.makeErrRsp("update user failed");			
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 用户退出登陆
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/userLogOut")
	public Object userLogOut(HttpServletRequest request) {
		request.getSession().invalidate();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	/**
	 * //验证用户是否存在
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping("/isUserExist")
	public RetResult<String> isUserExist(@Param("phone") String phone) {
		User users = userService.findUserByUserPhone(phone);
		if (null == users) {
			return RetResponse.makeErrRsp("用户已存在");
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 用户借书
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/borrowing")
	public RetResult<String> userBorrowBook(HttpServletRequest request) {
		int row = userService.userBorrowBook(request);
		if (row == 0) {
			return RetResponse.makeErrRsp("failed");			
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返回借书页面
	 * 
	 * @return
	 */
	@RequestMapping("/borrowingPage")
	public RetResult<String> borrowing() {
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返还书籍
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/returnBook")
	public RetResult<String> userReturnBook(HttpServletRequest request) {
		String borrwingId = request.getParameter("brrowingId");
		int isreturn = 1;
		int row = userService.returnBook(Integer.valueOf(borrwingId), isreturn);
		if (row == 0) {
			return RetResponse.makeErrRsp("书籍归还失败");

		}
		return RetResponse.makeOKRsp();
	}
    /**
     * 书籍续借
     * @param borrowBookid
     * @return
     */
	@RequestMapping("/continueBorrowBook")
	public RetResult<String> continueBorrowBook(@Param("borrowBookid")Integer borrowBookid) {
		int row = userService.continueBorrowBook(borrowBookid);
		if (row == 0) {
			RetResponse.makeErrRsp("续借失败！");
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返回还书页面
	 * 
	 * @return
	 */
	@RequestMapping("/userReturnBooksPage")
	public RetResult<String> userReturnBooksPage() {
		return RetResponse.makeOKRsp();
	}

	/**
	 * 按照书名进行模糊查询
	 */
	@RequestMapping("/findBooksByBookName")
	public RetResult<List<Book>> findBooksByBookName(HttpServletRequest request) {
		String bookName = request.getParameter("bookName");
		List<Book> list = userService.findBookBybookName(bookName);
		return RetResponse.makeOKRsp(list);
	}

	/**
	 * 按照书籍上架时间查询
	 * 
	 * @return
	 */
	@RequestMapping("/findBookByOnsaleDate")
	public RetResult<List<Book>> findBookByTheOnSaleDate() {
		List<Book> list = userService.findBookByTheOnsaleDate();
		return RetResponse.makeOKRsp(list);
	}
	

}
