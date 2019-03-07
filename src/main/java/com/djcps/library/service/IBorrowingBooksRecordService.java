package com.djcps.library.service;

import com.djcps.library.model.vo.PageVo;

/**
 * @author djsxs
 *
 */
public interface IBorrowingBooksRecordService {

	/**根据页码查询所有借书记录
	 * @param pageNum
	 * @return
	 */
	PageVo selectAllBorrowingRecord(int pageNum);

}
