package com.przemo.RestAPI.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.subjects.History;
import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.exception.ObjectNotFoundException;
import com.przemo.RestAPI.repository.parent.SubjectRepository;
import com.przemo.RestAPI.repository.service.SubjectService;

@RestController
public class SubjectController
{
	@Autowired
	private SubjectService subjectService;
	
	
	
	@GetMapping("/subjects")
	public List<Subject> getSubject()
	{
		List<Subject> subjectsList = subjectService.getAllSubjects();
		for(Subject sub: subjectsList)
		{
			List<Subject> subjects = subjectService.getAllSubjects();
			for(Subject s: subjects)
			{
				for(Student st: s.getStudentsList())
						{
						st.setSubjectsList(null);
						st.setGradesList(null);
						}
			}
			
			return subjects;
		}
		return subjectsList;
	}
	
	@GetMapping("/subjects/{id}")
	Subject getOne(@PathVariable int id) throws ObjectNotFoundException
	{
		Subject subject =  subjectService.getSubjectById(id).orElseThrow(() -> new ObjectNotFoundException(id));
		subject.getStudentsList().forEach((student) -> {student.setGradesList(null);student.setSubjectsList(null);});
		return subject;
	}
	
}
