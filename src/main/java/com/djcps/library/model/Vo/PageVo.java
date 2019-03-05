package com.djcps.library.model.Vo;

import java.util.List;

import com.djcps.library.model.Book;
import com.djcps.library.model.BorrowingBooks;

public class PageVo {
	private Integer pageIndex; // 封装 前端传来的第几页
	private Integer pageSize; // 每一页有几个记录
	private Integer totalPage; // 总共几页
	private List<Book> Booklist; //
	private List<BorrowingBooks> borrowingBookslist; //
	public PageVo(Integer pageIndex, Integer pageSize, Integer totalPage, List<Book> booklist,
			List<BorrowingBooks> borrowingBookslist) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		Booklist = booklist;
		this.borrowingBookslist = borrowingBookslist;
	}

	public PageVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PageVo [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", Booklist="
				+ Booklist + ", borrowingBookslist=" + borrowingBookslist + "]";
	}	

	public List<Book> getBooklist() {
		return Booklist;
	}

	public void setBooklist(List<Book> booklist) {
		Booklist = booklist;
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
