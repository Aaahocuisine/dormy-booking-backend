package com.dormy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="icon_details")
public class Icon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long iconId;
	private String name;
	private String type;
	private long propertyNo;
	
	@Lob
	private byte[] imageData;
	

}
