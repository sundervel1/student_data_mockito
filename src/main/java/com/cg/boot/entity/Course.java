package com.cg.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_boot")
public class Course {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private double fees;
	@ManyToOne
	@JoinColumn(name = "stud_id")
	private Student student;
	public Course() {
	}
	public Course(Integer id, String name, double fees, Student student) {
		super();
		this.id = id;
		this.name = name;
		this.fees = fees;
		this.student = student;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", fees=" + fees + "]";
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
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}
