package com.cg.boot.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "student_boot")
@NamedQuery(name="Student.findByFirstNameAndLastName",
query="SELECT stud FROM Student stud WHERE stud.firstName = ?1 AND stud.lastName = ?2")
public class Student {
	 @Id
	 @GeneratedValue
     private Integer id;
     private String firstName;
     private String lastName;
     private int age;
     @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
     private Set<Course> courses = new HashSet<>();
     @JsonFormat(pattern = "yyyy-MM-dd")
     private LocalDate dob;
	public Student() {
	}
	public Student(Integer id, String firstName, String lastName, int age, Set<Course> courses) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.courses = courses;
	}
	
	public Student(String firstName, String lastName, int age, LocalDate dob) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.courses = courses;
		this.dob = dob;
	}
	public Student(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	public void addCourse(Course course) {
		course.setStudent(this);
		courses.add(course);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", courses=" + courses + ", dob=" + dob + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
