package com.przemo.RestAPI.aspect.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.przemo.RestAPI.RestApiApplication;
import com.przemo.RestAPI.controllers.StudentController;
import com.przemo.RestAPI.entity.classes.Klasa;
import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.entity.user.Admin;
import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.exception.ObjectNotFoundException;
import com.przemo.RestAPI.repository.StudentRepository;
import com.przemo.RestAPI.repository.parent.GradeRepository;
import com.przemo.RestAPI.repository.service.GradeService;
import com.przemo.RestAPI.repository.service.StudentService;
import com.przemo.RestAPI.repository.service.StudentServiceImpl;
import com.przemo.RestAPI.security.config.UserPrincipal;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;


@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest(StudentController.class)
@WebAppConfiguration
public class TestStudentController 
{
	@Autowired MockMvc mockMvc;	

	@Autowired
	@MockBean
	StudentService studentService;
		
	@Test
	@WithMockUser(roles={"Admin"})
	public void deleteUser() throws Exception {
	   String uri = "/students/72";
	  
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	}
	
	@Test
	@WithMockUser(roles={"Admin"})
	public void deleteUserDontExist() throws Exception {
	 
		String uri = "/students/72";
	    doThrow(EmptyResultDataAccessException.class)
	    	.when(studentService)
	    	.deleteStudent(72);

	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   MockHttpServletResponse response = mvcResult.getResponse();

	   final int HTTPStatus = 404;
	   
	   assertEquals(HTTPStatus, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Admin"})
	public void deleteUserBadPathVariable() throws Exception {
	 
	   String uri = "/students/kk";

	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   MockHttpServletResponse response = mvcResult.getResponse();

	   final int HTTPStatus = 404;
	   
	   assertEquals(HTTPStatus, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Teacher"})
	public void createStudentTestShloudBeValidAndCreate() throws Exception
	{
		//given
		Student studentPost = new Student();
		studentPost.setFirstname("firstname");
		studentPost.setLastname("lastname");
		studentPost.setPassword("123");
		studentPost.setUser_id(1);

		String inputJson = this.mapToJson(studentPost);
		
		//because of @Valid and @JsonIgnore annotations we have to add manual field "username" to input
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(inputJson, JsonObject.class); // parse
		jsonObject.addProperty("username", "username"); // modify
		
		inputJson = jsonObject.toString();
		
		String uri = "/students";
		
		//when
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(studentPost);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		//then			   
		MockHttpServletResponse response = mvcResult.getResponse();
		String outputJson = response.getContentAsString();
		//because of @Valid and @JsonIgnore annotations we have to add manual field "username" to output
		gson = new Gson();
		jsonObject = gson.fromJson(outputJson, JsonObject.class); // parse
		jsonObject.addProperty("username", "username");
				
		outputJson = jsonObject.toString();
			 
		assertTrue(outputJson.equals(inputJson));
	}
	
	@Test
	@WithMockUser(roles={"Teacher"})
	public void createStudentTestShloudBeNotValidBadRequest() throws Exception
	{
		//given
		Student studentPost = new Student();
		studentPost.setFirstname("");
		studentPost.setLastname("");
		studentPost.setPassword("");
		studentPost.setUser_id(2);

		String inputJson = this.mapToJson(studentPost);
	
		String uri = "/students";
		
		//when
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(studentPost);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		//then
			   
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		final int BadRequest = 400;
		assertEquals(BadRequest, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Admin","Student"})
	public void createStudentTestShloudBeBadAuthentication() throws Exception
	{
		//given
		String inputJson = this.mapToJson(new Student());
		String uri = "/students";
		
		//when
		//when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(studentPost);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		//then  
		MockHttpServletResponse response = mvcResult.getResponse();
		
		final int ForBidden = 403;
		assertEquals(ForBidden, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Admin","Student"})
	public void updateStudentTestShloudBeBadAuthentication() throws Exception
	{
		//given
		String inputJson = this.mapToJson(new Student());
		String uri = "/students/7";
		
		//when
		//when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(studentPost);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		//then  
		MockHttpServletResponse response = mvcResult.getResponse();
		
		final int ForBidden = 403;
		assertEquals(ForBidden, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Teacher"})
	public void updateStudentTestShloudBeValidAndCreate() throws Exception
	{
		//given
		Student studentPost = new Student();
		studentPost.setFirstname("firstname");
		studentPost.setLastname("lastname");
		studentPost.setPassword("123");
		studentPost.setUser_id(1);

		String inputJson = this.mapToJson(studentPost);
		
		//because of @Valid and @JsonIgnore annotations we have to add manual field "username" to input
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(inputJson, JsonObject.class); // parse
		jsonObject.addProperty("username", "username"); // modify
		
		inputJson = jsonObject.toString();
		
		String uri = "/students";
		
		//when
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(studentPost);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
			      .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
		//then			   
		MockHttpServletResponse response = mvcResult.getResponse();
		String outputJson = response.getContentAsString();
		//because of @Valid and @JsonIgnore annotations we have to add manual field "username" to output
		gson = new Gson();
		jsonObject = gson.fromJson(outputJson, JsonObject.class); // parse
		jsonObject.addProperty("username", "username");
				
		outputJson = jsonObject.toString();
			 
		assertTrue(outputJson.equals(inputJson));
	}
	
	@Test
	@WithMockUser(roles={"Teacher","Admin"})
	public void getStudentsSouldBeBadAuthentication() throws Exception
	{
		//given
		String uri = "/students";
		
		//when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int ForBidden = 403;
		assertEquals(ForBidden, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Student"})
	public void getStudentsSouldReturnStudentList() throws Exception
	{
		//given
		String uri = "/students";
		
		//when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int isOk = 200;
		assertEquals(isOk, response.getStatus());
	}
	  private String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
}
