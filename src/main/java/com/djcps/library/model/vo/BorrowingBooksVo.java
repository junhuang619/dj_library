package com.djcps.library.model.vo;


import java.util.Date;

import com.djcps.library.model.Book;
import com.djcps.library.model.User;


/**
 * @author djsxs
 *
 */
public class BorrowingBooksVo {
	private Integer borrowBookId;
	private Integer bookId;
	private String bookName;
	private Double bookPrice;
	private Integer isReturn;
	/** 可借天数 */
	private Integer dateCount;
	/** 已借日期 */
	private Long borrowedDays;
	public Integer getBorrowBookId() {
		return borrowBookId;
	}
	public void setBorrowBookId(Integer borrowBookId) {
		this.borrowBookId = borrowBookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Integer getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(Integer isReturn) {
		this.isReturn = isReturn;
	}
	public Integer getDateCount() {
		return dateCount;
	}
	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}
	public Long getBorrowedDays() {
		return borrowedDays;
	}
	public void setBorrowedDays(Long borrowedDays) {
		this.borrowedDays = borrowedDays;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	
}
