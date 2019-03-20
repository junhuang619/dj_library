package com.djcps.library.model.vo;

import java.util.Date;

import com.djcps.library.model.User;

/**
 * @author djsxs
 *
 */
public class BookVo {
	/**书籍id*/
    private Integer bookId;  
    /**书名*/
    private String bookName; 
    /**起始日期*/
    private Date beginDate;  
    /**剩余天数*/
    private Integer dateCount;
    /**借书用户信息*/
    private User user;
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Integer getDateCount() {
		return dateCount;
	}
	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BookVo(Integer bookId, String bookName, Date beginDate, Integer dateCount, User user) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.beginDate = beginDate;
		this.dateCount = dateCount;
		this.user = user;
	}
	public BookVo() {
		super();
	}

    
}
