package com.testdatabase.testdbauto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testdatabase.testdbauto.entity.Student;
import com.testdatabase.testdbauto.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Student> findAll() {
		
		
		return studentRepo.findAll();
	}

	@Override
	public Student findById(int theId) {
		Optional<Student> result =  studentRepo.findById(theId);
		
		Student theStudent = null;
		
		if(result.isPresent()) {
			
			theStudent = result.get();
		}else {
			throw new RuntimeException("Employee with Id not found - "+theId);
		}
		
		return theStudent;
	}

	@Override
	public void save(Student theStudent) {

		studentRepo.save(theStudent);
	}

	@Override
	public void deleteById(int theId) {

		studentRepo.deleteById(theId);
		
	}

}
