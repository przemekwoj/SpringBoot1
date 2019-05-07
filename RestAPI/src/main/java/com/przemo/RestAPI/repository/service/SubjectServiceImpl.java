package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.repository.parent.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService
{
	@Autowired
	private SubjectRepository subjectRepository;
	@Override
	public List<Subject> getAllSubjects() {
		// TODO Auto-generated method stub
		return subjectRepository.findAll();
	}

	@Override
	public Optional<Subject> getSubjectById(int id) {
		// TODO Auto-generated method stub
		return subjectRepository.findById(id);
	}

	@Override
	public Subject saveSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectRepository.save(subject);
	}

	@Override
	public Subject updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectRepository.save(subject);
	}

	@Override
	public void deleteSubject(int id) {
		subjectRepository.deleteById(id);		
	}

}
