package com.djcps.library.model;

import java.util.Date;

public class Book {
    private Integer bookId;

    private String bookName;

    private String bookAuthor;

    private String bookPublish;

    private Integer bookCategory;

    private Double bookPrice;

    private String bookIntroduction;
    
    private String bookImage;
    
    private Date bookDate;
    
    private Integer isborrowedout;
    
    private Integer dateCount;
    
    private Integer borrowCount;

    public Integer getIsborrowedout() {
		return isborrowedout;
	}

	public void setIsborrowedout(Integer isborrowedout) {
		this.isborrowedout = isborrowedout;
	}

	public Integer getDateCount() {
		return dateCount;
	}

	public void setDateCount(Integer dateCount) {
		this.dateCount = dateCount;
	}

	public Integer getBorrowCount() {
		return borrowCount;
	}

	public void setBorrowCount(Integer borrowCount) {
		this.borrowCount = borrowCount;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

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
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish == null ? null : bookPublish.trim();
    }

    public Integer getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Integer bookCategory) {
        this.bookCategory = bookCategory;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookIntroduction() {
        return bookIntroduction;
    }

    public void setBookIntroduction(String bookIntroduction) {
        this.bookIntroduction = bookIntroduction == null ? null : bookIntroduction.trim();
    }
}