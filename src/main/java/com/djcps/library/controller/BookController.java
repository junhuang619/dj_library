package com.djcps.library.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.djcps.library.common.RetResponse;
import com.djcps.library.common.RetResult;
import com.djcps.library.model.Book;
import com.djcps.library.model.vo.PageVo;
import com.djcps.library.service.BookService;

/**
 * @author djsxs
 *
 */
@RestController
@RequestMapping(value = "dj_library/book")
public class BookController {
	@Autowired
	@Qualifier("bookservice")
	private BookService bookService;

	/**
	 * 返回所有书籍页面
	 * 
	 * @return
	 */
	@RequestMapping("/allBook")
	public RetResult<List<Book>> allBook() {
		List<Book> list = bookService.listBook();
		if (list == null) {
			return RetResponse.makeErrRsp("查询失败");
		}
		return RetResponse.makeOKRsp(list);
	}

	/**
	 * 按页分查询书籍列表
	 * 
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/listBook")
	public RetResult<PageVo> listBookByPageNum(@RequestParam("pageNum") int pageNum) {
		PageVo pVo = bookService.selectAllBook(pageNum);
		return RetResponse.makeOKRsp(pVo);
	}

	/**
	 * 按照书籍上架时间查询
	 * 
	 * @return
	 */
	@RequestMapping("/findBookByOnsaleDate")
	public RetResult<PageVo> findBookByTheOnSaleDate(@RequestParam("pageNum") int pageNum) {
		PageVo pVo = bookService.findBookByTheOnsaleDate(pageNum);
		return RetResponse.makeOKRsp(pVo);
	}

	@RequestMapping("/findBookByOnRecently")
	public RetResult<List<Book>> findBookByOnRecently() {
		List<Book> list = bookService.findBookByOnRecently();
		return RetResponse.makeOKRsp(list);
	}

	@RequestMapping("/findHotBook")
	public RetResult<PageVo> findHotBook(@RequestParam("pageNum") int pageNum) {
		PageVo pVo = bookService.findHotBook(pageNum);
		return RetResponse.makeOKRsp(pVo);
	}

	@RequestMapping("/getBookListBybookCondition")
	public RetResult<PageVo> getBookListBybookCondition(
			@RequestParam(value = "bookName", required = false) String bookName,
			@RequestParam(value = "bookDate", required = false) String bookDate,
			@RequestParam(value = "isborrowedout", required = false) Integer isborrowedout,
			@RequestParam("pageNum") int pageNum) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date newbookDate = new Date();
		if (!bookDate.equals("")) {
			try {
				newbookDate = sdf.parse(bookDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			newbookDate = null;
		}
		Book bookCondition = compactBookConditionToSearch(bookName, newbookDate, isborrowedout);
		PageVo pVo = bookService.getBookListBybookCondition(bookCondition, Integer.valueOf(pageNum));
		return RetResponse.makeOKRsp(pVo);
	}

	private Book compactBookConditionToSearch(String bookName, Date newbookDate, Integer isborrowedout) {
		Book bookCondition = new Book();
		if (bookName != null) {
			bookCondition.setBookName(bookName);
		}
		if (newbookDate != null) {
			bookCondition.setBookDate(newbookDate);
		}
		if (isborrowedout != null) {
			bookCondition.setIsborrowedout(isborrowedout);
		}
		return bookCondition;
	}

}
