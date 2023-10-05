package com.spring.project.dynomodb;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.dynomodb.controller.StudentController;
import com.spring.project.dynomodb.entity.Student;
import com.spring.project.dynomodb.repository.StudentRepository;

import ch.qos.logback.core.net.ObjectWriter;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest  {
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = (ObjectWriter) objectMapper.writer();
	
	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentController studentController;
	
	Student record_1 = new Student("1","kumar","kumar@gmail.com","1234567890");
	Student record_2 = new Student("2","rajesh","rajesh@gmail.com","1234567890");
	
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		
	}
	
	@org.junit.jupiter.api.Test
	public void getStudentById() throws Exception {
		String studentId="1";
		Student record_3 = new Student(studentId,"selva","selva@gmail.com","1234567890");
		Mockito.when(studentRepository.getStudentById(record_3.getSid())).thenReturn(record_3);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/student/{id}",studentId))
		.andExpect(status().isOk())
		.andExpect((ResultMatcher) content().contentType("application/json"))
		.andExpect((ResultMatcher) jsonPath("$.sid").value(record_3.getSid()))
		.andExpect((ResultMatcher) jsonPath("$.sname").value(record_3.getSname()))
		.andExpect((ResultMatcher) jsonPath("$.email").value(record_3.getEmail()))
		.andExpect((ResultMatcher) jsonPath("$.mobile").value(record_3.getMobile()));
		
		verify(studentRepository,times(1)).save(any(Student.class));
	}
	

}
