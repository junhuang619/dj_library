package com.djcps.library.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.djcps.library.mapper.AdminMapper;
import com.djcps.library.mapper.BookMapper;
import com.djcps.library.model.Admin;
import com.djcps.library.service.AdminService;

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
	public boolean adminIsExist(String adminName) {
		// TODO Auto-generated method stub
		Admin admin = adminMapper.adminIsExist(adminName);
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
	public boolean adminLogin(String adminName, String password) {
		Admin admin = adminMapper.adminLogin(adminName, password);
		boolean flag = false;
		if (admin != null) {
			flag = true;
		}
		return flag;
	}
	
   /**
    * 根据书籍ID删除书籍
    */
	@Override
	public int delBookByid(Integer bookId) {
		int row = bookmapper.delBookByid(bookId);
		return row;
	}

}
