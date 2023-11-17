package com.ssafy.updown.model.dto;

public class Img {
	
	private String img;
	private String orgImg;
	
	public Img() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Img(String img, String orgImg) {
		super();
		this.img = img;
		this.orgImg = orgImg;
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
		return "Img [img=" + img + ", orgImg=" + orgImg + "]";
	}

	
}
