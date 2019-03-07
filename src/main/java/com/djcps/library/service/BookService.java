package com.djcps.library.service;

import java.util.List;

import com.djcps.library.model.Book;
import com.djcps.library.model.vo.PageVo;

/**
 * @author djsxs
 *
 */
public interface BookService {

	/**添加书籍
	 * @param book
	 * @return
	 */
	int addBook(Book book);

	/**书籍集合
	 * @return
	 */
	List<Book> listbook();

	/**根据页码查询所有书籍
	 * @param pageNum
	 * @return
	 */
	PageVo selectAllBook(int pageNum);

}
