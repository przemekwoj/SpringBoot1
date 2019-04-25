package com.przemo.RestAPI.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.przemo.RestAPI.entity.classes.Klasa;
import com.przemo.RestAPI.entity.parent.User;

@Entity
@DiscriminatorValue("Teacher")
public class Teacher extends User
{
	
	
	@Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	
	@OneToOne(mappedBy = "teacher",
            fetch = FetchType.LAZY)
	private Klasa klasa;

	public Klasa getKlasa() {
		return klasa;
	}

	public void setKlasa(Klasa klasa) {
		this.klasa = klasa;
	}
	
	
	
	
	
}

