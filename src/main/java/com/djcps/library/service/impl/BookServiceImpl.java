package com.djcps.library.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djcps.library.mapper.BookMapper;
import com.djcps.library.model.Book;
import com.djcps.library.model.vo.PageVo;
import com.djcps.library.service.BookService;

/**
 * @author djsxs
 *
 */
@Service("bookservice")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;

	

	@Override
	public List<Book> listBook() {
		List<Book> list = bookMapper.listBook();
		return list;
	}

	@Override
	public PageVo selectAllBook(int pageNum) {
		int bookTotalCounts = bookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<Book> list = bookMapper.selectAllByCondition(pageIndex, pageSize);
		pageVo.setBookList(list);
		return pageVo;
	}
	
	@Override
	public PageVo findBookByTheOnsaleDate(int pageNum) {
		int bookTotalCounts = bookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<Book> list = bookMapper.findBookByTheOnsaleDate(pageIndex, pageSize);
		pageVo.setBookList(list);
		return pageVo;
	}
	
	@Override
	public List<Book> findBookByOnRecently() {
		SimpleDateFormat formata = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		try {
			date = formata.parse(formata.format(date));
			System.out.println(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return bookMapper.getBookOnRecently(date);
	}

	@Override
	public PageVo findHotBook(int pageNum) {
		int bookTotalCounts = bookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<Book> list = bookMapper.findHotBook(pageIndex, pageSize);
		pageVo.setBookList(list);
		return pageVo;
	}

	@Override
	public PageVo getBookListBybookCondition(Book bookCondition, int pageNum) {
		int bookTotalCounts = bookMapper.getBookTotalCounts();
		PageVo pageVo = new PageVo();
		int pageSize = 10;
		int pageIndex = 0;
		int totalPage = 0;
		if (0 == pageNum) {
			pageIndex = 1;
			pageSize = 10;
		} else {
			pageIndex = (pageNum - 1) * pageSize;
			pageSize = pageNum * 10;
		}
		if (bookTotalCounts > 0) {
			totalPage = (bookTotalCounts - 1) / 10 + 1;
		}
		pageVo.setPageIndex(pageIndex);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageSize(pageSize);
		List<Book> list = bookMapper.getBookListBybookCondition(bookCondition, pageIndex, pageSize);
		pageVo.setBookList(list);
		return pageVo;
	}
}
