package com.testdatabase.testdbauto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testdatabase.testdbauto.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
