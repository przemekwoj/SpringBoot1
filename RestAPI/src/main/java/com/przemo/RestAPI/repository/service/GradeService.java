package com.przemo.RestAPI.repository.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.user.Student;

public interface GradeService 
{
	
	public Optional<Grade> findParticularSubjectGrade(int subject_id, int user_id);
	
	public List<Grade> getAllGrades();
	
	public Optional<Grade> getGradeById(int id);
	
	public Grade saveGrade(Grade grade);
	
	public Grade updateGrade(Grade grade);
	
	public void deleteGrade(int id);

}
