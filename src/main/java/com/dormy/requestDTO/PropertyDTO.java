package com.dormy.requestDTO;

import java.sql.Date;

import com.dormy.constant.Approvalstatus;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PropertyDTO {

	private int propertyId;
	private Long propertyNo;
	private String propertyName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private long pinCode;
	private Date dateRegistered;
	private Approvalstatus approvalstatus;
	private String managerName;
	private String ownerName;
	private long managerMobNo;
	private String customerCareNo;
	private String googleMapLink;
	
	

}
