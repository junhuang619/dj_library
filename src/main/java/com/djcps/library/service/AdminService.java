package com.djcps.library.service;

public interface AdminService {

	boolean adminIsExist(String adminName);

	boolean adminLogin(String adminName, String password);

	int delBookByid(Integer bookId);

}
