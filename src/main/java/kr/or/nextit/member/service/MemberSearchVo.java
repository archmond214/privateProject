package kr.or.nextit.member.service;

import org.apache.commons.lang3.builder.ToStringBuilder;

import kr.or.nextit.com.cmm.PagingVo;

@SuppressWarnings("serial")
public class MemberSearchVo extends PagingVo {

	private String searchType;
	private String searchText;
	private String seqNo;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

}
