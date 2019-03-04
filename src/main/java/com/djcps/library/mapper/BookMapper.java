package com.djcps.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;

public interface BookMapper {

	int addBook(Book book);

	List<Book> listbook();

	int delBookByid(@Param("bookId")Integer bookId);

	int getBookTotalCounts();

	List<BorrowingBooks> selectAllByCondition(@Param("pageIndex")int pageIndex,
			                                  @Param("pageSize") int pageSize);

	List<Book> findBookBybookName(@Param("bookName")String bookName);

	List<Book> findBookByTheOnsaleDate();

	
    
	
  
}
