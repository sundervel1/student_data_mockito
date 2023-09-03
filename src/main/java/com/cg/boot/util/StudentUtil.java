package com.cg.boot.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.cg.boot.dto.CourseDetails;
import com.cg.boot.dto.StudentDetails;
import com.cg.boot.entity.Course;
import com.cg.boot.entity.Student;

@Component
public class StudentUtil {

	public StudentDetails toDetails(Student stud) {
		Set<CourseDetails> details = new HashSet<>();
		Set<Course> courses = stud.getCourses();
		for (Course course : courses) {
			details.add(new CourseDetails(course));
		}
		return new StudentDetails(stud.getId(), stud.getFirstName(), stud.getLastName(), 
				stud.getAge(), details);
	}

	public List<StudentDetails> toDetails(List<Student> students) {
		List<StudentDetails> detailsList = new ArrayList<>();
		for (Student student : students) {
			StudentDetails details = toDetails(student);
			detailsList.add(details);
		}		
		return detailsList;
	}

}
