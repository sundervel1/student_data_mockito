package com.cg.boot.service;

import java.util.List;

import com.cg.boot.dto.UserDetails;
import com.cg.boot.entity.Student;

public interface StudentService {

	Student addStudent(Student stud);

	Student findById(Integer id);

	List<Student> findAll();

	public String login(UserDetails userDetails);

	List<Student> findByName(String name);

	List<Student> findByFullName(String fname, String lname);

	List<Student> findByFirstNameAndLastName(String fname, String lname);

	Student updateStudent(Student stud);

	Student deleteById(Integer id);
}
