package com.djcps.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.service.AdminService;

@RestController()
@RequestMapping("/admin")
public class AdminController {
	@Autowired()
	@Qualifier(value = "adminservice")
	private AdminService adService;

	/**
	 * 判断admin用户名是否存在
	 * 
	 * @param adminName
	 * @return
	 */
	@RequestMapping("/isAdminExist")
	@ResponseBody
	public RetResult<String> adminIsExist(@Param("adminName") String adminName) {
		boolean b = adService.adminIsExist(adminName);
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
	public RetResult<String> adminLogin(@Param("adminName") String adminName, @Param("password") String password) {
		boolean b = adService.adminLogin(adminName, password);
		if (b) {
			return RetResponse.makeOKRsp();
		} else
			return RetResponse.makeErrRsp("管理员登录失败！");
	}
     /**
      * 管理员删除书籍
      * @param request
      * @return
      */
	@RequestMapping("/delBook")
	public RetResult<String> delBook(HttpServletRequest request) {
		String bookId = request.getParameter("bookId");
		int row = adService.delBookByid(Integer.valueOf(bookId));
		if(row>0){
			return RetResponse.makeOKRsp();
		}
		return RetResponse.makeErrRsp("书籍删除失败");
	}

}
