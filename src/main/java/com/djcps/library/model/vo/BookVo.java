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
    /**已借天数*/
    private Long borrowedDays;
    /**剩余天数*/
    private Long  remainDayCount;
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
	public Long getBorrowedDays() {
		return borrowedDays;
	}
	public void setBorrowedDays(Long borrowedDays) {
		this.borrowedDays = borrowedDays;
	}
	public Long getRemainDayCount() {
		return remainDayCount;
	}
	public void setRemainDayCount(Long remainDayCount) {
		this.remainDayCount = remainDayCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BookVo(Integer bookId, String bookName, Date beginDate, Long borrowedDays, Long remainDayCount, User user) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.beginDate = beginDate;
		this.borrowedDays = borrowedDays;
		this.remainDayCount = remainDayCount;
		this.user = user;
	}
	public BookVo() {
		super();
	}
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", bookName=" + bookName + ", beginDate=" + beginDate + ", borrowedDays="
				+ borrowedDays + ", remainDayCount=" + remainDayCount + ", user=" + user + "]";
	}
	
	
    
}
