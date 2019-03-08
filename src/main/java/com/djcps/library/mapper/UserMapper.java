package com.djcps.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.User;
/**
 * @author djsxs
 *
 */
@Mapper
public interface UserMapper {
	/**注册账户
	 * @param user
	 * @return
	 */
	public int register(User user);

	
	/**登陆
	 * @param userPhone
	 * @param userPwd
	 * @return
	 */
	public User findUser(@Param(value="userPhone")String userPhone,
			@Param(value="userPwd")String userPwd);

	/**更新用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**通过phone查找用户
	 * @param phone
	 * @return
	 */
	public User findUserByUserPhone(@Param(value="phone")String phone);

	/**通过id查找用户
	 * @param id
	 * @return
	 */
	public User findUserById(@Param(value="id")Integer id);

	
	
	
}
