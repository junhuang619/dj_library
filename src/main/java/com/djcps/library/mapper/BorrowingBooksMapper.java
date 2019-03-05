package com.djcps.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.djcps.library.model.BorrowingBooks;

public interface BorrowingBooksMapper {
    
    //查询所有记录总数
    List<BorrowingBooks> selectAllByCondition(@Param("pageIndex")int pageIndex, 
    		                      @Param("pageSize")int pageSize);

	int BorrowBook(BorrowingBooks books);

	int retunBook( @Param(value="i")int i,@Param(value="brrowingbookid")Integer brrowingbookid);

	int getBorrowingTotalCount();

	BorrowingBooks getBorrowMsgByid(@Param("id")Integer id);

	int updatelastdateByid(BorrowingBooks books);
}