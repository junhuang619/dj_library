package com.djcps.library.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.Book;
import com.djcps.library.model.vo.PageVo;

/**
 * @author djsxs
 *
 */
public interface BookService {


	/**书籍集合
	 * @return
	 */
	List<Book> listBook();

	/**根据页码查询所有书籍
	 * @param pageNum
	 * @return
	 */
	PageVo selectAllBook(int pageNum);
	
	/**根据上架时间查询书籍
	 * @return
	 */
	PageVo findBookByTheOnsaleDate(int pageNum);
	
	/**查找最近几天上架书籍
	 * @return
	 */
	List<Book> findBookByOnRecently();
	
	PageVo findHotBook(int pageNum);
	
	PageVo getBookListBybookCondition(Book bookCondition,int pageNum);

}
