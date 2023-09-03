package com.cg.boot.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.dao.LoginDao;
import com.cg.boot.dao.StudentDao;
import com.cg.boot.dto.UserDetails;
import com.cg.boot.entity.Student;
import com.cg.boot.exception.AuthenticationFailedException;
import com.cg.boot.exception.StudentNotFoundException;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studDao;
	@Autowired
	private LoginDao loginDao; 
	
	private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Override
	public Student addStudent(Student stud) {
		logger.info("Adding :" +stud);
		validate(stud); // for mock test
		Student studentSaved = studDao.save(stud);
		System.out.println(studDao.getClass().getName());
		System.out.println("saved: " + studentSaved);
		return studentSaved;
	}
	
	public void validate(Student stud) {
		System.out.println("validate:"+stud);
	}

	@Override
	public Student findById(Integer id) {
		logger.info("find by id :" + id);
		Optional<Student> opt = studDao.findById(id);
		// studDao.findById(id); // for whitebox testing
		if(!opt.isPresent()) {
			logger.error("Student not found for id: "+id);
			throw new StudentNotFoundException("Student not found for id: "+id);
		}
		return opt.get();
	}

	@Override
	public List<Student> findAll() {
		List<Student> studList = studDao.findAll();
		if(studList==null || studList.size()==0) {
			logger.error("Error No data found for Students");
			throw new StudentNotFoundException("No data found for Students");
		}
		logger.info("find all: " + studList);
		Collections.sort(studList, (s1,s2)->s2.getId()-s1.getId());
		return studList;
	}
	public String login(UserDetails userDetails) {
		Optional<UserDetails> opt = loginDao.findById(userDetails.getUsername());
		if(!opt.isPresent()) {
			logger.error("login user: " + userDetails.getUsername());
			throw new AuthenticationFailedException
			    ("No User found for username: " + userDetails.getUsername());
		}
		UserDetails uDetails = opt.get();
		if(!userDetails.getPassword().equals(uDetails.getPassword())) {
			logger.error("login failed");
			throw new AuthenticationFailedException
		    ("Authentication failed for username: " + userDetails.getUsername());
		}
		return uDetails.getUserRole();
	}

	@Override
	public List<Student> findByName(String name) {
		return studDao.findByFirstName(name);
	}

	@Override
	public List<Student> findByFullName(String fname, String lname) {
		return studDao.findByFullName(fname, lname);
	}

	@Override
	public List<Student> findByFirstNameAndLastName(String fname, String lname) {
		return studDao.findByFirstNameAndLastName(fname, lname);
	}

	@Override
	public Student updateStudent(Student stud) {
		return studDao.save(stud);
	}

	@Override
	public Student deleteById(Integer id) {
		logger.info("delete by id :" + id);
		Student stud = findById(id);
		if(stud==null) {
			logger.error("delete failed, id: "+id);
			return null;
		}
		studDao.deleteById(id);
		return stud;
	}
	
}
