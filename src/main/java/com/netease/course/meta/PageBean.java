package com.netease.course.meta;

import java.util.List;

/**
 * 页面对象
 * 
 * @param <T>
 *
 * @author 公猴脖子男
 */
public class PageBean<T> {

	/** 分页数据 */
	private List<T> list;
	/** 总记录数 */
	private Integer totalRecord;
	/** 每页记录数 */
	private Integer pageSize;
	/** 总页数 */
	private Integer totalPage;
	/** 当前页 */
	private Integer currentPage;
	/** 上一页 */
	private Integer previousPage;
	/** 下一页 */
	private Integer nextPage;
	/** 页码条 */
	private Integer[] pageBar;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getTotalPage() {
		if (totalRecord % pageSize == 0) {
			totalPage = totalRecord / pageSize;
		} else {
			totalPage = totalRecord / pageSize + 1;
		}
		return totalPage;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPreviousPage() {
		if (currentPage - 1 > 1) {
			previousPage = currentPage - 1;
		} else {
			previousPage = 1;
		}
		return previousPage;
	}

	public Integer getNextPage() {
		if (currentPage + 1 > totalPage) {
			nextPage = totalPage;
		} else {
			nextPage = currentPage + 1;
		}
		return nextPage;
	}

	public Integer[] getPageBar() {

		int startPage;
		int endPage;
		if (totalPage <= 10) {
			startPage = 1;
			endPage = totalPage;
		} else {
			startPage = currentPage - 4;
			endPage = currentPage + 5;
			if (startPage < 1) {
				startPage = 1;
				endPage = 10;
			}
			if (endPage > totalPage) {
				endPage = totalPage;
				startPage = totalPage - 9;
			}
		}
		
		pageBar = new Integer[endPage-startPage+1];
		int index = 0;
		for (int i = startPage; i <= endPage; i++) {
			pageBar[index++] = i;
		}
		return pageBar;
	}
/*
	public static void main(String[] args) {
		PageBean<?> pageBean = new PageBean<>();
		pageBean.setPageSize(5);
		pageBean.setTotalRecord(30);
		pageBean.setCurrentPage(1);
		pageBean.getTotalPage();
		for (Integer page : pageBean.getPageBar()) {
			System.out.println(page);
		}
	}
*/
}
