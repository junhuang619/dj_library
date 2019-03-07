package com.djcps.library.model.vo;

import java.util.List;

import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;

/**
 * @author djsxs
 *
 */
public class PageVo {
	/**封装 前端传来的第几页*/
	private Integer pageIndex; 
	/**每一页有几个记录*/
	private Integer pageSize; 
	/**总共几页*/
	private Integer totalPage; 
	/**书籍集合*/
	private List<Book> bookList; 
	/**借书记录集合*/
	private List<BorrowingBooks> borrowingBookslist; 
	public PageVo(Integer pageIndex, Integer pageSize, Integer totalPage, List<Book> bookList,
			List<BorrowingBooks> borrowingBookslist) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		bookList = bookList;
		this.borrowingBookslist = borrowingBookslist;
	}

	public PageVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PageVo [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", bookList="
				+ bookList + ", borrowingBookslist=" + borrowingBookslist + "]";
	}	

	public List<Book> getbookList() {
		return bookList;
	}

	public void setbookList(List<Book> bookList) {
		bookList = bookList;
	}

	public List<BorrowingBooks> getBorrowingBookslist() {
		return borrowingBookslist;
	}

	public void setBorrowingBookslist(List<BorrowingBooks> borrowingBookslist) {
		this.borrowingBookslist = borrowingBookslist;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

}
