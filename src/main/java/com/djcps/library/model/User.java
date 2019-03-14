package com.djcps.library.model;


/**
 * @author djsxs
 *
 */
public class User {
	private Integer userId;
	private String userName;
	private String userPwd;
	private String userPhone;
	private Integer isAllowBorrow;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getIsAllowBorrow() {
		return isAllowBorrow;
	}
	public void setIsAllowBorrow(Integer isAllowBorrow) {
		this.isAllowBorrow = isAllowBorrow;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userPhone=" + userPhone
				+ ", isAllowBorrow=" + isAllowBorrow + "]";
	}
	public User(Integer userId, String userName, String userPwd, String userPhone, Integer isAllowBorrow) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userPhone = userPhone;
		this.isAllowBorrow = isAllowBorrow;
	}
	public User() {
		super();
	}
	
	
	
}
