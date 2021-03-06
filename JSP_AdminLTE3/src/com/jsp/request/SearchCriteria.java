package com.jsp.request;

public class SearchCriteria {
	
	private int	page; // 현재 페이지 번호
	private int perPageNum; // 페이지당 보여줄 리스트 갯수
	private String searchType; 
	private String keyword;
	
	public SearchCriteria() {
		this.page = 1;
		this.perPageNum = 10;
		this.searchType = "";
		this.keyword = "";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getPageStartRowNum() { // 각 페이지마다 시작하는 행번호
		return (this.page-1)*perPageNum;
	}
}
