package com.cg.boot.service;

import com.cg.boot.dao.LoginDao;
import com.cg.boot.dao.StudentDao;
import com.cg.boot.entity.Student;
import com.cg.boot.exception.StudentNotFoundException;
import com.cg.boot.service.StudentServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplUnitTests {

    @Mock
    StudentDao studentDao;
    @Mock
    LoginDao loginDao;

    @InjectMocks
    @Spy
    StudentServiceImpl service;

//    @BeforeEach
//    public void setUp() {
//    	service = new StudentServiceImpl();
//    	StudentServiceImpl spy = spy(service);
//    }
    /**
     * scenario: Student found by id
     * input id:34, studentDao#findById stubbed returns optional of expectedStudent
     * expectation : expectedStudent
     */
    @Test
    void findById_StudentFound(){
        int id=34;
        Student expectedStudent=mock(Student.class);
        Optional<Student>optional=Optional.of(expectedStudent);
        when(studentDao.findById(id)).thenReturn(optional);
        Student result=service.findById(id);
        assertEquals(expectedStudent,result);
        verify(studentDao).findById(id); // whitebox testing, internal execution is as expected
    }

    /**
     * scenario: Student NOT found by id
     * input id:12, studentDao#findById stubbed returns empty optional
     * expectation : expectedStudent
     */
    @Test
    void findById_StudentNOTFound(){
        int id=12;
        Optional<Student>optional=Optional.empty();
        when(studentDao.findById(id)).thenReturn(optional);
        Executable executable=()->service.findById(id);
        assertThrows(StudentNotFoundException.class,executable);
        verify(studentDao).findById(id);
    }

    /**
     * scenario: student succesfully added
     */
    @Test
    void addStudent_1(){
        Student student=mock(Student.class);
        Student saved=mock(Student.class);
        when(studentDao.save(student)).thenReturn(saved);
        doNothing().when(service).validate(student); // prevent validate
        Student result=service.addStudent(student);
        assertEquals(saved,result);
        verify(studentDao).save(student);
    }

}
