package com.team.chat.VO;

//페이징용
public class ChatCriteria {
	private Integer page;
	private Integer perPageNum;
	
	public ChatCriteria() {
		this.page = 1;
		this.perPageNum  = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(Integer page) {
		//페이지가 1보다 작은 수로 입력되면 1로 고정
		this.page = (page < 1)? 1: page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(Integer perPageNum) {
		//perPageNum이 10 미만 또는 100 이상일 경우 10으로 고정한다.
		this.perPageNum = (perPageNum < 10 || perPageNum >= 100) ? 10: perPageNum;
	}
	
	public int getPageStart() {
		return (this.page - 1)*perPageNum;
	}
}
