package com.cg.boot.dto;

import com.cg.boot.entity.Course;

public class CourseDetails {
	private Integer id;
	private String name;
	private double fees;
	public CourseDetails() {
	}
	public CourseDetails(Integer id, String name, double fees) {
		super();
		this.id = id;
		this.name = name;
		this.fees = fees;
	}
	public CourseDetails(Course course) {
		this.id = course.getId();
		this.name = course.getName();
		this.fees = course.getFees();
	}
	@Override
	public String toString() {
		return "CourseDetails [id=" + id + ", name=" + name + ", fees=" + fees + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}

}
