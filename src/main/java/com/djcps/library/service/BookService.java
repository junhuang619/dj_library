package com.djcps.library.service;

import java.util.List;

import com.djcps.library.model.Book;
import com.djcps.library.model.Vo.PageVo;

public interface BookService {

	int addBook(Book book);

	List<Book> listbook();

	PageVo selectAllBook(int pageNum);

}
