package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customers {

	private int cusId;
	private String cusName;
	private String courseName;
	@Autowired
	private Technologies techDetails;

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Technologies getTechDetails() {
		return techDetails;
	}

	public void setTechDetails(Technologies techDetails) {
		this.techDetails = techDetails;
	}

	public void display() {
		System.out.println("Customers object returned succesfully...!");
		techDetails.tech();
	}

}
