package com.przemo.RestAPI.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.exception.ApiError;
import com.przemo.RestAPI.exception.ObjectNotFoundException;
import com.przemo.RestAPI.exception.globalControllerAdvice.GlobalExceptionHandler;
import com.przemo.RestAPI.repository.StudentRepository;
import com.przemo.RestAPI.repository.parent.GradeRepository;
import com.przemo.RestAPI.repository.service.GradeService;
import com.przemo.RestAPI.repository.service.StudentService;

@RestController
public class StudentController 
{
	@Autowired
	StudentService studentService;
	
	@Autowired
	GradeService gradeService;
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		List<Student> studentsList = preapreStundetsToJson(studentService.getAllStudents());
		return studentsList ;
	}
	
	@GetMapping("/students/{id}")
	Student getOne(@PathVariable int id) throws ObjectNotFoundException
	{
		Student student = studentService.getStudentById(id).orElseThrow(() -> new ObjectNotFoundException(id));
		
		student.getGradesList().forEach((grade) -> {grade.setStudent(null);});
		student.getSubjectsList().forEach((subject) -> {subject.setStudentsList(null);});
		
		return student;
	}
	
	@GetMapping("/students/{id_student}/{subject_id}")
	Grade grades(@PathVariable int id_student,@PathVariable int subject_id) throws ObjectNotFoundException 
	{
		Grade grade = gradeService.findParticularSubjectGrade(subject_id,id_student).orElseThrow(() -> new ObjectNotFoundException(id_student,subject_id));
		grade.getStudent().setGradesList(null);
		grade.getStudent().setSubjectsList(null);
		
		return grade;
	}
	
	@PostMapping("/students")
	public Student addStudent(@Valid @RequestBody Student newStudent)
	{	
		return studentService.saveStudent(newStudent);
	}
	
	@PutMapping("/students/{id_student}")
	public Student updateStudent(@PathVariable int id_student,@Valid @RequestBody Student newStudent)
	{
		Student student = newStudent;
		student.setUser_id(id_student);
		return studentService.saveStudent(newStudent);
	}
	
	@DeleteMapping("/students/{id_student}")
	public void deleteStudent(@PathVariable int id_student)
	{
		try{
			studentService.deleteStudent(id_student);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id_student);
		}
	}
	
	
	public List<Student> preapreStundetsToJson(List<Student> studentsList)
	{
		for(Student student: studentsList)
		{
			student.getSubjectsList().forEach((subject) -> {subject.setStudentsList(null);});

			student.getGradesList().forEach((grade) -> {grade.setStudent(null);});
		}
		return studentsList;
	}
}
