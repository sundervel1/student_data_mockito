package com.cg.boot.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Set;
//import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.dto.CreateStudentRequest;
//import com.cg.boot.dto.DateForJson;
import com.cg.boot.dto.StudentDetails;
import com.cg.boot.dto.UserDetails;
import com.cg.boot.entity.Course;
import com.cg.boot.entity.Student;
//import com.cg.boot.exception.NotLoggedInException;
//import com.fasterxml.jackson.annotation.JsonFormat;
import com.cg.boot.service.StudentService;
import com.cg.boot.util.StudentUtil;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studService;
	@Autowired
	private StudentUtil studUtil;
	
	@DeleteMapping("/delete/{id}")
	public StudentDetails deleteStudent(@PathVariable("id") Integer id, 
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		Student stud = studService.deleteById(id);
		StudentDetails details = studUtil.toDetails(stud);
		return details;
	}
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentDetails addStudent(@RequestBody @Valid CreateStudentRequest requestData, 
			  HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		System.out.println("requestData: " + requestData);
		Student stud = new Student(requestData.getFirstName(), 
				requestData.getLastName(), requestData.getAge(),requestData.getDob());
		Set<Course> courseSet = requestData.getCourses();
		if(courseSet!=null) {
			for (Course course : courseSet) {
				stud.addCourse(course);
			}
		}
		Student studSaved = studService.addStudent(stud);
		StudentDetails details = studUtil.toDetails(studSaved);
		return details;
	}
	@GetMapping("/findbyid/{id}")
	public StudentDetails findById(@PathVariable("id") Integer id, 
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		Student stud = studService.findById(id);
		StudentDetails details = studUtil.toDetails(stud);
		return details;
	}
	@GetMapping("/all")
	public List<StudentDetails> fetchAll(/* @RequestBody DateForJson date, */ HttpServletRequest request){
		HttpSession session = request.getSession();
//		System.out.println(date);
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		List<Student> students = studService.findAll();
		List<StudentDetails> allStudents = studUtil.toDetails(students);
		return allStudents;
	}
	@GetMapping("/findbyname/{name}")
	public List<StudentDetails> fetchStudentByName(@PathVariable("name") String name,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		System.out.println("cntrlr fetch name: " + name);
		List<Student> students = studService.findByName(name);
		List<StudentDetails> response = studUtil.toDetails(students);
		System.out.println("by name details: " + response);
		return response;
	}

	@GetMapping("/findbyname/{fname}/{lname}")
	public List<StudentDetails> fetchStudentByFullName(@PathVariable("fname") String fname,
			@PathVariable("lname") String lname,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		System.out.println("cntrlr fetch name: " + fname + " " + lname);
		 List<Student> students = studService.findByFullName(fname,lname);
		//List<Student> students = studService.findByFirstNameAndLastName(fname, lname);
		List<StudentDetails> response = studUtil.toDetails(students);
		System.out.println("by name details: " + response);
		return response;
	}	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public StudentDetails updateStudent(@RequestBody Student stud,
			HttpServletRequest request) {
		System.out.println("update: " + stud);
		HttpSession session = request.getSession();
		String uName = (String) session.getAttribute("user");
		if(uName==null) {
//			throw new NotLoggedInException("You have not logged in.");
		}
		// JSON does not have student in course. so we have to add it
		Set<Course> courseSet = stud.getCourses(); // check if course exists
		if(courseSet!=null && courseSet.size()!=0) {
			for (Course course : courseSet) {
				course.setStudent(stud); // add stud reference into each course
			}
		}
		Student studUpdated = studService.updateStudent(stud);
		StudentDetails details = studUtil.toDetails(studUpdated);
		return details;
	}
	@PostMapping("/login")
	public String login(@RequestBody UserDetails userDetails, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String role = studService.login(userDetails);
		session.setAttribute("user", userDetails.getUsername());
		session.setAttribute("role", role);
		return "You have successfully logged in as: "+role;
	}
	@PostMapping("/logout")
	public String logout(@RequestBody UserDetails userDetails, 
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> attrNames = session.getAttributeNames();
		while(attrNames.hasMoreElements()) {
			String atName = attrNames.nextElement();
			String uName = (String) session.getAttribute(atName);
			if(uName.equals(userDetails.getUsername())) {
				session.invalidate();
				return "You have successfully logged out "+userDetails.getUsername();
			}
		}
		return "You have not logged in as: "+userDetails.getUsername();
		
	}
}
