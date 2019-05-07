package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.user.Student;

public interface SubjectService 
{
	public List<Subject> getAllSubjects();
	
	public Optional<Subject> getSubjectById(int id);
	
	public Subject saveSubject(Subject subject);
	
	public Subject updateSubject(Subject subject);
	
	public void deleteSubject(int id);

}
