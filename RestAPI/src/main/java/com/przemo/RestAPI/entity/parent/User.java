package com.przemo.RestAPI.entity.parent;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="roleType")
@Table(name = "users")
public abstract class User 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;
	
	@Column(name = "username",unique = true)
    @NotBlank(message = "username name should not be blank")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String username;
	
	@Column(name = "firstname")
    @NotBlank(message = "firstname name should not be blank")
	private String firstname;
	
	@Column(name = "lastname")
    @NotBlank(message = "lastname name should not be blank")
	private String lastname;
	
	@Column(name = "password")
    @NotBlank(message = "password name should not be blank")
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		int workload = 12;
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password, salt);
		this.password = hashed_password;
	}

	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", password=" + password + "]";
	}

	
	
	

}
