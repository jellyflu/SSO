package com.tingcream.ssoServer.common;

import java.io.Serializable;

/**
 * 分页对象
 * @author jelly
 *
 */
public class Page implements Serializable{
    /**
	 * 
	 */
	 private static final long serialVersionUID = -3978025756754573054L;
	 private  Integer  currentPage=1 ;//当前页码
	 private Integer  pageSize=10;//每页条数 默认为10 
	 private String sortName;//排序字段
	 private String sortOrder="asc";// 排序规则 asc | desc 默认为asc正序
	 
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Page() {
		super();
	}
	public Page(Integer currentPage, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public  int getStartNum(){
		return   (currentPage-1)*pageSize;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	 
	 
}