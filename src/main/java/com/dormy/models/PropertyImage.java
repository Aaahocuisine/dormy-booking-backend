package com.dormy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Prop_Image")
public class PropertyImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;
	private String name;
	private String type;
	
	@Lob
	private byte[] imageData;
	

}
