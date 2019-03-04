package com.djcps.library.service;

import com.djcps.library.model.Vo.PageVo;

public interface IBorrowingBooksRecordService {

	PageVo selectAllBorrowingRecord(int pageNum);

}
