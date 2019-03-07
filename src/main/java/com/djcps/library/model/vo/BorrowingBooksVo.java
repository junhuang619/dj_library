package com.djcps.library.model.vo;


import com.djcps.library.model.Book;
import com.djcps.library.model.User;


/**
 * @author djsxs
 *
 */
public class BorrowingBooksVo {
    private User user;
    /**借阅书籍 */
    private Book book;  
    /**借书日期 */
    private String dateOfBorrowing;  
    /**还书日期 */
    private String dateOfReturn;    

    public void setBook(Book book) {
        this.book = book;
    }

    public void setDateOfBorrowing(String dateOfBorrowing) {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public Book getBook() {
        return book;
    }

    public String getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public String getDateOfReturn() {
        return dateOfReturn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
