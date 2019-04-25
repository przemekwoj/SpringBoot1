package com.przemo.RestAPI.entity.parent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.przemo.RestAPI.entity.user.Student;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="name")
@Table(name = "subjects")
public abstract class Subject
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subject_id;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
	name="students_subjects",
	joinColumns=@JoinColumn(name="subject_id"),
	inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<Student> studentsList;

	
	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	
	
	@Override
	public String toString() {
		return "Subject [subject_id=" + subject_id + ", studentsList=" + studentsList + "]";
	}
	
	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}
	
	public void addStudent(Student student)
	{
		if(studentsList == null)
		{
			studentsList = new ArrayList<Student>();
		}
		
		studentsList.add(student);
	}
	
	
	
}
