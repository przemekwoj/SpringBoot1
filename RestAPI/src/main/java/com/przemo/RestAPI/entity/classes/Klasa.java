package com.przemo.RestAPI.entity.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.przemo.RestAPI.entity.user.Student;
import com.przemo.RestAPI.entity.user.Teacher;

@Entity
@Table(name = "klasy")
public class Klasa 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "klasa_id")
    private int klasa_id;
	
	@Column(name = "nameKlasa")
	private String nameKlasa;
	
	/*@OneToMany(mappedBy = "klasa",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Student> studentsList;*/
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Teacher teacher;

	public int getKlasa_id() {
		return klasa_id;
	}

	public void setKlasa_id(int klasa_id) {
		this.klasa_id = klasa_id;
	}

	public String getNameKlasa() {
		return nameKlasa;
	}

	public void setNameKlasa(String nameKlasa) {
		this.nameKlasa = nameKlasa;
	}

/*	public List<Student> getStudentsList() {
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
		
		student.setKlasa(this);
	}
	*/
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	

}
