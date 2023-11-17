package com.ssafy.updown.model.dto;

public class Weight {
	private int no;
	private int nowWeight;
	private String regDate;
	private String userId;
	
	public Weight() {
	}

	public Weight(int no, int nowWeight, String regDate, String userId) {
		this.no = no;
		this.nowWeight = nowWeight;
		this.regDate = regDate;
		this.userId = userId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getNowWeight() {
		return nowWeight;
	}

	public void setNowWeight(int nowWeight) {
		this.nowWeight = nowWeight;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Weight [no=" + no + ", nowWeight=" + nowWeight + ", regDate=" + regDate + ", userId=" + userId + "]";
	}
	
}
