package com.przemo.RestAPI.entity.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.przemo.RestAPI.entity.classes.Klasa;
import com.przemo.RestAPI.entity.parent.Subject;
import com.przemo.RestAPI.entity.parent.User;


@Entity
@DiscriminatorValue("Student")
public class Student  extends User
{	
	@Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
	name="students_subjects",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="subject_id")
	)
	private List<Subject> subjectsList;
	

	/*@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})		
	@JoinColumn(name = "klasa_id")
	private Klasa klasa;*/
	
	/*@OneToMany(mappedBy = "student",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Grade> gradesList;*/
	

	public List<Subject> getSubjectsList() {
		return subjectsList;
	}

	public void setSubjectsList(List<Subject> subjectsList) {
		this.subjectsList = subjectsList;
	}

	/*public Klasa getKlasa() {
		return klasa;
	}

	public void setKlasa(Klasa klasa) {
		this.klasa = klasa;
	}*/
	
	/*public List<Grade> getGradesList() {
		return gradesList;
	}

	public void setGradesList(List<Grade> gradesList) {
		this.gradesList = gradesList;
	}
	
	public void addGrade(Grade grade)
	{
		if(gradesList == null)
		{
			gradesList = new ArrayList<Grade>();
		}
		
		gradesList.add(grade);
		
		grade.setStudent(this);
	}
	*/
	public void addSubject(Subject subject)
	{
		if(subjectsList == null)
		{
			subjectsList = new ArrayList<Subject>();
		}
		
		subjectsList.add(subject);
	}

}
