package com.djcps.library.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Book;
import com.djcps.library.model.User;
import com.djcps.library.model.vo.BookVo;
import com.djcps.library.model.vo.PageVo;
import com.djcps.library.service.UserService;

/**
 * @author djsxs
 *
 */
@RestController
@RequestMapping(value = "dj_library/user", method = { RequestMethod.GET, RequestMethod.POST })
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
		int row = userService.registerUser(userName, password, phone);
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
	public RetResult<Object> userLogin(@RequestParam("phone") String userPhone,
			@RequestParam("password") String userPwd, HttpServletRequest request) {
		User user = userService.userLogin(userPhone, userPwd, request);
		if (null == user) {
			return RetResponse.makeErrRsp("login failed");
		}
		return RetResponse.makeOKRsp(user);
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
	@RequestMapping("/updateUserByphone")
	public RetResult<String> updateUserByphone(@Param("userName") String userName, @Param("phone") String phone,
			@Param("newPassword") String newPassword) {
		String md5Password = DigestUtils.md5DigestAsHex(newPassword.getBytes());
		int row = userService.updateUser(userName, md5Password, phone);
		if (row == 0) {
			return RetResponse.makeErrRsp("update user failed");
		}
		return RetResponse.makeOKRsp();
	}

	@RequestMapping("/isRightPassword")
	public RetResult<String> isRightPassword(@Param("phone") String phone, @Param("oldPassword") String oldPassword) {
		User user = userService.findUserByUserPhone(phone);
		String md5Password = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
		if (!md5Password.equals(user.getUserPwd())) {
			return RetResponse.makeErrRsp("password failed");
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
	public void userLogOut(HttpServletRequest request) {
		request.getSession().invalidate();

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
	public RetResult<String> userReturnBook(@RequestParam("borrowBookId")Integer borrowBookId) {
		int isreturn = 1;
		int row = userService.returnBook(borrowBookId, isreturn);
		if (row == 0) {
			return RetResponse.makeErrRsp("书籍归还失败");

		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 书籍续借
	 * 
	 * @param borrowBookid
	 * @return
	 */
	@RequestMapping("/continueBorrowBook")
	public RetResult<String> continueBorrowBook(HttpServletRequest request) {
		int row = userService.continueBorrowBook(request);
		if (row == 0) {
			return RetResponse.makeErrRsp("续借失败！");
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返回还书页面
	 * 
	 * @return
	 */
	/**
	 * @RequestMapping("/userReturnBooksPage") public RetResult<String>
	 * userReturnBooksPage() { return RetResponse.makeOKRsp(); }
	 */

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
	 * 根据书籍的id查询该书籍的详细借阅信息
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/getBorrowedMsgBybookId")
	public RetResult<BookVo> getBorrowedMsgBybookId(@RequestParam("bookId") Integer bookId) {
		BookVo bookVo = userService.getBorrowedMsg(bookId);
		if (bookVo == null) {
			return RetResponse.makeErrRsp("书籍信息返回失败！");
		} else {
			return RetResponse.makeOKRsp(bookVo);
		}
	}

	@RequestMapping("/getBorrowBookMsgByUserId")
	public RetResult<PageVo> getBorrowBookMsgByUserId(@RequestParam("pageNum") int pageNum,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		PageVo pageVo = userService.getBorrowBookMsg(pageNum, user.getUserId());
		/*Integer userId = Integer.valueOf(request.getParameter("userId"));
		PageVo pageVo = userService.getBorrowBookMsg(pageNum, userId);*/
		if (pageVo == null) {
			return RetResponse.makeErrRsp("记录查询失败！");
		} else {
			return RetResponse.makeOKRsp(pageVo);
		}
	}
	public User findUserByPhone(String phone) {
		return userService.findUserByUserPhone(phone);
	}
}
