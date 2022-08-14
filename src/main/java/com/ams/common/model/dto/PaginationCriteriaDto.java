package com.ams.common.model.dto;

public class PaginationCriteriaDto {
	private int page;		//현재 페이지 번호
	private int perPageNum; //페이지당 보여줄 게시글 개수 
	
	public int getPageStart() {
		//특정 페이지의 범위를 정하는 구간
		return (this.page -1) * perPageNum; 
	}
	
	public PaginationCriteriaDto() {
		//페이지 기준값 생성 10페이지가 기본
		this.page = 0;
		this.perPageNum = 10;
	}
	
	public PaginationCriteriaDto(int page, int perPageNum) {
		this.page = page;
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page <=0) {
			this.page = 0;
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
