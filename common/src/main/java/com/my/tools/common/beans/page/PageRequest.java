package com.my.tools.common.beans.page;

import com.my.tools.common.beans.BaseBean;

/**
 * 统一的分页请求对象 描述：
 * 
 * @author ping.huang 2016年1月25日
 */
public class PageRequest extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4026346032261456086L;

	/**
	 * 当前页数
	 */
	private int page = 1;
	
	/**
	 * 每页条数
	 */
	private int rows = 10;
	
	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * 排序方式
	 */
	private String order = "ASC";

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
}
