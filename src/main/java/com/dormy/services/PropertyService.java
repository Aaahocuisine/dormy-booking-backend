package com.dormy.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dormy.dtos.PropertyDTO;
import com.dormy.models.Property;
import com.dormy.repos.PropertyRepository;

@Service
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepo;
	

	public Property save(PropertyDTO propertyDTO) {
		Property property =new Property();
		property.setAddress1(propertyDTO.getAddress1());
		property.setAddress2(propertyDTO.getAddress2());
		property.setApprovalstatus(propertyDTO.getApprovalstatus());
		property.setCity(propertyDTO.getCity());
		property.setCustomerCareNo(propertyDTO.getCustomerCareNo());
		property.setDateRegistered(propertyDTO.getDateRegistered());
		property.setManagerMobNo(propertyDTO.getManagerMobNo());
		property.setManagerName(propertyDTO.getManagerName());
		property.setState(propertyDTO.getState());
		property.setDateRegistered(propertyDTO.getDateRegistered());
		property.setPinCode(propertyDTO.getPinCode());
		property.setPropertyName(propertyDTO.getPropertyName());
		property.setCutomField1(propertyDTO.getCutomField1());
		property.setCutomField2(propertyDTO.getCutomField2());
	//	property.setPropertyPicture(propertyPicture.getBytes());
		

		 return propertyRepo.save(property);

	}

	public List<Property> finalAll() {
		return propertyRepo.findAll();
	}

	public Property findById(long id) {
		return propertyRepo.getById(id);
	}
	
	public void deleteById(long id) {
		propertyRepo.deleteById(id);
	}
	

}
