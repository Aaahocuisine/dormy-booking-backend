package com.dormy.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.dormy.constant.Approvalstatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="property_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long propertyId;
	
	@NotNull
	@Column(unique = true)
	private Long propertyNo;
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
	private Approvalstatus approvalstatus;
	
	@NotNull
	private String managerName;
	
	@NotNull
	private long managerMobNo;
	private String customerCareNo;
	
	
	
	@NotNull
	private String country;
	
	@NotNull
	private String ownerName;
	
	@NotNull
	private String googleMapLink;
	

}
