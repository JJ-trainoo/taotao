package com.taotao.portal.pojo;

import java.util.List;

/**
 * 结果集
 * @ClassName: SearchResult 
 * @Description: TODO 
 * @author ZhouTao
 * @date 2016年12月21日 下午5:47:58 
 *
 */
public class SearchResult {

	/**
	 * 总结果集
	 */
	private List<Item> resultList;
	/**
	 * 查询结果总记录数
	 */
	private long totalRecord;
	/**
	 * 查询结果总页数
	 */
	private long totalPage;
	/**
	 * 当前页
	 */
	private long curPage;
	
	
	public List<Item> getResultList() {
		return resultList;
	}
	public void setResultList(List<Item> resultList) {
		this.resultList = resultList;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getCurPage() {
		return curPage;
	}
	public void setCurPage(long curPage) {
		this.curPage = curPage;
	}
}
