package com.ams.common.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaginationDto {
	private int totalCount; 	//게시판 전체 데이터 개수
	private int displayPageNum = 10; //게시판 화면에서 한번에 보여질 페이지 번호 개수
	private int startPage; //화면 시작번호
	private int endPage; //화면 끝 번호
	private int finalEndPage; //최종 페이지 끝번호
	private boolean prev; //페이지네이션 이전버튼 활성화
	private boolean next; //페이지네이션 다음버튼 활성화
	
	private PaginationCriteriaDto criteria; //페이지네이션 기준
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paginationData();
	}
	
	public int getFinalEndPage() {
		return this.finalEndPage = (int) Math.ceil(totalCount/(double) displayPageNum);
	}
	
	private void paginationData() {
		
		endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
		//(현재페이지 번호/한 화면에 보여질 페이지 번호 개수) -> (올림처리 후) 현재 보여질 페이지 번호 개수 곱하기
		//우선적으로 마지막 페이지 번호 까지만 계산 ex) 10페이지, 20페이지 30페이지
		
		startPage = (endPage - displayPageNum)+1;
		//끝페이지 번호(10단위) - 화면에 보여질 페이지 번호 개수(10페이지씩) +1
		//11,21,31,41,51,...
		
		//endPage의 실제값 셋팅
		//전체페이지/한 페이지당 보여줄 게시글 개수 -> 올림
		int tmpEndPage = (int) Math.ceil(totalCount / (double) criteria.getPerPageNum());
		//106개 글이 있다고 가정 -> 106/10 -> 10.6 -> 올림 -> 11페이지가 endPage
		if(endPage > tmpEndPage) { //endPage는 10단위로 있기 때문에 tmpPage가 무조건 작거나 같다.
			endPage = tmpEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		// 시작 페이지가 1로 시작하면 이전페이지 버튼 활성화하지 않고, 그 이후(11,21,...)면 이전페이지 버튼 활성화
		
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true; //이부분은 체크해 봐야 할듯...
		//다음버튼 생성 여부
	}

//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "PaginationDto [totalCount= "+totalCount+", displayPageNum= "+displayPageNum+", startPage= "+startPage+", endPage= "+endPage+", prev= "+prev+", next= "+next+", criteria="+criteria+"]";
//	}
	
	
}