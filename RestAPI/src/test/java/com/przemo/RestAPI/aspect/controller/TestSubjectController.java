package com.przemo.RestAPI.aspect.controller;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.przemo.RestAPI.controllers.StudentController;
import com.przemo.RestAPI.controllers.SubjectController;
import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.subjects.Mathematics;
import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.exception.ObjectNotFoundException;
import com.przemo.RestAPI.repository.service.SubjectService;

@RunWith(SpringRunner.class)
@ContextConfiguration
@WebMvcTest(SubjectController.class)
@WebAppConfiguration
public class TestSubjectController 
{

	@Autowired MockMvc mockMvc;	

	@Autowired
	@MockBean
	private SubjectService subjectService;
	
	
	@Test
	@WithMockUser(roles={"Teacher","Admin","Student"})
	public void getStudentsSouldReturnSubjecyList() throws Exception
	{
		//given
		String uri = "/subjects";
		
		//when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int isOk = 200;
		assertEquals(isOk, response.getStatus());
	}
	
	@Test
	public void getStudentsSouldNotBeAuthorizated() throws Exception
	{
		//given
		String uri = "/subjects";
		
		//when
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int NotAuthrizated = 401;
		assertEquals(NotAuthrizated, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Teacher","Admin","Student"})
	public void getStudentsSouldReturnSubject() throws Exception
	{
		//given
		String uri = "/subjects/8";
		Mathematics mat = new Mathematics();
		mat.setStudentsList(new ArrayList<Student>());
		mat.setSubject_id(8);
		Optional<Subject> subject = Optional.of(mat);
		
		when(subjectService.getSubjectById(anyInt())).thenReturn(subject);

		//when
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int isOk = 200;
		assertEquals(isOk, response.getStatus());
	}
	
	@Test
	@WithMockUser(roles={"Teacher","Admin","Student"})
	public void getStudentsSouldReturnNotFound() throws Exception
	{
		//given
		String uri = "/subjects/4";
		
		//when
		doThrow(ObjectNotFoundException.class)
    	.when(subjectService)
    	.getSubjectById(4);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		//then
		final int NotFound = 404;
		assertEquals(NotFound, response.getStatus());
	}
}
