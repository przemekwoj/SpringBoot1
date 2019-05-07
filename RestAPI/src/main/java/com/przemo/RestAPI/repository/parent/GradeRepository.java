package com.przemo.RestAPI.repository.parent;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.przemo.RestAPI.entity.grade.Grade;
import com.przemo.RestAPI.entity.user.Admin;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer>
{
	@Query("SELECT grade FROM Grade grade WHERE grade.subject_id = :subject_id AND grade.student.user_id = :user_id")
	Optional<Grade> findParticularSubjectGrade(@Param("subject_id") int subject_id,@Param("user_id") int user_id); 
}
