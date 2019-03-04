package com.djcps.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.User;
@Mapper
public interface UserMapper {
	public int register(User user);

	public User findUser(@Param(value="phone")Integer phone,@Param(value="password")String password);

	public int updateUser(User user);

	public User findUserByUserPhone(@Param(value="phone")Integer phone);

	public User findUserById(@Param(value="id")Integer id);

	
	
	
}
