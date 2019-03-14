package com.djcps.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.vo.PageVo;
import com.djcps.library.service.BookService;
/**
 * @author djsxs
 *
 */
@RestController
@RequestMapping(value="/book")
public class BookController {
    
	@Autowired
    @Qualifier("bookservice")
	private BookService bookService; 
	/**
	 * 返回所有书籍页面
	 * 
	 * @return
	 */
	@RequestMapping("/listBook")
	public RetResult<PageVo> listBookByPageNum(@RequestParam("pageNum") int pageNum) {
		PageVo pVo = bookService.selectAllBook(pageNum);
		return RetResponse.makeOKRsp(pVo);
	}
	
}
