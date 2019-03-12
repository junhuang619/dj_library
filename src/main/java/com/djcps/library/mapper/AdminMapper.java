package com.djcps.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.Admin;
/**
 * @author djsxs
 *
 */
@Mapper
public interface AdminMapper {
	
	
	/**检测管理员账号是否存在
	 * @param adminName
	 * @return
	 */
	Admin adminIsExist(@Param("phone")String phone);

	/**管理员登陆
	 * @param adminName
	 * @param password
	 * @return
	 */
	Admin adminLogin(@Param("phone")String phone, @Param("password")String password);
 
}