package com.ssafy.updown.model.dto;

public class ExerciseInfo {
	private String type;
	private float met;
	
	public ExerciseInfo() {
	}

	public ExerciseInfo(String type, float met) {
		this.type = type;
		this.met = met;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getMet() {
		return met;
	}

	public void setMet(float met) {
		this.met = met;
	}

	@Override
	public String toString() {
		return "ExerciseInfo [type=" + type + ", met=" + met + "]";
	}

}
