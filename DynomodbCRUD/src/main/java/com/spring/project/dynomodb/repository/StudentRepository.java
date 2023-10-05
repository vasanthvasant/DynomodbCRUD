package com.spring.project.dynomodb.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.spring.project.dynomodb.entity.Student;

@Repository
public class StudentRepository {
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public Student save(Student student) {
		dynamoDBMapper.save(student);
		return student;	
	}
	
	public Student getStudentById(String studentId) {
		return dynamoDBMapper.load(Student.class, studentId);
	}
	
	public String delete(String studentId) {
		Student std = dynamoDBMapper.load(Student.class, studentId);
		dynamoDBMapper.delete(std);
		return "Employee Deleted!";
	}
	
	public String update(String studentId, Student student) {
		dynamoDBMapper.save(student);
		return studentId;
	}

}
