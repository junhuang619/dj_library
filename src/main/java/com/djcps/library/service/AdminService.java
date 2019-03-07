package com.djcps.library.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.djcps.library.model.Book;

/**
 * @author djsxs
 *
 */
public interface AdminService {

	/**管理员账号是否存在
	 * @param adminName
	 * @return
	 */
	boolean adminIsExist(String adminName);

	/**管理员登陆
	 * @param adminName
	 * @param password
	 * @return
	 */
	boolean adminLogin(String adminName, String password);

	/**根据id删除书籍
	 * @param bookId
	 * @return
	 */
	int delBookByid(Integer bookId);

	/**添加书籍信息
	 * @param file
	 * @param request
	 * @return
	 */
	int addBookMsg(MultipartFile file, HttpServletRequest request);
	
	/**根据bookId查找书籍信息
	 * @param bookId
	 * @return
	 */
	Book getBookMsg(Integer bookId);

	/**更新书籍信息
	 * @param file
	 * @param request
	 * @return
	 */
	int updateBookMsg(MultipartFile file, HttpServletRequest request);


}
