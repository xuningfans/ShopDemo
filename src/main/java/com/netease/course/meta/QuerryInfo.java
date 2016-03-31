package com.netease.course.meta;

/**
 * 分页查询信息
 *
 * @author 公猴脖子男
 */
public class QuerryInfo {

	/** 当前页 */
	private Integer currentPage = 1;
	/** 每页大小 */
	private Integer pageSize = 4;
	/** 起始页 */
	private Integer startIndex;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartIndex() {
		startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}
}
