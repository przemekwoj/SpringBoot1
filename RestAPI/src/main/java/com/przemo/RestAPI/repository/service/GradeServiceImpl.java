package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.repository.parent.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService
{
	@Autowired
	private GradeRepository gradeRepository;

	@Override
	public Optional<Grade> findParticularSubjectGrade(int subject_id, int user_id) 
	{
		return gradeRepository.findParticularSubjectGrade(subject_id, user_id);
	}

	@Override
	public List<Grade> getAllGrades() {
		return gradeRepository.findAll();
	}

	@Override
	public Optional<Grade> getGradeById(int id) {
		return gradeRepository.findById(id);
	}

	@Override
	public Grade saveGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public Grade updateGrade(Grade grade) {
		return gradeRepository.save(grade);
	}

	@Override
	public void deleteGrade(int id) {
		gradeRepository.deleteById(id);		
	}
	
	
}
