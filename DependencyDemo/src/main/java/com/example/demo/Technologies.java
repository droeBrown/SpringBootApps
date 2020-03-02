package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Technologies {
	private int techId;
	private String techName;

	public int getTechId() {
		return techId;
	}

	public void setTechId(int paramstTechId) {
		this.techId = paramstTechId;
	}

	public String getTechName() {
		return techName;
	}

	public void setTechName(String paramsTechName) {
		this.techName = paramsTechName;
	}

	public void tech() {
		System.out.println("Completed tech");
	}

}
