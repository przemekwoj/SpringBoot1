package com.przemo.RestAPI.entity.subjects;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.przemo.RestAPI.entity.parent.Subject;

@Entity
@DiscriminatorValue("Mathematics")
public class Mathematics extends Subject
{
	@Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
}
