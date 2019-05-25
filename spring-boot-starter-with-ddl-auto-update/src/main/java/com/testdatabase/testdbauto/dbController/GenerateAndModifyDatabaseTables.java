package com.testdatabase.testdbauto.dbController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.testdatabase.testdbauto.entity.Student;
import com.testdatabase.testdbauto.service.StudentService;

@Controller
@RequestMapping("/")
public class GenerateAndModifyDatabaseTables {
	
	@Autowired
	public StudentService st;
	
	@GetMapping("/dataMod")
	public String dataModify() {
		
		Student theStudent = new Student();
		theStudent.setName("Vijay");
		
		st.save(theStudent);
		
		
		return null;
	}

}
