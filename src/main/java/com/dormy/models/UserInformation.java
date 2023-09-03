package com.dormy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(unique=true)
	private String mobileNo;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	private String password;
	@Column(unique=true)
	@Email(message =" Please provide valid Email !!")
	private String email;
	private String role;
	private String geoLocation;

//	mobile_no   (Userid)
//	f_name
//	l_name
//	password
//	email
//	role
//	geo_loc

}
