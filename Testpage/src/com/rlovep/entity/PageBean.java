package com.rlovep.entity;

import java.util.List;

public class PageBean<T> {
	private int curpage=1;//当前页
	private int perRow=4;//每页显示的行数
	private int allRow;//总行数
	private int allPage;//总页数
	private List<T> list;//显示的内容
	//当前页
	public int getCurpage() {
		return curpage;
	}
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}
	//每页的行数
	public int getPerRow() {
		return perRow;
	}
	public void setPerRow(int perRow) {
		this.perRow = perRow;
	}
	//总行数
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	//总页数
	public int getAllPage() {
		if((allRow%perRow)==0){
			allPage=allRow/perRow;
		}else
			allPage=allRow/perRow+1;
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	//显示的内容
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	

}
