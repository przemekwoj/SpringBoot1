package com.przemo.RestAPI.entity.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.przemo.RestAPI.entity.parent.User;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends User
{
	@Transient
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
}
