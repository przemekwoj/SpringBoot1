package com.przemo.RestAPI.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.przemo.RestAPI.entity.classes.Klasa;
import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.parent.User;
import com.przemo.RestAPI.entity.subjects.History;
import com.przemo.RestAPI.entity.subjects.Mathematics;
import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.entity.user.Teacher;
import com.przemo.RestAPI.repository.AdminRepository;
import com.przemo.RestAPI.repository.KlasaRepository;
import com.przemo.RestAPI.repository.NoteRepository;
import com.przemo.RestAPI.repository.StudentRepository;
import com.przemo.RestAPI.repository.SubjectRepository;
import com.przemo.RestAPI.repository.TeacherRepository;
import com.przemo.RestAPI.repository.UserRepository;
import javafx.print.Collation;

@RestController
public class TestController {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	KlasaRepository klasaRepository;
	
	//@Autowired
	//GradeRepository gradeRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	TeacherRepository teacherRepository;
	
	
	@GetMapping("/registration")
	public void registration()
	{
		System.out.println("registration");
	}
	
	@GetMapping("/test")
	public List<User> jsp()
	{
		/*System.out.println("test");
		Student entity = new Student();
		entity.setFirstname("przemonew");
		entity.setLastname("emonew");
		entity.setPassword("123new");*/
		/*Optional<Subject> history = subjectRepository.findById(1);
		entity.addSubject(history.get());
		Grade grade = new Grade();
		grade.setGrades("5,3");
		entity.addGrade(grade);*/
		//userRepository.save(entity);
		/*History history = new History();
		subjectRepository.save(history);*/
		/*Klasa klasa =  new Klasa();
		klasa.setNameKlasa("D");
		klasaRepository.save(klasa);*/
		/*Teacher t = new Teacher();
		t.setFirstname("nau");
		t.setLastname("cyzciel");
		t.setPassword("123");
		teacherRepository.save(t);*/
		//Optional<Student> s = studentRepository.findById(1);
		//return userRepository.findAll();
		
		//Student s = studentRepository.findById(1).get();
		
		/*Subject sub = subjectRepository.findById(1).get();
		
		s.addSubject(sub);
		
		
		studentRepository.save(s);*/
		
		//System.out.println(s.getUser_id() + " asd  "+s.getSubjectsList().toString());
		
		
	//	subjectRepository.delete(subjectRepository.findById(1).get());
	//	subjectRepository.delete(subjectRepository.findById(2).get());

		List<Integer> list = new ArrayList<Integer>();
		
		///Collections.sort
		
		return userRepository.findAll();
	}
}
