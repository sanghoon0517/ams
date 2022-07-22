package com.ams.common.model.dto;

public class PaginationDto {
	private int page;
	private int perPageNum;
	
	public int getPageStart() {
		return (this.page -1) * perPageNum; 
	}
	
	public PaginationDto() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public PaginationDto(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <=0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	//페이지당 보여줄 게시글의 개수
	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;
		
		if(perPageNum != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = perPageNum;
		}
	}

	@Override
	public String toString() {
		return "PaginationDto [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
