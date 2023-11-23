package com.ssafy.updown.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String id;
	private String password;
	private String nickname;
	private String name;
	private String email;
	//목표 체중
	private int targetWeight;
	//하루 목표 운동 시간
	private int targetTime; 
	private String img;
	private String orgImg;
	
	public User() {
	}

	public User(String id, String password, String nickname, String name, String email, int targetWeight,
			int targetTime,String img, String orgImg) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.name = name;
		this.email = email;
		this.targetWeight = targetWeight;
		this.targetTime = targetTime;
		this.img = img;
		this.orgImg = orgImg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(int targetWeight) {
		this.targetWeight = targetWeight;
	}

	public int getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}
	

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", nickname=" + nickname + ", name=" + name + ", email="
				+ email + ", targetWeight=" + targetWeight + ", targetTime=" + targetTime + ", img=" + img + ", orgImg="
				+ orgImg + "]";
	}


}
