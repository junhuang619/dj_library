package com.djcps.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;

/**
 * @author djsxs
 *
 */
public interface BookMapper {

	/**添加书籍
	 * @param book
	 * @return
	 */
	int addBook(Book book);
	
	/**更新书籍
	 * @param book
	 * @return
	 */
	int updateBook(Book book);

	/**书籍集合
	 * @return
	 */
	List<Book> listBook();

	/**根据bookId删除书籍
	 * @param bookId
	 * @return
	 */
	int delBookByid(@Param("bookId")Integer bookId);

	/**书籍总数
	 * @return
	 */
	int getBookTotalCounts();

	/**查询所有书籍并按条件分页
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<BorrowingBooks> selectAllByCondition(@Param("pageIndex")int pageIndex,
			                                  @Param("pageSize") int pageSize);

	/**通过书名查询书籍
	 * @param bookName
	 * @return
	 */
	List<Book> findBookBybookName(@Param("bookName")String bookName);

	/**通过上架日期查找书籍
	 * @return
	 */
	List<Book> findBookByTheOnsaleDate();

	/**借书日期
	 * @param bookId
	 * @return
	 */
	int getBookdateCount(@Param("bookId")Integer bookId);

	/**通过bookId更改书籍
	 * @param bookId
	 * @param isBorrowed
	 * @return
	 */
	int updateBookByid(@Param("bookId")Integer bookId,
			@Param("isBorrowed") int isBorrowed);

	/**通过bookId获取该书被借次数
	 * @param bookId
	 * @return
	 */
	int getBookBorrowCountByid(Integer bookId);

	/**通过bookId更新书籍被借次数
	 * @param bookId
	 * @return
	 */
	int updateBookborrowCountByid(@Param("bookId")Integer bookId);
	/**通过bookId获取书籍信息
	 * @param bookId
	 * @return
	 */
	Book getBookMsgByid(Integer bookId);
}
