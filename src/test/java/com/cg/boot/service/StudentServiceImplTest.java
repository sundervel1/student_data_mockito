package com.cg.boot.service;

import javax.persistence.EntityManager;

import com.cg.boot.service.StudentService;
import com.cg.boot.service.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.boot.entity.Student;

@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(StudentServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceImplTest {
	@Autowired
	private StudentService service;
	@Autowired
	private EntityManager em;
	
	@Test
	public void testFindById() {
		Student stud = new Student("Anuhya", "Muppavarapu", 22);
		em.persist(stud);
		Integer id = stud.getId();
		Student studFound = service.findById(id);
		Assertions.assertEquals(studFound.getFirstName(), "Anuhya");		
	}
	@Test
	public void testAddStudent() {
		Student stud = new Student("Pavani", "Mudiyala", 21);
		Student studSaved = service.addStudent(stud);
		Assertions.assertEquals(studSaved.getFirstName(), stud.getFirstName());
	}
}
