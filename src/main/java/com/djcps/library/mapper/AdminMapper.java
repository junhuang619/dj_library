package com.djcps.library.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.Admin;
@Mapper
public interface AdminMapper {

	Admin adminIsExist(@Param("adminName")String adminName);

	Admin adminLogin(@Param("adminName")String adminName, @Param("password")String password);
 
}