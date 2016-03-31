package com.netease.course.meta;

import java.util.List;

public class QuerryResult<T> {

	private List<T> list;
	private Integer rotalRecord;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Integer getRotalRecord() {
		return rotalRecord;
	}

	public void setRotalRecord(Integer rotalRecord) {
		this.rotalRecord = rotalRecord;
	}

}
