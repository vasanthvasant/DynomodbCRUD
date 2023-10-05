package com.spring.project.dynomodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dynomodb.entity.Student;
import com.spring.project.dynomodb.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
    public ValidateInput validateInput;
	Message emailMessage = new Message("003","invalid email");
	Message mobileMessage = new Message("002","invalid Mobile number");
	Message snameMessage = new Message("001","number not allowed in name");
	@ResponseBody
	@PostMapping("/student")
	public Object saveStudent(@RequestBody Student student) {
		if(!validateInput.validMail(student.getEmail())) {
			 //studentRepository.save(student);
			 return emailMessage;
		}
		else if(!validateInput.validateMobile(student.getMobile())) {
			return mobileMessage;
		}
		else if(validateInput.validateSname(student.getSname())) {
			return snameMessage;
		}
		else {
			studentRepository.save(student);
			return "document sucessfully added";
		}
		
	}
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable("id") String studentId) {
		return studentRepository.getStudentById(studentId);
	}
	@DeleteMapping("/student/{id}")	
	public String deleteStudent(@PathVariable("id") String studentId,@RequestBody Student student) {
		return studentRepository.delete(studentId);
	}
	
	@PutMapping("/student/{id}")
	public String updateStudent(@PathVariable("id") String studentId,@RequestBody Student student) {
		return studentRepository.update(studentId, student);
	}

}
