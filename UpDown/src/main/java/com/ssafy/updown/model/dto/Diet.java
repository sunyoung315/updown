package com.ssafy.updown.model.dto;

public class Diet {
	private int no;
	private String category;
	private String food;
	private int calorie;
	private String regDate;
	private String userId;
	
	public Diet() {
	}
	
	public Diet(int no, String category, String food, int calorie, String regDate, String userId) {
		this.no = no;
		this.category = category;
		this.food = food;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
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
		return "Diet [no=" + no + ", category=" + category + ", food=" + food + ", calorie=" + calorie + ", regDate="
				+ regDate + ", userId=" + userId + "]";
	}
	
}