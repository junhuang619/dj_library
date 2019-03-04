package com.djcps.library.model;

import java.util.Date;

public class BorrowingBooks {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Date date;            //借书日期
   
    private Date lastdate;        //书籍还书截止日期
    
    private Integer isreturn;     //书籍归还状态   0未归还  1归还
     
    public Integer getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(Integer isreturn) {
		this.isreturn = isreturn;
	}

	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	
}