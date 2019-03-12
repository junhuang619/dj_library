package com.djcps.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Admin;
import com.djcps.library.model.Book;
import com.djcps.library.service.AdminService;

/**
 * @author djsxs
 *
 */
@RestController()
@RequestMapping("/admin")
public class AdminController {
	@Autowired()
	@Qualifier(value = "adminservice")
	private AdminService adminService;

	/**
	 * 判断admin用户名是否存在
	 * 
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/isAdminExist")
	@ResponseBody
	public RetResult<String> adminIsExist(@RequestParam("phone") String phone) {
		boolean b = adminService.adminIsExist(phone);
		if (b) {
			return RetResponse.makeOKRsp();
		} else {
			return RetResponse.makeErrRsp("该用户名已存在");
		}
	}

	/**
	 * 管理员登录后台
	 */
	@RequestMapping("/adminLogin")
	@ResponseBody
	public RetResult<String> adminLogin(@RequestParam("phone") String phone, @RequestParam("password") String password,
			HttpServletRequest request) {
		Admin admin = adminService.adminLogin(phone, password);
		if (null == admin) {
			return RetResponse.makeErrRsp("管理员登录失败！");
		}
		request.getSession().setAttribute("admin", admin);
		return RetResponse.makeOKRsp();
	}
	/**
	 * 管理员删除书籍
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delBook")
	public RetResult<String> delBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		int row = adminService.delBookByid(Integer.valueOf(bookId));
		if (row == 0) {
			return RetResponse.makeErrRsp("书籍删除失败");
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返回管理员登录界面
	 * 
	 * @return
	 */
	@GetMapping("/adminLoginPage")
	public ModelAndView adminLoginPage() {
		ModelAndView mAndView = new ModelAndView();
		mAndView.setViewName("adminLogin");
		return mAndView;
	}

	/**
	 * 添加书籍信息
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/addBook")
	public RetResult<String> addBookMsg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (file.isEmpty()) {
			return RetResponse.makeErrRsp("上传文件信息为空！");
		}
		int row = adminService.addBookMsg(file, request);
		if (row == 0) {
			return RetResponse.makeErrRsp("书籍信息保存失败！");
		}
		return RetResponse.makeOKRsp();
	}

	@PostMapping("/updateBook")
	public RetResult<String> updateBookMsg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if (file.isEmpty()) {
			return RetResponse.makeErrRsp("上传文件信息为空！");
		}
		int row = adminService.updateBookMsg(file, request);
		if (row == 0) {
			return RetResponse.makeErrRsp("书籍信息保存失败！");
		}
		return RetResponse.makeOKRsp();
	}

	/**
	 * 返回书籍信息
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/returnBookmsg")
	public RetResult<Book> returnBookMsg(Integer bookId) {
		Book book = adminService.getBookMsg(bookId);
		if (book == null) {
			return RetResponse.makeErrRsp("书籍信息查找不存在");
		}
		return RetResponse.makeOKRsp(book);
	}

}
