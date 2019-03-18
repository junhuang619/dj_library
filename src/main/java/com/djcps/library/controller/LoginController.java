/*package com.djcps.library.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Admin;
import com.djcps.library.model.User;

*//**
 * @author djsxs
 *
 *//*
@RestController
@RequestMapping("dj_library/")
public class LoginController {
	@Resource
	private AdminController adminController;
	@Resource
	private UserController usercontroller;

	@RequestMapping("login")
	public RetResult<Object> release(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		Admin admin = adminController.findAdminByPhone(phone);
		User user = usercontroller.findUserByPhone(phone);
		if (admin != null) {
			return adminController.adminLogin(phone, password, request);
		} else if (user != null) {
			return usercontroller.userLogin(phone, password, request);
		}
		return RetResponse.makeErrRsp("无法访问");
	}
}*/
