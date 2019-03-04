package com.djcps.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Book;
import com.djcps.library.model.Vo.PageVo;
import com.djcps.library.service.BookService;
@RestController
@RequestMapping(value="/book")
public class BookCntroller {
    
	@Autowired
    @Qualifier("bookservice")
	private BookService bookService; 
	/**
	 * 查询全部书籍
	 * 并返回list
	 * @return
	 */
	@RequestMapping(value="/listBook")
	public RetResult<List<Book>> listBook(){
		List<Book> list = bookService.listbook();	
		return RetResponse.makeOKRsp(list);
	}
	/**
	 * 返回所有书籍页面
	 * 
	 * @return
	 */
	@RequestMapping("/allBooks")
	public RetResult<PageVo> allBorrowingBooksRecordPage(@RequestParam("pageNum") int pageNum) {
		PageVo pVo = bookService.selectAllBook(pageNum);
		return RetResponse.makeOKRsp(pVo);
	}
	
}
