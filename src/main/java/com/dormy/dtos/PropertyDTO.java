package com.dormy.dtos;

import java.sql.Date;


import lombok.Data;


@Data
public class PropertyDTO {

	private int propertyId;
	private String propertyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private long pinCode;
	private Date dateRegistered;
	private String approvalstatus;
	private String managerName;
	private long managerMobNo;
	private String customerCareNo;
	private String propertyIcon;
	
	private String cutomField1;
	private String cutomField2;
	

}
