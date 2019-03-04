package com.djcps.library.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.library.mapper.BookMapper;
import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;
import com.djcps.library.model.Vo.PageVo;
import com.djcps.library.service.BookService;

@Service("bookservice")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper BookMapper;

	@Override
	public int addBook(Book book) {
		// TODO Auto-generated method stub
		int row = BookMapper.addBook(book);
		return row;
	}

	@Override
	public List<Book> listbook() {
		// TODO Auto-generated method stub
		List<Book> list = BookMapper.listbook();
		return list;
	}

	@Override
	public PageVo selectAllBook(int pageNum) {
		int bookTotalCounts = BookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 8;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 8;
		} else {
			pageIndex = (pageNum - 1) * pageSize ;
			pageSize = pageNum * 8;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 8 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<BorrowingBooks> list = BookMapper.selectAllByCondition(pageIndex, pageSize);
		pageVo.setBorrowingBookslist(list);
		return pageVo;
	}

}
