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

import com.dormy.constant.OtpStatus;

import lombok.Data;

@Entity
@Data
@Table(name = "USER_DETAILS")
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
	@NotNull
	private String password;

	private String otpStatus;
	
	private String role;


//	mobile_no   (Userid)
//	f_name
//	l_name
//	password
//	email
//	role
//	geo_loc

}
