package com.cg.boot.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cg.boot.entity.Course;

public class CreateStudentRequest {
	@NotBlank @Size(min = 2, max = 20)
    private String firstName;
	@NotBlank @Size(min = 2, max = 20)
    private String lastName;
	@Max(100)
    private int age;
    private Set<Course> courses = new HashSet<>();
    private LocalDate dob;

	@Override
	public String toString() {
		return "CreateStudentRequest [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", courses="
				+ courses + ", dob=" + dob + "]";
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
    
    
}
