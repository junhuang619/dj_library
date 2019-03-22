package com.djcps.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.BorrowingBooks;

/**
 * @author djsxs
 *
 */
public interface BorrowingBooksMapper {
    
    /**按条件查询所有借书记录并分页显示
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<BorrowingBooks> selectAllByCondition(@Param("pageIndex")int pageIndex, 
    		                      @Param("pageSize")int pageSize);

	/**借书
	 * @param books
	 * @return
	 */
	int borrowBook(BorrowingBooks books);

	/**还书
	 * @param i
	 * @param brrowingbookid
	 * @return
	 */
	int retunBook(@Param(value="i")int i,
			@Param(value="brrowingbookid")Integer brrowingbookid);

	/**获取所有借书记录总数
	 * @return
	 */
	int getBorrowingTotalCount();

	/**根据id获取借书记录信息
	 * @param id
	 * @return
	 */
	BorrowingBooks getBorrowMsgByid(@Param("id")Integer id);

	/**根据bookId更新最新借书日期
	 * @param books
	 * @return
	 */
	int updatelastdateByid(BorrowingBooks books);
	
	BorrowingBooks getBorrowMsgBybookId(@Param("bookId")Integer bookId);
	
	
	int getBorrowingBookTotalCountsByUserId(@Param("userId")Integer userId);
	

	List<BorrowingBooks> getBorrowingBookMsgByUserId(@Param("userId")Integer userId,@Param("pageIndex")int pageIndex, 
            @Param("pageSize")int pageSize);

	BorrowingBooks getBorrowRecordByBookId(@Param("bookId")Integer bookId);

	int delBorrowingRecordByisreturn();
	
}