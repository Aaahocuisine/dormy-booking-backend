package com.dormy.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Entity
@Data
@Table(name="property_info")
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long propertyId;
	@NotNull
	private String propertyName;
	@NotNull
	private String address1;
	private String address2;
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	private long pinCode;
	@JsonSerialize
	@JsonDeserialize
	private Date dateRegistered;
	@NotNull
	private String approvalstatus;
	@NotNull
	private String managerName;
	private long managerMobNo;
	private String customerCareNo;
	@Lob
	private byte[] propertyPicture;
	
	private String propertyIcon;
	@NotNull
	private String cutomField1;
	@NotNull
	private String cutomField2;
	private String cutomField3;
	private String cutomField4;

}
