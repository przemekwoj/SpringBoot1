package com.przemo.RestAPI;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.przemo.RestAPI.repository.StudentRepository;

//@RunWith(SpringRunner.class)
@WebMvcTest
public class RestApiApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void contextLoads() throws Exception {
		when(studentRepository.findAll()).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/students").accept(MediaType.APPLICATION_JSON)).andReturn();
	}

}
