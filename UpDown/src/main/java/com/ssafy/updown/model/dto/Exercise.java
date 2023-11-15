package com.ssafy.updown.model.dto;

public class Exercise {
	private int no;
	private String type;
	private int time;
	private int calorie;
	private String regDate;
	private String userId;
	
	public Exercise() {
	}

	public Exercise(int no, String type, int time, int calorie, String regDate, String userId) {
		this.no = no;
		this.type = type;
		this.time = time;
		this.calorie = calorie;
		this.regDate = regDate;
		this.userId = userId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
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
		return "Exercise [no=" + no + ", type=" + type + ", time=" + time + ", calorie=" + calorie + ", regDate="
				+ regDate + ", userId=" + userId + "]";
	}
	
}
