package com.djcps.library.model.vo;

/**
 * @author djsxs
 *
 */
public class BookVo {
	/**书籍id*/
    private Integer bookId;  
    /**书名*/
    private String bookName; 
    /**作者*/
    private String bookAuthor;
    /**出版社*/
    private String bookPublish;
    /**是否可借*/
    private String isExist;  

    public Integer getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public String getIsExist() {
        return isExist;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish;
    }

    public void setIsExist(String isExist) {
        this.isExist = isExist;
    }
}
